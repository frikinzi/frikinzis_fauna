package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ShoebillEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private PanicGoal PanicGoal;
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.AFRICA))
            .put(2, List.of(Region.AFRICA))
            .put(3, List.of(Region.AFRICA))
            .build();
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, CreaturesItems.RAW_LUNGFISH.get());

    public ShoebillEntity(EntityType<? extends ShoebillEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.removeGoal(PanicGoal);
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS,false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, LungfishEntity.class, false));
    }

    protected <E extends ShoebillEntity> PlayState walkAnimController(final AnimationState<E> event) {
        if (event.isMoving()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int getAmbientSoundInterval() {
        return 600;
    }

    public int numVariants() {
        return 3;
    }

    @Override
    public ShoebillEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        ShoebillEntity wildduckentity = CreaturesEntities.SHOEBILL.get().create(p_241840_1_);
        wildduckentity.setVariant(this.getVariant());
        wildduckentity.setGender(this.random.nextInt(2));
        wildduckentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return wildduckentity;
    }

    @Override
    public boolean canMate(Animal p_70878_1_) {
        if (p_70878_1_ == this) {
            return false;
        } else if (p_70878_1_.getClass() != this.getClass()) {
            return false;
        }
        else {
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.SHOEBILL.get();
        }
        else
        {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.LARGE_BIRD_GENERIC;
    }

    public double getHatchChance() {
        return CreaturesConfig.shoebill_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.shoebill_clutch_size.get());
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.SALMON, 1);
    }

    public int getIUCNStatus() {
        return 2;
    }

    public String getScientificName() {
        return "Balaeniceps rex";
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.shoebill");
    }

    public int getScaleforGUI() {
        return (int)(super.getScaleforGUI() *2.0f);
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

}
