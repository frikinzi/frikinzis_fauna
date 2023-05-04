package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.AbstractCreaturesFish;
import com.frikinzi.creatures.entity.egg.RoeEntity;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
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
import java.util.Random;
import java.util.function.Predicate;

public class ArapaimaEntity extends AbstractCreaturesFish implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public ArapaimaEntity(EntityType<? extends ArapaimaEntity> p_29790_, Level p_29791_) {
        super(p_29790_, p_29791_);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_30023_, DifficultyInstance p_30024_, MobSpawnType p_30025_, @Nullable SpawnGroupData p_30026_, @Nullable CompoundTag p_30027_) {
        return super.finalizeSpawn(p_30023_, p_30024_, p_30025_, p_30026_, p_30027_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, ShrimpEntity.class, true));

    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (!this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("flop", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<ArapaimaEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }


    protected @NotNull SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.ARAPAIMA_BUCKET.get());
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SALMON_HURT;
    }

    public double getHatchChance() {
        return CreaturesConfig.arapaima_hatch_chance.get();
    }

    public int noVariants() {
        return 3;
    }

    public Item getFoodItem() {
        return Items.TROPICAL_FISH;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double)0.1F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    protected void layEgg(ServerLevel server, AbstractCreaturesFish father) {
        int c = this.getClutchSize();
        for (int j = 0; j <= c; j++) {
            RoeEntity egg = this.layEgg(this);
            if (egg != null) {
                AbstractCreaturesFish mother;
                mother = this;

                float f = (float)(this.getRandom().nextGaussian() * 0.05 + ((this.getHeightMultiplier())));
                egg.setHeightMultiplier(f);
                int[] vars = {this.getVariant(), father.getVariant()};
                int rnd = new Random().nextInt(vars.length);
                egg.setVariant(vars[rnd]);
                int random = this.random.nextInt(50);
                if (random == 1) {
                    //leucistic variant chance
                    egg.setVariant(3);
                }

                Random rand = new Random();
                egg.setPos(Mth.floor(mother.getX()) + 0.5 + (-1+rand.nextFloat()), Mth.floor(mother.getY()) + 0.5, Mth.floor(mother.getZ()) + 0.5 + (-1+rand.nextFloat()));
                server.addFreshEntityWithPassengers(egg);
                //System.out.println(this.bird.getClutchSize());
            }
            server.broadcastEntityEvent(this, (byte)18);
        }
        Random random = this.getRandom();
        for (int i = 0; i < 17; ++i) {
            final double d0 = random.nextGaussian() * 0.02D;
            final double d1 = random.nextGaussian() * 0.02D;
            final double d2 = random.nextGaussian() * 0.02D;
            final double d3 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            final double d4 = 0.5D + random.nextDouble() * this.getBbHeight();
            final double d5 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            this.level.addParticle(ParticleTypes.HEART, this.getX() + d3, this.getY() + d4, this.getZ() + d5, d0, d1, d2);
        }
        this.setBred(true);
        if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            server.addFreshEntity(new ExperienceOrb(server, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
        }
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



}
