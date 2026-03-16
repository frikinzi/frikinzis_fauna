package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.WalkingSwimmingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LittlePenguinEntity extends WalkingSwimmingBird implements GeoEntity {
    private boolean searchingForLand;
    private static final EntityDataAccessor<Integer> VARIANT_SUBID = SynchedEntityData.defineId(LittlePenguinEntity.class, EntityDataSerializers.INT);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD);
    public static final Map<Integer, Component> SPECIES_NAMES;

    static {
        Map<Integer, Component> messageMap = new HashMap<>();
        messageMap.put(1, Component.translatable("message.creatures.bluepenguin"));
        messageMap.put(2, Component.translatable("message.creatures.australianlittle"));
        SPECIES_NAMES = Collections.unmodifiableMap(messageMap);
    }

    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Eudyptula minor")
            .put(2, "Eudyptula novaehollandiae")
            .build();

    public LittlePenguinEntity(EntityType<? extends LittlePenguinEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);

    }

    protected float nextStep() {
        return this.moveDist + 0.15F;
    }


    protected <E extends LittlePenguinEntity> PlayState walkAnimController(final AnimationState<E> event)
    {
        if (this.isInWater()) {
            if (this.isBaby()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
            }
            if (!event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("dive"));
        }
        else {
            if (event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
            }

            if (this.isSleeping()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
            }
            if (this.isGrooming()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("grooming"));

            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Walking", 0, this::walkAnimController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int numVariants() {
        return 2;
    }

    @Override
    public LittlePenguinEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        LittlePenguinEntity crestedpenguin = CreaturesEntities.LITTLE_PENGUIN.get().create(p_241840_1_);
        crestedpenguin.setVariant(this.getVariant());
        crestedpenguin.setGender(this.random.nextInt(2));
        crestedpenguin.setHeightMultiplier(getSpawnEggOffspringHeight());
        return crestedpenguin;
    }

    protected void playSwimSound(float p_203006_1_) {
        super.playSwimSound(p_203006_1_ * 1.5F);
    }

    @Override
    public boolean canMate(Animal p_70878_1_) {
        if (p_70878_1_ == this) {
            return false;
        } else if (p_70878_1_.getClass() != this.getClass()) {
            return false;
        } else {
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }

    public int methodOfDeterminingVariant() {
        if (this.random.nextInt(10) == 1) {
            this.setSubVariant(2);
        } return super.methodOfDeterminingVariant();
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.LITTLE_PENGUIN.get(); } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    private boolean wantsToSwim() {
        if (this.searchingForLand) {
            return true;
        } else {
            LivingEntity livingentity = this.getTarget();
            return livingentity != null && livingentity.isInWater();
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT_SUBID, 0);
    }


    public void setSubVariant(int p_191997_1_) {
        this.entityData.set(VARIANT_SUBID, p_191997_1_);
    }

    public int getSubVariant() {
        return Mth.clamp(this.entityData.get(VARIANT_SUBID), 1, 3);
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setSubVariant(p_70037_1_.getInt("Subvariant"));
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putInt("Subvariant", this.getSubVariant());
    }

    public double getHatchChance() {
        return CreaturesConfig.penguin_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.penguin_clutch_size.get());
    }

    public void aiStep() {
        if (!this.level().isClientSide) {
            if (this.isBaby()) {
                this.getNavigation().stop();
            }
            int i = this.random.nextInt(3000);
            if (i == 0 && !this.isInWater() && !this.isSleeping() && !this.isBaby()) {
                this.getNavigation().stop();
                this.setGrooming(true);
            }
            if ((i == 1 || this.isInWater()) && this.isGrooming()) {
                this.setGrooming(false);
            }
        }
        super.aiStep();
    }


    public boolean canBreatheUnderwater() {
        return true;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.COD, 1);
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 1) {
            return 1;
        } if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 5 || this.getVariant() == 7) {
            return 2;
        } if (this.getVariant() == 3 || this.getVariant() == 6) {
            return 3;
        }
        return super.getIUCNStatus();
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.littlepenguin");
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

}
