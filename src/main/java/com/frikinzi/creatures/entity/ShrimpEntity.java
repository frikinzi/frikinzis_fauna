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
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
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

public class ShrimpEntity extends FishBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.crystal_shrimp"))
            .put(2, Component.translatable("message.creatures.red_shrimp"))
            .put(3, Component.translatable("message.creatures.blue_shrimp"))
            .put(4, Component.translatable("message.creatures.wild_type_shrimp"))
            .put(5, Component.translatable("message.creatures.yellow_shrimp"))
            .put(6, Component.translatable("message.creatures.green_shrimp"))
            .put(7, Component.translatable("message.creatures.black_shrimp"))
            .put(8, Component.translatable("message.creatures.white_shrimp"))
            .build();

    public ShrimpEntity(EntityType<? extends ShrimpEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected <E extends ShrimpEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (!this.isInWater()) {
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
        return new ItemStack(CreaturesItems.SHRIMP_BUCKET.get());
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.15D);
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.shrimp_hatch_chance.get()).floatValue();
    }


    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SHRIMP;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, AbstractFish.class, 8.0F, 2.2D, 2.2D));
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.1F;
    }

    public int getIUCNStatus() {
        return -1;
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.shrimp");
    }

    public String getScientificName() {
        return "Neocaridina davidi";
    }

    public int numVariants() {
        return 8;
    }

    public net.minecraft.world.item.Item getFoodItem() {
        return CreaturesItems.ALGAE_WAFER.get();
    }

}
