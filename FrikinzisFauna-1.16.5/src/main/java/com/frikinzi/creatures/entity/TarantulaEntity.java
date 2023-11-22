package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.util.CreaturesLootTables;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
<<<<<<< Updated upstream
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
=======
import java.util.*;
>>>>>>> Stashed changes

public class TarantulaEntity extends AbstractCrabBase implements IAnimatable {
    final private int[] old_worlds = new int[] {6,9,11,13};
    final private int[] jungle_variants = new int[] {1,2,4,5,8,9,12,13,14};
    final private int[] desert_variants = new int[] {3,6,7,10,11};
    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(TarantulaEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(TarantulaEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.defineId(TarantulaEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> THREAT = EntityDataManager.defineId(TarantulaEntity.class, DataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.MEALWORMS);
    private AnimationFactory factory = new AnimationFactory(this);
<<<<<<< Updated upstream
=======
    public static Map<Integer, TranslationTextComponent> SPECIES_NAMES;

    static {
        Map<Integer, TranslationTextComponent> map = new HashMap<>();
        map.put(1, new TranslationTextComponent("message.creatures.celegans"));
        map.put(2, new TranslationTextComponent("message.creatures.pumpkinpatch"));
        map.put(3, new TranslationTextComponent("message.creatures.mexicanfireleg"));
        map.put(4, new TranslationTextComponent("message.creatures.entrerios"));
        map.put(5, new TranslationTextComponent("message.creatures.brazilianjewel"));
        map.put(6, new TranslationTextComponent("message.creatures.orangebaboon"));
        map.put(7, new TranslationTextComponent("message.creatures.chacogoldenknee"));
        map.put(8, new TranslationTextComponent("message.creatures.lavatarantula"));
        map.put(9, new TranslationTextComponent("message.creatures.gootysapphire"));
        map.put(10, new TranslationTextComponent("message.creatures.arizonablonde"));
        map.put(11, new TranslationTextComponent("message.creatures.goldenblueleg"));
        map.put(12, new TranslationTextComponent("message.creatures.purpletree"));
        map.put(13, new TranslationTextComponent("message.creatures.neonblueleg"));
        map.put(14, new TranslationTextComponent("message.creatures.juruensis"));
        SPECIES_NAMES = Collections.unmodifiableMap(map);
    }

>>>>>>> Stashed changes

    public TarantulaEntity(EntityType<? extends TarantulaEntity> p_i48550_1_, World p_i48550_2_) {
        super(p_i48550_1_, p_i48550_2_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new TarantulaEntity.TarantulaBreedGoal(1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new TarantulaEntity.WanderGoal(this, 0.7D,0.0005F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new TarantulaEntity.ThreatGoal());
        this.goalSelector.addGoal(4, new TarantulaEntity.AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 2.2D, 1.2D));

    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        this.setVariant(this.methodofDeterminingVariant(p_213386_1_));
        this.setGender(this.random.nextInt(2));
        if (p_213386_4_ == null) {
            p_213386_4_ = new AgeableData(false);
        }

        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.isThreatPose()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("threat", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() && this.isClimbing()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("climb", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    public double getPassengersRidingOffset() {
        return (double)(this.getBbHeight() * 0.5F);
    }

    protected PathNavigator createNavigation(World p_175447_1_) {
        return new ClimberPathNavigator(this, p_175447_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(GENDER, 0);
        this.entityData.define(THREAT, false);
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 0.13F;
    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }

    }


    public boolean onClimbable() {
        return this.isClimbing();
    }

    public void makeStuckInBlock(BlockState p_213295_1_, Vector3d p_213295_2_) {
        if (!p_213295_1_.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(p_213295_1_, p_213295_2_);
        }

    }

    public CreatureAttribute getMobType() {
        return CreatureAttribute.ARTHROPOD;
    }

    public boolean canBeAffected(EffectInstance p_70687_1_) {
        if (p_70687_1_.getEffect() == Effects.POISON) {
            net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(this, p_70687_1_);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
            return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
        }
        return super.canBeAffected(p_70687_1_);
    }


    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean p_70839_1_) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (p_70839_1_) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        TarantulaEntity fiddlercrabentity = (TarantulaEntity) getType().create(p_241840_1_);
        fiddlercrabentity.setVariant(this.getVariant());
        fiddlercrabentity.setGender(this.random.nextInt(2));
        return fiddlercrabentity;
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        if (super.doHurtTarget(p_70652_1_)) {
            if (p_70652_1_ instanceof LivingEntity) {
                int i = 0;
                if (this.level.getDifficulty() == Difficulty.NORMAL) {
                    i = 7;
                } else if (this.level.getDifficulty() == Difficulty.HARD) {
                    i = 15;
                }
                if (this.isOldWorld()) {
                    i *= 2;
                }

                if (i > 0) {
                    ((LivingEntity)p_70652_1_).addEffect(new EffectInstance(Effects.POISON, i * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean canMate(AnimalEntity p_70878_1_) {
        if (p_70878_1_ == this) {
            return false;
        } else if (p_70878_1_.getClass() != this.getClass()) {
            return false;
        } else {
            TarantulaEntity partner = (TarantulaEntity)p_70878_1_;
            if (this.getGender() == partner.getGender()) {
                return false;
            }
            return this.isInLove() && p_70878_1_.isInLove();
        }
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putInt("Variant", this.getVariant());
        p_213281_1_.putInt("Gender", this.getGender());
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setVariant(p_70037_1_.getInt("Variant"));
        this.setGender(p_70037_1_.getInt("Gender"));
    }

    public void aiStep() {
        super.aiStep();
        if (this.getTarget() != null) {
            if (this.getTarget().isDeadOrDying()) {
                this.setTarget(null);
            }
        }
        if (this.isThreatPose()) {
            TarantulaEntity.this.getNavigation().stop();
            TarantulaEntity.this.setDeltaMovement(0,0,0);
            TarantulaEntity.this.setDeltaMovement(TarantulaEntity.this.getDeltaMovement().scale(0));
        }
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public int getVariant() {
        return MathHelper.clamp(this.entityData.get(DATA_VARIANT_ID), 1, 14);
    }

    public int getGender() {
        return MathHelper.clamp(this.entityData.get(GENDER), 0, 2);
    }

    public void setVariant(int p_191997_1_) {
        this.entityData.set(DATA_VARIANT_ID, p_191997_1_);
    }

    public void setGender(int p_191997_1_) {
        this.entityData.set(GENDER, p_191997_1_);
    }

    public int methodofDeterminingVariant(IWorld p_213610_1_) {
        if (CreaturesConfig.biome_only_variants.get()) {
            Biome biome = p_213610_1_.getBiome(this.blockPosition());
            RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);
            if (types.contains(BiomeDictionary.Type.JUNGLE)) {
                int i = this.random.nextInt(jungle_variants.length);
                return jungle_variants[i];
            } if (types.contains(BiomeDictionary.Type.DRY)) {
                int i = this.random.nextInt(desert_variants.length);
                return desert_variants[i];
            }
        }
<<<<<<< Updated upstream
        return this.random.nextInt(15);

    }

=======
        return this.random.nextInt(determineVariant());

    }

    public int determineVariant() {
        return 15;
    }

>>>>>>> Stashed changes
    public boolean contains(int[] s, int type) {
        for (int t : s) {
            if (t == type) {
                return true;
            }
        }
        return false;
    }

    public boolean isOldWorld() {
        if (contains(old_worlds, this.getVariant())) {
            return true;
        }
        return false;
    }

    public void setThreatPose(boolean p_70918_1_) {
        this.entityData.set(THREAT, p_70918_1_);
    }

    public boolean isThreatPose() {
        return this.entityData.get(THREAT);
    }

    public class ThreatGoal extends Goal {
        private PlayerEntity angertarget;
        private final EntityPredicate predicate = (new EntityPredicate()).range(2.0D).allowInvulnerable().selector((p_220844_0_) -> {
            return !((PlayerEntity)p_220844_0_).isCrouching();
        });

        protected ThreatGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
            angertarget = null;
        }

        public boolean canUse() {
            if (!TarantulaEntity.this.isOldWorld() || TarantulaEntity.this.isBaby() ||TarantulaEntity.this.getTarget() != null) {
                return false;
            } else {
                List<PlayerEntity> list = TarantulaEntity.this.level.getNearbyEntities(PlayerEntity.class, this.predicate, TarantulaEntity.this, TarantulaEntity.this.getBoundingBox().inflate(2.0D, 2.0D, 2.0D));
                if (list.isEmpty()) {
                    return false;
                }
                angertarget = list.get(0);
                return true;

            }
        }

        public void start() {
            TarantulaEntity.this.getLookControl().setLookAt(this.angertarget, 10.0F, (float)TarantulaEntity.this.getMaxHeadXRot());
            TarantulaEntity.this.setDeltaMovement(0,0,0);
            TarantulaEntity.this.setDeltaMovement(TarantulaEntity.this.getDeltaMovement().scale(0));
            TarantulaEntity.this.setThreatPose(true);
        }

        public void stop() {
            TarantulaEntity.this.setThreatPose(false);
            angertarget = null;
        }

        public boolean canContinueToUse() {
            if (TarantulaEntity.this.getTarget() != null) {
                return false;
            }
            List<PlayerEntity> list = TarantulaEntity.this.level.getNearbyEntities(PlayerEntity.class, this.predicate, TarantulaEntity.this, TarantulaEntity.this.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));
            return !(list.isEmpty() || TarantulaEntity.this.isBaby());
        }

    }

    static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
        private final TarantulaEntity tarantula;

        public AvoidEntityGoal(TarantulaEntity p_i50037_1_, Class<T> p_i50037_2_, float p_i50037_3_, double p_i50037_4_, double p_i50037_6_) {
            super(p_i50037_1_, p_i50037_2_, p_i50037_3_, p_i50037_4_, p_i50037_6_, EntityPredicates.NO_CREATIVE_OR_SPECTATOR::test);
            this.tarantula = p_i50037_1_;
        }

        public boolean canUse() {
            return !this.tarantula.isThreatPose() && super.canUse();
        }

        public boolean canContinueToUse() {
            return !this.tarantula.isThreatPose() && super.canContinueToUse();
        }
    }


    public class WanderGoal extends WaterAvoidingRandomWalkingGoal {
        TarantulaEntity tarantula;
        public WanderGoal(CreatureEntity p_i47302_1_, double p_i47302_2_, float p_i47302_4_) {
            super(p_i47302_1_,p_i47302_2_,p_i47302_4_);
            tarantula = (TarantulaEntity) p_i47302_1_;
        }

        public boolean canUse() {
            return !tarantula.isThreatPose() && super.canUse();
        }

        public boolean canContinueToUse() {
            if (tarantula.isThreatPose()) {
                return false;
            }
            return super.canContinueToUse();
        }

    }


    public String getSpeciesName() {
<<<<<<< Updated upstream
        if (this.getVariant() == 1) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.celegans");
            return s1.getString();
        }
        else if (this.getVariant() == 2) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.pumpkinpatch");
            return s1.getString();
        }
        else if (this.getVariant() == 3) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.mexicanfireleg");
            return s1.getString();
        }
        else if (this.getVariant() == 4) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.entrerios");
            return s1.getString();
        }
        else if (this.getVariant() == 5) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.brazilianjewel");
            return s1.getString();
        }
        else if (this.getVariant() == 6) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.orangebaboon");
            return s1.getString();
        }
        else if (this.getVariant() == 7) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.chacogoldenknee");
            return s1.getString();
        }
        else if (this.getVariant() == 8) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.lavatarantula");
            return s1.getString();
        }
        else if (this.getVariant() == 9) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.gootysapphire");
            return s1.getString();
        }
        else if (this.getVariant() == 10) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.arizonablonde");
            return s1.getString();
        }
        else if (this.getVariant() == 11) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.goldenblueleg");
            return s1.getString();
        }
        else if (this.getVariant() == 12) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.purpletree");
            return s1.getString();
        }
        else if (this.getVariant() == 13) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.neonblueleg");
            return s1.getString();
        }
        else if (this.getVariant() == 14) {
            ITextComponent s1 = new TranslationTextComponent("message.creatures.juruensis");
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

    public ItemStack getFoodItem() {
        return new ItemStack(CreaturesItems.MEALWORMS, 1);
    }

    public class TarantulaBreedGoal extends BreedGoal {
        public TarantulaBreedGoal(double p_i50738_2_) {
            super(TarantulaEntity.this, p_i50738_2_);
        }

        public void stop() {
            int rand = this.animal.getRandom().nextInt(100);
            TarantulaEntity tarantula = (TarantulaEntity) this.animal;
            TarantulaEntity tarantula_boyfriend = (TarantulaEntity) this.partner;
            if (rand <= 50) {
                if (tarantula.getGender() == 0 && tarantula_boyfriend.getGender() == 1) {
                    tarantula.setTarget(this.partner);
                    //tarantula_boyfriend.goalSelector.addGoal(1, new AvoidEntityGoal<TarantulaEntity>(tarantula_boyfriend, TarantulaEntity.class, 6.0F, 1.0D, 1.2D));
                } if (tarantula_boyfriend.getGender() == 0 && tarantula.getGender() == 1) {
                    tarantula_boyfriend.setTarget(this.animal);
                    //tarantula.goalSelector.addGoal(1, new AvoidEntityGoal<TarantulaEntity>(tarantula, TarantulaEntity.class, 6.0F, 1.0D, 1.2D));

                }
            }

            super.stop();
        }

        public void tick() {
            super.tick();

        }

        protected void breed() {
            int rand = this.animal.getRandom().nextInt(10) + 1;
            for (int i = 0; i < rand; i++) {
                this.animal.spawnChildFromBreeding((ServerWorld)this.level, this.partner);
            }
        }

    }

    public String getGenderString() {
        if (this.getGender() == 1) {
            ITextComponent i = new TranslationTextComponent("gui.male");
            return i.getString();
        } else {
            ITextComponent i = new TranslationTextComponent("gui.female");
            return i.getString();
        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.TARANTULA;
    }


}
