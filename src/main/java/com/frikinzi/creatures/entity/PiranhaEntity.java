package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;

public class PiranhaEntity extends FishBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.redbelliedpiranha"),
            2, Component.translatable("message.creatures.blackdotpiranha"),
            3, Component.translatable("message.creatures.blackpiranha"),
            4, Component.translatable("message.creatures.pirayapiranha"),
            5, Component.translatable("message.creatures.rubyredpiranha")
    );

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.SOUTH_AMERICA))
            .put(2, List.of(Region.SOUTH_AMERICA))
            .put(3, List.of(Region.SOUTH_AMERICA))
            .put(4, List.of(Region.SOUTH_AMERICA))
            .put(5, List.of(Region.SOUTH_AMERICA))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Pygocentrus nattereri")
            .put(2, "Pygocentrus cariba")
            .put(3, "Serrasalmus rhombeus")
            .put(4, "Pygocentrus piraya")
            .put(5, "Serrasalmus sanchezi")
            .build();
    public PiranhaEntity(EntityType<? extends PiranhaEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(3, new TargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ShrimpEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Salmon.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GouramiEntity.class, false));
    }

    protected <E extends PiranhaEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (this.isInWater()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("swim")); }
        else {
            return event.setAndContinue(RawAnimation.begin().thenLoop("flop"));
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Swimming", 0, this::swimAnimController));
    }

@Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

public int getMaxSchoolSize() {
        return 10;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.PIRANHA_BUCKET.get());
    }

    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
        compoundnbt.putInt("Age", this.getAge());
    }

    protected SoundEvent getAmbientSound() {
        return CreaturesSound.PIRANHA_AMBIENT.get();
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public float getHatchChance() {
        return CreaturesConfig.piranha_hatch_chance.get().floatValue();
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.PIRANHA;
    }

    public Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public TargetGoal(PiranhaEntity p_i45818_1_, Class<T> p_i45818_2_) {
            super(p_i45818_1_, p_i45818_2_, true);
        }

        public boolean canUse() {
            if (super.canUse() && this.target != null) {
                return this.target.getHealth() < (this.target.getMaxHealth() / 2);
            }
            return false;
        }
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 2) {
            return -1;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.piranha");
    }

    public int numVariants() {
        return 5;
    }

    public int getScaleforGUI() {
        if (this.isBaby()) {
            return (int)(super.getScaleforGUI() *3f);

        }
        return (int)(super.getScaleforGUI());

    }
}
