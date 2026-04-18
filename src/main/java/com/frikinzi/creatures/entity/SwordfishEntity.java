package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.animal.Squid;
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

public class SwordfishEntity extends FishBase implements GeoEntity {
    //private static final EntityDataAccessor<Integer> VARIANT_SUBID = SynchedEntityData.defineId(SwordfishEntity.class, EntityDataSerializers.INT);
    private static final UUID SPEED_BOOST_UUID = UUID.randomUUID();
    private int cooldownTimer = 0;
    private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_BOOST_UUID, "Aggressive speed boost", 0.5D, AttributeModifier.Operation.ADDITION);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static Map<Integer, String[]> SWORDFISH = ImmutableMap.<Integer, String[]>builder()
            .put(1, new String[]{"a", "b", "c", "d"})
            .put(2, new String[]{"a", "b"})
            .put(3, new String[]{"a", "b", "c"})
            .put(4, new String[]{"a", "b"})
            .put(5, new String[]{"a", "b", "c", "d"})
            .put(6, new String[]{""})
            .put(7, new String[]{"a", "b"})
            .put(8, new String[]{""})
            .put(9, new String[]{""})
            .build();
    public static Map<Integer, Float> SIZES = ImmutableMap.<Integer, Float>builder()
            .put(1, 0.9f)
            .put(2, 0.9f)
            .put(3, 0.9f)
            .put(4, 1.0f)
            .put(5, 1.0f)
            .put(6, 0.7f)
            .put(7, 1.0f)
            .put(8, 0.9f)
            .put(9, 0.7f)
            .build();
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.atlanticsailfish"))
            .put(2, Component.translatable("message.creatures.indopacificsailfish"))
            .put(3, Component.translatable("message.creatures.swordfish"))
            .put(4, Component.translatable("message.creatures.blackmarlin"))
            .put(5, Component.translatable("message.creatures.bluemarlin"))
            .put(6, Component.translatable("message.creatures.whitemarlin"))
            .put(7, Component.translatable("message.creatures.stripedmarlin"))
            .put(8, Component.translatable("message.creatures.longbillspearfish"))
            .put(9, Component.translatable("message.creatures.shortbillspearfish"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Istiophorus albicans")
            .put(2, "Istiophorus platypterus")
            .put(3, "Xiphias gladius")
            .put(4, "Istiompax indica")
            .put(5, "Makaira nigricans")
            .put(6, "Tetrapterus albida")
            .put(7, "Tetrapturus audax")
            .put(8, "Tetrapturus pfluegeri")
            .put(9, "Tetrapturus angustirostris")
            .build();

    public SwordfishEntity(EntityType<? extends SwordfishEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected <E extends SwordfishEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (this.swinging && !this.isBaby()){
            return event.setAndContinue(RawAnimation.begin().thenPlayAndHold("attack"));
        }
        if (!this.isInWater() && !this.isBaby()) {
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

//    public int getSubVariant() {
//        return Mth.clamp(this.entityData.get(VARIANT_SUBID), 0, SWORDFISH.get(this.getVariant()).length-1);
//    }

    @Override
    public int getSubVariant() {
        String[] subVariants = SWORDFISH.get(this.getVariant());
        if (subVariants == null || subVariants.length == 0) return 0;
        return Mth.clamp(super.getSubVariant(), 0, subVariants.length - 1);
    }
//
//    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
//        super.addAdditionalSaveData(p_213281_1_);
//        p_213281_1_.putInt("Subvariant", this.getSubVariant());
//
//    }
//
//    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
//        super.readAdditionalSaveData(p_70037_1_);
//        this.setSubVariant(p_70037_1_.getInt("Subvariant"));
//    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.ATTACK_SPEED, 2);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SWORDFISH;
    }

    public float getHatchChance() {
        return CreaturesConfig.swordfish_hatch_chance.get().floatValue();
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
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoalCooldown<>(this, Squid.class, false));

    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
            return InteractionResult.PASS;
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    public String getTextureString() {
        if (this.isSexuallyDimorphic()) {
            return this.getVariant() + SWORDFISH.get(this.getVariant())[this.getSubVariant()] + this.getGenderString();
        }
        return this.getVariant() + SWORDFISH.get(this.getVariant())[this.getSubVariant()];
    }

    public boolean isSexuallyDimorphic() {
        if (this.getVariant() == 4 || this.getVariant() == 5) {
            return true;
        }
        return false;
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

    public float getSizeMultiplier() {
        Float size = SIZES.get(this.getVariant());
        if (size == null) size = 1.0f;

        float multiplier = size;
        if (this.getGender() == 0 && (this.getVariant() == 4 || this.getVariant() == 5 || this.getVariant() == 6)) {
            multiplier = multiplier * 1.5f;
        }
        return multiplier;
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

    public class NearestAttackableTargetGoalCooldown<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

        public NearestAttackableTargetGoalCooldown(SwordfishEntity swordfish, Class<T> targetClass, boolean checkSight) {
            super(SwordfishEntity.this, targetClass, checkSight);
        }

        @Override
        public boolean canUse() {
            if (SwordfishEntity.this.cooldownTimer > 0) {
                return false;
            }
            if (SwordfishEntity.this.getTarget() != null && !SwordfishEntity.this.canAttackTarget(SwordfishEntity.this.getTarget())) {
                return false;
            }
            return super.canUse();
        }

        public void start() {
            SwordfishEntity.this.cooldownTimer = 1000;
            super.start();
        }
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 1 || this.getVariant() == 2 || this.getVariant() == 5) {
            return 2;
        } if (this.getVariant() == 3) {
            return 1;
        } if (this.getVariant() == 4 || this.getVariant() == 9) {
            return -1;
        }
        return super.getIUCNStatus();
    }
    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public int getBabyVariant() {
        if (this.getVariant() == 1 || this.getVariant() == 2) {
            return 1;
        } if (this.getVariant() == 3) {
            return 3;
        } return 2;
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public int methodOfDeterminingSubVariant() {
        int tmp = this.random.nextInt(SWORDFISH.get(this.getVariant()).length);
        if (this.getVariant() == 1 || this.getVariant() == 5) {
                if (this.random.nextInt(CreaturesConfig.swordfish_mutation_chance.get()) == 1) {
                    tmp = this.random.nextInt(SWORDFISH.get(this.getVariant()).length);
                } else {
                    tmp = this.random.nextInt(SWORDFISH.get(this.getVariant()).length-1);
                }

        }
        return tmp;
    }

    public Component getFunFact() {
        if (this.getVariant() == 2) {
            return Component.translatable("description.creatures.indopacificsailfish");
        }
        return Component.translatable("description.creatures.billfish");
    }

    public int numVariants() {
        return 9;
    }

    @Override
    public int getSubVariantBasedOnVariant(int variant) {
        String[] subVariants = SWORDFISH.get(variant);
        if (subVariants == null || subVariants.length == 0) return 0;
        return this.random.nextInt(subVariants.length);
    }

    public int getScaleforGUI() {
        if (this.isBaby()) {
            return (int)(super.getScaleforGUI() *3f);

        }
        if (this.getGender() == 0) {
            return (int)(super.getScaleforGUI() *0.6f);
        }
        return (int)(super.getScaleforGUI() *0.8f);

    }
}
