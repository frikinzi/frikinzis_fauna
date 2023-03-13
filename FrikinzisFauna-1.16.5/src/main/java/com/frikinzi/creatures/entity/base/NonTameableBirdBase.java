package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.entity.ai.StayCloseToEggGoal;
import com.frikinzi.creatures.entity.ai.StayCloseToMateGoal;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Set;

public class NonTameableBirdBase extends CreaturesBirdEntity {
    private static final DataParameter<Integer> DATA_TYPE_ID = EntityDataManager.defineId(NonTameableBirdBase.class, DataSerializers.INT);
    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(NonTameableBirdBase.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(NonTameableBirdBase.class, DataSerializers.INT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.defineId(NonTameableBirdBase.class, DataSerializers.INT);
    public static Set<Item> BREED_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    private float flapping = 1.0F;

    public NonTameableBirdBase(EntityType<? extends NonTameableBirdBase> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, -1.0F);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        this.setVariant(this.random.nextInt(determineVariant()));
        this.setGender(this.random.nextInt(2));
        if (p_213386_4_ == null) {
            p_213386_4_ = new AgeableData(false);
        }

        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        //this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new NonTameableBirdBase.SleepGoal());
        this.goalSelector.addGoal(1, new StayCloseToEggGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new StayCloseToMateGoal(this, 1.0D));
    }

    public int determineVariant() {
        return 1;
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

    @Override
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
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
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

    public void aiStep() {
        super.aiStep();
        Vector3d vector3d = this.getDeltaMovement();
        if (!this.onGround && vector3d.y < 0.0D) {
            this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
        }
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

    public Set<Item> getBreedFood() {
        BREED_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
        return BREED_FOOD;
    }


    class SleepGoal extends Goal {
        public SleepGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public boolean canUse() {
            if (NonTameableBirdBase.this.xxa == 0.0F && NonTameableBirdBase.this.yya == 0.0F && NonTameableBirdBase.this.zza == 0.0F) {
                return this.canSleep() || NonTameableBirdBase.this.isSleeping();
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return this.canSleep();
        }

        private boolean canSleep() {
                return NonTameableBirdBase.this.level.isNight() && !NonTameableBirdBase.this.isInWater() && !NonTameableBirdBase.this.isOnFire() && NonTameableBirdBase.this.isOnGround();
        }

        public void stop() {
            NonTameableBirdBase.this.setSleeping(false);
        }

        public void start() {
            NonTameableBirdBase.this.setSleeping(true);
            NonTameableBirdBase.this.getNavigation().stop();
            //TameableBirdBase.this.getMoveControl().setWantedPosition(TameableBirdBase.this.getX(), TameableBirdBase.this.getY(), TameableBirdBase.this.getZ(), 0.0D);
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

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE) {
            if (this.level.isClientSide) {
                Creatures.PROXY.setReferencedMob(this);
                Creatures.PROXY.openCreaturesGUI(itemstack);
                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
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
