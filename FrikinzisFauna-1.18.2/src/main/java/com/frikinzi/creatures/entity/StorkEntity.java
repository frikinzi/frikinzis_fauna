package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
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

import java.util.Map;
import java.util.Random;

public class StorkEntity extends CreaturesFlyingBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public static Map<Integer, Integer> STORK_MODEL = ImmutableMap.of(
            1, 1,
            2, 1,
            3, 1,
            4, 1
    );
    public static Map<Integer, TranslatableComponent> SPECIES_NAMES = ImmutableMap.of(
            1, new TranslatableComponent("message.creatures.yellowbilledstork"),
            2, new TranslatableComponent("message.creatures.paintedstork"),
            3, new TranslatableComponent("message.creatures.milkystork"),
            4, new TranslatableComponent("message.creatures.woodstork")
    );

    public StorkEntity(EntityType<? extends StorkEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new SleepGoal());
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new BirdWanderGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, this.getBirdFood(), false));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
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
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (!event.isMoving())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    public int getModelNumberFromVariant() {
        if (STORK_MODEL.get(this.getVariant()) != null) {
            return STORK_MODEL.get(this.getVariant());
        }
        return 1;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<StorkEntity>(this, "controller", 0, this::predicate));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.4F).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public static boolean checkBirdSpawnRules(EntityType<StorkEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 4;
    }

    public StorkEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        StorkEntity bushtit = CreaturesEntities.STORK.get().create(p_149088_);
        bushtit.setVariant(this.getVariant());
        bushtit.setGender(this.random.nextInt(2));
        bushtit.setHeightMultiplier(getSpawnEggOffspringHeight());
        return bushtit;
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!(p_30392_ instanceof StorkEntity)) {
            return false;
        } else {
            StorkEntity bushtit = (StorkEntity)p_30392_;
            if (bushtit.getGender() == this.getGender()) {
                return false;
            }
            return this.isInLove() && bushtit.isInLove();
        }
    }

    public String getSpeciesName() {
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.STORK_AMBIENT; } else {
            return null;
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_29872_) {
        return CreaturesSound.STORK_HURT;
    }

    public double getHatchChance() {
        return CreaturesConfig.stork_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.stork_clutch_size.get());
    }

    public int getAmbientSoundInterval() {
        return 300;
    }


}
