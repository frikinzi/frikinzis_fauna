package com.creatures.afrikinzi.entity.raven;

import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import com.google.common.collect.Sets;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Set;

public class EntityRaven extends EntityTameable implements IAnimatable, EntityFlying {

    private AnimationFactory factory = new AnimationFactory(this);
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.EGG, Items.ROTTEN_FLESH);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntityRaven.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> WANDERING = EntityDataManager.createKey(EntityRaven.class, DataSerializers.BOOLEAN);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    public int timeUntilNextGift;
    public int timeUntilNextSleep;

    public EntityRaven(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 1F);
        this.moveHelper = new EntityFlyHelper(this);
        this.timeUntilNextGift = this.rand.nextInt(6000) + 6000;
    }

    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F));
        this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        //this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving() && this.onGround && !this.isDead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        } if (!this.onGround || this.isFlying() && !this.isDead) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
        return PlayState.CONTINUE;
    } if (this.isSleeping() && !this.isDead) {
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


    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.RAVEN;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    protected PathNavigate createNavigator(World worldIn)
    {
        PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
        pathnavigateflying.setCanOpenDoors(true);
        pathnavigateflying.setCanFloat(true);
        pathnavigateflying.setCanEnterDoors(true);
        return pathnavigateflying;
    }

    public void onLivingUpdate()
    {
        if (this.onGround) {
            setSleeping(world.getWorldTime() >= 12000 && world.getWorldTime() <= 23000);
        }
        if (this.inWater || this.isInWater() || this.isInLava() || this.isBurning()) {
            setSleeping(false);
        }
        if (!this.world.isRemote && this.isTamed() && --this.timeUntilNextGift <= 0)
        {
            this.playSound(SoundsHandler.RAVEN_AMBIENT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            int a = this.rand.nextInt(20);
            if (a == 1) {
                this.dropItem(Items.ENDER_PEARL, 1);
            } else if (a == 2 || a == 3) {
                this.dropItem(Items.BOOK, 1);
            } else if (a > 3 && a < 15) {
                this.dropItem(Items.FEATHER, 1);
            } else {
                this.dropItem(Items.CARROT, rand.nextInt(3));
            }
            this.timeUntilNextGift = this.rand.nextInt(6000) + 6000;

        }
        super.onLivingUpdate();
        this.calculateFlapping();
    }

    private void calculateFlapping()
    {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = (float)((double)this.flapSpeed + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);

        if (!this.onGround && this.flapping < 1.0F)
        {
            this.flapping = 1.0F;
        }

        this.flapping = (float)((double)this.flapping * 0.9D);

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        this.flap += this.flapping * 2.0F;
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

            if (!this.isSilent())
            {
                this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PARROT_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
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
                    if (ai instanceof EntityAIFollowOwner || ai instanceof EntityAIFollowOwnerFlying) this.tasks.removeTask(ai);
                    this.setWandering(true);
                }
                Minecraft mc = Minecraft.getMinecraft();
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("Set to wandering"));
                }
                return true;
            } else {
                this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
                this.tasks.addTask(2, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F));
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
            if (!this.world.isRemote && !this.isFlying() && this.isTamed() && this.isOwner(player))
            {
                this.aiSit.setSitting(!this.isSitting());
            }

            return super.processInteract(player, hand);
        }
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }

    public boolean canBePushed()
    {
        return true;
    }

    protected void collideWithEntity(Entity entityIn)
    {
        if (!(entityIn instanceof EntityPlayer))
        {
            super.collideWithEntity(entityIn);
        }
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
            if (this.isSleeping() == true) {
                this.setSleeping(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public boolean isFlying()
    {
        return !this.onGround;
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SLEEPING, Boolean.valueOf(false));
        this.dataManager.register(WANDERING, Boolean.valueOf(false));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sleeping", this.isSleeping());
        compound.setBoolean("Wandering", this.isWandering());
        compound.setInteger("GiftTime", this.timeUntilNextGift);
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setWandering(compound.getBoolean("Wandering"));
        if (compound.hasKey("GiftTime"))
        {
            this.timeUntilNextGift = compound.getInteger("GiftTime");
        }
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

    @Override
    public boolean isMovementBlocked() {
        if(this.onGround) {
            return super.isMovementBlocked() || isSleeping();
        }
        else{
            return super.isMovementBlocked();
        }
    }

    public SoundEvent getAmbientSound()
    {
        if (!this.isSleeping()) {

            return SoundsHandler.RAVEN_AMBIENT;
        } else {
            return null;
        }
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return TAME_ITEMS.contains(stack.getItem());
    }

    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityRaven))
        {
            return false;
        }
        else
        {
            EntityRaven entityraven = (EntityRaven)otherAnimal;
            return this.isInLove() && entityraven.isInLove();
        }


    }

    public EntityRaven createChild(EntityAgeable ageable)
    {
        EntityRaven entityraven = new EntityRaven(this.world);

        return entityraven;
    }

}
