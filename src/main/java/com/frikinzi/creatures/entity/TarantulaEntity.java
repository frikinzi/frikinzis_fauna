package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.*;

public class TarantulaEntity extends AbstractCrabBase implements GeoEntity {
    private final int[] old_worlds = new int[]{6, 9, 11, 13};
    private final int[] jungle_variants = new int[]{1, 2, 4, 5, 8, 9, 12, 13, 14};
    private final int[] desert_variants = new int[]{3, 6, 7, 10, 11, 15};
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(TarantulaEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> THREAT = SynchedEntityData.defineId(TarantulaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.MEALWORMS.get());
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final Map<Integer, Component> SPECIES_NAMES;
    static {
        Map<Integer, Component> map = new HashMap<>();
        map.put(1, Component.translatable("message.creatures.celegans"));
        map.put(2, Component.translatable("message.creatures.pumpkinpatch"));
        map.put(3, Component.translatable("message.creatures.mexicanfireleg"));
        map.put(4, Component.translatable("message.creatures.entrerios"));
        map.put(5, Component.translatable("message.creatures.brazilianjewel"));
        map.put(6, Component.translatable("message.creatures.orangebaboon"));
        map.put(7, Component.translatable("message.creatures.chacogoldenknee"));
        map.put(8, Component.translatable("message.creatures.lavatarantula"));
        map.put(9, Component.translatable("message.creatures.gootysapphire"));
        map.put(10, Component.translatable("message.creatures.arizonablonde"));
        map.put(11, Component.translatable("message.creatures.goldenblueleg"));
        map.put(12, Component.translatable("message.creatures.purpletree"));
        map.put(13, Component.translatable("message.creatures.neonblueleg"));
        map.put(14, Component.translatable("message.creatures.juruensis"));
        map.put(15, Component.translatable("message.creatures.emilia"));
        SPECIES_NAMES = Collections.unmodifiableMap(map);
    }

    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Cyriocosmus elegans").put(2, "Hapalopus formosus")
            .put(3, "Brachypelma boehmei").put(4, "Grammostola iheringi")
            .put(5, "Typhochlaena seladonia").put(6, "Pterinochilus murinus")
            .put(7, "Grammostola pulchripes").put(8, "Davus sp.")
            .put(9, "Poecilotheria metallica").put(10, "Aphonopelma chalcodes")
            .put(11, "Harpactira pulchripes").put(12, "Avicularia purpurea")
            .put(13, "Birupes simoroxigorum").put(14, "Avicularia juruensis")
            .put(15, "Brachypelma emilia").build();

    public TarantulaEntity(EntityType<? extends TarantulaEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new TarantulaBreedGoal(1.0D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new WanderGoal(this, 0.7D, 0.0005F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new ThreatGoal());
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 1.2D,
                EntitySelector.NO_CREATIVE_OR_SPECTATOR::test));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnData,
                                        @Nullable CompoundTag tag) {
        this.setVariant(this.methodofDeterminingVariant(level));
        this.setGender(this.random.nextInt(2));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, tag);
    }

    protected <E extends TarantulaEntity> PlayState animController(final AnimationState<E> event) {
        if (this.isThreatPose()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("threat"));
        }
        if (event.isMoving() && this.isClimbing()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("climb"));
        }
        if (event.isMoving()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::animController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
        this.entityData.define(THREAT, false);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 0.13F;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            this.setClimbing(this.horizontalCollision);
        }
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 factor) {
        if (!state.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(state, factor);
        }
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        if (effect.getEffect() == MobEffects.POISON) {
            return false;
        }
        return super.canBeAffected(effect);
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean climbing) {
        byte b = this.entityData.get(DATA_FLAGS_ID);
        b = climbing ? (byte)(b | 1) : (byte)(b & -2);
        this.entityData.set(DATA_FLAGS_ID, b);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob other) {
        TarantulaEntity entity = (TarantulaEntity) getType().create(level);
        entity.setVariant(this.getVariant());
        entity.setGender(this.random.nextInt(2));
        return entity;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (super.doHurtTarget(target)) {
            if (target instanceof LivingEntity living) {
                int i = 0;
                if (this.level().getDifficulty() == Difficulty.NORMAL) i = 7;
                else if (this.level().getDifficulty() == Difficulty.HARD) i = 15;
                if (this.isOldWorld()) i *= 2;
                if (i > 0) living.addEffect(new MobEffectInstance(MobEffects.POISON, i * 20, 0));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canMate(Animal other) {
        if (other == this) return false;
        if (other.getClass() != this.getClass()) return false;
        TarantulaEntity partner = (TarantulaEntity) other;
        if (this.getGender() == partner.getGender()) return false;
        return this.isInLove() && other.isInLove();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.getTarget() != null && this.getTarget().isDeadOrDying()) {
            this.setTarget(null);
        }
        if (this.isThreatPose()) {
            this.getNavigation().stop();
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    @Override
    public int getVariant() {
        return Mth.clamp(super.getVariant(), 1, 15);
    }

    public int methodofDeterminingVariant(ServerLevelAccessor level) {
        if (CreaturesConfig.biome_only_variants.get()) {
            var holder = level.getBiome(this.blockPosition());
            if (holder.is(BiomeTags.IS_JUNGLE)) {
                return jungle_variants[this.random.nextInt(jungle_variants.length)];
            }
            if (holder.is(BiomeTags.IS_SAVANNA) || holder.is(BiomeTags.IS_BADLANDS)) {
                return desert_variants[this.random.nextInt(desert_variants.length)];
            }
        }
        return this.random.nextInt(determineVariant()) + 1;
    }

    @Override
    public int determineVariant() {
        return 15;
    }

    public boolean contains(int[] arr, int type) {
        for (int t : arr) if (t == type) return true;
        return false;
    }

    public boolean isOldWorld() {
        return contains(old_worlds, this.getVariant());
    }

    public void setThreatPose(boolean value) {
        this.entityData.set(THREAT, value);
    }

    public boolean isThreatPose() {
        return this.entityData.get(THREAT);
    }

    @Override
    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.TARANTULA;
    }

    @Override
    public int getIUCNStatus() {
        if (this.getVariant() == 3) return 3;
        if (this.getVariant() == 9) return 4;
        return -1;
    }

    public static boolean checkTarantulaSpawnRules(EntityType<? extends Animal> type,
            ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return (level.getBlockState(pos.below()).is(Blocks.SAND)
                || level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK))
                && level.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public String getSpeciesName() {
        Component c = SPECIES_NAMES.get(this.getVariant());
        return c != null ? c.getString() : "Unknown";
    }

    @Override
    public ItemStack getFoodItem() {
        return new ItemStack(CreaturesItems.MEALWORMS.get(), 1);
    }

    @Override
    public String getScientificName() {
        return SCIENTIFIC_NAMES.getOrDefault(this.getVariant(), "Unknown");
    }

    @Override
    public Component getFunFact() {
        return Component.translatable("description.creatures.tarantula");
    }

    public class ThreatGoal extends Goal {
        private Player angertarget;
        private final TargetingConditions predicate = TargetingConditions.forNonCombat()
                .range(2.0D).ignoreLineOfSight()
                .selector(e -> !((Player) e).isCrouching());

        protected ThreatGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public boolean canUse() {
            if (!TarantulaEntity.this.isOldWorld() || TarantulaEntity.this.isBaby()
                    || TarantulaEntity.this.getTarget() != null) return false;
            List<Player> list = TarantulaEntity.this.level().getNearbyEntities(Player.class,
                    predicate, TarantulaEntity.this,
                    TarantulaEntity.this.getBoundingBox().inflate(2.0D));
            if (list.isEmpty()) return false;
            angertarget = list.get(0);
            return true;
        }

        public void start() {
            TarantulaEntity.this.getLookControl().setLookAt(angertarget, 10.0F, TarantulaEntity.this.getMaxHeadXRot());
            TarantulaEntity.this.setDeltaMovement(Vec3.ZERO);
            TarantulaEntity.this.setThreatPose(true);
        }

        public void stop() {
            TarantulaEntity.this.setThreatPose(false);
            angertarget = null;
        }

        public boolean canContinueToUse() {
            if (TarantulaEntity.this.getTarget() != null) return false;
            List<Player> list = TarantulaEntity.this.level().getNearbyEntities(Player.class,
                    predicate, TarantulaEntity.this,
                    TarantulaEntity.this.getBoundingBox().inflate(4.0D));
            return !list.isEmpty() && !TarantulaEntity.this.isBaby();
        }
    }

    public class WanderGoal extends WaterAvoidingRandomStrollGoal {
        TarantulaEntity tarantula;
        public WanderGoal(PathfinderMob mob, double speed, float probability) {
            super(mob, speed, probability);
            tarantula = (TarantulaEntity) mob;
        }
        public boolean canUse() { return !tarantula.isThreatPose() && super.canUse(); }
        public boolean canContinueToUse() { return !tarantula.isThreatPose() && super.canContinueToUse(); }
    }

    public class TarantulaBreedGoal extends BreedGoal {
        public TarantulaBreedGoal(double speed) {
            super(TarantulaEntity.this, speed);
        }

        public void stop() {
            int rand = this.animal.getRandom().nextInt(100);
            TarantulaEntity t1 = (TarantulaEntity) this.animal;
            TarantulaEntity t2 = (TarantulaEntity) this.partner;
            if (rand <= 50) {
                if (t1.getGender() == 0 && t2.getGender() == 1) t1.setTarget(this.partner);
                if (t2.getGender() == 0 && t1.getGender() == 1) t2.setTarget(this.animal);
            }
            super.stop();
        }

        @Override
        protected void breed() {
            int rand = this.animal.getRandom().nextInt(10) + 1;
            for (int i = 0; i < rand; i++) {
                this.animal.spawnChildFromBreeding((ServerLevel) this.level, this.partner);
            }
        }
    }
}
