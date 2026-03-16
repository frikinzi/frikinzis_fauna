package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Map;

public class ParrotfishEntity extends FishBase implements GeoEntity {
    private static final EntityDataAccessor<Boolean> EATING = SynchedEntityData.defineId(ParrotfishEntity.class, EntityDataSerializers.BOOLEAN);

    private boolean isCrunching = false;
    private int eatTimer = 0;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.bumpheadparrotfish"))
            .put(2, Component.translatable("message.creatures.cetoscarusparrotfish"))
            .put(3, Component.translatable("message.creatures.cetoscarusparrotfish"))
            .put(4, Component.translatable("message.creatures.heavybeakparrotfish"))
            .put(5, Component.translatable("message.creatures.bluntheadparrotfish"))
            .put(6, Component.translatable("message.creatures.blueparrotfish"))
            .put(7, Component.translatable("message.creatures.knobsnoutparrotfish"))
            .put(8, Component.translatable("message.creatures.midnightparrotfish2"))
            .put(9, Component.translatable("message.creatures.bumpheadparrotfish2"))
            .put(10, Component.translatable("message.creatures.midnightparrotfish3"))
            .put(11, Component.translatable("message.creatures.rainbowparrotfish"))
            .put(12, Component.translatable("message.creatures.singaporeparrotfish"))
            .build();

    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Bolbometopon muricatum")
            .put(2, "Cetoscarus bicolor")
            .put(3, "Cetoscarus bicolor")
            .put(4, "Chlorurus gibbus")
            .put(5, "Chlorurus microrhinos")
            .put(6, "Scarus coeruleus")
            .put(7, "Scarus ovifrons")
            .put(8, "Scarus coelestinus")
            .put(9, "Bolbometopon muricatum")
            .put(10, "Scarus coelestinus")
            .put(11, "Scarus guacamaia")
            .put(12, "Scarus prasiognathos")
            .build();

    public ParrotfishEntity(EntityType<? extends ParrotfishEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected <E extends ParrotfishEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (this.isEating()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("crunch"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Swimming", 0, this::swimAnimController));
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("Eating", this.isEating());
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setEating(p_70037_1_.getBoolean("Eating"));
    }

@Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(2, new EatCoralGoal(this));
    }

    public void aiStep() {
        if (!this.level().isClientSide) {
            if (this.eatTimer > 0) {
                this.playAmbientSound();
                this.spawnParticles();
                this.eatTimer--;
            } else {
                this.setEating(false);
            }
        }
        super.aiStep();
    }

    protected void spawnParticles() {
        ItemParticleOption iparticledata = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.BRAIN_CORAL.asItem()));

        for(int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            ((ServerLevel)this.level()).addParticle(iparticledata, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
        }

    }

    public int getMaxSchoolSize() {
        return 10;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.PARROTFISH_BUCKET.get());
    }

//    public void saveToBucketTag(ItemStack p_204211_1_) {
//        super.saveToBucketTag(p_204211_1_);
//        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
//        compoundnbt.putInt("BucketVariantTag", this.getVariant());
//        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
//        compoundnbt.putInt("Age", this.getAge());
//    }

    protected SoundEvent getAmbientSound() {
        if (this.isCrunching) {
            return SoundEvents.GENERIC_EAT;
        }
        return SoundEvents.SALMON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    public boolean isEating() {
        return this.entityData.get(EATING);
    }

    public void setEating(boolean p_70606_1_) {
        this.entityData.set(EATING, p_70606_1_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.06D);
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public float getHatchChance() {
        return CreaturesConfig.parrotfish_hatch_chance.get().floatValue();
    }

    public Item getFoodItem() {
        return CreaturesItems.ALGAE_WAFER.get();
    }

    public double getMoveSpeed() {
        return 1.2D;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EATING, false);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.PARROTFISH;
    }

    static class EatCoralGoal extends MoveToBlockGoal {
        private final ParrotfishEntity parrotfish;
        private boolean wantsToEat;
        private int eatTimer = 0;
        private boolean canEat;

        public EatCoralGoal(ParrotfishEntity p_i45860_1_) {
            super(p_i45860_1_, (double)1.0F, 16, 16);
            this.parrotfish = p_i45860_1_;
        }

        public boolean canUse() {
            if (this.parrotfish.isBaby()) {
                return false;
            }
            return super.canUse();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }

        public void stop() {
            super.stop();
        }

        public void tick() {
            super.tick();
            this.parrotfish.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.parrotfish.getMaxHeadXRot());
            if (this.isReachedTarget()) {
                this.parrotfish.eatTimer = 500;
                this.parrotfish.setEating(true);
            }

        }

        protected boolean isValidTarget(LevelReader p_179488_1_, BlockPos p_179488_2_) {
            return p_179488_1_.isWaterAt(p_179488_2_.above()) && (p_179488_1_.getBlockState(p_179488_2_).is(BlockTags.CORAL_BLOCKS) || p_179488_1_.getBlockState(p_179488_2_).is(BlockTags.CORAL_PLANTS));        }
    }

    public String getGenderName() {
        if (this.getGender() == 0) {
            return "f";
        } return "m";
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public static class ParrotfishData extends AbstractSchoolingFish.SchoolSpawnGroupData {
        public final int variant;

        public ParrotfishData(AbstractSchoolingFish leader, int variant) {
            super(leader);
            this.variant = variant;
        }
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.parrotfish");
    }

    public int numVariants() {
        return 12;
    }
}
