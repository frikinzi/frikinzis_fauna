package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
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

import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.ai.SitOnShoulderGoal;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

public class LorikeetEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.NECTAR.get());
    private boolean isGumi;
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.lorikeet.rainbow"))
            .put(2, Component.translatable("message.creatures.lorikeet.black"))
            .put(3, Component.translatable("message.creatures.lorikeet.blue"))
            .put(4, Component.translatable("message.creatures.lorikeet.olive"))
            .put(5, Component.translatable("message.creatures.lorikeet.chattering"))
            .put(6, Component.translatable("message.creatures.lorikeet.duskylory"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Trichoglossus moluccanus")
            .put(2, "Eos cyanogenia")
            .put(3, "Trichoglossus moluccanus")
            .put(4, "Trichoglossus euteles")
            .put(5, "Lorius garrulus")
            .put(6, "Pseudeos fuscata")
            .build();

    public LorikeetEntity(EntityType<? extends LorikeetEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(6, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(3, new SitOnShoulderGoal(this));

    }

    protected <E extends LorikeetEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() || this.isFlying()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
    } if (this.isSleeping()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
    } if (this.isInSittingPose()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sit"));
    }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Flying", 0, this::flyAnimController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
    }

    public int numVariants() {
        return 6;
    }

    @Override
    public LorikeetEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        LorikeetEntity lorikeetentity = CreaturesEntities.LORIKEET.get().create(p_241840_1_);
        lorikeetentity.setGender(this.random.nextInt(2));
        if (this.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.lorikeet_mutation_chance.get()) == 1) {
            lorikeetentity.setVariant(3); }
            else {
                lorikeetentity.setVariant(this.getVariant());
            }
        } else {
            lorikeetentity.setVariant(this.getVariant());
        }
        lorikeetentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return lorikeetentity;
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

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (this.getVariant() == 5 && this.isGumi && !this.isSleeping()) {
            return CreaturesSound.LORIKEET_AMBIENT2.get();
        }
        else if (!this.isSleeping()) {
        return CreaturesSound.LORIKEET_AMBIENT.get(); } else {
            return null;
        }
    }

    public void setCustomName(@Nullable Component p_200203_1_) {
        super.setCustomName(p_200203_1_);
        if (!this.isGumi && p_200203_1_ != null && p_200203_1_.getString().equals("Gumi")) {
            this.isGumi = true;
        }

    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        if (this.isGumi) {
            p_213281_1_.putBoolean("Gumi", true);
        }

    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        if (p_70037_1_.contains("Gumi", 99)) {
            this.isGumi = p_70037_1_.getBoolean("Gumi");
        }

    }

    @Override
    public int methodOfDeterminingVariant() {
        if (CreaturesConfig.breed_only_variants.get() == true) {
            int i = this.random.nextInt(numVariants());
            while (i == 3) {
                i = this.random.nextInt(numVariants());
            }
            return i; }

        else {
            return this.random.nextInt(numVariants());
        }

    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public ItemStack getFoodItem() {
        return new ItemStack(CreaturesItems.NECTAR.get(), 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.lorikeet_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.lorikeet_clutch_size.get());
    }

    public int getMaxFlockSize() {
        return 10;
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 2) {
            return 1;
        } if (this.getVariant() == 4) {
            return 2;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.lorikeet");
    }

    public boolean canTame() {
        return true;
    }

}
