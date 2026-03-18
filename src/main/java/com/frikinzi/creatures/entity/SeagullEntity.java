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
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import net.minecraftforge.common.ForgeMod;

import java.util.*;

public class SeagullEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, CreaturesItems.CRAB_PINCERS.get(), CreaturesItems.RAW_TROUT.get(), Items.BREAD, CreaturesItems.MEALWORMS.get());
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.herringgull"))
            .put(2, Component.translatable("message.creatures.sabinesgull"))
            .put(3, Component.translatable("message.creatures.rossgull"))
            .put(4, Component.translatable("message.creatures.laughinggull"))
            .put(5, Component.translatable("message.creatures.heermannsgull"))
            .put(6, Component.translatable("message.creatures.ivorygull"))
            .put(7, Component.translatable("message.creatures.blackbilledgull"))
            .put(8, Component.translatable("message.creatures.andeangull"))
            .build();
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE, Region.NORTH_AMERICA))
            .put(2, List.of(Region.NORTH_AMERICA, Region.EUROPE, Region.ASIA))
            .put(3, List.of(Region.NORTH_AMERICA, Region.EUROPE, Region.ASIA))
            .put(4, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(5, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(6, List.of(Region.NORTH_AMERICA, Region.EUROPE, Region.ASIA))
            .put(7, List.of(Region.OCEANIA))
            .put(8, List.of(Region.SOUTH_AMERICA))
            .build();
    public static Map<Integer, Component> DESCRIPTIONS = new HashMap<Integer, Component>() {{
        put(1, Component.translatable("description.creatures.herringgull"));
        put(2, Component.translatable("description.creatures.sabinesgull"));
        put(3, Component.translatable("description.creatures.rossgull"));
        put(4, Component.translatable("description.creatures.laughinggull"));
        put(5, Component.translatable("description.creatures.heermannsgull"));
        put(6, Component.translatable("description.creatures.ivorygull"));
        put(7, Component.translatable("description.creatures.blackbilledgull"));
        put(8, Component.translatable("description.creatures.andeangull"));
    }};
    public static Map<Integer, String> SCIENTIFIC_NAMES = new HashMap<Integer, String>() {{
        put(1, "Larus argentatus");
        put(2, "Xema sabini");
        put(3, "Rhodostethia rosea");
        put(4, "Leucophaeus atricilla");
        put(5, "Larus heermanni");
        put(6, "Pagophila eburnea");
        put(7, "Chroicocephalus bulleri");
        put(8, "Chroicocephalus serranus");
    }};

    public SeagullEntity(EntityType<? extends SeagullEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Cod.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Salmon.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TropicalFish.class, false));
        this.goalSelector.addGoal(6, new FollowFlockLeaderGoal(this));
    }

    protected <E extends SeagullEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround() || this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() || this.isFlying()) {
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(ForgeMod.SWIM_SPEED.get(), 3.0).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    public int numVariants() {
        return 8;
    }

    @Override
    public SeagullEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        SeagullEntity seagullentity = CreaturesEntities.SEAGULL.get().create(p_241840_1_);
        seagullentity.setVariant(this.getVariant());
        seagullentity.setGender(this.random.nextInt(2));
        seagullentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return seagullentity;
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
        if (!this.isSleeping()) {
        return CreaturesSound.SEAGULL.get(); } else {
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

    public ItemStack getFoodItem() {
        return new ItemStack(Items.COD, 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.seagull_hatch_chance.get().floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.seagull_clutch_size.get());
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)|| p_218106_.getBlockState(p_218108_.below()).is(BlockTags.SAND) )&& isBrightEnoughToSpawn(p_218106_, p_218108_);
    }


    public int getMaxFlockSize() {
        return 5;
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 1) {
            return 2; //atlantic is vulnerable
        } return super.getIUCNStatus();
    }
    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.gull");
    }

}
