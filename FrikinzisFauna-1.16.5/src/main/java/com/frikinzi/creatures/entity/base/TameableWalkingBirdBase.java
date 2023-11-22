package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.CreaturesFollowGoal;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.entity.ai.StayCloseToEggGoal;
import com.frikinzi.creatures.entity.ai.StayCloseToMateGoal;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

public class TameableWalkingBirdBase extends CreaturesBirdEntity {
    private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(TameableWalkingBirdBase.class, DataSerializers.INT);
    private static final DataParameter<Integer> WANDERING = EntityDataManager.defineId(TameableWalkingBirdBase.class, DataSerializers.INT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.defineId(TameableWalkingBirdBase.class, DataSerializers.INT);
    public static Set<Item> TAME_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    private float flapping = 1.0F;
    public World world;

    public TameableWalkingBirdBase(EntityType<? extends TameableWalkingBirdBase> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathNodeType.COCOA, -1.0F);

    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        this.setVariant(methodofDeterminingVariant(p_213386_1_));
        this.setWandering(0);
        this.setGender(this.random.nextInt(2));
        if (p_213386_4_ == null) {
            p_213386_4_ = new AgeableData(false);
        }

        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }


    @Override
    protected void registerGoals() {
        if (this.isBaby()) {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
            }
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.targetSelector.addGoal(1, (new CreaturesBirdEntity.HurtByTargetGoal()));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(2, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(1, new SitGoal(this));
        this.goalSelector.addGoal(6, new CreaturesFollowGoal(this,1.0D, 5.0F, 1.0F, true));
        this.goalSelector.addGoal(0, new TameableWalkingBirdBase.SleepGoal());
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new StayCloseToEggGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new StayCloseToMateGoal(this, 1.0D));
    }

    public int determineVariant() {
        return 1;
    }

    public void aiStep() {
        super.aiStep();
        Vector3d vector3d = this.getDeltaMovement();
        if (!this.onGround && vector3d.y < 0.0D) {
            this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
        }
    }


    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE) {
            if (this.level.isClientSide) {
                Creatures.PROXY.setReferencedMob(this);
                Creatures.PROXY.openCreaturesGUI(itemstack);
                //return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
            return ActionResultType.SUCCESS;
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
        }

        else if (this.isTame() && itemstack.getItem() == Items.STICK) {
            if (this.getWandering() == 0) {
                if (this.level.isClientSide) {
                    ITextComponent i = new TranslationTextComponent("message.wander");
                    this.getOwner().sendMessage(i, Util.NIL_UUID);
                }
                this.setWandering(1);
            } else {
                if (this.level.isClientSide) {
                    ITextComponent i = new TranslationTextComponent("message.follow");
                    this.getOwner().sendMessage(i, Util.NIL_UUID);
                }
                this.setWandering(0);
            }
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }

        else if (this.isTame() && this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
            if (!p_230254_1_.abilities.instabuild) {
                itemstack.shrink(1);
            }

            this.heal((float) 2.0);
            return ActionResultType.SUCCESS;
        }
<<<<<<< Updated upstream
        else if (!getTamedFood().contains(itemstack.getItem())  && itemstack.getItem() != CreaturesItems.FF_GUIDE && this.isTame() && this.isOwnedBy(p_230254_1_)) {
=======
        else if (!getTamedFood().contains(itemstack.getItem()) && itemstack.getItem() != CreaturesItems.BIRD_CARRIER  && itemstack.getItem() != CreaturesItems.FF_GUIDE && this.isTame() && this.isOwnedBy(p_230254_1_)) {
>>>>>>> Stashed changes
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

    public int getWandering() {
        return MathHelper.clamp(this.entityData.get(WANDERING), 0, 2);
    }

    public void setWandering(int p_191997_1_) {
        this.entityData.set(WANDERING, p_191997_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(WANDERING, 0);
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("Sleeping", this.isSleeping());
        p_213281_1_.putInt("Variant", this.getVariant());
        p_213281_1_.putInt("Gender", this.getGender());
        p_213281_1_.putInt("Wandering", this.getWandering());
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setSleeping(p_70037_1_.getBoolean("Sleeping"));
        this.setVariant(p_70037_1_.getInt("Variant"));
        this.setGender(p_70037_1_.getInt("Gender"));
        this.setWandering(p_70037_1_.getInt("Wandering"));
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

    public Set<Item> getTamedFood() {
        TAME_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
        return TAME_FOOD;
    }

    public boolean getTime() {
        return TameableWalkingBirdBase.this.level.isNight();
    }


<<<<<<< Updated upstream
    class SleepGoal extends Goal {
=======
    public class SleepGoal extends Goal {
>>>>>>> Stashed changes
        public SleepGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public boolean canUse() {
            if (!TameableWalkingBirdBase.this.isInSittingPose() && TameableWalkingBirdBase.this.isTame() && CreaturesConfig.following.get() == true) {
                return false;
            }
            if (TameableWalkingBirdBase.this.xxa == 0.0F && TameableWalkingBirdBase.this.yya == 0.0F && TameableWalkingBirdBase.this.zza == 0.0F) {
                return this.canSleep() || TameableWalkingBirdBase.this.isSleeping();
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return this.canSleep();
        }

        private boolean canSleep() {
                return getTime() && !TameableWalkingBirdBase.this.isInWater() && !TameableWalkingBirdBase.this.isOnFire() && TameableWalkingBirdBase.this.isOnGround();
        }

        public void stop() {
            TameableWalkingBirdBase.this.setSleeping(false);
        }

        public void start() {
            TameableWalkingBirdBase.this.setSleeping(true);
            TameableWalkingBirdBase.this.getNavigation().stop();
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

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

}
