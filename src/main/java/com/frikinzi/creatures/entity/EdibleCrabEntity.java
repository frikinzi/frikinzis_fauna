package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import com.frikinzi.creatures.registry.CreaturesItems;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import org.joml.Quaternionf;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.*;

public class EdibleCrabEntity extends AbstractCrabBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(CreaturesItems.RAW_SHRIMP.get());
    private static final EntityDataAccessor<Boolean> THREAT = SynchedEntityData.defineId(EdibleCrabEntity.class, EntityDataSerializers.BOOLEAN);
    private boolean didAttack;

    public static final Map<Integer, Component> SPECIES_NAMES;
    static {
        Map<Integer, Component> map = new HashMap<>();
        map.put(1, Component.translatable("message.creatures.ediblecrab"));
        map.put(2, Component.translatable("message.creatures.rockcrab"));
        map.put(3, Component.translatable("message.creatures.dungenesscrab"));
        map.put(4, Component.translatable("message.creatures.bluecrab"));
        SPECIES_NAMES = Collections.unmodifiableMap(map);
    }

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE))
            .put(2, List.of(Region.NORTH_AMERICA))
            .put(3, List.of(Region.NORTH_AMERICA))
            .put(4, List.of(Region.NORTH_AMERICA, Region.SOUTH_AMERICA))
            .build();

    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.of(
            1, "Cancer pagurus",
            2, "Cancer irroratus",
            3, "Metacarcinus magister",
            4, "Callinectes sapidus");

    public static final Map<Integer, Component> DESCRIPTIONS = ImmutableMap.of(
            1, Component.translatable("description.creatures.ediblecrab"),
            2, Component.translatable("description.creatures.rockcrab"),
            3, Component.translatable("description.creatures.dungenesscrab"),
            4, Component.translatable("description.creatures.bluecrab"));

    public EdibleCrabEntity(EntityType<? extends EdibleCrabEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2.0D, true));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.7D, 0.0005F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new ThreatGoal());
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnData,
                                        @Nullable CompoundTag tag) {
        this.setGender(this.random.nextInt(2));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, tag);
    }

    protected <E extends EdibleCrabEntity> PlayState animController(final AnimationState<E> event) {
        if (this.isThreatPose()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("defense"));
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

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public ItemStack getFoodItem() {
        return new ItemStack(CreaturesItems.RAW_SHRIMP.get(), 1);
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob other) {
        EdibleCrabEntity entity = (EdibleCrabEntity) getType().create(level);
        entity.setVariant(this.getVariant());
        return entity;
    }

    @Override
    public boolean canMate(Animal other) {
        if (other == this) return false;
        if (other.getClass() != this.getClass()) return false;
        return this.isInLove() && other.isInLove();
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    @Override
    public int getVariant() {
        return Mth.clamp(super.getVariant(), 1, 4);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(THREAT, false);
    }

    public void setThreatPose(boolean value) {
        this.entityData.set(THREAT, value);
    }

    public boolean isThreatPose() {
        return this.entityData.get(THREAT);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Threat", this.isThreatPose());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setThreatPose(tag.getBoolean("Threat"));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public ResourceLocation getDefaultLootTable() {
        return switch (this.getVariant()) {
            case 2 -> CreaturesLootTables.ROCKCRAB;
            case 3 -> CreaturesLootTables.DUNGENESSCRAB;
            case 4 -> CreaturesLootTables.BLUE_CRAB;
            default -> CreaturesLootTables.EDIBLE_CRAB;
        };
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> type,
            ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return level.getBlockState(pos.below()).is(Blocks.SAND)
                && level.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public void aiStep() {
        if (this.isThreatPose()) {
            this.getNavigation().stop();
        }
        super.aiStep();
    }

    @Override
    public String getSpeciesName() {
        Component c = SPECIES_NAMES.get(this.getVariant());
        return c != null ? c.getString() : "Unknown";
    }

    @Override
    public String getScientificName() {
        return SCIENTIFIC_NAMES.getOrDefault(this.getVariant(), "Unknown");
    }

    private void setDidAttack(boolean value) {
        this.didAttack = value;
    }

    @Override
    public Component getFunFact() {
        Component c = DESCRIPTIONS.get(this.getVariant());
        return c != null ? c : Component.translatable("creatures.unknown");
    }

    @Override
    public int determineVariant() {
        return 4;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public int getIUCNStatus() {
        return -1;
    }

    public class ThreatGoal extends Goal {
        private Player angertarget;
        private final TargetingConditions predicate = TargetingConditions.forNonCombat()
                .range(2.0D)
                .ignoreLineOfSight()
                .selector(e -> !((Player) e).isCrouching());

        protected ThreatGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        public boolean canUse() {
            if (EdibleCrabEntity.this.isBaby() || EdibleCrabEntity.this.getTarget() != null) return false;
            List<Player> list = EdibleCrabEntity.this.level().getNearbyEntities(Player.class,
                    predicate, EdibleCrabEntity.this,
                    EdibleCrabEntity.this.getBoundingBox().inflate(2.0D));
            if (list.isEmpty()) return false;
            angertarget = list.get(0);
            return true;
        }

        public void start() {
            EdibleCrabEntity.this.getLookControl().setLookAt(angertarget, 10.0F, EdibleCrabEntity.this.getMaxHeadXRot());
            EdibleCrabEntity.this.setDeltaMovement(0, 0, 0);
            EdibleCrabEntity.this.setThreatPose(true);
        }

        public void stop() {
            EdibleCrabEntity.this.setThreatPose(false);
            angertarget = null;
        }

        public boolean canContinueToUse() {
            if (EdibleCrabEntity.this.getTarget() != null) return false;
            List<Player> list = EdibleCrabEntity.this.level().getNearbyEntities(Player.class,
                    predicate, EdibleCrabEntity.this,
                    EdibleCrabEntity.this.getBoundingBox().inflate(4.0D));
            return !list.isEmpty() && !EdibleCrabEntity.this.isBaby();
        }
    }

    static class HurtByTargetGoal extends net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal {
        public HurtByTargetGoal(EdibleCrabEntity crab) {
            super(crab);
        }

        public boolean canContinueToUse() {
            if (this.mob instanceof EdibleCrabEntity crab) {
                if (crab.didAttack) {
                    crab.setDidAttack(false);
                    return false;
                }
            }
            return super.canContinueToUse();
        }
    }

    @Override
    public Quaternionf getRotforGUI() {
        return new Quaternionf()
                .rotateZ((float) Math.PI)
                .rotateY((float) Math.toRadians(160))
                .rotateX((float) Math.toRadians(45));
    }
}
