package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.FollowFlockLeaderGoal;
import com.frikinzi.creatures.entity.base.TameableFlyingBirdEntity;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesSound;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.BiomeDictionary;
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

public class DoveEntity extends TameableFlyingBirdEntity implements IAnimatable, IAnimationTickable {
    private static final EntityDataAccessor<BlockPos> HOME_POS = SynchedEntityData.defineId(DoveEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> GOING_HOME = SynchedEntityData.defineId(DoveEntity.class, EntityDataSerializers.BOOLEAN);
    public static Map<Integer, TranslatableComponent> SPECIES_NAMES;

    static {
        Map<Integer, TranslatableComponent> map = new HashMap<>();
        map.put(1, new TranslatableComponent("message.creatures.dove.jambu"));
        map.put(2, new TranslatableComponent("message.creatures.dove.release"));
        map.put(3, new TranslatableComponent("message.creatures.dove.rose"));
        map.put(4, new TranslatableComponent("message.creatures.dove.rock"));
        map.put(5, new TranslatableComponent("message.creatures.dove.flame"));
        map.put(6, new TranslatableComponent("message.creatures.dove.goldenheart"));
        map.put(7, new TranslatableComponent("message.creatures.dove.mbleeding"));
        map.put(8, new TranslatableComponent("message.creatures.dove.orangebellied"));
        map.put(9, new TranslatableComponent("message.creatures.dove.victoria"));
        map.put(10, new TranslatableComponent("message.creatures.dove.mourning"));
        map.put(11, new TranslatableComponent("message.creatures.dove.europeanturtle"));
        map.put(12, new TranslatableComponent("message.creatures.dove.snow"));
        map.put(13, new TranslatableComponent("message.creatures.dove.nicobar"));
        map.put(14, new TranslatableComponent("message.creatures.dove.pacificemerald"));
        map.put(15, new TranslatableComponent("message.creatures.dove.crested"));
        map.put(16, new TranslatableComponent("message.creatures.dove.spinifex"));
        map.put(17, new TranslatableComponent("message.creatures.dove.pink"));
        SPECIES_NAMES = Collections.unmodifiableMap(map);
    }

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    final private int[] jungle_variants = new int[] {1,3,5,7,8,13,14};
    final private int[] swamp_variant = new int[] {9};
    final private int[] mountain_variant = new int[] {12};
    final private int[] forest_variant = new int[] {2,4,6,11,10,15,17};
    final private int[] mesa_variant = new int[] {16};

    public DoveEntity(EntityType<? extends DoveEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_29389_, DifficultyInstance p_29390_, MobSpawnType p_29391_, @Nullable SpawnGroupData p_29392_, @Nullable CompoundTag p_29393_) {
        this.setVariant(this.determineVariant(p_29389_));
        this.setGoingHome(false);
        this.setGender(this.random.nextInt(2));
        this.setHomePos(this.blockPosition());
        if (p_29392_ == null) {
            p_29392_ = new AgeableMob.AgeableMobGroupData(false);
        }

        float f = (float)(this.random.nextGaussian() * CreaturesConfig.height_standard_deviation.get() + CreaturesConfig.height_base_multiplier.get());
        this.setHeightMultiplier(f);

        return super.finalizeSpawn(p_29389_, p_29390_, p_29391_, p_29392_, p_29393_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));
        //this.targetSelector.addGoal(0, new GoHomeGoal(this,1.3D));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if ((this.isFlying()) || !this.isOnGround()) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (this.isSleeping()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.isInSittingPose()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.isGrooming()) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("eat", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

        if (!event.isMoving())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<DoveEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED, (double)0.4F).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public static boolean checkBirdSpawnRules(EntityType<DoveEntity> p_29424_, LevelAccessor p_29425_, MobSpawnType p_29426_, BlockPos p_29427_, Random p_29428_) {
        return p_29425_.getBlockState(p_29427_.below()).is(BlockTags.PARROTS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_29425_, p_29427_);
    }

    public int noVariants() {
        return 17;
    }

    public DoveEntity getBreedOffspring(ServerLevel p_149088_, AgeableMob p_149089_) {
        DoveEntity lovebirdEntity = CreaturesEntities.DOVE.get().create(p_149088_);
        lovebirdEntity.setVariant(this.getVariant());
        lovebirdEntity.setGender(this.random.nextInt(2));
        lovebirdEntity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return lovebirdEntity;
    }

    public int determineVariant(LevelAccessor p_29676_) {
        if (CreaturesConfig.biome_only_variants.get()) {
            Biome biome = p_29676_.getBiome(this.blockPosition()).value();
            ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName());
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);
            if (types.contains(BiomeDictionary.Type.MESA)) {
                int i = this.random.nextInt(mesa_variant.length);
                return mesa_variant[i];
            }
            if (types.contains(BiomeDictionary.Type.JUNGLE)) {
                int i = this.random.nextInt(jungle_variants.length);
                return jungle_variants[i];
            } if (types.contains(BiomeDictionary.Type.FOREST) || types.contains(BiomeDictionary.Type.PLAINS)) {
                int i = this.random.nextInt(forest_variant.length);
                return forest_variant[i];
            } if (types.contains(BiomeDictionary.Type.MOUNTAIN)) {
                int i = this.random.nextInt(mountain_variant.length);
                return mountain_variant[i];
            } if (types.contains(BiomeDictionary.Type.SWAMP)) {
                int i = this.random.nextInt(swamp_variant.length);
                return swamp_variant[i];
            }
        }
        return this.random.nextInt(this.noVariants()) + 1;

    }

    public boolean canMate(Animal p_30392_) {
        if (p_30392_ == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(p_30392_ instanceof DoveEntity)) {
            return false;
        } else {
            DoveEntity dove = (DoveEntity)p_30392_;
            if (!dove.isTame()) {
                return false;
            } else if (dove.isInSittingPose()) {
                return false;
            } else {
                return this.isInLove() && dove.isInLove();
            }
        }
    }

    public String getSpeciesName() {
        TranslatableComponent translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    protected float getStandingEyeHeight(Pose p_33540_, EntityDimensions p_33541_) {
        return 0.3F;
    }

    protected SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
            if (this.getVariant() == 10) {
                return CreaturesSound.MOURNING_DOVE;
            } if (this.getVariant() == 15 && this.isFlying()) {
                return CreaturesSound.CRESTED_PIGEON;
            }
            return CreaturesSound.DOVE_AMBIENT;
        } else {
            return null;
        }
    }

    public void setHomePos(BlockPos p_30220_) {
        this.entityData.set(HOME_POS, p_30220_);
    }

    BlockPos getHomePos() {
        return this.entityData.get(HOME_POS);
    }

    public void readAdditionalSaveData(CompoundTag p_30162_) {
        int i = p_30162_.getInt("HomePosX");
        int j = p_30162_.getInt("HomePosY");
        int k = p_30162_.getInt("HomePosZ");
        this.setHomePos(new BlockPos(i, j, k));
        this.setGoingHome(p_30162_.getBoolean("GoingHome"));
        super.readAdditionalSaveData(p_30162_);
    }

    public void addAdditionalSaveData(CompoundTag p_30176_) {
        super.addAdditionalSaveData(p_30176_);
        p_30176_.putInt("HomePosX", this.getHomePos().getX());
        p_30176_.putInt("HomePosY", this.getHomePos().getY());
        p_30176_.putInt("HomePosZ", this.getHomePos().getZ());
        p_30176_.putBoolean("GoingHome", this.isGoingHome());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOME_POS, BlockPos.ZERO);
        this.entityData.define(GOING_HOME, false);
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
 //       if (this.isTame()) {
 //           if (itemstack.getItem() == Items.COMPASS && this.isTame() && this.getOwner() == p_230254_1_) {
 //               this.setHomePos(this.blockPosition());
 //               if (this.level.isClientSide) {
 //                   Component i = new TranslatableComponent("message.homeposset");
 //                   this.getOwner().sendMessage(i, Util.NIL_UUID);
 //               }
 //               return InteractionResult.SUCCESS;
 //           }
 //           if (itemstack.getItem() == CreaturesItems.RAPTOR_HORN.get() && this.isTame() && this.getOwner() == p_230254_1_) {
 //               boolean b = !this.isGoingHome();
 //               this.setGoingHome(b);
 //               System.out.println(this.isGoingHome());
 //               if (this.level.isClientSide) {
 //                   Component i;
 //                   if (this.isGoingHome()) {
 //                       i = new TranslatableComponent("message.goinghome");
 //                   } else {
 //                       i = new TranslatableComponent("message.notgoinghome");
 //                   }
//
 //                   this.getOwner().sendMessage(i, Util.NIL_UUID);
 //               }
 //               return InteractionResult.SUCCESS;
 //           }
 //       }
        return super.mobInteract(p_230254_1_, p_230254_2_);

    }

    boolean isGoingHome() {
        return this.entityData.get(GOING_HOME);
    }

    void setGoingHome(boolean p_30239_) {
        this.entityData.set(GOING_HOME, p_30239_);
    }

    public double getHatchChance() {
        return CreaturesConfig.dove_hatch_chance.get();
    }

    static class GoHomeGoal extends Goal {
        private final DoveEntity pigeon;
        private final double speedModifier;
        private boolean stuck;
        private int closeToHomeTryTicks;
        private static final int GIVE_UP_TICKS = 600;

        GoHomeGoal(DoveEntity p_30253_, double p_30254_) {
            this.pigeon = p_30253_;
            this.speedModifier = p_30254_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            if (!this.pigeon.isTame()) {
                return false;
            }
            if (this.pigeon.isGoingHome()) {
                return true;
            } else if (this.pigeon.getRandom().nextInt(reducedTickDelay(700)) != 0) {
                return false;
            } else {
                return !this.pigeon.getHomePos().closerToCenterThan(this.pigeon.position(), 5.0D);
            }
        }

        public void start() {
            this.stuck = false;
            this.closeToHomeTryTicks = 0;
            this.pigeon.getNavigation().moveTo(this.pigeon.getX(), this.pigeon.getY(), this.pigeon.getZ(), this.speedModifier);
            System.out.println("Starting to fly back to " + this.pigeon.getHomePos());
        }

        public void stop() {
            //this.pigeon.setGoingHome(false);
            if (this.pigeon.level.isClientSide) {
                Component i = new TranslatableComponent("message.gothome");
                this.pigeon.getOwner().sendMessage(i, Util.NIL_UUID);
            }
        }

        public boolean canContinueToUse() {
            if (!this.pigeon.isGoingHome()) {
                return false;
            }
            return !this.pigeon.getHomePos().closerToCenterThan(this.pigeon.position(), 7.0D) && !this.stuck && this.closeToHomeTryTicks <= this.adjustedTickDelay(600);
        }

        public void tick() {
            BlockPos blockpos = this.pigeon.getHomePos();
            boolean flag = blockpos.closerToCenterThan(this.pigeon.position(), 16.0D);
            if (flag) {
                ++this.closeToHomeTryTicks;
            }

            if (this.pigeon.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(blockpos);
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.pigeon, 16, 3, vec3, (double)((float)Math.PI / 10F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.pigeon, 8, 7, vec3, (double)((float)Math.PI / 2F));
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }

                this.pigeon.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }

        }
    }

    public void aiStep() {
        if (!this.level.isClientSide) {
            int i = this.random.nextInt(3000);
            if (i == 0 && !this.isInWater() && this.isNotMoving() && this.canMove() && !this.isSleeping()) {
                if (this.isTame() && this.isWandering() == 1) {
                    this.getNavigation().stop();
                    this.setGrooming(true);
                } else if (!this.isTame()) {
                    this.getNavigation().stop();
                    this.setGrooming(true);
                }

            }
            if ((i == 1 || this.isInWater()) && this.isGrooming()) {
                this.setGrooming(false);
            }
        }
        super.aiStep();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.dove_clutch_size.get());
    }

}
