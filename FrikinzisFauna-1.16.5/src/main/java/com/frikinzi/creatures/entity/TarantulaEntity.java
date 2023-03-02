package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class TarantulaEntity extends AbstractCrabBase implements IAnimatable {
    private int[] old_worlds = new int[] {6,9,11,13};
    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(TarantulaEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(TarantulaEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.defineId(TarantulaEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> THREAT = EntityDataManager.defineId(TarantulaEntity.class, DataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.DEAD_BUSH, Items.DEAD_BRAIN_CORAL, Items.DEAD_BRAIN_CORAL_FAN, Items.DEAD_BUBBLE_CORAL, Items.DEAD_FIRE_CORAL);
    private AnimationFactory factory = new AnimationFactory(this);

    public TarantulaEntity(EntityType<? extends TarantulaEntity> p_i48550_1_, World p_i48550_2_) {
        super(p_i48550_1_, p_i48550_2_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.7D,0.0005F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new TarantulaEntity.ThreatGoal());

    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        this.setVariant(this.random.nextInt(14));
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
            TarantulaEntity.this.setDeltaMovement(0,0,0);
            TarantulaEntity.this.getLookControl().setLookAt(this.angertarget, 10.0F, (float)TarantulaEntity.this.getMaxHeadXRot());
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

    static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public TargetGoal(SpiderEntity p_i45818_1_, Class<T> p_i45818_2_) {
            super(p_i45818_1_, p_i45818_2_, true);
        }

        public boolean canUse() {
            float f = this.mob.getBrightness();
            return f >= 0.5F ? false : super.canUse();
        }
    }

    public class WanderGoal extends WaterAvoidingRandomWalkingGoal {
        TarantulaEntity tarantula;
        public WanderGoal(CreatureEntity p_i47302_1_, double p_i47302_2_, float p_i47302_4_) {
            super(p_i47302_1_,p_i47302_2_,p_i47302_4_);
            tarantula = (TarantulaEntity) p_i47302_1_;
        }

        public boolean canContinueToUse() {
            if (tarantula.isThreatPose()) {
                return false;
            }
            return super.canContinueToUse();
        }

    }


    public String getSpeciesName() {
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
        } else {
            return "Unknown";
        }
    }



}
