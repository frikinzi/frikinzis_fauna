package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.TameableFlyingBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesLootTables;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LorikeetEntity extends TameableFlyingBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private boolean isGumi;
    public static Map<Integer, TranslatableComponent> SPECIES_NAMES = ImmutableMap.of(
            1, new TranslatableComponent("message.creatures.lorikeet.rainbow"),
            2, new TranslatableComponent("message.creatures.lorikeet.black"),
            3, new TranslatableComponent("message.creatures.lorikeet.blue"),
            4, new TranslatableComponent("message.creatures.lorikeet.olive"),
            5, new TranslatableComponent("message.creatures.lorikeet.chattering"),
            6, new TranslatableComponent("message.creatures.lorikeet.duskylory")
    );

    public LorikeetEntity(EntityType<? extends LorikeetEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }


    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
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
        data.addAnimationController(new AnimationController<LorikeetEntity>(this, "controller", 0, this::predicate));
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

    public static boolean checkBirdSpawnRules(EntityType<LorikeetEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 6;
    }

    public LorikeetEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        LorikeetEntity lovebirdEntity = CreaturesEntities.LORIKEET.get().create(p_149088_);
        if (this.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.lorikeet_mutation_chance.get()) == 2) {
                lovebirdEntity.setVariant(3); }
            else {
                lovebirdEntity.setVariant(this.getVariant());
            }
        } else {
            lovebirdEntity.setVariant(this.getVariant());
        }
        lovebirdEntity.setGender(this.random.nextInt(2));
        lovebirdEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return lovebirdEntity;
    }

    public int determineVariant() {
        if (CreaturesConfig.breed_only_variants.get()) {
            int i = this.random.nextInt(noVariants()) + 1;
            while (i == 3) {
                i = this.random.nextInt(noVariants()) + 1;
            }
            return i;
        }
        return this.random.nextInt(this.noVariants()) + 1;

    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(p_30392_ instanceof LorikeetEntity)) {
            return false;
        } else {
            LorikeetEntity lovebird = (LorikeetEntity)p_30392_;
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
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.4F;
    }

    protected SoundEvent getAmbientSound() {
        if (this.getVariant() == 5 && this.isGumi && !this.isSleeping()) {
            return CreaturesSound.LORIKEET_AMBIENT2;
        }
        if (!this.isSleeping()) {
            return CreaturesSound.LORIKEET_AMBIENT; } else {
            return null;
        }
    }

    public void setCustomName(@Nullable Component p_200203_1_) {
        super.setCustomName(p_200203_1_);
        if (!this.isGumi && p_200203_1_ != null && p_200203_1_.getString().equals("Gumi")) {
            this.isGumi = true;
        }

    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        if (this.isGumi) {
            p_213281_1_.putBoolean("Gumi", true);
        }

    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        if (p_70037_1_.contains("Gumi", 99)) {
            this.isGumi = p_70037_1_.getBoolean("Gumi");
        }

    }

    public ItemStack getFoodItem() {
        return new ItemStack(CreaturesItems.NECTAR.get(), 1);
    }

    public Set<Item> getTameFood() {
        return Sets.newHashSet(CreaturesItems.NECTAR.get());
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(CreaturesItems.NECTAR.get());
    }

    public double getHatchChance() {
        return CreaturesConfig.lorikeet_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.lorikeet_clutch_size.get());
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.PARROT;
    }

}
