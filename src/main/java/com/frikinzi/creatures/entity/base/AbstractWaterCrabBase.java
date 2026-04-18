package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.registry.CreaturesItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.util.List;

abstract public class AbstractWaterCrabBase extends WaterAnimal {
    private static final EntityDataAccessor<Float> HEIGHT_MULTIPLIER = SynchedEntityData.defineId(AbstractWaterCrabBase.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(AbstractWaterCrabBase.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(AbstractWaterCrabBase.class, EntityDataSerializers.INT);

    public AbstractWaterCrabBase(EntityType<? extends AbstractWaterCrabBase> type, Level level) {
        super(type, level);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        if (spawnData == null) {
            spawnData = new AgeableMob.AgeableMobGroupData(false);
        }
        if (spawnData instanceof CrabData crabData) {
            this.setVariant(crabData.variant);
        } else {
            int variant = Math.max(determineVariant(), 1);
            this.setVariant(this.random.nextInt(variant) + 1);
        }
        this.setGender(this.random.nextInt(2));

        float f = (float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
        this.setHeightMultiplier(f);

        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, tag);
    }

    public String getSpeciesName() {
        return this.getType().getDescription().getString();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getItem() == CreaturesItems.FF_GUIDE.get()) {
            if (this.level().isClientSide()) {
                Creatures.PROXY.setReferencedMob(this);
                Creatures.PROXY.openCreaturesGui();
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.DEAD_BUSH, 1);
    }

    public String getGenderString() {
        return this.getGender() == 1 ? "m" : "f";
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(GENDER, 0);
    }

    public float getHeightMultiplier() {
        return this.entityData.get(HEIGHT_MULTIPLIER);
    }

    public void setHeightMultiplier(float value) {
        if (this.getHeightMultiplier() < 0.7F) {
            this.entityData.set(HEIGHT_MULTIPLIER, 1.0F);
        } else {
            this.entityData.set(HEIGHT_MULTIPLIER, Mth.clamp(value, 0.7F, 1.5F));
        }
    }

    public String getHeightString() {
        if (this.getHeightMultiplier() >= 1.5)  return Component.translatable("gui.giant").getString();
        if (this.getHeightMultiplier() >= 1.4)  return Component.translatable("gui.huge").getString();
        if (this.getHeightMultiplier() >= 1.21) return Component.translatable("gui.large").getString();
        if (this.getHeightMultiplier() > 1.11)  return Component.translatable("gui.above_average").getString();
        if (this.getHeightMultiplier() >= 0.89) return Component.translatable("gui.average").getString();
        if (this.getHeightMultiplier() >= 0.79) return Component.translatable("gui.below_average").getString();
        return Component.translatable("gui.small").getString();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putFloat("HeightMultiplier", this.getHeightMultiplier());
        tag.putInt("Gender", this.getGender());
        tag.putInt("Variant", this.getVariant());
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(tag.getInt("Variant"));
        this.setGender(tag.getInt("Gender"));
        if (!tag.contains("HeightMultiplier") || this.getHeightMultiplier() < 0.7F || this.getHeightMultiplier() > 1.5F) {
            this.setHeightMultiplier((float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get()));
        } else {
            this.setHeightMultiplier(tag.getFloat("HeightMultiplier"));
        }
    }

    public int getGender() {
        return Mth.clamp(this.entityData.get(GENDER), 0, 2);
    }

    public void setGender(int value) {
        this.entityData.set(GENDER, value);
    }

    public void setVariant(int value) {
        this.entityData.set(DATA_VARIANT_ID, value);
    }

    public int getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    public int determineVariant() {
        return 1;
    }

    public Component getFunFact() {
        return Component.translatable("creatures.unknown");
    }

    public int getIUCNStatus() {
        return 0;
    }

    public int getIUCNColor() {
        if (this.getIUCNStatus() == 0) return 0x00AA00;
        if (this.getIUCNStatus() == 1) return 0xFFAA00;
        if (this.getIUCNStatus() == 2) return 0xFFAA00;
        if (this.getIUCNStatus() == 3) return 0xFF5555;
        if (this.getIUCNStatus() == 4) return 0xAA0000;
        if (this.getIUCNStatus() == 5) return 0xAA00AA;
        if (this.getIUCNStatus() == 6) return 0x000000;
        return 0xAAAAAA;
    }

    public Component getIUCNText() {
        if (this.getIUCNStatus() == 0) return Component.translatable("creatures.leastconcern");
        if (this.getIUCNStatus() == 1) return Component.translatable("creatures.nearthreatened");
        if (this.getIUCNStatus() == 2) return Component.translatable("creatures.vulnerable");
        if (this.getIUCNStatus() == 3) return Component.translatable("creatures.endangered");
        if (this.getIUCNStatus() == 4) return Component.translatable("creatures.criticallyendangered");
        if (this.getIUCNStatus() == 5) return Component.translatable("creatures.extinctinwild");
        if (this.getIUCNStatus() == 6) return Component.translatable("creatures.extinct");
        return Component.translatable("creatures.datadeficient");
    }

    public String getScientificName() {
        return "";
    }

    public Component getGenderText() {
        if (this.getGender() == 1) return Component.translatable("gui.male");
        return Component.translatable("gui.female");
    }

    public static class CrabData extends AgeableMob.AgeableMobGroupData {
        public final int variant;

        public CrabData(int variant) {
            super(true);
            this.variant = variant;
        }
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public Quaternionf getRotforGUI() {
        Quaternionf rot = new Quaternionf().rotateZ((float)Math.PI).rotateY((float)Math.toRadians(160));
        return rot;
    }

    public int getScaleforGUI() {
        float h = this.getBbHeight();
        int scale = (int)(20f / h);
        return scale;
    }

    public int methodOfDeterminingVariant() {
        return this.random.nextInt(this.determineVariant()) + 1;
    }

    public List<ItemStack> getAllFoodItems() {
        return List.of(getFoodItem());
    }

    public int getYOffsetForGUI() {
        return 0;
    }

}
