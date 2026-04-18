package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Map;

public class SquidEntity extends FishBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public float xBodyRotO  =1;
    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.<Integer, Component>builder()
            .put(1, Component.translatable("message.creatures.humboldt"))
            .put(2, Component.translatable("message.creatures.swordtipsquid"))
            .put(3, Component.translatable("message.creatures.europeansquid"))
            .put(4, Component.translatable("message.creatures.purplebackflying"))
            .put(5, Component.translatable("message.creatures.antarcticflying"))
            .put(6, Component.translatable("message.creatures.japaneseflying"))
            .put(7, Component.translatable("message.creatures.newzealandarrow"))
            .put(8, Component.translatable("message.creatures.caribbeansquid"))
            .put(9, Component.translatable("message.creatures.bigfinsquid"))
            .put(10, Component.translatable("message.creatures.southernreef"))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Dosidicus gigas")
            .put(2, "Uroteuthis edulis")
            .put(3, "Loligo vulgaris")
            .put(4, "Sthenoteuthis oualaniensis")
            .put(5, "Todarodes filippovae")
            .put(6, "Todarodes pacificus")
            .put(7, "Nototodarus sloanii")
            .put(8, "Sepioteuthis sepioidea")
            .put(9,"Sepioteuthis lessoniana")
            .put(10,"Sepioteuthis australis")
            .build();

    public static Map<Integer, Integer> SQUID = ImmutableMap.<Integer, Integer>builder()
            .put(1, 3)
            .put(2, 1)
            .put(3, 2)
            .put(4, 1)
            .put(5, 1)
            .put(6, 1)
            .put(7, 1)
            .put(8, 3)
            .put(9, 3)
            .put(10, 3)
            .build();

    public static Map<Integer, Double> SIZES = ImmutableMap.<Integer, Double>builder()
            .put(1, 2.0D)
            .put(2, 1.0D)
            .put(3, 0.8D)
            .put(4, 0.8D)
            .put(5, 1.0D)
            .put(6, 0.8D)
            .put(7, 0.8D)
            .put(8, 0.8D)
            .put(9, 0.8D)
            .put(10, 0.8D)
            .build();

    public SquidEntity(EntityType<? extends SquidEntity> p_i50246_1_, Level p_i50246_2_) {
        super(p_i50246_1_, p_i50246_2_);
    }

    protected <E extends SquidEntity> PlayState swimAnimController(final AnimationState<E> event)
    {
        if (this.isReef() || this.isBaby()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("swim_reef"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Swimming", 0, this::swimAnimController));
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 2.2D));
    }

@Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

public ItemStack getBucketItemStack() {
        return new ItemStack(CreaturesItems.TROUT_BUCKET.get());
    }

    public void saveToBucketTag(ItemStack p_204211_1_) {
        super.saveToBucketTag(p_204211_1_);
        CompoundTag compoundnbt = p_204211_1_.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
        compoundnbt.putInt("BucketSubVariantTag", this.getSubVariant());
        compoundnbt.putFloat("BucketHeightMultiplier", this.getHeightMultiplier());
        compoundnbt.putInt("Age", this.getAge());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SQUID_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SQUID_DEATH;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.1D);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.SQUID;
    }

    public float getHatchChance() {
        return Double.valueOf(CreaturesConfig.squid_hatch_chance.get()).floatValue();
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

    @Override
    public int getSubVariant() {
        Integer max = SQUID.get(this.getVariant());
        if (max == null) return 1;
        return Mth.clamp(super.getSubVariant(), 1, max);
    }

//    public void setSubVariant(int p_191997_1_) {
//        this.entityData.set(VARIANT_SUBID, p_191997_1_);
//    }

    private Vec3 rotateVector(Vec3 p_207400_1_) {
        Vec3 vector3d = p_207400_1_.xRot(this.xBodyRotO * ((float)Math.PI / 180F));
        return vector3d.yRot(-this.yBodyRotO * ((float)Math.PI / 180F));
    }

    private void spawnInk() {
        this.playSound(SoundEvents.SQUID_SQUIRT, this.getSoundVolume(), this.getVoicePitch());
        Vec3 vector3d = this.rotateVector(new Vec3(0.0D, -1.0D, 0.0D)).add(this.getX(), this.getY(), this.getZ());

        for(int i = 0; i < 30; ++i) {
            Vec3 vector3d1 = this.rotateVector(new Vec3((double)this.random.nextFloat() * 0.6D - 0.3D, -1.0D, (double)this.random.nextFloat() * 0.6D - 0.3D));
            Vec3 vector3d2 = vector3d1.scale(0.3D + (double)(this.random.nextFloat() * 2.0F));
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.SQUID_INK, vector3d.x, vector3d.y + 0.5D, vector3d.z, 0, vector3d2.x, vector3d2.y, vector3d2.z, (double)0.1F);
        }

    }

    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if (super.hurt(p_70097_1_, p_70097_2_) && this.getLastHurtByMob() != null) {
            this.spawnInk();
            return true;
        } else {
            return false;
        }
    }

    public boolean isReef() {
        return this.getVariant() == 8 || this.getVariant() == 9 || this.getVariant() == 10;
    }

    public double getSizeMultiplier() {
        Double size = SIZES.get(this.getVariant());
        if (size == null) return 0.8D;
        return size;
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 1 || this.getVariant() == 2 || this.getVariant() == 3 || this.getVariant() == 9) {
            return -1;
        }
        return super.getIUCNStatus();
    }

//    public int methodofDeterminingVariant(LevelAccessor p_213610_1_) {
//        if (CreaturesConfig.biome_only_variants.get()) {
//            Biome biome = p_213610_1_.getBiome(this.blockPosition());
//            RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());
//            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);
//
//            if (types.contains(BiomeDictionary.Type.HOT) && types.contains(BiomeDictionary.Type.OCEAN)) {
//                return this.random.nextInt(3) + 8;
//            }
//            return this.random.nextInt(7)+1;
//        } return this.random.nextInt(10)+1;
//    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
            return InteractionResult.PASS;
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    public static class SquidData implements SpawnGroupData {
        public final int variant;

        public SquidData(int p_i231557_1_) {
            this.variant = p_i231557_1_;
        }
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public int methodOfDeterminingSubVariant() {
        return this.random.nextInt(SQUID.get(this.getVariant()))+1;
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.squid");
    }

    public int numVariants() {
        return 10;
    }


}
