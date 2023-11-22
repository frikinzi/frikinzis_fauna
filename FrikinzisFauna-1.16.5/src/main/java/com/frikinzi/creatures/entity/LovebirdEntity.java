package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.client.model.LovebirdModel;
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
>>>>>>> Stashed changes
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
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

<<<<<<< Updated upstream
public class LovebirdEntity extends TameableBirdBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

=======
import java.util.HashMap;
import java.util.Map;

public class LovebirdEntity extends TameableBirdBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public static Map<Integer, TranslationTextComponent> SPECIES_NAMES = new HashMap<Integer, TranslationTextComponent>() {{
        put(1, new TranslationTextComponent("message.creatures.lovebird.fischers"));
        put(2, new TranslationTextComponent("message.creatures.lovebird.fischersmutation"));
        put(3, new TranslationTextComponent("message.creatures.lovebird.masked"));
        put(4, new TranslationTextComponent("message.creatures.lovebird.maskedmutation"));
        put(5, new TranslationTextComponent("message.creatures.lovebird.peach"));
        put(6, new TranslationTextComponent("message.creatures.lovebird.madagascar"));
        put(7, new TranslationTextComponent("message.creatures.lovebird.blackwingedlovebird"));
        put(8, new TranslationTextComponent("message.creatures.lovebird.redfaced"));
        put(9, new TranslationTextComponent("message.creatures.lovebird.swindern"));
        put(10, new TranslationTextComponent("message.creatures.lovebird.blackcheeked"));
        put(11, new TranslationTextComponent("message.creatures.lovebird.lilians"));
        put(12, new TranslationTextComponent("message.creatures.lovebird.aquamarine"));
        put(13, new TranslationTextComponent("message.creatures.lovebird.bluepeachfaced"));
    }};
    
>>>>>>> Stashed changes
    public LovebirdEntity(EntityType<? extends LovebirdEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.isBaby()) {
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
        if (event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lovebird.walking", true));
            return PlayState.CONTINUE;
        } if (!this.onGround || this.isFlying()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("flying", true));
        return PlayState.CONTINUE;
    } if (this.isSleeping()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lovebird.sleep", true));
        return PlayState.CONTINUE;
    } if (this.isInSittingPose()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", true));
        return PlayState.CONTINUE;
    }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lovebird.idle", true));
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
        return 14;
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        LovebirdEntity lovebirdentity = (LovebirdEntity) getType().create(p_241840_1_);
        lovebirdentity.setGender(this.random.nextInt(2));
        if (this.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
            lovebirdentity.setVariant(2); }
            else {
                lovebirdentity.setVariant(this.getVariant());
            }
        }
        else if (this.getVariant() == 3) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 1) {
                lovebirdentity.setVariant(4); }
            else {
                lovebirdentity.setVariant(this.getVariant());
            }
        }
        else if (this.getVariant() == 5) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 1) {
                lovebirdentity.setVariant(13); }
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
                lovebirdentity.setVariant(12); }
            else {
                lovebirdentity.setVariant(this.getVariant());
            }
        } else {
            lovebirdentity.setVariant(this.getVariant());
        }
        lovebirdentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return lovebirdentity;
    }

    public CreaturesEggEntity layEgg(CreaturesBirdEntity animal) {
        CreaturesEggEntity egg = new CreaturesEggEntity(ModEntityTypes.EGG.get(), this.level);
<<<<<<< Updated upstream
        egg.setSpecies(ModEntityTypes.getIntFromBirdEntity(animal));
=======
        egg.setSpecies(EntityAttributes.getBirdEntityMap().inverse().get(animal.getType()));
>>>>>>> Stashed changes
        egg.setGender(this.random.nextInt(2));
        if (this.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
                egg.setVariant(2); }
            else {
                egg.setVariant(this.getVariant());
            }
        }
        else if (this.getVariant() == 3) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 1) {
                egg.setVariant(4); }
            else {
                egg.setVariant(this.getVariant());
            }
        }
        else if (this.getVariant() == 5) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 1) {
                egg.setVariant(13); }
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
                egg.setVariant(12); }
            else {
                egg.setVariant(this.getVariant());
            }
        }
        else {
            egg.setVariant(this.getVariant());
        }
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
        return CreaturesSound.LOVEBIRD_AMBIENT; } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.PARROT;
    }

    @Override
    public int methodofDeterminingVariant(IWorld p_213610_1_) {
        if (CreaturesConfig.breed_only_variants.get() == true) {
        int i = this.random.nextInt(determineVariant());
        while (i == 2 || i == 4 || i == 12 || i == 13) {
            i = this.random.nextInt(determineVariant());
        }
        return i; }

        else {
            return this.random.nextInt(determineVariant());
        }

    }

    public String getSpeciesName() {
<<<<<<< Updated upstream
        if (this.getVariant() == 1) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.fischers");
            return s1.getString();
        }
        else if (this.getVariant() == 2) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.fischersmutation");
            return s1.getString();
        }
        else if (this.getVariant() == 3) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.masked");
            return s1.getString();
        }
        else if (this.getVariant() == 4) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.maskedmutation");
            return s1.getString();
        }
        else if (this.getVariant() == 5) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.peach");
            return s1.getString();
        }
        else if (this.getVariant() == 6) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.madagascar");
            return s1.getString();
        }
        else if (this.getVariant() == 7) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.blackwingedlovebird");
            return s1.getString();
        }   else if (this.getVariant() == 8) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.redfaced");
            return s1.getString();
        } else if (this.getVariant() == 9) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.swindern");
            return s1.getString();
        } else if (this.getVariant() == 10) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.blackcheeked");
            return s1.getString();
        } else if (this.getVariant() == 11) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.lilians");
            return s1.getString();
        } else if (this.getVariant() == 12) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.aquamarine");
            return s1.getString();
        } else if (this.getVariant() == 13) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lovebird.bluepeachfaced");
            return s1.getString();
        } else {
            return "Unknown";
        }
=======
        TranslationTextComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
>>>>>>> Stashed changes
    }

    @Override
    public boolean isMonogamous() {
        return true;
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.lovebird_hatch_chance.get()).floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.lovebird_clutch_size.get());
    }

    public int getMaxFlockSize() {
        this.hurt(DamageSource.CACTUS,10);
        return 10;
    }

    public boolean isInvulnerableTo(DamageSource p_180431_1_) {
        if (p_180431_1_ == DamageSource.CACTUS) {
            return true;
        }
        return super.isInvulnerableTo(p_180431_1_);
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 0.3F;
    }



}
