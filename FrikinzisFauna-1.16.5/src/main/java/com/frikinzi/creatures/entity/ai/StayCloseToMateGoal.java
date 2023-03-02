package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.registry.ModEntityTypes;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;

import java.util.List;
import java.util.UUID;

public class StayCloseToMateGoal extends Goal {
    private final CreaturesBirdEntity animal;
    private final UUID mate;
    private CreaturesBirdEntity egg;
    private final double speedModifier;
    private int timeToRecalcPath;
    private EntityPredicate predicate;
    private EntityPredicate predicatetwo;

    public StayCloseToMateGoal(CreaturesBirdEntity p_i1626_1_, double p_i1626_2_) {
        this.animal = p_i1626_1_;
        this.mate = this.animal.getMateUUID();
        this.speedModifier = p_i1626_2_;
        predicate = (new EntityPredicate()).range(16.0D).allowInvulnerable().selector((p_220844_0_) -> {
            return ((CreaturesEggEntity)p_220844_0_).getSpecies() == ModEntityTypes.getIntFromBirdEntity(animal);
        });
        predicatetwo = (new EntityPredicate()).range(16.0D).allowInvulnerable().selector((p_220844_0_) -> {
            return ((CreaturesBirdEntity)p_220844_0_).getGender() != 1;
        });
    }

    public boolean canUse() {
        if (!this.animal.isMonogamous()) {
            return false;
        }
        if (this.mate == null) {
            return false;
        }
        if (this.animal.getGender() != 1) {
            return false;
        }
        if (this.animal.isBaby()) {
            return false;
        } else {
 //           List<CreaturesBirdEntity> parentsnearby = egg.level.getNearbyEntities(animal.getClass(), predicatetwo, egg, egg.getBoundingBox().inflate(5.0D, 4.0D, 5.0D));
 //           if (!parentsnearby.isEmpty()) {
 //               return false;
 //           }

            List<CreaturesBirdEntity> list = animal.level.getNearbyEntities(CreaturesBirdEntity.class, this.predicate, animal, animal.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            CreaturesBirdEntity animalentity = null;
            //System.out.println(list);
            double d0 = Double.MAX_VALUE;

            for(CreaturesBirdEntity animalentity1 : list) {
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
            } else if (animalentity.getMateUUID() != this.mate) {
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