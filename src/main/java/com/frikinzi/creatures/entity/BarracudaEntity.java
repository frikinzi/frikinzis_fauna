package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Map;
import java.util.UUID;

public class BarracudaEntity extends FishBase implements GeoEntity {
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.greatbarracuda"))
            .put(3, Component.translatable("message.creatures.guineanbarracuda"))
            .put(2, Component.translatable("message.creatures.greatbarracuda"))

            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Sphyraena barracuda")
            .put(2, "Sphyraena barracuda")
            .put(3, "Sphyraena afra")
            .build();
    private static final UUID SPEED_BOOST_UUID = UUID.randomUUID();
    private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_BOOST_UUID, "Aggressive speed boost", 0.5D, AttributeModifier.Operation.ADDITION);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int cooldownTimer = 0;

    public BarracudaEntity(EntityType<? extends BarracudaEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.ATTACK_SPEED, 2);
    }

    protected <E extends BarracudaEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (this.swinging){
            return event.setAndContinue(RawAnimation.begin().thenPlayAndHold("attack"));
        }
        if (!this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("flop"));
        }
        if (this.isAggressive()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fast"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
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
        return new ItemStack(CreaturesItems.TAMBAQUI_BUCKET.get());
    }

    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
        compoundnbt.putInt("Age", this.getAge());
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SWORDFISH;
    }

    public float getHatchChance() {
        return CreaturesConfig.barracuda_hatch_chance.get().floatValue();
    }

    public Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2.0D, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoalCooldown<>(this, RedSnapperEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoalCooldown<>(this, Cod.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoalCooldown<>(this, TropicalFish.class, false));

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
        this.updateSwingTime();
        if (cooldownTimer > 0) {
            cooldownTimer--;
        }
        super.aiStep();
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

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public int numVariants() {
        return 3;
    }

    public class NearestAttackableTargetGoalCooldown<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

        public NearestAttackableTargetGoalCooldown(BarracudaEntity swordfish, Class<T> targetClass, boolean checkSight) {
            super(BarracudaEntity.this, targetClass, checkSight);
        }

        @Override
        public boolean canUse() {
            if (BarracudaEntity.this.cooldownTimer > 0) {
                return false;
            }
            if (BarracudaEntity.this.getTarget() != null && !BarracudaEntity.this.canAttackTarget(BarracudaEntity.this.getTarget())) {
                return false;
            }
            return super.canUse();
        }

        public void start() {
            BarracudaEntity.this.cooldownTimer = 1000;
            System.out.println(BarracudaEntity.this.cooldownTimer);
            super.start();
        }
    }
}
