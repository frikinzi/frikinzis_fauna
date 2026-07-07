package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.RaptorBase;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Animal;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class GyrfalconEntity extends RaptorBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CHICKEN, Items.ROTTEN_FLESH, CreaturesItems.SMALL_BIRD_MEAT.get(), Items.RABBIT, CreaturesItems.LARGE_BIRD_MEAT.get(), Items.PORKCHOP, Items.BEEF);
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.RABBIT || entitytype == EntityType.CHICKEN;
    };

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.NORTH_AMERICA, Region.EUROPE, Region.ASIA))
            .build();

    public GyrfalconEntity(EntityType<? extends GyrfalconEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        if (!this.isBaby() && CreaturesConfig.raptor_attacks.get() == true) {
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        }
    }

    protected <E extends GyrfalconEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (!this.onGround() || this.isFlying() && !this.isBaby()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        }
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        }
        if (this.isSleeping()) {
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int numVariants() {
        return 1;
    }

    @Override
    public GyrfalconEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        GyrfalconEntity gyrfalconentity = CreaturesEntities.GYRFALCON.get().create(p_241840_1_);
        gyrfalconentity.setGender(this.random.nextInt(2));
        gyrfalconentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return gyrfalconentity;
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
        return CreaturesSound.GYRFALCON_AMBIENT.get(); }
        return null;
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.BIRD_OF_PREY;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.CHICKEN, 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.gyrfalcon_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.gyrfalcon_clutch_size.get());
    }

    public String getScientificName() {
        return "Falco rusticolus";
    }

    public boolean canTame() {
        return true;
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.gyrfalcon");
    }


    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

    public int getScaleforGUI() {
        return (int)(super.getScaleforGUI() *2f);
    }

}
