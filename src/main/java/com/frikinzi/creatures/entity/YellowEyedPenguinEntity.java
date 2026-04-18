package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.WalkingSwimmingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
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
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
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

import java.util.*;

public class YellowEyedPenguinEntity extends WalkingSwimmingBird implements GeoEntity {
    private static final EntityDataAccessor<Integer> VARIANT_SUBID = SynchedEntityData.defineId(YellowEyedPenguinEntity.class, EntityDataSerializers.INT);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(YellowEyedPenguinEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> TRAVELLING = SynchedEntityData.defineId(YellowEyedPenguinEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, CreaturesItems.RAW_RED_SNAPPER.get());
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.OCEANIA))
            .build();

    static {
        Map<Integer, Component> messageMap = new HashMap<>();
        messageMap.put(1, Component.translatable("message.creatures.yelloweyedpenguin"));
        SPECIES_NAMES = Collections.unmodifiableMap(messageMap);
    }
    public static final Map<Integer, Component> DESCRIPTION;

    static {
        Map<Integer, Component> messageMap = new HashMap<>();
        messageMap.put(1, Component.translatable("description.creatures.yelloweyedpenguin"));
        DESCRIPTION = Collections.unmodifiableMap(messageMap);
    }

    public YellowEyedPenguinEntity(EntityType<? extends YellowEyedPenguinEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);

    }

    protected <E extends YellowEyedPenguinEntity> PlayState walkAnimController(final AnimationState<E> event)
    {
        if (this.isInWater()) {
            if (this.isBaby()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
            }
            if (!event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("dive"));
        }
        else {
            if (event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
            }

            if (this.isSleeping()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
            }
            if (this.isGrooming()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("grooming"));

            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int numVariants() {
        return 1;
    }

    @Override
    public YellowEyedPenguinEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        YellowEyedPenguinEntity cormorantentity = CreaturesEntities.YELLOW_EYED_PENGUIN.get().create(p_241840_1_);
        cormorantentity.setVariant(this.getVariant());
        cormorantentity.setGender(this.random.nextInt(2));
        cormorantentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return cormorantentity;
    }

    protected void playSwimSound(float p_203006_1_) {
        super.playSwimSound(p_203006_1_ * 1.5F);
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
        return CreaturesSound.YELLOWEYED_PENGUIN.get(); } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TRAVELLING, false);
        this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
        this.entityData.define(VARIANT_SUBID, 0);
    }

    private void setTravelPos(BlockPos p_203019_1_) {
        this.entityData.set(TRAVEL_POS, p_203019_1_);
    }

    private BlockPos getTravelPos() {
        return this.entityData.get(TRAVEL_POS);
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }
    public double getHatchChance() {
        return CreaturesConfig.penguin_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.penguin_clutch_size.get());
    }

    public void aiStep() {
        if (!this.level().isClientSide) {
            if (this.isBaby()) {
                this.getNavigation().stop();
            }
            int i = this.random.nextInt(3000);

            if (i == 0 && !this.isInWater() && !this.isSleeping() && !this.isBaby()) {
                this.getNavigation().stop();
                this.setGrooming(true);
            }
            if ((i == 1 || this.isInWater()) && this.isGrooming()) {
                this.setGrooming(false);
            }
        }
        super.aiStep();
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.COD, 1);
    }

    public int getIUCNStatus() {
        return 3;
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.yelloweyedpenguin");
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }
}
