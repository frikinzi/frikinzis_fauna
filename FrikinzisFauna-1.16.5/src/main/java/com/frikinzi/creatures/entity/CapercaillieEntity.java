package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.NonTameableBirdBase;
import com.frikinzi.creatures.entity.base.TameableWalkingBirdBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.util.CreaturesLootTables;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeMod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CapercaillieEntity extends NonTameableBirdBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.BEETROOT, Items.SWEET_BERRIES);

    public CapercaillieEntity(EntityType<? extends CapercaillieEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
        this.setPathfindingMalus(PathNodeType.WATER, 1.0F);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, FOOD_ITEMS));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!this.isOnGround() & !this.isBaby()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            if (this.getGender() != 1 & !this.isBaby()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walk_female", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if (this.isSleeping()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
            return PlayState.CONTINUE;
        } if (this.getGender() != 1 & !this.isBaby()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle_female", true));
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
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public int determineVariant() {
        return 2;
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        CapercaillieEntity capercaillieentity = (CapercaillieEntity) getType().create(p_241840_1_);
        capercaillieentity.setGender(this.random.nextInt(2));
        capercaillieentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return capercaillieentity;
    }

    @Override
    public boolean canMate(AnimalEntity p_70878_1_) {
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

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.CAPERCAILLIE_AMBIENT;
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

    @Override
    public String getSpeciesName() {
        ITextComponent s1 = new TranslationTextComponent("entity.creatures.capercaillie");
        return s1.getString();
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.SWEET_BERRIES, 1);
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.capercaillie_hatch_chance.get()).floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.capercaillie_clutch_size.get());
    }

}
