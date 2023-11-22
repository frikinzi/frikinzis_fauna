package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.NonTameableFlyingBirdBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.util.CreaturesLootTables;
import com.google.common.collect.Sets;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import org.codehaus.plexus.util.StringUtils;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

<<<<<<< Updated upstream
=======
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
>>>>>>> Stashed changes
import java.util.Set;

public class TanagerEntity extends NonTameableFlyingBirdBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.MEALWORMS, Items.MELON_SLICE, Items.SWEET_BERRIES);
<<<<<<< Updated upstream
=======
    public static final Map<Integer, TranslationTextComponent> SPECIES_NAMES;

    static {
        Map<Integer, TranslationTextComponent> messageMap = new HashMap<>();
        messageMap.put(1, new TranslationTextComponent("message.creatures.paradisetanager"));
        messageMap.put(2, new TranslationTextComponent("message.creatures.spangledberyl"));
        messageMap.put(3, new TranslationTextComponent("message.creatures.cherrythroated"));
        messageMap.put(4, new TranslationTextComponent("message.creatures.greenheaded"));
        messageMap.put(5, new TranslationTextComponent("message.creatures.redheadedtanager"));
        messageMap.put(6, new TranslationTextComponent("message.creatures.scarlettanager"));
        messageMap.put(7, new TranslationTextComponent("message.creatures.silverbeaked"));
        messageMap.put(8, new TranslationTextComponent("message.creatures.multicoloredtanager"));
        messageMap.put(9, new TranslationTextComponent("message.creatures.bluegraytanager"));
        SPECIES_NAMES = Collections.unmodifiableMap(messageMap);
    }
>>>>>>> Stashed changes

    public TanagerEntity(EntityType<? extends TanagerEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, FOOD_ITEMS));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 2.2D, 2.2D));
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
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
    }

    public int determineVariant() {
        return 10;
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        TanagerEntity bushtitEntity = (TanagerEntity) getType().create(p_241840_1_);
        bushtitEntity.setVariant(this.getVariant());
        bushtitEntity.setGender(this.random.nextInt(2));
        bushtitEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return bushtitEntity;
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
            return TAME_FOOD = Sets.newHashSet(Items.MELON_SLICE, Items.SWEET_BERRIES);
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.TANAGER_AMBIENT; } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

<<<<<<< Updated upstream
    @Override
    public String getSpeciesName() {
        if (this.getVariant() == 1) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.paradisetanager");
            return s1.getString();
        }
        else if (this.getVariant() == 2) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.spangledberyl");
            return s1.getString();
        }
        else if (this.getVariant() == 3) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.cherrythroated");
            return s1.getString();
        }
        else if (this.getVariant() == 4) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.greenheaded");
            return s1.getString();
        }
        else if (this.getVariant() == 5) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.redheadedtanager");
            return s1.getString();
        }
        else if (this.getVariant() == 6) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.scarlettanager");
            return s1.getString();
        }
        else if (this.getVariant() == 7) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.silverbeaked");
            return s1.getString();
        }
        else if (this.getVariant() == 8) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.bluegraytanager");
            return s1.getString();
        }
        else if (this.getVariant() == 9) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.multicoloredtanager");
            return s1.getString();
        } else {
            return "Unknown";
        }
=======
    public String getSpeciesName() {
        TranslationTextComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
>>>>>>> Stashed changes
    }

    public String getFoodName() {
        return StringUtils.capitalizeFirstLetter(Items.MELON_SLICE.toString());
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.MELON_SLICE, 1);
    }

    @Override
    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.bushtit_clutch_size.get());
    }

    @Override
    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.bushtit_hatch_chance.get()).floatValue();
    }

    public int getMaxFlockSize() {
        return 4;
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 0.3F;
    }

}
