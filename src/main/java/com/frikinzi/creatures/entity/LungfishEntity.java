package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.*;

public class LungfishEntity extends FishBase implements GeoEntity {
    private static final EntityDataAccessor<Integer> VARIANT_SUBID = SynchedEntityData.defineId(LungfishEntity.class, EntityDataSerializers.INT);
    private static final Set<Block> DIGGABLES = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.SOUL_SOIL);
    private static final UUID SPEED_BOOST_UUID = UUID.randomUUID();
    private int cooldownTimer = 0;
    private int noJumpDelay;
    private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_BOOST_UUID, "Aggressive speed boost", 0.5D, AttributeModifier.Operation.ADDITION);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.australianlungfish"))
            .put(2, Component.translatable("message.creatures.australianlungfish"))
            .put(3, Component.translatable("message.creatures.africanlungfish"))
            .put(4, Component.translatable("message.creatures.southamericanlungfish"))

            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Neoceratodus forsteri")
            .put(2, "Neoceratodus forsteri")
            .put(3, "Protopterus annectens")
            .put(4, "Lepidosiren paradoxa")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.OCEANIA))
            .put(2, List.of(Region.OCEANIA))
            .put(3, List.of(Region.AFRICA))
            .put(4, List.of(Region.SOUTH_AMERICA))
            .build();

    public LungfishEntity(EntityType<? extends LungfishEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
        this.moveControl = new LungfishMoveController(this);

    }

    protected <E extends LungfishEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (!this.isInWater()) {
            if (this.onDiggableBlock(this)) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("curl"));
            }
        }
        if (event.isMoving()) {
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
        return new ItemStack(CreaturesItems.TAMBAQUI_BUCKET.get());
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 2.0).add(Attributes.ATTACK_SPEED, 2);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.LUNGFISH;
    }

    public float getHatchChance() {
        return CreaturesConfig.lungfish_hatch_chance.get().floatValue();
    }

    public Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    protected void registerGoals() {
        this.randomStrollGoal = new LungfishWalking(this, 0.8D);
        this.goalSelector.addGoal(7, this.randomStrollGoal);
        this.randomStrollGoal.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2.0D, true));
        //this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new EatFoodGoal());

    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
            return InteractionResult.PASS;
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    public void tick() {
        if (this.isAggressive() && !this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPEED_MODIFIER)) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(SPEED_MODIFIER);
        } else {
            if (this.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(SPEED_MODIFIER)) {
                this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER);
            }
        }

        super.tick();
    }

    public void aiStep() {
        if (!this.level().isClientSide && this.coolDown > 0) {
            --this.coolDown;
        }

        if (this.isAlive()) {
            if (this.level().isClientSide) {

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
        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            this.setDeltaMovement(0.0D, 0.0D, 0.0D);
            this.setOnGround(true);
            this.hasImpulse = false;
        }
        if (this.noJumpDelay > 0) {
            --this.noJumpDelay;
        }

        if (this.isControlledByLocalInstance()) {
            this.lerpSteps = 0;
            this.xOld = this.getX();
            this.yOld = this.getY();
            this.zOld = this.getZ();
        }

        if (this.lerpSteps > 0) {
            double d0 = this.getX() + (this.lerpX - this.getX()) / (double)this.lerpSteps;
            double d2 = this.getY() + (this.lerpY - this.getY()) / (double)this.lerpSteps;
            double d4 = this.getZ() + (this.lerpZ - this.getZ()) / (double)this.lerpSteps;
            double d6 = Mth.wrapDegrees(this.lerpYRot - (double)this.getYRot());
            this.setYRot((float)((double)this.getYRot() + d6 / (double)this.lerpSteps));
            this.setXRot((float)((double)this.getXRot() + (this.lerpXRot - (double)this.getXRot()) / (double)this.lerpSteps));
            --this.lerpSteps;
            this.setPos(d0, d2, d4);
            this.setRot(this.getYRot(), this.getXRot());
        } else if (!this.isEffectiveAi()) {
            this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        }

        if (this.lerpHeadSteps > 0) {
            this.yHeadRot = (float)((double)this.yHeadRot + Mth.wrapDegrees(this.lyHeadRot - (double)this.yHeadRot) / (double)this.lerpHeadSteps);
            --this.lerpHeadSteps;
        }

        Vec3 vector3d = this.getDeltaMovement();
        double d1 = vector3d.x;
        double d3 = vector3d.y;
        double d5 = vector3d.z;
        if (Math.abs(vector3d.x) < 0.003D) {
            d1 = 0.0D;
        }

        if (Math.abs(vector3d.y) < 0.003D) {
            d3 = 0.0D;
        }

        if (Math.abs(vector3d.z) < 0.003D) {
            d5 = 0.0D;
        }

        this.setDeltaMovement(d1, d3, d5);
        this.level().getProfiler().push("ai");
        if (this.isImmobile()) {
            this.jumping = false;
            this.xxa = 0.0F;
            this.zza = 0.0F;
        } else if (this.isEffectiveAi()) {
            this.level().getProfiler().push("newAi");
            this.serverAiStep();
            this.level().getProfiler().pop();
        }

        this.level().getProfiler().pop();
        this.level().getProfiler().push("jump");
        if (this.jumping && this.isAffectedByFluids()) {
            double d7;
            if (this.isInLava()) {
                d7 = this.getFluidHeight(FluidTags.LAVA);
            } else {
                d7 = this.getFluidHeight(FluidTags.WATER);
            }

            boolean flag = this.isInWater() && d7 > 0.0D;
            double d8 = this.getFluidJumpThreshold();
            if (!flag || this.onGround() && !(d7 > d8)) {
                if (!this.isInLava() || this.onGround() && !(d7 > d8)) {
                    if ((this.onGround() || flag && d7 <= d8) && this.noJumpDelay == 0) {
                        this.jumpFromGround();
                        this.noJumpDelay = 10;
                    }
                } else {
                    this.jumpInLiquid(FluidTags.LAVA);
                }
            } else {
                this.jumpInLiquid(FluidTags.WATER);
            }
        } else {
            this.noJumpDelay = 0;
        }

        this.level().getProfiler().pop();
        this.level().getProfiler().push("travel");
        this.xxa *= 0.98F;
        this.zza *= 0.98F;
        this.updateFallFlying();
        AABB axisalignedbb = this.getBoundingBox();
        this.travel(new Vec3((double)this.xxa, (double)this.yya, (double)this.zza));
        this.level().getProfiler().pop();
        this.level().getProfiler().push("push");
        if (this.autoSpinAttackTicks > 0) {
            --this.autoSpinAttackTicks;
            this.checkAutoSpinAttack(axisalignedbb, this.getBoundingBox());
        }

        this.pushEntities();
        this.level().getProfiler().pop();
        if (!this.level().isClientSide && this.isSensitiveToWater() && this.isInWaterRainOrBubble()) {
            this.hurt(this.damageSources().drown(), 1.0F);
        }

    }

    private void updateFallFlying() {
        boolean flag = this.getSharedFlag(7);
        if (flag && !this.onGround() && !this.isPassenger() && !this.hasEffect(MobEffects.LEVITATION)) {
            ItemStack itemstack = this.getItemBySlot(EquipmentSlot.CHEST);
            flag = itemstack.canElytraFly(this) && itemstack.elytraFlightTick(this, this.fallFlyTicks);
            if (false) //Forge: Moved to ElytraItem
                if (itemstack.getItem() == Items.ELYTRA && ElytraItem.isFlyEnabled(itemstack)) {
                    flag = true;
                    if (!this.level().isClientSide && (this.fallFlyTicks + 1) % 20 == 0) {
                        itemstack.hurtAndBreak(1, this, (p_233652_0_) -> {
                            p_233652_0_.broadcastBreakEvent(EquipmentSlot.CHEST);
                        });
                    }
                } else {
                    flag = false;
                }
        } else {
            flag = false;
        }

        if (!this.level().isClientSide) {
            this.setSharedFlag(7, flag);
        }

    }

    private int getCurrentSwingDuration() {
        return 20;
    }

    public void swing(InteractionHand p_226292_1_, boolean p_226292_2_) {
        ItemStack stack = this.getItemInHand(p_226292_1_);
        if (!stack.isEmpty() && stack.onEntitySwing(this)) return;
        if (!this.swinging || this.swingTime >= this.getCurrentSwingDuration() / 2 || this.swingTime < 0) {
            this.swingTime = -1;
            this.swinging = true;
            this.swingingArm = p_226292_1_;
            if (this.level() instanceof ServerLevel) {
                ClientboundAnimatePacket sanimatehandpacket = new ClientboundAnimatePacket(this, p_226292_1_ == InteractionHand.MAIN_HAND ? 0 : 3);
                ServerChunkCache serverchunkprovider = ((ServerLevel)this.level()).getChunkSource();
                if (p_226292_2_) {
                    serverchunkprovider.broadcastAndSend(this, sanimatehandpacket);
                } else {
                    serverchunkprovider.broadcast(this, sanimatehandpacket);
                }
            }
        }

    }

    protected void updateSwingTime() {
        int i = this.getCurrentSwingDuration();
        if (this.swinging) {
            ++this.swingTime;
            if (this.swingTime >= i) {
                this.swingTime = 0;
                this.swinging = false;
            }
        } else {
            this.swingTime = 0;
        }

        this.attackAnim = (float)this.swingTime / (float)i;
    }

    public boolean canAttackTarget(LivingEntity target) {
        if (this.cooldownTimer > 0) {
            return false;
        }
        this.cooldownTimer = 1000;
        return true;
    }

    public class NearestAttackableTargetGoalCooldown<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

        public NearestAttackableTargetGoalCooldown(LungfishEntity swordfish, Class<T> targetClass, boolean checkSight) {
            super(LungfishEntity.this, targetClass, checkSight);
        }

        @Override
        public boolean canUse() {
            if (LungfishEntity.this.cooldownTimer > 0) {
                return false;
            }
            if (LungfishEntity.this.getTarget() != null && !LungfishEntity.this.canAttackTarget(LungfishEntity.this.getTarget())) {
                return false;
            }
            return super.canUse();
        }

        public void start() {
            LungfishEntity.this.cooldownTimer = 1000;
            super.start();
        }
    }

    protected void handleAirSupply(int p_209207_1_) {
        this.setAirSupply(300);

    }

    private boolean onDiggableBlock(PathfinderMob entity) {
        BlockPos pos = entity.blockPosition().below();
        BlockState blockState = entity.level().getBlockState(pos);
        return DIGGABLES.contains(blockState.getBlock());
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
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

    static class LungfishMoveController extends MoveHelperController {

        public LungfishMoveController(FishBase p_i45831_1_) {
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
                this.fishbase.setYBodyRot(this.fishbase.getYRot());
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

    public int getActivityLevel() {
        return 1000;
    }

    static class LungfishWalking extends RandomStrollGoal {
        public LungfishWalking(LungfishEntity p_i1648_1_, double p_i1648_2_) {
            super(p_i1648_1_, p_i1648_2_, 120);
        }

        @Override
        public void tick() {
            if (this.mob instanceof LungfishEntity) {
                this.interval = ((LungfishEntity) this.mob).getActivityLevel();
            }
            super.tick();

        }

        public boolean canUse() {
            if (this.mob.level().isNight()) {
                this.interval = ((LungfishEntity)this.mob).getActivityLevel();
            }
            return super.canUse();
        }

    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.lungfish");
    }

    public int numVariants() {
        return 4;
    }

    public int getScaleforGUI() {
        return (int)(super.getScaleforGUI() *0.6f);
    }
}
