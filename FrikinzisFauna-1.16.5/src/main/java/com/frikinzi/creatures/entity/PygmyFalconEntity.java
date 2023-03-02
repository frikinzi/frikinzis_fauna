package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.RaptorBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.registry.ModEntityTypes;
import com.frikinzi.creatures.util.CreaturesLootTables;
import com.google.common.collect.Sets;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class PygmyFalconEntity extends RaptorBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CHICKEN, Items.ROTTEN_FLESH, CreaturesItems.SMALL_BIRD_MEAT, Items.RABBIT);
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.RABBIT || entitytype == ModEntityTypes.FAIRYWREN.get() || entitytype == ModEntityTypes.SPARROW.get();
    };

    public PygmyFalconEntity(EntityType<? extends PygmyFalconEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        if (!this.isBaby() && CreaturesConfig.raptor_attacks.get() == true) {
        this.targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, PREY_SELECTOR));
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if (!this.onGround || this.isFlying() && !this.isBaby()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
            return PlayState.CONTINUE;
        }
        if (this.isSleeping()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
            return PlayState.CONTINUE;
        } if (this.isInSittingPose()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", true));
        return PlayState.CONTINUE;
    }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
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
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int determineVariant() {
        return 1;
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        PygmyFalconEntity pygmyfalconentity = (PygmyFalconEntity) getType().create(p_241840_1_);
        pygmyfalconentity.setGender(this.random.nextInt(2));
        pygmyfalconentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return pygmyfalconentity;
    }

    @Override
    public boolean canMate(AnimalEntity p_70878_1_) {
        if (p_70878_1_ == this) {
            return false;
        } else if (p_70878_1_.getClass() != this.getClass()) {
            return false;
        } else {
            PygmyFalconEntity pygmyfalconentity = (PygmyFalconEntity) p_70878_1_;
            if (pygmyfalconentity.isInSittingPose()) {
            return false;
        } else if (!pygmyfalconentity.isTame()) {
            return false;
            } else {
            return this.isInLove() && p_70878_1_.isInLove();
        }
        }
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.PYGMY_FALCON_AMBIENT; }
        return null;
    }

    @Override
    public Set<Item> getTamedFood() {
        TAME_FOOD = Sets.newHashSet(Items.CHICKEN, Items.ROTTEN_FLESH, CreaturesItems.SMALL_BIRD_MEAT);
        return TAME_FOOD;
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_OF_PREY;
    }

    public static boolean checkFalconSpawnRules(EntityType<? extends AnimalEntity> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_) {
        return p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.SAND) || p_223316_1_.getBlockState(p_223316_3_.below()).is(Blocks.GRASS_BLOCK) && p_223316_1_.getRawBrightness(p_223316_3_, 0) > 8;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.CHICKEN, 1);
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.pygmy_falcon_hatch_chance.get()).floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.pygmy_falcon_clutch_size.get());
    }

}
