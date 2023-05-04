package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBirdEntity;
import com.frikinzi.creatures.entity.base.RaptorEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
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

import java.util.Map;
import java.util.Random;

public class WildDuckEntity extends CreaturesBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public static final Map<Integer, TranslatableComponent> SPECIES_NAMES = ImmutableMap.<Integer, TranslatableComponent>builder()
            .put(1, new TranslatableComponent("message.creatures.torrent"))
            .put(2, new TranslatableComponent("message.creatures.redhead"))
            .put(3, new TranslatableComponent("message.creatures.greenwing"))
            .put(4, new TranslatableComponent("message.creatures.ruddy"))
            .put(5, new TranslatableComponent("message.creatures.mallard"))
            .put(6, new TranslatableComponent("message.creatures.ringteal"))
            .put(7, new TranslatableComponent("message.creatures.indianspotbill"))
            .put(8, new TranslatableComponent("message.creatures.whiteheadduck"))
            .put(9, new TranslatableComponent("message.creatures.chestnutteal"))
            .put(10, new TranslatableComponent("message.creatures.madagascarteal"))
            .put(11, new TranslatableComponent("message.creatures.bluebilledteal"))
            .put(12, new TranslatableComponent("message.creatures.punateal"))
            .put(13, new TranslatableComponent("message.creatures.maccoa"))
            .build();

    public WildDuckEntity(EntityType<? extends WildDuckEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new SleepGoal());
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, RaptorEntity.class, 10.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Player.class, 10.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, this.getBirdFood(), false));
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isBaby()) {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            } if (!this.onGround && !this.isInWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("flying", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            } if (this.isSleeping()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if ((!this.isOnGround() && !this.isInWater())) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.isInWater()) {
            if (this.getVariant() == 4 || this.getVariant() == 8) {
                event.getController()
                        .setAnimation(new AnimationBuilder().addAnimation("swimming_ruddy", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("swimming", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.isGrooming()) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("groom", ILoopType.EDefaultLoopTypes.LOOP));
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
        data.addAnimationController(new AnimationController<WildDuckEntity>(this, "controller", 0, this::predicate));
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

    public static boolean checkBirdSpawnRules(EntityType<WildDuckEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 13;
    }

    public WildDuckEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        WildDuckEntity duck = CreaturesEntities.WILD_DUCK.get().create(p_149088_);
        duck.setVariant(this.getVariant());
        duck.setGender(this.random.nextInt(2));
        duck.setHeightMultiplier(getSpawnEggOffspringHeight());
        return duck;
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!(p_30392_ instanceof WildDuckEntity)) {
            return false;
        } else {
            WildDuckEntity duck = (WildDuckEntity)p_30392_;
            return this.isInLove() && duck.isInLove();
        }
    }

    public String getSpeciesName() {
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.4F;
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.WILD_DUCK_AMBIENT; } else {
            return null;
        }
    }

    public void aiStep() {
        if (!this.level.isClientSide) {
            int i = this.random.nextInt(3000);
            if (i == 0 && !this.isInWater() && this.isNotMoving() && this.canMove() && !this.isSleeping() && !this.isBaby()) {
                this.getNavigation().stop();
                this.setGrooming(true);
            }
            if ((i == 1 || this.isInWater()) && this.isGrooming()) {
                this.setGrooming(false);
            }
        }
        super.aiStep();
    }

    public double getHatchChance() {
        return CreaturesConfig.wild_duck_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.wild_duck_clutch_size.get());
    }

}
