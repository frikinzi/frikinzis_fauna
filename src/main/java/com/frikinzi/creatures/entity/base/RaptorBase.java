package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Set;

public class RaptorBase extends CreaturesFlyingBird {
    public int ticksToSit;
    private PanicGoal PanicGoal;

    public RaptorBase(EntityType<? extends RaptorBase> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);
    }

    protected void registerGoals() {
        super.registerGoals();
        if (!this.isBaby()) {
            this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
            this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
            this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
            this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
            this.targetSelector.removeGoal(PanicGoal);
        }
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
        //this.targetSelector.addGoal(1, new CreaturesBirdEntity.DefendBabyGoal());
        // this.targetSelector.addGoal(3, (new BabyHurtGoal(this)).setAlertOthers());
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        if (super.doHurtTarget(p_70652_1_)) {
            if (p_70652_1_ instanceof LivingEntity && this.getY() < 80 && CreaturesConfig.raptor_throws.get() == true && !(p_70652_1_ instanceof CreaturesBirdEntity)) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, 0.8D, 0));
                p_70652_1_.setDeltaMovement(p_70652_1_.getDeltaMovement().add(0, 0.8D, 0));
            }
            return true;
        } else {
            return false;
        }
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        Item item = itemstack.getItem();
        if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                if (!p_230254_1_.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.heal((float) item.getFoodProperties().getNutrition());
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(Items.RABBIT, Items.CHICKEN, CreaturesItems.SMALL_BIRD_MEAT.get(), CreaturesItems.LARGE_BIRD_MEAT.get());
    }


}
