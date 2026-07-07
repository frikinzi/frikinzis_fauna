package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.Map;

public class TrumpetfishEntity extends FishBase implements GeoEntity {
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.chinesetrumpetfish"))
            .put(2, Component.translatable("message.creatures.atlantictrumpetfish"))
            .put(3, Component.translatable("message.creatures.westernatlantictrumpetfish"))
            .build();
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public TrumpetfishEntity(EntityType<? extends TrumpetfishEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
        this.moveControl = new StingrayMoveHelperController(this);
    }
    private boolean didAttack;
    public static Map<Integer, Integer> TRUMPETFISH = ImmutableMap.<Integer, Integer>builder()
            .put(1, 4)
            .put(2, 3)
            .put(3, 6)
            .build();

    protected <E extends TrumpetfishEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (this.isMoving() || this.isAggressive() || !this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Swimming", 10, this::swimAnimController));
    }

@Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

public int getMaxSchoolSize() {
        return 1;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.TRUMPETFISH_BUCKET.get());
    }

    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        compoundnbt.putInt("BucketGenderTag", this.getGender());
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
        compoundnbt.putInt("Age", this.getAge());
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

//    public int getGender() {
//        return Mth.clamp(this.entityData.get(GENDER), 0, 2);
//    }
//
//    public void setGender(int p_191997_1_) {
//        this.entityData.set(GENDER, p_191997_1_);
//    }

    @Override
    public int getSubVariant() {
        Integer max = TRUMPETFISH.get(this.getVariant());
        if (max == null) return 1;
        return Mth.clamp(super.getSubVariant(), 1, max);
    }

//    public void setSubVariant(int p_191997_1_) {
//        this.entityData.set(VARIANT_SUBID, p_191997_1_);
//    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public float getHatchChance() {
        return CreaturesConfig.trumpetfish_hatch_chance.get().floatValue();
    }

    public Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    protected void registerGoals() {
        //super.registerGoals();
        this.randomStrollGoal = new StingrayRandomStrollGoal(this, 0.8D);
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 2.0D, true));
        this.goalSelector.addGoal(7, this.randomStrollGoal);
        this.randomStrollGoal.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(3, new EatFoodGoal());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, BlueTangEntity.class, false));
    }

    public String getGenderName() {
        if (this.getGender() == 0) {
            return "f";
        } return "m";
    }

    public void travel(Vec3 p_213352_1_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, p_213352_1_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (!this.isMoving() && this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_213352_1_);
        }

    }

    static class StingrayMoveHelperController extends MoveHelperController {

        public StingrayMoveHelperController(FishBase p_i45831_1_) {
            super(p_i45831_1_);
            this.fishbase = p_i45831_1_;
        }

        @Override
        public void tick() {
            if (this.operation == Operation.MOVE_TO && !this.fishbase.getNavigation().isDone()) {
                if ((this.wantedY - this.fishbase.getY())> 0 && !this.mob.isAggressive()) {
                    this.wantedY -= 0.7*((this.wantedY - this.fishbase.getY()));
                }
                Vec3 vector3d = new Vec3(this.wantedX - this.fishbase.getX(), this.wantedY - this.fishbase.getY(), this.wantedZ - this.fishbase.getZ());
                double d0 = vector3d.length();
                double d1 = vector3d.x / d0;
                double d2 = (vector3d.y / d0); // Scale down Y movement component
                double d3 = vector3d.z / d0;
                float f = (float) (Mth.atan2(vector3d.z, vector3d.x) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.fishbase.setYRot(this.rotlerp(this.fishbase.getYRot(), f, 90.0F));
                this.fishbase.yBodyRot = this.fishbase.getYRot();
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

                //this.fishbase.getLookControl().setLookAt(Mth.lerp(0.125D, d11, d8), Mth.lerp(0.125D, d12, d9), Mth.lerp(0.125D, d13, d10), 10.0F, 40.0F);
                this.fishbase.setMoving(true);
            } else {
                this.fishbase.setSpeed(0.0F);
                this.fishbase.setMoving(false);
            }
        }
    }

    static class HurtByTargetGoal extends net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal {
        public HurtByTargetGoal(TrumpetfishEntity p_i47282_1_) {
            super(p_i47282_1_);
        }

        public boolean canContinueToUse() {
            if (this.mob instanceof TrumpetfishEntity) {
                TrumpetfishEntity stingrayentity = (TrumpetfishEntity)this.mob;
                if (stingrayentity.didAttack) {
                    stingrayentity.setDidAttack(false);
                    return false;
                }
            }

            return super.canContinueToUse();
        }
    }

    static class StingrayRandomStrollGoal extends RandomStrollGoal {
        public StingrayRandomStrollGoal(TrumpetfishEntity p_i1648_1_, double p_i1648_2_) {
            super(p_i1648_1_, p_i1648_2_, 120);
        }

        @Override
        public void tick() {
            if (this.mob instanceof TrumpetfishEntity) {
                this.interval = ((TrumpetfishEntity) this.mob).getActivityLevel();
            }
            super.tick();

        }

        public boolean canUse() {
            if (this.mob.level().isNight()) {
                this.interval = ((TrumpetfishEntity)this.mob).getActivityLevel();
            }
            return super.canUse();
        }

    }

    private void setDidAttack(boolean p_190714_1_) {
        this.didAttack = p_190714_1_;
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        if (super.doHurtTarget(p_70652_1_)) {
            this.setDidAttack(true);

            return true;
        } else {
            return false;
        }
    }

    public int getActivityLevel() {
        return 2000;
    }

    public String getScientificName() {
        switch(this.getVariant()) {
            case 1:
                return "Aulostomus chinensis";
            case 2:
                return "Aulostomus stigosus";
            case 3:
                return "Aulostomus maculatus";
        }
        return "Unknown";

    }

    public int getIUCNStatus() {
        return 1;
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.TROPICAL_FISH;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

//    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
//        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
//        if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
//            return InteractionResult.PASS;
//        } else {
//            return super.mobInteract(p_230254_1_, p_230254_2_);
//        }
//    }

    public int methodOfDeterminingSubVariant() {
        return this.random.nextInt(TRUMPETFISH.get(this.getVariant()))+1;
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.trumpetfish");
    }

    public int numVariants() {
        return 3;
    }
}
