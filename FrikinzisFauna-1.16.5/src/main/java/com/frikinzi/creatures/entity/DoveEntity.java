package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.TameableBirdBase;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.util.CreaturesLootTables;
import com.google.common.collect.Sets;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;
import org.codehaus.plexus.util.StringUtils;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Set;

public class DoveEntity extends TameableBirdBase implements IAnimatable {
    final private int[] jungle_variants = new int[] {1,3,5,7,8,13,14};
    final private int[] swamp_variant = new int[] {9};
    final private int[] mountain_variant = new int[] {12};
    final private int[] forest_variant = new int[] {2,4,6,11,10,15,17};
    final private int[] mesa_variant = new int[] {16};
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    private static final Ingredient FRUIT_ITEMS = Ingredient.of(Items.CHORUS_FRUIT, Items.SWEET_BERRIES, Items.APPLE, Items.MELON_SLICE);

    public DoveEntity(EntityType<? extends DoveEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        } if (!this.onGround || this.isFlying()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
        return PlayState.CONTINUE;
    } if (this.isSleeping()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
        return PlayState.CONTINUE;
    } if (this.isInSittingPose()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", true));
        return PlayState.CONTINUE;
    }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(6, new FollowFlockLeaderGoal(this));
    }

    public int getMaxFlockSize() {
        return 10;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
    }

    public int determineVariant() {
        return 18;
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        DoveEntity doveentity = (DoveEntity) getType().create(p_241840_1_);
        doveentity.setGender(this.random.nextInt(2));
        doveentity.setVariant(this.getVariant());
        doveentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return doveentity;
    }

    @Override
    public boolean canMate(AnimalEntity p_70878_1_) {
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

    public Set<Item> getTamedFood() {
        if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 6 || this.getVariant() == 7 || this.getVariant() == 16) {
            return TAME_FOOD = Sets.newHashSet(Items.BEETROOT_SEEDS, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.PUMPKIN_SEEDS);
        } else {
            return TAME_FOOD = Sets.newHashSet(Items.MELON_SLICE, Items.APPLE, Items.SWEET_BERRIES, Items.CHORUS_FRUIT);
        }
    }

    protected SoundEvent getAmbientSound() {
        if (this.getVariant() == 15 && this.isFlying()) {
            return CreaturesSound.CRESTED_PIGEON;
        }
        if (!this.isSleeping()) {
            if (this.getVariant() == 10) {
                return CreaturesSound.MOURNING_DOVE;
            }
            return CreaturesSound.DOVE_AMBIENT;
        }
        else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    @Override
    public int methodofDeterminingVariant(IWorld p_213610_1_) {
        if (CreaturesConfig.biome_only_variants.get()) {
            Biome biome = p_213610_1_.getBiome(this.blockPosition());
            RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);
            if (types.contains(BiomeDictionary.Type.MESA)) {
                int i = this.random.nextInt(mesa_variant.length);
                return mesa_variant[i];
            }
            if (types.contains(BiomeDictionary.Type.JUNGLE)) {
                int i = this.random.nextInt(jungle_variants.length);
                return jungle_variants[i];
            } if (types.contains(BiomeDictionary.Type.FOREST) || types.contains(BiomeDictionary.Type.PLAINS)) {
                int i = this.random.nextInt(forest_variant.length);
                return forest_variant[i];
            } if (types.contains(BiomeDictionary.Type.MOUNTAIN)) {
                int i = this.random.nextInt(mountain_variant.length);
                return mountain_variant[i];
            } if (types.contains(BiomeDictionary.Type.SWAMP)) {
                int i = this.random.nextInt(swamp_variant.length);
                return swamp_variant[i];
            }
        }
        return this.random.nextInt(18);

    }

    public String getSpeciesName() {
        if (this.getVariant() == 1) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.jambu");
            return s1.getString();
        }
        else if (this.getVariant() == 2) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.release");
            return s1.getString();
        }
        else if (this.getVariant() == 3) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.rose");
            return s1.getString();
        }
        else if (this.getVariant() == 4) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.rock");
            return s1.getString();
        }
        else if (this.getVariant() == 5) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.flame");
            return s1.getString();
        }
        else if (this.getVariant() == 6) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.goldenheart");
            return s1.getString();
        }  else if (this.getVariant() == 7) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.mbleeding");
            return s1.getString();
        }  else if (this.getVariant() == 8) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.orangebellied");
            return s1.getString();
        }  else if (this.getVariant() == 9) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.victoria");
            return s1.getString();
        }  else if (this.getVariant() == 10) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.mourning");
            return s1.getString();
        }  else if (this.getVariant() == 11) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.europeanturtle");
            return s1.getString();
        }  else if (this.getVariant() == 12) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.snow");
            return s1.getString();
        }  else if (this.getVariant() == 13) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.nicobar");
            return s1.getString();
        }  else if (this.getVariant() == 14) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.pacificemerald");
            return s1.getString();
        }  else if (this.getVariant() == 15) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.crested");
            return s1.getString();
        }  else if (this.getVariant() == 16) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.spinifex");
            return s1.getString();
        }  else if (this.getVariant() == 17) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dove.pink");
            return s1.getString();
        }
        else {
            return "Unknown";
        }
    }

    public String getFoodName() {
        if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 6 || this.getVariant() == 7 || this.getVariant() == 16) {
        return StringUtils.capitalizeFirstLetter(Items.WHEAT_SEEDS.toString()); } else {
            return StringUtils.capitalizeFirstLetter(Items.SWEET_BERRIES.toString());
        }
    }

    public ItemStack getFoodItem() {

        if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 6 || this.getVariant() == 7 || this.getVariant() == 16) {
            return new ItemStack(Items.WHEAT_SEEDS, 1); } else {
            return new ItemStack(Items.SWEET_BERRIES, 1);
        }
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.dove_hatch_chance.get()).floatValue();
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

    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 0.3F;
    }


}
