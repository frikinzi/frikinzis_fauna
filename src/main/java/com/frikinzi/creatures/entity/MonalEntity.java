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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MonalEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.BEETROOT, Items.SWEET_BERRIES, CreaturesItems.MEALWORMS.get());
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.himalayanmonal"),
            2, Component.translatable("message.creatures.sclater"),
            3, Component.translatable("message.creatures.chinesemonal")
    );
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Lophophorus impejanus")
            .put(2, "Lophophorus sclateri")
            .put(3, "Lophophorus lhuysii")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.ASIA))
            .put(2, List.of(Region.ASIA))
            .put(3, List.of(Region.ASIA))
            .build();

    public MonalEntity(EntityType<? extends MonalEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 1.0F);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
    }

    protected <E extends MonalEntity> PlayState walkAnimController(final AnimationState<E> event) {
        if (event.isMoving()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        }
        if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public int numVariants() {
        return 3;
    }

    @Override
    public MonalEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        MonalEntity wildduckentity = CreaturesEntities.MONAL.get().create(p_241840_1_);
        wildduckentity.setVariant(this.getVariant());
        wildduckentity.setGender(this.random.nextInt(2));
        wildduckentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return wildduckentity;
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
            return CreaturesSound.MONAL_AMBIENT.get();
        }
        else
        {
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
        return CreaturesLootTables.LARGE_BIRD_GENERIC;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.BEETROOT, 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.monal_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.monal_clutch_size.get());
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 2) {
            return 2;
        } if (this.getVariant() == 3) {
            return 2;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.monal");
    }

    public boolean canTame() {
        return true;
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

}
