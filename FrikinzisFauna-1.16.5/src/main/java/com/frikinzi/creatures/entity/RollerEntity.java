package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.NonTameableFlyingBirdBase;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.util.CreaturesLootTables;
import com.google.common.collect.Sets;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Set;

public class RollerEntity extends NonTameableFlyingBirdBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.PUMPKIN_SEEDS);

    public RollerEntity(EntityType<? extends RollerEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, FOOD_ITEMS));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        } if (!this.onGround || this.isFlying()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
        return PlayState.CONTINUE;
    } if (this.isSleeping()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
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
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
    }

    public int determineVariant() {
        return 6;
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        RollerEntity rollerentity = (RollerEntity) getType().create(p_241840_1_);
        rollerentity.setVariant(this.getVariant());
        rollerentity.setGender(this.random.nextInt(2));
        rollerentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return rollerentity;
    }

    @Override
    public boolean canMate(AnimalEntity p_70878_1_) {
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

    public Set<Item> getTamedFood() {
            return TAME_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.ROLLER_AMBIENT; } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    public String getSpeciesName() {
        if (this.getVariant() == 1) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lilac");
            return s1.getString();
        }
        else if (this.getVariant() == 2) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.europeanroller");
            return s1.getString();
        }
        else if (this.getVariant() == 3) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.indochinese");
            return s1.getString();
        }
        else if (this.getVariant() == 4) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.purplewinged");
            return s1.getString();
        }
        else if (this.getVariant() == 5) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.bluebellied");
            return s1.getString();
        } else {
            return "Unknown";
        }
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.roller_hatch_chance.get()).floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.roller_clutch_size.get());
    }

    @Override
    public boolean isMonogamous() {
        return true;
    }

}
