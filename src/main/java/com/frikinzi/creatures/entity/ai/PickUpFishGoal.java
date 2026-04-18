package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.KingfisherEntity;
import com.frikinzi.creatures.registry.CreaturesItems;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;
import java.util.List;

public class PickUpFishGoal extends Goal {
    private final KingfisherEntity bird;
    private ItemEntity target;

    public PickUpFishGoal(KingfisherEntity bird) {
        this.bird = bird;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!bird.getMainHandItem().isEmpty()) return false;
        if (bird.pickupCooldown > 0) return false;

        List<ItemEntity> items = bird.level().getEntitiesOfClass(
                ItemEntity.class,
                bird.getBoundingBox().inflate(12.0),
                e -> {
                    if (bird.getVariant() == 7) {
                        // guam kingfisher mealworms only
                        return e.getItem().is(CreaturesItems.MEALWORMS.get());
                    }
                    return e.getItem().is(Items.SALMON)
                            || e.getItem().is(Items.COD)
                            || e.getItem().is(Items.TROPICAL_FISH)
                            || e.getItem().is(CreaturesItems.CRAB_PINCERS.get())
                            || e.getItem().is(CreaturesItems.RAW_TROUT.get())
                            || e.getItem().is(CreaturesItems.GOURAMI.get());
                }
        );
        if (items.isEmpty()) return false;
        target = items.get(0);
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return target != null && target.isAlive() && bird.getMainHandItem().isEmpty();
    }

    @Override
    public void tick() {
        if (target == null) return;
        bird.getNavigation().moveTo(target, 1.2);

        if (bird.distanceToSqr(target) < 1.5 * 1.5) {
            // Pick it up
            ItemStack fish = target.getItem().copy();
            fish.setCount(1);
            bird.setHeldItem(fish);
            target.discard();
            target = null;
        }
    }

    @Override
    public void stop() {
        target = null;
    }
}