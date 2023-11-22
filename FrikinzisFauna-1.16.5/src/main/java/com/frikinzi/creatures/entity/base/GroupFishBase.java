package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.ModEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.FollowSchoolLeaderGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public abstract class GroupFishBase extends AbstractGroupFishEntity {
    public static DataParameter<Boolean> DATA_ID_MOVING = EntityDataManager.defineId(GroupFishBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Float> HEIGHT_MULTIPLIER = EntityDataManager.defineId(GroupFishBase.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> BRED = EntityDataManager.defineId(GroupFishBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> AGE = EntityDataManager.defineId(GroupFishBase.class, DataSerializers.INT);
    private static final DataParameter<Boolean> DATA_BABY_ID = EntityDataManager.defineId(GroupFishBase.class, DataSerializers.BOOLEAN);
    public int coolDown = 0;

    protected RandomWalkingGoal randomStrollGoal;

    public GroupFishBase(EntityType<? extends GroupFishBase> p_i48554_1_, World p_i48554_2_) {
        super(p_i48554_1_, p_i48554_2_);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
        this.moveControl = new GroupFishBase.MoveHelperController(this);
        this.setCanPickUpLoot(true);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        if (p_213386_5_ != null && p_213386_5_.contains("HeightMultiplier")) {
            this.setHeightMultiplier(p_213386_5_.getFloat("HeightMultiplier"));
            if (p_213386_5_.contains("Age")) {
                this.setAge(p_213386_5_.getInt("Age"));
            }
            return p_213386_4_;
        } else {

            float f = (float) (this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
            this.setHeightMultiplier(f);
        }


        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    public boolean isBaby() {
        return this.getAge() < 0;
    }

    public void setBaby(boolean p_82227_1_) {
        this.setAge(p_82227_1_ ? -24000 : 0);
    }


    protected void registerGoals() {
        this.randomStrollGoal = new RandomWalkingGoal(this, this.getMoveSpeed(), 20);
        this.goalSelector.addGoal(7, this.randomStrollGoal);
        this.randomStrollGoal.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.goalSelector.addGoal(5, new FollowSchoolLeaderGoal(this));
        this.goalSelector.addGoal(1, new GroupFishBase.EatFoodGoal());
    }

    protected PathNavigator createNavigation(World p_175447_1_) {
        return new SwimmerPathNavigator(this, p_175447_1_);
    }


    public boolean canBreatheUnderwater() {
        return true;
    }

    public CreatureAttribute getMobType() {
        return CreatureAttribute.WATER;
    }

    public boolean isMoving() {
        return this.entityData.get(DATA_ID_MOVING);
    }

    private void setMoving(boolean p_175476_1_) {
        this.entityData.set(DATA_ID_MOVING, p_175476_1_);
    }

    public float getWalkTargetValue(BlockPos p_205022_1_, IWorldReader p_205022_2_) {
        return p_205022_2_.getFluidState(p_205022_1_).is(FluidTags.WATER) ? 10.0F + p_205022_2_.getBrightness(p_205022_1_) - 0.5F : super.getWalkTargetValue(p_205022_1_, p_205022_2_);
    }

    public Item getFoodItem() {
        return Items.KELP;
    }

    public ItemStack getDisplayFood() {
        return new ItemStack(getFoodItem(), 1);
    }


    public void aiStep() {
        if (!this.level.isClientSide && this.coolDown > 0) {
            --this.coolDown;
        }
        if (this.isAlive()) {
            if (this.level.isClientSide) {

            }
            int i = this.getAge();
            if (i < 0) {
                ++i;
                this.setAge(i);
            } else if (i > 0) {
                --i;
                this.setAge(i);
            }

            if (this.isInWaterOrBubble()) {
                this.setAirSupply(300);
            }

        }

        super.aiStep();
    }


    public void travel(Vector3d p_213352_1_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, p_213352_1_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(p_213352_1_);
        }

    }


    static class MoveHelperController extends MovementController {
        private final GroupFishBase fishbase;

        public MoveHelperController(GroupFishBase p_i45831_1_) {
            super(p_i45831_1_);
            this.fishbase = p_i45831_1_;
        }

        public void tick() {
            if (this.operation == Action.MOVE_TO && !this.fishbase.getNavigation().isDone()) {
                Vector3d vector3d = new Vector3d(this.wantedX - this.fishbase.getX(), this.wantedY - this.fishbase.getY(), this.wantedZ - this.fishbase.getZ());
                double d0 = vector3d.length();
                double d1 = vector3d.x / d0;
                double d2 = vector3d.y / d0;
                double d3 = vector3d.z / d0;
                float f = (float) (MathHelper.atan2(vector3d.z, vector3d.x) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.fishbase.yRot = this.rotlerp(this.fishbase.yRot, f, 90.0F);
                this.fishbase.yBodyRot = this.fishbase.yRot;
                float f1 = (float) (this.speedModifier * this.fishbase.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float f2 = MathHelper.lerp(0.125F, this.fishbase.getSpeed(), f1);
                this.fishbase.setSpeed(f2);
                double d4 = Math.sin((double) (this.fishbase.tickCount + this.fishbase.getId()) * 0.5D) * 0.05D;
                double d5 = Math.cos((double) (this.fishbase.yRot * ((float) Math.PI / 180F)));
                double d6 = Math.sin((double) (this.fishbase.yRot * ((float) Math.PI / 180F)));
                double d7 = Math.sin((double) (this.fishbase.tickCount + this.fishbase.getId()) * 0.75D) * 0.05D;
                this.fishbase.setDeltaMovement(this.fishbase.getDeltaMovement().add(0, d7 * (d6 + d5) * 0.25D + (double)f2 * d2 * 0.1D, 0));
                LookController lookcontroller = this.fishbase.getLookControl();
                double d8 = this.fishbase.getX() + d1 * 2.0D;
                double d9 = this.fishbase.getEyeY() + d2 / d0;
                double d10 = this.fishbase.getZ() + d3 * 2.0D;
                double d11 = lookcontroller.getWantedX();
                double d12 = lookcontroller.getWantedY();
                double d13 = lookcontroller.getWantedZ();
                if (!lookcontroller.isHasWanted()) {
                    d11 = d8;
                    d12 = d9;
                    d13 = d10;
                }

                //this.fishbase.getLookControl().setLookAt(MathHelper.lerp(0.125D, d11, d8), MathHelper.lerp(0.125D, d12, d9), MathHelper.lerp(0.125D, d13, d10), 10.0F, 40.0F);
                this.fishbase.setMoving(true);
            } else {
                this.fishbase.setSpeed(0.0F);
                this.fishbase.setMoving(false);
            }
        }
    }

    public static boolean checkFishSpawnRules(EntityType<? extends AbstractFishEntity> p_223363_0_, IWorld p_223363_1_, SpawnReason p_223363_2_, BlockPos p_223363_3_, Random p_223363_4_) {
        return p_223363_1_.getBlockState(p_223363_3_).is(Blocks.WATER) && p_223363_1_.getBlockState(p_223363_3_.above()).is(Blocks.WATER);
    }

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (!this.level.isClientSide()) {
            if (itemstack.getItem() == getFoodItem() && this.isAlive() && this.coolDown <= 0 && !this.isBaby()) {
                EntityPredicate predicate = (new EntityPredicate()).range(16.0D).allowInvulnerable().selector((p_220844_0_) -> {
                    return ModEntityTypes.getIntFromFishEntity(((GroupFishBase)p_220844_0_)) == ModEntityTypes.getIntFromFishEntity(this);
                });
                List<GroupFishBase> list = this.level.getNearbyEntities(GroupFishBase.class, predicate, this, this.getBoundingBox().inflate(10.0D, 10.0D, 10.0D));
                if (!list.isEmpty() ) {
                    // if none of the fish in the vicinity have cooled down, can't breed
                    boolean canbreed = false;
                    int index = 0;
                    for (int lol = 0; lol < list.size(); lol++) {
                        if (list.get(lol).coolDown <= 0) {
                            canbreed = true;
                            break;
                        }
                        index += 1;
                    }
                    if (canbreed) {
                        this.layEgg((ServerWorld)this.level, list.get(index));
                        if (!p_230254_1_.abilities.instabuild) {
                            itemstack.shrink(1);
                        }
                        this.coolDown = this.random.nextInt(6000) + 6000;
                        if (list.get(0) != null) {
                            list.get(0).coolDown = this.random.nextInt(6000) + 6000;
                        }
                    }
                }

                return ActionResultType.sidedSuccess(this.level.isClientSide);
            }
        }
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

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
        this.entityData.define(BRED, false);
        this.entityData.define(AGE, 0);
        this.entityData.define(DATA_BABY_ID, false);
    }

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        if (DATA_BABY_ID.equals(p_184206_1_)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(p_184206_1_);
    }

    public int getAge() {
        return this.entityData.get(AGE);
    }

    public void setAge(int i) {
        this.entityData.set(AGE, i);
    }

    private boolean wasBred() {
        return this.entityData.get(BRED);
    }

    public void setBred(boolean p_203706_1_) {
        this.entityData.set(BRED, p_203706_1_);
    }

    public float getHeightMultiplier() {
        return this.entityData.get(HEIGHT_MULTIPLIER);
    }

    public void setHeightMultiplier(float p_70606_1_) {
        if (this.getHeightMultiplier() < 0.7F) {
            this.entityData.set(HEIGHT_MULTIPLIER, 1.0F);
        } else {
            this.entityData.set(HEIGHT_MULTIPLIER, MathHelper.clamp(p_70606_1_, 0.7F, 1.5F)); }
    }

    public String getHeightString() {
        if (this.getHeightMultiplier() >= 1.5) {
            ITextComponent i = new TranslationTextComponent("gui.giant");
            return i.getString();
        }
        if (this.getHeightMultiplier() >= 1.4) {
            ITextComponent i = new TranslationTextComponent("gui.huge");
            return i.getString();
        }
        if (this.getHeightMultiplier() >= 1.21) {
            ITextComponent i = new TranslationTextComponent("gui.large");
            return i.getString();
        } if (this.getHeightMultiplier() < 1.21 && this.getHeightMultiplier() > 1.11) {
            ITextComponent i = new TranslationTextComponent("gui.above_average");
            return i.getString();
        }
        if (this.getHeightMultiplier() <= 1.11 && this.getHeightMultiplier() >= 0.89) {
            ITextComponent i = new TranslationTextComponent("gui.average");
            return i.getString();
        }
        if (this.getHeightMultiplier() < 0.89 && this.getHeightMultiplier() >= 0.79) {
            ITextComponent i = new TranslationTextComponent("gui.below_average");
            return i.getString();
        }
        else {
            ITextComponent i = new TranslationTextComponent("gui.small");
            return i.getString();
        }
    }

    protected void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundNBT compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putFloat("HeightMultiplier", this.getHeightMultiplier());
        compoundnbt.putInt("Age", this.getAge());
    }


    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        p_213281_1_.putFloat("HeightMultiplier", this.getHeightMultiplier());
        p_213281_1_.putBoolean("Bred", this.wasBred());
        p_213281_1_.putInt("Age", this.getAge());
        super.addAdditionalSaveData(p_213281_1_);
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setBred(p_70037_1_.getBoolean("Bred"));
        this.setAge(p_70037_1_.getInt("Age"));
        if (!p_70037_1_.contains("HeightMultiplier") || this.getHeightMultiplier() < 0.7F || this.getHeightMultiplier() > 1.5F) {
            this.setHeightMultiplier((float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get()));
        } else {
            this.setHeightMultiplier(p_70037_1_.getFloat("HeightMultiplier")); }
    }

    public float getHatchChance() {
        return 1;
    }


    public int getVariant() {
        return 1;
    }

    public CreaturesRoeEntity layEgg(GroupFishBase animal) {
        CreaturesRoeEntity egg = new CreaturesRoeEntity(ModEntityTypes.ROE.get(), this.level);
        egg.setSpecies(ModEntityTypes.getIntFromFishEntity(animal));
        egg.setGender(this.random.nextInt(2));
        egg.setVariant(this.getVariant());
        egg.setPos(MathHelper.floor(this.getX()) + 0.5, MathHelper.floor(this.getY()) + 0.5, MathHelper.floor(this.getZ()) + 0.5);
        return egg;
    }



    protected void layEgg(ServerWorld server, GroupFishBase father) {
        int c = 10;
        for (int j = 0; j <= c; j++) {
            CreaturesRoeEntity egg = this.layEgg(this);
            if (egg != null) {
                GroupFishBase mother;
                mother = this;

                egg.setParentUUID(mother.getUUID());

                float f = (float)(this.getRandom().nextGaussian() * 0.05 + ((this.getHeightMultiplier())));
                egg.setHeightMultiplier(f);
                int[] vars = {this.getVariant(), father.getVariant()};
                int rnd = new Random().nextInt(vars.length);
                egg.setVariant(vars[rnd]);

                Random rand = new Random();
                egg.setPos(MathHelper.floor(mother.getX()) + 0.5 + (-1+rand.nextFloat()), MathHelper.floor(mother.getY()) + 0.5, MathHelper.floor(mother.getZ()) + 0.5 + (-1+rand.nextFloat()));
                server.addFreshEntityWithPassengers(egg);
                //System.out.println(this.bird.getClutchSize());
            }
            server.broadcastEntityEvent(this, (byte)18);
        }
        this.setBred(true);
        Random random = this.getRandom();
        for (int i = 0; i < 17; ++i) {
            final double d0 = random.nextGaussian() * 0.02D;
            final double d1 = random.nextGaussian() * 0.02D;
            final double d2 = random.nextGaussian() * 0.02D;
            final double d3 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            final double d4 = 0.5D + random.nextDouble() * this.getBbHeight();
            final double d5 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            this.level.addParticle(ParticleTypes.HEART, this.getX() + d3, this.getY() + d4, this.getZ() + d5, d0, d1, d2);
        }
        if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            server.addFreshEntity(new ExperienceOrbEntity(server, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
        }
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.wasBred();
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return super.removeWhenFarAway(p_213397_1_) && !this.wasBred();
    }

    public double getMoveSpeed() {
        return 1.0D;
    }


    class EatFoodGoal extends Goal {
        private int cooldown;
        public final Predicate<ItemEntity> CAN_EAT = (p_205023_0_) -> {
            return p_205023_0_.getItem().getItem() == GroupFishBase.this.getFoodItem() && p_205023_0_.isAlive() && p_205023_0_.isInWater();
        };

        private EatFoodGoal() {
        }

        public boolean canUse() {
            if (this.cooldown > GroupFishBase.this.tickCount) {
                return false;
            } else {
                List<ItemEntity> list = GroupFishBase.this.level.getEntitiesOfClass(ItemEntity.class, GroupFishBase.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CAN_EAT);
                return !list.isEmpty() || !GroupFishBase.this.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty();
            }
        }

        public void start() {
            List<ItemEntity> list = GroupFishBase.this.level.getEntitiesOfClass(ItemEntity.class, GroupFishBase.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CAN_EAT);
            if (!list.isEmpty()) {
                GroupFishBase.this.getNavigation().moveTo(list.get(0), (double)1.2F);
                //FishBase.this.playSound(SoundEvents.DOLPHIN_PLAY, 1.0F, 1.0F);
            }

            this.cooldown = 0;
        }

        public void stop() {
            ItemStack itemstack = GroupFishBase.this.getItemBySlot(EquipmentSlotType.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.eat(itemstack);
                GroupFishBase.this.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
                this.cooldown = GroupFishBase.this.tickCount + GroupFishBase.this.random.nextInt(100);
            }

        }

        public void tick() {
            List<ItemEntity> list = GroupFishBase.this.level.getEntitiesOfClass(ItemEntity.class, GroupFishBase.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CAN_EAT);
            ItemStack itemstack = GroupFishBase.this.getItemBySlot(EquipmentSlotType.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.eat(itemstack);
                GroupFishBase.this.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
            } else if (!list.isEmpty()) {
                GroupFishBase.this.getNavigation().moveTo(list.get(0), (double)1.2F);
                if (!GroupFishBase.this.level.isClientSide && GroupFishBase.this.isAlive() && !GroupFishBase.this.dead) {
                    for(ItemEntity itementity : GroupFishBase.this.level.getEntitiesOfClass(ItemEntity.class, GroupFishBase.this.getBoundingBox().inflate(1.0D, 0.0D, 1.0D), CAN_EAT)) {
                        if (!itementity.removed && !itementity.getItem().isEmpty() && !itementity.hasPickUpDelay() && itementity.getItem().getItem() == GroupFishBase.this.getFoodItem()) {
                            GroupFishBase.this.pickUpItem(itementity);
                        }
                    }
                }
            }

        }

        private void eat(ItemStack p_220810_1_) {
            if (!p_220810_1_.isEmpty()) {
                GroupFishBase.this.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
                EntityPredicate predicate = (new EntityPredicate()).range(16.0D).allowInvulnerable().selector((p_220844_0_) -> {
                    return ModEntityTypes.getIntFromFishEntity(((GroupFishBase)p_220844_0_)) == ModEntityTypes.getIntFromFishEntity(GroupFishBase.this) && !((GroupFishBase)p_220844_0_).isBaby();
                });
                List<GroupFishBase> list = GroupFishBase.this.level.getNearbyEntities(GroupFishBase.class, predicate, GroupFishBase.this, GroupFishBase.this.getBoundingBox().inflate(10.0D, 10.0D, 10.0D));
                if (!list.isEmpty() ) {
                    boolean canbreed = false;
                    int index = 0;
                    for (int lol = 0; lol < list.size(); lol++) {
                        if (list.get(lol).coolDown <= 0) {
                            canbreed = true;
                            break;
                        }
                        index += 1;
                    }
                    if (canbreed && !GroupFishBase.this.isBaby()) {
                        GroupFishBase.this.layEgg((ServerWorld)GroupFishBase.this.level, list.get(index));
                        GroupFishBase.this.coolDown = GroupFishBase.this.random.nextInt(6000) + 6000;
                        if (list.get(0) != null) {
                            list.get(0).coolDown = GroupFishBase.this.random.nextInt(6000) + 6000;
                        }
                    }
                }
                GroupFishBase.this.heal((float)GroupFishBase.this.getMaxHealth());
                if (GroupFishBase.this.isBaby()) {
                    int age = GroupFishBase.this.getAge();
                    age += (int)(float)(24000 / 20.0);
                    GroupFishBase.this.setAge(age);
                }
            }
        }
    }

    public boolean canTakeItem(ItemStack p_213365_1_) {
        EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(p_213365_1_);
        if (p_213365_1_.getItem() != this.getFoodItem()) {
            return false;
        }
        if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
            return false;
        } else {
            return equipmentslottype == EquipmentSlotType.MAINHAND && super.canTakeItem(p_213365_1_);
        }
    }

    public boolean canHoldItem(ItemStack p_175448_1_) {
        if (p_175448_1_.getItem() != this.getFoodItem()) {
            return false;
        }
        return true;
    }

    protected void pickUpItem(ItemEntity p_175445_1_) {
        if (this.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty()) {
            ItemStack itemstack = p_175445_1_.getItem();
            if (this.canHoldItem(itemstack)) {
                this.onItemPickup(p_175445_1_);
                this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
                this.handDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 2.0F;
                this.take(p_175445_1_, itemstack.getCount());
                p_175445_1_.remove();
            }
        }

    }

<<<<<<< Updated upstream
=======
    public void setVariant(int i) {

    }

    public int determineVariant()
    {
        return 1;
    }
>>>>>>> Stashed changes

}
