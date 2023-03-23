package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.TameableFlyingBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
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

public class LovebirdEntity extends TameableFlyingBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public LovebirdEntity(EntityType<? extends LovebirdEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

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
        data.addAnimationController(new AnimationController<LovebirdEntity>(this, "controller", 0, this::predicate));
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

    public static boolean checkBirdSpawnRules(EntityType<LovebirdEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 13;
    }

    public LovebirdEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        LovebirdEntity lovebirdEntity = CreaturesEntities.LOVEBIRD.get().create(p_149088_);
        if (this.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
                lovebirdEntity.setVariant(2); }
            else {
                lovebirdEntity.setVariant(this.getVariant());
            }
        }
        else if (this.getVariant() == 3) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 1) {
                lovebirdEntity.setVariant(4); }
            else {
                lovebirdEntity.setVariant(this.getVariant());
            }
        }
        else if (this.getVariant() == 5) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 1) {
                lovebirdEntity.setVariant(13);
            }
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
                lovebirdEntity.setVariant(12);
            }
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
            while (i == 2 || i == 4 || i == 12 || i == 13) {
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
        } else if (!(p_30392_ instanceof LovebirdEntity)) {
            return false;
        } else {
            LovebirdEntity lovebird = (LovebirdEntity)p_30392_;
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
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.fischers");
            return s1.getString();
        }
        else if (this.getVariant() == 2) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.fischersmutation");
            return s1.getString();
        }
        else if (this.getVariant() == 3) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.masked");
            return s1.getString();
        }
        else if (this.getVariant() == 4) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.maskedmutation");
            return s1.getString();
        }
        else if (this.getVariant() == 5) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.peach");
            return s1.getString();
        }
        else if (this.getVariant() == 6) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.madagascar");
            return s1.getString();
        }
        else if (this.getVariant() == 7) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.blackwingedlovebird");
            return s1.getString();
        }   else if (this.getVariant() == 8) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.redfaced");
            return s1.getString();
        } else if (this.getVariant() == 9) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.swindern");
            return s1.getString();
        } else if (this.getVariant() == 10) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.blackcheeked");
            return s1.getString();
        } else if (this.getVariant() == 11) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.lilians");
            return s1.getString();
        } else if (this.getVariant() == 12) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.aquamarine");
            return s1.getString();
        } else if (this.getVariant() == 13) {
            net.minecraft.network.chat.Component s1 = new TranslatableComponent("message.creatures.lovebird.bluepeachfaced");
            return s1.getString();
        } else {
            return "Unknown";
        }
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.3F;
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.LOVEBIRD_AMBIENT; } else {
            return null;
        }
    }


    public double getHatchChance() {
        return CreaturesConfig.lovebird_hatch_chance.get();
    }

}
