package com.frikinzi.creatures.entity.ai;

import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;

import java.util.EnumSet;

public class BabyHurtGoal extends HurtByTargetGoal {

    public BabyHurtGoal(CreatureEntity p_i50317_1_, Class<?>... p_i50317_2_) {
        super(p_i50317_1_, p_i50317_2_);
    }

    public boolean canUse() {
        CreaturesBirdEntity bird = (CreaturesBirdEntity) this.mob;
        if (bird.isBaby()) {
            return true;
        } else {
            return false;
        }
    }
}
