package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.CreaturesFollowGoal;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.entity.ai.StayCloseToEggGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.TameableWalkingBirdBase;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.registry.ModEntityTypes;
import com.frikinzi.creatures.util.CreaturesLootTables;
import com.frikinzi.creatures.util.EntityAttributes;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CormorantEntity extends TameableWalkingBirdBase implements IAnimatable {
    private final GroundPathNavigator groundNavigation;
    protected final SwimmerPathNavigator waterNavigation;
    private boolean searchingForLand;
    private AnimationFactory factory = new AnimationFactory(this);
    private PanicGoal PanicGoal;
    private static final DataParameter<BlockPos> TRAVEL_POS = EntityDataManager.defineId(CormorantEntity.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<Boolean> TRAVELLING = EntityDataManager.defineId(CormorantEntity.class, DataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD);
    public static Map<Integer, TranslationTextComponent> SPECIES_NAMES = ImmutableMap.of(
            1, new TranslationTextComponent("message.creatures.piedcormorant"),
            2, new TranslationTextComponent("message.creatures.whitebreastedcormorant"),
            3, new TranslationTextComponent("message.creatures.doublecrestedcormorant"),
            4, new TranslationTextComponent("message.creatures.greatcormorant")

    );

    public CormorantEntity(EntityType<? extends CormorantEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
        this.moveControl = new CormorantEntity.MoveHelperController(this);
        this.maxUpStep = 1.0F;
        this.groundNavigation = new GroundPathNavigator(this, p_i50251_2_);
        this.waterNavigation = new SwimmerPathNavigator(this, p_i50251_2_);

    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        this.setTravelPos(BlockPos.ZERO);
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

   protected PathNavigator createNavigation(World p_175447_1_) {
       return new CormorantEntity.Navigator(this, p_175447_1_);
   }

    protected float nextStep() {
        return this.moveDist + 0.15F;
    }

    public float getWalkTargetValue(BlockPos p_205022_1_, IWorldReader p_205022_2_) {
        if (p_205022_2_.getFluidState(p_205022_1_).is(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return p_205022_2_.getBrightness(p_205022_1_) - 0.5F;
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.isInSittingPose()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }
        if (this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
            return PlayState.CONTINUE;
        }
        else {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
                return PlayState.CONTINUE;
            }
            if (this.isSleeping()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(7, new CormorantEntity.TravelGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new CormorantEntity.WanderGoal(this, 1.0D, 100));
        this.goalSelector.addGoal(1, new StayCloseToEggGoal(this, 1.0D));
        //this.goalSelector.addGoal(6, new CormorantEntity.GoToBeachGoal(this, 1.0D));
        //this.goalSelector.addGoal(4, new CormorantEntity.SwimUpGoal(this, 1.0D, this.level.getSeaLevel()));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(1, new SitGoal(this));
        this.goalSelector.addGoal(6, new CreaturesFollowGoal(this,1.0D, 5.0F, 1.0F, true));
        this.goalSelector.addGoal(0, new TameableWalkingBirdBase.SleepGoal());
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(2, new MateGoal(this, 1.0D));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, CodEntity.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, SalmonEntity.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TropicalFishEntity.class, false));

    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int determineVariant() {
        return 5;
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        CormorantEntity cormorantentity = (CormorantEntity) getType().create(p_241840_1_);
        cormorantentity.setVariant(this.getVariant());
        cormorantentity.setGender(this.random.nextInt(2));
        cormorantentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return cormorantentity;
    }

    protected void playSwimSound(float p_203006_1_) {
        super.playSwimSound(p_203006_1_ * 1.5F);
    }


    @Override
    public boolean canMate(AnimalEntity p_70878_1_) {
        if (p_70878_1_ == this) {
            return false;
        } else if (p_70878_1_.getClass() != this.getClass()) {
            return false;
        } else {
            CormorantEntity ospreyentity = (CormorantEntity) p_70878_1_;
            if (!ospreyentity.isTame()) {
                return false;
            }
            else if (ospreyentity.isInSittingPose()) {
                return false;
            }
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public Set<Item> getTamedFood() {
            return TAME_FOOD = Sets.newHashSet(Items.COD, Items.TROPICAL_FISH);
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.CORMORANT; } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.PEAFOWL;
    }

    public CreaturesEggEntity layEgg(CreaturesBirdEntity animal) {
        CreaturesEggEntity egg = new CreaturesEggEntity(ModEntityTypes.EGG.get(), this.level);
        egg.setSpecies(EntityAttributes.getBirdEntityMap().inverse().get(animal.getType()));
        egg.setGender(this.random.nextInt(2));
        egg.setVariant(this.getVariant());
        return egg;
    }

    public void updateSwimming() {
        if (!this.level.isClientSide) {
            if (this.isEffectiveAi() && this.isInWater() && this.wantsToSwim()) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.groundNavigation;
                this.setSwimming(false);
            }
        }

    }

    private boolean wantsToSwim() {
        if (this.searchingForLand) {
            return true;
        } else {
            LivingEntity livingentity = this.getTarget();
            return livingentity != null && livingentity.isInWater();
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TRAVELLING, false);
        this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
    }

    private void setTravelPos(BlockPos p_203019_1_) {
        this.entityData.set(TRAVEL_POS, p_203019_1_);
    }

    private BlockPos getTravelPos() {
        return this.entityData.get(TRAVEL_POS);
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        int l = p_70037_1_.getInt("TravelPosX");
        int i1 = p_70037_1_.getInt("TravelPosY");
        int j1 = p_70037_1_.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, i1, j1));
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public String getSpeciesName() {
        TranslationTextComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putInt("TravelPosX", this.getTravelPos().getX());
        p_213281_1_.putInt("TravelPosY", this.getTravelPos().getY());
        p_213281_1_.putInt("TravelPosZ", this.getTravelPos().getZ());
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.peafowl_hatch_chance.get()).floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.peafowl_clutch_size.get());
    }

    private boolean isTravelling() {
        return this.entityData.get(TRAVELLING);
    }

    private void setTravelling(boolean p_203021_1_) {
        this.entityData.set(TRAVELLING, p_203021_1_);
    }

    public void travel(Vector3d p_213352_1_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, p_213352_1_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
//            if (this.getTarget() == null) {
//                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
//            }
        } else {
            super.travel(p_213352_1_);
        }

    }

    static class MoveHelperController extends MovementController {
        private final CormorantEntity cormorant;

        MoveHelperController(CormorantEntity p_i48817_1_) {
            super(p_i48817_1_);
            this.cormorant = p_i48817_1_;
        }

        private void updateSpeed() {
 //           if (this.cormorant.isInWater()) {
 //               this.cormorant.setDeltaMovement(this.cormorant.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
//
 //           }

        }

        public void tick() {
            if (this.cormorant.wantsToSwim() && this.cormorant.isInWater()) {
                if (this.cormorant.searchingForLand) {
                    this.cormorant.setDeltaMovement(this.cormorant.getDeltaMovement().add(0.0D, 0.002D, 0.0D));
                }
            }

            this.updateSpeed();
            if (this.operation == MovementController.Action.MOVE_TO && !this.cormorant.getNavigation().isDone()) {
                double d0 = this.wantedX - this.cormorant.getX();
                double d1 = this.wantedY - this.cormorant.getY();
                double d2 = this.wantedZ - this.cormorant.getZ();
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.cormorant.yRot = this.rotlerp(this.cormorant.yRot, f, 90.0F);
                this.cormorant.yBodyRot = this.cormorant.yRot;
                float f1 = (float)(this.speedModifier * this.cormorant.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.cormorant.setSpeed(MathHelper.lerp(0.125F, this.cormorant.getSpeed(), f1));
                this.cormorant.setDeltaMovement(this.cormorant.getDeltaMovement().add(0.0D, (double)this.cormorant.getSpeed() * d1 * 0.1D, 0.0D));
            }
            else {
                this.cormorant.setSpeed(0.0F);
            }
        }
    }


    static class Navigator extends SwimmerPathNavigator {
        Navigator(CormorantEntity p_i48815_1_, World p_i48815_2_) {
            super(p_i48815_1_, p_i48815_2_);
        }

        protected boolean canUpdatePath() {
            return true;
        }

        protected PathFinder createPathFinder(int p_179679_1_) {
            this.nodeEvaluator = new WalkAndSwimNodeProcessor();
            return new PathFinder(this.nodeEvaluator, p_179679_1_);
        }

        public boolean isStableDestination(BlockPos p_188555_1_) {
            if (this.mob instanceof CormorantEntity) {
                CormorantEntity cormorant = (CormorantEntity)this.mob;
                if (cormorant.isTravelling()) {
                    //return this.level.getBlockState(p_188555_1_).is(Blocks.WATER);
                }
            }

            return !this.level.getBlockState(p_188555_1_.below()).isAir();
        }
    }

    static class TravelGoal extends Goal {
        private final CormorantEntity cormorant;
        private final double speedModifier;
        private boolean stuck;

        TravelGoal(CormorantEntity p_i48811_1_, double p_i48811_2_) {
            this.cormorant = p_i48811_1_;
            this.speedModifier = p_i48811_2_;
        }

        public boolean canUse() {
            return this.cormorant.isInWater();
        }

        public void start() {
            int i = 512;
            int j = 4;
            Random random = this.cormorant.random;
            int k = random.nextInt(1025) - 512;
            int l = random.nextInt(9) - 4;
            int i1 = random.nextInt(1025) - 512;
            //if ((double)l + this.cormorant.getY() > (double)(this.cormorant.level.getSeaLevel() - 1)) {
            //    l = 0;
            //}

            BlockPos blockpos = new BlockPos((double)k + this.cormorant.getX(), (double)l + this.cormorant.getY(), (double)i1 + this.cormorant.getZ());
            this.cormorant.setTravelPos(blockpos);
            this.cormorant.setTravelling(true);
            this.stuck = false;
        }

        public void tick() {
            if (this.cormorant.getNavigation().isDone()) {
                Vector3d vector3d = Vector3d.atBottomCenterOf(this.cormorant.getTravelPos());
                Vector3d vector3d1 = RandomPositionGenerator.getPosTowards(this.cormorant, 16, 3, vector3d, (double)((float)Math.PI / 10F));
                if (vector3d1 == null) {
                    vector3d1 = RandomPositionGenerator.getPosTowards(this.cormorant, 8, 7, vector3d);
                }

                if (vector3d1 != null) {
                    int i = MathHelper.floor(vector3d1.x);
                    int j = MathHelper.floor(vector3d1.z);
                    int k = 34;
                    if (!this.cormorant.level.hasChunksAt(i - 34, 0, j - 34, i + 34, 0, j + 34)) {
                        vector3d1 = null;
                    }
                }

                if (vector3d1 == null) {
                    this.stuck = true;
                    return;
                }

                this.cormorant.getNavigation().moveTo(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
            }

        }

        public boolean canContinueToUse() {
            return !this.cormorant.getNavigation().isDone() && !this.stuck && !this.cormorant.isInLove();
        }

        public void stop() {
            this.cormorant.setTravelling(false);
            super.stop();
        }
    }

    static class WanderGoal extends RandomWalkingGoal {
        private final CormorantEntity cormorant;

        private WanderGoal(CormorantEntity p_i48813_1_, double p_i48813_2_, int p_i48813_4_) {
            super(p_i48813_1_, p_i48813_2_, p_i48813_4_);
            this.cormorant = p_i48813_1_;
        }

        public boolean canUse() {
            return !this.mob.isInWater() && super.canUse();
        }
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    static class GoToBeachGoal extends MoveToBlockGoal {
        private final CormorantEntity drowned;

        public GoToBeachGoal(CormorantEntity p_i48911_1_, double p_i48911_2_) {
            super(p_i48911_1_, p_i48911_2_, 8, 2);
            this.drowned = p_i48911_1_;
        }

        public boolean canUse() {
            return super.canUse() && !this.drowned.level.isDay() && this.drowned.isInWater() && this.drowned.getY() >= (double)(this.drowned.level.getSeaLevel() - 3);
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }

        protected boolean isValidTarget(IWorldReader p_179488_1_, BlockPos p_179488_2_) {
            BlockPos blockpos = p_179488_2_.above();
            return p_179488_1_.isEmptyBlock(blockpos) && p_179488_1_.isEmptyBlock(blockpos.above()) ? p_179488_1_.getBlockState(p_179488_2_).entityCanStandOn(p_179488_1_, p_179488_2_, this.drowned) : false;
        }

        public void start() {
            this.drowned.setSearchingForLand(false);
            this.drowned.navigation = this.drowned.groundNavigation;
            super.start();
        }

        public void stop() {
            super.stop();
        }
    }

    public void setSearchingForLand(boolean p_204713_1_) {
        this.searchingForLand = p_204713_1_;
    }

    static class SwimUpGoal extends Goal {
        private final CormorantEntity drowned;
        private final double speedModifier;
        private final int seaLevel;
        private boolean stuck;

        public SwimUpGoal(CormorantEntity p_i48908_1_, double p_i48908_2_, int p_i48908_4_) {
            this.drowned = p_i48908_1_;
            this.speedModifier = p_i48908_2_;
            this.seaLevel = p_i48908_4_;
        }

        public boolean canUse() {
            return this.drowned.isInWater() && this.drowned.getY() < (double)(this.seaLevel)-2;
        }

        public boolean canContinueToUse() {
            return this.canUse() && !this.stuck;
        }

        public void tick() {
            if (this.drowned.getY() < (double)(this.seaLevel - 1) && (this.drowned.getNavigation().isDone() || this.drowned.closeToNextPos())) {
                Vector3d vector3d = RandomPositionGenerator.getPosTowards(this.drowned, 4, 8, new Vector3d(this.drowned.getX(), (double)(this.seaLevel - 1), this.drowned.getZ()));
                if (vector3d == null) {
                    this.stuck = true;
                    return;
                }

                this.drowned.getNavigation().moveTo(vector3d.x, vector3d.y, vector3d.z, this.speedModifier);
            }

        }

        public void start() {
            this.drowned.setSearchingForLand(true);
            this.stuck = false;
        }

        public void stop() {
            this.drowned.setSearchingForLand(false);
        }
    }

    protected boolean closeToNextPos() {
        Path path = this.getNavigation().getPath();
        if (path != null) {
            BlockPos blockpos = path.getTarget();
            if (blockpos != null) {
                double d0 = this.distanceToSqr((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
                if (d0 < 4.0D) {
                    return true;
                }
            }
        }

        return false;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.COD, 1);
    }


}
