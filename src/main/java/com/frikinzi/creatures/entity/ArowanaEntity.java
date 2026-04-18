package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
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

public class ArowanaEntity extends FishBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.ASIA))
            .put(2, List.of(Region.ASIA))
            .put(3, List.of(Region.ASIA))
            .put(4, List.of(Region.ASIA))
            .build();
    public ArowanaEntity(EntityType<? extends ArowanaEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ShrimpEntity.class, false));
    }

    protected <E extends ArowanaEntity> PlayState swimAnimController(final AnimationState<E> event)
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
        return new ItemStack(CreaturesItems.AROWANA_BUCKET.get());
    }

    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
        compoundnbt.putInt("Age", this.getAge());
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

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.AROWANA;
    }

    public String getSpeciesName() {
        Component i = Component.translatable("entity.creatures.arowana");
        return i.getString();
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.arowana_hatch_chance.get()).floatValue();
    }

    public net.minecraft.world.item.Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 2) {
            return 0;
        }
        return 3;
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.arowana");
    }

    public int numVariants() {
        return 3;
    }
}
