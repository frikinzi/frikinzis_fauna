package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.FleeGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
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

import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.google.common.collect.ImmutableMap;
import net.minecraftforge.common.ForgeMod;

import java.util.*;

public class PuffinEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, CreaturesItems.CRAB_PINCERS.get(), CreaturesItems.RAW_TROUT.get());
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.atlanticpuffin"))
            .put(2, Component.translatable("message.creatures.hornedpuffin"))
            .put(3, Component.translatable("message.creatures.tuftedpuffin"))
            .put(4, Component.translatable("message.creatures.atlanticpuffinleucistic"))
            .put(5, Component.translatable("message.creatures.atlanticpuffinpiebald"))
            .build();
    public static Map<Integer, Component> DESCRIPTIONS = new HashMap<Integer, Component>() {{
        put(1, Component.translatable("description.creatures.atlanticpuffin"));
        put(2, Component.translatable("description.creatures.hornedpuffin"));
        put(3, Component.translatable("description.creatures.tuftedpuffin"));
        put(4, Component.translatable("description.creatures.atlanticpuffinleucistic"));
        put(5, Component.translatable("description.creatures.atlanticpuffinpiebald"));
    }};
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE, Region.NORTH_AMERICA))
            .put(2, List.of(Region.NORTH_AMERICA, Region.ASIA))
            .put(3, List.of(Region.NORTH_AMERICA, Region.ASIA))
            .put(4, List.of(Region.EUROPE, Region.NORTH_AMERICA))
            .put(5, List.of(Region.EUROPE, Region.NORTH_AMERICA))
            .build();
    public static Map<Integer, String> SCIENTIFIC_NAMES = new HashMap<Integer, String>() {{
        put(1, "Fratercula arctica");
        put(2, "Fratercula corniculata");
        put(3, "Fratercula cirrhata");
        put(4, "Fratercula arctica");
        put(5, "Fratercula arctica");
    }};

    public PuffinEntity(EntityType<? extends PuffinEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS,false));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Cod.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Salmon.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TropicalFish.class, false));
        this.goalSelector.addGoal(6, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(4, new FleeGoal<>(this, Player.class, 6.0F, 1.0D, 1.5D));


    }

    protected <E extends PuffinEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround() || this.isInWater()) {
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(ForgeMod.SWIM_SPEED.get(), 3.0).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    public int numVariants() {
        return 5;
    }

    @Override
    public int methodOfDeterminingVariant() {
        if (CreaturesConfig.breed_only_variants.get()) {
            int i = this.random.nextInt(numVariants())+1;
            while (i == 4 || i == 5) {
                i = this.random.nextInt(numVariants())+1;
            }
            return i; }

        else {
            return this.random.nextInt(numVariants())+1;
        }

    }

    @Override
    public PuffinEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        PuffinEntity puffinentity = CreaturesEntities.PUFFIN.get().create(p_241840_1_);
        puffinentity.setVariant(this.getVariant());
        puffinentity.setGender(this.random.nextInt(2));
        puffinentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return puffinentity;
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
        return CreaturesSound.PUFFIN.get(); } else {
            return null;
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return CreaturesSound.PUFFIN_HURT.get();
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
        return new ItemStack(Items.COD, 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.puffin_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.puffin_clutch_size.get());
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)|| p_218106_.getBlockState(p_218108_.below()).is(BlockTags.SAND)|| p_218106_.getBlockState(p_218108_.below()).is(BlockTags.BASE_STONE_OVERWORLD) )&& isBrightEnoughToSpawn(p_218106_, p_218108_);
    }


    public int getMaxFlockSize() {
        return 10;
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 1) {
            return 2; //atlantic is vulnerable
        } return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        Component translatable = DESCRIPTIONS.get(this.getVariant());
        if (translatable != null) {
            return translatable;
        } return Component.translatable("creatures.unknown");
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

    public EggEntity layEgg(CreaturesBirdEntity animal) {
        EggEntity egg = super.layEgg(animal);
        if (egg.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.puffin_mutation_chance.get())==1) { // one in mutation_chance of getting a piebald or leucistic
                if (this.random.nextInt(2) == 1) {
                    egg.setVariant(4);
                } else {
                    egg.setVariant(5);
                }
            }
        }
        return egg;
    }

}
