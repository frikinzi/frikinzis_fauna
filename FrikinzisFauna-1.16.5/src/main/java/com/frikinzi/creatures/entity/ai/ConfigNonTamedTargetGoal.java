package com.frikinzi.creatures.entity.ai;

import java.util.function.Predicate;
import javax.annotation.Nullable;

import com.frikinzi.creatures.config.CreaturesConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.TameableEntity;

public class ConfigNonTamedTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    private final TameableEntity tamableMob;

    public ConfigNonTamedTargetGoal(TameableEntity p_i48571_1_, Class<T> p_i48571_2_, boolean p_i48571_3_, @Nullable Predicate<LivingEntity> p_i48571_4_) {
        super(p_i48571_1_, p_i48571_2_, 10, p_i48571_3_, false, p_i48571_4_);
        this.tamableMob = p_i48571_1_;
    }

    public boolean canUse() {
        if (super.canUse()) {
        return !this.tamableMob.isBaby() && !this.tamableMob.isTame() && CreaturesConfig.raptor_attacks.get() && super.canUse(); } return false;
    }

    public boolean canContinueToUse() {
        return this.targetConditions != null ? this.targetConditions.test(this.mob, this.target) : super.canContinueToUse();
    }
}