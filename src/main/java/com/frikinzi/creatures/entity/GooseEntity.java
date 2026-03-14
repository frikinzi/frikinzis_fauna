package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
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
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class GooseEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.GRASS, Items.SEAGRASS, Items.TALL_GRASS);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.canada"))
            .put(2, Component.translatable("message.creatures.barnacle"))
            .put(3, Component.translatable("message.creatures.graylag"))
            .put(4, Component.translatable("message.creatures.snow"))
            .put(5, Component.translatable("message.creatures.orinoco"))
            .put(6, Component.translatable("message.creatures.barheaded"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Branta canadensis")
            .put(2, "Branta leucopsis")
            .put(3, "Anser anser")
            .put(4, "Anser caerulescens")
            .put(5, "Neochen jubata")
            .put(6, "Anser indicus")
            .build();
    public static final Map<Integer, Component> DESCRIPTIONS = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("description.creatures.canada"))
            .put(2, Component.translatable("description.creatures.barnacle"))
            .put(3, Component.translatable("description.creatures.graylag"))
            .put(4, Component.translatable("description.creatures.snow"))
            .put(5, Component.translatable("description.creatures.orinoco"))
            .put(6, Component.translatable("description.creatures.barheaded"))
            .build();

    public GooseEntity(EntityType<? extends GooseEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        //this.targetSelector.addGoal(2, new CreaturesBirdEntity.DefendBabyGoal());
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
    }

    protected <E extends GooseEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (this.isBaby()) {
            if (event.isMoving() && this.onGround()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walking"));
            }
            if (!this.onGround() || this.isFlying() && !this.isInWater()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("flying"));
            }
        }
        if (event.isMoving() && this.onGround() || this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() || this.isFlying() && !this.isInWater()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
    } if (this.isSleeping()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int numVariants() {
        return 6;
    }

    @Override
    public GooseEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        GooseEntity gooseEntity = CreaturesEntities.GOOSE.get().create(p_241840_1_);
        gooseEntity.setVariant(this.getVariant());
        gooseEntity.setGender(this.random.nextInt(2));
        gooseEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return gooseEntity;
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
        if (!this.isSleeping() && this.isFlying()) {
            return CreaturesSound.GOOSE_FLY.get();
        }
        else if (!this.isSleeping()) {
            return CreaturesSound.GOOSE_AMBIENT.get(); }
        else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public double getHatchChance() {
        return CreaturesConfig.goose_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.goose_clutch_size.get());
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.GRASS, 1);
    }

    public boolean isMonogamous() {
        return true;
    }

    public int getMaxFlockSize() {
        return 10;
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 5) {
            return 1;
        }
        return super.getIUCNStatus();
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
