package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.entity.base.AbstractWalkingCreature;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.core.jmx.Server;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.*;

public class TarantulaEntity extends AbstractWalkingCreature implements IAnimatable, IAnimationTickable {
    final private int[] old_worlds = new int[] {6,9,11,13};
    final private int[] jungle_variants = new int[] {1,2,4,5,8,9,12,13,14};
    final private int[] desert_variants = new int[] {3,6,7,10,11};
    private static final EntityDataAccessor<Boolean> THREAT = SynchedEntityData.defineId(TarantulaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(TarantulaEntity.class, EntityDataSerializers.BYTE);
    public static Map<Integer, TranslatableComponent> SPECIES_NAMES;

    static {
        Map<Integer, TranslatableComponent> map = new HashMap<>();
        map.put(1, new TranslatableComponent("message.creatures.celegans"));
        map.put(2, new TranslatableComponent("message.creatures.pumpkinpatch"));
        map.put(3, new TranslatableComponent("message.creatures.mexicanfireleg"));
        map.put(4, new TranslatableComponent("message.creatures.entrerios"));
        map.put(5, new TranslatableComponent("message.creatures.brazilianjewel"));
        map.put(6, new TranslatableComponent("message.creatures.orangebaboon"));
        map.put(7, new TranslatableComponent("message.creatures.chacogoldenknee"));
        map.put(8, new TranslatableComponent("message.creatures.lavatarantula"));
        map.put(9, new TranslatableComponent("message.creatures.gootysapphire"));
        map.put(10, new TranslatableComponent("message.creatures.arizonablonde"));
        map.put(11, new TranslatableComponent("message.creatures.goldenblueleg"));
        map.put(12, new TranslatableComponent("message.creatures.purpletree"));
        map.put(13, new TranslatableComponent("message.creatures.neonblueleg"));
        map.put(14, new TranslatableComponent("message.creatures.juruensis"));
        SPECIES_NAMES = Collections.unmodifiableMap(map);
    }

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public TarantulaEntity(EntityType<? extends TarantulaEntity> p_29790_, Level p_29791_) {
        super(p_29790_, p_29791_);
        this.moveControl = new TarantulaEntity.TarantulaMoveControl();
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_30023_, DifficultyInstance p_30024_, MobSpawnType p_30025_, @Nullable SpawnGroupData p_30026_, @Nullable CompoundTag p_30027_) {
        return super.finalizeSpawn(p_30023_, p_30024_, p_30025_, p_30026_, p_30027_);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new TarantulaEntity.ThreatGoal());
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new TarantulaEntity.WanderGoal(this, 0.7D,0.0005F));
        this.goalSelector.addGoal(2, new TarantulaEntity.TarantulaBreedGoal(1.0D));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Player.class, 10.0F, 1.5D, 1.5D));
    }

    protected PathNavigation createNavigation(Level p_33802_) {
        return new WallClimberNavigation(this, p_33802_);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.isThreatPose()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("threat", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() && this.isClimbing()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("climb", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }


    public TarantulaEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        TarantulaEntity tarantula = CreaturesEntities.TARANTULA.get().create(p_149088_);
        TarantulaEntity partner = (TarantulaEntity) p_149089_;
        int[] vars = {partner.getVariant(), this.getVariant()};
        tarantula.setVariant(vars[this.random.nextInt(2)]);
        tarantula.setGender(this.random.nextInt(2));
        tarantula.setHeightMultiplier(getSpawnEggOffspringHeight());
        return tarantula;
    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!(p_30392_ instanceof TarantulaEntity)) {
            return false;
        } else {
            TarantulaEntity tarantula = (TarantulaEntity)p_30392_;
            if (tarantula.getGender() == this.getGender()) {
                return false;
            }
            return this.isInLove() && tarantula.isInLove();
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<TarantulaEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    public int noVariants() {
        return 14;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public static boolean checkCrabSpawnRules(EntityType<TarantulaEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return (p_29425_.getBlockState(p_29427_.below()).is(BlockTags.SAND) || p_29425_.getBlockState(p_29427_.below()).is(BlockTags.DIRT)) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.TARANTULA;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(THREAT, false);
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntityDimensions p_213348_2_) {
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

    public void makeStuckInBlock(BlockState p_213295_1_, Vec3 p_213295_2_) {
        if (!p_213295_1_.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(p_213295_1_, p_213295_2_);
        }

    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public boolean canBeAffected(MobEffectInstance p_33809_) {
        if (p_33809_.getEffect() == MobEffects.POISON) {
            net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(this, p_33809_);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
            return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
        }
        return super.canBeAffected(p_33809_);
    }


    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean p_33820_) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (p_33820_) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
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
                    ((LivingEntity)p_70652_1_).addEffect(new MobEffectInstance(MobEffects.POISON, i * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
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

    public ItemStack getFoodItem() {
        return new ItemStack(CreaturesItems.MEALWORMS.get(), 1);
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(CreaturesItems.MEALWORMS.get());
    }

    public boolean isThreatPose() {
        return this.entityData.get(THREAT);
    }

    public void setThreatPose(boolean p_70918_1_) {
        this.entityData.set(THREAT, p_70918_1_);
    }

    public class ThreatGoal extends Goal {
        private Player angertarget;
        private final TargetingConditions predicate = TargetingConditions.forNonCombat().range(2.0D).selector((p_220844_0_) -> {
            return !((Player)p_220844_0_).isCrouching();
        });

        protected ThreatGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
            angertarget = null;
        }

        public boolean canUse() {
            if (!TarantulaEntity.this.isOldWorld() || TarantulaEntity.this.isBaby() ||TarantulaEntity.this.getTarget() != null) {
                return false;
            } else {
                List<Player> list = TarantulaEntity.this.level.getNearbyEntities(Player.class, this.predicate, TarantulaEntity.this, TarantulaEntity.this.getBoundingBox().inflate(2.0D, 2.0D, 2.0D));
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
            List<Player> list = TarantulaEntity.this.level.getNearbyEntities(Player.class, this.predicate, TarantulaEntity.this, TarantulaEntity.this.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));
            return !(list.isEmpty() || TarantulaEntity.this.isBaby());
        }

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
                } if (tarantula_boyfriend.getGender() == 0 && tarantula.getGender() == 1) {
                    tarantula_boyfriend.setTarget(this.animal);

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
                this.animal.spawnChildFromBreeding((ServerLevel) this.level, this.partner);
            }
        }

    }

    public class WanderGoal extends WaterAvoidingRandomStrollGoal {
        TarantulaEntity tarantula;
        public WanderGoal(Animal p_i47302_1_, double p_i47302_2_, float p_i47302_4_) {
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
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public boolean canMove() {
        return !this.isThreatPose();
    }

    class TarantulaMoveControl extends MoveControl {
        public TarantulaMoveControl() {
            super(TarantulaEntity.this);
        }

        public void tick() {
            if (TarantulaEntity.this.canMove()) {
                super.tick();
            }

        }
    }

}
