package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.entity.base.AbstractWalkingCreature;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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
import java.util.Map;
import java.util.Random;

public class FiddlerCrabEntity extends AbstractWalkingCreature implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public static Map<Integer, TranslatableComponent> SPECIES_NAMES = ImmutableMap.of(
            1, new TranslatableComponent("message.creatures.thicklegged"),
            2, new TranslatableComponent("message.creatures.atlanticfiddler"),
            3, new TranslatableComponent("message.creatures.africanfiddler"),
            4, new TranslatableComponent("message.creatures.demanding"),
            5, new TranslatableComponent("message.creatures.flamebacked")
    );

    public FiddlerCrabEntity(EntityType<? extends FiddlerCrabEntity> p_29790_, Level p_29791_) {
        super(p_29790_, p_29791_);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_30023_, DifficultyInstance p_30024_, MobSpawnType p_30025_, @Nullable SpawnGroupData p_30026_, @Nullable CompoundTag p_30027_) {
        return super.finalizeSpawn(p_30023_, p_30024_, p_30025_, p_30026_, p_30027_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Player.class, 10.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Monster.class, 10.0F, 1.5D, 1.5D));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }


    public FiddlerCrabEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        FiddlerCrabEntity crab = CreaturesEntities.FIDDLER_CRAB.get().create(p_149088_);
        crab.setVariant(this.getVariant());
        crab.setGender(this.random.nextInt(2));
        crab.setHeightMultiplier(getSpawnEggOffspringHeight());
        return crab;
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!(p_30392_ instanceof FiddlerCrabEntity)) {
            return false;
        } else {
            FiddlerCrabEntity crab = (FiddlerCrabEntity)p_30392_;
            return this.isInLove() && crab.isInLove();
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<FiddlerCrabEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    public int noVariants() {
        return 5;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F);
    }

    public String getSpeciesName() {
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public static boolean checkCrabSpawnRules(EntityType<FiddlerCrabEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return (p_29425_.getBlockState(p_29427_.below()).is(BlockTags.SAND) || p_29425_.getBlockState(p_29427_.below()).is(BlockTags.DIRT)) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.CRAB;
    }

}
