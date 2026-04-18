package com.frikinzi.creatures.entity;

import com.frikinzi.creatures.CreaturesConfig;
import com.frikinzi.creatures.client.gui.Region;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.CreaturesFlyingBird;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.CreaturesLootTables;
import com.frikinzi.creatures.registry.CreaturesSound;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RavenEntity extends CreaturesFlyingBird implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private PanicGoal PanicGoal;
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.ROTTEN_FLESH, Items.EGG, Items.CHICKEN, Items.WHEAT_SEEDS, Items.SWEET_BERRIES);
    public static Map<Integer, Component> SPECIES_NAMES = ImmutableMap.of(
            1, Component.translatable("message.creatures.commonraven"),
            2, Component.translatable("message.creatures.brownheadedraven"),
            3, Component.translatable("message.creatures.whitethroatedraven"),
            4, Component.translatable("message.creatures.thickbilledraven"),
            5, Component.translatable("message.creatures.commonravenalbino")
    );

    private int giftCooldown = 0;
    private static final int GIFT_COOLDOWN_MIN = 6000;
    private static final int GIFT_COOLDOWN_MAX = 12000;

    public static final Map<Integer, List<Region>> REGIONS = ImmutableMap.<Integer, List<Region>>builder()
            .put(1, List.of(Region.EUROPE, Region.ASIA, Region.NORTH_AMERICA, Region.AFRICA))
            .put(2, List.of(Region.AFRICA, Region.ASIA))
            .put(3, List.of(Region.AFRICA))
            .put(4, List.of(Region.AFRICA))
            .put(5, List.of(Region.EUROPE, Region.ASIA, Region.NORTH_AMERICA, Region.AFRICA))
            .build();
    public static final Map<Integer, String> SCIENTIFIC_NAMES = ImmutableMap.<Integer, String>builder()
            .put(1, "Corvus corax")
            .put(2, "Corvus ruficollis")
            .put(3, "Corvus albicollis")
            .put(4, "Corvus crassirostris")
            .put(5, "Corvus corax")
            .build();

    public RavenEntity(EntityType<? extends RavenEntity> p_i50251_1_, Level p_i50251_2_) {
        super(p_i50251_1_, p_i50251_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        if (!this.isBaby()) {
            this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
            this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
            this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
            this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
            this.targetSelector.removeGoal(PanicGoal);
            this.targetSelector.addGoal(1, new CreaturesBirdEntity.DefendBabyGoal());

        }
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());

    }

    protected <E extends RavenEntity> PlayState flyAnimController(final AnimationState<E> event)
    {
        if (event.isMoving() && this.onGround()) {
            return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
        } if (!this.onGround() || this.isFlying()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
    } if (this.isSleeping()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sleep"));
    } if (this.isInSittingPose()) {
        return event.setAndContinue(RawAnimation.begin().thenLoop("sit"));
    }
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Flying", 0, this::flyAnimController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.4F).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    public int methodOfDeterminingVariant() {
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get()) == 1) {
            return 5;
        }
        else {
            return this.random.nextInt(4)+1;
        }
    }

    @Override
    public RavenEntity getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        RavenEntity ravenentity = CreaturesEntities.RAVEN.get().create(p_241840_1_);
        if (this.random.nextInt(CreaturesConfig.raven_albino_chance.get() * 2) == 1) {
            ravenentity.setVariant(2);
        } else {
            ravenentity.setVariant(this.getVariant());
        }
        ravenentity.setGender(this.random.nextInt(2));
        ravenentity.setHeightMultiplier(getSpawnEggOffspringHeight());
        return ravenentity;
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {
        return CreaturesSound.RAVEN_AMBIENT.get(); }
        return null;
    }

//    public Ingredient getBirdFood() {
//        return Ingredient.of(Items.ROTTEN_FLESH, Items.EGG, Items.CHICKEN);
//    }

    public boolean isFood(ItemStack p_70877_1_) {
        return FOOD_ITEMS.test(p_70877_1_);
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.RAVEN;
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.ROTTEN_FLESH, 1);
    }

    public double getHatchChance() {
        return CreaturesConfig.raven_hatch_chance.get();
    }

    public int getClutchSize() {
        return this.random.nextInt(CreaturesConfig.raven_clutch_size.get());
    }

    public int numVariants() {
        return 5;
    }

    public boolean isMonogamous() {
        return true;
    }

    public int getMaxFlockSize() {
        return 4;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    public int getAmbientSoundInterval() {
        return 600;
    }

    public String getSpeciesName() {
        Component translatable = SPECIES_NAMES.get(this.getVariant());
        if (translatable != null) {
            return translatable.getString();
        } return "Unknown";
    }

    public int getIUCNStatus() {
        if (this.getVariant()== 2) {
            return 1;
        }
        return super.getIUCNStatus();
    }

    public String getScientificName() {
        return SCIENTIFIC_NAMES.get(this.getVariant());
    }

    public Component getFunFact() {
        return Component.translatable("description.creatures.raven");
    }

    public boolean canTame() {
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level().isClientSide()) return;
        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        if (!this.isTame()) return;
        if (this.getOwner() == null) return;
        if (this.isOrderedToSit()) return;
        if (this.isBaby()) return;
        if (!this.getMainHandItem().isEmpty()) return; // already holding a gift
        if (giftCooldown > 0) { giftCooldown--; return; }

        if (this.distanceTo(this.getOwner()) > 16.0) { return; }

        // Roll loot table
        LootTable table = serverLevel.getServer()
                .getLootData().getLootTable(CreaturesLootTables.RAVEN_GIFT);
        LootParams params = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.ORIGIN, this.position())
                .withParameter(LootContextParams.THIS_ENTITY, this)
                .create(LootContextParamSets.GIFT);

        List<ItemStack> gifts = table.getRandomItems(params);
        if (gifts.isEmpty()) { resetGiftCooldown(); return; }

        ItemStack gift = gifts.get(0);
        if (gift.isEmpty()) { resetGiftCooldown(); return; }

        this.setItemSlot(EquipmentSlot.MAINHAND, gift);
        this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
        this.playSound(CreaturesSound.RAVEN_AMBIENT.get(), 1.0F, 1.5F);

//        // Notify owner
//        if (this.getOwner() instanceof Player player) {
//            player.sendSystemMessage(Component.translatable(
//                    "message.creatures.raven_gift",
//                    this.hasCustomName() ? this.getCustomName() :
//                            Component.translatable("entity.creatures.raven")));
//        }

    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isTame() && this.isOwnedBy(player) && !this.getMainHandItem().isEmpty()) {
            ItemStack gift = this.getMainHandItem().copy();

            if (!player.getInventory().add(gift)) {
                player.drop(gift, false);
            }

            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            this.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 1.0F);
            resetGiftCooldown();
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("GiftCooldown", giftCooldown);
    }

    private void resetGiftCooldown() {
        giftCooldown = GIFT_COOLDOWN_MIN +
                this.random.nextInt(GIFT_COOLDOWN_MAX - GIFT_COOLDOWN_MIN);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        giftCooldown = tag.getInt("GiftCooldown");
    }

    public List<ItemStack> getAllFoodItems() {
        return Arrays.stream(FOOD_ITEMS.getItems())
                .map(ItemStack::copy)
                .collect(java.util.stream.Collectors.toList());
    }

}
