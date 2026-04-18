package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class FindBreedTargetBehavior<T extends CreaturesBirdEntity> extends Behavior<T> {

    public FindBreedTargetBehavior() {
        super(ImmutableMap.of(
                MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT,
                MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryStatus.VALUE_PRESENT
        ));
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, T animal) {
        return animal.isInLove();
    }

    @Override
    protected void start(ServerLevel level, T animal, long time) {
        animal.getBrain()
                .getMemory(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES)
                .ifPresent(entities -> entities.findClosest(e ->
                        e.getClass() == animal.getClass()
                                && e instanceof CreaturesBirdEntity other
                                && other.isInLove()
                                && other.getGender() != animal.getGender()
                ).ifPresent(partner -> {
                    ((Brain) animal.getBrain()).setMemory(
                            MemoryModuleType.BREED_TARGET,
                            partner
                    );
                }));
    }
}