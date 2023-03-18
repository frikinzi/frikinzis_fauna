package com.frikinzi.creatures.entity.egg;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.registry.ModEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class CreaturesRoeEntity extends WaterMobEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Integer> SPECIES_ID = EntityDataManager.defineId(CreaturesRoeEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(CreaturesRoeEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.defineId(CreaturesRoeEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> HATCHING = EntityDataManager.defineId(CreaturesRoeEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Float> HEIGHT_MULTIPLIER = EntityDataManager.defineId(CreaturesRoeEntity.class, DataSerializers.FLOAT);
    protected static final DataParameter<Optional<UUID>> DATA_PARENTUUID_ID = EntityDataManager.defineId(CreaturesRoeEntity.class, DataSerializers.OPTIONAL_UUID);
    public int hatchTime = this.random.nextInt(CreaturesConfig.base_egg_hatch_time.get()) + CreaturesConfig.base_egg_hatch_time.get();
    private CreaturesBirdEntity parent;

    public CreaturesRoeEntity(EntityType<? extends CreaturesRoeEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        parent = null;
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        if (p_213386_5_ != null) {
            //System.out.print(p_213386_5_);
            if (p_213386_5_.contains("EggVariant", 3)) {
            this.setVariant(p_213386_5_.getInt("EggVariant"));
            this.setGender(this.random.nextInt(2)); }
            if (p_213386_5_.contains("EggHeightMultiplier", 4)) {
                this.setHeightMultiplier(p_213386_5_.getFloat("EggHeightMultiplier"));
            }
        }
        return p_213386_4_;
    }

    public void setParent(CreaturesBirdEntity parent) {
        this.parent = parent;
    }

    public CreaturesBirdEntity getParent() {
        return this.parent;
    }

    protected void registerGoals() {
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        return null;
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

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D);
    }

    @Override
    public void tick() {
        super.tick();
        hatchEgg(this);
    }

    public int getVariant() {
        return MathHelper.clamp(this.entityData.get(DATA_VARIANT_ID), 1, 20);
    }
    public void setVariant(int p_191997_1_) {
        this.entityData.set(DATA_VARIANT_ID, p_191997_1_);
    }

    public int getGender() {
        return MathHelper.clamp(this.entityData.get(GENDER), 0, 2);
    }

    public void setGender(int p_191997_1_) {
        this.entityData.set(GENDER, p_191997_1_);
    }

    public int getSpecies() {
        return MathHelper.clamp(this.entityData.get(SPECIES_ID), 0, 1000);
    }

    public void setSpecies(int p_191997_1_) {
        this.entityData.set(SPECIES_ID, p_191997_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(SPECIES_ID, 0);
        this.entityData.define(HATCHING, false);
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
        this.entityData.define(DATA_PARENTUUID_ID, Optional.empty());
    }


    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("Hatching", this.isHatching());
        p_213281_1_.putInt("Variant", this.getVariant());
        p_213281_1_.putInt("Species", this.getSpecies());
        p_213281_1_.putInt("Gender", this.getGender());
        p_213281_1_.putFloat("HeightMultiplier", this.getHeightMultiplier());
        if (this.getParentUUID() != null) {
            p_213281_1_.putUUID("Parent", this.getParentUUID());
        }
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setHatching(p_70037_1_.getBoolean("Hatching"));
        this.setVariant(p_70037_1_.getInt("Variant"));
        this.setSpecies(p_70037_1_.getInt("Species"));
        this.setGender(p_70037_1_.getInt("Gender"));
        this.setHeightMultiplier(p_70037_1_.getFloat("HeightMultiplier"));
        UUID uuid;
        if (p_70037_1_.hasUUID("Parent")) {
            uuid = p_70037_1_.getUUID("Parent");
            this.setParentUUID(uuid);
        }

    }

    public float getHeightMultiplier() {
        return this.entityData.get(HEIGHT_MULTIPLIER);
    }

    public void setHeightMultiplier(float p_70606_1_) {
        this.entityData.set(HEIGHT_MULTIPLIER, MathHelper.clamp(p_70606_1_, 0.5F, 2F));
    }

    public boolean isHatching() {
        return this.entityData.get(HATCHING);
    }

    private void setHatching(boolean p_213485_1_) {
        this.entityData.set(HATCHING, p_213485_1_);
    }

    @Nullable
    public UUID getParentUUID() {
        return this.entityData.get(DATA_PARENTUUID_ID).orElse((UUID)null);
    }

    public void setParentUUID(@Nullable UUID p_184754_1_) {
        this.entityData.set(DATA_PARENTUUID_ID, Optional.ofNullable(p_184754_1_));
    }

    public void hatchEgg(CreaturesRoeEntity egg) {
        if (--egg.hatchTime <= 0) {
            if (egg.getSpecies() == 0 & !this.level.isClientSide) {
                KoiEntity fish = new KoiEntity(ModEntityTypes.KOI.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                this.level.addFreshEntity(fish);
                }
                egg.remove();
            }
            if (egg.getSpecies() == 1 & !this.level.isClientSide) {
                DottybackEntity fish = new DottybackEntity(ModEntityTypes.DOTTYBACK.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                //System.out.println(spoonbillEntity);
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);

                if (this.random.nextFloat() < fish.getHatchChance()) {
                this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 2 & !this.level.isClientSide) {
                PikeEntity fish = new PikeEntity(ModEntityTypes.PIKE.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                //kakapoEntity.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                //System.out.println(spoonbillEntity);
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 3 & !this.level.isClientSide) {
                ShrimpEntity fish = new ShrimpEntity(ModEntityTypes.SHRIMP.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                //System.out.println(spoonbillEntity);
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 4 & !this.level.isClientSide) {
                GuppyEntity fish = new GuppyEntity(ModEntityTypes.GUPPY.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                //System.out.println(spoonbillEntity);
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 5 & !this.level.isClientSide) {
                GouramiEntity fish = new GouramiEntity(ModEntityTypes.GOURAMI.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 6 & !this.level.isClientSide) {
                ArowanaEntity fish = new ArowanaEntity(ModEntityTypes.AROWANA.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 7 & !this.level.isClientSide) {
                GoldfishEntity fish = new GoldfishEntity(ModEntityTypes.GOLDFISH.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 8 & !this.level.isClientSide) {
                RanchuEntity fish = new RanchuEntity(ModEntityTypes.RANCHU.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 9 & !this.level.isClientSide) {
                FireGobyEntity fish = new FireGobyEntity(ModEntityTypes.FIRE_GOBY.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 10 & !this.level.isClientSide) {
                BlueTangEntity fish = new BlueTangEntity(ModEntityTypes.BLUE_TANG.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 11 & !this.level.isClientSide) {
                FlameAngelfishEntity fish = new FlameAngelfishEntity(ModEntityTypes.FLAME_ANGELFISH.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 12 & !this.level.isClientSide) {
                TroutEntity fish = new TroutEntity(ModEntityTypes.TROUT.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(this.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 13 & !this.level.isClientSide) {
                TigerBarbEntity fish = new TigerBarbEntity(ModEntityTypes.TIGERBARB.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBred(true);
                fish.setBaby(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 14 & !this.level.isClientSide) {
                RedSnapperEntity fish = new RedSnapperEntity(ModEntityTypes.RED_SNAPPER.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(egg.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBaby(true);
                fish.setBred(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 15 & !this.level.isClientSide) {
                ArapaimaEntity fish = new ArapaimaEntity(ModEntityTypes.ARAPAIMA.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBaby(true);
                fish.setBred(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            if (egg.getSpecies() == 16 & !this.level.isClientSide) {
                PiranhaEntity fish = new PiranhaEntity(ModEntityTypes.PIRANHA.get(), egg.level);

                if (egg.hasCustomName()) {
                    fish.setCustomName(egg.getCustomName());
                }
                fish.setVariant(this.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBaby(true);
                fish.setBred(true);
                if (this.random.nextFloat() < fish.getHatchChance()) {
                    this.level.addFreshEntity(fish); }
                egg.remove();
            }
            this.level.broadcastEntityEvent(this, (byte)3);
        }
    }
    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE) {
            System.out.println(this.getParentUUID());
            Creatures.PROXY.setReferencedMob(this);
            if (this.level.isClientSide) {
                Creatures.PROXY.openCreaturesGUI(itemstack);
                return ActionResultType.SUCCESS;
            }
        }
            return super.mobInteract(p_230254_1_, p_230254_2_);
    }

    protected void saveToEggTag(ItemStack p_204211_1_) {
        if (this.hasCustomName()) {
            p_204211_1_.setHoverName(this.getCustomName());
        }
        CompoundNBT compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("EggVariant", this.getVariant());
        //CompoundNBT compound = p_204211_1_.getOrCreateTagElement("EggHeightMultiplier");
        compoundnbt.putFloat("EggHeightMultiplier", this.getHeightMultiplier());
        System.out.println(compoundnbt);

    }

    public boolean isPushable() {
        return false;
    }

    public int getHatchTime() {
        return (this.hatchTime / 1200);
    }

    protected SoundEvent getAmbientSound() {
        if (this.isHatching()) {
            return CreaturesSound.EGG_HATCH; } else {
            return null;
        }
    }

    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            this.remove(); //Forge keep data until we revive player
        }

    }

    public void die(DamageSource p_70645_1_) {
        if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, p_70645_1_)) return;
        if (!this.removed && !this.dead) {
            Entity entity = p_70645_1_.getEntity();
            LivingEntity livingentity = this.getKillCredit();
            if (this.deathScore >= 0 && livingentity != null) {
                livingentity.awardKillScore(this, this.deathScore, p_70645_1_);
            }

            if (this.isSleeping()) {
                this.stopSleeping();
            }

            this.dead = true;
            this.getCombatTracker().recheckStatus();
            if (this.level instanceof ServerWorld) {
                if (entity != null) {
                    entity.killed((ServerWorld)this.level, this);
                }

                this.dropAllDeathLoot(p_70645_1_);
                this.createWitherRose(livingentity);
            }

            this.level.broadcastEntityEvent(this, (byte)3);}
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

    public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }

}
