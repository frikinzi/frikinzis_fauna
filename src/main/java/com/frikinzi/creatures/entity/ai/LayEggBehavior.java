package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.GameRules;

import java.util.Random;
public class LayEggBehavior<T extends CreaturesBirdEntity> extends Behavior<T> {

    private T partner;

    public LayEggBehavior() {
        super(ImmutableMap.of(
                MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_PRESENT,
                MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT
        ));
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel level, T animal) {
        LivingEntity target = animal.getBrain().getMemory(MemoryModuleType.BREED_TARGET).orElse(null);
        if (!(target instanceof CreaturesBirdEntity partner)) return false;
        return animal.isInLove() && partner.isInLove()
                && animal.distanceToSqr(partner) < 9.0D;
    }

    @Override
    protected void start(ServerLevel level, T animal, long time) {
        T breedTarget = (T) animal.getBrain().getMemory(MemoryModuleType.BREED_TARGET).orElse(null);
        if (breedTarget == null) return;

        animal.getBrain().setMemory(MemoryModuleType.BREED_TARGET, breedTarget);
        breedTarget.getBrain().setMemory(MemoryModuleType.BREED_TARGET, animal);

        this.partner = breedTarget;
        BehaviorUtils.lockGazeAndWalkToEachOther(animal, partner, 0.6F);
    }

    @Override
    protected boolean canStillUse(ServerLevel level, T animal, long time) {
        return partner != null && partner.isAlive() && partner.isInLove()
                && animal.isInLove()
                && animal.distanceToSqr(partner) < 9.0D;
    }

    @Override
    protected void tick(ServerLevel level, T animal, long time) {
        BehaviorUtils.lockGazeAndWalkToEachOther(animal, partner, 0.6F);
        if (animal.distanceToSqr(partner) < 4.0D) {
            spawnEgg(level, animal);
            animal.getBrain().eraseMemory(MemoryModuleType.BREED_TARGET);
        }
    }

    @Override
    protected void stop(ServerLevel level, T animal, long time) {
        partner = null;
        animal.getBrain().eraseMemory(MemoryModuleType.BREED_TARGET);
    }

    private void spawnEgg(ServerLevel level, T animal) {
        int c = animal.getClutchSize();
        for (int j = 1; j <= c; j++) {
            EggEntity egg = animal.layEgg(this.partner);
            if (egg != null) {
                animal.setAge(6000);
                this.partner.setAge(6000);
                animal.resetLove();
                this.partner.resetLove();

                T mother = animal.getGender() == 0 ? animal : this.partner;

                float f = (float)(animal.getRandom().nextGaussian() * 0.05
                        + ((animal.getHeightMultiplier() + this.partner.getHeightMultiplier()) / 2));
                egg.setHeightMultiplier(f);

                Random rand = new Random();
                egg.setPos(
                        Math.floor(mother.getX()) + 0.5 + (-1 + rand.nextFloat() * 2),
                        Math.floor(mother.getY()) + 0.5,
                        Math.floor(mother.getZ()) + 0.5 + (-1 + rand.nextFloat() * 2)
                );
                level.addFreshEntityWithPassengers(egg);
            }

            level.broadcastEntityEvent(animal, (byte) 18);
            RandomSource random = animal.getRandom();
            for (int i = 0; i < 17; ++i) {
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                double d3 = random.nextDouble() * animal.getBbWidth() * 2.0D - animal.getBbWidth();
                double d4 = 0.5D + random.nextDouble() * animal.getBbHeight();
                double d5 = random.nextDouble() * animal.getBbWidth() * 2.0D - animal.getBbWidth();
                level.addParticle(ParticleTypes.HEART,
                        animal.getX() + d3, animal.getY() + d4, animal.getZ() + d5, d0, d1, d2);
            }

            if (level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                level.addFreshEntity(new ExperienceOrb(level,
                        animal.getX(), animal.getY(), animal.getZ(),
                        animal.getRandom().nextInt(7) + 1));
            }
        }
    }
}