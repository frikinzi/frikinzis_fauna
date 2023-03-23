package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.TameableFlyingBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Random;
import java.util.Set;

public class RavenEntity extends TameableFlyingBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public RavenEntity(EntityType<? extends RavenEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());

    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if ((this.isFlying()) || !this.isOnGround()) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.isSleeping()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.isInSittingPose()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (!event.isMoving())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<RavenEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.FLYING_SPEED, (double)0.4F).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public static boolean checkBirdSpawnRules(EntityType<RavenEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 2;
    }

    public RavenEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        RavenEntity ravenEntity = CreaturesEntities.RAVEN.get().create(p_149088_);
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get()) == 1) {
            ravenEntity.setVariant(2); }
        else {
            ravenEntity.setVariant(this.getVariant());
        }
        ravenEntity.setGender(this.random.nextInt(2));
        ravenEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return ravenEntity;
    }

    public int determineVariant() {
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get()) == 1) {
            this.setVariant(2); }
        else {
            this.setVariant(1);
        }
        return this.random.nextInt(this.noVariants()) + 1;

    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(p_30392_ instanceof RavenEntity)) {
            return false;
        } else {
            RavenEntity raven = (RavenEntity)p_30392_;
            if (!raven.isTame()) {
                return false;
            } else if (raven.isInSittingPose()) {
                return false;
            } else {
                return this.isInLove() && raven.isInLove();
            }
        }
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.4F;
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.RAVEN_AMBIENT; } else {
            return null;
        }
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(Items.ROTTEN_FLESH, Items.EGG, Items.CHICKEN);
    }

    public Set<Item> getTameFood() {
        return Sets.newHashSet(Items.ROTTEN_FLESH, Items.EGG, Items.CHICKEN);
    }


    public double getHatchChance() {
        return CreaturesConfig.raven_hatch_chance.get();
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.ROTTEN_FLESH, 1);
    }

}
