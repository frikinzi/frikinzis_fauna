package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.util.CreaturesLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Random;

public class ArapaimaEntity extends FishBase implements IAnimatable {
    private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(ArapaimaEntity.class, DataSerializers.INT);
    private AnimationFactory factory = new AnimationFactory(this);
    public ArapaimaEntity(EntityType<? extends ArapaimaEntity> p_i50246_1_, World p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        if (p_213386_3_ != SpawnReason.BUCKET) {
            this.setVariant(this.random.nextInt(3));
            float f = (float) (this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
            this.setHeightMultiplier(f);
        }
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
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, TroutEntity.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, ShrimpEntity.class, false));
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (!this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("flop", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    protected ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.ARAPAIMA_BUCKET);
    }

    protected void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundNBT compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
        compoundnbt.putInt("Age", this.getAge());
    }

    protected SoundEvent getAmbientSound() {
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

    public int getVariant() {
        return MathHelper.clamp(this.entityData.get(DATA_VARIANT_ID), 1, 4);
    }

    public void setVariant(int p_191997_1_) {
        this.entityData.set(DATA_VARIANT_ID, p_191997_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(DATA_ID_MOVING, false);
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setVariant(p_70037_1_.getInt("Variant"));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.ARAPAIMA;
    }

    public String getSpeciesName() {
        ITextComponent i = new TranslationTextComponent("entity.creatures.arapaima");
        return i.getString();
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.arapaima_hatch_chance.get()).floatValue();
    }

    public Item getFoodItem() {
        return Items.TROPICAL_FISH;
    }

    protected void layEgg(ServerWorld server, FishBase father) {
        int c = this.getClutchSize();
        for (int j = 0; j <= c; j++) {
            CreaturesRoeEntity egg = this.layEgg(this);
            if (egg != null) {
                FishBase mother;
                mother = this;

                egg.setParentUUID(mother.getUUID());

                float f = (float)(this.getRandom().nextGaussian() * 0.05 + ((this.getHeightMultiplier())));
                egg.setHeightMultiplier(f);

                int[] vars = {this.getVariant(), father.getVariant()};
                int rnd = new Random().nextInt(vars.length);
                egg.setVariant(vars[rnd]);

                int random = this.random.nextInt(20);
                if (random == 1) {
                    //leucistic variant chance
<<<<<<< Updated upstream
                    egg.setVariant(this.random.nextInt(3));
=======
                    egg.setVariant(this.random.nextInt(4));
>>>>>>> Stashed changes
                }

                Random rand = new Random();
                egg.setPos(MathHelper.floor(mother.getX()) + 0.5 + (-1+rand.nextFloat()*2), MathHelper.floor(mother.getY()) + 0.5, MathHelper.floor(mother.getZ()) + 0.5 + (-1+rand.nextFloat()*2));
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
        if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            server.addFreshEntity(new ExperienceOrbEntity(server, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
        }
    }

    public float getScale() {
        return this.isBaby() ? 0.2F : 1.0F;
    }

}
