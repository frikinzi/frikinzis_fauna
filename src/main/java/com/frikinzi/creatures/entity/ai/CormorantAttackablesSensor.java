package com.frikinzi.creatures.entity.ai;


import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.NearestVisibleLivingEntitySensor;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;

public class CormorantAttackablesSensor extends NearestVisibleLivingEntitySensor {
    public static final float TARGET_DETECTION_DISTANCE = 8.0F;

    protected boolean isMatchingEntity(LivingEntity p_148266_, LivingEntity p_148267_) {
        return this.isClose(p_148266_, p_148267_) && p_148267_.isInWaterOrBubble() && (this.isHostileTarget(p_148267_) || this.isHuntTarget(p_148266_, p_148267_)) && Sensor.isEntityAttackable(p_148266_, p_148267_);
    }

    private boolean isHuntTarget(LivingEntity p_148272_, LivingEntity p_148273_) {
        return !p_148272_.getBrain().hasMemoryValue(MemoryModuleType.HAS_HUNTING_COOLDOWN) && (p_148273_ instanceof Salmon || p_148273_ instanceof Cod || p_148273_ instanceof TropicalFish);
    }

    private boolean isHostileTarget(LivingEntity entity) {
        return entity instanceof Cod
                || entity instanceof Salmon
                || entity instanceof TropicalFish
                || entity instanceof Pufferfish;
    }

    private boolean isClose(LivingEntity p_148275_, LivingEntity p_148276_) {
        return p_148276_.distanceToSqr(p_148275_) <= 64.0D;
    }

    protected MemoryModuleType<LivingEntity> getMemory() {
        return MemoryModuleType.NEAREST_ATTACKABLE;
    }
}