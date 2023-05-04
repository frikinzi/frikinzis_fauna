package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.PygmyGooseEntity;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;
import java.util.Random;

abstract public class AbstractWalkingCreature extends Animal {
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(AbstractWalkingCreature.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(AbstractWalkingCreature.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> HEIGHT_MULTIPLIER = SynchedEntityData.defineId(AbstractWalkingCreature.class, EntityDataSerializers.FLOAT);

    protected AbstractWalkingCreature(EntityType<? extends AbstractWalkingCreature> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_29389_, DifficultyInstance p_29390_, MobSpawnType p_29391_, @Nullable SpawnGroupData p_29392_, @Nullable CompoundTag p_29393_) {
        this.setVariant(this.determineVariant());
        this.setGender(this.random.nextInt(2));
        if (p_29392_ == null) {
            p_29392_ = new AgeableMob.AgeableMobGroupData(false);
        }

        float f = (float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
        this.setHeightMultiplier(f);

        return super.finalizeSpawn(p_29389_, p_29390_, p_29391_, p_29392_, p_29393_);
    }

    public int noVariants() {
        return 2;
    }

    public int determineVariant() {
        return this.random.nextInt(this.noVariants()) + 1;
    }


    public String getGenderDisplayName() {
        if (this.getGender() == 1) {
            Component i = new TranslatableComponent("gui.male");
            return i.getString();
        } else {
            Component i = new TranslatableComponent("gui.female");
            return i.getString();
        }
    }



    public int getVariant() {
        return Mth.clamp(this.entityData.get(DATA_VARIANT_ID), 1, this.noVariants());
    }

    public void setVariant(int p_29449_) {
        this.entityData.set(DATA_VARIANT_ID, p_29449_);
    }

    public int getGender() {
        return Mth.clamp(this.entityData.get(GENDER), 0, 2);
    }

    public void setGender(int p_29449_) {
        this.entityData.set(GENDER, p_29449_);
    }

    public float getHeightMultiplier() {
        return this.entityData.get(HEIGHT_MULTIPLIER);
    }

    public void setHeightMultiplier(float p_70606_1_) {
        this.entityData.set(HEIGHT_MULTIPLIER, Mth.clamp(p_70606_1_, 0.7F, 1.5F));
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


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
    }

    public void addAdditionalSaveData(CompoundTag p_29422_) {
        super.addAdditionalSaveData(p_29422_);
        p_29422_.putInt("Variant", this.getVariant());
        p_29422_.putFloat("HeightMultiplier", this.getHeightMultiplier());
        p_29422_.putInt("Gender", this.getGender());
    }

    public void readAdditionalSaveData(CompoundTag p_29402_) {
        super.readAdditionalSaveData(p_29402_);
        if (!p_29402_.contains("HeightMultiplier") || this.getHeightMultiplier() < 0.7F || this.getHeightMultiplier() > 1.5F) {
            this.setHeightMultiplier((float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get()));
        } else {
            this.setHeightMultiplier(p_29402_.getFloat("HeightMultiplier"));
        }
        this.setVariant(p_29402_.getInt("Variant"));
        this.setGender(p_29402_.getInt("Gender"));
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(Items.KELP);
    }

    public boolean isFood(ItemStack p_28177_) {
        return this.getBirdFood().test(p_28177_);
    }

    public float getSpawnEggOffspringHeight() {
        return (float)(this.getRandom().nextGaussian() * 0.05 + this.getHeightMultiplier());
    }

    public String getSpeciesName() {
        return this.getType().getDescription().getString();
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.KELP, 1);
    }

    public InteractionResult mobInteract(Player p_29414_, InteractionHand p_29415_) {
        ItemStack itemstack = p_29414_.getItemInHand(p_29415_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE.get()) {
            Creatures.PROXY.setReferencedMob(this);
            if (this.level.isClientSide) {
                Creatures.PROXY.openCreaturesGui();
            }
            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(p_29414_, p_29415_);
        }
    }

    public boolean canBeLeashed(Player p_30396_) {
        return false;
    }



}
