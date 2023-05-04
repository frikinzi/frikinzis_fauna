package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.google.common.collect.Sets;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Set;

public class TameableFlyingBirdEntity extends CreaturesFlyingBirdEntity {

    public TameableFlyingBirdEntity(EntityType<? extends TameableFlyingBirdEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new CreaturesBirdEntity.SleepGoal());
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new CreaturesBirdEntity.FollowGoal());
        this.goalSelector.addGoal(5, new CreaturesFlyingBirdEntity.BirdWanderGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, this.getBirdFood(), false));
    }

    public Set<Item> getTameFood() {
        return Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    }

    public InteractionResult mobInteract(Player p_29414_, InteractionHand p_29415_) {
        ItemStack itemstack = p_29414_.getItemInHand(p_29415_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE.get()) {
            Creatures.PROXY.setReferencedMob(this);
            if (this.level.isClientSide) {
                Creatures.PROXY.openCreaturesGui();
            }
            return InteractionResult.SUCCESS;
        }
        if (itemstack.getItem() == Items.STICK && this.isTame() && this.getOwner() == p_29414_) {
            if (this.isWandering() == 0) {
                if (this.level.isClientSide) {
                    Component i = new TranslatableComponent("message.wander");
                    this.getOwner().sendMessage(i, Util.NIL_UUID);
                }
                this.setWandering(1);
            } else {
                if (this.level.isClientSide) {
                    Component i = new TranslatableComponent("message.follow");
                    this.getOwner().sendMessage(i, Util.NIL_UUID);
                }
                this.setWandering(0);
            }
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
        if (!this.isTame() && getTameFood().contains(itemstack.getItem())) {
            if (!p_29414_.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (!this.isSilent()) {
                this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.PARROT_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }

            if (!this.level.isClientSide) {
                if (this.random.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_29414_)) {
                    this.tame(p_29414_);
                    this.level.broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte)6);
                }
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (!this.isFlying() && this.isTame() && this.isOwnedBy(p_29414_) && !getTameFood().contains(itemstack.getItem()) && itemstack.getItem() != CreaturesItems.BIRD_CARRIER.get()) {
            if (!this.level.isClientSide) {
                this.setOrderedToSit(!this.isOrderedToSit());
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(p_29414_, p_29415_);
        }
    }


}
