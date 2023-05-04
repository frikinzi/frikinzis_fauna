package com.frikinzi.creatures.entity.egg;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.AbstractCreaturesFish;
import com.frikinzi.creatures.util.ModEventSubscriber;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class RoeEntity extends AgeableMob implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(RoeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(RoeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SPECIES = SynchedEntityData.defineId(RoeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> HEIGHT_MULTIPLIER = SynchedEntityData.defineId(RoeEntity.class, EntityDataSerializers.FLOAT);
    public int hatchTime = this.random.nextInt(CreaturesConfig.base_egg_hatch_time.get()) + CreaturesConfig.base_egg_hatch_time.get();

    public RoeEntity(EntityType<? extends RoeEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_29389_, DifficultyInstance p_29390_, MobSpawnType p_29391_, @Nullable SpawnGroupData p_29392_, @Nullable CompoundTag p_29393_) {
        if (p_29393_ != null) {
            //System.out.print(p_213386_5_);
            if (p_29393_.contains("EggVariant", 3)) {
                this.setVariant(p_29393_.getInt("EggVariant"));
                this.setGender(this.random.nextInt(2)); }
            if (p_29393_.contains("EggHeightMultiplier", 4)) {
                this.setHeightMultiplier(p_29393_.getFloat("EggHeightMultiplier"));
            }
        }

        return super.finalizeSpawn(p_29389_, p_29390_, p_29391_, p_29392_, p_29393_);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if ((this.hatchTime < 100)) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("hatch", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        return null;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<RoeEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel p_148993_, AgeableMob p_148994_) {
        return null;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(SPECIES, 0);
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
    }

    public void addAdditionalSaveData(CompoundTag p_29422_) {
        super.addAdditionalSaveData(p_29422_);
        p_29422_.putInt("Variant", this.getVariant());
        p_29422_.putFloat("HeightMultiplier", this.getVariant());
        p_29422_.putInt("Gender", this.getGender());
        p_29422_.putInt("Species", this.getSpecies());
    }

    public void readAdditionalSaveData(CompoundTag p_29402_) {
        super.readAdditionalSaveData(p_29402_);
        if (!p_29402_.contains("HeightMultiplier") || this.getHeightMultiplier() < 0.7F || this.getHeightMultiplier() > 1.5F) {
            this.setHeightMultiplier((float)(this.random.nextGaussian() * 0.1 + 1.0));
        } else {
            this.setHeightMultiplier(p_29402_.getFloat("HeightMultiplier"));
        }
        this.setVariant(p_29402_.getInt("Variant"));
        this.setGender(p_29402_.getInt("Gender"));
        this.setSpecies(p_29402_.getInt("Species"));
    }


    public int getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
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

    public int getSpecies() {
        return this.entityData.get(SPECIES);
    }

    public void setSpecies(int p_29449_) {
        this.entityData.set(SPECIES, p_29449_);
    }

    public float getHeightMultiplier() {
        return this.entityData.get(HEIGHT_MULTIPLIER);
    }

    public void setHeightMultiplier(float p_70606_1_) {
        this.entityData.set(HEIGHT_MULTIPLIER, Mth.clamp(p_70606_1_, 0.7F, 1.5F));
    }

    public void hatchEgg(RoeEntity egg) {
        if (--egg.hatchTime <= 0) {
            if (!this.level.isClientSide) {
                int species = egg.getSpecies();
                EntityType<? extends AbstractCreaturesFish> type = ModEventSubscriber.getFishEntityMap().get(species);
                if (type != null) {
                    AbstractCreaturesFish bird = createBirdEntity(type, egg);

                    if (this.random.nextFloat() < bird.getHatchChance()) {
                        this.level.addFreshEntity(bird);
                    }
                } else {
                    System.out.println(egg.getSpecies());
                }

                egg.remove(RemovalReason.DISCARDED);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        hatchEgg(this);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D);
    }

    // Forge Start
    //protected EntityType<?> getBirdType() {
    //    return entityTypeSupplier.get();
    //}

    protected void saveToEggTag(ItemStack p_204211_1_) {
        if (this.hasCustomName()) {
            p_204211_1_.setHoverName(this.getCustomName());
        }
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("EggVariant", this.getVariant());
        //CompoundNBT compound = p_204211_1_.getOrCreateTagElement("EggHeightMultiplier");
        compoundnbt.putFloat("EggHeightMultiplier", this.getHeightMultiplier());

    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE.get()) {
            //System.out.println(this.getParentUUID());
            Creatures.PROXY.setReferencedMob(this);
            if (this.level.isClientSide) {
                Creatures.PROXY.openCreaturesGui();
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }

    private AbstractCreaturesFish createBirdEntity(EntityType<? extends AbstractCreaturesFish> entityType, RoeEntity egg) {
        AbstractCreaturesFish bird = entityType.create(egg.level);
        if (egg.hasCustomName()) {
            bird.setCustomName(egg.getCustomName());
        }
        bird.setVariant(egg.getVariant());
        bird.setHeightMultiplier(this.getHeightMultiplier());
        bird.setBaby(true);
        bird.setPos(egg.getX(), egg.getY(), egg.getZ());
        return bird;
    }

}
