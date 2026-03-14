package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
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
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;

public class PeafowlEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private PanicGoal PanicGoal;
    private static final EntityDataAccessor<Boolean> DISPLAYING = SynchedEntityData.defineId(PeafowlEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> ON_DISPLAY = SynchedEntityData.defineId(PeafowlEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.PUMPKIN_SEEDS, CreaturesItems.MEALWORMS.get());
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.greenpeafowl"),
            2, Component.translatable("message.creatures.indianpeafowl"),
            3, Component.translatable("message.creatures.albinopeafowl")
    );
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Pavo muticus")
            .put(2, "Pavo cristatus")
            .put(3, "Pavo cristatus")
            .build();

    public PeafowlEntity(EntityType<? extends PeafowlEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected <E extends PeafowlEntity> PlayState walkAnimController(final AnimationState<E> event)
    {
        if (this.isInSittingPose()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
        }
        else if (this.isOnDisplay() && !this.isBaby() && !(this.getGender() == 0)) {
            if (event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("displaywalk"));
            }
            return event.setAndContinue(RawAnimation.begin().then("display", Animation.LoopType.PLAY_ONCE).thenLoop("displayidle"));
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
            this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
            this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
            this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)));
            this.targetSelector.removeGoal(PanicGoal);
            this.goalSelector.addGoal(1, new PeafowlEntity.DisplayGoal());
            //this.targetSelector.addGoal(2, new CreaturesBirdEntity.DefendBabyGoal());
        }

    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Walking", 0, this::walkAnimController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int numVariants() {
        return 3;
    }

    @Override
    public PeafowlEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        PeafowlEntity peafowlentity = CreaturesEntities.PEAFOWL.get().create(p_241840_1_);
        peafowlentity.setVariant(this.getVariant());
        peafowlentity.setGender(this.random.nextInt(2));
        peafowlentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return peafowlentity;
    }

    @Override
    public boolean canMate(Animal p_70878_1_) {
        if (p_70878_1_ == this) {
            return false;
        } else if (p_70878_1_.getClass() != this.getClass()) {
            return false;
        } else {
            PeafowlEntity ospreyentity = (PeafowlEntity) p_70878_1_;
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
        return CreaturesSound.PEAFOWL_AMBIENT.get(); } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.PEAFOWL;
    }

    @Override
    public int methodOfDeterminingVariant() {
        int j = this.random.nextInt(100);
        if (j < 48) {
            return 1;
        } if (j < 98) {
            return 2;
        } else {
            return 3;
        }
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
                    return ((PeafowlEntity) e).getGender() == 0 && !((PeafowlEntity) e).isBaby();
                });

        protected DisplayGoal() {
        }

        public boolean canUse() {
            if (PeafowlEntity.this.getGender() == 0 || PeafowlEntity.this.isSleeping() || PeafowlEntity.this.isBaby()) {
                return false;
            }
            List<PeafowlEntity> list = PeafowlEntity.this.level().getNearbyEntities(
                    PeafowlEntity.class, this.predicate, PeafowlEntity.this,
                    PeafowlEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            return !list.isEmpty();
        }

        public void start() {
            PeafowlEntity.this.getNavigation().stop();
            PeafowlEntity.this.setOnDisplay(true);
        }

        public void stop() {
            PeafowlEntity.this.setOnDisplay(false);
        }

        public boolean canContinueToUse() {
            List<PeafowlEntity> list = PeafowlEntity.this.level().getNearbyEntities(
                    PeafowlEntity.class, this.predicate, PeafowlEntity.this,
                    PeafowlEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D));
            return !(list.isEmpty() || PeafowlEntity.this.isSleeping()
                    || PeafowlEntity.this.isInSittingPose() || PeafowlEntity.this.isBaby());
        }
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public double getHatchChance() {
        return CreaturesConfig.peafowl_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.peafowl_clutch_size.get());
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 1) {
            return 3;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public boolean canTame() {
        return true;
    }

}
