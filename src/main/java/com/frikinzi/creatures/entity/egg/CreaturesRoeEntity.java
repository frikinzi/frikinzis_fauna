package com.frikinzi.creatures.entity.egg;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class CreaturesRoeEntity extends WaterAnimal implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Integer> SPECIES_ID = SynchedEntityData.defineId(CreaturesRoeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(CreaturesRoeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_SUBVARIANT_ID = SynchedEntityData.defineId(CreaturesRoeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(CreaturesRoeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HATCHING = SynchedEntityData.defineId(CreaturesRoeEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> HEIGHT_MULTIPLIER = SynchedEntityData.defineId(CreaturesRoeEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Optional<UUID>> DATA_PARENTUUID_ID = SynchedEntityData.defineId(CreaturesRoeEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    public int hatchTime = this.random.nextInt(CreaturesConfig.base_egg_hatch_time.get()) + CreaturesConfig.base_egg_hatch_time.get();
    private CreaturesBirdEntity parent;

    public CreaturesRoeEntity(EntityType<? extends CreaturesRoeEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        parent = null;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_213386_1_, DifficultyInstance p_213386_2_, MobSpawnType p_213386_3_, @Nullable SpawnGroupData p_213386_4_, @Nullable CompoundTag p_213386_5_) {
        if (p_213386_5_ != null) {
            if (p_213386_5_.contains("EggVariant", 3)) {
                this.setVariant(p_213386_5_.getInt("EggVariant"));
                this.setGender(this.random.nextInt(2));
            }
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

    private <E extends CreaturesRoeEntity> PlayState predicate(AnimationState<E> event) {
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D);
    }

    @Override
    public void tick() {
        super.tick();
        hatchEgg(this);
    }

    public int getVariant() {
        return Mth.clamp(this.entityData.get(DATA_VARIANT_ID), 1, 20);
    }

    public void setVariant(int p_191997_1_) {
        this.entityData.set(DATA_VARIANT_ID, p_191997_1_);
    }

    public int getSubVariant() {
        return Mth.clamp(this.entityData.get(DATA_SUBVARIANT_ID), 1, 30);
    }

    public void setSubVariant(int p_191997_1_) {
        this.entityData.set(DATA_SUBVARIANT_ID, p_191997_1_);
    }

    public int getGender() {
        return Mth.clamp(this.entityData.get(GENDER), 0, 2);
    }

    public void setGender(int p_191997_1_) {
        this.entityData.set(GENDER, p_191997_1_);
    }

    public int getSpecies() {
        return Mth.clamp(this.entityData.get(SPECIES_ID), 0, 1000);
    }

    public void setSpecies(int p_191997_1_) {
        this.entityData.set(SPECIES_ID, p_191997_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(DATA_SUBVARIANT_ID, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(SPECIES_ID, 0);
        this.entityData.define(HATCHING, false);
        this.entityData.define(HEIGHT_MULTIPLIER, 1.0F);
        this.entityData.define(DATA_PARENTUUID_ID, Optional.empty());
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("Hatching", this.isHatching());
        p_213281_1_.putInt("Variant", this.getVariant());
        p_213281_1_.putInt("Subvariant", this.getSubVariant());
        p_213281_1_.putInt("Species", this.getSpecies());
        p_213281_1_.putInt("Gender", this.getGender());
        p_213281_1_.putFloat("HeightMultiplier", this.getHeightMultiplier());
        if (this.getParentUUID() != null) {
            p_213281_1_.putUUID("Parent", this.getParentUUID());
        }
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setHatching(p_70037_1_.getBoolean("Hatching"));
        this.setVariant(p_70037_1_.getInt("Variant"));
        this.setSubVariant(p_70037_1_.getInt("Subvariant"));
        this.setSpecies(p_70037_1_.getInt("Species"));
        this.setGender(p_70037_1_.getInt("Gender"));
        this.setHeightMultiplier(p_70037_1_.getFloat("HeightMultiplier"));
        if (p_70037_1_.hasUUID("Parent")) {
            this.setParentUUID(p_70037_1_.getUUID("Parent"));
        }
    }

    public float getHeightMultiplier() {
        return this.entityData.get(HEIGHT_MULTIPLIER);
    }

    public void setHeightMultiplier(float p_70606_1_) {
        this.entityData.set(HEIGHT_MULTIPLIER, Mth.clamp(p_70606_1_, 0.5F, 2F));
    }

    public boolean isHatching() {
        return this.entityData.get(HATCHING);
    }

    private void setHatching(boolean p_213485_1_) {
        this.entityData.set(HATCHING, p_213485_1_);
    }

    @Nullable
    public UUID getParentUUID() {
        return this.entityData.get(DATA_PARENTUUID_ID).orElse(null);
    }

    public void setParentUUID(@Nullable UUID p_184754_1_) {
        this.entityData.set(DATA_PARENTUUID_ID, Optional.ofNullable(p_184754_1_));
    }

    private <T extends FishBase> void spawnFish(CreaturesRoeEntity egg, T fish, boolean setVariant, boolean setSubVariant) {
        if (egg.hasCustomName()) fish.setCustomName(egg.getCustomName());
        if (setVariant) fish.setVariant(egg.getVariant());
        if (setSubVariant) fish.setSubVariant(this.getSubVariant());
        fish.setHeightMultiplier(this.getHeightMultiplier());
        fish.setPos(egg.getX(), egg.getY(), egg.getZ());
        fish.setBaby(true);
        fish.setBred(true);
        fish.setPersistenceRequired(); //baby fish should never despawn
        if (this.random.nextFloat() < fish.getHatchChance()) {
            this.level().addFreshEntity(fish);
        }
        egg.discard();
    }

    public void hatchEgg(CreaturesRoeEntity egg) {
        if (egg.getSpecies() == 19) {
            egg.hatchTime = 0;
        }
        if (--egg.hatchTime > 0) return;
        if (this.level().isClientSide()) return;

        switch (egg.getSpecies()) {
            case 0 -> spawnFish(egg, new KoiEntity(CreaturesEntities.KOI.get(), egg.level()), true, false);
            case 1 -> spawnFish(egg, new DottybackEntity(CreaturesEntities.DOTTYBACK.get(), egg.level()), true, false);
            case 2 -> spawnFish(egg, new PikeEntity(CreaturesEntities.PIKE.get(), egg.level()), false, false);
            case 3 -> spawnFish(egg, new ShrimpEntity(CreaturesEntities.SHRIMP.get(), egg.level()), true, false);
            case 4 -> {
                GuppyEntity fish = new GuppyEntity(CreaturesEntities.GUPPY.get(), egg.level());
                if (egg.hasCustomName()) fish.setCustomName(egg.getCustomName());
                fish.setVariant(this.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBaby(true);
                fish.setBred(true);
                this.level().addFreshEntity(fish);
                egg.discard();
                //spawnFish(egg, new GuppyEntity(CreaturesEntities.GUPPY.get(), egg.level()), true, false);
            }
            case 5 -> spawnFish(egg, new GouramiEntity(CreaturesEntities.GOURAMI.get(), egg.level()), true, false);
            case 6 -> spawnFish(egg, new ArowanaEntity(CreaturesEntities.AROWANA.get(), egg.level()), true, false);
            case 7 -> spawnFish(egg, new GoldfishEntity(CreaturesEntities.GOLDFISH.get(), egg.level()), true, false);
            case 8 -> spawnFish(egg, new RanchuEntity(CreaturesEntities.RANCHU.get(), egg.level()), true, false);
            case 9 -> spawnFish(egg, new FireGobyEntity(CreaturesEntities.FIRE_GOBY.get(), egg.level()), true, false);
            case 10 -> spawnFish(egg, new BlueTangEntity(CreaturesEntities.BLUE_TANG.get(), egg.level()), false, false);
            case 11 -> spawnFish(egg, new FlameAngelfishEntity(CreaturesEntities.FLAME_ANGELFISH.get(), egg.level()), false, false);
            case 12 -> spawnFish(egg, new TroutEntity(CreaturesEntities.TROUT.get(), egg.level()), true, false);
            case 13 -> spawnFish(egg, new TigerBarbEntity(CreaturesEntities.TIGERBARB.get(), egg.level()), true, false);
            case 14 -> spawnFish(egg, new RedSnapperEntity(CreaturesEntities.RED_SNAPPER.get(), egg.level()), true, false);
            case 15 -> spawnFish(egg, new ArapaimaEntity(CreaturesEntities.ARAPAIMA.get(), egg.level()), false, false);
            case 16 -> spawnFish(egg, new PiranhaEntity(CreaturesEntities.PIRANHA.get(), egg.level()), true, false);
            case 17 -> spawnFish(egg, new TambaquiEntity(CreaturesEntities.TAMBAQUI.get(), egg.level()), true, false);
            case 18 -> spawnFish(egg, new ElephantNoseFishEntity(CreaturesEntities.ELEPHANTNOSE.get(), egg.level()), true, false);
            case 19 -> {
                StingrayEntity fish = new StingrayEntity(CreaturesEntities.STINGRAY.get(), egg.level());
                if (egg.hasCustomName()) fish.setCustomName(egg.getCustomName());
                fish.setVariant(this.getVariant());
                fish.setHeightMultiplier(this.getHeightMultiplier());
                fish.setPos(egg.getX(), egg.getY(), egg.getZ());
                fish.setBaby(true);
                fish.setBred(true);
                this.level().addFreshEntity(fish);
                egg.discard();
            }
            case 20 -> spawnFish(egg, new SawfishEntity(CreaturesEntities.SAWFISH.get(), egg.level()), true, false);
            case 21 -> spawnFish(egg, new SwordfishEntity(CreaturesEntities.SWORDFISH.get(), egg.level()), true, false);
            case 22 -> spawnFish(egg, new SquidEntity(CreaturesEntities.SQUID.get(), egg.level()), true, false);
            case 23 -> spawnFish(egg, new LookdownEntity(CreaturesEntities.LOOKDOWN.get(), egg.level()), true, false);
            case 24 -> spawnFish(egg, new BarracudaEntity(CreaturesEntities.BARRACUDA.get(), egg.level()), true, false);
            case 25 -> spawnFish(egg, new SeaDragonEntity(CreaturesEntities.SEADRAGON.get(), egg.level()), true, false);
            case 26 -> spawnFish(egg, new TrumpetfishEntity(CreaturesEntities.TRUMPETFISH.get(), egg.level()), true, false);
            case 27 -> spawnFish(egg, new ParrotfishEntity(CreaturesEntities.PARROTFISH.get(), egg.level()), true, false);
            case 28 -> spawnFish(egg, new ClownfishEntity(CreaturesEntities.CLOWNFISH.get(), egg.level()), true, true);
            case 29 -> spawnFish(egg, new LungfishEntity(CreaturesEntities.LUNGFISH.get(), egg.level()), true, false);
            case 30 -> spawnFish(egg, new TetraEntity(CreaturesEntities.TETRA.get(), egg.level()), true, false);
            default -> egg.discard();
        }

        this.level().broadcastEntityEvent(this, (byte) 3);
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == CreaturesItems.FF_GUIDE.get()) {
            //System.out.println(this.getParentUUID());
            Creatures.PROXY.setReferencedMob(this);
            if (this.level().isClientSide()) {
                Creatures.PROXY.openCreaturesGui();
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }

    protected void saveToEggTag(ItemStack p_204211_1_) {
        if (this.hasCustomName()) {
            p_204211_1_.setHoverName(this.getCustomName());
        }
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("EggVariant", this.getVariant());
        compoundnbt.putFloat("EggHeightMultiplier", this.getHeightMultiplier());
        //System.out.println(compoundnbt);
    }

    public boolean isPushable() {
        return false;
    }

    public int getHatchTime() {
        return (this.hatchTime / 1200);
    }

    protected SoundEvent getAmbientSound() {
        return this.isHatching() ? CreaturesSound.EGG_HATCH.get() : null;
    }

    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            this.discard();
        }
    }

    public String getHeightString() {
        if (this.getHeightMultiplier() >= 1.5)  return net.minecraft.network.chat.Component.translatable("gui.giant").getString();
        if (this.getHeightMultiplier() >= 1.4)  return net.minecraft.network.chat.Component.translatable("gui.huge").getString();
        if (this.getHeightMultiplier() >= 1.21) return net.minecraft.network.chat.Component.translatable("gui.large").getString();
        if (this.getHeightMultiplier() > 1.11)  return net.minecraft.network.chat.Component.translatable("gui.above_average").getString();
        if (this.getHeightMultiplier() >= 0.89) return net.minecraft.network.chat.Component.translatable("gui.average").getString();
        if (this.getHeightMultiplier() >= 0.79) return net.minecraft.network.chat.Component.translatable("gui.below_average").getString();
        return net.minecraft.network.chat.Component.translatable("gui.small").getString();
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }
}
