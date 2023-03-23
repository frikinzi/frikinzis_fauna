package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
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

import java.util.Random;

public class KakapoEntity extends CreaturesBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public KakapoEntity(EntityType<? extends KakapoEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new SleepGoal());
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, this.getBirdFood(), false));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kakapo.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } if (!this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kakapo.flying", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } if (this.isSleeping()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kakapo.sleep", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kakapo.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<KakapoEntity>(this, "controller", 0, this::predicate));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public static boolean checkBirdSpawnRules(EntityType<KakapoEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 5;
    }

    public KakapoEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        KakapoEntity kakapoentity = CreaturesEntities.KAKAPO.get().create(p_149088_);
        kakapoentity.setGender(this.random.nextInt(2));
        kakapoentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return kakapoentity;
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!(p_30392_ instanceof KakapoEntity)) {
            return false;
        } else {
            KakapoEntity kakapo = (KakapoEntity)p_30392_;
            return this.isInLove() && kakapo.isInLove();
        }
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.4F;
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(Items.APPLE, Items.MELON_SLICE);
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.KAKAPO_AMBIENT; } else {
            return null;
        }
    }

    public ItemStack getFoodItem() {
        return new ItemStack(CreaturesItems.CRAB_PINCERS.get(), 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.kakapo_hatch_chance.get();
    }


}
