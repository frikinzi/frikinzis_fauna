package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.StayCloseToEggGoal;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.ModEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

abstract public class CreaturesBirdEntity extends TameableEntity {
    World world;
    CreaturesBirdEntity mate;

    private static final DataParameter<Float> HEIGHT_MULTIPLIER = EntityDataManager.defineId(CreaturesBirdEntity.class, DataSerializers.FLOAT);
    protected static final DataParameter<Optional<UUID>> DATA_MATEUUID_ID = EntityDataManager.defineId(CreaturesBirdEntity.class, DataSerializers.OPTIONAL_UUID);

    public CreaturesBirdEntity(EntityType<? extends CreaturesBirdEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        mate = null;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new StayCloseToEggGoal(this, 1.0D));
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        if (p_213386_4_ == null) {
            p_213386_4_ = new AgeableData(false);
        }

        if (this.random.nextFloat() <= 0.05) {
            this.setBaby(true);
        }

        float f = (float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
        this.setHeightMultiplier(f);

        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
        this.entityData.define(DATA_MATEUUID_ID, Optional.empty());
    }

    public CreaturesBirdEntity getMate() {
        return this.mate;
    }

    public void setMate(CreaturesBirdEntity bird) {
        this.mate = bird;
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

    public boolean isMonogamous() {
        return false;
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


    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        p_213281_1_.putFloat("HeightMultiplier", this.getHeightMultiplier());
        if (this.getMateUUID() != null) {
            p_213281_1_.putUUID("Mate", this.getMateUUID());
        }
        super.addAdditionalSaveData(p_213281_1_);
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        if (!p_70037_1_.contains("HeightMultiplier") || this.getHeightMultiplier() < 0.7F || this.getHeightMultiplier() > 1.5F) {
            this.setHeightMultiplier((float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get()));
        } else {
        this.setHeightMultiplier(p_70037_1_.getFloat("HeightMultiplier")); }
        if (p_70037_1_.hasUUID("Mate")) {
            uuid = p_70037_1_.getUUID("Mate");
            if (uuid != null) {
                try {
                    this.setMateUUID(uuid);
                } catch (Throwable throwable) {
                }
            }
        }
    }

    @Nullable
    public UUID getMateUUID() {
        return this.entityData.get(DATA_MATEUUID_ID).orElse((UUID)null);
    }

    public void setMateUUID(@Nullable UUID p_184754_1_) {
        this.entityData.set(DATA_MATEUUID_ID, Optional.ofNullable(p_184754_1_));
    }

    public CreaturesEggEntity layEgg(CreaturesBirdEntity animal) {
        CreaturesEggEntity egg = new CreaturesEggEntity(ModEntityTypes.EGG.get(), this.level);
        egg.setSpecies(ModEntityTypes.getIntFromBirdEntity(animal));
        egg.setGender(this.random.nextInt(2));
        egg.setVariant(this.getVariant());
        egg.setPos(MathHelper.floor(this.getX()) + 0.5, MathHelper.floor(this.getY()) + 0.5, MathHelper.floor(this.getZ()) + 0.5);
        return egg;
    }

    public int getVariant() {
        return 0;
    }

    public float getHatchChance() {
        return 1;
    }

    public int getClutchSize() {
        return 1;
    }

    public int methodofDeterminingVariant(IWorld p_213610_1_) {
        return 1;
    }

    public int getGender() {
        return 1;
    }

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE) {
            //System.out.println(this.getUUID());
            if (this.level.isClientSide) {
                if (itemstack.getTag() == null) {
                    itemstack.getOrCreateTag();
                }
                String s = String.valueOf(ModEntityTypes.getIntFromBirdEntity(this));
                if (!itemstack.getTag().contains(s)) {
                    if (this.determineVariant() == 1) {
                        itemstack.getTag().putIntArray(s, new int[1]);
                    } else {
                        itemstack.getTag().putIntArray(s, new int[this.determineVariant()-1]);
                    }
                }
                try {
                    itemstack.getTag().getIntArray(s)[this.getVariant()-1] = this.getVariant();
                } catch (Exception e) {
                    itemstack.getTag().getIntArray(s)[0] = 1;
                }

                System.out.println(Arrays.toString(itemstack.getTag().getIntArray(s)));

                return ActionResultType.SUCCESS;
            }
        } return super.mobInteract(p_230254_1_, p_230254_2_);
    }

    public float getSpawnEggOffspringHeight() {
        return (float)(this.getRandom().nextGaussian() * 0.05 + this.getHeightMultiplier());
    }

    public class DefendBabyGoal extends NearestAttackableTargetGoal<LivingEntity> {
        public DefendBabyGoal() {
            super(CreaturesBirdEntity.this, LivingEntity.class, 5, true, true, (Predicate<LivingEntity>)LivingEntity::attackable);
        }

        public boolean canUse() {
            if (!CreaturesBirdEntity.this.isBaby() && !CreaturesBirdEntity.this.isTame()) {
                if (super.canUse()) {
                    for (CreaturesBirdEntity birdEntity : CreaturesBirdEntity.this.level.getEntitiesOfClass(CreaturesBirdEntity.class, CreaturesBirdEntity.this.getBoundingBox().inflate(4.0D, 4.0D, 4.0D))) {
                        if (birdEntity.isBaby()) {

                            if (this.target.getClass() == CreaturesBirdEntity.this.getClass() || this.target.getClass() == CreaturesEggEntity.class || this.target.isBaby()) {
                                return false;
                            }
                            //System.out.println(CreaturesBirdEntity.this.getName() + "defend target");
                            return true;
                        }
                    }
                    for (CreaturesEggEntity eggEntity : CreaturesBirdEntity.this.level.getEntitiesOfClass(CreaturesEggEntity.class, CreaturesBirdEntity.this.getBoundingBox().inflate(3.0D, 2.0D, 3.0D))) {
                        if (eggEntity.getSpecies() == ModEntityTypes.getIntFromBirdEntity(CreaturesBirdEntity.this)) {
                            if (this.target.getClass() == CreaturesBirdEntity.this.getClass() || this.target.isBaby() || this.target.getClass() == CreaturesEggEntity.class) {
                                return false;
                            }
                            //System.out.println("defend egg");
                            return true;
                        }
                    }
                }

            }
            return false;
        }

        protected double getFollowDistance() {
            return super.getFollowDistance() * 0.1D;
        }
    }

    class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal {
        public HurtByTargetGoal() {
            super(CreaturesBirdEntity.this);
        }

        public void start() {
            super.start();
            if (CreaturesBirdEntity.this.isBaby()) {
                this.alertOthers();
                this.stop();
            }

        }

        protected void alertOther(MobEntity p_220793_1_, LivingEntity p_220793_2_) {
            if (p_220793_1_.getClass() == CreaturesBirdEntity.this.getClass() && !p_220793_1_.isBaby()) {
                super.alertOther(p_220793_1_, p_220793_2_);
            }

        }


    }

    public int determineVariant() {
        return 1;
    }
}
