package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class StingrayEntity extends FishBase implements GeoEntity {
    private static final Set<Block> DIGGABLES = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_BLOCK, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.SOUL_SOIL);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(StingrayEntity.class, EntityDataSerializers.INT);
    private boolean isHiding = false;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public StingrayEntity(EntityType<? extends StingrayEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
        this.moveControl = new StingrayMoveHelperController(this);
    }
    private boolean didAttack;
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.commonstingray"))
            .put(2, Component.translatable("message.creatures.atlanticstingray"))
            .put(3, Component.translatable("message.creatures.bluespotted"))
            .put(4, Component.translatable("message.creatures.roundribbontail"))
            .put(5, Component.translatable("message.creatures.bullseye"))
            .put(6, Component.translatable("message.creatures.yellowstingray"))
            .put(7, Component.translatable("message.creatures.cowtailstingray"))
            .put(8, Component.translatable("message.creatures.commonstingaree"))
            .put(9, Component.translatable("message.creatures.maskedstingaree"))
            .put(10, Component.translatable("message.creatures.bandedstingaree"))
            .put(11, Component.translatable("message.creatures.spottedstingaree"))
            .put(12, Component.translatable("message.creatures.southernstingray"))
            .put(13, Component.translatable("message.creatures.pelagicstingray"))
            .put(14, Component.translatable("message.creatures.porcupinestingray"))
            .put(15, Component.translatable("message.creatures.leopardwhipray"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Dasyatis pastinaca")
            .put(2, "Dasyatis sabinus")
            .put(3, "Taeniura lymma")
            .put(4, "Taeniura meyeni")
            .put(5, "Diplobatis ommata")
            .put(6, "Urobatis jamaicensis")
            .put(7, "Pastinachus sephen")
            .put(8, "Trygonoptera testacea")
            .put(9, "Trygonoptera personata")
            .put(10, "Urolophus cruciatus")
            .put(11, "Urolophus gigas")
            .put(12, "Hypanus americanus")
            .put(13, "Pteroplatytrygon violacea")
            .put(14, "Urogymnus asperrimus")
            .put(15, "Himantura leoparda")
            .build();

    protected <E extends StingrayEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (!this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("Flop"));
        }
        if (this.isMoving()) {
            isHiding = false;
            return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));

        }
        if (this.canBurrow() && this.onGround() && !this.isMoving() && onDiggableBlock(this)) {
            if (!isHiding) {
                isHiding = true;  // Set the flag to true when Hiding animation starts
                return event.setAndContinue(RawAnimation.begin().then("Hiding", Animation.LoopType.PLAY_ONCE).thenLoop("HideIdle"));
            }
            return PlayState.CONTINUE;
        }
        isHiding = false;
        return event.setAndContinue(RawAnimation.begin().thenLoop("Idle"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Swimming", 0, this::swimAnimController));
    }

@Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

public int getMaxSchoolSize() {
        return 1;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.ELEPHANTNOSE_BUCKET.get());
    }

//    public void saveToBucketTag(ItemStack p_204211_1_) {
//        super.saveToBucketTag(p_204211_1_);
//        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
//        compoundnbt.putInt("BucketVariantTag", this.getVariant());
//        compoundnbt.putInt("BucketGenderTag", this.getGender());
//        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
//        compoundnbt.putInt("Age", this.getAge());
//    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

//    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
//        super.addAdditionalSaveData(p_213281_1_);
//        p_213281_1_.putInt("Gender", this.getGender());
//    }
//
//    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
//        super.readAdditionalSaveData(p_70037_1_);
//        this.setGender(p_70037_1_.getInt("Gender"));
//    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.TROPICAL_FISH;
    }

    public float getHatchChance() {
        return CreaturesConfig.stingray_hatch_chance.get().floatValue();
    }

    public net.minecraft.world.item.Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    protected void registerGoals() {
        this.randomStrollGoal = new StingrayRandomStrollGoal(this, 0.8D);
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2.0D, true));
        this.goalSelector.addGoal(7, this.randomStrollGoal);
        this.randomStrollGoal.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(3, new FishBase.EatFoodGoal());
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 1.0F, 1.8D, 1.8D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public String getGenderName() {
        if (this.getGender() == 0) {
            return "f";
        } return "m";
    }

    public float getSize() {
        switch(this.getVariant()) {
            case 1:
                return 1;
            case 2:
                return 0.5f;
            case 3:
                return 0.5f;
            case 4:
                return 1.5f;
            case 5:
                return 0.5f;
            case 6:
                return 0.5f;
            case 7:
                return 1.5f;
            case 8:
                return 0.5f;
            case 9:
                return 0.5f;
            case 10:
                return 0.5f;
            case 11:
                return 0.5f;
            case 12:
                return 1.1f;
            case 13:
                return 0.8f;
            case 14:
                return 1.1f;
            case 15:
                return 1.2f;
            default:
                return 1;
        }
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

    private boolean onDiggableBlock(PathfinderMob entity) {
        BlockPos pos = entity.blockPosition().below();
        BlockState blockState = entity.level().getBlockState(pos);
        return DIGGABLES.contains(blockState.getBlock());
    }

    static class HurtByTargetGoal extends net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal {
        public HurtByTargetGoal(StingrayEntity p_i47282_1_) {
            super(p_i47282_1_);
        }

        public boolean canContinueToUse() {
            if (this.mob instanceof StingrayEntity) {
                StingrayEntity stingrayentity = (StingrayEntity)this.mob;
                if (stingrayentity.didAttack) {
                    stingrayentity.setDidAttack(false);
                    return false;
                }
            }

            return super.canContinueToUse();
        }
    }

    static class StingrayRandomStrollGoal extends RandomStrollGoal {
        public StingrayRandomStrollGoal(StingrayEntity p_i1648_1_, double p_i1648_2_) {
            super(p_i1648_1_, p_i1648_2_, 120);
        }

        @Override
        public void tick() {
            if (this.mob instanceof StingrayEntity) {
                this.interval = ((StingrayEntity) this.mob).getActivityLevel();
            }
            super.tick();

        }

        public boolean canUse() {
            if (this.mob.level().isNight()) {
                this.interval = ((StingrayEntity)this.mob).getActivityLevel();
            }
            return super.canUse();
        }

    }

    private void setDidAttack(boolean p_190714_1_) {
        this.didAttack = p_190714_1_;
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        if (super.doHurtTarget(p_70652_1_)) {
            if (p_70652_1_ instanceof LivingEntity) {
                if (this.getVariant() != 14) {
                    int i = 0;
                    if (this.level().getDifficulty() == Difficulty.NORMAL) {
                        i = 7;
                    } else if (this.level().getDifficulty() == Difficulty.HARD) {
                        i = 15;
                    }

                    if (i > 0) {
                        ((LivingEntity)p_70652_1_).addEffect(new MobEffectInstance(MobEffects.POISON, i * 20, 0));
                    }
                }

            }
            this.setDidAttack(true);

            return true;
        } else {
            return false;
        }
    }

    public int getActivityLevel() {
        if (this.getVariant() == 13) {
            return 20;
        }
        else if (this.level().isNight()) {
            return 20;
        } else {
            return 5000;
        }
    }

    public boolean canBurrow() {
        return (this.getVariant() != 3 && this.getVariant() !=4 && this.getVariant() != 13);
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 1 || this.getVariant() == 4) {
            return 2;
        } if (this.getVariant() == 7 || this.getVariant() == 8 || this.getVariant() == 12) {
            return 1;
        } if (this.getVariant() == 14){
            return -1;
        } if (this.getVariant() == 15) {
            return 3;
        }
        return super.getIUCNStatus();
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
            return InteractionResult.PASS;
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.stingray");
    }
    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public int numVariants() {
        return 15;
    }

    @Override
    public Quaternionf getRotforGUI() {
        return new Quaternionf()
                .rotateZ((float) Math.PI)
                .rotateY((float) Math.toRadians(160))
                .rotateX((float) Math.toRadians(45));
    }
}
