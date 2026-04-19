package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.FleeWithFoodGoal;
import com.frikinzi.creatures.entity.ai.PickUpFoodGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameType;
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
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraftforge.common.ForgeMod;

import java.util.*;

public class SeagullEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, CreaturesItems.CRAB_PINCERS.get(), CreaturesItems.RAW_TROUT.get(), Items.BREAD, CreaturesItems.MEALWORMS.get());
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.herringgull"))
            .put(2, Component.translatable("message.creatures.sabinesgull"))
            .put(3, Component.translatable("message.creatures.rossgull"))
            .put(4, Component.translatable("message.creatures.laughinggull"))
            .put(5, Component.translatable("message.creatures.heermannsgull"))
            .put(6, Component.translatable("message.creatures.ivorygull"))
            .put(7, Component.translatable("message.creatures.blackbilledgull"))
            .put(8, Component.translatable("message.creatures.andeangull"))
            .put(9, Component.translatable("message.creatures.graygull"))
            .build();
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE, Region.NORTH_AMERICA))
            .put(2, List.of(Region.NORTH_AMERICA, Region.EUROPE, Region.ASIA))
            .put(3, List.of(Region.NORTH_AMERICA, Region.EUROPE, Region.ASIA))
            .put(4, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(5, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(6, List.of(Region.NORTH_AMERICA, Region.EUROPE, Region.ASIA))
            .put(7, List.of(Region.OCEANIA))
            .put(8, List.of(Region.SOUTH_AMERICA))
            .put(9, List.of(Region.SOUTH_AMERICA))
            .build();
    public static Map<Integer, Component> DESCRIPTIONS = new HashMap<Integer, Component>() {{
        put(1, Component.translatable("description.creatures.herringgull"));
        put(2, Component.translatable("description.creatures.sabinesgull"));
        put(3, Component.translatable("description.creatures.rossgull"));
        put(4, Component.translatable("description.creatures.laughinggull"));
        put(5, Component.translatable("description.creatures.heermannsgull"));
        put(6, Component.translatable("description.creatures.ivorygull"));
        put(7, Component.translatable("description.creatures.blackbilledgull"));
        put(8, Component.translatable("description.creatures.andeangull"));
        put(9, Component.translatable("description.creatures.graygull"));
    }};
    public static Map<Integer, String> SCIENTIFIC_NAMES = new HashMap<Integer, String>() {{
        put(1, "Larus argentatus");
        put(2, "Xema sabini");
        put(3, "Rhodostethia rosea");
        put(4, "Leucophaeus atricilla");
        put(5, "Larus heermanni");
        put(6, "Pagophila eburnea");
        put(7, "Chroicocephalus bulleri");
        put(8, "Chroicocephalus serranus");
        put(9, "Leucophaeus modestus");
    }};

    private int stealCooldown = 0;
    private static final int STEAL_COOLDOWN_MIN = 1200;
    private static final int STEAL_COOLDOWN_MAX = 3600;

    public SeagullEntity(EntityType<? extends SeagullEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FleeWithFoodGoal(this));
        this.goalSelector.addGoal(3, new PickUpFoodGoal(this));
        this.goalSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers());
        //this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.targetSelector.addGoal(1, new CreaturesBirdEntity.DefendBabyGoal());
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Cod.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Salmon.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TropicalFish.class, false));
        this.goalSelector.addGoal(6, new FollowFlockLeaderGoal(this));
    }

    protected <E extends SeagullEntity> PlayState flyAnimController(final AnimationState<E> event)
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(ForgeMod.SWIM_SPEED.get(), 3.0).add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    public int numVariants() {
        return 9;
    }

    @Override
    public SeagullEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        SeagullEntity seagullentity = CreaturesEntities.SEAGULL.get().create(p_241840_1_);
        seagullentity.setVariant(this.getVariant());
        seagullentity.setGender(this.random.nextInt(2));
        seagullentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return seagullentity;
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
        return CreaturesSound.SEAGULL.get(); } else {
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

    public ItemStack getFoodItem() {
        return new ItemStack(Items.COD, 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.seagull_hatch_chance.get().floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.seagull_clutch_size.get());
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
        return (p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)|| p_218106_.getBlockState(p_218108_.below()).is(BlockTags.SAND) )&& isBrightEnoughToSpawn(p_218106_, p_218108_);
    }


    public int getMaxFlockSize() {
        return 5;
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
        return Component.translatable("description.creatures.gull");
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide()) return;

        if (stealCooldown > 0) {
            stealCooldown--;
        }
        if (pickupCooldown > 0) {
            pickupCooldown--;
        }

        // Eat held item after 3 seconds
        if (!this.getMainHandItem().isEmpty()) {
            heldFishTicks++;
            if (heldFishTicks >= 240) {
                this.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.2F);
                if (this.isFood(this.getMainHandItem()) && this.getMainHandItem().getFoodProperties(this) != null && this.getHealth() < this.getMaxHealth()) {
                    this.heal((float)this.getMainHandItem().getFoodProperties(this).getNutrition());

                }
                if (this.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(new net.minecraft.core.particles.ItemParticleOption(
                                    net.minecraft.core.particles.ParticleTypes.ITEM,
                                    this.getMainHandItem()
                            ),
                            this.getX(), this.getY() + this.getBbHeight() * 0.8,
                            this.getZ(), 8, 0.1, 0.1, 0.1, 0.05);
                }
                this.clearHeldItem();
                heldFishTicks = 0;
            }
            return;
        }

        // Only steal if off cooldown
        if (stealCooldown > 0) return;

        if (CreaturesConfig.bird_stealing.get() && !this.isSleeping() && !this.isBaby()) {
            List<Player> nearbyPlayers = this.level().getEntitiesOfClass(
                    Player.class,
                    this.getBoundingBox().inflate(5.0),
                    p -> p.getMainHandItem().isEdible() || p.getOffhandItem().isEdible()
            );

            if (!nearbyPlayers.isEmpty()) {
                Player victim = nearbyPlayers.get(0);
                if (victim.isCreative()) return;
                if (victim.isSpectator()) return;
                if (!this.level().getGameRules().getBoolean(net.minecraft.world.level.GameRules.RULE_MOBGRIEFING)) return;

                if (this.distanceTo(victim) > 3.0) {
                    this.getNavigation().moveTo(victim, 1.4);
                    return;
                }


                stealFrom(victim.getMainHandItem().isEdible()
                        ? victim.getMainHandItem()
                        : victim.getOffhandItem(), victim);
            }

            List<CreaturesBirdEntity> nearbyBirds = this.level().getEntitiesOfClass(
                    CreaturesBirdEntity.class,
                    this.getBoundingBox().inflate(8.0),
                    b -> b != this && !b.getMainHandItem().isEmpty() && b.getMainHandItem().isEdible()
            );

            if (!nearbyBirds.isEmpty()) {
                CreaturesBirdEntity victim = nearbyBirds.get(0);
                if (this.distanceTo(victim) > 3.0) {
                    this.getNavigation().moveTo(victim, 1.4);
                    return;
                }
                stealFrom(victim.getMainHandItem(), victim);
            }

        }


    }

    private void stealFrom(ItemStack source, LivingEntity victim) {
        if (source.isEmpty()) return;

        ItemStack stolen = source.split(1);
        if (stolen.isEmpty()) return;

        this.setHeldItem(stolen);
        this.setTarget(null);
        this.getNavigation().moveTo(
                this.getX() + (this.random.nextFloat() - 0.5) * 25,
                this.getY() + 10,
                this.getZ() + (this.random.nextFloat() - 0.5) * 25,
                1.6
        );

        if (victim instanceof Player player) {
            player.displayClientMessage(
                    Component.translatable("message.creatures.seagull_steal"), true);
        }

        if (victim instanceof Player) {
            List<SeagullEntity> flock = this.level().getEntitiesOfClass(
                    SeagullEntity.class,
                    this.getBoundingBox().inflate(16.0),
                    s -> s != this && s.getMainHandItem().isEmpty() && s.stealCooldown <= 0
            );
            for (SeagullEntity friend : flock) {
                friend.setTarget(victim);
            }
        }
        stealCooldown = STEAL_COOLDOWN_MIN +
                this.random.nextInt(STEAL_COOLDOWN_MAX - STEAL_COOLDOWN_MIN);
    }

    private int heldFishTicks = 0;

    public void setHeldItem(ItemStack stack) {
        this.setItemSlot(EquipmentSlot.MAINHAND, stack);
        this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
    }

    public void clearHeldItem() {
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("StealCooldown", stealCooldown);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        stealCooldown = tag.getInt("StealCooldown");
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
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

}
