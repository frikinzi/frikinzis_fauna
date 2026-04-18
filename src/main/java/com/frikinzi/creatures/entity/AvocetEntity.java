package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvocetEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
//    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
//        EntityType<?> entitytype = p_213440_0_.getType();
//        return entitytype == CreaturesEntities.VAMPIRECRAB.get() || entitytype == CreaturesEntities.GHOST_CRAB.get() || entitytype == CreaturesEntities.FIDDLER_CRAB.get();
//    };
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.CRAB_PINCERS.get());
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.piedavocet"))
            .put(2, Component.translatable("message.creatures.andeanavocet"))
            .put(3, Component.translatable("message.creatures.redneckedavocet"))
            .put(4, Component.translatable("message.creatures.americanavocet"))
            .build();
    public static final Map<Integer, Component> DESCRIPTIONS = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("description.creatures.piedavocet"))
            .put(2, Component.translatable("description.creatures.andeanavocet"))
            .put(3, Component.translatable("description.creatures.redneckedavocet"))
            .put(4, Component.translatable("description.creatures.americanavocet"))
            .build();
    public static Map<Integer, String> SCIENTIFIC_NAMES = new HashMap<Integer, String>() {{
        put(1, "Recurvirostra avosetta");
        put(2, "Recurvirostra andina");
        put(3, "Recurvirostra novaehollandiae");
        put(4, "Recurvirostra americana");
    }};

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE, Region.AFRICA, Region.ASIA))
            .put(2, List.of(Region.SOUTH_AMERICA))
            .put(3, List.of(Region.OCEANIA))
            .put(4, List.of(Region.NORTH_AMERICA))
            .build();

    public AvocetEntity(EntityType<? extends AvocetEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2.0D, true));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 2.2D));
//        this.targetSelector.addGoal(5, new ConfigNonTamedTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
    }

    protected <E extends AvocetEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (this.swinging){
            return event.setAndContinue(RawAnimation.begin().then("sweep", Animation.LoopType.PLAY_ONCE));
        }
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, (double)2.0F);
    }

    public int numVariants() {
        return 4;
    }

    private int getCurrentSwingDuration() {
        return 40;
    }

    protected void updateSwingTime() {
        int i = this.getCurrentSwingDuration();
        if (this.swinging) {
            ++this.swingTime;
            if (this.swingTime >= i) {
                this.swingTime = 0;
                this.swinging = false;
            }
        } else {
            this.swingTime = 0;
        }

        this.attackAnim = (float)this.swingTime / (float)i;
    }

    public void aiStep() {
        this.updateSwingTime();
        super.aiStep();
    }

    public void swing(InteractionHand hand, boolean updateSelf) {
        ItemStack stack = this.getItemInHand(hand);
        if (!stack.isEmpty() && stack.onEntitySwing(this)) return;
        if (!this.swinging || this.swingTime >= this.getCurrentSwingDuration() / 2 || this.swingTime < 0) {
            this.swingTime = -1;
            this.swinging = true;
            this.swingingArm = hand;
            if (this.level() instanceof ServerLevel serverLevel) {
                ClientboundAnimatePacket animatePacket = new ClientboundAnimatePacket(this, hand == InteractionHand.MAIN_HAND ? 0 : 3);
                ServerChunkCache serverChunkCache = serverLevel.getChunkSource();
                if (updateSelf) {
                    serverChunkCache.broadcastAndSend(this, animatePacket);
                } else {
                    serverChunkCache.broadcast(this, animatePacket);
                }
            }
        }
    }

    @Override
    public AvocetEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        AvocetEntity rollerentity = CreaturesEntities.AVOCET.get().create(p_241840_1_);
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
        return CreaturesSound.AVOCET.get(); } else {
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
        return CreaturesConfig.avocet_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.avocet_clutch_size.get());
    }

    public int getMaxFlockSize() {
        return 10;
    }

    public Component getFunFact() {
        Component translatable = DESCRIPTIONS.get(this.getVariant());
        if (translatable != null) {
            return translatable;
        } return Component.translatable("creatures.unknown");
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }
}
