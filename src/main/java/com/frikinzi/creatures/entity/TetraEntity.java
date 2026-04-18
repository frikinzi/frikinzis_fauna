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
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.player.Player;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TetraEntity extends FishBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static Map<Integer, Component> SPECIES_NAMES;
    static {
        Map<Integer, Component> map = new HashMap<>();
        map.put(1, Component.translatable("message.creatures.rummynose"));
        map.put(2, Component.translatable("message.creatures.neontetra"));
        map.put(3, Component.translatable("message.creatures.cardinaltetra"));
        map.put(4, Component.translatable("message.creatures.embertetra"));
        map.put(5, Component.translatable("message.creatures.congotetra"));
        map.put(6, Component.translatable("message.creatures.blackneontetra"));
        map.put(7, Component.translatable("message.creatures.bloodfintetra"));
        SPECIES_NAMES = Collections.unmodifiableMap(map);
    }

    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Hemigrammus sp.")
            .put(2, "Paracheirodon innesi")
            .put(3, "Paracheirodon axelrodi")
            .put(4, "Hyphessobrycon amandae")
            .put(5, "Phenacogrammus interruptus")
            .put(6, "Hyphessobrycon herbertaxelrodi")
            .put(7, "Aphyocharax anisitsi")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.SOUTH_AMERICA))
            .put(2, List.of(Region.SOUTH_AMERICA))
            .put(3, List.of(Region.SOUTH_AMERICA))
            .put(4, List.of(Region.SOUTH_AMERICA))
            .put(5, List.of(Region.AFRICA))
            .put(6, List.of(Region.SOUTH_AMERICA))
            .put(7, List.of(Region.SOUTH_AMERICA))
            .build();

    public TetraEntity(EntityType<? extends TetraEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected <E extends TetraEntity> PlayState swimAnimController(final AnimationState<E> event)
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

public int getMaxSchoolSize() {
        return 20;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.TETRA_BUCKET.get());
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.1D);
    }

    public float getHatchChance() {
        return CreaturesConfig.tetra_hatch_chance.get().floatValue();
    }

    public Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    public double getMoveSpeed() {
        return 1.5D;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 2.2D));
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.1F;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public int numVariants() {
        return 7;
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.tetra");
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.TROPICAL_FISH;
    }

}
