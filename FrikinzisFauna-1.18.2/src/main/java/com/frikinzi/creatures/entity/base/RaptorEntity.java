package com.frikinzi.creatures.entity.base;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.entity.ai.MateGoal;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.CreaturesLootTables;
import com.google.common.collect.Sets;
import net.minecraft.Util;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class RaptorEntity extends TameableFlyingBirdEntity {
    private static final EntityDataAccessor<Integer> HUNTING = SynchedEntityData.defineId(RaptorEntity.class, EntityDataSerializers.INT);
    public static Predicate<LivingEntity> PREY = (p_30437_) -> {
        EntityType<?> entitytype = p_30437_.getType();
        return !(p_30437_ instanceof TamableAnimal);
    };
    public static final Predicate<ItemEntity> ALLOWED_ITEMS = Entity::isAlive;
    private final SimpleContainer inventory = new SimpleContainer(8);

    public RaptorEntity(EntityType<? extends RaptorEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);
        this.setCanPickUpLoot(true);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
        this.targetSelector.addGoal(1, new MoveToItemGoal());
        this.targetSelector.addGoal(2, new HuntGoal(this, LivingEntity.class, this.getHuntingPrey()));

    }

    public Predicate<LivingEntity> getHuntingPrey() {
        Predicate<LivingEntity> prey = (p_30437_) -> {
            EntityType<?> entitytype = p_30437_.getType();
            return !(p_30437_ instanceof TamableAnimal);
        };
        return prey;
    }

    public Set<Item> getTameFood() {
        return Sets.newHashSet(Items.CHICKEN, Items.RABBIT, CreaturesItems.SMALL_BIRD_MEAT.get(), Items.BEEF, Items.PORKCHOP, Items.MUTTON);
    }

    public Ingredient getBirdFood() {
        return Ingredient.of(Items.CHICKEN, Items.RABBIT, CreaturesItems.SMALL_BIRD_MEAT.get(), Items.BEEF, Items.PORKCHOP, Items.MUTTON);
    }

    public ItemStack getFoodItem() {
        return new ItemStack(Items.CHICKEN, 1);
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        if (super.doHurtTarget(p_70652_1_)) {
            if (p_70652_1_ instanceof LivingEntity && this.getY() < 80 && CreaturesConfig.raptor_throws.get() && !(p_70652_1_ instanceof CreaturesBirdEntity)) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, 0.6D, 0));
                p_70652_1_.setDeltaMovement(p_70652_1_.getDeltaMovement().add(0, 0.8D, 0));
            }
            return true;
        } return false;
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        Item item = itemstack.getItem();
        if (this.isTame()) {
            if (itemstack.getItem() == CreaturesItems.RAPTOR_HORN.get() && this.isTame() && this.getOwner() == p_230254_1_) {
                if (this.isHunting() == 0) {
                    if (this.level.isClientSide) {
                        Component i = new TranslatableComponent("message.hunting");
                        this.getOwner().sendMessage(i, Util.NIL_UUID);
                    }
                    this.setHunting(1);
                } else {
                    if (this.level.isClientSide) {
                        Component i = new TranslatableComponent("message.nothunting");
                        this.getOwner().sendMessage(i, Util.NIL_UUID);
                    }
                    this.setHunting(0);
                }
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
            if (this.isFood(itemstack)) {
                if (this.getHealth() < this.getMaxHealth()) {
                    if (!p_230254_1_.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                    return InteractionResult.SUCCESS;
                } else if (!this.getInventory().isEmpty()) {
                    double d0 = this.getEyeY() - (double)0.3F;
                    for (int i = 0; i < this.getInventory().getContainerSize(); i++) {
                        if (this.getInventory().getItem(i) != ItemStack.EMPTY) {
                            ItemEntity itementity = new ItemEntity(this.level, this.getX(), d0, this.getZ(), this.getInventory().getItem(i));
                            this.level.addFreshEntity(itementity);
                        }
                    }
                    double d3 = this.random.nextGaussian() * 0.02D;
                    double d1 = this.random.nextGaussian() * 0.02D;
                    double d2 = this.random.nextGaussian() * 0.02D;
                    this.level.addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d3, d1, d2);
                    this.getInventory().removeAllItems();
                    return InteractionResult.SUCCESS;

                }

            }
        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }

    public void addAdditionalSaveData(CompoundTag p_35301_) {
        super.addAdditionalSaveData(p_35301_);
        p_35301_.put("Inventory", this.inventory.createTag());
        p_35301_.putInt("Hunting", this.isHunting());
    }

    public void readAdditionalSaveData(CompoundTag p_35290_) {
        super.readAdditionalSaveData(p_35290_);
        this.inventory.fromTag(p_35290_.getList("Inventory", 10));
        this.setWandering(p_35290_.getInt("Hunting"));
    }

    public SimpleContainer getInventory() {
        return this.inventory;
    }

    public SlotAccess getSlot(int p_149995_) {
        int i = p_149995_ - 300;
        return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, i) : super.getSlot(p_149995_);
    }

    protected void pickUpItem(ItemEntity p_35467_) {
        ItemStack itemstack = p_35467_.getItem();
        if (this.wantsToPickUp(itemstack)) {
            SimpleContainer simplecontainer = this.getInventory();
            boolean flag = simplecontainer.canAddItem(itemstack);
            if (!flag) {
                return;
            }

            this.onItemPickup(p_35467_);
            this.take(p_35467_, itemstack.getCount());
            ItemStack itemstack1 = simplecontainer.addItem(itemstack);
            if (itemstack1.isEmpty()) {
                p_35467_.discard();
            } else {
                itemstack.setCount(itemstack1.getCount());
            }
        }

    }

    public boolean wantsToPickUp(ItemStack p_35543_) {
        return (this.isHunting() == 1 && this.isTame() && this.getInventory().canAddItem(p_35543_));
    }

    public int isHunting() {
        return Mth.clamp(this.entityData.get(HUNTING), 0, 2);
    }

    public void setHunting(int p_29449_) {
        this.entityData.set(HUNTING, p_29449_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HUNTING, 0);
    }

    class HuntGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        Predicate<LivingEntity> predicate;
        public HuntGoal(RaptorEntity p_33832_, Class<T> p_33833_, @Nullable Predicate<LivingEntity> p_26100_) {
            super(p_33832_, p_33833_, 10,false,false, p_26100_);
            this.predicate = p_26100_;
        }

        public boolean canUse() {
            if (RaptorEntity.this.isHunting() != 1 || !RaptorEntity.this.isTame()) {
                return false;
            }
            else if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
                return false;
            } else {
                this.findTarget();
                return this.target != null;
            }
        }

        protected void findTarget() {
            if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
                this.target = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), this.predicate), this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            } else {
                this.target = this.mob.level.getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            }

        }

        public boolean canContinueToUse() {
            if (RaptorEntity.this.isHunting() != 1) {
                return false;
            }
            return this.targetConditions != null ? this.targetConditions.test(this.mob, this.target) : super.canContinueToUse();
        }
    }

    class MoveToItemGoal extends Goal {
        public boolean canUse() {
            if (RaptorEntity.this.isHunting() != 1) {
                return false;
            }
            List<ItemEntity> list = RaptorEntity.this.level.getEntitiesOfClass(ItemEntity.class, RaptorEntity.this.getBoundingBox().inflate(6.0D, 8.0D, 6.0D), ALLOWED_ITEMS);
            if (!list.isEmpty()) {
                return RaptorEntity.this.wantsToPickUp(list.get(0).getItem());
            }
            return false;
        }

        public boolean canContinueToUse() {
            return RaptorEntity.this.isHunting() == 1;
        }

        public void start() {
            List<ItemEntity> list = RaptorEntity.this.level.getEntitiesOfClass(ItemEntity.class, RaptorEntity.this.getBoundingBox().inflate(6.0D, 8.0D, 6.0D), ALLOWED_ITEMS);
            if (!list.isEmpty()) {
                RaptorEntity.this.getNavigation().moveTo(list.get(0), (double)1.2F);
            }
        }

        public void tick() {
            List<ItemEntity> list = RaptorEntity.this.level.getEntitiesOfClass(ItemEntity.class, RaptorEntity.this.getBoundingBox().inflate(6.0D, 8.0D, 6.0D), ALLOWED_ITEMS);
            if (!list.isEmpty()) {
                RaptorEntity.this.getNavigation().moveTo(list.get(0), (double)1.2F);
            }

        }
    }

    public ResourceLocation getDefaultLootTable() {
        return CreaturesLootTables.BIRD_OF_PREY;
    }

    public boolean wantsToAttack(LivingEntity p_30389_, LivingEntity p_30390_) {
        if (!(p_30389_ instanceof Creeper) && !(p_30389_ instanceof Ghast)) {
            if (p_30389_ instanceof TameableFlyingBirdEntity) {
                TameableFlyingBirdEntity bird = (TameableFlyingBirdEntity)p_30389_;
                return !bird.isTame() || bird.getOwner() != p_30390_;
            } else if (p_30389_ instanceof Player && p_30390_ instanceof Player && !((Player)p_30390_).canHarmPlayer((Player)p_30389_)) {
                return false;
            } else if (p_30389_ instanceof AbstractHorse && ((AbstractHorse)p_30389_).isTamed()) {
                return false;
            } else {
                return !(p_30389_ instanceof TamableAnimal) || !((TamableAnimal)p_30389_).isTame();
            }
        } else {
            return false;
        }
    }

}

