package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.PickUpFoodGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
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
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class SkuaEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CHICKEN, CreaturesItems.SMALL_BIRD_MEAT.get(), Items.EGG, Items.COD, CreaturesItems.RAW_TROUT.get());
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.southpolar"),
            2, Component.translatable("message.creatures.chilean"),
            3, Component.translatable("message.creatures.greatskua"),
            4, Component.translatable("message.creatures.brownskua")
    );
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.ANTARCTICA))
            .put(2, List.of(Region.SOUTH_AMERICA))
            .put(3, List.of(Region.EUROPE, Region.NORTH_AMERICA, Region.AFRICA))
            .put(4, List.of(Region.ANTARCTICA))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Stercorarius maccormicki")
            .put(2, "Stercorarius chilensis")
            .put(3, "Stercorarius skua")
            .put(4, "Stercorarius antarcticus")
            .build();

    private int stealCooldown = 0;
    private static final int STEAL_COOLDOWN_MIN = 1200;
    private static final int STEAL_COOLDOWN_MAX = 3600;

    public SkuaEntity(EntityType<? extends SkuaEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new PickUpFoodGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(2, new AttackBabyGoal());
        this.targetSelector.addGoal(2, new CreaturesBirdEntity.DefendBabyGoal());
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
    }

    protected <E extends SkuaEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround()) {
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int numVariants() {
        return 4;
    }

    @Override
    public SkuaEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        SkuaEntity rollerentity = CreaturesEntities.SKUA.get().create(p_241840_1_);
        rollerentity.setVariant(this.getVariant());
        rollerentity.setGender(this.random.nextInt(2));
        rollerentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return rollerentity;
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
        return CreaturesSound.SKUA_AMBIENT.get(); } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.LARGE_BIRD_GENERIC;
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

    class AttackBabyGoal extends NearestAttackableTargetGoal<LivingEntity> {
        public AttackBabyGoal() {
            super(SkuaEntity.this, LivingEntity.class, 20, true, true, (Predicate<LivingEntity>)(p_213616_0_) -> {
                return p_213616_0_.isBaby() && p_213616_0_.getClass() != SkuaEntity.this.getClass();});
        }
        }

    public double getHatchChance() {
        return CreaturesConfig.skua_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.skua_clutch_size.get());
    }

    public int getMaxFlockSize() {
        return 10;
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.skua");
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
                this.heal(3.0F);
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
                stealCooldown = STEAL_COOLDOWN_MIN +
                        this.random.nextInt(STEAL_COOLDOWN_MAX - STEAL_COOLDOWN_MIN);
            }
            return; // don't try to steal while already holding something
        }

        // Only steal if off cooldown
        if (stealCooldown > 0) return;

        if (CreaturesConfig.bird_stealing.get() && !this.isBaby() && !this.isSleeping()) {
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
                return;
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
                this.getX() + (this.random.nextFloat() - 0.5) * 20,
                this.getY() + 5,
                this.getZ() + (this.random.nextFloat() - 0.5) * 20,
                1.6
        );

        if (victim instanceof Player player) {
            player.displayClientMessage(
                    Component.translatable("message.creatures.seagull_steal"), true);
        }

        // Flock aggro — only against players
        if (victim instanceof Player) {
            List<SkuaEntity> flock = this.level().getEntitiesOfClass(
                    SkuaEntity.class,
                    this.getBoundingBox().inflate(16.0),
                    s -> s != this && s.getMainHandItem().isEmpty() && s.stealCooldown <= 0
            );
            for (SkuaEntity friend : flock) {
                friend.setTarget(victim);
            }
        }
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
