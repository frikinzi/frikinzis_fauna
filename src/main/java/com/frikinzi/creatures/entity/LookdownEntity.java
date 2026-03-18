package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
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

import java.util.Map;

public class LookdownEntity extends FishBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public LookdownEntity(EntityType<? extends LookdownEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("entity.creatures.lookdown"),
            2, Component.translatable("entity.creatures.lookdown")
    );

    protected <E extends LookdownEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (this.isInWater()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("swim")); }
        else {
            return event.setAndContinue(RawAnimation.begin().thenLoop("flop"));
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Swimming", 0, this::swimAnimController));
    }

@Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

public int getMaxSchoolSize() {
        return 10;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.LOOKDOWN_BUCKET.get());
    }

//    public void saveToBucketTag(ItemStack p_204211_1_) {
//        super.saveToBucketTag(p_204211_1_);
//        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
//        compoundnbt.putInt("BucketVariantTag", this.getVariant());
//        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
//        compoundnbt.putInt("Age", this.getAge());
//    }

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

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.1D);
    }

    public float getHatchChance() {
        return CreaturesConfig.red_snapper_hatch_chance.get().floatValue();
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.RED_SNAPPER;
    }

    public Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public String getScientificName() {
        return "Selene vomer";
    }

    public int numVariants() {
        return 2;
    }
}
