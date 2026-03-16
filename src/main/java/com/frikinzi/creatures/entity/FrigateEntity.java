package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class FrigateEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private PanicGoal PanicGoal;
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.TROPICAL_FISH || entitytype == EntityType.SALMON || entitytype == EntityType.COD;
    };
    private static final EntityDataAccessor<Boolean> DISPLAYING = SynchedEntityData.defineId(FrigateEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> ON_DISPLAY = SynchedEntityData.defineId(FrigateEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.magnificentfrigate"))
            .put(2, Component.translatable("message.creatures.greatfrigate"))
            .put(3, Component.translatable("message.creatures.ascensionfrigate"))
            .put(4, Component.translatable("message.creatures.christmasislandfrigate"))
            .put(5, Component.translatable("message.creatures.lesserfrigate"))
            .build();
    public static final Map<Integer, Component> DESCRIPTIONS = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("description.creatures.magnificentfrigate"))
            .put(2, Component.translatable("description.creatures.greatfrigate"))
            .put(3, Component.translatable("description.creatures.ascensionfrigate"))
            .put(4, Component.translatable("description.creatures.christmasislandfrigate"))
            .put(5, Component.translatable("description.creatures.lesserfrigate"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Fregata magnificens")
            .put(2, "Fregata minor")
            .put(3, "Fregata aquila")
            .put(4, "Fregata andrewsi")
            .put(5, "Fregata ariel")
            .build();

    public FrigateEntity(EntityType<? extends FrigateEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected <E extends FrigateEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (this.isFlying()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        }
        if (this.onGround() && this.isOnDisplay() && !this.isBaby() && !(this.getGender() == 0)) {
            return event.setAndContinue(RawAnimation.begin().then("inflate", Animation.LoopType.PLAY_ONCE).thenLoop("inflateidle"));
        } else {
            if (event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
            }
            if (this.isSleeping()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
    }

    protected void registerGoals() {
        super.registerGoals();
        if (!this.isBaby()) {
            this.goalSelector.addGoal(1, new DisplayGoal());
            this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, WaterAnimal.class, false, PREY_SELECTOR));
            this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        }

    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Flying", 0, this::flyAnimController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int numVariants() {
        return 5;
    }

    @Override
    public FrigateEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        FrigateEntity boobyentity = CreaturesEntities.FRIGATE.get().create(p_241840_1_);
        boobyentity.setVariant(this.getVariant());
        boobyentity.setGender(this.random.nextInt(2));
        boobyentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return boobyentity;
    }

    @Override
    public boolean canMate(Animal p_70878_1_) {
        if (p_70878_1_ == this) {
            return false;
        } else if (p_70878_1_.getClass() != this.getClass()) {
            return false;
        } else {
            FrigateEntity ospreyentity = (FrigateEntity) p_70878_1_;
            if (!ospreyentity.isTame()) {
                return false;
            }
            else if (ospreyentity.isInSittingPose()) {
                return false;
            }
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.FRIGATE_AMBIENT.get(); } else {
            return null;
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return CreaturesSound.BOOBY_HURT.get();
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DISPLAYING, false);
        this.entityData.define(ON_DISPLAY, false);
    }

    public void setOnDisplay(boolean p_70918_1_) {
        this.entityData.set(ON_DISPLAY, p_70918_1_);
    }

    public boolean isOnDisplay() {
        return this.entityData.get(ON_DISPLAY);
    }

    public class DisplayGoal extends Goal {
        private final TargetingConditions predicate = TargetingConditions.forNonCombat()
                .range(16.0D)
                .ignoreLineOfSight()
                .selector((e) -> {
                    return ((FrigateEntity) e).getGender() == 0 && !((FrigateEntity) e).isBaby();
                });

        protected DisplayGoal() {
        }

        public boolean canUse() {
            if (FrigateEntity.this.getGender() == 0 || FrigateEntity.this.isSleeping() || FrigateEntity.this.isBaby()) {
                return false;
            }
            List<FrigateEntity> list = FrigateEntity.this.level().getNearbyEntities(
                    FrigateEntity.class, this.predicate, FrigateEntity.this,
                    FrigateEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            return !list.isEmpty();
        }

        public void start() {
            FrigateEntity.this.getNavigation().stop();
            FrigateEntity.this.setOnDisplay(true);
        }

        public void stop() {
            FrigateEntity.this.setOnDisplay(false);
        }

        public boolean canContinueToUse() {
            List<FrigateEntity> list = FrigateEntity.this.level().getNearbyEntities(
                    FrigateEntity.class, this.predicate, FrigateEntity.this,
                    FrigateEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            return !(list.isEmpty() || FrigateEntity.this.isSleeping()
                    || FrigateEntity.this.isInSittingPose() || FrigateEntity.this.isBaby());
        }
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public double getHatchChance() {
        return CreaturesConfig.booby_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.booby_clutch_size.get());
    }

    public String getGenderName() {
        if (this.getGender() == 0) {
            return "f";
        } return "m";
    }

    public void aiStep() {
        super.aiStep();
        Vec3 vector3d = this.getDeltaMovement();
        if (this.isAggressive() & !this.onGround() && vector3d.y < 0.0D) {
            this.setDeltaMovement(vector3d.multiply(1.0D, 2.0D, 1.0D));
        }
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 3) {
            return 3;
        } return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

}
