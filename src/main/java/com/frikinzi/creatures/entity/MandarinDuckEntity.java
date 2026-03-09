package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Map;

public class MandarinDuckEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public MandarinDuckEntity(EntityType<? extends MandarinDuckEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new FollowFlockLeaderGoal(this));
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Flying", 0, this::flyAnimController));
    }

    protected <E extends MandarinDuckEntity> PlayState flyAnimController(final AnimationState<E> event) {
        if (!this.isBaby()) {
            if (event.isMoving() && this.onGround()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
            }
            if (!this.onGround() && !this.isInWater()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
            }
            if (this.isSleeping()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
            }
            if (this.isInWater()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("swim2"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        } else {
            if (event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walking"));
            }
            if (!this.onGround() && !this.isInWater()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("flying"));
            }
            if (this.isSleeping()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    public MandarinDuckEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        MandarinDuckEntity mandarinDuckEntity = CreaturesEntities.MANDARIN_DUCK.get().create(p_149088_);
        mandarinDuckEntity.setVariant(this.getVariant());
        mandarinDuckEntity.setGender(this.random.nextInt(2));
        mandarinDuckEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return mandarinDuckEntity;
    }

    public boolean canMate(Animal p_30392_) {
        return super.canMate(p_30392_);
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.3F;
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.MANDARIN_DUCK_AMBIENT.get(); } else {
            return null;
        }
    }

    public double getHatchChance() {
        return CreaturesConfig.mandarin_duck_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.mandarin_duck_clutch_size.get());
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(ForgeMod.SWIM_SPEED.get(), 3.0).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public String getScientificName() {
        return "Aix galericulata";
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.mandarinduck");
    }
}
