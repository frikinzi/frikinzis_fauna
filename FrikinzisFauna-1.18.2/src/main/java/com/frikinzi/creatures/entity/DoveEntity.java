package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.TameableFlyingBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
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
import java.util.Random;
import java.util.Set;

public class DoveEntity extends TameableFlyingBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    final private int[] jungle_variants = new int[] {1,3,5,7,8,13,14};
    final private int[] swamp_variant = new int[] {9};
    final private int[] mountain_variant = new int[] {12};
    final private int[] forest_variant = new int[] {2,4,6,11,10,15,17};
    final private int[] mesa_variant = new int[] {16};

    public DoveEntity(EntityType<? extends DoveEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_29389_, DifficultyInstance p_29390_, MobSpawnType p_29391_, @Nullable SpawnGroupData p_29392_, @Nullable CompoundTag p_29393_) {
        this.setVariant(this.determineVariant(p_29389_));
        this.setGender(this.random.nextInt(2));
        if (p_29392_ == null) {
            p_29392_ = new AgeableMob.AgeableMobGroupData(false);
        }

        float f = (float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
        this.setHeightMultiplier(f);

        return super.finalizeSpawn(p_29389_, p_29390_, p_29391_, p_29392_, p_29393_);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if ((this.isFlying()) || !this.isOnGround()) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.isSleeping()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.isInSittingPose()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (!event.isMoving())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<DoveEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.4F).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public static boolean checkBirdSpawnRules(EntityType<DoveEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 17;
    }

    public DoveEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        DoveEntity lovebirdEntity = CreaturesEntities.DOVE.get().create(p_149088_);
        lovebirdEntity.setVariant(this.getVariant());
        lovebirdEntity.setGender(this.random.nextInt(2));
        lovebirdEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return lovebirdEntity;
    }

    public int determineVariant(LevelAccessor p_29676_) {
        if (CreaturesConfig.biome_only_variants.get()) {
            Biome biome = p_29676_.getBiome(this.blockPosition()).value();
            ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);
            if (types.contains(BiomeDictionary.Type.MESA)) {
                int i = this.random.nextInt(mesa_variant.length);
                return mesa_variant[i];
            }
            if (types.contains(BiomeDictionary.Type.JUNGLE)) {
                int i = this.random.nextInt(jungle_variants.length);
                return jungle_variants[i];
            } if (types.contains(BiomeDictionary.Type.FOREST) || types.contains(BiomeDictionary.Type.PLAINS)) {
                int i = this.random.nextInt(forest_variant.length);
                return forest_variant[i];
            } if (types.contains(BiomeDictionary.Type.MOUNTAIN)) {
                int i = this.random.nextInt(mountain_variant.length);
                return mountain_variant[i];
            } if (types.contains(BiomeDictionary.Type.SWAMP)) {
                int i = this.random.nextInt(swamp_variant.length);
                return swamp_variant[i];
            }
        }
        return this.random.nextInt(this.noVariants()) + 1;

    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(p_30392_ instanceof DoveEntity)) {
            return false;
        } else {
            DoveEntity lovebird = (DoveEntity)p_30392_;
            if (!lovebird.isTame()) {
                return false;
            } else if (lovebird.isInSittingPose()) {
                return false;
            } else {
                return this.isInLove() && lovebird.isInLove();
            }
        }
    }

    public String getSpeciesName() {
        if (this.getVariant() == 1) {
            Component s1 = new TranslatableComponent("message.creatures.dove.jambu");
            return s1.getString();
        }
        else if (this.getVariant() == 2) {
            Component s1 = new TranslatableComponent("message.creatures.dove.release");
            return s1.getString();
        }
        else if (this.getVariant() == 3) {
            Component s1 = new TranslatableComponent("message.creatures.dove.rose");
            return s1.getString();
        }
        else if (this.getVariant() == 4) {
            Component s1 = new TranslatableComponent("message.creatures.dove.rock");
            return s1.getString();
        }
        else if (this.getVariant() == 5) {
            Component s1 = new TranslatableComponent("message.creatures.dove.flame");
            return s1.getString();
        }
        else if (this.getVariant() == 6) {
            Component s1 = new TranslatableComponent("message.creatures.dove.goldenheart");
            return s1.getString();
        }  else if (this.getVariant() == 7) {
            Component s1 = new TranslatableComponent("message.creatures.dove.mbleeding");
            return s1.getString();
        }  else if (this.getVariant() == 8) {
            Component s1 = new TranslatableComponent("message.creatures.dove.orangebellied");
            return s1.getString();
        }  else if (this.getVariant() == 9) {
            Component s1 = new TranslatableComponent("message.creatures.dove.victoria");
            return s1.getString();
        }  else if (this.getVariant() == 10) {
            Component s1 = new TranslatableComponent("message.creatures.dove.mourning");
            return s1.getString();
        }  else if (this.getVariant() == 11) {
            Component s1 = new TranslatableComponent("message.creatures.dove.europeanturtle");
            return s1.getString();
        }  else if (this.getVariant() == 12) {
            Component s1 = new TranslatableComponent("message.creatures.dove.snow");
            return s1.getString();
        }  else if (this.getVariant() == 13) {
            Component s1 = new TranslatableComponent("message.creatures.dove.nicobar");
            return s1.getString();
        }  else if (this.getVariant() == 14) {
            Component s1 = new TranslatableComponent("message.creatures.dove.pacificemerald");
            return s1.getString();
        }  else if (this.getVariant() == 15) {
            Component s1 = new TranslatableComponent("message.creatures.dove.crested");
            return s1.getString();
        }  else if (this.getVariant() == 16) {
            Component s1 = new TranslatableComponent("message.creatures.dove.spinifex");
            return s1.getString();
        }  else if (this.getVariant() == 17) {
            Component s1 = new TranslatableComponent("message.creatures.dove.pink");
            return s1.getString();
        }
        else {
            return "Unknown";
        }
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.3F;
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            if (this.getVariant() == 10) {
                return CreaturesSound.MOURNING_DOVE;
            } if (this.getVariant() == 15 && this.isFlying()) {
                return CreaturesSound.CRESTED_PIGEON;
            }
            return CreaturesSound.DOVE_AMBIENT;
        } else {
            return null;
        }
    }


    public double getHatchChance() {
        return CreaturesConfig.dove_hatch_chance.get();
    }

}
