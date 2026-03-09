package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.ai.SitOnShoulderGoal;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
import java.util.Map;

public class SpoonbillEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final Map<Integer, Component> SPECIES_NAMES = new HashMap<Integer, Component>() {{
            put(1, Component.translatable("message.creatures.roseate"));
            put(2, Component.translatable("message.creatures.royal"));
            put(3, Component.translatable("message.creatures.african"));
            put(4, Component.translatable("message.creatures.eurasiansp"));
            put(5, Component.translatable("message.creatures.yellowbilled"));
            put(6, Component.translatable("message.creatures.blackfaced"));
    }};
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Platalea ajaja")
            .put(2, "Platalea regia")
            .put(3, "Platalea alba")
            .put(4, "Platalea leucorodia")
            .put(5, "Platalea flavipes")
            .put(6, "Platalea minor")
            .build();
    public static Map<Integer, Component> DESCRIPTIONS = new HashMap<Integer, Component>() {{
        put(1, Component.translatable("description.spoonbill.roseate"));
        put(2, Component.translatable("description.spoonbill.royal"));
        put(3, Component.translatable("description.spoonbill.african"));
        put(4, Component.translatable("description.spoonbill.eurasiansp"));
        put(5, Component.translatable("description.spoonbill.yellowbilled"));
        put(6, Component.translatable("description.spoonbill.blackfaced"));
    }};

    public SpoonbillEntity(EntityType<? extends SpoonbillEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Flying", 0, this::flyAnimController));
    }

    protected <E extends SpoonbillEntity> PlayState flyAnimController(final AnimationState<E> event) {
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() || this.isFlying()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        } if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    public SpoonbillEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        SpoonbillEntity spoonbillEntity = CreaturesEntities.SPOONBILL.get().create(p_149088_);
        spoonbillEntity.setVariant(this.getVariant());
        spoonbillEntity.setGender(this.random.nextInt(2));
        spoonbillEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return spoonbillEntity;
    }

    public boolean canMate(Animal p_30392_) {
        return super.canMate(p_30392_);
    }

    public String getSpeciesName() {
        Component s1 = SPECIES_NAMES.get(this.getVariant());
        if (s1 != null) {
            return s1.getString();
        } else {
            return "Unknown";
        }
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.3F;
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.SPOONBILL_AMBIENT.get(); } else {
            return null;
        }
    }

    public double getHatchChance() {
        return CreaturesConfig.spoonbill_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.spoonbill_clutch_size.get());
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    public int numVariants() {
        return 6;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        Component translatable = DESCRIPTIONS.get(this.getVariant());
        if (translatable != null) {
            return translatable;
        } return Component.translatable("creatures.unknown");
    }
}
