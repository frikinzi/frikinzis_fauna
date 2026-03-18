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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
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
import com.frikinzi.creatures.entity.ai.SitOnShoulderGoal;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RavenEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private PanicGoal PanicGoal;
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.ROTTEN_FLESH, Items.EGG, Items.CHICKEN);
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.commonraven"),
            2, Component.translatable("message.creatures.brownheadedraven"),
            3, Component.translatable("message.creatures.whitethroatedraven"),
            4, Component.translatable("message.creatures.thickbilledraven"),
            5, Component.translatable("message.creatures.commonravenalbino")
    );

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE, Region.ASIA, Region.NORTH_AMERICA, Region.AFRICA))
            .put(2, List.of(Region.AFRICA, Region.ASIA))
            .put(3, List.of(Region.AFRICA))
            .put(4, List.of(Region.AFRICA))
            .put(5, List.of(Region.EUROPE, Region.ASIA, Region.NORTH_AMERICA, Region.AFRICA))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Corvus corax")
            .put(2, "Corvus ruficollis")
            .put(3, "Corvus albicollis")
            .put(4, "Corvus crassirostris")
            .put(5, "Corvus corax")
            .build();

    public RavenEntity(EntityType<? extends RavenEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        if (!this.isBaby()) {
            this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
            this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
            this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
            this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
            this.targetSelector.removeGoal(PanicGoal);
        }
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));

    }

    protected <E extends RavenEntity> PlayState flyAnimController(final AnimationState<E> event)
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int methodOfDeterminingVariant() {
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get()) == 1) {
            return 5;
        }
        else {
            return this.random.nextInt(5);
        }
    }

    @Override
    public RavenEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        RavenEntity ravenentity = CreaturesEntities.RAVEN.get().create(p_241840_1_);
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get() * 2) == 1) {
            ravenentity.setVariant(2);
        } else {
            ravenentity.setVariant(this.getVariant());
        }
        ravenentity.setGender(this.random.nextInt(2));
        ravenentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return ravenentity;
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.RAVEN_AMBIENT.get(); }
        return null;
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(Items.ROTTEN_FLESH, Items.EGG, Items.CHICKEN);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.RAVEN;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.ROTTEN_FLESH, 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.raven_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.raven_clutch_size.get());
    }

    public int numVariants() {
        return 5;
    }

    public boolean isMonogamous() {
        return true;
    }

    public int getMaxFlockSize() {
        return 4;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    public int getAmbientSoundInterval() {
        return 600;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 2) {
            return 1;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.raven");
    }

    public boolean canTame() {
        return true;
    }

}
