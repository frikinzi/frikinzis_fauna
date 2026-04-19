package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.SeagullEntity;
import com.frikinzi.creatures.entity.SkuaEntity;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class FleeWithFoodGoal extends AvoidEntityGoal<LivingEntity> {
    private final CreaturesBirdEntity bird;

    public FleeWithFoodGoal(CreaturesBirdEntity bird) {
        super(bird, LivingEntity.class, 8.0F, 1.4, 1.6,
                e -> e.isAlive()
                        && !(e instanceof SeagullEntity)
                        && !(e instanceof SkuaEntity)
                        && e.getBbWidth() > 0.5f);
        this.bird = bird;
    }

//    @Override
//    public boolean canUse() {
//        if (bird.getMainHandItem().isEmpty()) return false;
//        return super.canUse();
//    }

    @Override
    public boolean canContinueToUse() {
        if (bird.getMainHandItem().isEmpty()) return false;
        return super.canContinueToUse();
    }

    @Override
    public boolean canUse() {
        if (bird.getMainHandItem().isEmpty()) return false;

        toAvoid = (LivingEntity) bird.level().getNearestEntity(
                bird.level().getEntitiesOfClass(LivingEntity.class,
                        bird.getBoundingBox().inflate(8.0),
                        e -> e.isAlive()
                                && !(e instanceof SeagullEntity)
                                && !(e instanceof SkuaEntity)
                                && e.getBbWidth() > 0.5f),
                TargetingConditions.forCombat().range(8.0),
                bird, bird.getX(), bird.getY(), bird.getZ());

        if (toAvoid == null) return false;

        double dx = bird.getX() - toAvoid.getX();
        double dz = bird.getZ() - toAvoid.getZ();
        double len = Math.sqrt(dx * dx + dz * dz);
        if (len < 0.01) return false;

        double fleeX = bird.getX() + (dx / len) * 16;
        double fleeY = bird.getY() + 3;
        double fleeZ = bird.getZ() + (dz / len) * 16;

        this.path = pathNav.createPath(fleeX, fleeY, fleeZ, 0);
        return this.path != null;
    }
}