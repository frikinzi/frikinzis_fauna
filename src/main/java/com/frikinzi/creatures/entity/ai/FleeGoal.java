package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class FleeGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    private final CreaturesBirdEntity bird;

    public FleeGoal(CreaturesBirdEntity bird, Class<T> avoidClass,
                    float maxDist, double walkSpeed, double sprintSpeed) {
        super(bird, avoidClass, maxDist, walkSpeed, sprintSpeed);
        this.bird = bird;
    }

    @Override
    public boolean canUse() {
        toAvoid = bird.level().getNearestEntity(
                bird.level().getEntitiesOfClass(avoidClass,
                        bird.getBoundingBox().inflate(maxDist, 3.0, maxDist),
                        e -> true),
                TargetingConditions.forCombat().range(maxDist),
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

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse();
    }
}