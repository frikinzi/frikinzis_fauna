package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.CreaturesWalkingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SecretaryBirdEntity extends CreaturesWalkingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.SPIDER_EYE);
    public int swingTime;
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.AFRICA))
            .build();

    public SecretaryBirdEntity(EntityType<? extends SecretaryBirdEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5D, true));
        this.targetSelector.addGoal(1, new CreaturesBirdEntity.DefendBabyGoal());
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Spider.class, false));
    }

    protected <E extends SecretaryBirdEntity> PlayState walkAnimController(final AnimationState<E> event) {
        if (this.swinging && !this.isBaby()){
            return event.setAndContinue(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
        }
        if (this.isAggressive()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("run"));
        }
        if (event.isMoving()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, (double)4.0F);
    }

    @Override
    public SecretaryBirdEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        SecretaryBirdEntity wildduckentity = CreaturesEntities.SECRETARYBIRD.get().create(p_241840_1_);
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
            return CreaturesSound.SECRETARYBIRD.get();
        }
        else
        {
            return null;
        }
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.LARGE_BIRD_GENERIC;
    }

    public double getHatchChance() {
        return CreaturesConfig.secretarybird_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.secretarybird_clutch_size.get());
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        if (super.doHurtTarget(p_70652_1_)) {

            if (p_70652_1_ instanceof Spider) {
                int i = 0;
                if (this.level().getDifficulty() == Difficulty.NORMAL) {
                    i = 7;
                } else if (this.level().getDifficulty() == Difficulty.HARD) {
                    i = 15;
                }

                if (i > 0) {
                    ((LivingEntity)p_70652_1_).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, i * 20, 0), this);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.SPIDER_EYE, 1);
    }

    public void aiStep() {
        this.updateSwingTime();
        super.aiStep();
    }

    private int getCurrentSwingDuration() {
        return 14;
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

    public int getIUCNStatus() {
        return 3;
    }

    public String getScientificName() {
        return "Sagittarius serpentarius";
    }

    public int getScaleforGUI() {
        return (int)(super.getScaleforGUI() *1.5);
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.secretarybird");
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

}
