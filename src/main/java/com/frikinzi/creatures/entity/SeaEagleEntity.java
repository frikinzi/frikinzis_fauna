package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.RaptorBase;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Map;
import java.util.function.Predicate;

public class SeaEagleEntity extends RaptorBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.RAW_TROUT.get(), Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH, Items.COD, CreaturesItems.RAW_KOI.get(), CreaturesItems.RAW_ARAPAIMA.get(), CreaturesItems.RAW_RED_SNAPPER.get(), CreaturesItems.RAW_PIKE.get());
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.RABBIT || entitytype == EntityType.SALMON || entitytype == EntityType.COD;
    };
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.stellersseaeagle"))
            .put(2, Component.translatable("message.creatures.baldeagle"))
            .put(3, Component.translatable("message.creatures.whitetailedeagle"))
            .put(4, Component.translatable("message.creatures.africanfisheagle"))
            .put(5, Component.translatable("message.creatures.whitebelliedeagle"))
            .build();
    public static final Map<Integer, Component> DESCRIPTIONS = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("description.creatures.stellersseaeagle"))
            .put(2, Component.translatable("description.creatures.baldeagle"))
            .put(3, Component.translatable("description.creatures.whitetailedeagle"))
            .put(4, Component.translatable("description.creatures.africanfisheagle"))
            .put(5, Component.translatable("description.creatures.whitebelliedeagle"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Haliaeetus pelagicus")
            .put(2, "Haliaeetus leucocephalus")
            .put(3, "Haliaeetus albicilla")
            .put(4, "Haliaeetus vocifer")
            .put(5, "Haliaeetus leucogaster")
            .build();

    public SeaEagleEntity(EntityType<? extends SeaEagleEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected <E extends SeaEagleEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (!this.isBaby() && this.isAggressive() && !this.onGround() & this.getDeltaMovement().y < 0.0D) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("attack"));
        }
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        }
        if (!this.onGround() || this.isFlying() && !this.isBaby()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        }
        if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
        } if (this.isInSittingPose()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sit"));
    }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    protected void registerGoals() {
        super.registerGoals();
        if (!this.isBaby() && CreaturesConfig.raptor_attacks.get() == true) {
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, LivingEntity.class, false, PREY_SELECTOR));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int numVariants() {
        return 5;
    }

    @Override
    public SeaEagleEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        SeaEagleEntity stellersseaeagleentity = CreaturesEntities.SEA_EAGLE.get().create(p_241840_1_);
        stellersseaeagleentity.setVariant(this.getVariant());
        stellersseaeagleentity.setGender(this.random.nextInt(2));
        stellersseaeagleentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return stellersseaeagleentity;
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
        return CreaturesSound.STELLERS_SEA_EAGLE_AMBIENT.get(); }
        return null;
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.BIRD_OF_PREY;
    }

//    public static boolean checkSSESpawnRules(EntityType<? extends Animal> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
//        return p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.SAND) || p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.GRASS_BLOCK) || p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.SNOW) || p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.SNOW_BLOCK) && p_223316_1_.getRawBrightness(p_223316_3_, 0) > 8;
//    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.COD, 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.stellers_sea_eagle_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.stellers_sea_eagle_clutch_size.get());
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public Component getFunFact() {
        Component translatable = DESCRIPTIONS.get(this.getVariant());
        if (translatable != null) {
            return translatable;
        } return Component.translatable("creatures.unknown");
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 1) {
            return 2; // vulnerable
        }
        return super.getIUCNStatus();
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)|| p_218106_.getBlockState(p_218108_.below()).is(BlockTags.SAND) || p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ICE) || p_218106_.getBlockState(p_218108_.below()).is(BlockTags.SNOW)) && isBrightEnoughToSpawn(p_218106_, p_218108_);
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public boolean canTame() {
        return true;
    }

}
