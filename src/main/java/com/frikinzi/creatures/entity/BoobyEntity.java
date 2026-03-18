package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class BoobyEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private PanicGoal PanicGoal;
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.TROPICAL_FISH || entitytype == EntityType.SALMON || entitytype == EntityType.COD;
    };
    private static final EntityDataAccessor<Boolean> DISPLAYING = SynchedEntityData.defineId(BoobyEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> ON_DISPLAY = SynchedEntityData.defineId(BoobyEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.TROPICAL_FISH, Items.SALMON);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.northerngannet"))
            .put(2, Component.translatable("message.creatures.australasiangannet"))
            .put(3, Component.translatable("message.creatures.capegannet"))
            .put(4, Component.translatable("message.creatures.redfootedbooby"))
            .put(5, Component.translatable("message.creatures.nazcabooby"))
            .put(6, Component.translatable("message.creatures.maskedbooby"))
            .put(7, Component.translatable("message.creatures.brownbooby"))
            .put(8, Component.translatable("message.creatures.abbottsbooby"))
            .put(9, Component.translatable("message.creatures.bluefootedbooby"))
            .put(10, Component.translatable("message.creatures.peruvianbooby"))
            .put(11, Component.translatable("message.creatures.brownbooby"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Morus bassanus")
            .put(2, "Morus serrator")
            .put(3, "Morus capensis")
            .put(4, "Sula sula")
            .put(5, "Sula granti")
            .put(6, "Sula dactylatra")
            .put(7, "Sula leucogaster")
            .put(8, "Papasula abbotti")
            .put(9, "Sula nebouxii")
            .put(10, "Sula variegata")
            .put(11, "Sula brewsteri")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE, Region.NORTH_AMERICA, Region.AFRICA))
            .put(2, List.of(Region.OCEANIA))
            .put(3, List.of(Region.AFRICA))
            .put(4, List.of(Region.ASIA, Region.OCEANIA, Region.SOUTH_AMERICA))
            .put(5, List.of(Region.SOUTH_AMERICA, Region.NORTH_AMERICA))
            .put(6, List.of(Region.ASIA, Region.OCEANIA, Region.NORTH_AMERICA, Region.SOUTH_AMERICA, Region.AFRICA))
            .put(7, List.of(Region.ASIA, Region.OCEANIA, Region.NORTH_AMERICA, Region.SOUTH_AMERICA, Region.AFRICA))
            .put(8, List.of(Region.OCEANIA))
            .put(9, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(10, List.of(Region.SOUTH_AMERICA))
            .put(11, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .build();

    public BoobyEntity(EntityType<? extends BoobyEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected <E extends BoobyEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (this.isAggressive() && !this.onGround() & this.getDeltaMovement().y < 0.0D) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("dive"));
        }
        if (this.isFlying()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        }
        if (this.onGround() && this.isOnDisplay() && !this.isBaby() && !(this.getGender() == 0)) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("dance"));
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
        return 11;
    }

    @Override
    public BoobyEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        BoobyEntity boobyentity = CreaturesEntities.BOOBY.get().create(p_241840_1_);
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
            BoobyEntity ospreyentity = (BoobyEntity) p_70878_1_;
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
        return CreaturesSound.BOOBY.get(); } else {
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

    public void setDisplaying(boolean p_70918_1_) {
        this.entityData.set(DISPLAYING, p_70918_1_);
    }

    public boolean isDisplaying() {
        return this.entityData.get(DISPLAYING);
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
                    return ((BoobyEntity) e).getGender() == 0 && !((BoobyEntity) e).isBaby();
                });

        protected DisplayGoal() {
        }

        public boolean canUse() {
            if (BoobyEntity.this.getGender() == 0 || BoobyEntity.this.isSleeping() || BoobyEntity.this.isBaby()) {
                return false;
            }
            List<BoobyEntity> list = BoobyEntity.this.level().getNearbyEntities(
                    BoobyEntity.class, this.predicate, BoobyEntity.this,
                    BoobyEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            return !list.isEmpty();
        }

        public void start() {
            BoobyEntity.this.getNavigation().stop();
            BoobyEntity.this.setOnDisplay(true);
        }

        public void stop() {
            BoobyEntity.this.setOnDisplay(false);
        }

        public boolean canContinueToUse() {
            List<BoobyEntity> list = BoobyEntity.this.level().getNearbyEntities(
                    BoobyEntity.class, this.predicate, BoobyEntity.this,
                    BoobyEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            return !(list.isEmpty() || BoobyEntity.this.isSleeping()
                    || BoobyEntity.this.isInSittingPose() || BoobyEntity.this.isBaby());
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

    public boolean isSexuallyDimorphic() {
        if (this.getVariant() == 4 || this.getVariant() == 7||this.getVariant() == 8||this.getVariant() == 9||this.getVariant() == 10||this.getVariant() == 11) {
            return true;
        } return false;
    }

    public String getGenderName() {
        if (!this.isSexuallyDimorphic()) {
            return "";
        }
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
