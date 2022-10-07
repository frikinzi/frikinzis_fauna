package com.creatures.afrikinzi.entity.peafowl;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.entity.AbstractCreaturesTameable;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
import com.creatures.afrikinzi.init.ItemInit;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
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
import java.util.List;
import java.util.Set;

public class EntityPeafowl extends AbstractCreaturesTameable implements IAnimatable, ICreaturesEntity {
    private AnimationFactory factory = new AnimationFactory(this);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntityPeafowl.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> WANDERING = EntityDataManager.createKey(EntityPeafowl.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> ON_DISPLAY = EntityDataManager.createKey(EntityPeafowl.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> DISPLAYING = EntityDataManager.createKey(EntityPeafowl.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Integer> GENDER = EntityDataManager.createKey(EntityPeafowl.class, DataSerializers.VARINT);
    protected static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(EntityPeafowl.class, DataSerializers.VARINT);
    private EntityAITempt aiTempt;
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.APPLE, Items.MELON, Items.WHEAT_SEEDS);
    public int timeUntilDisplayingEnds;

    public EntityPeafowl(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 1F);
        this.timeUntilDisplayingEnds = 20;
    }

    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.aiTempt = new EntityAITempt(this, 0.6D, Items.APPLE, false);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, this.aiTempt);
        if (CreaturesConfig.birdsFollow == true) {
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F)); }
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(10, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.2D, true));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(VARIANT, Integer.valueOf(0));
        this.dataManager.register(GENDER, Integer.valueOf(0));
        this.dataManager.register(SLEEPING, Boolean.valueOf(false));
        this.dataManager.register(WANDERING, Boolean.valueOf(false));
        this.dataManager.register(ON_DISPLAY, Boolean.valueOf(false));
        this.dataManager.register(DISPLAYING, Boolean.valueOf(false));

    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(this.rand.nextInt(4));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.isSitting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
            return PlayState.CONTINUE;
        }
        if (this.isDisplaying() && this.getGender() == 1) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("closing", false).addAnimation("idle", true));
            return PlayState.CONTINUE;
        }
        if (this.isOnDisplay()) {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("displaywalk", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("display", false).addAnimation("displayidle", true));
            return PlayState.CONTINUE;
        } else {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
                return PlayState.CONTINUE;
            }
            if (this.isSleeping()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (this.aiSit != null)
            {
                this.aiSit.setSitting(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.PEAFOWL;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!this.isTamed() && TAME_ITEMS.contains(itemstack.getItem()))
        {
            if (!player.capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }

            if (!this.world.isRemote)
            {
                if (this.rand.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                {
                    this.setTamedBy(player);
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        else if (itemstack.getItem() == Items.STICK && this.isTamed()) {
            if (this.isWandering() == false) {
                for (Object entry : this.tasks.taskEntries.toArray()) {
                    EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) entry).action;
                    if (ai instanceof EntityAIFollowOwner) this.tasks.removeTask(ai);
                    this.setWandering(true);
                }
                Minecraft mc = Minecraft.getMinecraft();
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("Set to wandering"));
                }
                return true;
            } else {
                this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
                this.setWandering(false);
                Minecraft mc = Minecraft.getMinecraft();
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("Set to following"));
                }
                return true;
            }
        }

        else
        {
            if (!this.world.isRemote && this.isTamed() && this.isOwner(player))
            {
                this.aiSit.setSitting(!this.isSitting());
            }


            return super.processInteract(player, hand);
        }
    }

    public EntityPeafowl createChild(EntityAgeable ageable)
    {
        EntityPeafowl entitypeafowl = new EntityPeafowl(this.world);

        if (this.isTamed())
        {
            entitypeafowl.setOwnerId(this.getOwnerId());
            entitypeafowl.setTamed(true);
        }
        entitypeafowl.setVariant(this.getVariant());
        entitypeafowl.setGender(this.rand.nextInt(2));

        return entitypeafowl;
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Items.APPLE;
    }

    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        if (this.isSitting()) {
            return false;
        }
        if (!this.isTamed()) {
            return false;
        }
        else if (!(otherAnimal instanceof EntityPeafowl))
        {
            return false;
        }
        else
        {
            EntityPeafowl entitypeafowl = (EntityPeafowl)otherAnimal;

            if (!entitypeafowl.isTamed())
            {
                return false;
            }
            else
            {
                return this.isInLove() && entitypeafowl.isInLove();
            }
        }
    }

    public SoundEvent getAmbientSound()
    {
        if (!this.isSleeping()) {
        return SoundsHandler.PEAFOWL_AMBIENT;
        }
        else {
            return null;
        }
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sleeping", this.isSleeping());
        compound.setBoolean("Wandering", this.isWandering());
        compound.setBoolean("OnDisplay", this.isOnDisplay());
        compound.setBoolean("Displaying", this.isDisplaying());
        compound.setInteger("Variant", this.getVariant());
        compound.setInteger("Gender", this.getGender());

    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setWandering(compound.getBoolean("Wandering"));
        this.setOnDisplay(compound.getBoolean("OnDisplay"));
        this.setDisplaying(compound.getBoolean("Displaying"));
        this.setVariant(compound.getInteger("Variant"));
        this.setGender(compound.getInteger("Gender"));
    }

    public void setSleeping(boolean value) {
        this.dataManager.set(SLEEPING, Boolean.valueOf(value));
    }

    public boolean isSleeping() {
        return this.dataManager.get(SLEEPING);
    }

    public void setWandering(boolean value) {
        this.dataManager.set(WANDERING, Boolean.valueOf(value));
    }

    public boolean isWandering() {
        return this.dataManager.get(WANDERING);
    }

    public void setOnDisplay(boolean value) {
        this.dataManager.set(ON_DISPLAY, Boolean.valueOf(value));
    }

    public boolean isOnDisplay() {
        return this.dataManager.get(ON_DISPLAY);
    }

    public void setDisplaying(boolean value) {
        this.dataManager.set(DISPLAYING, Boolean.valueOf(value));
    }

    public boolean isDisplaying() {
        return this.dataManager.get(DISPLAYING);
    }

    public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 1, 4);
    }

    public void setVariant(int p_191997_1_)
    {
        this.dataManager.set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    @Override
    public boolean isMovementBlocked() {
        if(this.onGround) {
            return super.isMovementBlocked() || isSleeping();
        }
        else{
            return super.isMovementBlocked();
        }
    }

    public void onLivingUpdate()
    {
        if (this.onGround) {
            setSleeping(world.getWorldTime() >= 12000 && world.getWorldTime() <= 23000);
        }
        if (this.inWater || this.isInWater() || this.isInLava() || this.isBurning()) {
            setSleeping(false);
        }
        if (this.determineDisplay() == true && this.rand.nextInt(50) == 1) {
            this.timeUntilDisplayingEnds = 20;
            setOnDisplay(true);
        }
        if (this.determineDisplay() == false && this.getGender() == 1) {
            setOnDisplay(false);
        }

        super.onLivingUpdate();
    }

    public boolean determineDisplay() {
        List<EntityPeafowl> list = this.world.<EntityPeafowl>getEntitiesWithinAABB(EntityPeafowl.class, this.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
        EntityPeafowl entityanimal = null;
        double d0 = Double.MAX_VALUE;

        for (EntityPeafowl entityanimal1 : list) {
            if (entityanimal1 instanceof EntityPeafowl && entityanimal1.getGender() == 0) {
                double d1 = this.getDistanceSq(entityanimal1);

                if (d1 <= d0) {
                    d0 = d1;
                    entityanimal = entityanimal1;
                }
            }
        }
        if (entityanimal == null) {
            return false;
        }
        else if (!this.isChild() && this.getGender() == 1 && !this.isSleeping()) {
            return true;
        } else if (d0 > 12.0D) {
            return false;
        }
        else {
            return false;
        }
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public String getSpeciesName() {
        if (this.getVariant() == 1) {
            String s1 = I18n.format("message.creatures.greenpeafowl");
            return s1;
        }
        else if (this.getVariant() == 2) {
            String s1 = I18n.format("message.creatures.indianpeafowl");
            return s1;
        }
        else if (this.getVariant() == 3) {
            String s1 = I18n.format("message.creatures.albinopeafowl");
            return s1;
        } else {
            return "Unknown";
        }
    }

    public String getFoodName() {
        return net.minecraft.util.text.translation.I18n.translateToLocal(Items.APPLE.getUnlocalizedName() + ".name").trim();
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
    }


}
