package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.TameableBirdBase;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.registry.ModEntityTypes;
import com.frikinzi.creatures.util.CreaturesLootTables;
<<<<<<< Updated upstream
=======
import com.frikinzi.creatures.util.EntityAttributes;
import com.google.common.collect.ImmutableMap;
>>>>>>> Stashed changes
import com.google.common.collect.Sets;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
<<<<<<< Updated upstream
=======
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
>>>>>>> Stashed changes
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

<<<<<<< Updated upstream
=======
import java.util.Map;
>>>>>>> Stashed changes
import java.util.Set;

public class RavenEntity extends TameableBirdBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.ROTTEN_FLESH, Items.EGG, Items.CHICKEN);
<<<<<<< Updated upstream
=======
    public static Map<Integer, TranslationTextComponent> SPECIES_NAMES = ImmutableMap.of(
            1, new TranslationTextComponent("message.creatures.commonraven"),
            2, new TranslationTextComponent("message.creatures.brownheadedraven"),
            3, new TranslationTextComponent("message.creatures.whitethroatedraven"),
            4, new TranslationTextComponent("message.creatures.thickbilledraven"),
            5, new TranslationTextComponent("message.creatures.commonravenalbino")
    );
>>>>>>> Stashed changes

    public RavenEntity(EntityType<? extends RavenEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(1, new CreaturesBirdEntity.DefendBabyGoal());
        this.goalSelector.addGoal(6, new FollowFlockLeaderGoal(this));

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
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

<<<<<<< Updated upstream
    public int determineVariant() {
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get()) == 1) {
        return 3; } else {
            return 2;
=======
    public int methodofDeterminingVariant(IWorld p_213610_1_) {
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get()) == 1) {
            return 5;
        }
        else {
            return this.random.nextInt(5);
>>>>>>> Stashed changes
        }
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        RavenEntity ravenentity = (RavenEntity) getType().create(p_241840_1_);
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get() * 2) == 1) {
            ravenentity.setVariant(2);
        } else {
            ravenentity.setVariant(this.getVariant());
        }
        ravenentity.setGender(this.random.nextInt(2));
        ravenentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return ravenentity;
    }

    public CreaturesEggEntity layEgg(CreaturesBirdEntity animal) {
        CreaturesEggEntity egg = new CreaturesEggEntity(ModEntityTypes.EGG.get(), this.level);
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get() * 2) == 1) {
<<<<<<< Updated upstream
            egg.setVariant(2);
=======
            egg.setVariant(5);
>>>>>>> Stashed changes
        } else {
            egg.setVariant(this.getVariant());
        }
        egg.setGender(this.random.nextInt(2));
<<<<<<< Updated upstream
        egg.setSpecies(ModEntityTypes.getIntFromBirdEntity(animal));
=======
        egg.setSpecies(EntityAttributes.getBirdEntityMap().inverse().get(animal.getType()));
>>>>>>> Stashed changes
        return egg;
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
        if (!this.isSleeping()) {
        return CreaturesSound.RAVEN_AMBIENT; }
        return null;
    }

    public Set<Item> getTamedFood() {
        TAME_FOOD = Sets.newHashSet(Items.ROTTEN_FLESH, Items.EGG, Items.CHICKEN);
        return TAME_FOOD;
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.RAVEN;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.ROTTEN_FLESH, 1);
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.raven_hatch_chance.get()).floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.raven_clutch_size.get());
    }

<<<<<<< Updated upstream
=======
    public int determineVariant() {
        return 6;
    }

>>>>>>> Stashed changes
    public boolean isMonogamous() {
        return true;
    }

    public int getMaxFlockSize() {
        return 4;
    }

<<<<<<< Updated upstream
=======
    protected float getSoundVolume() {
        return 0.2F;
    }

    public int getAmbientSoundInterval() {
        return 600;
    }

    public String getSpeciesName() {
        TranslationTextComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

>>>>>>> Stashed changes
}
