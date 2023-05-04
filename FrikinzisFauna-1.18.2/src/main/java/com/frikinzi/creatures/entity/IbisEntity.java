package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBirdEntity;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Collections;

public class IbisEntity extends CreaturesFlyingBirdEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
        public static final Map<Integer, TranslatableComponent> SPECIES_NAMES;

    static {
        Map<Integer, TranslatableComponent> messageMap = new HashMap<>();
        messageMap.put(1, new TranslatableComponent("message.creatures.straw"));
        messageMap.put(2, new TranslatableComponent("message.creatures.scarlet"));
        messageMap.put(3, new TranslatableComponent("message.creatures.green"));
        messageMap.put(4, new TranslatableComponent("message.creatures.madagascan"));
        messageMap.put(5, new TranslatableComponent("message.creatures.crested"));
        messageMap.put(6, new TranslatableComponent("message.creatures.southern"));
        messageMap.put(7, new TranslatableComponent("message.creatures.northernibis"));
        messageMap.put(8, new TranslatableComponent("message.creatures.americanwhiteibis"));
        messageMap.put(9, new TranslatableComponent("message.creatures.glossy"));
        messageMap.put(10, new TranslatableComponent("message.creatures.binchicken"));
        messageMap.put(11, new TranslatableComponent("message.creatures.hadada"));
        SPECIES_NAMES = Collections.unmodifiableMap(messageMap);
    }

    public IbisEntity(EntityType<? extends IbisEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new SleepGoal());
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new BirdWanderGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new MateGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, this.getBirdFood(), false));
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
        data.addAnimationController(new AnimationController<IbisEntity>(this, "controller", 0, this::predicate));
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

    public static boolean checkBirdSpawnRules(EntityType<IbisEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 11;
    }

    public IbisEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        IbisEntity ibis = CreaturesEntities.IBIS.get().create(p_149088_);
        ibis.setVariant(this.getVariant());
        ibis.setGender(this.random.nextInt(2));
        ibis.setHeightMultiplier(getSpawnEggOffspringHeight());
        return ibis;
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!(p_30392_ instanceof IbisEntity)) {
            return false;
        } else {
            IbisEntity ibis = (IbisEntity)p_30392_;
            return this.isInLove() && ibis.isInLove();
        }
    }

    public String getSpeciesName() {
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.3F;
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.IBIS_AMBIENT; } else {
            return null;
        }
    }

    public double getHatchChance() {
        return CreaturesConfig.ibis_hatch_chance.get();
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(CreaturesItems.CRAB_PINCERS.get());
    }

    public ItemStack getFoodItem() {
        return new ItemStack(CreaturesItems.CRAB_PINCERS.get(), 1);
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.ibis_clutch_size.get());
    }


}
