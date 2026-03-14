package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
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
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Set;

public class TanagerEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.MEALWORMS.get(), Items.MELON_SLICE, Items.SWEET_BERRIES);
    public static final Map<Integer, Component> SPECIES_NAMES;

    static {
        Map<Integer, Component> messageMap = new HashMap<>();
        messageMap.put(1, Component.translatable("message.creatures.paradisetanager"));
        messageMap.put(2, Component.translatable("message.creatures.spangledberyl"));
        messageMap.put(3, Component.translatable("message.creatures.cherrythroated"));
        messageMap.put(4, Component.translatable("message.creatures.greenheaded"));
        messageMap.put(5, Component.translatable("message.creatures.redheadedtanager"));
        messageMap.put(6, Component.translatable("message.creatures.scarlettanager"));
        messageMap.put(7, Component.translatable("message.creatures.silverbeaked"));
        messageMap.put(8, Component.translatable("message.creatures.multicoloredtanager"));
        messageMap.put(9, Component.translatable("message.creatures.bluegraytanager"));
        SPECIES_NAMES = Collections.unmodifiableMap(messageMap);
    }
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Tangara chilensis")
            .put(2, "Tangara nigroviridis")
            .put(3, "Nemosia rourei")
            .put(4, "Tangara seledon")
            .put(5, "Piranga erythrocephala")
            .put(6, "Piranga olivacea")
            .put(7, "Ramphocelus carbo")
            .put(8, "Chlorochrysa nitidissima")
            .put(9, "Thraupis episcopus")
            .build();

    public TanagerEntity(EntityType<? extends TanagerEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(6, new FollowFlockLeaderGoal(this));
    }

    protected <E extends TanagerEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() || this.isFlying()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
    } if (this.isSleeping()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
    }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
    }

    public int numVariants() {
        return 9;
    }

    @Override
    public TanagerEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        TanagerEntity bushtitEntity = CreaturesEntities.TANAGER.get().create(p_241840_1_);
        bushtitEntity.setVariant(this.getVariant());
        bushtitEntity.setGender(this.random.nextInt(2));
        bushtitEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return bushtitEntity;
    }

    @Override
    public boolean canMate(Animal p_70878_1_) {
        if (p_70878_1_ == this) {
            return false;
        } else if (p_70878_1_.getClass() != this.getClass()) {
            return false;
        } else {
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.TANAGER_AMBIENT.get(); } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.MELON_SLICE, 1);
    }

    @Override
    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.bushtit_clutch_size.get());
    }

    @Override
    public double getHatchChance() {
        return CreaturesConfig.bushtit_hatch_chance.get();
    }

    public int getMaxFlockSize() {
        return 4;
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.3F;
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 3) {
            return 4;
        } if (this.getVariant() == 8) {
            return 1;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

}
