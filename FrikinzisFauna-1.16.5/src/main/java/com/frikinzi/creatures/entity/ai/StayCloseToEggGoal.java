package com.frikinzi.creatures.entity.ai;

import java.util.List;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.registry.ModEntityTypes;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;

public class StayCloseToEggGoal extends Goal {
    private final CreaturesBirdEntity animal;
    private CreaturesEggEntity egg;
    private final double speedModifier;
    private int timeToRecalcPath;
    private EntityPredicate predicate;
    private EntityPredicate predicatetwo;

    public StayCloseToEggGoal(CreaturesBirdEntity p_i1626_1_, double p_i1626_2_) {
        this.animal = p_i1626_1_;
        this.speedModifier = p_i1626_2_;
        predicate = (new EntityPredicate()).range(16.0D).allowInvulnerable().selector((p_220844_0_) -> {
            return ((CreaturesEggEntity)p_220844_0_).getSpecies() == ModEntityTypes.getIntFromBirdEntity(animal);
        });
        predicatetwo = (new EntityPredicate()).range(16.0D).allowInvulnerable().selector((p_220844_0_) -> {
            return ((CreaturesBirdEntity)p_220844_0_).getGender() != 1;
        });
    }

    public boolean canUse() {
        if (this.animal.getGender() == 1) {
            return false;
        }
        if (this.animal.isBaby()) {
            return false;
        } else {
 //           List<CreaturesBirdEntity> parentsnearby = egg.level.getNearbyEntities(animal.getClass(), predicatetwo, egg, egg.getBoundingBox().inflate(5.0D, 4.0D, 5.0D));
 //           if (!parentsnearby.isEmpty()) {
 //               return false;
 //           }

            List<CreaturesEggEntity> list = animal.level.getNearbyEntities(CreaturesEggEntity.class, this.predicate, animal, animal.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            CreaturesEggEntity animalentity = null;
            //System.out.println(list);
            double d0 = Double.MAX_VALUE;

            for(CreaturesEggEntity animalentity1 : list) {
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