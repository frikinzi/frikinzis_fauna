package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.PickUpFoodGoal;
import com.frikinzi.creatures.entity.ai.StayCloseToEggGoal;
import com.frikinzi.creatures.entity.base.WalkingSwimmingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LargePenguinEntity extends WalkingSwimmingBird implements GeoEntity {
    private boolean moving;
    private boolean isPesto;
    private static final Set<Block> SLIDE = Sets.newHashSet(Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE);
    private static final EntityDataAccessor<Integer> VARIANT_SUBID = SynchedEntityData.defineId(LargePenguinEntity.class, EntityDataSerializers.INT);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int heldFishTicks = 0;
    private static final int TICKS_TO_EAT = 200;
    //public int pickupCooldown = 0;
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON,Items.TROPICAL_FISH, CreaturesItems.RAW_RED_SNAPPER.get(), CreaturesItems.RAW_SQUID.get());
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.emperorpenguin"),
            2, Component.translatable("message.creatures.kingpenguin")
    );
    public static Map<Integer, Component> DESCRIPTIONS = ImmutableMap.of(
            1, Component.translatable("description.creatures.emperorpenguin"),
            2, Component.translatable("description.creatures.kingpenguin")
    );

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.ANTARCTICA))
            .put(2, List.of(Region.ANTARCTICA))
            .build();

    public static Map<Integer, Integer> BANDEDPENGUIN = ImmutableMap.<Integer, Integer>builder()
            .put(1, 1)
            .put(2, 1)
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Aptenodytes forsteri")
            .put(2, "Aptenodytes patagonicus")
            .build();

    public LargePenguinEntity(EntityType<? extends LargePenguinEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

//    protected float nextStep() {
//        return this.moveDist + 0.15F;
//    }

    protected <E extends LargePenguinEntity> PlayState walkAnimController(final AnimationState<E> event)
    {
        //this.isMoving() = event.isMoving();
        if (this.onIceBlock(this) && !this.isBaby() && event.isMoving()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("slide"));
        }
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

    protected void registerGoals(){
        this.goalSelector.addGoal(0, new SleepGoal());
        this.goalSelector.addGoal(1, new PickUpFoodGoal(this));
        this.goalSelector.addGoal(1, new StayCloseToEggGoal(this, 1.0D));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Cod.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Salmon.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, SquidEntity.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TropicalFish.class, false));
    }

    public void setHeldItem(ItemStack stack) {
        this.setItemSlot(EquipmentSlot.MAINHAND, stack);
        this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
    }

    public void clearHeldItem() {
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }


    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && !this.level().isClientSide() && !this.getMainHandItem().isEmpty()) {
            this.spawnAtLocation(this.getMainHandItem());
            this.clearHeldItem();
            heldFishTicks = 0;
            pickupCooldown = 200;
        }
        return result;
    }

//    protected void registerGoals() {
//        this.goalSelector.addGoal(7, new BrushTailedPenguinEntity.TravelGoal(this, 1.0D));
//        this.goalSelector.addGoal(9, new BrushTailedPenguinEntity.WanderGoal(this, 1.0D, 100));
//        this.goalSelector.addGoal(1, new StayCloseToEggGoal(this, 1.0D));
//        this.goalSelector.addGoal(2, new StayCloseToChildGoal(this, 1.0D));
//        this.goalSelector.addGoal(10, new LookAtGoal(this, Player.class, 8.0F));
//        this.goalSelector.addGoal(6, new CreaturesFollowGoal(this,1.0D, 5.0F, 1.0F, true));
//        this.goalSelector.addGoal(0, new SleepGoal());
//        //this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
//        this.goalSelector.addGoal(1, new MateGoal(this, 1.0D));
//        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, CodEntity.class, false));
//        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, SalmonEntity.class, false));
//        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TropicalFishEntity.class, false));
//
//    }

    public boolean moving() {
        return this.moving;
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
        return 2;
    }

    @Override
    public LargePenguinEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        LargePenguinEntity cormorantentity = CreaturesEntities.LARGE_PENGUIN.get().create(p_241840_1_);
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
            LargePenguinEntity ospreyentity = (LargePenguinEntity) p_70878_1_;
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping() && !this.isInWater()) {
        return CreaturesSound.LARGE_PENGUIN.get(); } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT_SUBID, 0);
    }

    public void setSubVariant(int p_191997_1_) {
        this.entityData.set(VARIANT_SUBID, p_191997_1_);
    }

    public int getSubVariant() {
        Integer max = BANDEDPENGUIN.get(this.getVariant());
        if (max == null) return 1;
        return Mth.clamp(this.entityData.get(VARIANT_SUBID), 1, max + 1);
    }
    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        if (p_70037_1_.contains("Pesto", 99)) {
            this.isPesto = p_70037_1_.getBoolean("Pesto");
        }
        this.setSubVariant(p_70037_1_.getInt("Subvariant"));
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public void setCustomName(@Nullable Component p_200203_1_) {
        super.setCustomName(p_200203_1_);
        if (!this.isPesto && p_200203_1_ != null && p_200203_1_.getString().equals("Pesto")) {
            this.isPesto = true;
        }

    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putInt("Subvariant", this.getSubVariant());
        if (this.isPesto) {
            p_213281_1_.putBoolean("Pesto", true);
        }
    }

    public boolean isBaby() {
        return super.isBaby();
    }

    public boolean isPesto() {
        if (!this.isBaby()) {
            return false;
        }
        if (this.getVariant() == 1) {
            return false;
        }
        if (this.getCustomName() != null) {
            return this.getCustomName().getString().equals("Pesto");
        }
        return this.isPesto;
    }

    public double getHatchChance() {
        return CreaturesConfig.penguin_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.penguin_clutch_size.get());
    }

    public void aiStep() {
        if (this.isPesto()) {
            this.setAge(-100);
        }
        if (!this.level().isClientSide()) {
            if (pickupCooldown > 0) pickupCooldown--;
            if (!this.getMainHandItem().isEmpty()) {
                heldFishTicks++;
                if (heldFishTicks >= TICKS_TO_EAT) {
                    this.heal(4.0F);

                    heldFishTicks = 0;
                    this.playSound(net.minecraft.sounds.SoundEvents.GENERIC_EAT,
                            1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                    // Spawn eating particles
                    if (this.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(new net.minecraft.core.particles.ItemParticleOption(
                                        net.minecraft.core.particles.ParticleTypes.ITEM,
                                        this.getMainHandItem()
                                ),
                                this.getX(), this.getY() + this.getBbHeight() * 0.8,
                                this.getZ(), 8, 0.1, 0.1, 0.1, 0.05);
                    }
                    this.clearHeldItem();
                }
            } else {
                heldFishTicks = 0;
            }
        }
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

    public int methodOfDeterminingVariant() {
        int var = this.random.nextInt(this.numVariants()) + 1;
        int mutationChance = CreaturesConfig.penguin_mutation_chance.get();
        if (mutationChance > 0 && this.random.nextInt(mutationChance) == 1) {
            Integer subCount = BANDEDPENGUIN.get(var);
            if (subCount != null && subCount > 0) {
                this.setSubVariant(this.random.nextInt(subCount) + 1);
            } else {
                this.setSubVariant(1);
            }
        } else {
            this.setSubVariant(1);
        }
        return var;
    }

    public String getGenderName() {
        if (this.getVariant()==4) {
            return "";
        }
        if (this.getGender() == 0) {
            return "f";
        } return "m";
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 1) {
            return 3;
        } if (this.getVariant() == 2) {
            return 0;
        } return super.getIUCNStatus();
    }

    public boolean onIceBlock(LargePenguinEntity entity) {
        BlockPos pos = entity.blockPosition().below();
        BlockState blockState = entity.level().getBlockState(pos);
        return SLIDE.contains(blockState.getBlock());
    }

    public Component getFunFact() {
        Component translatable = DESCRIPTIONS.get(this.getVariant());
        if (translatable != null) {
            return translatable;
        } return Component.translatable("creatures.unknown");
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public int getScaleforGUI() {
        return (int)(super.getScaleforGUI() *1.5f);
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }



}
