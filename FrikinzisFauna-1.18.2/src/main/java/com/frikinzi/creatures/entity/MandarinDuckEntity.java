package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.ForgeMod;
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

public class MandarinDuckEntity extends CreaturesBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private int featherTime = this.random.nextInt(6000) + 6000;

    public MandarinDuckEntity(EntityType<? extends MandarinDuckEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new SleepGoal());
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, this.getBirdFood(), false));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!this.isBaby()) {
            if (event.isMoving() && this.onGround) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            if (!this.onGround && !this.isInWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            if (this.isSleeping()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            if (this.isInWater() && !event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("swim2", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            if (this.isInWater() && event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("swim2", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } else {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            if (!this.onGround && !this.isInWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("flying", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            if (this.isSleeping()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<MandarinDuckEntity>(this, "controller", 0, this::predicate));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(ForgeMod.SWIM_SPEED.get(), 3.0);
    }

    public static boolean checkBirdSpawnRules(EntityType<MandarinDuckEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 5;
    }

    public MandarinDuckEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        MandarinDuckEntity mandarinDuckEntity = CreaturesEntities.MANDARIN_DUCK.get().create(p_149088_);
        mandarinDuckEntity.setGender(this.random.nextInt(2));
        mandarinDuckEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return mandarinDuckEntity;
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!(p_30392_ instanceof MandarinDuckEntity)) {
            return false;
        } else {
            MandarinDuckEntity kakapo = (MandarinDuckEntity)p_30392_;
            return this.isInLove() && kakapo.isInLove();
        }
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.4F;
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide && this.isAlive() && CreaturesConfig.drop_feather.get() && !this.isBaby() && --this.featherTime <= 0) {
            this.spawnAtLocation(CreaturesItems.DUCK_FEATHER.get());
            this.featherTime = this.random.nextInt(6000) + 6000;
        }
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(Items.BREAD, Items.WHEAT_SEEDS);
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.MANDARIN_DUCK_AMBIENT;
        }
        return null;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.BREAD, 1);
    }


    public double getHatchChance() {
        return CreaturesConfig.mandarin_duck_hatch_chance.get();
    }

}
