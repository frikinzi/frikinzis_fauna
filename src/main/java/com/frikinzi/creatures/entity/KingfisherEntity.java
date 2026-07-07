package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.FleeGoal;
import com.frikinzi.creatures.entity.ai.PickUpFishGoal;
import com.frikinzi.creatures.entity.ai.PickUpFoodGoal;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
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
import java.util.function.Predicate;

public class KingfisherEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected BlockPos blockPos = BlockPos.ZERO;
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.CRAB_PINCERS.get(), CreaturesItems.GOURAMI.get(), CreaturesItems.RAW_TROUT.get(), Items.COD, Items.SALMON, CreaturesItems.RAW_TROUT.get(), Items.TROPICAL_FISH);
    private static final Ingredient FOOD_ITEMS_INSECTIVORE = Ingredient.of(CreaturesItems.MEALWORMS.get());
    private int heldFishTicks = 0;
    private static final int TICKS_TO_EAT = 200;
    public int pickupCooldown = 0;
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.SALMON || entitytype == CreaturesEntities.GUPPY.get() || entitytype == CreaturesEntities.GOURAMI.get() || entitytype == EntityType.COD || entitytype == CreaturesEntities.TROUT.get();
    };
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.commonkingfisher"))
            .put(2, Component.translatable("message.creatures.dwarforiental"))
            .put(3, Component.translatable("message.creatures.forestkingfisher"))
            .put(4, Component.translatable("message.creatures.beltedkingfisher"))
            .put(5, Component.translatable("message.creatures.marquesas"))
            .put(6, Component.translatable("message.creatures.spottedkingfisher"))
            .put(7, Component.translatable("message.creatures.guamkingfisher"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Alcedo atthis")
            .put(2, "Ceyx erithaca")
            .put(3, "Todiramphus macleayii")
            .put(4, "Megaceryle alcyon")
            .put(5, "Todiramphus godeffroyi")
            .put(6, "Actenoides lindsayi")
            .put(7, "Todiramphus cinnamominus")
            .build();

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE, Region.ASIA, Region.AFRICA))
            .put(2, List.of(Region.ASIA))
            .put(3, List.of(Region.OCEANIA, Region.ASIA))
            .put(4, List.of(Region.NORTH_AMERICA))
            .put(5, List.of(Region.OCEANIA))
            .put(6, List.of(Region.ASIA))
            .put(7, List.of(Region.OCEANIA))
            .build();

    public KingfisherEntity(EntityType<? extends KingfisherEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, OspreyEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(4, new NonTameRandomTargetGoal<>(this, WaterAnimal.class, false, PREY_SELECTOR));
        this.goalSelector.addGoal(2, new PickUpFoodGoal(this));
        this.goalSelector.addGoal(4, new FleeGoal<>(this, Player.class, 6.0F, 1.0D, 1.5D));

    }

    protected <E extends KingfisherEntity> PlayState flyAnimController(final AnimationState<E> event)
    {

        Level world = this.level();
        BlockPos blockpos = this.blockPos.below();
        if (this.isAggressive() && !this.onGround() & this.getDeltaMovement().y < 0.0D & !this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("dive"));
        }
        if (!this.onGround() || this.isFlying()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        }
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        }  if (this.isSleeping()) {
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, (double)1.0F);
    }

    public int numVariants() {
        return 7;
    }

    @Override
    public KingfisherEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        KingfisherEntity kingfisher = CreaturesEntities.KINGFISHER.get().create(p_241840_1_);
        kingfisher.setVariant(this.getVariant());
        kingfisher.setGender(this.random.nextInt(2));
        return kingfisher;
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
        if (this.getVariant() == 7) {
            return FOOD_ITEMS_INSECTIVORE.test(p_70877_1_);

        }
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.KINGFISHER_AMBIENT.get(); } else {
            return null;
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
        return CreaturesConfig.kingfisher_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.kingfisher_clutch_size.get());
    }

    public ItemStack getFoodItem() {
        if (this.getVariant() == 7) {
            return new ItemStack(CreaturesItems.MEALWORMS.get(), 1);

        }
        return new ItemStack(Items.COD, 1);
    }

    public void aiStep() {
        super.aiStep();
        Vec3 vector3d = this.getDeltaMovement();
        if (pickupCooldown > 0) pickupCooldown--;
        if (this.isAggressive() & !this.onGround() && vector3d.y < 0.0D) {
            this.setDeltaMovement(vector3d.multiply(1.0D, 1.5D, 1.0D));
        }
        if (!this.level().isClientSide()) {
            if (!this.getMainHandItem().isEmpty()) {
                heldFishTicks++;
                if (heldFishTicks >= TICKS_TO_EAT) {
                    this.heal(4.0F);

                    heldFishTicks = 0;
                    this.playSound(net.minecraft.sounds.SoundEvents.GENERIC_EAT,
                            1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
                    // Spawn eating particles
                    if (this.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(new net.minecraft.core.particles.ItemParticleOption(
                                        net.minecraft.core.particles.ParticleTypes.ITEM,
                                        this.getMainHandItem()
                                ),
                                this.getX(), this.getY() + this.getBbHeight() * 0.8,
                                this.getZ(), 8, 0.1, 0.1, 0.1, 0.05);
                    }
                    this.clearHeldItem();
                }
            } else {
                heldFishTicks = 0;
            }
        }
    }

    public boolean canBreatheUnderwater() {
        if (this.isAggressive()) {
        return true; } else {
            return false;
        }
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 2) {
            return 1;
        } if (this.getVariant() == 5) {
            return 4;
        } if (this.getVariant() == 7) {
            return 5;
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
        return Component.translatable("description.creatures.kingfisher");
    }

    public void setHeldItem(ItemStack stack) {
        this.setItemSlot(EquipmentSlot.MAINHAND, stack);
        this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
    }

    public void clearHeldItem() {
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && !this.level().isClientSide() && !this.getMainHandItem().isEmpty()) {
            this.spawnAtLocation(this.getMainHandItem());
            this.clearHeldItem();
            heldFishTicks = 0;
            pickupCooldown = 200;
        }
        return result;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if (!this.getMainHandItem().isEmpty()) return false;
        return super.canAttack(target);
    }

    public List<ItemStack> getAllFoodItems() {
        if (this.getVariant() == 7) {
            return Arrays.stream(FOOD_ITEMS_INSECTIVORE.getItems())
                    .map(ItemStack::copy)
                    .collect(java.util.stream.Collectors.toList());
        }
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

//    @Override
//    public int methodOfDeterminingVariant() {
//        int[] iucnWeights = { 100, 60, 30, 15, 5, 1, 0, 50 };
//
//        boolean isMushroomIsland = this.level().getBiome(this.blockPosition())
//                .is(Tags.Biomes.IS_MUSHROOM);
//
//        List<Integer> pool = new ArrayList<>();
//        for (int v = 1; v <= numVariants(); v++) {
//            if (v == 7) {
//                if (isMushroomIsland && this.random.nextInt(100) == 0) {
//                    pool.add(7);
//                }
//                continue;
//            }
//
//            this.setVariant(v);
//            int status = this.getIUCNStatus();
//            int weight = (status >= 0 && status < iucnWeights.length) ? iucnWeights[status] : 50;
//            for (int w = 0; w < weight; w++) {
//                pool.add(v);
//            }
//        }
//
//        if (pool.isEmpty()) {
//            return this.random.nextInt(numVariants() - 1) + 1;
//        }
//
//        return pool.get(this.random.nextInt(pool.size()));
//    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)|| p_218106_.getBlockState(p_218108_.below()).is(BlockTags.SAND) )&& isBrightEnoughToSpawn(p_218106_, p_218108_);
    }

    public boolean isSexuallyDimorphic() {
        if (this.getVariant() == 4) {
            return true;
        }
        return false;
    }

    public String getGenderIndicator() {
        if (!this.isSexuallyDimorphic()) {
            return "";
        }
        if (this.getGender() == 0) {
            return "f";
        } else {
            return "m";
        }
    }

}
