package com.creatures.afrikinzi.entity.kakapo;

import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
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

import javax.annotation.Nullable;
import java.util.Set;

public class EntityKakapo extends EntityTameable implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntityKakapo.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> WANDERING = EntityDataManager.createKey(EntityKakapo.class, DataSerializers.BOOLEAN);
    private EntityAITempt aiTempt;
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.APPLE, Items.MELON);

    public EntityKakapo(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 1F);
    }

    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.aiTempt = new EntityAITempt(this, 0.6D, Items.APPLE, false);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, this.aiTempt);
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(10, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SLEEPING, Boolean.valueOf(false));
        this.dataManager.register(WANDERING, Boolean.valueOf(false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kakapo.walk", true));
            return PlayState.CONTINUE;
        } if (!this.onGround) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kakapo.flying", true));
        return PlayState.CONTINUE;
    } if (this.isSitting()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kakapo.sit", false));
        return PlayState.CONTINUE;
    } if (this.isSleeping()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kakapo.sleep", true));
        return PlayState.CONTINUE;
    }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.kakapo.idle", true));
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
        return LootTableList.ENTITIES_PARROT;
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

    public EntityKakapo createChild(EntityAgeable ageable)
    {
        EntityKakapo entitykakapo = new EntityKakapo(this.world);

        if (this.isTamed())
        {
            entitykakapo.setOwnerId(this.getOwnerId());
            entitykakapo.setTamed(true);
        }

        return entitykakapo;
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
        else if (!(otherAnimal instanceof EntityKakapo))
        {
            return false;
        }
        else
        {
            EntityKakapo entitykakapo = (EntityKakapo)otherAnimal;

            if (!entitykakapo.isTamed())
            {
                return false;
            }
            else
            {
                return this.isInLove() && entitykakapo.isInLove();
            }
        }
    }

    public SoundEvent getAmbientSound()
    {
        if (!this.isSleeping()) {
        return SoundsHandler.KAKAPO_AMBIENT;
        }
        else {
            return null;
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundsHandler.KAKAPO_HURT;
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sleeping", this.isSleeping());
        compound.setBoolean("Wandering", this.isWandering());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setWandering(compound.getBoolean("Wandering"));
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

    public void onLivingUpdate()
    {
        if (this.onGround) {
            setSleeping(world.getWorldTime() >= 12000 && world.getWorldTime() <= 23000);
        }
        if (this.inWater || this.isInWater() || this.isInLava() || this.isBurning()) {
            setSleeping(false);
        }
        super.onLivingUpdate();
    }


}
