package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StorkEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, CreaturesItems.RAW_BLUECRAB.get(), CreaturesItems.CRAB_PINCERS.get(), CreaturesItems.RAW_LUNGFISH.get(), CreaturesItems.RAW_EDIBLECRAB.get());
    public static Map<Integer, Integer> STORK_MODEL = ImmutableMap.of(
            1, 1,
            2, 1,
            3, 1,
            4, 1,
            5, 2,
            6, 2,
            7, 2,
            8,3,
            9,3
    );
    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.AFRICA))
            .put(2, List.of(Region.ASIA))
            .put(3, List.of(Region.ASIA))
            .put(4, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .put(5, List.of(Region.AFRICA))
            .put(6, List.of(Region.ASIA))
            .put(7, List.of(Region.NORTH_AMERICA,Region.SOUTH_AMERICA))
            .put(8, List.of(Region.AFRICA))
            .put(9, List.of(Region.ASIA))
            .build();
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.yellowbilledstork"),
            2, Component.translatable("message.creatures.paintedstork"),
            3, Component.translatable("message.creatures.milkystork"),
            4, Component.translatable("message.creatures.woodstork"),
            5, Component.translatable("message.creatures.saddlebilledstork"),
            6, Component.translatable("message.creatures.blackneckedstork"),
            7, Component.translatable("message.creatures.jabirustork"),
            8, Component.translatable("message.creatures.africanopenbill"),
            9, Component.translatable("message.creatures.asianopenbill")
    );
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Mycteria ibis")
            .put(2, "Mycteria leucocephala")
            .put(3, "Mycteria cinerea")
            .put(4, "Mycteria americana")
            .put(5, "Ephippiorhynchus senegalensis")
            .put(6, "Ephippiorhynchus asiaticus")
            .put(7, "Jabiru mycteria")
            .put(8, "Anastomus lamelligerus")
            .put(9, "Anastomus oscitans")
            .build();

    public StorkEntity(EntityType<? extends StorkEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, LungfishEntity.class, false));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1.5D, 1.2D));
    }

    protected <E extends StorkEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F);
    }

    public int numVariants() {
        return 9;
    }

    @Override
    public StorkEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        StorkEntity bushtitEntity = CreaturesEntities.STORK.get().create(p_241840_1_);
        bushtitEntity.setVariant(this.getVariant());
        bushtitEntity.setGender(this.random.nextInt(2));
        bushtitEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return bushtitEntity;
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
            if (this.getModelNumberFromVariant() == 2) {
                return CreaturesSound.JABIRU_AMBIENT.get();
            }
        return CreaturesSound.STORK_AMBIENT.get(); } else {
            return null;
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return CreaturesSound.STORK_HURT.get();
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

    @Override
    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.stork_clutch_size.get());
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.COD, 1);
    }

    @Override
    public double getHatchChance() {
        return CreaturesConfig.stork_hatch_chance.get();
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
        return 0.3F;
    }

    public int getModelNumberFromVariant() {
        if (STORK_MODEL.get(this.getVariant()) != null) {
            return STORK_MODEL.get(this.getVariant());
        }
        return 1;
    }

    public int getIUCNStatus() {
        if (this.getVariant() == 5) {
            return 1;
        }
        if (this.getVariant() == 3) {
            return 3;
        } return super.getIUCNStatus();
    }

    public boolean isSexuallyDimorphic() {
        if (this.getVariant() == 5 || this.getVariant() == 6 || this.getVariant() == 8 || this.getVariant() == 9) {
            return true;
        }
        return false;
    }

    public String getGenderString2() {
        if (this.isSexuallyDimorphic()) {
            return super.getGenderString();
        } return "";
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        if (this.getVariant() == 5) {
            return Component.translatable("description.creatures.saddlebilledstork");
        } if (this.getVariant() == 6) {
            return Component.translatable("description.creatures.blackneckedstork");
        } if (this.getVariant() == 7) {
            return Component.translatable("description.creatures.jabirustork");
        } if (this.getVariant() == 8 || this.getVariant() == 9) {
            return Component.translatable("description.creatures.openbillstork");
        }
        return Component.translatable("description.creatures.stork");
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

}
