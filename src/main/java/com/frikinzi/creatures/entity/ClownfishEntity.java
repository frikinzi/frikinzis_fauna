package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
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
import java.util.Random;

public class ClownfishEntity extends FishBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.ocellaris"))
            .put(2, Component.translatable("message.creatures.orangeclownfish"))
            .put(3, Component.translatable("message.creatures.maroonclownfish"))
            .put(4, Component.translatable("message.creatures.tomatoclownfish"))
            .put(5, Component.translatable("message.creatures.cinnamonclownfish"))
            .put(6, Component.translatable("message.creatures.saddlebackclownfish"))
            .put(7, Component.translatable("message.creatures.orangeskunkclownfish"))
            .put(8, Component.translatable("message.creatures.pinkskunkclownfish"))
            .build();
    public static Map<Integer, Integer> CLOWNFISH = ImmutableMap.<Integer, Integer>builder()
            .put(1, 17)
            .put(2, 4)
            .put(3, 5)
            .put(4,2)
            .put(5,2)
            .put(6,2)
            .put(7,1)
            .put(8,1)
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Amphiprion ocellaris")
            .put(2, "Amphiprion percula")
            .put(3, "Premnas biaculeatus")
            .put(4, "Amphiprion frenatus")
            .put(5, "Amphiprion melanopus")
            .put(6, "Amphiprion polymnus")
            .put(7, "Amphiprion sandaracinos")
            .put(8, "Amphiprion perideraion")
            .build();

    public ClownfishEntity(EntityType<? extends ClownfishEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected <E extends ClownfishEntity> PlayState swimAnimController(final AnimationState<E> event)
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
        return new ItemStack(CreaturesItems.CLOWNFISH_BUCKET.get());
    }

    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("Subvariant", this.getVariant());
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
        return Double.valueOf(CreaturesConfig.clownfish_hatch_chance.get()).floatValue();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.1D);
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

    @Override
    protected void layEgg(ServerLevel server, FishBase father) {
        int c = 10;
        for (int j = 0; j <= c; j++) {
            CreaturesRoeEntity egg = this.layEgg(this);
            if (egg != null) {
                FishBase mother;
                mother = this;

                egg.setParentUUID(mother.getUUID());

                float f = (float)(this.getRandom().nextGaussian() * 0.05 + ((this.getHeightMultiplier())));
                egg.setHeightMultiplier(f);
                egg.setVariant(this.getVariant());
                if (this.random.nextInt(20) == 1) {
                    egg.setSubVariant(this.random.nextInt(CLOWNFISH.get(this.getVariant()))+1);
                } else {
                    egg.setSubVariant(this.getSubVariant());
                }

                Random rand = new Random();
                egg.setPos(Mth.floor(mother.getX()) + 0.5 + (-1+rand.nextFloat()), Mth.floor(mother.getY()) + 0.5, Mth.floor(mother.getZ()) + 0.5 + (-1+rand.nextFloat()));
                server.addFreshEntityWithPassengers(egg);
            }
            server.broadcastEntityEvent(this, (byte)18);
        }
        this.setBred(true);
        RandomSource random = this.getRandom();
        for (int i = 0; i < 17; ++i) {
            final double d0 = random.nextGaussian() * 0.02D;
            final double d1 = random.nextGaussian() * 0.02D;
            final double d2 = random.nextGaussian() * 0.02D;
            final double d3 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            final double d4 = 0.5D + random.nextDouble() * this.getBbHeight();
            final double d5 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            this.level().addParticle(ParticleTypes.HEART, this.getX() + d3, this.getY() + d4, this.getZ() + d5, d0, d1, d2);
        }
        if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            server.addFreshEntity(new ExperienceOrb(server, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
        }
    }

    public Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.clownfish");
    }

    public int numVariants() {
        return 8;
    }

    public int getSubVariantBasedOnVariant(int variant) {
        return this.random.nextInt(CLOWNFISH.get(this.getVariant()))+1;
    }
}
