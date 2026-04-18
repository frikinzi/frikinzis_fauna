package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.ModEventSubscriber;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class SwimToHookGoal extends Goal {
    private final FishBase fish;
    private net.minecraft.world.entity.projectile.FishingHook targetHook = null;
    private static final double BITE_RANGE = 0.8;

    public SwimToHookGoal(FishBase fish) {
        this.fish = fish;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        List<FishingHook> hooks =
                fish.level().getEntitiesOfClass(
                        FishingHook.class,
                        fish.getBoundingBox().inflate(12.0),
                        h -> {
                            if (!h.isInWater() || h.getPlayerOwner() == null) return false;
                            if (ModEventSubscriber.FFGuideInteractEvent.isBitten(h)) return false;
                            Player owner = h.getPlayerOwner();
                            ItemStack rod = owner.getMainHandItem();
                            if (!rod.is(Items.FISHING_ROD)) return false;
                            if (!ModEventSubscriber.FFGuideInteractEvent.hasBait(rod)) return false;
                            // Check if the bait matches this fish's food preference
                            String baitType = ModEventSubscriber.FFGuideInteractEvent.getBaitType(rod);
                            Item preferredFood = fish.getFoodItem();
                            String preferredKey = ForgeRegistries.ITEMS.getKey(preferredFood).getPath();
                            return preferredKey.equals(baitType);
                        });
        if (hooks.isEmpty()) return false;
        targetHook = hooks.stream()
                .min(Comparator.comparingDouble(h -> h.distanceToSqr(fish)))
                .orElse(null);
        return targetHook != null;
    }

    @Override
    public boolean canContinueToUse() {
        return targetHook != null
                && targetHook.isAlive()
                && targetHook.isInWater()
                && !ModEventSubscriber.FFGuideInteractEvent.isBitten(targetHook); // don't keep chasing after bite
    }

    @Override
    public void tick() {
        if (targetHook == null) return;

        fish.getNavigation().moveTo(targetHook.getX(), targetHook.getY(), targetHook.getZ(), 1.5D);

        double dist = fish.distanceTo(targetHook);

        if (dist < 3.0) {
            double dx = targetHook.getX() - fish.getX();
            double dy = targetHook.getY() - fish.getY();
            double dz = targetHook.getZ() - fish.getZ();
            fish.setDeltaMovement(dx * 0.2, dy * 0.2, dz * 0.2);
        }

        if (dist < 1.5) {
            ModEventSubscriber.FFGuideInteractEvent.registerBite(targetHook, fish);
            fish.getNavigation().stop();
        }
    }

    @Override
    public void stop() {
        targetHook = null;
        fish.getNavigation().stop();
    }
}