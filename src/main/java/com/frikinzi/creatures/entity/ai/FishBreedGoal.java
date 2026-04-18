package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.FishBase;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FishBreedGoal extends Goal {
    private final FishBase fish;
    private FishBase partner;
    private int loveTimer = 0;

    public FishBreedGoal(FishBase fish) {
        this.fish = fish;
    }

    @Override
    public boolean canUse() {
        if (fish.isBaby()) return false;
        if (fish.coolDown > 0) return false;
        if (!fish.isWantsToBreed()) return false;
        
        partner = findPartner();
        return partner != null;
    }

    @Override
    public boolean canContinueToUse() {
        return partner != null 
                && partner.isAlive() 
                && !partner.isBaby()
                && partner.coolDown <= 0
                && partner.isWantsToBreed()
                && loveTimer < 60;

    }

    @Override
    public void start() {
        loveTimer = 0;
    }

    @Override
    public void stop() {
        partner = null;
        loveTimer = 0;
        fish.setWantsToBreed(false);
//        partner.wantsToBreed = false;
    }

    @Override
    public void tick() {
        if (partner == null) return;
        
        fish.getNavigation().moveTo(partner, 1.0);
        loveTimer++;

        if (fish.distanceTo(partner) < 2.0 && loveTimer >= 20) {
            if (fish.level() instanceof ServerLevel serverLevel) {
                fish.layEgg(serverLevel, partner);
                fish.coolDown = fish.getRandom().nextInt(6000) + 6000;
                partner.coolDown = fish.getRandom().nextInt(6000) + 6000;
                fish.setWantsToBreed(false);
                partner.setWantsToBreed(false);
                // Heart particles
                for (int i = 0; i < 7; i++) {
                    double dx = fish.getRandom().nextGaussian() * 0.02;
                    double dy = fish.getRandom().nextGaussian() * 0.02;
                    double dz = fish.getRandom().nextGaussian() * 0.02;
                    serverLevel.sendParticles(ParticleTypes.HEART,
                            fish.getX(), fish.getY() + fish.getBbHeight(),
                            fish.getZ(), 1, dx, dy, dz, 0.1);
                }
            }
            partner = null;
            loveTimer = 0;
        }
    }

    private FishBase findPartner() {
        TargetingConditions predicate = TargetingConditions.forNonCombat()
                .range(10.0)
                .ignoreLineOfSight()
                .selector(e -> e.getClass() == fish.getClass()
                        && !((FishBase) e).isBaby()
                        && ((FishBase) e).coolDown <= 0
                        && ((FishBase) e).isWantsToBreed());

        List<FishBase> nearby = fish.level().getNearbyEntities(
                FishBase.class, predicate, fish,
                fish.getBoundingBox().inflate(10.0));

        return nearby.isEmpty() ? null : nearby.get(0);
    }
}