package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
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

import java.util.*;

public class FairywrenEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.MEALWORMS.get());
    public static Map<Integer, Component> SPECIES_NAMES;
    static {
        Map<Integer, Component> map = new HashMap<>();
        map.put(1, Component.translatable("message.creatures.variegated"));
        map.put(2, Component.translatable("message.creatures.splendid"));
        map.put(3, Component.translatable("message.creatures.whitewinged"));
        map.put(4, Component.translatable("message.creatures.blackwinged"));
        map.put(5, Component.translatable("message.creatures.purplecrowned"));
        map.put(6, Component.translatable("message.creatures.superb"));
        SPECIES_NAMES = Collections.unmodifiableMap(map);
    }
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Malurus lamberti")
            .put(2, "Malurus splendens")
            .put(3, "Malurus leucopterus")
            .put(4, "Malurus melanocephalus")
            .put(5, "Malurus coronatus")
            .put(6, "Malurus cyaneus")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.OCEANIA))
            .put(2, List.of(Region.OCEANIA))
            .put(3, List.of(Region.OCEANIA))
            .put(4, List.of(Region.OCEANIA))
            .put(5, List.of(Region.OCEANIA))
            .put(6, List.of(Region.OCEANIA))
            .build();

    public FairywrenEntity(EntityType<? extends FairywrenEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1.5D, 1.2D));
    }

    protected <E extends FairywrenEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround()) {
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
    }

    public int numVariants() {
        return 6;
    }

    @Override
    public FairywrenEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        FairywrenEntity fairywrenentity = CreaturesEntities.FAIRYWREN.get().create(p_241840_1_);
        fairywrenentity.setVariant(this.getVariant());
        fairywrenentity.setGender(this.random.nextInt(2));
        fairywrenentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return fairywrenentity;
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
        return CreaturesSound.FAIRYWREN_AMBIENT.get(); } else {
            return null;
        }
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
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
        return new ItemStack(CreaturesItems.MEALWORMS.get(), 1);
    }

    @Override
    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.fairywren_clutch_size.get());
    }

    @Override
    public double getHatchChance() {
        return CreaturesConfig.fairywren_hatch_chance.get();
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.3F;
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.fairywren");
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

}
