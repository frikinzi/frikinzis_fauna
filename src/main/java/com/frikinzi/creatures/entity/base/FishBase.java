package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public abstract class FishBase extends AbstractSchoolingFish {
    public static final EntityDataAccessor<Boolean> DATA_ID_MOVING = SynchedEntityData.defineId(FishBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> HEIGHT_MULTIPLIER = SynchedEntityData.defineId(FishBase.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(FishBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> BRED = SynchedEntityData.defineId(FishBase.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(FishBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SUBVARIANT = SynchedEntityData.defineId(FishBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(FishBase.class, EntityDataSerializers.INT);

    public int coolDown = 0;

    protected RandomStrollGoal randomStrollGoal;

    public FishBase(EntityType<? extends FishBase> p_i48554_1_, Level p_i48554_2_) {
        super(p_i48554_1_, p_i48554_2_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new MoveHelperController(this);
        this.setCanPickUpLoot(true);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_213386_1_, DifficultyInstance p_213386_2_, MobSpawnType p_213386_3_, @Nullable SpawnGroupData p_213386_4_, @Nullable CompoundTag p_213386_5_) {
        this.setGender(this.random.nextInt(2));
        if (p_213386_3_ == MobSpawnType.SPAWN_EGG) { //spawn egg variants should be completely random, not based on biome or anything
            this.setVariant(this.random.nextInt(numVariants()) + 1);
            this.setSubVariant(this.methodOfDeterminingSubVariant());
        }  else if (p_213386_3_ == MobSpawnType.BUCKET) {
            if (p_213386_5_ != null) {
                if (p_213386_5_.contains("BucketVariantTag", 3)) {
                    this.setVariant(p_213386_5_.getInt("BucketVariantTag"));
                    //return p_213386_4_;
                }
                if (p_213386_5_.contains("BucketHeightMultiplier")) {
                    this.setHeightMultiplier(p_213386_5_.getFloat("BucketHeightMultiplier"));
                } if (p_213386_5_.contains("Age")) {
                    this.setAge(p_213386_5_.getInt("Age"));
                }
                return p_213386_4_;
            }
        }
        else {
            this.setVariant(this.methodOfDeterminingVariant());
            this.setSubVariant(this.methodOfDeterminingSubVariant());
        }
        float f = (float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
        this.setHeightMultiplier(f);
        this.setGender(this.random.nextInt(2));
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    protected void registerGoals() {
        this.randomStrollGoal = new RandomStrollGoal(this, this.getMoveSpeed(), 20);
        this.goalSelector.addGoal(7, this.randomStrollGoal);
        this.randomStrollGoal.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.goalSelector.addGoal(1, new EatFoodGoal());
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));

    }

    public double getMoveSpeed() {
        return 1.0D;
    }

    protected PathNavigation createNavigation(Level p_175447_1_) {
        return new WaterBoundPathNavigation(this, p_175447_1_);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isBaby() {
        return this.getAge() < 0;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public boolean isMoving() {
        return this.entityData.get(DATA_ID_MOVING);
    }

    public void setMoving(boolean p_175476_1_) {
        this.entityData.set(DATA_ID_MOVING, p_175476_1_);
    }

    public float getWalkTargetValue(BlockPos p_205022_1_, net.minecraft.world.level.LevelReader p_205022_2_) {
        return p_205022_2_.getFluidState(p_205022_1_).is(FluidTags.WATER)
                ? 10.0F + p_205022_2_.getBrightness(net.minecraft.world.level.LightLayer.BLOCK, p_205022_1_) - 0.5F
                : super.getWalkTargetValue(p_205022_1_, p_205022_2_);
    }

    public void aiStep() {
        if (!this.level().isClientSide && this.coolDown > 0) {
            --this.coolDown;
        }

        if (this.isAlive()) {
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

    public void travel(Vec3 p_213352_1_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, p_213352_1_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(p_213352_1_);
        }
    }

    public int getSubVariant() {
        return this.entityData.get(SUBVARIANT);
    }

    public void setSubVariant(int sub) {
        this.entityData.set(SUBVARIANT, sub);
    }

    public Component getFunFact() {
        return Component.translatable("creatures.unknown");
    }

    public String getScientificName() {
        return "";
    }

    public int getIUCNStatus() {
        return 0;
    }

    public int getIUCNColor() {
        // Return as chat color int — callers can use ChatFormatting or direct color
        if (this.getIUCNStatus() == 0) return 0x00AA00; // dark green - least concern
        if (this.getIUCNStatus() == 1) return 0xFFAA00; // gold - near threatened
        if (this.getIUCNStatus() == 2) return 0xFFAA00; // gold - vulnerable
        if (this.getIUCNStatus() == 3) return 0xFF5555; // red - endangered
        if (this.getIUCNStatus() == 4) return 0xAA0000; // dark red - critically endangered
        if (this.getIUCNStatus() == 5) return 0xAA00AA; // dark purple - extinct in wild
        if (this.getIUCNStatus() == 6) return 0x000000; // black - extinct
        return 0xAAAAAA; // gray - unknown
    }

    public Component getIUCNText() {
        if (this.getIUCNStatus() == 0) return Component.translatable("creatures.leastconcern");
        if (this.getIUCNStatus() == 1) return Component.translatable("creatures.nearthreatened");
        if (this.getIUCNStatus() == 2) return Component.translatable("creatures.vulnerable");
        if (this.getIUCNStatus() == 3) return Component.translatable("creatures.endangered");
        if (this.getIUCNStatus() == 4) return Component.translatable("creatures.criticallyendangered");
        if (this.getIUCNStatus() == 5) return Component.translatable("creatures.extinctinwild");
        if (this.getIUCNStatus() == 6) return Component.translatable("creatures.extinct");
        return Component.translatable("creatures.datadeficient");
    }

    protected static class MoveHelperController extends MoveControl {
        protected FishBase fishbase;

        public MoveHelperController(FishBase p_i45831_1_) {
            super(p_i45831_1_);
            this.fishbase = p_i45831_1_;
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO && !this.fishbase.getNavigation().isDone()) {
                Vec3 vector3d = new Vec3(
                        this.wantedX - this.fishbase.getX(),
                        this.wantedY - this.fishbase.getY(),
                        this.wantedZ - this.fishbase.getZ());
                double d0 = vector3d.length();
                double d1 = vector3d.x / d0;
                double d2 = vector3d.y / d0;
                double d3 = vector3d.z / d0;
                float f = (float)(Mth.atan2(vector3d.z, vector3d.x) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.fishbase.setYRot(this.rotlerp(this.fishbase.getYRot(), f, 90.0F));
                this.fishbase.yBodyRot = this.fishbase.getYRot();
                float f1 = (float)(this.speedModifier * this.fishbase.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float f2 = Mth.lerp(0.125F, this.fishbase.getSpeed(), f1);
                this.fishbase.setSpeed(f2);
                double d4 = Math.sin((double)(this.fishbase.tickCount + this.fishbase.getId()) * 0.5D) * 0.05D;
                double d5 = Math.cos((double)(this.fishbase.getYRot() * ((float)Math.PI / 180F)));
                double d6 = Math.sin((double)(this.fishbase.getYRot() * ((float)Math.PI / 180F)));
                double d7 = Math.sin((double)(this.fishbase.tickCount + this.fishbase.getId()) * 0.75D) * 0.05D;
                this.fishbase.setDeltaMovement(this.fishbase.getDeltaMovement().add(
                        0, d7 * (d6 + d5) * 0.25D + (double)f2 * d2 * 0.1D, 0));
                LookControl lookcontrol = this.fishbase.getLookControl();
                double d8 = this.fishbase.getX() + d1 * 2.0D;
                double d9 = this.fishbase.getEyeY() + d2 / d0;
                double d10 = this.fishbase.getZ() + d3 * 2.0D;
                double d11 = lookcontrol.getWantedX();
                double d12 = lookcontrol.getWantedY();
                double d13 = lookcontrol.getWantedZ();
                if (!lookcontrol.isLookingAtTarget()) {
                    d11 = d8;
                    d12 = d9;
                    d13 = d10;
                }
                this.fishbase.setMoving(true);
            } else {
                this.fishbase.setSpeed(0.0F);
                this.fishbase.setMoving(false);
            }
        }
    }

    public static boolean checkFishSpawnRules(EntityType<? extends AbstractFish> p_223363_0_, ServerLevelAccessor p_223363_1_, MobSpawnType p_223363_2_, BlockPos p_223363_3_, net.minecraft.util.RandomSource p_223363_4_) {
        return p_223363_1_.getBlockState(p_223363_3_).is(Blocks.WATER)
                && p_223363_1_.getBlockState(p_223363_3_.above()).is(Blocks.WATER);
    }

    public Item getFoodItem() {
        return CreaturesItems.ALGAE_WAFER.get();
    }

    public void breed() {
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (!this.level().isClientSide()) {
            if (itemstack.getItem() == getFoodItem() && this.isAlive() && this.coolDown <= 0 && !this.isBaby()) {
                TargetingConditions predicate = TargetingConditions.forNonCombat()
                        .range(16.0D)
                        .ignoreLineOfSight()
                        .selector((e) -> e.getClass() == this.getClass() && !((FishBase)e).isBaby());
                List<FishBase> list = this.level().getNearbyEntities(FishBase.class, predicate, this,
                        this.getBoundingBox().inflate(10.0D, 10.0D, 10.0D));
                if (!list.isEmpty()) {
                    boolean canbreed = false;
                    int index = 0;
                    for (int lol = 0; lol < list.size(); lol++) {
                        if (list.get(lol).coolDown <= 0) {
                            canbreed = true;
                            break;
                        }
                        index += 1;
                    }
                    if (canbreed && !FishBase.this.isBaby()) {
                        this.layEgg((ServerLevel) this.level(), list.get(index));
                        if (!p_230254_1_.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                        this.heal(2.0F);
                        this.coolDown = this.random.nextInt(6000) + 6000;
                        if (list.get(index) != null) {
                            list.get(index).coolDown = this.random.nextInt(6000) + 6000;
                        }
                        return InteractionResult.SUCCESS;
                    }
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            }
        }
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE.get()) {
            if (this.level().isClientSide()) {
                Creatures.PROXY.setReferencedMob(this);
                Creatures.PROXY.openCreaturesGui();
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }

    public String getSpeciesName() {
        return this.getType().getDescription().getString();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_MOVING, false);
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
        this.entityData.define(AGE, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(BRED, false);
        this.entityData.define(SUBVARIANT, 0);
        this.entityData.define(VARIANT, 0);
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

    public int getAge() {
        return this.entityData.get(AGE);
    }

    public void setAge(int i) {
        this.entityData.set(AGE, i);
    }

    public int getGender() {
        return Mth.clamp(this.entityData.get(GENDER), 0, 2);
    }

    public void setGender(int p_191997_1_) {
        this.entityData.set(GENDER, p_191997_1_);
    }

    public void setHeightMultiplier(float p_70606_1_) {
        if (this.getHeightMultiplier() < 0.7F) {
            this.entityData.set(HEIGHT_MULTIPLIER, 1.0F);
        } else {
            this.entityData.set(HEIGHT_MULTIPLIER, Mth.clamp(p_70606_1_, 0.7F, 1.5F));
        }
    }

    public String getHeightString() {
        if (this.getHeightMultiplier() >= 1.5)  return Component.translatable("gui.giant").getString();
        if (this.getHeightMultiplier() >= 1.4)  return Component.translatable("gui.huge").getString();
        if (this.getHeightMultiplier() >= 1.21) return Component.translatable("gui.large").getString();
        if (this.getHeightMultiplier() > 1.11)  return Component.translatable("gui.above_average").getString();
        if (this.getHeightMultiplier() >= 0.89) return Component.translatable("gui.average").getString();
        if (this.getHeightMultiplier() >= 0.79) return Component.translatable("gui.below_average").getString();
        return Component.translatable("gui.small").getString();
    }

    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        p_213281_1_.putFloat("HeightMultiplier", this.getHeightMultiplier());
        p_213281_1_.putBoolean("Bred", this.wasBred());
        p_213281_1_.putInt("Age", this.getAge());
        p_213281_1_.putInt("Gender", this.getGender());
        p_213281_1_.putInt("Subvariant", this.getSubVariant());
        p_213281_1_.putInt("Variant", this.getVariant());
        p_213281_1_.putInt("CoolDown", this.coolDown);
        super.addAdditionalSaveData(p_213281_1_);
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setBred(p_70037_1_.getBoolean("Bred"));
        this.setAge(p_70037_1_.getInt("Age"));
        this.setGender(p_70037_1_.getInt("Gender"));
        this.setVariant(p_70037_1_.getInt("Variant"));
        this.setSubVariant(p_70037_1_.getInt("Subvariant"));
        this.coolDown = p_70037_1_.getInt("CoolDown");
        if (!p_70037_1_.contains("HeightMultiplier")
                || this.getHeightMultiplier() < 0.7F
                || this.getHeightMultiplier() > 1.5F) {
            this.setHeightMultiplier((float)(this.random.nextGaussian()
                    * CreaturesConfig.height_standard_deviation.get()
                    + CreaturesConfig.height_base_multiplier.get()));
        } else {
            this.setHeightMultiplier(p_70037_1_.getFloat("HeightMultiplier"));
        }
    }

    public float getHatchChance() {
        return 1;
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setBaby(boolean p_82227_1_) {
        this.setAge(p_82227_1_ ? -24000 : 0);
    }

    public CreaturesRoeEntity layEgg(FishBase animal) {
        CreaturesRoeEntity egg = new CreaturesRoeEntity(CreaturesEntities.ROE.get(), this.level());
        egg.setSpecies(CreaturesEntities.getIntFromFishEntity(animal));
        egg.setGender(this.random.nextInt(2));
        egg.setVariant(this.getVariant());
        egg.setPos(
                Mth.floor(this.getX()) + 0.5,
                Mth.floor(this.getY()) + 0.5,
                Mth.floor(this.getZ()) + 0.5);
        return egg;
    }

    public int getClutchSize() {
        return 10;
    }

    protected void layEgg(ServerLevel server, FishBase father) {
        int c = this.getClutchSize();
        for (int j = 0; j <= c; j++) {
            CreaturesRoeEntity egg = this.layEgg(this);
            if (egg != null) {
                FishBase mother = this;
                egg.setParentUUID(mother.getUUID());

                float f = (float)(this.getRandom().nextGaussian() * 0.05 + this.getHeightMultiplier());
                egg.setHeightMultiplier(f);

                int[] vars = {this.getVariant(), father.getVariant()};
                int rnd = new Random().nextInt(vars.length);
                egg.setVariant(vars[rnd]);
                egg.setGender(this.random.nextInt(2));

                Random rand = new Random();
                egg.setPos(
                        Mth.floor(mother.getX()) + 0.5 + (-1 + rand.nextFloat()),
                        Mth.floor(mother.getY()) + 0.5,
                        Mth.floor(mother.getZ()) + 0.5 + (-1 + rand.nextFloat()));
                server.addFreshEntityWithPassengers(egg);
            }
            server.broadcastEntityEvent(this, (byte) 18);
        }

        net.minecraft.util.RandomSource random = this.getRandom();
        for (int i = 0; i < 17; ++i) {
            double d0 = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;
            double d3 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            double d4 = 0.5D + random.nextDouble() * this.getBbHeight();
            double d5 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            this.level().addParticle(ParticleTypes.HEART,
                    this.getX() + d3, this.getY() + d4, this.getZ() + d5, d0, d1, d2);
        }
        this.setBred(true);
        if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            server.addFreshEntity(new ExperienceOrb(server,
                    this.getX(), this.getY(), this.getZ(),
                    this.getRandom().nextInt(7) + 1));
        }
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.wasBred();
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return super.removeWhenFarAway(p_213397_1_) && !this.wasBred();
    }

    public ItemStack getDisplayFood() {
        return new ItemStack(getFoodItem(), 1);
    }

    protected class EatFoodGoal extends Goal {
        private int cooldown;
        public final Predicate<ItemEntity> CAN_EAT = (p_205023_0_) ->
                p_205023_0_.getItem().getItem() == FishBase.this.getFoodItem()
                        && p_205023_0_.isAlive()
                        && p_205023_0_.isInWater();

        public EatFoodGoal() {
        }

        public boolean canUse() {
            if (this.cooldown > FishBase.this.tickCount) {
                return false;
            }
            List<ItemEntity> list = FishBase.this.level().getEntitiesOfClass(ItemEntity.class,
                    FishBase.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CAN_EAT);
            return !list.isEmpty() || !FishBase.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
        }

        public void start() {
            List<ItemEntity> list = FishBase.this.level().getEntitiesOfClass(ItemEntity.class,
                    FishBase.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CAN_EAT);
            if (!list.isEmpty()) {
                FishBase.this.getNavigation().moveTo(list.get(0), 1.2F);
            }
            this.cooldown = 0;
        }

        public void stop() {
            ItemStack itemstack = FishBase.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.eat(itemstack);
                FishBase.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                this.cooldown = FishBase.this.tickCount + FishBase.this.random.nextInt(100);
            }
        }

        public void tick() {
            List<ItemEntity> list = FishBase.this.level().getEntitiesOfClass(ItemEntity.class,
                    FishBase.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CAN_EAT);
            ItemStack itemstack = FishBase.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.eat(itemstack);
                FishBase.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else if (!list.isEmpty()) {
                FishBase.this.getNavigation().moveTo(list.get(0), 1.2F);
                if (!FishBase.this.level().isClientSide() && FishBase.this.isAlive()) {
                    for (ItemEntity itementity : FishBase.this.level().getEntitiesOfClass(ItemEntity.class,
                            FishBase.this.getBoundingBox().inflate(1.0D, 0.0D, 1.0D), CAN_EAT)) {
                        if (itementity.isAlive() && !itementity.getItem().isEmpty()
                                && itementity.getItem().getItem() == FishBase.this.getFoodItem()) {
                            FishBase.this.pickUpItem(itementity);
                        }
                    }
                }
            }
        }

        private void eat(ItemStack p_220810_1_) {
            if (!p_220810_1_.isEmpty()) {
                FishBase.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                TargetingConditions predicate = TargetingConditions.forNonCombat()
                        .range(16.0D)
                        .ignoreLineOfSight()
                        .selector((e) -> e.getClass() == FishBase.this.getClass() && !((FishBase)e).isBaby());
                List<FishBase> list = FishBase.this.level().getNearbyEntities(FishBase.class, predicate,
                        FishBase.this, FishBase.this.getBoundingBox().inflate(10.0D, 10.0D, 10.0D));
                if (!list.isEmpty()) {
                    boolean canbreed = false;
                    int index = 0;
                    for (int lol = 0; lol < list.size(); lol++) {
                        if (list.get(lol).coolDown <= 0) {
                            canbreed = true;
                            break;
                        }
                        index += 1;
                    }
                    if (!(p_220810_1_.getItem() == CreaturesItems.FISH_FOOD.get()
                            || p_220810_1_.getItem() == CreaturesItems.ALGAE_WAFER.get())) {
                        canbreed = false;
                    }
                    if (canbreed && !FishBase.this.isBaby()) {
                        FishBase.this.layEgg((ServerLevel) FishBase.this.level(), list.get(index));
                        FishBase.this.coolDown = FishBase.this.random.nextInt(6000) + 6000;
                        if (list.get(index) != null) {
                            list.get(index).coolDown = FishBase.this.random.nextInt(6000) + 6000;
                        }
                    }
                }
                FishBase.this.heal(FishBase.this.getMaxHealth());
                if (FishBase.this.isBaby()) {
                    int age = FishBase.this.getAge();
                    age += (int)(float)(24000 / 20.0);
                    FishBase.this.setAge(age);
                }
            }
        }
    }

    public boolean canTakeItem(ItemStack p_213365_1_) {
        EquipmentSlot equipmentslottype = Mob.getEquipmentSlotForItem(p_213365_1_);
        if (p_213365_1_.getItem() != this.getFoodItem()) {
            return false;
        }
        if (!this.getItemBySlot(equipmentslottype).isEmpty()) {
            return false;
        }
        return equipmentslottype == EquipmentSlot.MAINHAND && super.canTakeItem(p_213365_1_);
    }

    public boolean canHoldItem(ItemStack p_175448_1_) {
        return p_175448_1_.getItem() == this.getFoodItem();
    }

    protected void pickUpItem(ItemEntity p_175445_1_) {
        if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            ItemStack itemstack = p_175445_1_.getItem();
            if (this.canHoldItem(itemstack)) {
                this.onItemPickup(p_175445_1_);
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
                this.take(p_175445_1_, itemstack.getCount());
                p_175445_1_.discard();
            }
        }
    }

    public void setVariant(int i) {
        this.entityData.set(VARIANT, i);
    }

    public int determineVariant() {
        return 1;
    }

    public Component getGenderText() {
        if (this.getGender() == 1) return Component.translatable("gui.male");
        return Component.translatable("gui.female");
    }

    public String getGenderString() {
        if (this.getGender() == 0) return "f";
        return "m";
    }

    public <T extends FishBase> T getBreedOffspring(ServerLevel world, Class<T> offspringClass) {
        try {
            T offspring = offspringClass.getConstructor(ServerLevel.class).newInstance(world);
            if (offspring != null) {
                offspring.setVariant(this.getVariant());
                offspring.setGender(this.random.nextInt(2));
                offspring.setHeightMultiplier(this.getHeightMultiplier());
            }
            return offspring;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int numVariants() {
        return 1;
    }

//    public int methodOfDeterminingSubVariant() {
//        return this.random.nextInt(numVariants()) + 1;
//    }

    public int methodOfDeterminingVariant() {
        return this.random.nextInt(numVariants()) + 1;
    }

    public int methodOfDeterminingSubVariant() {
        return 1;
    }

    public void setForcedInWater(boolean inWater) {
        this.wasTouchingWater = inWater;
    }

    public int getSubVariantBasedOnVariant(int variant) {
        return 1;
    }

    public Quaternionf getRotforGUI() {
        return new Quaternionf().rotateZ((float)Math.PI).rotateY((float)Math.toRadians(140));
    }

    public int getScaleforGUI() {
        float h = this.getBbHeight();
        int scale = (int)(20f / h);
        return scale;
    }

}
