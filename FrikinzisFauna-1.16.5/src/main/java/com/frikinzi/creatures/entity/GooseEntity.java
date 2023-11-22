package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.NonTameableFlyingBirdBase;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.util.CreaturesLootTables;
<<<<<<< Updated upstream
=======
import com.google.common.collect.ImmutableMap;
>>>>>>> Stashed changes
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.codehaus.plexus.util.StringUtils;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

<<<<<<< Updated upstream
public class GooseEntity extends NonTameableFlyingBirdBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.GRASS, Items.SEAGRASS, Items.TALL_GRASS);
=======
import java.util.Map;

public class GooseEntity extends NonTameableFlyingBirdBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.GRASS, Items.SEAGRASS, Items.TALL_GRASS);
    public static final Map<Integer, TranslationTextComponent> SPECIES_NAMES = ImmutableMap.<Integer, TranslationTextComponent>builder()
            .put(1, new TranslationTextComponent("message.creatures.canada"))
            .put(2, new TranslationTextComponent("message.creatures.barnacle"))
            .put(3, new TranslationTextComponent("message.creatures.graylag"))
            .put(4, new TranslationTextComponent("message.creatures.snow"))
            .put(5, new TranslationTextComponent("message.creatures.orinoco"))
            .put(6, new TranslationTextComponent("message.creatures.barheaded"))
            .build();
>>>>>>> Stashed changes

    public GooseEntity(EntityType<? extends GooseEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, FOOD_ITEMS));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(2, new CreaturesBirdEntity.DefendBabyGoal());
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.isBaby()) {
            if (event.isMoving() && this.onGround) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", true));
                return PlayState.CONTINUE;
            }
            if (!this.onGround || this.isFlying() && !this.isInWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("flying", true));
                return PlayState.CONTINUE;
            }
        }
        if (event.isMoving() && this.onGround || this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        } if (!this.onGround || this.isFlying() && !this.isInWater()) {
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
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int determineVariant() {
<<<<<<< Updated upstream
        return 6;
=======
        return 7;
>>>>>>> Stashed changes
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        GooseEntity gooseEntity = (GooseEntity) getType().create(p_241840_1_);
        gooseEntity.setVariant(this.getVariant());
        gooseEntity.setGender(this.random.nextInt(2));
        gooseEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return gooseEntity;
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

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping() && this.isFlying()) {
            return CreaturesSound.GOOSE_FLY;
        }
        else if (!this.isSleeping()) {
            return CreaturesSound.GOOSE_AMBIENT; }
        else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    public String getSpeciesName() {
<<<<<<< Updated upstream
        if (this.getVariant() == 1) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.canada");
            return s1.getString();
        }
        else if (this.getVariant() == 2) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.barnacle");
            return s1.getString();
        }
        else if (this.getVariant() == 3) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.graylag");
            return s1.getString();
        }
        else if (this.getVariant() == 4) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.snow");
            return s1.getString();
        }
        else if (this.getVariant() == 5) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.orinoco");
            return s1.getString();
        }
        else {
            return "Unknown";
        }
=======
        TranslationTextComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
>>>>>>> Stashed changes
    }

    public String getFoodName() {
        return StringUtils.capitalizeFirstLetter(Items.GRASS.toString());
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.goose_hatch_chance.get()).floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.goose_clutch_size.get());
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.GRASS, 1);
    }

    public boolean isMonogamous() {
        return true;
    }

    public int getMaxFlockSize() {
        return 10;
    }

}
