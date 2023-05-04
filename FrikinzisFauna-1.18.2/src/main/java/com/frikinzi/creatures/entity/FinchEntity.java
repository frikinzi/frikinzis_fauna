package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.TameableFlyingBirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.util.ModEventSubscriber;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class FinchEntity extends TameableFlyingBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public static final Map<Integer, TranslatableComponent> SPECIES_NAMES = ImmutableMap.<Integer, TranslatableComponent>builder()
            .put(1, new TranslatableComponent("message.creatures.zebrafinch"))
            .put(2, new TranslatableComponent("message.creatures.strawberryfinch"))
            .put(3, new TranslatableComponent("message.creatures.zebrafinchmutation"))
            .put(4, new TranslatableComponent("message.creatures.owlfaced"))
            .put(5, new TranslatableComponent("message.creatures.gouldianfinchred"))
            .put(6, new TranslatableComponent("message.creatures.gouldianfinchblack"))
            .put(7, new TranslatableComponent("message.creatures.bullfinch"))
            .put(8, new TranslatableComponent("message.creatures.javafinch"))
            .put(9, new TranslatableComponent("message.creatures.parrotfinch"))
            .put(10, new TranslatableComponent("message.creatures.purplegrenadier"))
            .put(11, new TranslatableComponent("message.creatures.europeangoldfinch"))
            .build();

    public FinchEntity(EntityType<? extends FinchEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }


    protected void registerGoals() {
        super.registerGoals();
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
        data.addAnimationController(new AnimationController<FinchEntity>(this, "controller", 0, this::predicate));
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

    public static boolean checkBirdSpawnRules(EntityType<FinchEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 11;
    }

    public FinchEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        FinchEntity finch = CreaturesEntities.FINCH.get().create(p_149088_);
        if (this.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.lorikeet_mutation_chance.get()) == 2) {
                finch.setVariant(3); }
            else {
                finch.setVariant(this.getVariant());
            }
        } else {
            finch.setVariant(this.getVariant());
        }
        finch.setGender(this.random.nextInt(2));
        finch.setHeightMultiplier(getSpawnEggOffspringHeight());
        return finch;
    }

    public int determineVariant() {
        if (CreaturesConfig.breed_only_variants.get()) {
            int i = this.random.nextInt(noVariants());
            while (i == 3) {
                i = this.random.nextInt(noVariants());
            }
            return i; }

        else {
            return this.random.nextInt(noVariants());
        }

    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(p_30392_ instanceof FinchEntity)) {
            return false;
        } else {
            FinchEntity finch = (FinchEntity)p_30392_;
            if (!finch.isTame()) {
                return false;
            } else if (finch.isInSittingPose()) {
                return false;
            } else {
                return this.isInLove() && finch.isInLove();
            }
        }
    }

    public EggEntity layEgg(CreaturesBirdEntity animal) {
        EggEntity egg = new EggEntity(CreaturesEntities.EGG.get(), this.level);
        egg.setSpecies(ModEventSubscriber.getBirdEntityMap().inverse().get(animal.getType()));
        egg.setGender(this.random.nextInt(2));
        if (this.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
                egg.setVariant(3); }
            else {
                egg.setVariant(this.getVariant());
            }
        }
        else {
            egg.setVariant(this.getVariant());
        }
        return egg;
    }

    public String getSpeciesName() {
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.3F;
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.FINCH_AMBIENT; } else {
            return null;
        }
    }


    public double getHatchChance() {
        return CreaturesConfig.finch_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.finch_clutch_size.get());
    }

}
