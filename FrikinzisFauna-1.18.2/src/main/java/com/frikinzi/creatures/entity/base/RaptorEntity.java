package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.google.common.collect.Sets;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Set;

public class RaptorEntity extends TameableFlyingBirdEntity {

    public RaptorEntity(EntityType<? extends RaptorEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));

    }

    public Set<Item> getTameFood() {
        return Sets.newHashSet(Items.CHICKEN, Items.RABBIT, CreaturesItems.SMALL_BIRD_MEAT.get(), Items.BEEF, Items.PORKCHOP, Items.MUTTON);
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        if (super.doHurtTarget(p_70652_1_)) {
            if (p_70652_1_ instanceof LivingEntity && this.getY() < 80 && CreaturesConfig.raptor_throws.get() && !(p_70652_1_ instanceof CreaturesBirdEntity)) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, 0.6D, 0));
                p_70652_1_.setDeltaMovement(p_70652_1_.getDeltaMovement().add(0, 0.8D, 0));
            }
            return true;
        } return false;
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        Item item = itemstack.getItem();
        if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                if (!p_230254_1_.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }

}

