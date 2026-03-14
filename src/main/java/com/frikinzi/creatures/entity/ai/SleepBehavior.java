package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
public class SleepBehavior extends Behavior<CreaturesBirdEntity> {

    private long wakeTime = -1L;
    private static final long WAKE_COOLDOWN = 100L; // 5 seconds before can sleep again

    public SleepBehavior() {
        super(ImmutableMap.of()); // remove WALK_TARGET precondition
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, CreaturesBirdEntity animal) {
        // don't restart sleep immediately after waking
        if (wakeTime >= 0 && level.getGameTime() - wakeTime < WAKE_COOLDOWN) {
            return false;
        }
        return canSleep(animal);
    }

    @Override
    protected boolean canStillUse(ServerLevel level, CreaturesBirdEntity animal, long time) {
        return canSleep(animal);
    }

    private boolean canSleep(CreaturesBirdEntity animal) {
        return animal.timeSleep()
                && !animal.isFlying()
                && !animal.isInWaterOrRain()
                && !animal.isInPowderSnow;
    }

    @Override
    protected void start(ServerLevel level, CreaturesBirdEntity animal, long time) {
        animal.setSleeping(true);
        animal.getNavigation().stop();
        animal.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        animal.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
    }

    @Override
    protected void stop(ServerLevel level, CreaturesBirdEntity animal, long time) {
        wakeTime = level.getGameTime();
        animal.setSleeping(false);
        animal.clearStates();
        animal.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);

    }

    @Override
    protected void tick(ServerLevel level, CreaturesBirdEntity animal, long time) {
        animal.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        animal.getBrain().eraseMemory(MemoryModuleType.LOOK_TARGET);
        animal.getNavigation().stop();
    }
}