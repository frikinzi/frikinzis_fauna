package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.ai.SitOnShoulderGoal;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KakapoEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.OCEANIA))
            .build();

    public KakapoEntity(EntityType<? extends KakapoEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Flying", 0, this::flyAnimController));
    }

    protected <E extends KakapoEntity> PlayState flyAnimController(final AnimationState<E> event) {
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.kakapo.walk"));
        } if (!this.onGround() || this.isFlying()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.kakapo.flying"));
        } if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.kakapo.sleep"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.kakapo.idle"));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    public KakapoEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        KakapoEntity kakapoEntity = CreaturesEntities.KAKAPO.get().create(p_149088_);
        kakapoEntity.setVariant(this.getVariant());
        kakapoEntity.setGender(this.random.nextInt(2));
        kakapoEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return kakapoEntity;
    }

    public boolean canMate(Animal p_30392_) {
        return super.canMate(p_30392_);
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.3F;
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.KAKAPO_AMBIENT.get(); } else {
            return null;
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_28306_) {
        return CreaturesSound.KAKAPO_HURT.get();
    }

    public double getHatchChance() {
        return CreaturesConfig.kakapo_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.kakapo_clutch_size.get());
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.PARROT;
    }

    public int numVariants() {
        return 1;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public String getScientificName() {
        return "Strigops habroptilus";
    }

    public boolean timeSleep() {
        return this.level().isDay();
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.kakapo");
    }
}
