package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.CormorantAi;
import com.frikinzi.creatures.entity.ai.StayCloseToEggGoal;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.entity.base.WalkingSwimmingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSensorTypes;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.PathFinder;
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
import java.util.Map;

public class CormorantEntity extends WalkingSwimmingBird implements GeoEntity {

    private boolean searchingForLand;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private PanicGoal PanicGoal;
    protected static final ImmutableList<SensorType<? extends Sensor<? super CormorantEntity>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.HURT_BY, CreaturesSensorTypes.CORMORANT_ATTACKABLES.get(), SensorType.IS_IN_WATER);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.PATH,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.ATTACK_COOLING_DOWN,
            MemoryModuleType.TEMPTING_PLAYER,
            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
            MemoryModuleType.IS_TEMPTED,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.NEAREST_ATTACKABLE,
            MemoryModuleType.IS_IN_WATER,
            MemoryModuleType.HAS_HUNTING_COOLDOWN
    );    private static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(CormorantEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> TRAVELLING = SynchedEntityData.defineId(CormorantEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD);
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.piedcormorant"),
            2, Component.translatable("message.creatures.whitebreastedcormorant"),
            3, Component.translatable("message.creatures.doublecrestedcormorant"),
            4, Component.translatable("message.creatures.greatcormorant")

    );
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Phalacrocorax varius")
            .put(2, "Phalacrocorax lucidus")
            .put(3, "Phalacrocorax auritus")
            .put(4, "Phalacrocorax carbo")
            .build();

    public CormorantEntity(EntityType<? extends CormorantEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 4.0F);
        //this.moveControl =  new CormorantMoveControl(this);
        this.moveControl =  new SmoothSwimmingMoveControl(this, 85, 10, 0.1F, 0.5F, false);
        this.lookControl = new CormorantLookControl(this, 20);
        this.setMaxUpStep(1.0F);

    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_29389_, DifficultyInstance p_29390_, MobSpawnType p_29391_, @Nullable SpawnGroupData p_29392_, @Nullable CompoundTag p_29393_) {
        return super.finalizeSpawn(p_29389_, p_29390_, p_29391_, p_29392_, p_29393_);
    }

    protected float nextStep() {
        return this.moveDist + 0.15F;
    }

    protected <E extends CormorantEntity> PlayState walkAnimController(final AnimationState<E> event)
    {
        if (this.isInSittingPose()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
        if (this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
        }
        else {
            if (event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
            }
            if (this.isSleeping()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SleepGoal());
        this.goalSelector.addGoal(1, new StayCloseToEggGoal(this, 1.0D));
//        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
//        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0D));
//        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 2.0D, 40));
//        this.goalSelector.addGoal(2, new MateGoal(this, 1.0D));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Cod.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Salmon.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TropicalFish.class, false));

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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, 0.3D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int numVariants() {
        return 4;
    }

    @Override
    public CormorantEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        CormorantEntity cormorantentity = CreaturesEntities.CORMORANT.get().create(p_241840_1_);
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
            CormorantEntity cormorantEntity = (CormorantEntity) p_70878_1_;
//            if (!ospreyentity.isTame()) {
//                return false;
//            }
//            else if (ospreyentity.isInSittingPose()) {
//                return false;
//            }
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }


    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.CORMORANT.get(); } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.LARGE_BIRD_GENERIC;
    }

    protected PathNavigation createNavigation(Level p_149128_) {
        return new CormorantPathNavigation(this, p_149128_);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TRAVELLING, false);
        this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
    }


    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public double getHatchChance() {
        return CreaturesConfig.cormorant_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.cormorant_clutch_size.get());
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.COD, 1);
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public boolean canTame() {
        return false;
    }

    class CormorantLookControl extends SmoothSwimmingLookControl {
        public CormorantLookControl(CormorantEntity p_149210_, int p_149211_) {
            super(p_149210_, p_149211_);
        }

        public void tick() {
            super.tick();

        }
    }

    static class CormorantMoveControl extends SmoothSwimmingMoveControl {
        private final CormorantEntity cormorant;

        public CormorantMoveControl(CormorantEntity p_149215_) {
            super(p_149215_, 85, 10, 0.1F, 0.5F, false);
            this.cormorant = p_149215_;
        }

        public void tick() {
            super.tick();

        }
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    public boolean doHurtTarget(Entity p_149201_) {
        boolean flag = p_149201_.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, p_149201_);
            //this.playSound(SoundEvents.AXOLOTL_ATTACK, 1.0F, 1.0F);
        }

        return flag;
    }

    public double getMeleeAttackRangeSqr(LivingEntity p_149185_) {
        return 1.5D + (double)p_149185_.getBbWidth() * 2.0D;
    }

    public void travel(Vec3 p_149181_) {
        if (this.isControlledByLocalInstance() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), p_149181_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(p_149181_);
        }
    }

    static class CormorantNodeEvaluator extends AmphibiousNodeEvaluator {
        private final BlockPos.MutableBlockPos belowPos = new BlockPos.MutableBlockPos();

        public CormorantNodeEvaluator(boolean p_218548_) {
            super(p_218548_);
        }

        public Node getStart() {
            return !this.mob.isInWater() ? super.getStart() : this.getStartNode(new BlockPos(Mth.floor(this.mob.getBoundingBox().minX), Mth.floor(this.mob.getBoundingBox().minY), Mth.floor(this.mob.getBoundingBox().minZ)));
        }

        public BlockPathTypes getBlockPathType(BlockGetter p_218551_, int p_218552_, int p_218553_, int p_218554_) {
            this.belowPos.set(p_218552_, p_218553_ - 1, p_218554_);
            BlockState blockstate = p_218551_.getBlockState(this.belowPos);
            return blockstate.is(BlockTags.FROG_PREFER_JUMP_TO) ? BlockPathTypes.OPEN : super.getBlockPathType(p_218551_, p_218552_, p_218553_, p_218554_);
        }
    }

    static class CormorantPathNavigation extends AmphibiousPathNavigation {
        CormorantPathNavigation(CormorantEntity p_218556_, Level p_218557_) {
            super(p_218556_, p_218557_);
        }

        public boolean canCutCorner(BlockPathTypes p_265335_) {
            return p_265335_ != BlockPathTypes.WATER_BORDER && super.canCutCorner(p_265335_);
        }

        protected PathFinder createPathFinder(int p_218559_) {
            this.nodeEvaluator = new CormorantEntity.CormorantNodeEvaluator(true);
            this.nodeEvaluator.setCanPassDoors(true);
            return new PathFinder(this.nodeEvaluator, p_218559_);
        }
    }


}
