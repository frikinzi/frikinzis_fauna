package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesLootTables;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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

import java.util.List;
import java.util.Map;
import java.util.Random;

public class PeafowlEntity extends CreaturesBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final EntityDataAccessor<Boolean> ON_DISPLAY = SynchedEntityData.defineId(PeafowlEntity.class, EntityDataSerializers.BOOLEAN);
    public static Map<Integer, TranslatableComponent> SPECIES_NAMES = ImmutableMap.of(
            1, new TranslatableComponent("message.creatures.greenpeafowl"),
            2, new TranslatableComponent("message.creatures.indianpeafowl"),
            3, new TranslatableComponent("message.creatures.albinopeafowl")
    );

    public PeafowlEntity(EntityType<? extends PeafowlEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new SleepGoal());
        this.goalSelector.addGoal(4, new DisplayGoal());
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, this.getBirdFood(), false));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isInSittingPose()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        else if (this.isOnDisplay() && !this.isBaby() && !(this.getGender() == 0)) {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("displaywalk", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("display", ILoopType.EDefaultLoopTypes.PLAY_ONCE).addAnimation("displayidle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } else {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            if (this.isSleeping()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<PeafowlEntity>(this, "controller", 10, this::predicate));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.FLYING_SPEED, (double)0.4F).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public static boolean checkBirdSpawnRules(EntityType<PeafowlEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 3;
    }

    public PeafowlEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        PeafowlEntity peafowl = CreaturesEntities.PEAFOWL.get().create(p_149088_);
        peafowl.setGender(this.random.nextInt(2));
        peafowl.setVariant(this.getVariant());
        peafowl.setHeightMultiplier(getSpawnEggOffspringHeight());
        return peafowl;
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!(p_30392_ instanceof PeafowlEntity)) {
            return false;
        } else {
            PeafowlEntity peafowl = (PeafowlEntity)p_30392_;
            return this.isInLove() && peafowl.isInLove();
        }
    }

    public String getSpeciesName() {
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.PEAFOWL_AMBIENT; } else {
            return null;
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ON_DISPLAY, false);
    }

    public void setOnDisplay(boolean p_70918_1_) {
        this.entityData.set(ON_DISPLAY, p_70918_1_);
    }

    public boolean isOnDisplay() {
        return this.entityData.get(ON_DISPLAY);
    }

    public void addAdditionalSaveData(CompoundTag p_29422_) {
        super.addAdditionalSaveData(p_29422_);
        p_29422_.putBoolean("Displaying", this.isOnDisplay());
    }

    public void readAdditionalSaveData(CompoundTag p_29402_) {
        super.readAdditionalSaveData(p_29402_);
        this.setOnDisplay(p_29402_.getBoolean("Displaying"));
    }


    public double getHatchChance() {
        return CreaturesConfig.peafowl_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.peafowl_clutch_size.get());
    }


    public class DisplayGoal extends Goal {
        private final TargetingConditions predicate = TargetingConditions.forNonCombat().range(16.0D).selector((p_220844_0_) -> {
            return ((PeafowlEntity)p_220844_0_).getGender() == 0 && !((PeafowlEntity)p_220844_0_).isBaby();
        });

        protected DisplayGoal() {

        }

        public boolean canUse() {
            if (PeafowlEntity.this.getGender() == 0 || PeafowlEntity.this.isSleeping() || PeafowlEntity.this.isBaby()) {
                return false;
            } else {
                List<PeafowlEntity> list = PeafowlEntity.this.level.getNearbyEntities(PeafowlEntity.class, this.predicate, PeafowlEntity.this, PeafowlEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
                if (list.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        public void start() {
            PeafowlEntity.this.getNavigation().stop();
            PeafowlEntity.this.setOnDisplay(true);
        }

        public void stop() {
            PeafowlEntity.this.setOnDisplay(false);
        }

        public boolean canContinueToUse() {
            List<PeafowlEntity> list = PeafowlEntity.this.level.getNearbyEntities(PeafowlEntity.class, this.predicate, PeafowlEntity.this, PeafowlEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            return !(list.isEmpty() || PeafowlEntity.this.isSleeping() || PeafowlEntity.this.isInSittingPose() || PeafowlEntity.this.isBaby());
        }

    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.PEAFOWL;
    }

}
