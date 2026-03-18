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
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class IbisEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.CRAB_PINCERS.get(), CreaturesItems.RAW_SHRIMP.get(), CreaturesItems.GOLDFISH.get());
    public static final Map<Integer, Component> SPECIES_NAMES;

    static {
        Map<Integer, Component> messageMap = new HashMap<>();
        messageMap.put(1, Component.translatable("message.creatures.straw"));
        messageMap.put(2, Component.translatable("message.creatures.scarlet"));
        messageMap.put(3, Component.translatable("message.creatures.green"));
        messageMap.put(4, Component.translatable("message.creatures.madagascan"));
        messageMap.put(5, Component.translatable("message.creatures.crested"));
        messageMap.put(6, Component.translatable("message.creatures.southern"));
        messageMap.put(7, Component.translatable("message.creatures.northernibis"));
        messageMap.put(8, Component.translatable("message.creatures.americanwhiteibis"));
        messageMap.put(9, Component.translatable("message.creatures.glossy"));
        messageMap.put(10, Component.translatable("message.creatures.binchicken"));
        messageMap.put(11, Component.translatable("message.creatures.hadada"));
        SPECIES_NAMES = Collections.unmodifiableMap(messageMap);
    }
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Threskiornis spinicollis")
            .put(2, "Eudocimus ruber")
            .put(3, "Mesembrinibis cayennensis")
            .put(4, "Lophotibis cristata")
            .put(5, "Nipponia nippon")
            .put(6, "Geronticus calvus")
            .put(7, "Geronticus eremita")
            .put(8, "Eudocimus albus")
            .put(9, "Plegadis falcinellus")
            .put(10, "Threskiornis molucca")
            .put(11, "Bostrychia hagedash")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.OCEANIA))
            .put(2, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(3, List.of(Region.SOUTH_AMERICA))
            .put(4, List.of(Region.AFRICA))
            .put(5, List.of(Region.ASIA))
            .put(6, List.of(Region.AFRICA))
            .put(7, List.of(Region.AFRICA))
            .put(8, List.of(Region.NORTH_AMERICA))
            .put(9, List.of(Region.EUROPE, Region.AFRICA, Region.ASIA, Region.OCEANIA))
            .put(10, List.of(Region.OCEANIA))
            .put(11, List.of(Region.AFRICA))
            .build();

    public IbisEntity(EntityType<? extends IbisEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS,false));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
    }

    protected <E extends IbisEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() || this.isFlying()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
    } if (this.isSleeping()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
    } else {
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle")); }
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
    }

    public int numVariants() {
        return 11;
    }

    @Override
    public IbisEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        IbisEntity ibisentity = CreaturesEntities.IBIS.get().create(p_241840_1_);
        ibisentity.setVariant(this.getVariant());
        ibisentity.setGender(this.random.nextInt(2));
        ibisentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return ibisentity;
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
        return CreaturesSound.IBIS_AMBIENT.get(); } else {
            return null;
        }
    }

    public ItemStack getFoodItem() {
        return new ItemStack(CreaturesItems.CRAB_PINCERS.get(), 1);
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

    public double getHatchChance() {
        return CreaturesConfig.ibis_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.ibis_clutch_size.get());
    }

    public int getMaxFlockSize() {
        return 10;
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.5F;
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 4) {
            return 1;
        } if (this.getVariant() == 5 || this.getVariant() == 7) {
            return 3;
        } if (this.getVariant() == 6) {
            return 2;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

}
