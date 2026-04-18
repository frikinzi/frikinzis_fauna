package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
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
import java.util.Random;

public class ArapaimaEntity extends FishBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.SOUTH_AMERICA))
            .put(2, List.of(Region.SOUTH_AMERICA))
            .put(3, List.of(Region.SOUTH_AMERICA))
            .put(4, List.of(Region.SOUTH_AMERICA))
            .build();
    public ArapaimaEntity(EntityType<? extends ArapaimaEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, TroutEntity.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, ShrimpEntity.class, false));
    }

    protected <E extends ArapaimaEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (!this.isInWater()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("flop"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Swimming", 0, this::swimAnimController));
    }

@Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.ARAPAIMA_BUCKET.get());
    }

    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
        compoundnbt.putInt("Age", this.getAge());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
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
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.ARAPAIMA;
    }

    public String getSpeciesName() {
        Component i = Component.translatable("entity.creatures.arapaima");
        return i.getString();
    }

    public float getHatchChance() {
        return CreaturesConfig.arapaima_hatch_chance.get().floatValue();
    }

    public Item getFoodItem() {
        return CreaturesItems.FISH_FOOD.get();
    }

    public float getScale() {
        return this.isBaby() ? 0.2F : 1.0F;
    }

    public int getIUCNStatus() {
        return -1;
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.arapaima");

    }

    public String getScientificName() {
        return "Arapaima gigas";
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
            return InteractionResult.PASS;
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    public int numVariants() {
        return 3;
    }

    public int methodOfDeterminingVariant() {
        if (this.random.nextInt(CreaturesConfig.arapaima_mutation_chance.get()) == 1) {
            return 3;
        }
        else {
            return this.random.nextInt(2)+1;
        }
    }

    @Override
    public void layEgg(ServerLevel server, FishBase father) {
        int c = this.getClutchSize();
        for (int j = 0; j <= c; j++) {
            CreaturesRoeEntity egg = this.layEgg(this);
            if (egg != null) {
                FishBase mother = this;
                egg.setParentUUID(mother.getUUID());

                float f = (float)(this.getRandom().nextGaussian() * 0.05 + this.getHeightMultiplier());
                egg.setHeightMultiplier(f);

                int[] vars = {this.getVariant(), father.getVariant()};
                int rnd = new Random().nextInt(vars.length);
                if (this.random.nextInt(CreaturesConfig.arapaima_mutation_chance.get()) == 1) {
                    egg.setVariant(3);
                } else {
                    egg.setVariant(vars[rnd]);
                }
                egg.setGender(this.random.nextInt(2));

                Random rand = new Random();
                egg.setPos(
                        Mth.floor(mother.getX()) + 0.5 + (-1 + rand.nextFloat()),
                        Mth.floor(mother.getY()) + 0.5,
                        Mth.floor(mother.getZ()) + 0.5 + (-1 + rand.nextFloat()));
                server.addFreshEntityWithPassengers(egg);
            }
            server.broadcastEntityEvent(this, (byte) 18);
        }

        net.minecraft.util.RandomSource random = this.getRandom();
        for (int i = 0; i < 17; ++i) {
            double d0 = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;
            double d3 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            double d4 = 0.5D + random.nextDouble() * this.getBbHeight();
            double d5 = random.nextDouble() * this.getBbWidth() * 2.0D - this.getBbWidth();
            this.level().addParticle(ParticleTypes.HEART,
                    this.getX() + d3, this.getY() + d4, this.getZ() + d5, d0, d1, d2);
        }
        this.setBred(true);
        if (server.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            server.addFreshEntity(new ExperienceOrb(server,
                    this.getX(), this.getY(), this.getZ(),
                    this.getRandom().nextInt(7) + 1));
        }
    }

    public int getScaleforGUI() {
        if (this.isBaby()) {
            return (int)(super.getScaleforGUI() *3f);

        }
        return (int)(super.getScaleforGUI());
    }

}
