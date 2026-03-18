package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.ai.SitOnShoulderGoal;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoveEntity extends CreaturesFlyingBird implements GeoEntity {
    final private int[] jungle_variants = new int[] {1,3,5,7,8,13,14};
    final private int[] swamp_variant = new int[] {9};
    final private int[] mountain_variant = new int[] {12};
    final private int[] forest_variant = new int[] {2,4,6,11,10,15,17};
    final private int[] mesa_variant = new int[] {16};
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    private static final Ingredient FRUIT_ITEMS = Ingredient.of(Items.CHORUS_FRUIT, Items.SWEET_BERRIES, Items.APPLE, Items.MELON_SLICE);
    public static Map<Integer, Component> SPECIES_NAMES;
    static {
        Map<Integer, Component> map = new HashMap<>();
        map.put(1, Component.translatable("message.creatures.dove.jambu"));
        map.put(2, Component.translatable("message.creatures.dove.release"));
        map.put(3, Component.translatable("message.creatures.dove.rose"));
        map.put(4, Component.translatable("message.creatures.dove.rock"));
        map.put(5, Component.translatable("message.creatures.dove.flame"));
        map.put(6, Component.translatable("message.creatures.dove.goldenheart"));
        map.put(7, Component.translatable("message.creatures.dove.mbleeding"));
        map.put(8, Component.translatable("message.creatures.dove.orangebellied"));
        map.put(9, Component.translatable("message.creatures.dove.victoria"));
        map.put(10, Component.translatable("message.creatures.dove.mourning"));
        map.put(11, Component.translatable("message.creatures.dove.europeanturtle"));
        map.put(12, Component.translatable("message.creatures.dove.snow"));
        map.put(13, Component.translatable("message.creatures.dove.nicobar"));
        map.put(14, Component.translatable("message.creatures.dove.pacificemerald"));
        map.put(15, Component.translatable("message.creatures.dove.crested"));
        map.put(16, Component.translatable("message.creatures.dove.spinifex"));
        map.put(17, Component.translatable("message.creatures.dove.pink"));
        SPECIES_NAMES = Collections.unmodifiableMap(map);
    }
    public static Map<Integer, Component> DESCRIPTIONS;
    static {
        Map<Integer, Component> map = new HashMap<>();
        map.put(1, Component.translatable("description.creatures.dove.jambu"));
        map.put(2, Component.translatable("description.creatures.dove.release"));
        map.put(3, Component.translatable("description.creatures.dove.rose"));
        map.put(4, Component.translatable("description.creatures.dove.rock"));
        map.put(5, Component.translatable("description.creatures.dove.flame"));
        map.put(6, Component.translatable("description.creatures.dove.goldenheart"));
        map.put(7, Component.translatable("description.creatures.dove.mbleeding"));
        map.put(8, Component.translatable("description.creatures.dove.orangebellied"));
        map.put(9, Component.translatable("description.creatures.dove.victoria"));
        map.put(10, Component.translatable("description.creatures.dove.mourning"));
        map.put(11, Component.translatable("description.creatures.dove.europeanturtle"));
        map.put(12, Component.translatable("description.creatures.dove.snow"));
        map.put(13, Component.translatable("description.creatures.dove.nicobar"));
        map.put(14, Component.translatable("description.creatures.dove.pacificemerald"));
        map.put(15, Component.translatable("description.creatures.dove.crested"));
        map.put(16, Component.translatable("description.creatures.dove.spinifex"));
        map.put(17, Component.translatable("description.creatures.dove.pink"));
        DESCRIPTIONS = Collections.unmodifiableMap(map);
    }
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Ptilinopus jambu")
            .put(2, "Columba livia")
            .put(3, "Ptilinopus regina")
            .put(4, "Columba livia")
            .put(5, "Ptilinopus victor")
            .put(6, "Gallicolumba rufigula")
            .put(7, "Gallicolumba crinigera")
            .put(8, "Ptilinopus iozonus")
            .put(9, "Goura victoria")
            .put(10, "Zenaida macroura")
            .put(11, "Streptopelia turtur")
            .put(12, "Columba leuconota")
            .put(13, "Caloenas nicobarica")
            .put(14, "Chalcophaps longirostris")
            .put(15, "Ocyphaps lophotes")
            .put(16, "Geophaps plumifera")
            .put(17, "Nesoenas mayeri")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.ASIA))
            .put(2, List.of(Region.EUROPE, Region.ASIA, Region.AFRICA, Region.NORTH_AMERICA, Region.SOUTH_AMERICA, Region.OCEANIA))
            .put(3, List.of(Region.ASIA, Region.OCEANIA))
            .put(4, List.of(Region.EUROPE, Region.ASIA, Region.AFRICA, Region.NORTH_AMERICA, Region.SOUTH_AMERICA, Region.OCEANIA))
            .put(5, List.of(Region.OCEANIA))
            .put(6, List.of(Region.ASIA, Region.OCEANIA))
            .put(7, List.of(Region.ASIA))
            .put(8, List.of(Region.OCEANIA))
            .put(9, List.of(Region.OCEANIA))
            .put(10, List.of(Region.NORTH_AMERICA))
            .put(11, List.of(Region.EUROPE, Region.AFRICA, Region.ASIA))
            .put(12, List.of(Region.ASIA))
            .put(13, List.of(Region.ASIA, Region.OCEANIA))
            .put(14, List.of(Region.OCEANIA, Region.ASIA))
            .put(15, List.of(Region.OCEANIA))
            .put(16, List.of(Region.OCEANIA))
            .put(17, List.of(Region.AFRICA))
            .build();
    
    public DoveEntity(EntityType<? extends DoveEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected <E extends DoveEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() || this.isFlying()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
    } if (this.isSleeping()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
    } if (this.isInSittingPose()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sit"));
    }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(6, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(3, new SitOnShoulderGoal(this));
    }

    public int getMaxFlockSize() {
        return 10;
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
        return 17;
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
        if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 6 || this.getVariant() == 7 || this.getVariant() == 10 || this.getVariant() == 11 || this.getVariant() == 15) {
            return FOOD_ITEMS.test(p_70877_1_);
        } else {
            return FRUIT_ITEMS.test(p_70877_1_);
        }

    }

    public Ingredient getBirdFood() {
        if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 6 || this.getVariant() == 7 || this.getVariant() == 16) {
            return Ingredient.of(Items.BEETROOT_SEEDS, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.PUMPKIN_SEEDS);
        } else {
            return Ingredient.of(Items.MELON_SLICE, Items.APPLE, Items.SWEET_BERRIES, Items.CHORUS_FRUIT);
        }
    }

    public SoundEvent getAmbientSound() {
        if (this.getVariant() == 15 && this.isFlying()) {
            return CreaturesSound.CRESTED_PIGEON.get();
        }
        if (!this.isSleeping()) {
            if (this.getVariant() == 10) {
                return CreaturesSound.MOURNING_DOVE.get();
            }
            return CreaturesSound.DOVE_AMBIENT.get();
        }
        else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    @Override
    public int methodOfDeterminingVariant() {
        if (CreaturesConfig.biome_only_variants.get()) {
            Holder<Biome> biome = this.level().getBiome(this.blockPosition());
            if (biome.is(BiomeTags.IS_BADLANDS)) {
                return mesa_variant[this.random.nextInt(mesa_variant.length)];
            }
            if (biome.is(BiomeTags.IS_JUNGLE)) {
                return jungle_variants[this.random.nextInt(jungle_variants.length)];
            }
            if (biome.is(BiomeTags.IS_FOREST)) {
                return forest_variant[this.random.nextInt(forest_variant.length)];
            }
            if (biome.is(BiomeTags.IS_MOUNTAIN)) {
                return mountain_variant[this.random.nextInt(mountain_variant.length)];
            }
            if (biome.is(BiomeTags.HAS_SWAMP_HUT)) {
                return swamp_variant[this.random.nextInt(swamp_variant.length)];
            }
        }
        return this.random.nextInt(18);
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public ItemStack getFoodItem() {

        if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 6 || this.getVariant() == 7 || this.getVariant() == 16) {
            return new ItemStack(Items.WHEAT_SEEDS, 1); } else {
            return new ItemStack(Items.SWEET_BERRIES, 1);
        }
    }

    public double getHatchChance() {
        return CreaturesConfig.dove_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.dove_clutch_size.get());
    }

    public String getGenderName() {
        if (this.getGender() == 0) {
            return "f";
        } else {
            return "m";
        }
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 1 || this.getVariant() == 9 || this.getVariant() == 13) {
            return 1;
        } if (this.getVariant() == 1 || this.getVariant() == 11 || this.getVariant() == 17) {
            return 2;
        }
        return super.getIUCNStatus();
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.3F;
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

    public static boolean checkDoveSpawnRules(EntityType<? extends Animal> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        BlockState below = level.getBlockState(pos.below());
        return below.is(Blocks.TERRACOTTA)
                || below.is(Blocks.RED_SAND)
                || below.is(Blocks.SAND)
                || (below.is(Blocks.GRASS_BLOCK) && level.getRawBrightness(pos, 0) > 8);
    }

    public boolean canTame() {
        return true;
    }

}
