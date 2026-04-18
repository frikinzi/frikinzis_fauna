package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.WalkingSwimmingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
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
import java.util.Set;

public class BrushTailedPenguinEntity extends WalkingSwimmingBird implements GeoEntity {
    private boolean moving;
    private static final Set<Block> SLIDE = Sets.newHashSet(Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE);
    private static final EntityDataAccessor<Integer> VARIANT_SUBID = SynchedEntityData.defineId(BrushTailedPenguinEntity.class, EntityDataSerializers.INT);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH);
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.chinstrap"),
            2, Component.translatable("message.creatures.gentoo"),
            3, Component.translatable("message.creatures.adelie")
    );
    public static Map<Integer, Component> DESCRIPTIONS = ImmutableMap.of(
            1, Component.translatable("description.creatures.chinstrap"),
            2, Component.translatable("description.creatures.gentoo"),
            3, Component.translatable("description.creatures.adelie")
    );
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.ANTARCTICA, Region.SOUTH_AMERICA))
            .put(2, List.of(Region.ANTARCTICA, Region.SOUTH_AMERICA))
            .put(3, List.of(Region.ANTARCTICA))
            .build();

    public static Map<Integer, Integer> BANDEDPENGUIN = ImmutableMap.<Integer, Integer>builder()
            .put(1, 4)
            .put(2, 3)
            .put(3, 4)
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Pygoscelis antarcticus")
            .put(2, "Pygoscelis papua")
            .put(3, "Pygoscelis adeliae")
            .build();

    public BrushTailedPenguinEntity(EntityType<? extends BrushTailedPenguinEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

//    protected float nextStep() {
//        return this.moveDist + 0.15F;
//    }

    protected <E extends BrushTailedPenguinEntity> PlayState walkAnimController(final AnimationState<E> event)
    {
        this.moving = event.isMoving();
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
            if (event.isMoving() && !this.isBaby()) {
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
        return 3;
    }

    @Override
    public BrushTailedPenguinEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        BrushTailedPenguinEntity cormorantentity = CreaturesEntities.BRUSH_TAILED_PENGUIN.get().create(p_241840_1_);
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
            BrushTailedPenguinEntity ospreyentity = (BrushTailedPenguinEntity) p_70878_1_;
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping() && !this.isInWater()) {
        return CreaturesSound.BRUSHTAILED_PENGUIN.get(); } else {
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
        return Mth.clamp(this.entityData.get(VARIANT_SUBID), 1, BANDEDPENGUIN.get(this.getVariant())+1);
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);

        this.setSubVariant(p_70037_1_.getInt("Subvariant"));
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putInt("Subvariant", this.getSubVariant());
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

    public int methodOfDeterminingVariant() {
        int var = this.random.nextInt(3)+1;
        if (this.random.nextInt(CreaturesConfig.penguin_mutation_chance.get()) == 1) {
            this.setSubVariant(this.random.nextInt(BANDEDPENGUIN.get(var))+1);
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
        if (this.getVariant() == 2 || this.getVariant() == 4) {
            return 3;
        } if (this.getVariant() == 1) {
            return 2;
        } return super.getIUCNStatus();
    }

    public boolean onIceBlock(BrushTailedPenguinEntity entity) {
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

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

}
