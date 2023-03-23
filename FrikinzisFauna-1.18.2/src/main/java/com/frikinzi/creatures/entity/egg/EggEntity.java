package com.frikinzi.creatures.entity.egg;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
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
    //private final java.util.function.Supplier<? extends EntityType<?>> entityTypeSupplier;

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

    public void hatchEgg(EggEntity egg) {
        if (--egg.hatchTime <= 0) {
            if (egg.getSpecies() == 0 & !this.level.isClientSide) {
                LovebirdEntity lovebirdentity = new LovebirdEntity(CreaturesEntities.LOVEBIRD.get(), egg.level);

                if (egg.hasCustomName()) {
                    lovebirdentity.setCustomName(egg.getCustomName());
                }
                lovebirdentity.setVariant(egg.getVariant());
                lovebirdentity.setHeightMultiplier(this.getHeightMultiplier());
                lovebirdentity.setGender(egg.getGender());
                lovebirdentity.setBaby(true);
                lovebirdentity.setPos(egg.getX(), egg.getY(), egg.getZ());
                if (this.random.nextFloat() < lovebirdentity.getHatchChance()) {
                    this.level.addFreshEntity(lovebirdentity);
                }
                egg.remove(RemovalReason.DISCARDED);
            }
            if (egg.getSpecies() == 1 & !this.level.isClientSide) {
                SpoonbillEntity bird = new SpoonbillEntity(CreaturesEntities.SPOONBILL.get(), egg.level);

                if (egg.hasCustomName()) {
                    bird.setCustomName(egg.getCustomName());
                }
                bird.setVariant(egg.getVariant());
                bird.setHeightMultiplier(this.getHeightMultiplier());
                bird.setGender(egg.getGender());
                bird.setBaby(true);
                bird.setPos(egg.getX(), egg.getY(), egg.getZ());
                if (this.random.nextFloat() < bird.getHatchChance()) {
                    this.level.addFreshEntity(bird);
                }
                egg.remove(RemovalReason.DISCARDED);
            }
            if (egg.getSpecies() == 2 & !this.level.isClientSide) {
                KakapoEntity bird = new KakapoEntity(CreaturesEntities.KAKAPO.get(), egg.level);

                if (egg.hasCustomName()) {
                    bird.setCustomName(egg.getCustomName());
                }
                bird.setHeightMultiplier(this.getHeightMultiplier());
                bird.setGender(egg.getGender());
                bird.setBaby(true);
                bird.setPos(egg.getX(), egg.getY(), egg.getZ());
                if (this.random.nextFloat() < bird.getHatchChance()) {
                    this.level.addFreshEntity(bird);
                }
                egg.remove(RemovalReason.DISCARDED);
            }
            if (egg.getSpecies() == 3 & !this.level.isClientSide) {
                MandarinDuckEntity bird = new MandarinDuckEntity(CreaturesEntities.MANDARIN_DUCK.get(), egg.level);

                if (egg.hasCustomName()) {
                    bird.setCustomName(egg.getCustomName());
                }
                bird.setHeightMultiplier(this.getHeightMultiplier());
                bird.setGender(egg.getGender());
                bird.setBaby(true);
                bird.setPos(egg.getX(), egg.getY(), egg.getZ());
                if (this.random.nextFloat() < bird.getHatchChance()) {
                    this.level.addFreshEntity(bird);
                }
                egg.remove(RemovalReason.DISCARDED);
            }
            if (egg.getSpecies() == 4 & !this.level.isClientSide) {
                RavenEntity bird = new RavenEntity(CreaturesEntities.RAVEN.get(), egg.level);

                if (egg.hasCustomName()) {
                    bird.setCustomName(egg.getCustomName());
                }
                bird.setVariant(this.getVariant());
                bird.setHeightMultiplier(this.getHeightMultiplier());
                bird.setGender(egg.getGender());
                bird.setBaby(true);
                bird.setPos(egg.getX(), egg.getY(), egg.getZ());
                if (this.random.nextFloat() < bird.getHatchChance()) {
                    this.level.addFreshEntity(bird);
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
        if (this.getSpecies() == 0) {
            return new ItemStack(CreaturesItems.LOVEBIRD_EGG.get());
        }
        else if (this.getSpecies() == 1) {
            return new ItemStack(CreaturesItems.SPOONBILL_EGG.get()); }
        else if (this.getSpecies() == 2) {
            return new ItemStack(CreaturesItems.KAKAPO_EGG.get());
        } else if (this.getSpecies() == 3) {
            return new ItemStack(CreaturesItems.MANDARIN_DUCK_EGG.get());
        } else if (this.getSpecies() == 4) {
            return new ItemStack(CreaturesItems.RAVEN_EGG.get());
        } else if (this.getSpecies() == 5) {
            return new ItemStack(CreaturesItems.DOVE_EGG.get());
        } else if (this.getSpecies() == 6) {
            return new ItemStack(CreaturesItems.RED_KITE_EGG.get());
        } else if (this.getSpecies() == 7) {
            return new ItemStack(CreaturesItems.GOLDEN_EAGLE_EGG.get());
        } else if (this.getSpecies() == 8) {
            return new ItemStack(CreaturesItems.STELLERS_SEA_EAGLE_EGG.get());
        } else if (this.getSpecies() == 9) {
            return new ItemStack(CreaturesItems.GYRFALCON_EGG.get());
        } else if (this.getSpecies() == 10) {
            return new ItemStack(CreaturesItems.LORIKEET_EGG.get());
        } else if (this.getSpecies() == 11) {
            return new ItemStack(CreaturesItems.CONURE_EGG.get());
        } else if (this.getSpecies() == 12) {
            return new ItemStack(CreaturesItems.FAIRYWREN_EGG.get());
        } else if (this.getSpecies() == 13) {
            return new ItemStack(CreaturesItems.PYGMY_FALCON_EGG.get());
        } else if (this.getSpecies() == 14) {
            return new ItemStack(CreaturesItems.BARN_OWL_EGG.get());
        } else if (this.getSpecies() == 15) {
            return new ItemStack(CreaturesItems.WILD_DUCK_EGG.get());
        } else if (this.getSpecies() == 16) {
            return new ItemStack(CreaturesItems.ROLLER_EGG.get());
        } else if (this.getSpecies() == 17) {
            return new ItemStack(CreaturesItems.CHICKADEE_EGG.get());
        } else if (this.getSpecies() == 18) {
            return new ItemStack(CreaturesItems.PYGMY_GOOSE_EGG.get());
        } else if (this.getSpecies() == 19) {
            return new ItemStack(CreaturesItems.SWALLOW_EGG.get());
        } else if (this.getSpecies() == 20) {
            return new ItemStack(CreaturesItems.IBIS_EGG.get());
        } else if (this.getSpecies() == 21) {
            return new ItemStack(CreaturesItems.WOOD_DUCK_EGG.get());
        } else if (this.getSpecies() == 22) {
            return new ItemStack(CreaturesItems.PEAFOWL_EGG.get());
        } else if (this.getSpecies() == 23) {
            return new ItemStack(CreaturesItems.SPARROW_EGG.get());
        } else if (this.getSpecies() == 24) {
            return new ItemStack(CreaturesItems.BUSHTIT_EGG.get());
        } else if (this.getSpecies() == 25) {
            return new ItemStack(CreaturesItems.EAGLEOWL_EGG.get());
        } else if (this.getSpecies() == 26) {
            return new ItemStack(CreaturesItems.ROBIN_EGG.get());
        } else if (this.getSpecies() == 27) {
            return new ItemStack(CreaturesItems.LAUGHINGTHRUSH_EGG.get());
        } else if (this.getSpecies() == 28) {
            return new ItemStack(CreaturesItems.MAGPIE_EGG.get());
        } else if (this.getSpecies() == 29) {
            return new ItemStack(CreaturesItems.GOOSE_EGG.get());
        } else if (this.getSpecies() == 30) {
            return new ItemStack(CreaturesItems.OSPREY_EGG.get());
        } else if (this.getSpecies() == 31) {
            return new ItemStack(CreaturesItems.KINGFISHER_EGG.get());
        } else if (this.getSpecies() == 32) {
            return new ItemStack(CreaturesItems.PELICAN_EGG.get());
        } else if (this.getSpecies() == 33) {
            return new ItemStack(CreaturesItems.LAPWING_EGG.get());
        } else if (this.getSpecies() == 34) {
            return new ItemStack(CreaturesItems.SKUA_EGG.get());
        }  else if (this.getSpecies() == 35) {
            return new ItemStack(CreaturesItems.BUNTING_EGG.get());
        }  else if (this.getSpecies() == 36) {
            return new ItemStack(CreaturesItems.MONAL_EGG.get());
        }  else if (this.getSpecies() == 37) {
            return new ItemStack(CreaturesItems.TANAGER_EGG.get());
        }  else if (this.getSpecies() == 38) {
            return new ItemStack(CreaturesItems.FINCH_EGG.get());
        }   else if (this.getSpecies() == 39) {
            return new ItemStack(CreaturesItems.CAPERCAILLIE_EGG.get());
        }    else if (this.getSpecies() == 40) {
            return new ItemStack(CreaturesItems.PHEASANT_EGG.get());
        } else {
            return new ItemStack(CreaturesItems.LOVEBIRD_EGG.get());
        }
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

}
