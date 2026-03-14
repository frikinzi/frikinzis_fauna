package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import net.minecraftforge.common.ForgeMod;

public class WoodDuckEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public int featherTime = this.random.nextInt(6000) + 6000;
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.SWEET_BERRIES);

    public WoodDuckEntity(EntityType<? extends WoodDuckEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 1.0F);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1.5D, 1.2D));
    }

    protected <E extends WoodDuckEntity> PlayState walkAnimController(final AnimationState<E> event) {
        if (this.isBaby()) {
            if (event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walking"));
            } if (!this.onGround() && !this.isInWater()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("flying"));
            } if (this.isSleeping()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
        if (event.isMoving()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() && !this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        } if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
        } if (this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(ForgeMod.SWIM_SPEED.get(), 3.0).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide && this.isAlive() && CreaturesConfig.drop_feather.get() && !this.isBaby() && --this.featherTime <= 0) {
            this.spawnAtLocation(CreaturesItems.DUCK_FEATHER.get());
            this.featherTime = this.random.nextInt(6000) + 6000;
        }
    }

    public int numVariants() {
        return 1;
    }

    @Override
    public WoodDuckEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        WoodDuckEntity woodduckentity = CreaturesEntities.WOOD_DUCK.get().create(p_241840_1_);
        woodduckentity.setVariant(this.getVariant());
        woodduckentity.setGender(this.random.nextInt(2));
        woodduckentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return woodduckentity;
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return this.random.nextInt(4) == 0 ? CreaturesSound.WOOD_DUCK_AMBIENT1.get() : CreaturesSound.WOOD_DUCK_AMBIENT2.get(); } else {
            return null;
        }
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.DUCK;
    }

    public double getHatchChance() {
        return CreaturesConfig.wood_duck_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.wood_duck_clutch_size.get());
    }

    public int getMaxFlockSize() {
        return 4;
    }

    public String getScientificName() {
        return "Aix sponsa";
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.woodduck");
    }

}
