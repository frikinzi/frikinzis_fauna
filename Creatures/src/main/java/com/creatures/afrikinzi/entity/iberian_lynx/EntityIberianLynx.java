package com.creatures.afrikinzi.entity.iberian_lynx;

import com.creatures.afrikinzi.entity.mandarin_duck.EntityMandarinDuck;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class EntityIberianLynx extends EntityAnimal implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntityIberianLynx.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(EntityIberianLynx.class, DataSerializers.BOOLEAN);
    public int timeUntilSit;
    public int sitTimer;

    public EntityIberianLynx(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 1.2F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(10, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-4F));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        } if (this.isSleeping() || this.isSitting()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
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

    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_OCELOT;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SLEEPING, Boolean.valueOf(false));
        this.dataManager.register(SITTING, Boolean.valueOf(false));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sleeping", this.isSleeping());
        compound.setBoolean("Sitting", this.isSitting());

    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setSitting(compound.getBoolean("Sitting"));
    }

    public EntityIberianLynx createChild(EntityAgeable ageable)
    {
        EntityIberianLynx entityiberianlynx = new EntityIberianLynx(this.world);
        return entityiberianlynx;
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Items.CHICKEN;
    }

    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityIberianLynx))
        {
            return false;
        }
        else
        {
            EntityIberianLynx entityiberianlynx = (EntityIberianLynx)otherAnimal;
                return this.isInLove() && entityiberianlynx.isInLove();
        }


    }

    public void setSleeping(boolean value) {
        this.dataManager.set(SLEEPING, Boolean.valueOf(value));
    }

    public boolean isSleeping() {
        return this.dataManager.get(SLEEPING);
    }

    public void setSitting(boolean value) {
        this.dataManager.set(SITTING, Boolean.valueOf(value));
    }

    public boolean isSitting() {
        return this.dataManager.get(SITTING);
    }

    public void onLivingUpdate()
    {
        if (this.onGround) {
            setSleeping(world.getWorldTime() >= 12000 && world.getWorldTime() <= 23000);
        }
        if (this.shouldSleep() == false) {
            setSleeping(false);
        }
        if (!this.world.isRemote && --this.timeUntilSit <= 0 && !this.isSitting() && !this.isSleeping()) {
            this.setSitting(true);
            this.sitTimer = this.rand.nextInt(300) + 500;
        } if (this.isSitting() && !this.world.isRemote && --this.sitTimer <= 0) {
            this.setSitting(false);
            this.timeUntilSit = this.rand.nextInt(1000) + 2000;
    } if (this.isSitting() && (this.isBurning() || this.isInWater() || this.isInLava())) {
        this.setSitting(false);
        this.sitTimer = 0;
    }
        super.onLivingUpdate();
    }

    @Override
    public boolean isMovementBlocked() {
        if(this.onGround) {
            return super.isMovementBlocked() || isSleeping() || isSitting();
        }
        else{
            return super.isMovementBlocked();
        }
    }

    public boolean shouldSleep() {
        if (this.inWater || this.isInWater() || this.isInLava() || this.isBurning()) {
            return false;
        } else {
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

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (this.isSitting())
            {
                this.setSitting(false);
                this.timeUntilSit = this.rand.nextInt(1000) + 2000;
            }
            if (this.isSleeping() == true) {
                this.setSleeping(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public SoundEvent getAmbientSound()
    {
        if (!this.isSleeping()) {

            return SoundsHandler.IBERIAN_LYNX_AMBIENT;
        } else {
            return null;
        }
    }

}
