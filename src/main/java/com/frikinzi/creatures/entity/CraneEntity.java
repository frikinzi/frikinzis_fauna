package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.FleeGoal;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CraneEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Integer> VARIANT_SUBID = SynchedEntityData.defineId(CraneEntity.class, EntityDataSerializers.INT);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS, CreaturesItems.MEALWORMS.get(), Items.SWEET_BERRIES);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.redcrownedcrane"))
            .put(2, Component.translatable("message.creatures.greycrownedcrane"))
            .put(3, Component.translatable("message.creatures.blackcrowncrane"))
            .put(4, Component.translatable("message.creatures.whoopingcrane"))
            .put(5, Component.translatable("message.creatures.blackneckedcrane"))
            .put(6, Component.translatable("message.creatures.sandhillcrane"))
            .put(7, Component.translatable("message.creatures.whitenapedcrane"))
            .put(8, Component.translatable("message.creatures.saruscrane"))
            .put(9, Component.translatable("message.creatures.wattledcrane"))
            .put(10, Component.translatable("message.creatures.demoisellecrane"))
            .put(11, Component.translatable("message.creatures.hoodedcrane"))
            .put(12, Component.translatable("message.creatures.commoncrane"))
            .put(13, Component.translatable("message.creatures.bolga"))
            .put(14, Component.translatable("message.creatures.siberiancrane"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Grus japonensis")
            .put(2, "Balearica regulorum")
            .put(3, "Balearica pavonina")
            .put(4, "Grus americana")
            .put(5, "Grus nigricollis")
            .put(6, "Antigone canadensis")
            .put(7, "Antigone vipio")
            .put(8, "Antigone antigone")
            .put(9, "Grus carunculatus")
            .put(10, "Grus virgo")
            .put(11, "Grus monacha")
            .put(12, "Grus grus")
            .put(13, "Antigone rubicunda")
            .put(14, "Leucogeranus leucogeranus")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.ASIA))
            .put(2, List.of(Region.AFRICA))
            .put(3, List.of(Region.AFRICA))
            .put(4, List.of(Region.NORTH_AMERICA))
            .put(5, List.of(Region.ASIA))
            .put(6, List.of(Region.NORTH_AMERICA))
            .put(7, List.of(Region.ASIA))
            .put(8, List.of(Region.ASIA))
            .put(9, List.of(Region.AFRICA))
            .put(10, List.of(Region.ASIA))
            .put(11, List.of(Region.ASIA))
            .put(12, List.of(Region.ASIA, Region.EUROPE, Region.AFRICA))
            .put(13, List.of(Region.OCEANIA))
            .put(14, List.of(Region.ASIA))
            .build();

    public static final Map<Integer, Float> HEIGHT_MAP = ImmutableMap.<Integer, Float>builder()
            .put(1, 1.0f)
            .put(2, 1.0f)
            .put(3, 1.0f)
            .put(4, 1.0f)
            .put(5, 1.0f)
            .put(6, 1.0f)
            .put(7, 1.0f)
            .put(8, 1.3f)
            .put(9, 1.3f)
            .put(10, 0.8f)
            .put(11, 1.0f)
            .put(12, 1.0f)
            .put(13,1.0f)
            .put(14, 1.0f)
            .build();

    public CraneEntity(EntityType<? extends CraneEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 3 || this.getVariant() == 1 || this.getVariant() == 7 || this.getVariant() == 9|| this.getVariant() == 11 || this.getVariant() == 8) {
            return 2;
        } else if (this.getVariant() == 5) {
            return 1;
        } else if (this.getVariant() == 2 || this.getVariant() == 4) {
            return 3;
        } else if (this.getVariant() == 14) {
            return 4;
        } else return super.getIUCNStatus();
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false ));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(4, new FleeGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D));
    }

    protected <E extends CraneEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() || this.isFlying()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
    } if (this.isSleeping()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
    } if (this.isGrooming()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("groom"));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
    }

    public int numVariants() {
        return 14;
    }

    @Override
    public CraneEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        CraneEntity crane = CreaturesEntities.CRANE.get().create(p_241840_1_);
        crane.setVariant(this.getVariant());
        crane.setGender(this.random.nextInt(2));
        crane.setHeightMultiplier(getSpawnEggOffspringHeight());
        return crane;
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)|| p_218106_.getBlockState(p_218108_.below()).is(BlockTags.SAND) )&& isBrightEnoughToSpawn(p_218106_, p_218108_);
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
        return CreaturesSound.CRANE.get(); } else {
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
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public double getHatchChance() {
        return CreaturesConfig.crane_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.crane_clutch_size.get());
    }

    public int getMaxFlockSize() {
        return 10;
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.3F;
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public void aiStep() {
        if (!this.level().isClientSide) {
            if (this.isBaby()) {
                this.getNavigation().stop();
            }
            int i = this.random.nextInt(3000);

            if (i == 0 && !this.isInWater() && !this.isSleeping() && !this.isBaby()) {
                this.getNavigation().stop();
                this.setGrooming(true);
            }
            if ((i == 1 || this.isInWater()) && this.isGrooming()) {
                this.setGrooming(false);
            }
        }
        super.aiStep();
    }

    public int getScaleforGUI() {
        if (this.isBaby()) {
            return (int)(super.getScaleforGUI() *1.2f);

        }
        return (int)(super.getScaleforGUI() *0.9f);
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.crane");
    }

    public int methodOfDeterminingVariant() {
        int[] iucnWeights = { 100, 60, 30, 15, 5, 1, 0, 50 };

        // Determine biome restrictions
        Holder<Biome> biome = this.level().getBiome(this.blockPosition());
        boolean isSavanna = biome.is(BiomeTags.IS_SAVANNA);
        boolean isSwamp   = biome.is(Tags.Biomes.IS_SWAMP);

        List<Integer> pool = new ArrayList<>();
        for (int v = 1; v <= numVariants(); v++) {

            // Savanna — only variants 2 and 3
            if (isSavanna && !isSwamp && (v != 2 && v != 3)) continue;

            this.setVariant(v);
            int status = this.getIUCNStatus();
            int weight = (status >= 0 && status < iucnWeights.length) ? iucnWeights[status] : 50;
            for (int w = 0; w < weight; w++) {
                pool.add(v);
            }
        }

        if (pool.isEmpty()) {
            return this.random.nextInt(numVariants()) + 1;
        }

        return pool.get(this.random.nextInt(pool.size()));
    }

    public float getSizeMultiplier() {
        Float size = HEIGHT_MAP.get(this.getVariant());
        if (size == null) size = 1.0f;

        return size;
    }
    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }




}
