package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
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

import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraftforge.common.ForgeMod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WildDuckEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.PUMPKIN_SEEDS);
    public int featherTime = this.random.nextInt(6000) + 6000;
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.torrent"))
            .put(2, Component.translatable("message.creatures.redhead"))
            .put(3, Component.translatable("message.creatures.greenwing"))
            .put(4, Component.translatable("message.creatures.ruddy"))
            .put(5, Component.translatable("message.creatures.mallard"))
            .put(6, Component.translatable("message.creatures.ringteal"))
            .put(7, Component.translatable("message.creatures.indianspotbill"))
            .put(8, Component.translatable("message.creatures.whiteheadduck"))
            .put(9, Component.translatable("message.creatures.chestnutteal"))
            .put(10, Component.translatable("message.creatures.madagascarteal"))
            .put(11, Component.translatable("message.creatures.bluebilledteal"))
            .put(12, Component.translatable("message.creatures.punateal"))
            .put(13, Component.translatable("message.creatures.maccoa"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Merganetta armata")
            .put(2, "Aythya americana")
            .put(3, "Anas carolinensis")
            .put(4, "Oxyura jamaicensis")
            .put(5, "Anas platyrhynchos")
            .put(6, "Callonetta leucophrys")
            .put(7, "Anas poecilorhyncha")
            .put(8, "Oxyura leucocephala")
            .put(9, "Anas castanea")
            .put(10, "Anas bernieri")
            .put(11, "Anas hottentota")
            .put(12, "Spatula puna")
            .put(13, "Oxyura maccoa")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.SOUTH_AMERICA))
            .put(2, List.of(Region.NORTH_AMERICA))
            .put(3, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(4, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(5, List.of(Region.EUROPE, Region.ASIA, Region.NORTH_AMERICA, Region.OCEANIA, Region.AFRICA, Region.SOUTH_AMERICA))
            .put(6, List.of(Region.SOUTH_AMERICA))
            .put(7, List.of(Region.ASIA))
            .put(8, List.of(Region.EUROPE, Region.ASIA, Region.AFRICA))
            .put(9, List.of(Region.OCEANIA))
            .put(10, List.of(Region.AFRICA))
            .put(11, List.of(Region.AFRICA))
            .put(12, List.of(Region.SOUTH_AMERICA))
            .put(13, List.of(Region.AFRICA))
            .build();

    public WildDuckEntity(EntityType<? extends WildDuckEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 1.0F);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1.5D, 1.2D));
    }

    protected <E extends WildDuckEntity> PlayState walkAnimController(final AnimationState<E> event) {
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
        if (this.isGrooming()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("groom"));
        }
        if (event.isMoving()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() && !this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        } if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
        } if (this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("swimming"));
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
        if (!this.level().isClientSide) {
            int i = this.random.nextInt(3000);

            if (i == 0 && !this.isInWater() && !this.isSleeping() && !this.isBaby()) {
                this.getNavigation().stop();
                this.setGrooming(true);
            }
            if ((i == 1 || this.isInWater()) && this.isGrooming()) {
                this.setGrooming(false);
            }
        }
    }

    public int numVariants() {
        return 13;
    }

    @Override
    public WildDuckEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        WildDuckEntity wildduckentity = CreaturesEntities.WILD_DUCK.get().create(p_241840_1_);
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
            if (this.getVariant() == 1) {
                return CreaturesSound.WILD_DUCK_AMBIENT.get();
            }
            return CreaturesSound.MALLARD_AMBIENT.get();
        }
        else
        {
            return null;
        }
    }


    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.DUCK;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public double getHatchChance() {
        return CreaturesConfig.wild_duck_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.wild_duck_clutch_size.get());
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 8 || this.getVariant() == 13) {
            return 3;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.duck");
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }
}
