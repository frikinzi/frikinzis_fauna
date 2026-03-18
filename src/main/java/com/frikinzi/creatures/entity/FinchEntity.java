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
import com.frikinzi.creatures.entity.ai.SitOnShoulderGoal;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

public class FinchEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.zebrafinch"))
            .put(2, Component.translatable("message.creatures.strawberryfinch"))
            .put(3, Component.translatable("message.creatures.zebrafinchmutation"))
            .put(4, Component.translatable("message.creatures.owlfaced"))
            .put(5, Component.translatable("message.creatures.gouldianfinchred"))
            .put(6, Component.translatable("message.creatures.gouldianfinchblack"))
            .put(7, Component.translatable("message.creatures.bullfinch"))
            .put(8, Component.translatable("message.creatures.javafinch"))
            .put(9, Component.translatable("message.creatures.parrotfinch"))
            .put(10, Component.translatable("message.creatures.purplegrenadier"))
            .put(11, Component.translatable("message.creatures.europeangoldfinch"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Taeniopygia castanotis")
            .put(2, "Amandava amandava")
            .put(3, "Taeniopygia castanotis")
            .put(4, "Stizoptera bichenovii")
            .put(5, "Chloebia gouldiae")
            .put(6, "Chloebia gouldiae")
            .put(7, "Pyrrhula pyrrhula")
            .put(8, "Lonchura oryzivora")
            .put(9, "Erythrura tricolor")
            .put(10, "Granatina ianthinogaster")
            .put(11, "Carduelis carduelis")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.OCEANIA, Region.ASIA))
            .put(2, List.of(Region.ASIA))
            .put(3, List.of(Region.OCEANIA, Region.ASIA))
            .put(4, List.of(Region.OCEANIA))
            .put(5, List.of(Region.OCEANIA))
            .put(6, List.of(Region.OCEANIA))
            .put(7, List.of(Region.EUROPE, Region.ASIA))
            .put(8, List.of(Region.ASIA))
            .put(9, List.of(Region.ASIA))
            .put(10, List.of(Region.AFRICA))
            .put(11, List.of(Region.EUROPE, Region.ASIA, Region.AFRICA))
            .build();

    public FinchEntity(EntityType<? extends FinchEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1.5D, 1.2D));
        this.goalSelector.addGoal(3, new SitOnShoulderGoal(this));
    }

    protected <E extends FinchEntity> PlayState flyAnimController(final AnimationState<E> event)
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
        return 11;
    }

    @Override
    public FinchEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        FinchEntity lovebirdentity = CreaturesEntities.FINCH.get().create(p_241840_1_);
        lovebirdentity.setGender(this.random.nextInt(2));
        if (this.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
            lovebirdentity.setVariant(3); }
            else {
                lovebirdentity.setVariant(this.getVariant());
            }
        } else {
            lovebirdentity.setVariant(this.getVariant());
        }
        lovebirdentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return lovebirdentity;
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
        return CreaturesSound.FINCH_AMBIENT.get(); } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    @Override
    public int methodOfDeterminingVariant() {
        if (CreaturesConfig.breed_only_variants.get()) {
        int i = this.random.nextInt(numVariants());
        while (i == 3) {
            i = this.random.nextInt(numVariants());
        }
        return i; }

        else {
            return this.random.nextInt(numVariants());
        }

    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public double getHatchChance() {
        return CreaturesConfig.lovebird_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.lovebird_clutch_size.get());
    }

    public int getMaxFlockSize() {
        return 10;
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.3F;
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 8) {
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
