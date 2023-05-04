package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.util.ModEventSubscriber;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesLootTables;
import com.google.common.collect.Sets;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Stream;

abstract public class CreaturesBirdEntity extends TamableAnimal {
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(CreaturesBirdEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(CreaturesBirdEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> WANDERING = SynchedEntityData.defineId(CreaturesBirdEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> HEIGHT_MULTIPLIER = SynchedEntityData.defineId(CreaturesBirdEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> GROOMING = SynchedEntityData.defineId(CreaturesBirdEntity.class, EntityDataSerializers.BOOLEAN);

    @Nullable
    private CreaturesBirdEntity leader;
    private int schoolSize = 1;

    private static final Set<Item> TAME_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(CreaturesBirdEntity.class, EntityDataSerializers.BYTE);
    public int sitProgress;
    public int ticksToSit;

    public CreaturesBirdEntity(EntityType<? extends CreaturesBirdEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);
        this.lookControl = new CreaturesBirdEntity.BirdLookControl();
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
        this.ticksToSit = 40;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_29389_, DifficultyInstance p_29390_, MobSpawnType p_29391_, @Nullable SpawnGroupData p_29392_, @Nullable CompoundTag p_29393_) {
        this.setVariant(this.determineVariant());
        this.setGender(this.random.nextInt(2));
        if (p_29392_ == null) {
            p_29392_ = new AgeableMob.AgeableMobGroupData(false);
        }

        float f = (float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
        this.setHeightMultiplier(f);

        return super.finalizeSpawn(p_29389_, p_29390_, p_29391_, p_29392_, p_29393_);
    }

    public int noVariants() {
        return 2;
    }

    public int determineVariant() {
        return this.random.nextInt(this.noVariants()) + 1;
    }

    protected float getStandingEyeHeight(Pose p_29411_, EntityDimensions p_29412_) {
        return p_29412_.height * 0.6F;
    }

    public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
        return false;
    }

    protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!(p_30392_.getClass() == this.getClass())) {
            return false;
        } else {
            return this.isInLove() && p_30392_.isInLove();
        }
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public CreaturesBirdEntity startFollowing(CreaturesBirdEntity p_27526_) {
        this.leader = p_27526_;
        p_27526_.addFollower();
        return p_27526_;
    }


    public void stopFollowing() {
        this.leader.removeFollower();
        this.leader = null;
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    public void tick() {
        super.tick();
        if (this.hasFollowers() && this.level.random.nextInt(200) == 1) {
            List<? extends CreaturesBirdEntity> list = this.level.getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.schoolSize = 1;
            }
        }

    }


    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.0D);
        }

    }

    public void addFollowers(Stream<? extends CreaturesBirdEntity> p_27534_) {
        p_27534_.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p_27538_) -> {
            return p_27538_ != this;
        }).forEach((p_27536_) -> {
            p_27536_.startFollowing(this);
        });
    }

    public int getMaxSchoolSize() {
        return super.getMaxSpawnClusterSize();
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel p_148993_, AgeableMob p_148994_) {
        return null;
    }

    protected void playStepSound(BlockPos p_29419_, BlockState p_29420_) {
        this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
    }

    public boolean isPushable() {
        return true;
    }

    protected void doPush(Entity p_29367_) {
        if (!(p_29367_ instanceof Player)) {
            super.doPush(p_29367_);
        }
    }

    public String getGenderString() {
        if (this.getGender() == 1) {
            return "m";
        } return "f";
    }

    public String getGenderDisplayName() {
        if (this.getGender() == 1) {
            Component i = new TranslatableComponent("gui.male");
            return i.getString();
        } else {
            Component i = new TranslatableComponent("gui.female");
            return i.getString();
        }
    }


    public boolean hurt(DamageSource p_29378_, float p_29379_) {
        if (this.isGrooming()) {
            this.setGrooming(false);
        }
        if (this.isSleeping()) {
            this.setSleeping(false);
        }
        if (this.isInvulnerableTo(p_29378_)) {
            return false;
        } else {
            if (!this.level.isClientSide) {
                this.setOrderedToSit(false);
            }

            return super.hurt(p_29378_, p_29379_);
        }
    }

    public int getVariant() {
        return Mth.clamp(this.entityData.get(DATA_VARIANT_ID), 1, this.noVariants());
    }

    public void setVariant(int p_29449_) {
        this.entityData.set(DATA_VARIANT_ID, p_29449_);
    }

    public int getGender() {
        return Mth.clamp(this.entityData.get(GENDER), 0, 2);
    }

    public void setGender(int p_29449_) {
        this.entityData.set(GENDER, p_29449_);
    }

    public int isWandering() {
        return Mth.clamp(this.entityData.get(WANDERING), 0, 2);
    }

    public void setWandering(int p_29449_) {
        this.entityData.set(WANDERING, p_29449_);
    }

    public float getHeightMultiplier() {
        return this.entityData.get(HEIGHT_MULTIPLIER);
    }

    public void setHeightMultiplier(float p_70606_1_) {
        this.entityData.set(HEIGHT_MULTIPLIER, Mth.clamp(p_70606_1_, 0.7F, 1.5F));
    }

    public String getHeightString() {
        if (this.getHeightMultiplier() >= 1.5) {
            Component i = new TranslatableComponent("gui.giant");
            return i.getString();
        }
        if (this.getHeightMultiplier() >= 1.4) {
            Component i = new TranslatableComponent("gui.huge");
            return i.getString();
        }
        if (this.getHeightMultiplier() >= 1.21) {
            Component i = new TranslatableComponent("gui.large");
            return i.getString();
        } if (this.getHeightMultiplier() < 1.21 && this.getHeightMultiplier() > 1.11) {
            Component i = new TranslatableComponent("gui.above_average");
            return i.getString();
        }
        if (this.getHeightMultiplier() <= 1.11 && this.getHeightMultiplier() >= 0.89) {
            Component i = new TranslatableComponent("gui.average");
            return i.getString();
        }
        if (this.getHeightMultiplier() < 0.89 && this.getHeightMultiplier() >= 0.79) {
            Component i = new TranslatableComponent("gui.below_average");
            return i.getString();
        }
        else {
            TranslatableComponent i = new TranslatableComponent("gui.small");
            return i.getString();
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(WANDERING, 0);
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(GROOMING, false);
    }

    public void addAdditionalSaveData(CompoundTag p_29422_) {
        super.addAdditionalSaveData(p_29422_);
        p_29422_.putBoolean("Sleeping", this.isSleeping());
        p_29422_.putBoolean("Grooming", this.isGrooming());
        p_29422_.putInt("Variant", this.getVariant());
        p_29422_.putFloat("HeightMultiplier", this.getHeightMultiplier());
        p_29422_.putInt("Gender", this.getGender());
        p_29422_.putInt("Wandering", this.isWandering());
    }

    public void readAdditionalSaveData(CompoundTag p_29402_) {
        super.readAdditionalSaveData(p_29402_);
        if (!p_29402_.contains("HeightMultiplier") || this.getHeightMultiplier() < 0.7F || this.getHeightMultiplier() > 1.5F) {
            this.setHeightMultiplier((float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get()));
        } else {
            this.setHeightMultiplier(p_29402_.getFloat("HeightMultiplier"));
        }
        this.setGrooming(p_29402_.getBoolean("Grooming"));
        this.setSleeping(p_29402_.getBoolean("Sleeping"));
        this.setVariant(p_29402_.getInt("Variant"));
        this.setGender(p_29402_.getInt("Gender"));
        this.setWandering(p_29402_.getInt("Wandering"));
    }

    public void setGrooming(boolean grooming) {
        this.entityData.set(GROOMING, grooming);
    }

    public boolean isGrooming() {
        return (this.entityData.get(GROOMING));
    }

    public boolean isNotMoving(){
        return this.getDeltaMovement().x == 0 && this.getDeltaMovement().z == 0;
    }

    public void aiStep() {
        if (!this.level.isClientSide) {
            if (!this.getNavigation().isDone() && (this.isGrooming() || this.isSleeping())) {
                this.setGrooming(false);
                this.setSleeping(false);
            }
        }
        if (this.isGrooming() && this.sitProgress < this.ticksToSit) {
            this.sitProgress++;
        } else if (!this.isGrooming() && this.sitProgress > 0) {
            this.sitProgress--;
        }
        super.aiStep();

    }



    public boolean isSleeping() {
        return this.getFlag(32);
    }

    void setSleeping(boolean p_28627_) {
        this.setFlag(32, p_28627_);
    }

    private void setFlag(int p_28533_, boolean p_28534_) {
        if (p_28534_) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) | p_28533_));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) & ~p_28533_));
        }

    }

    private boolean getFlag(int p_28609_) {
        return (this.entityData.get(DATA_FLAGS_ID) & p_28609_) != 0;
    }

    void wakeUp() {
        this.setSleeping(false);
    }

    void clearStates() {
        this.setSleeping(false);
    }

    protected boolean canMove() {
        return !this.isSleeping() && !this.isGrooming();
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS);
    }

    public boolean isFood(ItemStack p_28177_) {
        return this.getBirdFood().test(p_28177_);
    }

    public float getSpawnEggOffspringHeight() {
        return (float)(this.getRandom().nextGaussian() * 0.05 + this.getHeightMultiplier());
    }

    public class BirdLookControl extends LookControl {
        public BirdLookControl() {
            super(CreaturesBirdEntity.this);
        }

        public void tick() {
            if (!CreaturesBirdEntity.this.isSleeping()) {
                super.tick();
            }

        }
    }

    public class SleepGoal extends Goal {
        //private static final int WAIT_TIME_BEFORE_SLEEP = reducedTickDelay(140);
        //private int countdown = CreaturesBirdEntity.this.random.nextInt(WAIT_TIME_BEFORE_SLEEP);

        public SleepGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        }

        public boolean canUse() {
            if (CreaturesBirdEntity.this instanceof RaptorEntity) {
                RaptorEntity raptor = (RaptorEntity)CreaturesBirdEntity.this;
                if (raptor.isHunting() == 1) {
                    return false;
                }
                if (CreaturesBirdEntity.this.isTame() && CreaturesBirdEntity.this.isWandering() == 0) {
                    return false;
                }
            }
            if (CreaturesBirdEntity.this.xxa == 0.0F && CreaturesBirdEntity.this.yya == 0.0F && CreaturesBirdEntity.this.zza == 0.0F) {
                return this.canSleep() || CreaturesBirdEntity.this.isSleeping();
            } return false;
        }

        public boolean canContinueToUse() {
            return this.canSleep();
        }

        private boolean canSleep() {
            return CreaturesBirdEntity.this.timeSleep() && CreaturesBirdEntity.this.isOnGround() && !CreaturesBirdEntity.this.isInWaterOrRain() && !CreaturesBirdEntity.this.isInPowderSnow;
        }

        public void stop() {
            CreaturesBirdEntity.this.clearStates();
        }

        public void start() {
            CreaturesBirdEntity.this.setSleeping(true);
            CreaturesBirdEntity.this.getNavigation().stop();
            //CreaturesBirdEntity.this.getMoveControl().setWantedPosition(CreaturesBirdEntity.this.getX(), CreaturesBirdEntity.this.getY(), CreaturesBirdEntity.this.getZ(), 0.0D);
        }
    }

    public boolean timeSleep() {
        return this.level.isNight();
    }

    public double getHatchChance() {
        return 1.0;
    }

    public int getClutchSize() {
        return 5;
    }

    public EggEntity layEgg(CreaturesBirdEntity animal) {
        EggEntity egg = new EggEntity(CreaturesEntities.EGG.get(), this.level);
        egg.setSpecies(ModEventSubscriber.getBirdEntityMap().inverse().get(animal.getType()));
        int[] variants = {this.getVariant(), animal.getVariant()};
        int rand = this.random.nextInt(2);
        egg.setGender(this.random.nextInt(2));
        egg.setVariant(variants[rand]);
        egg.setPos(Mth.floor(this.getX()) + 0.5, Mth.floor(this.getY()) + 0.5, Mth.floor(this.getZ()) + 0.5);
        return egg;
    }

    public String getSpeciesName() {
        return this.getType().getDescription().getString();
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.WHEAT_SEEDS, 1);
    }

    public InteractionResult mobInteract(Player p_29414_, InteractionHand p_29415_) {
        ItemStack itemstack = p_29414_.getItemInHand(p_29415_);
        if (itemstack.getItem() == Items.STICK && this.isTame() && this.getOwner() == p_29414_) {
            if (this.isWandering() == 0) {
                if (this.level.isClientSide) {
                    Component i = new TranslatableComponent("message.wander");
                    this.getOwner().sendMessage(i, Util.NIL_UUID);
                }
                this.setWandering(1);
            } else {
                if (this.level.isClientSide) {
                    Component i = new TranslatableComponent("message.follow");
                    this.getOwner().sendMessage(i, Util.NIL_UUID);
                }
                this.setWandering(0);
            }
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE.get()) {
            Creatures.PROXY.setReferencedMob(this);
            if (this.level.isClientSide) {
                Creatures.PROXY.openCreaturesGui();
            }
            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(p_29414_, p_29415_);
        }
    }

    class FollowGoal extends FollowOwnerGoal {
        public FollowGoal() {
            super(CreaturesBirdEntity.this,1.0D, 10.0F, 2.0F, false);
        }

        public boolean canUse() {
            if (CreaturesBirdEntity.this.isWandering() == 1) {
                return false;
            } return super.canUse();
        }

        public boolean canContinueToUse() {
            if (CreaturesBirdEntity.this.isWandering() == 1) {
                return false;
            } return super.canUse();
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    public int getAmbientSoundInterval() {
        return 180;
    }


}
