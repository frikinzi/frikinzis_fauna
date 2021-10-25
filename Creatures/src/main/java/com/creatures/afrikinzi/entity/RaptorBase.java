package com.creatures.afrikinzi.entity;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.init.ItemInit;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import com.google.common.collect.Sets;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class RaptorBase extends EntityTameable implements EntityFlying {
    public static Set<Item> TAME_ITEMS = Sets.newHashSet(Items.RABBIT, Items.PORKCHOP, Items.CHICKEN, ItemInit.RAW_LARGE_WILD_BIRD_MEAT, ItemInit.RAW_SMALL_WILD_BIRD_MEAT);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(RaptorBase.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> WANDERING = EntityDataManager.createKey(RaptorBase.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(RaptorBase.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> HAS_PREY = EntityDataManager.createKey(RaptorBase.class, DataSerializers.BOOLEAN);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    public int HungerTime;

    public RaptorBase(World worldIn)
    {
        super(worldIn);
        //this.setSize(1.8F, 1.5F);
        this.moveHelper = new EntityFlyHelper(this);
        this.HungerTime = 0;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.BIRD_OF_PREY;
    }

    protected PathNavigate createNavigator(World worldIn)
    {
        PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
        pathnavigateflying.setCanOpenDoors(false);
        pathnavigateflying.setCanFloat(true);
        pathnavigateflying.setCanEnterDoors(true);
        return pathnavigateflying;
    }


    public void onLivingUpdate()
    {
        if (this.onGround) {
            getsleep();
        }
        if (this.shouldSleep() == false) {
            setSleeping(false);
        }
        if (this.getAttackTarget() != null) {
            setAttacking(true);
        } else if (this.getAttackTarget() == null) {
            setAttacking(false);
        }
        if (this.hasPrey() && this.onGround && !this.isFlying() && !this.isInWater()) {
            this.dropItem(Items.FISH, 1);
            this.setPrey(false);
        }
        super.onLivingUpdate();
        this.calculateFlapping();
    }

    public void calculateFlapping()
    {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = (float)((double)this.flapSpeed + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 3.0F);

        if (!this.onGround && this.flapping < 1.0F)
        {
            this.flapping = 1.0F;
        }

        this.flapping = (float)((double)this.flapping * 0.9D);

        if (!this.onGround && this.motionY < 0.0D)
        {
            if (this.isAttacking() == false) {
                this.motionY *= 0.6D; }
            else {
                this.motionY *= 1.2D;
            }
        }

        this.flap += this.flapping * 2.0F;
    }

    public Set<Item> getTameItems() {
        TAME_ITEMS = Sets.newHashSet(Items.RABBIT, Items.PORKCHOP, Items.CHICKEN);
        return TAME_ITEMS;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!this.isTamed() && getTameItems().contains(itemstack.getItem()))
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
                    this.setAttackTarget(null);
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
            if (this.isTamed() && TAME_ITEMS.contains(itemstack.getItem()) && this.getHealth() < this.getMaxHealth()) {
                if (!this.world.isRemote) {
                this.heal(5.0F);
                this.playTameEffect(true);
                }
            }
            if (!this.world.isRemote && !this.isFlying() && this.isTamed() && this.isOwner(player) && !this.isBreedingItem(itemstack))
            {
                this.aiSit.setSitting(!this.isSitting());
            }

            return super.processInteract(player, hand);
        }
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return false;
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }

    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        return false;
    }

    @Nullable
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        return null;
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
        //grab and throw in the air
        if (this.getAttackTarget() == entityIn && this.getHealth() > ((EntityLivingBase) entityIn).getHealth() && CreaturesConfig.eagleThrows == true && this.posY < 100) {
            this.motionY = 1.5D;
            entityIn.motionY = 1.5D;
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
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 6.0F);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SLEEPING, Boolean.valueOf(false));
        this.dataManager.register(WANDERING, Boolean.valueOf(false));
        this.dataManager.register(ATTACKING, Boolean.valueOf(false));
        this.dataManager.register(HAS_PREY, Boolean.valueOf(false));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sleeping", this.isSleeping());
        compound.setBoolean("Wandering", this.isWandering());
        compound.setBoolean("Attacking", this.isAttacking());
        compound.setBoolean("Prey", this.hasPrey());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setWandering(compound.getBoolean("Wandering"));
        this.setAttacking(compound.getBoolean("Attacking"));
        this.setPrey(compound.getBoolean("Prey"));
    }

    public void setSleeping(boolean value) {
        this.dataManager.set(SLEEPING, Boolean.valueOf(value));
    }

    public boolean isSleeping() {
        return this.dataManager.get(SLEEPING);
    }

    public void setPrey(boolean value) {
        this.dataManager.set(HAS_PREY, Boolean.valueOf(value));
    }

    public boolean hasPrey() {
        return this.dataManager.get(HAS_PREY);
    }

    public void setWandering(boolean value) {
        this.dataManager.set(WANDERING, Boolean.valueOf(value));
    }

    public boolean isWandering() {
        return this.dataManager.get(WANDERING);
    }

    public void setAttacking(boolean value) {
        this.dataManager.set(ATTACKING, Boolean.valueOf(value));
    }

    public boolean isAttacking() {
        return this.dataManager.get(ATTACKING);
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

    public boolean shouldSleep() {
        if (this.inWater || this.isInWater() || this.isInLava() || this.isBurning()) {
            return false;
        }
        else if (this.isTamed() && !this.isWandering()) {
            return false;
        }
        else {
            List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
            EntityPlayer entityanimal = null;
            double d0 = Double.MAX_VALUE;

            for (EntityPlayer entityanimal1 : list) {
                if (entityanimal1 instanceof EntityPlayer && !entityanimal1.isSneaking()) {
                    double d1 = this.getDistanceSq(entityanimal1);

                    if (d1 <= d0) {
                        d0 = d1;
                        entityanimal = entityanimal1;
                    }
                }
            }

            if (entityanimal == null) {
                return true;
            } else if (d0 < 12.0D) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void getsleep() {
        setSleeping(world.getWorldTime() >= 12000 && world.getWorldTime() <= 23000);
    }

}
