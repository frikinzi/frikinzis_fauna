package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraftforge.common.ForgeMod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RailEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.MEALWORMS.get());
    public int featherTime = this.random.nextInt(6000) + 6000;
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.okinawarail"))
            .put(2, Component.translatable("message.creatures.plumbeousrail"))
            .put(3, Component.translatable("message.creatures.spottedrail"))
            .put(4, Component.translatable("message.creatures.barredrail"))
            .put(5, Component.translatable("message.creatures.guamrail"))
            .build();
    public static final Map<Integer, Component> DESCRIPTIONS = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("description.creatures.okinawarail"))
            .put(2, Component.translatable("description.creatures.plumbeousrail"))
            .put(3, Component.translatable("description.creatures.spottedrail"))
            .put(4, Component.translatable("description.creatures.barredrail"))
            .put(5, Component.translatable("description.creatures.guamrail"))
            .build();
    public static Map<Integer, String> SCIENTIFIC_NAMES = new HashMap<Integer, String>() {{
        put(1, "Hypotaenidia okinawae");
        put(2, "Pardirallus sanguinolentus");
        put(3, "Pardirallus maculatus");
        put(4, "Hypotaenidia torquata");
        put(5, "Hypotaenidia owstoni");
    }};

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.ASIA))
            .put(2, List.of(Region.SOUTH_AMERICA))
            .put(3, List.of(Region.SOUTH_AMERICA, Region.NORTH_AMERICA))
            .put(4, List.of(Region.ASIA, Region.OCEANIA))
            .put(5, List.of(Region.OCEANIA))
            .build();

    public RailEntity(EntityType<? extends RailEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 1.0F);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1.5D, 1.2D));
    }

    protected <E extends RailEntity> PlayState walkAnimController(final AnimationState<E> event) {
        if (event.isMoving()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
        } if (this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(ForgeMod.SWIM_SPEED.get(), 3.0).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public int numVariants() {
        return 5;
    }

    @Override
    public RailEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        RailEntity railentity = CreaturesEntities.RAIL.get().create(p_241840_1_);
        railentity.setVariant(this.getVariant());
        railentity.setGender(this.random.nextInt(2));
        railentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return railentity;
    }

    @Override
    public boolean canMate(Animal p_70878_1_) {
        if (p_70878_1_ == this) {
            return false;
        } else if (p_70878_1_.getClass() != this.getClass()) {
            return false;
        }
        else {
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.RAIL.get();
        }
        else
        {
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
        return CreaturesConfig.rail_hatch_chance.get().floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.whistlingduck_clutch_size.get());
    }

    public Component getFunFact() {
        Component translatable = DESCRIPTIONS.get(this.getVariant());
        if (translatable != null) {
            return translatable;
        } return Component.translatable("creatures.unknown");
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 1) {
            return 3; // endangered
        } if (this.getVariant() == 5) {
            return 4; // CR
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

}
