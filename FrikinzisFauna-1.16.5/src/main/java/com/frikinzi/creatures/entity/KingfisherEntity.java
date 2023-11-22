package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.NonTameableFlyingBirdBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.frikinzi.creatures.registry.ModEntityTypes;
import com.frikinzi.creatures.util.CreaturesLootTables;
<<<<<<< Updated upstream
=======
import com.google.common.collect.ImmutableMap;
>>>>>>> Stashed changes
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NonTamedTargetGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fluids.FluidAttributes;
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
import java.util.Map;
>>>>>>> Stashed changes
import java.util.function.Predicate;

public class KingfisherEntity extends NonTameableFlyingBirdBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    protected BlockPos blockPos = BlockPos.ZERO;
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.CRAB_PINCERS, CreaturesItems.GOURAMI, CreaturesItems.RAW_TROUT, Items.COD, Items.SALMON);
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == ModEntityTypes.GOLDFISH.get() || entitytype == EntityType.SALMON || entitytype == ModEntityTypes.GOURAMI.get() || entitytype == ModEntityTypes.GUPPY.get() || entitytype == ModEntityTypes.SHRIMP.get();
    };
<<<<<<< Updated upstream
=======
    public static final Map<Integer, TranslationTextComponent> SPECIES_NAMES = ImmutableMap.<Integer, TranslationTextComponent>builder()
            .put(1, new TranslationTextComponent("message.creatures.commonkingfisher"))
            .put(2, new TranslationTextComponent("message.creatures.dwarforiental"))
            .put(3, new TranslationTextComponent("message.creatures.forestkingfisher"))
            .put(4, new TranslationTextComponent("message.creatures.beltedkingfisher"))
            .put(5, new TranslationTextComponent("message.creatures.marquesas"))
            .put(6, new TranslationTextComponent("message.creatures.spottedkingfisher"))
            .build();
>>>>>>> Stashed changes

    public KingfisherEntity(EntityType<? extends KingfisherEntity> p_i50251_1_, World p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, FOOD_ITEMS));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, PlayerEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, OspreyEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, WaterMobEntity.class, false, PREY_SELECTOR));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {

        World world = this.level;
        BlockPos blockpos = this.blockPos.below();
        //BlockState blockstate = world.getBlockState(blockpos);
        //Block block = blockstate.getBlock();
        if (this.isAggressive() && !this.onGround & this.getDeltaMovement().y < 0.0D & !this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("dive", true));
            return PlayState.CONTINUE;
        }
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
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, (double)1.0F);
    }

    public int determineVariant() {
        return 7;
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        KingfisherEntity rollerentity = (KingfisherEntity) getType().create(p_241840_1_);
        rollerentity.setVariant(this.getVariant());
        rollerentity.setGender(this.random.nextInt(2));
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

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.KINGFISHER_AMBIENT; } else {
            return null;
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SMALL_BIRD_GENERIC;
    }

    public String getSpeciesName() {
<<<<<<< Updated upstream
        if (this.getVariant() == 1) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.commonkingfisher");
            return s1.getString();
        }
        else if (this.getVariant() == 2) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.dwarforiental");
            return s1.getString();
        }
        else if (this.getVariant() == 3) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.forestkingfisher");
            return s1.getString();
        }
        else if (this.getVariant() == 4) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.beltedkingfisher");
            return s1.getString();
        }
        else if (this.getVariant() == 5) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.marquesas");
            return s1.getString();
        }
        else if (this.getVariant() == 6) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.spottedkingfisher");
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
        return StringUtils.capitalizeFirstLetter(Items.COD.toString());
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.kingfisher_hatch_chance.get()).floatValue();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.kingfisher_clutch_size.get());
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.COD, 1);
    }

    public void aiStep() {
        super.aiStep();
        Vector3d vector3d = this.getDeltaMovement();
        if (this.isAggressive() & !this.onGround && vector3d.y < 0.0D) {
            this.setDeltaMovement(vector3d.multiply(1.0D, 2.0D, 1.0D));
        }
    }


    public boolean canBreatheUnderwater() {
        if (this.isAggressive()) {
        return true; } else {
            return false;
        }
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 0.3F;
    }

}
