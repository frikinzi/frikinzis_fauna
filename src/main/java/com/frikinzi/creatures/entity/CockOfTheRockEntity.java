package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.FleeGoal;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CockOfTheRockEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Integer> VARIANT_SUBID = SynchedEntityData.defineId(CockOfTheRockEntity.class, EntityDataSerializers.INT);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.SWEET_BERRIES, Items.MELON_SLICE, Items.APPLE);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.andeancockoftherock"))
            .put(2, Component.translatable("message.creatures.guianancockoftherock"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Rupicola peruvianus")
            .put(2, "Rupicola rupicola")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.SOUTH_AMERICA))
            .put(2, List.of(Region.SOUTH_AMERICA))
            .build();

    public static Map<Integer, Integer> COCKOFTHEROCK = ImmutableMap.<Integer, Integer>builder()
            .put(1, 3)
            .put(2, 1)
            .build();

    public CockOfTheRockEntity(EntityType<? extends CockOfTheRockEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false ));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(4, new FleeGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D));
    }

    protected <E extends CockOfTheRockEntity> PlayState flyAnimController(final AnimationState<E> event)
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
        return 2;
    }

    @Override
    public CockOfTheRockEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        CockOfTheRockEntity sparrowentity = CreaturesEntities.COCK_OF_THE_ROCK.get().create(p_241840_1_);
        sparrowentity.setVariant(this.getVariant());
        sparrowentity.setGender(this.random.nextInt(2));
        sparrowentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return sparrowentity;
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)|| p_218106_.getBlockState(p_218108_.below()).is(BlockTags.SAND) )&& isBrightEnoughToSpawn(p_218106_, p_218108_);
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
        return CreaturesSound.COCKOFTHEROCK.get(); } else {
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

    public double getHatchChance() {
        return CreaturesConfig.cockoftherock_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.cockoftherock_clutch_size.get());
    }

    public int getMaxFlockSize() {
        return 10;
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.3F;
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.cockoftherock");
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT_SUBID, 0);
    }

    public void setSubVariant(int p_191997_1_) {
        this.entityData.set(VARIANT_SUBID, p_191997_1_);
    }

    public int getSubVariant() {
        return Mth.clamp(this.entityData.get(VARIANT_SUBID), 1, COCKOFTHEROCK.get(this.getVariant())+1);
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putInt("Subvariant", this.getSubVariant());
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);

        this.setSubVariant(p_70037_1_.getInt("Subvariant"));
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.SWEET_BERRIES, 1);
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

}
