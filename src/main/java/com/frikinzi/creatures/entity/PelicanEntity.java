package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.PickUpFoodGoal;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PelicanEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Boolean> HAS_FISH =
            SynchedEntityData.defineId(PelicanEntity.class, EntityDataSerializers.BOOLEAN);

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, CreaturesItems.CRAB_PINCERS.get(), CreaturesItems.RAW_TROUT.get(), Items.TROPICAL_FISH, CreaturesItems.RAW_RED_SNAPPER.get());
    private static final int MAX_POUCH_SIZE = 10;
    private final List<ItemStack> pouchItems = new ArrayList<>();
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.greatwhite"))
            .put(2, Component.translatable("message.creatures.brownpelican"))
            .put(3, Component.translatable("message.creatures.australianpelican"))
            .put(4, Component.translatable("message.creatures.pinkbacked"))
            .put(5, Component.translatable("message.creatures.americanwhite"))
            .put(6, Component.translatable("message.creatures.dalmatian"))
            .put(7, Component.translatable("message.creatures.peruvianpelican"))
            .put(8, Component.translatable("message.creatures.spotbilledpelican"))
            .build();

    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Pelecanus onocrotalus")
            .put(2, "Pelecanus occidentalis")
            .put(3, "Pelecanus conspicillatus")
            .put(4, "Pelecanus rufescens")
            .put(5, "Pelecanus erythrorhynchos")
            .put(6, "Pelecanus crispus")
            .put(7, "Pelecanus thagus")
            .put(8, "Pelecanus philippensis")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE, Region.AFRICA, Region.ASIA))
            .put(2, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(3, List.of(Region.OCEANIA))
            .put(4, List.of(Region.AFRICA))
            .put(5, List.of(Region.NORTH_AMERICA))
            .put(6, List.of(Region.EUROPE, Region.ASIA))
            .put(7, List.of(Region.SOUTH_AMERICA))
            .put(8, List.of(Region.ASIA))
            .build();

    public PelicanEntity(EntityType<? extends PelicanEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new PickUpFoodGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Cod.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Salmon.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TropicalFish.class, false));
        this.goalSelector.addGoal(6, new FollowFlockLeaderGoal(this));
    }

    protected <E extends PelicanEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if ((!this.onGround() || this.isFlying()) && !this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        }
        if (event.isMoving() && this.onGround() || this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        }  if (this.isSleeping()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
    }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    private <E extends PelicanEntity> PlayState pouchController(AnimationState<E> event) {
        if (this.hasFishInPouch()) {
            return event.setAndContinue(RawAnimation.begin().thenPlayAndHold("openmouth"));
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Flying", 0, this::flyAnimController));
        controllers.add(new AnimationController<>(this, "Pouch", 0, this::pouchController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    public int numVariants() {
        return 8;
    }

    @Override
    public PelicanEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        PelicanEntity rollerentity = CreaturesEntities.PELICAN.get().create(p_241840_1_);
        rollerentity.setVariant(this.getVariant());
        rollerentity.setGender(this.random.nextInt(2));
        rollerentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return rollerentity;
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
        return CreaturesSound.PELICAN_AMBIENT.get(); } else {
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
        return CreaturesConfig.pelican_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.pelican_clutch_size.get());
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)|| p_218106_.getBlockState(p_218108_.below()).is(BlockTags.SAND) )&& isBrightEnoughToSpawn(p_218106_, p_218108_);
    }

    public int getMaxFlockSize() {
        return 5;
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 6 || this.getVariant() == 7 || this.getVariant() == 8) {
            return 1;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.pelican");
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }


    public boolean addToPouch(ItemStack item) {
        if (pouchItems.size() >= MAX_POUCH_SIZE) return false;
        pouchItems.add(item.copyWithCount(1));
        this.entityData.set(HAS_FISH, true);
        updateHeldItem();
        return true;
    }

    public void clearPouch() {
        pouchItems.clear();
        this.entityData.set(HAS_FISH, false);
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    public boolean isPouchFull() {
        return pouchItems.size() >= MAX_POUCH_SIZE;
    }

    public boolean hasFishInPouch() {
        return this.entityData.get(HAS_FISH);
    }

    private void updateHeldItem() {
        if (!pouchItems.isEmpty()) {
            this.setItemSlot(EquipmentSlot.MAINHAND, pouchItems.get(0));
            this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
        } else {
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }
        if (pouchItems.size() >= 2) {
            this.setItemSlot(EquipmentSlot.OFFHAND, pouchItems.get(1));
            this.setGuaranteedDrop(EquipmentSlot.OFFHAND);
        } else {
            this.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        net.minecraft.nbt.ListTag list = new net.minecraft.nbt.ListTag();
        for (ItemStack stack : pouchItems) {
            list.add(stack.save(new CompoundTag()));
        }
        tag.put("PouchItems", list);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        pouchItems.clear();
        net.minecraft.nbt.ListTag list = tag.getList("PouchItems", 10);
        for (int i = 0; i < list.size(); i++) {
            pouchItems.add(ItemStack.of(list.getCompound(i)));
        }
        updateHeldItem();
    }

    private int eatTimer = 0;

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide() && hasFishInPouch()) {
            eatTimer++;
            if (eatTimer >= 400) {
                pouchItems.remove(0);
                if (pouchItems.isEmpty()) this.entityData.set(HAS_FISH, false);
                this.heal(4.0F);
                this.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
                updateHeldItem();
                eatTimer = 0;
            }
        }
    }

    public ItemStack getPouchItem(int index) {
        if (index < pouchItems.size()) {
            return pouchItems.get(index);
        }
        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_FISH, false);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && !this.level().isClientSide() && !this.getMainHandItem().isEmpty()) {
            for (ItemStack stack : pouchItems) {
                this.spawnAtLocation(stack);
            }
            this.clearPouch();
            pickupCooldown = 200;
        }
        return result;
    }



}
