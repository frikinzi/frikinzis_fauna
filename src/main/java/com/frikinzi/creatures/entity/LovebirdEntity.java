package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.ai.SitOnShoulderGoal;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LovebirdEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static Map<Integer, Component> SPECIES_NAMES = new HashMap<Integer, Component>() {{
        put(1, Component.translatable("message.creatures.lovebird.fischers"));
        put(2, Component.translatable("message.creatures.lovebird.fischersmutation"));
        put(3, Component.translatable("message.creatures.lovebird.masked"));
        put(4, Component.translatable("message.creatures.lovebird.maskedmutation"));
        put(5, Component.translatable("message.creatures.lovebird.peach"));
        put(6, Component.translatable("message.creatures.lovebird.madagascar"));
        put(7, Component.translatable("message.creatures.lovebird.blackwingedlovebird"));
        put(8, Component.translatable("message.creatures.lovebird.redfaced"));
        put(9, Component.translatable("message.creatures.lovebird.swindern"));
        put(10, Component.translatable("message.creatures.lovebird.blackcheeked"));
        put(11, Component.translatable("message.creatures.lovebird.lilians"));
        put(12, Component.translatable("message.creatures.lovebird.aquamarine"));
        put(13, Component.translatable("message.creatures.lovebird.bluepeachfaced"));
    }};
    public static Map<Integer, Component> DESCRIPTIONS = new HashMap<Integer, Component>() {{
        put(1, Component.translatable("description.lovebird.fischers"));
        put(2, Component.translatable("description.lovebird.fischersmutation"));
        put(3, Component.translatable("description.lovebird.masked"));
        put(4, Component.translatable("description.lovebird.maskedmutation"));
        put(5, Component.translatable("description.lovebird.peach"));
        put(6, Component.translatable("description.lovebird.madagascar"));
        put(7, Component.translatable("description.lovebird.blackwingedlovebird"));
        put(8, Component.translatable("description.lovebird.redfaced"));
        put(9, Component.translatable("description.lovebird.swindern"));
        put(10, Component.translatable("description.lovebird.blackcheeked"));
        put(11, Component.translatable("description.lovebird.lilians"));
        put(12, Component.translatable("description.lovebird.aquamarine"));
        put(13, Component.translatable("description.lovebird.bluepeachfaced"));
    }};
    public static Map<Integer, String> SCIENTIFIC_NAMES = new HashMap<Integer, String>() {{
        put(1, "Agapornis fischeri");
        put(2, "Agapornis fischeri");
        put(3, "Agapornis personatus");
        put(4, "Agapornis personatus");
        put(5, "Agapornis roseicollis");
        put(6, "Agapornis canus");
        put(7, "Agapornis taranta");
        put(8, "Agapornis pullarius");
        put(9, "Agapornis swindernianus");
        put(10, "Agapornis nigrigenis");
        put(11, "Agapornis lilianae");
        put(12, "Agapornis roseicollis");
        put(13, "Agapornis roseicollis");
    }};

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.AFRICA))
            .put(2, List.of(Region.AFRICA))
            .put(3, List.of(Region.AFRICA))
            .put(4, List.of(Region.AFRICA))
            .put(5, List.of(Region.AFRICA))
            .put(6, List.of(Region.AFRICA))
            .put(7, List.of(Region.AFRICA))
            .put(8, List.of(Region.AFRICA))
            .put(9, List.of(Region.AFRICA))
            .put(10, List.of(Region.AFRICA))
            .put(11, List.of(Region.AFRICA))
            .put(12, List.of(Region.AFRICA))
            .put(13, List.of(Region.AFRICA))
            .build();

    public LovebirdEntity(EntityType<? extends LovebirdEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
        this.targetSelector.addGoal(1, new CreaturesBirdEntity.DefendBabyGoal());
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)));
        this.goalSelector.addGoal(3, new SitOnShoulderGoal(this));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Flying", 0, this::flyAnimController));
    }

    protected <E extends LovebirdEntity> PlayState flyAnimController(final AnimationState<E> event) {
        if (this.isFlying()) {
            if (this.isBaby())
                return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
            return event.setAndContinue(RawAnimation.begin().thenLoop("flying"));
        }

        if (this.isSleeping()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
        }
        if (this.isInSittingPose()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("sit"));
        }
        if (event.isMoving())
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    public LovebirdEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        LovebirdEntity lovebirdEntity = CreaturesEntities.LOVEBIRD.get().create(p_149088_);
        if (this.getVariant() == 1) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
                lovebirdEntity.setVariant(2); }
            else {
                lovebirdEntity.setVariant(this.getVariant());
            }
        }
        else if (this.getVariant() == 3) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 1) {
                lovebirdEntity.setVariant(4); }
            else {
                lovebirdEntity.setVariant(this.getVariant());
            }
        }
        else if (this.getVariant() == 5) {
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 1) {
                lovebirdEntity.setVariant(13);
            }
            if (this.random.nextInt(CreaturesConfig.lovebird_mutation_chance.get()) == 2) {
                lovebirdEntity.setVariant(12);
            }
            else {
                lovebirdEntity.setVariant(this.getVariant());
            }
        } else {
            lovebirdEntity.setVariant(this.getVariant());
        }
        lovebirdEntity.setGender(this.random.nextInt(2));
        lovebirdEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return lovebirdEntity;
    }

@Override
public int methodOfDeterminingVariant() {
        if (CreaturesConfig.breed_only_variants.get()) {
            int i = this.random.nextInt(numVariants()) + 1;
            while (i == 2 || i == 4 || i == 12 || i == 13) {
                i = this.random.nextInt(numVariants()) + 1;
            }
            //System.out.println(i);
            return i;
        }

        return this.random.nextInt(this.numVariants()) + 1;

    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(p_30392_ instanceof LovebirdEntity)) {
            return false;
        } else {
            LovebirdEntity lovebird = (LovebirdEntity)p_30392_;
            if (!lovebird.isTame()) {
                return false;
            } else if (lovebird.isInSittingPose()) {
                return false;
            } else {
                return this.isInLove() && lovebird.isInLove();
            }
        }
    }

    public String getSpeciesName() {
        Component s1 = SPECIES_NAMES.get(this.getVariant());
        if (s1 != null) {
            return s1.getString();
        } else {
            return "Unknown";
        }
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.3F;
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            return CreaturesSound.LOVEBIRD_AMBIENT.get(); } else {
            return null;
        }
    }

    public boolean isInvulnerableTo(DamageSource p_180431_1_) {
        if (p_180431_1_.is(DamageTypes.CACTUS)) {
            return true;
        }
        return super.isInvulnerableTo(p_180431_1_);
    }

    public double getHatchChance() {
        return CreaturesConfig.lovebird_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.lovebird_clutch_size.get());
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.PARROT;
    }

    public int numVariants() {
        return 13;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.4F).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        Component translatable = DESCRIPTIONS.get(this.getVariant());
        if (translatable != null) {
            return translatable;
        } return Component.translatable("creatures.unknown");
    }

    public boolean canTame() {
        return true;
    }



}
