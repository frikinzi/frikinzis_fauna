package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.entity.base.AbstractCrabBase;
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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Map;

public class GhostCrabEntity extends AbstractCrabBase implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final Ingredient FOOD_ITEMS = Ingredient.of(
            net.minecraft.world.item.Items.DEAD_BUSH,
            net.minecraft.world.item.Items.DEAD_BRAIN_CORAL,
            net.minecraft.world.item.Items.DEAD_BRAIN_CORAL_FAN,
            net.minecraft.world.item.Items.DEAD_BUBBLE_CORAL,
            net.minecraft.world.item.Items.DEAD_FIRE_CORAL);

    public static final Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.redghost"),
            2, Component.translatable("message.creatures.atlanticghost"),
            3, Component.translatable("message.creatures.greenhorned"),
            4, Component.translatable("message.creatures.purplehorned"));

    public GhostCrabEntity(EntityType<? extends GhostCrabEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnData,
                                        @Nullable net.minecraft.nbt.CompoundTag tag) {
        this.setVariant(this.random.nextInt(determineVariant()) + 1);
        this.setGender(this.random.nextInt(2));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, tag);
    }

    protected <E extends GhostCrabEntity> PlayState animController(final AnimationState<E> event) {
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
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob other) {
        GhostCrabEntity entity = (GhostCrabEntity) getType().create(level);
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
        return Mth.clamp(super.getVariant(), 1, 5);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.CRAB;
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> type,
            ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return level.getBlockState(pos.below()).is(Blocks.SAND)
                && level.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public String getSpeciesName() {
        Component c = SPECIES_NAMES.get(this.getVariant());
        return c != null ? c.getString() : "Unknown";
    }

    @Override
    public String getScientificName() {
        return switch (this.getVariant()) {
            case 1 -> "Ocypode macrocera";
            case 2 -> "Ocypode quadrata";
            case 3, 4 -> "Ocypode ceratophthalmus";
            default -> "Unknown";
        };
    }

    @Override
    public int determineVariant() {
        return 4;
    }

    @Override
    public int getIUCNStatus() {
        return -1;
    }
}
