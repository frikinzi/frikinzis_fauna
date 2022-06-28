package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.gui.GUICreatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.CreaturesFollowGoal;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class TameableBirdBase extends CreaturesBirdEntity implements IFlyingAnimal {
    private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(TameableBirdBase.class, DataSerializers.INT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.defineId(TameableBirdBase.class, DataSerializers.INT);
    public static Set<Item> TAME_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;
    public World world;
    protected GroundPathNavigator groundNavigation;

    public TameableBirdBase(EntityType<? extends TameableBirdBase> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.moveControl = new TameableBirdBase.FlyController(this, 10, false);
        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(PathNodeType.COCOA, -1.0F);
        this.groundNavigation = new GroundPathNavigator(this, p_i50251_2_);

    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        this.setVariant(methodofDeterminingVariant(p_213386_1_));
        this.setGender(this.random.nextInt(2));
        if (p_213386_4_ == null) {
            p_213386_4_ = new AgeableEntity.AgeableData(false);
        }

        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(2, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(1, new SitGoal(this));
        this.goalSelector.addGoal(6, new CreaturesFollowGoal(this,1.0D, 5.0F, 1.0F, true));
        this.targetSelector.addGoal(7, new TameableBirdBase.FlyingGoal());
        this.goalSelector.addGoal(0, new TameableBirdBase.SleepGoal());
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    }

    public int determineVariant() {
        return 1;
    }


    protected PathNavigator createNavigation(World p_175447_1_) {
        BabyNoFlyNavigator flyingpathnavigator = new BabyNoFlyNavigator(this, p_175447_1_);
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(true);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }

    public void aiStep() {
        super.aiStep();
        this.calculateFlapping();
    }


    private void calculateFlapping() {
            this.oFlap = this.flap;
            this.oFlapSpeed = this.flapSpeed;
            this.flapSpeed = (float) ((double) this.flapSpeed + (double) (!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3D);
            this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
            if (!this.onGround && this.flapping < 1.0F) {
                this.flapping = 1.0F;
            }

            this.flapping = (float) ((double) this.flapping * 0.9D);
            Vector3d vector3d = this.getDeltaMovement();
            if (this.isAggressive() && !this.onGround && vector3d.y < 0.0D) {
                this.setDeltaMovement(vector3d.multiply(1.0D, 1.1D, 1.0D));
            }
            else if (!this.onGround && vector3d.y < 0.0D) {
                this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
            }

            this.flap += this.flapping * 2.0F;
    }


    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE) {
            Creatures.PROXY.setReferencedMob(this);
            if (this.level.isClientSide) {
            Creatures.PROXY.openCreaturesGUI(itemstack);
            return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        }
        if (!this.isTame() && getTamedFood().contains(itemstack.getItem())) {
            if (!p_230254_1_.abilities.instabuild) {
                itemstack.shrink(1);
            }

            if (!this.isSilent()) {
                this.level.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.PARROT_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }

            if (!this.level.isClientSide) {
                if (this.random.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_230254_1_)) {
                    this.tame(p_230254_1_);
                    this.level.broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte)6);
                }
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }  else if (!this.isFlying() && !getTamedFood().contains(itemstack.getItem()) && this.isTame() && this.isOwnedBy(p_230254_1_)) {
            if (!this.level.isClientSide) {
                this.setOrderedToSit(!this.isOrderedToSit());
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }


    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
    }

    protected void checkFallDamage(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {
    }


    public boolean canMate(AnimalEntity p_70878_1_) {
        return false;
    }

    @Nullable
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        return p_70652_1_.hurt(DamageSource.mobAttack(this), 3.0F);
    }

    public boolean isPushable() {
        return true;
    }

    protected void doPush(Entity p_82167_1_) {
        if (!(p_82167_1_ instanceof PlayerEntity)) {
            super.doPush(p_82167_1_);
        }
    }


    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isInvulnerableTo(p_70097_1_)) {
            return false;
        } else {
            this.setOrderedToSit(false);
            return super.hurt(p_70097_1_, p_70097_2_);
        }
    }


    public int getVariant() {
        return MathHelper.clamp(this.entityData.get(DATA_VARIANT_ID), 1, determineVariant());
    }

    public void setVariant(int p_191997_1_) {
        this.entityData.set(DATA_VARIANT_ID, p_191997_1_);
    }

    public int getGender() {
        return MathHelper.clamp(this.entityData.get(GENDER), 0, 2);
    }

    public void setGender(int p_191997_1_) {
        this.entityData.set(GENDER, p_191997_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(GENDER, 0);
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("Sleeping", this.isSleeping());
        p_213281_1_.putInt("Variant", this.getVariant());
        p_213281_1_.putInt("Gender", this.getGender());
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setSleeping(p_70037_1_.getBoolean("Sleeping"));
        this.setVariant(p_70037_1_.getInt("Variant"));
        this.setGender(p_70037_1_.getInt("Gender"));
    }

    public boolean isSleeping() {
        return this.getFlag(32);
    }

    private void setSleeping(boolean p_213485_1_) {
        this.setFlag(32, p_213485_1_);
    }

    private void setFlag(int p_213505_1_, boolean p_213505_2_) {
        if (p_213505_2_) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) | p_213505_1_));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) & ~p_213505_1_));
        }

    }

    private boolean getFlag(int p_213507_1_) {
        return (this.entityData.get(DATA_FLAGS_ID) & p_213507_1_) != 0;
    }

    public boolean isFlying() {
        return !this.onGround;
    }

    public Set<Item> getTamedFood() {
        TAME_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
        return TAME_FOOD;
    }

    public boolean getTime() {
        return TameableBirdBase.this.level.isNight();
    }


    class SleepGoal extends Goal {
        public SleepGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        }

        public boolean canUse() {
            if (!TameableBirdBase.this.isInSittingPose() && TameableBirdBase.this.isTame() && CreaturesConfig.following.get() == true) {
                return false;
            }
            if (TameableBirdBase.this.xxa == 0.0F && TameableBirdBase.this.yya == 0.0F && TameableBirdBase.this.zza == 0.0F) {
                return this.canSleep() || TameableBirdBase.this.isSleeping();
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return this.canSleep();
        }

        private boolean canSleep() {
                return getTime() && !TameableBirdBase.this.isInWater() && !TameableBirdBase.this.isOnFire() && TameableBirdBase.this.isOnGround();
        }

        public void stop() {
            TameableBirdBase.this.setSleeping(false);
        }

        public void start() {
            TameableBirdBase.this.setSleeping(true);
            TameableBirdBase.this.getNavigation().stop();
            //TameableBirdBase.this.getMoveControl().setWantedPosition(TameableBirdBase.this.getX(), TameableBirdBase.this.getY(), TameableBirdBase.this.getZ(), 0.0D);
        }
    }

    public int methodofDeterminingVariant(IWorld p_213610_1_) {
        return this.random.nextInt(determineVariant());
    }

    public static boolean checkBirdSpawnRules(EntityType<? extends AnimalEntity> p_223317_0_, IWorld p_223317_1_, SpawnReason p_223317_2_, BlockPos p_223317_3_, Random p_223317_4_) {
        BlockState blockstate = p_223317_1_.getBlockState(p_223317_3_.below());
        return (blockstate.is(BlockTags.LEAVES) || blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(BlockTags.LOGS) || blockstate.is(Blocks.AIR)) && p_223317_1_.getRawBrightness(p_223317_3_, 0) > 8;
    }

    class FlyingGoal extends WaterAvoidingRandomFlyingGoal {
        public FlyingGoal() {
            super(TameableBirdBase.this, 1.0D);
        }

        public boolean canUse() {
            if (TameableBirdBase.this.isInSittingPose()) {
                return false;
            }
            if (TameableBirdBase.this.isBaby()) {
                return false;
            }
            if (TameableBirdBase.this.isSleeping()) {
                return false;
            }
            else {
                return super.canUse();
            }
        }
    }

    class BabyNoFlyNavigator extends GroundPathNavigator {
        public BabyNoFlyNavigator(MobEntity p_i47412_1_, World p_i47412_2_) {
            super(p_i47412_1_, p_i47412_2_);
        }

        protected PathFinder createPathFinder(int p_179679_1_) {
            if (!TameableBirdBase.this.isBaby()) {
            this.nodeEvaluator = new FlyingNodeProcessor();
            this.nodeEvaluator.setCanPassDoors(true);
            return new PathFinder(this.nodeEvaluator, p_179679_1_); }
            else {
                return super.createPathFinder(p_179679_1_);
            }
        }


        protected boolean canUpdatePath() {
            if (!TameableBirdBase.this.isBaby()) {
            return this.canFloat() && this.isInLiquid() || !this.mob.isPassenger(); }
            else {
                return this.mob.isOnGround() || this.isInLiquid() || this.mob.isPassenger();
            }
        }

        protected Vector3d getTempMobPos() {
            if (!TameableBirdBase.this.isBaby()) {
            return this.mob.position(); } else {
                return super.getTempMobPos();
            }
        }

        public Path createPath(Entity p_75494_1_, int p_75494_2_) {
            if (!TameableBirdBase.this.isBaby()) {
            return this.createPath(p_75494_1_.blockPosition(), p_75494_2_); }
            else {
                return super.createPath(p_75494_1_, p_75494_2_);
            }
        }

        public void tick() {
            if (!TameableBirdBase.this.isBaby()) {
                ++this.tick;
                if (this.hasDelayedRecomputation) {
                    this.recomputePath();
                }

                if (!this.isDone()) {
                    if (this.canUpdatePath()) {
                        this.followThePath();
                    } else if (this.path != null && !this.path.isDone()) {
                        Vector3d vector3d = this.path.getNextEntityPos(this.mob);
                        if (MathHelper.floor(this.mob.getX()) == MathHelper.floor(vector3d.x) && MathHelper.floor(this.mob.getY()) == MathHelper.floor(vector3d.y) && MathHelper.floor(this.mob.getZ()) == MathHelper.floor(vector3d.z)) {
                            this.path.advance();
                        }
                    }

                    DebugPacketSender.sendPathFindingPacket(this.level, this.mob, this.path, this.maxDistanceToWaypoint);
                    if (!this.isDone()) {
                        Vector3d vector3d1 = this.path.getNextEntityPos(this.mob);
                        this.mob.getMoveControl().setWantedPosition(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
                    }
                }
            } else {
                super.tick();
            }
        }

        protected boolean canMoveDirectly(Vector3d p_75493_1_, Vector3d p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_) {
            if (!TameableBirdBase.this.isBaby()) {
                int i = MathHelper.floor(p_75493_1_.x);
                int j = MathHelper.floor(p_75493_1_.y);
                int k = MathHelper.floor(p_75493_1_.z);
                double d0 = p_75493_2_.x - p_75493_1_.x;
                double d1 = p_75493_2_.y - p_75493_1_.y;
                double d2 = p_75493_2_.z - p_75493_1_.z;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < 1.0E-8D) {
                    return false;
                } else {
                    double d4 = 1.0D / Math.sqrt(d3);
                    d0 = d0 * d4;
                    d1 = d1 * d4;
                    d2 = d2 * d4;
                    double d5 = 1.0D / Math.abs(d0);
                    double d6 = 1.0D / Math.abs(d1);
                    double d7 = 1.0D / Math.abs(d2);
                    double d8 = (double) i - p_75493_1_.x;
                    double d9 = (double) j - p_75493_1_.y;
                    double d10 = (double) k - p_75493_1_.z;
                    if (d0 >= 0.0D) {
                        ++d8;
                    }

                    if (d1 >= 0.0D) {
                        ++d9;
                    }

                    if (d2 >= 0.0D) {
                        ++d10;
                    }

                    d8 = d8 / d0;
                    d9 = d9 / d1;
                    d10 = d10 / d2;
                    int l = d0 < 0.0D ? -1 : 1;
                    int i1 = d1 < 0.0D ? -1 : 1;
                    int j1 = d2 < 0.0D ? -1 : 1;
                    int k1 = MathHelper.floor(p_75493_2_.x);
                    int l1 = MathHelper.floor(p_75493_2_.y);
                    int i2 = MathHelper.floor(p_75493_2_.z);
                    int j2 = k1 - i;
                    int k2 = l1 - j;
                    int l2 = i2 - k;

                    while (j2 * l > 0 || k2 * i1 > 0 || l2 * j1 > 0) {
                        if (d8 < d10 && d8 <= d9) {
                            d8 += d5;
                            i += l;
                            j2 = k1 - i;
                        } else if (d9 < d8 && d9 <= d10) {
                            d9 += d6;
                            j += i1;
                            k2 = l1 - j;
                        } else {
                            d10 += d7;
                            k += j1;
                            l2 = i2 - k;
                        }
                    }

                    return true;
                }
            }
            else {
                return super.canMoveDirectly(p_75493_1_, p_75493_2_, p_75493_3_, p_75493_4_, p_75493_5_);
            }
        }

        public void setCanOpenDoors(boolean p_192879_1_) {
            this.nodeEvaluator.setCanOpenDoors(p_192879_1_);
        }

        public void setCanPassDoors(boolean p_192878_1_) {
            this.nodeEvaluator.setCanPassDoors(p_192878_1_);
        }

        public boolean isStableDestination(BlockPos p_188555_1_) {
            if (!TameableBirdBase.this.isBaby()) {
            return this.level.getBlockState(p_188555_1_).entityCanStandOn(this.level, p_188555_1_, this.mob);
            } else {
                return super.isStableDestination(p_188555_1_);
            }
        }
    }

    class FlyController extends MovementController {
        private final int maxTurn;
        private final boolean hoversInPlace;

        public FlyController(MobEntity p_i225710_1_, int p_i225710_2_, boolean p_i225710_3_) {
            super(p_i225710_1_);
            this.maxTurn = p_i225710_2_;
            this.hoversInPlace = p_i225710_3_;
        }

        public void tick() {
            if (!TameableBirdBase.this.isBaby()) {
                if (this.operation == MovementController.Action.MOVE_TO) {
                    this.operation = MovementController.Action.WAIT;
                    this.mob.setNoGravity(true);
                    double d0 = this.wantedX - this.mob.getX();
                    double d1 = this.wantedY - this.mob.getY();
                    double d2 = this.wantedZ - this.mob.getZ();
                    double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                    if (d3 < (double) 2.5000003E-7F) {
                        this.mob.setYya(0.0F);
                        this.mob.setZza(0.0F);
                        return;
                    }

                    float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                    this.mob.yRot = this.rotlerp(this.mob.yRot, f, 90.0F);
                    float f1;
                    if (this.mob.isOnGround()) {
                        f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    } else {
                        f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
                    }

                    this.mob.setSpeed(f1);
                    double d4 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
                    float f2 = (float) (-(MathHelper.atan2(d1, d4) * (double) (180F / (float) Math.PI)));
                    this.mob.xRot = this.rotlerp(this.mob.xRot, f2, (float) this.maxTurn);
                    this.mob.setYya(d1 > 0.0D ? f1 : -f1);
                } else {
                    if (!this.hoversInPlace) {
                        this.mob.setNoGravity(false);
                    }

                    this.mob.setYya(0.0F);
                    this.mob.setZza(0.0F);
                }

            }
            else {
                super.tick();
            }
        }
    }

    public String getGenderString() {
        if (this.getGender() == 1) {
            ITextComponent i = new TranslationTextComponent("gui.male");
            return i.getString();
        } else {
            ITextComponent i = new TranslationTextComponent("gui.female");
            return i.getString();
        }
    }

    public String getSpeciesName() {
        return this.getType().getDescription().getString();
    }

    public String getFoodName() {
        return "";
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.WHEAT_SEEDS, 1);
    }

}
