package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.egg.RoeEntity;
import com.frikinzi.creatures.util.ModEventSubscriber;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
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
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

abstract public class AbstractCreaturesFish extends AbstractSchoolingFish {
    private static final EntityDataAccessor<Boolean> DATA_ID_MOVING = SynchedEntityData.defineId(AbstractCreaturesFish.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> HEIGHT_MULTIPLIER = SynchedEntityData.defineId(AbstractCreaturesFish.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(AbstractCreaturesFish.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> BRED = SynchedEntityData.defineId(AbstractCreaturesFish.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(AbstractCreaturesFish.class, EntityDataSerializers.INT);
    public int coolDown = 0;

    protected RandomStrollGoal randomStrollGoal;

    public AbstractCreaturesFish(EntityType<? extends AbstractCreaturesFish> p_27523_, Level p_27524_) {
        super(p_27523_, p_27524_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new AbstractCreaturesFish.MoveHelperController(this);
        this.setCanPickUpLoot(true);
    }


    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_30023_, DifficultyInstance p_30024_, MobSpawnType p_30025_, @Nullable SpawnGroupData p_30026_, @Nullable CompoundTag p_30027_) {
        p_30026_ = super.finalizeSpawn(p_30023_, p_30024_, p_30025_, p_30026_, p_30027_);
        if (p_30027_ != null && p_30025_ == MobSpawnType.BUCKET) {
            if (p_30027_.contains("BucketVariantTag", 3)) {
                this.setVariant(p_30027_.getInt("BucketVariantTag"));
                //return p_213386_4_;
            }
            if (p_30027_.contains("BucketHeightMultiplier")) {
                this.setHeightMultiplier(p_30027_.getFloat("BucketHeightMultiplier"));
            } if (p_30027_.contains("Age")) {
                this.setAge(p_30027_.getInt("Age"));
            }
        } else {
            this.setVariant(this.determineVariant());
            float f = (float) (this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
            this.setHeightMultiplier(f);
        }
        return p_30026_;
    }

    protected void registerGoals() {
        this.randomStrollGoal = new RandomStrollGoal(this, this.getMoveSpeed(), 20);
        this.goalSelector.addGoal(7, this.randomStrollGoal);
        this.randomStrollGoal.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.goalSelector.addGoal(1, new AbstractCreaturesFish.EatFoodGoal());
    }

    private void setMoving(boolean p_175476_1_) {
        this.entityData.set(DATA_ID_MOVING, p_175476_1_);
    }

    public boolean isMoving() {
        return this.entityData.get(DATA_ID_MOVING);
    }

    public float getWalkTargetValue(BlockPos p_32831_, LevelReader p_32832_) {
        return p_32832_.getFluidState(p_32831_).is(FluidTags.WATER) ? 10.0F + p_32832_.getBrightness(p_32831_) - 0.5F : super.getWalkTargetValue(p_32831_, p_32832_);
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

    public void travel(Vec3 p_32858_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, p_32858_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (!this.isMoving() && this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.000D, 0.0D));
            }
        } else {
            super.travel(p_32858_);
        }

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

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_MOVING, false);
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
        this.entityData.define(AGE, 0);
        this.entityData.define(BRED, false);
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

    public int noVariants() {
        return 1;
    }

    public int getVariant() {
        return Mth.clamp(this.entityData.get(VARIANT), 1, this.noVariants());
    }

    public void setVariant(int p_29449_) {
        this.entityData.set(VARIANT, p_29449_);
    }


    public int determineVariant() {
        return this.random.nextInt(this.noVariants()) + 1;
    }

    public void setHeightMultiplier(float p_70606_1_) {
        if (this.getHeightMultiplier() < 0.7F) {
            this.entityData.set(HEIGHT_MULTIPLIER, 1.0F);
        } else {
            this.entityData.set(HEIGHT_MULTIPLIER, Mth.clamp(p_70606_1_, 0.7F, 1.5F)); }
    }


    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        compoundnbt.putInt("Age", this.getAge());

    }


    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        p_213281_1_.putFloat("HeightMultiplier", this.getHeightMultiplier());
        p_213281_1_.putBoolean("Bred", this.wasBred());
        p_213281_1_.putInt("Age", this.getAge());
        p_213281_1_.putInt("Variant", this.getVariant());

        super.addAdditionalSaveData(p_213281_1_);
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setBred(p_70037_1_.getBoolean("Bred"));
        this.setAge(p_70037_1_.getInt("Age"));
        this.setVariant(p_70037_1_.getInt("Variant"));
        if (!p_70037_1_.contains("HeightMultiplier") || this.getHeightMultiplier() < 0.7F || this.getHeightMultiplier() > 1.5F) {
            this.setHeightMultiplier((float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get()));
        } else {
            this.setHeightMultiplier(p_70037_1_.getFloat("HeightMultiplier")); }
    }

    public double getHatchChance() {
        return 1;
    }

    public String getSpeciesName() {
        return this.getType().getDescription().getString();
    }

    public void setBaby(boolean p_82227_1_) {
        this.setAge(p_82227_1_ ? -24000 : 0);
    }


    static class MoveHelperController extends MoveControl {
        private final AbstractCreaturesFish fishbase;

        public MoveHelperController(AbstractCreaturesFish p_i45831_1_) {
            super(p_i45831_1_);
            this.fishbase = p_i45831_1_;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO && !this.fishbase.getNavigation().isDone()) {
                Vec3 vector3d = new Vec3(this.wantedX - this.fishbase.getX(), this.wantedY - this.fishbase.getY(), this.wantedZ - this.fishbase.getZ());
                double d0 = vector3d.length();
                double d1 = vector3d.x / d0;
                double d2 = vector3d.y / d0;
                double d3 = vector3d.z / d0;
                float f = (float) (Mth.atan2(vector3d.z, vector3d.x) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.fishbase.setYRot(this.rotlerp(this.fishbase.getYRot(), f, 90.0F));                this.fishbase.yBodyRot = this.fishbase.getYRot();
                float f1 = (float) (this.speedModifier * this.fishbase.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float f2 = Mth.lerp(0.125F, this.fishbase.getSpeed(), f1);
                this.fishbase.setSpeed(f2);
                double d4 = Math.sin((double) (this.fishbase.tickCount + this.fishbase.getId()) * 0.5D) * 0.05D;
                double d5 = Math.cos((double) (this.fishbase.getYRot() * ((float) Math.PI / 180F)));
                double d6 = Math.sin((double) (this.fishbase.getYRot() * ((float) Math.PI / 180F)));
                double d7 = Math.sin((double) (this.fishbase.tickCount + this.fishbase.getId()) * 0.75D) * 0.05D;
                this.fishbase.setDeltaMovement(this.fishbase.getDeltaMovement().add(0, d7 * (d6 + d5) * 0.25D + (double)f2 * d2 * 0.1D, 0));
                LookControl lookcontroller = this.fishbase.getLookControl();
                double d8 = this.fishbase.getX() + d1 * 2.0D;
                double d9 = this.fishbase.getEyeY() + d2 / d0;
                double d10 = this.fishbase.getZ() + d3 * 2.0D;
                double d11 = lookcontroller.getWantedX();
                double d12 = lookcontroller.getWantedY();
                double d13 = lookcontroller.getWantedZ();
                if (!lookcontroller.isLookingAtTarget()) {
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




    public RoeEntity layEgg(AbstractCreaturesFish animal) {
        RoeEntity egg = new RoeEntity(CreaturesEntities.ROE.get(), this.level);
        egg.setSpecies(ModEventSubscriber.getFishEntityMap().inverse().get(animal.getType()));
        egg.setGender(this.random.nextInt(2));
        egg.setVariant(this.getVariant());
        egg.setPos(Mth.floor(this.getX()) + 0.5, Mth.floor(this.getY()) + 0.5, Mth.floor(this.getZ()) + 0.5);
        return egg;
    }

    public int getClutchSize() {
        return 10;
    }


    protected void layEgg(ServerLevel server, AbstractCreaturesFish father) {
        int c = this.getClutchSize();
        for (int j = 0; j <= c; j++) {
            RoeEntity egg = this.layEgg(this);
            if (egg != null) {
                AbstractCreaturesFish mother;
                mother = this;

                float f = (float)(this.getRandom().nextGaussian() * 0.05 + ((this.getHeightMultiplier())));
                egg.setHeightMultiplier(f);
                int[] vars = {this.getVariant(), father.getVariant()};
                int rnd = new Random().nextInt(vars.length);
                egg.setVariant(vars[rnd]);

                Random rand = new Random();
                egg.setPos(Mth.floor(mother.getX()) + 0.5 + (-1+rand.nextFloat()), Mth.floor(mother.getY()) + 0.5, Mth.floor(mother.getZ()) + 0.5 + (-1+rand.nextFloat()));
                server.addFreshEntityWithPassengers(egg);
                //System.out.println(this.bird.getClutchSize());
            }
            server.broadcastEntityEvent(this, (byte)18);
        }
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
        this.setBred(true);
        if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            server.addFreshEntity(new ExperienceOrb(server, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
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

    public Item getFoodItem() {
        return Items.KELP;
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (!this.level.isClientSide()) {
            if (itemstack.getItem() == getFoodItem() && this.isAlive() && this.coolDown <= 0 && !this.isBaby()) {
                TargetingConditions predicate = TargetingConditions.forNonCombat().range(16.0D).selector((p_220844_0_) -> {
                    return p_220844_0_.getClass() == this.getClass() && !(p_220844_0_).isBaby();
                });
                List<AbstractCreaturesFish> list = this.level.getNearbyEntities(AbstractCreaturesFish.class, predicate, this, this.getBoundingBox().inflate(10.0D, 10.0D, 10.0D));
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
                    if (canbreed && !AbstractCreaturesFish.this.isBaby()) {
                        this.layEgg((ServerLevel) this.level, list.get(index));
                        if (!p_230254_1_.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                        this.heal((float)itemstack.getItem().getFoodProperties().getNutrition());
                        this.coolDown = this.random.nextInt(6000) + 6000;
                        if (list.get(0) != null) {
                            list.get(0).coolDown = this.random.nextInt(6000) + 6000;
                        }
                        return InteractionResult.SUCCESS;
                    }
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        }
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE.get()) {
            if (this.level.isClientSide) {
                Creatures.PROXY.setReferencedMob(this);
                Creatures.PROXY.openCreaturesGui();
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
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

    class EatFoodGoal extends Goal {
        private int cooldown;
        public final Predicate<ItemEntity> CAN_EAT = (p_205023_0_) -> {
            return p_205023_0_.getItem().getItem() == AbstractCreaturesFish.this.getFoodItem() && p_205023_0_.isAlive() && p_205023_0_.isInWater();
        };

        private EatFoodGoal() {
        }

        public boolean canUse() {
            if (!AbstractCreaturesFish.this.isInWater()) {
                return false;
            }
            if (this.cooldown > AbstractCreaturesFish.this.tickCount) {
                return false;
            } else {
                List<ItemEntity> list = AbstractCreaturesFish.this.level.getEntitiesOfClass(ItemEntity.class, AbstractCreaturesFish.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CAN_EAT);
                return !list.isEmpty() || !AbstractCreaturesFish.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            }
        }

        public void start() {
            List<ItemEntity> list = AbstractCreaturesFish.this.level.getEntitiesOfClass(ItemEntity.class, AbstractCreaturesFish.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CAN_EAT);
            if (!list.isEmpty()) {
                AbstractCreaturesFish.this.getNavigation().moveTo(list.get(0), (double)1.2F);
                //FishBase.this.playSound(SoundEvents.DOLPHIN_PLAY, 1.0F, 1.0F);
            }

            this.cooldown = 0;
        }

        public void stop() {
            ItemStack itemstack = AbstractCreaturesFish.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.eat(itemstack);
                AbstractCreaturesFish.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                this.cooldown = AbstractCreaturesFish.this.tickCount + AbstractCreaturesFish.this.random.nextInt(100);
            }

        }

        public void tick() {
            List<ItemEntity> list = AbstractCreaturesFish.this.level.getEntitiesOfClass(ItemEntity.class, AbstractCreaturesFish.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CAN_EAT);
            ItemStack itemstack = AbstractCreaturesFish.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.eat(itemstack);
                AbstractCreaturesFish.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else if (!list.isEmpty()) {
                AbstractCreaturesFish.this.getNavigation().moveTo(list.get(0), (double)1.2F);
                if (!AbstractCreaturesFish.this.level.isClientSide && AbstractCreaturesFish.this.isAlive() && !AbstractCreaturesFish.this.dead) {
                    for(ItemEntity itementity : AbstractCreaturesFish.this.level.getEntitiesOfClass(ItemEntity.class, AbstractCreaturesFish.this.getBoundingBox().inflate(1.0D, 0.0D, 1.0D), CAN_EAT)) {
                        if (!itementity.isRemoved() && !itementity.getItem().isEmpty() && !itementity.hasPickUpDelay() && itementity.getItem().getItem() == AbstractCreaturesFish.this.getFoodItem()) {
                            AbstractCreaturesFish.this.pickUpItem(itementity);
                        }
                    }
                }
            }

        }

        private void eat(ItemStack p_220810_1_) {
            if (!p_220810_1_.isEmpty()) {
                AbstractCreaturesFish.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                TargetingConditions predicate = TargetingConditions.forNonCombat().range(16.0D).selector((p_220844_0_) -> {
                    return p_220844_0_.getClass() == AbstractCreaturesFish.this.getClass() && !((AbstractCreaturesFish)p_220844_0_).isBaby();
                });
                List<AbstractCreaturesFish> list = AbstractCreaturesFish.this.level.getNearbyEntities(AbstractCreaturesFish.class, predicate, AbstractCreaturesFish.this, AbstractCreaturesFish.this.getBoundingBox().inflate(10.0D, 10.0D, 10.0D));
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
                    if (canbreed && !AbstractCreaturesFish.this.isBaby()) {
                        AbstractCreaturesFish.this.layEgg((ServerLevel)AbstractCreaturesFish.this.level, list.get(index));
                        AbstractCreaturesFish.this.coolDown = AbstractCreaturesFish.this.random.nextInt(6000) + 6000;
                        if (list.get(0) != null) {
                            list.get(0).coolDown = AbstractCreaturesFish.this.random.nextInt(6000) + 6000;
                        }
                    }
                }
                AbstractCreaturesFish.this.heal(AbstractCreaturesFish.this.getMaxHealth());
                if (AbstractCreaturesFish.this.isBaby()) {
                    int age = AbstractCreaturesFish.this.getAge();
                    age += (int)(float)(24000 / 20.0);
                    AbstractCreaturesFish.this.setAge(age);
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
        } else {
            return equipmentslottype == EquipmentSlot.MAINHAND && super.canTakeItem(p_213365_1_);
        }
    }

    public boolean canHoldItem(ItemStack p_175448_1_) {
        if (p_175448_1_.getItem() != this.getFoodItem()) {
            return false;
        }
        return true;
    }

    protected void pickUpItem(ItemEntity p_175445_1_) {
        if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            ItemStack itemstack = p_175445_1_.getItem();
            if (this.canHoldItem(itemstack)) {
                this.onItemPickup(p_175445_1_);
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
                this.take(p_175445_1_, itemstack.getCount());
                p_175445_1_.remove(RemovalReason.DISCARDED);
            }
        }

    }

    public boolean canBeLeashed(Player p_30396_) {
        return false;
    }


}
