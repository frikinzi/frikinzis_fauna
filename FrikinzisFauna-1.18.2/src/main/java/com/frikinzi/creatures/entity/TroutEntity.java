package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.AbstractCreaturesFish;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
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

public class TroutEntity extends AbstractCreaturesFish implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public static Map<Integer, TranslatableComponent> SPECIES_NAMES = ImmutableMap.of(
            1, new TranslatableComponent("message.creatures.rainbow"),
            2, new TranslatableComponent("message.creatures.browntrout"),
            3, new TranslatableComponent("message.creatures.brook"),
            4, new TranslatableComponent("message.creatures.goldentrout")
    );

    public TroutEntity(EntityType<? extends TroutEntity> p_29790_, Level p_29791_) {
        super(p_29790_, p_29791_);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_30023_, DifficultyInstance p_30024_, MobSpawnType p_30025_, @Nullable SpawnGroupData p_30026_, @Nullable CompoundTag p_30027_) {
        return super.finalizeSpawn(p_30023_, p_30024_, p_30025_, p_30026_, p_30027_);
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<TroutEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.TROUT_BUCKET.get());
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SALMON_HURT;
    }

    public double getHatchChance() {
        return CreaturesConfig.trout_hatch_chance.get();
    }

    public int noVariants() {
        return 4;
    }

    public Item getFoodItem() {
        return CreaturesItems.MEALWORMS.get();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, (double)0.1F);
    }

    public String getSpeciesName() {
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    protected float getStandingEyeHeight(@NotNull Pose p_33540_, @NotNull EntityDimensions p_33541_) {
        return 0.3F;
    }

}
