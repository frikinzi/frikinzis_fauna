package com.creatures.afrikinzi.entity.fairy_wren;

import com.creatures.afrikinzi.entity.FlyingEntityTameableBase;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
import com.creatures.afrikinzi.entity.RaptorBase;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Set;

public class EntityFairyWren extends FlyingEntityTameableBase implements IAnimatable, ICreaturesEntity {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityFairyWren.class, DataSerializers.VARINT);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntityFairyWren.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> GENDER = EntityDataManager.<Integer>createKey(EntityFairyWren.class, DataSerializers.VARINT);
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

    public EntityFairyWren(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.6F);
        this.moveHelper = new EntityFlyHelper(this);
    }


    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, RaptorBase.class, 7.0F, 1.0D, 1.2D));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        } if (!this.onGround || this.isFlying()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
        return PlayState.CONTINUE;
    } if (this.isSleeping()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
        return PlayState.CONTINUE;
    } if (this.isSitting()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", true));
        return PlayState.CONTINUE;
    }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(this.rand.nextInt(7));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    }

    public void onLivingUpdate()
    {
        if (this.onGround) {
            setSleeping(world.getWorldTime() >= 13000 && world.getWorldTime() <= 23000);
        }
        if (this.inWater || this.isInWater() || this.isInLava() || this.isBurning()) {
            setSleeping(false);
        }
        super.onLivingUpdate();
        this.calculateFlapping();
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        {

            return super.processInteract(player, hand);
        }
    }

    public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 1, 7);
    }

    public void setVariant(int p_191997_1_)
    {
        this.dataManager.set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(VARIANT, Integer.valueOf(0));
        this.dataManager.register(SLEEPING, Boolean.valueOf(false));
        this.dataManager.register(GENDER, Integer.valueOf(0));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
        compound.setBoolean("Sleeping", this.isSleeping());
        compound.setInteger("Gender", this.getGender());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setGender(compound.getInteger("Gender"));
    }

    public void setSleeping(boolean value) {
        this.dataManager.set(SLEEPING, Boolean.valueOf(value));
    }

    public boolean isSleeping() {
        return this.dataManager.get(SLEEPING);
    }

    @Override
    public boolean isMovementBlocked() {
        if (this.onGround) {
            return super.isMovementBlocked() || isSleeping();
        } else {
            return super.isMovementBlocked();
        }
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public TextComponentTranslation getVariantName() {
        if (this.getVariant() == 1) {
            return new TextComponentTranslation("message.creatures.variegated");
        }
        if (this.getVariant() == 2) {
            return new TextComponentTranslation("message.creatures.splendid");
        }
        if (this.getVariant() == 3) {
            return new TextComponentTranslation("message.creatures.whitewinged");
        }
        if (this.getVariant() == 4) {
            return new TextComponentTranslation("message.creatures.blackwinged");
        }
        if (this.getVariant() == 5) {
            return new TextComponentTranslation("message.creatures.purplecrowned");
        }
        else {
            return new TextComponentTranslation("message.creatures.superb");
        }
    }

    public String getSpeciesName() {
        if (this.getVariant() == 1) {
            String s1 = I18n.format("message.creatures.variegated");
            return s1;
        }
        else if (this.getVariant() == 2) {
            String s1 = I18n.format("message.creatures.splendid");
            return s1;
        }
        else if (this.getVariant() == 3) {
            String s1 = I18n.format("message.creatures.whitewinged");
            return s1;
        }
        else if (this.getVariant() == 4) {
            String s1 = I18n.format("message.creatures.blackwinged");
            return s1;
        }
        else if (this.getVariant() == 5) {
            String s1 = I18n.format("message.creatures.purplecrowned");
            return s1;
        }
        else if (this.getVariant() == 6) {
            String s1 = I18n.format("message.creatures.superb");
            return s1;
        } else {
            return "???";
        }
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Items.WHEAT_SEEDS;
    }

    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityFairyWren))
        {
            return false;
        }
        else
        {
            EntityFairyWren entityfairywren = (EntityFairyWren)otherAnimal;
            if (this.getGender() == entityfairywren.getGender()) {
                return false;
            }
            return this.isInLove() && entityfairywren.isInLove();
        }


    }

    public EntityFairyWren createChild(EntityAgeable ageable)
    {
        EntityFairyWren entityfairywren = new EntityFairyWren(this.world);
        entityfairywren.setVariant(this.getVariant());
        entityfairywren.setGender(this.rand.nextInt(2));

        return entityfairywren;
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {

            return SoundsHandler.FAIRYWREN_AMBIENT;
        } else {
            return null;
        }
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.GENERIC_BIRD;
    }

    public String getFoodName() {
        return net.minecraft.util.text.translation.I18n.translateToLocal(Items.WHEAT_SEEDS.getUnlocalizedName() + ".name").trim();
    }


}
