package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;

public class BlueTangEntity extends FishBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.bluetang"))
            .put(2, Component.translatable("message.creatures.achillestang"))
            .put(3, Component.translatable("message.creatures.powderbluetang"))
            .put(4, Component.translatable("message.creatures.powderbrowntang"))
            .put(5, Component.translatable("message.creatures.convicttang"))
            .put(6, Component.translatable("message.creatures.sailfintang"))
            .put(7, Component.translatable("message.creatures.yellowtang"))
            .put(8, Component.translatable("message.creatures.purpletang"))
            .put(9, Component.translatable("message.creatures.blacktang"))
            .put(10, Component.translatable("message.creatures.clowntang"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Paracanthurus hepatus")
            .put(2, "Acanthurus achilles")
            .put(3, "Acanthurus leucosternon")
            .put(4, "Acanthurus japonicus")
            .put(5, "Acanthurus triostegus")
            .put(6, "Zebrasoma veliferum")
            .put(7, "Zebrasoma flavescens")
            .put(8, "Zebrasoma xanthurum")
            .put(9, "Zebrasoma rostratum")
            .put(10, "Acanthurus lineatus")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.ASIA, Region.OCEANIA, Region.AFRICA))
            .put(2, List.of(Region.OCEANIA, Region.NORTH_AMERICA, Region.ASIA))
            .put(3, List.of(Region.ASIA, Region.AFRICA))
            .put(4, List.of(Region.ASIA, Region.OCEANIA))
            .put(5, List.of(Region.ASIA, Region.OCEANIA, Region.AFRICA))
            .put(6, List.of(Region.ASIA, Region.OCEANIA, Region.AFRICA))
            .put(7, List.of(Region.ASIA))
            .put(8, List.of(Region.ASIA, Region.AFRICA))
            .put(9, List.of(Region.OCEANIA))
            .put(10, List.of(Region.ASIA, Region.OCEANIA))
            .build();

    public BlueTangEntity(EntityType<? extends BlueTangEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected <E extends BlueTangEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if(!this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("flop"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Swimming", 0, this::swimAnimController));
    }

@Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.BLUE_TANG_BUCKET.get());
    }

    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        compoundnbt.putInt("Age", this.getAge());
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    public int getMaxSchoolSize() {
        return 10;
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.blue_tang_hatch_chance.get()).floatValue();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.1D);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.TROPICAL_FISH;
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.2F;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public int numVariants() {
        return 10;
    }
}
