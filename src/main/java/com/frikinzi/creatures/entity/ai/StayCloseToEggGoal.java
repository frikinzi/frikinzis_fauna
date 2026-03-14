package com.frikinzi.creatures.entity.ai;

import java.util.List;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.registry.ModEventSubscriber;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class StayCloseToEggGoal extends Goal {
    private final CreaturesBirdEntity animal;
    private EggEntity egg;
    private final double speedModifier;
    private int timeToRecalcPath;
    private TargetingConditions predicate;
    private TargetingConditions predicatetwo;

    public StayCloseToEggGoal(CreaturesBirdEntity p_i1626_1_, double p_i1626_2_) {
        this.animal = p_i1626_1_;
        this.speedModifier = p_i1626_2_;
        predicate = TargetingConditions.forNonCombat()
            .range(16.0D)
            .ignoreLineOfSight()
            .selector((e) -> {
                return ((EggEntity) e).getSpecies() == ModEventSubscriber.getBirdEntityMap().inverse().get(animal.getType());
            });
        predicatetwo = TargetingConditions.forNonCombat()
            .range(16.0D)
            .ignoreLineOfSight()
            .selector((e) -> {
                return ((CreaturesBirdEntity) e).getGender() != 1;
            });
    }

    public boolean canUse() {
        if (this.animal.getGender() == 1) {
            return false;
        }
        if (this.animal.isBaby()) {
            return false;
        } else {
//          List<CreaturesBirdEntity> parentsnearby = egg.level().getNearbyEntities(animal.getClass(), predicatetwo, egg, egg.getBoundingBox().inflate(5.0D, 4.0D, 5.0D));
//          if (!parentsnearby.isEmpty()) {
//              return false;
//          }
            List<EggEntity> list = animal.level().getNearbyEntities(EggEntity.class, this.predicate, animal, animal.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            EggEntity animalentity = null;
            double d0 = Double.MAX_VALUE;
            for (EggEntity animalentity1 : list) {
                double d1 = this.animal.distanceToSqr(animalentity1);
                if (!(d1 > d0)) {
                    d0 = d1;
                    animalentity = animalentity1;
                }
            }
            if (animalentity == null) {
                return false;
            } else if (d0 < 9.0D) {
                return false;
            } else if (animalentity.getParentUUID() != this.animal.getUUID()) {
                return false;
            } else {
                this.egg = animalentity;
                return true;
            }
        }
    }

    public boolean canContinueToUse() {
        if (!this.egg.isAlive()) {
            return false;
        } else {
            double d0 = this.animal.distanceToSqr(this.egg);
            return !(d0 < 9.0D) && !(d0 > 256.0D);
        }
    }

    public void start() {
        this.timeToRecalcPath = 0;
    }

    public void stop() {
        this.egg = null;
    }

    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            this.animal.getNavigation().moveTo(this.egg, this.speedModifier);
        }
    }
}