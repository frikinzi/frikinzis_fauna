package com.frikinzi.creatures.entity.egg;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.util.ModEventSubscriber;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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

public class EggEntity extends AgeableMob implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(EggEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(EggEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SPECIES = SynchedEntityData.defineId(EggEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> HEIGHT_MULTIPLIER = SynchedEntityData.defineId(EggEntity.class, EntityDataSerializers.FLOAT);
    public int hatchTime = this.random.nextInt(CreaturesConfig.base_egg_hatch_time.get()) + CreaturesConfig.base_egg_hatch_time.get();

    public EggEntity(EntityType<? extends EggEntity> p_29362_, Level p_29363_) {
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
        } else {
            this.setVariant(-1);

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
        data.addAnimationController(new AnimationController<EggEntity>(this, "controller", 0, this::predicate));
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
        this.entityData.define(DATA_VARIANT_ID, -1);
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

    public void hatchEgg(EggEntity egg) {
        if (--egg.hatchTime <= 0) {
            if (!this.level.isClientSide) {
                int species = egg.getSpecies();
                EntityType<? extends CreaturesBirdEntity> type = ModEventSubscriber.getBirdEntityMap().get(species);
                if (type != null) {
                    CreaturesBirdEntity bird = createBirdEntity(type, egg);

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

    public ItemStack getEggItem() {
        return switch (this.getSpecies()) {
            case 0 -> new ItemStack(CreaturesItems.LOVEBIRD_EGG.get());
            case 1 -> new ItemStack(CreaturesItems.SPOONBILL_EGG.get());
            case 2 -> new ItemStack(CreaturesItems.KAKAPO_EGG.get());
            case 3 -> new ItemStack(CreaturesItems.MANDARIN_DUCK_EGG.get());
            case 4 -> new ItemStack(CreaturesItems.RAVEN_EGG.get());
            case 5 -> new ItemStack(CreaturesItems.DOVE_EGG.get());
            case 6 -> new ItemStack(CreaturesItems.RED_KITE_EGG.get());
            case 7 -> new ItemStack(CreaturesItems.GOLDEN_EAGLE_EGG.get());
            case 8 -> new ItemStack(CreaturesItems.STELLERS_SEA_EAGLE_EGG.get());
            case 9 -> new ItemStack(CreaturesItems.GYRFALCON_EGG.get());
            case 10 -> new ItemStack(CreaturesItems.LORIKEET_EGG.get());
            case 11 -> new ItemStack(CreaturesItems.CONURE_EGG.get());
            case 12 -> new ItemStack(CreaturesItems.FAIRYWREN_EGG.get());
            case 13 -> new ItemStack(CreaturesItems.PYGMY_FALCON_EGG.get());
            case 14 -> new ItemStack(CreaturesItems.BARN_OWL_EGG.get());
            case 15 -> new ItemStack(CreaturesItems.WILD_DUCK_EGG.get());
            case 16 -> new ItemStack(CreaturesItems.ROLLER_EGG.get());
            case 17 -> new ItemStack(CreaturesItems.CHICKADEE_EGG.get());
            case 18 -> new ItemStack(CreaturesItems.PYGMY_GOOSE_EGG.get());
            case 19 -> new ItemStack(CreaturesItems.SWALLOW_EGG.get());
            case 20 -> new ItemStack(CreaturesItems.IBIS_EGG.get());
            case 21 -> new ItemStack(CreaturesItems.WOOD_DUCK_EGG.get());
            case 22 -> new ItemStack(CreaturesItems.PEAFOWL_EGG.get());
            case 23 -> new ItemStack(CreaturesItems.SPARROW_EGG.get());
            case 24 -> new ItemStack(CreaturesItems.BUSHTIT_EGG.get());
            case 25 -> new ItemStack(CreaturesItems.EAGLEOWL_EGG.get());
            case 26 -> new ItemStack(CreaturesItems.ROBIN_EGG.get());
            case 27 -> new ItemStack(CreaturesItems.LAUGHINGTHRUSH_EGG.get());
            case 28 -> new ItemStack(CreaturesItems.MAGPIE_EGG.get());
            case 29 -> new ItemStack(CreaturesItems.GOOSE_EGG.get());
            case 30 -> new ItemStack(CreaturesItems.OSPREY_EGG.get());
            case 31 -> new ItemStack(CreaturesItems.KINGFISHER_EGG.get());
            case 32 -> new ItemStack(CreaturesItems.PELICAN_EGG.get());
            case 33 -> new ItemStack(CreaturesItems.LAPWING_EGG.get());
            case 34 -> new ItemStack(CreaturesItems.SKUA_EGG.get());
            case 35 -> new ItemStack(CreaturesItems.BUNTING_EGG.get());
            case 36 -> new ItemStack(CreaturesItems.MONAL_EGG.get());
            case 37 -> new ItemStack(CreaturesItems.TANAGER_EGG.get());
            case 38 -> new ItemStack(CreaturesItems.FINCH_EGG.get());
            case 39 -> new ItemStack(CreaturesItems.CAPERCAILLIE_EGG.get());
            case 40 -> new ItemStack(CreaturesItems.PHEASANT_EGG.get());
            case 41 -> new ItemStack(CreaturesItems.STORK_EGG.get());
            default -> new ItemStack(CreaturesItems.LOVEBIRD_EGG.get());
        };
    }

    // Forge Start
    //protected EntityType<?> getBirdType() {
    //    return entityTypeSupplier.get();
    //}

    protected SoundEvent getAmbientSound() {
        if (this.hatchTime <= 80) {
            return CreaturesSound.EGG_HATCH;
        } else {
            return null;
        }
    }

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
        if (itemstack == ItemStack.EMPTY && this.isAlive()) {
            ItemStack itemstack1 = this.getEggItem();
            this.saveToEggTag(itemstack1);
            if (itemstack.isEmpty()) {
                p_230254_1_.setItemInHand(p_230254_2_, itemstack1);
            } else if (!p_230254_1_.getInventory().add(itemstack1)) {
                p_230254_1_.drop(itemstack1, false);
            }
            this.remove(RemovalReason.DISCARDED);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
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

    private CreaturesBirdEntity createBirdEntity(EntityType<? extends CreaturesBirdEntity> entityType, EggEntity egg) {
        CreaturesBirdEntity bird = entityType.create(egg.level);
        if (egg.hasCustomName()) {
            bird.setCustomName(egg.getCustomName());
        }
        if (this.getVariant() == -1) {
            bird.setVariant(bird.determineVariant());
        } else {
            bird.setVariant(egg.getVariant());
        }
        bird.setHeightMultiplier(this.getHeightMultiplier());
        bird.setGender(egg.getGender());
        bird.setBaby(true);
        bird.setPos(egg.getX(), egg.getY(), egg.getZ());
        return bird;
    }

}
