package com.creatures.afrikinzi.entity.swallow;

import com.creatures.afrikinzi.entity.AbstractCreaturesNonTameable;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
import com.creatures.afrikinzi.entity.RaptorBase;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import com.google.common.collect.Sets;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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

public class EntitySwallow extends AbstractCreaturesNonTameable implements EntityFlying, IAnimatable, ICreaturesEntity {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntitySwallow.class, DataSerializers.VARINT);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntitySwallow.class, DataSerializers.BOOLEAN);
    private static final Set<Item> BREEDING_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;


    public EntitySwallow(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.6F);
        this.moveHelper = new EntityFlyHelper(this);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, RaptorBase.class, 7.0F, 1.0D, 1.2D));
        //this.targetTasks.addTask(6, new EntityAITargetNonTamed(this, EntityChicken.class, false, (Predicate)null));
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
        return LootTableHandler.GENERIC_BIRD;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(this.rand.nextInt(6));
        return super.onInitialSpawn(difficulty, livingdata);
    }


    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40000000298023224D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
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
            setSleeping(world.getWorldTime() >= 13000 && world.getWorldTime() <= 23000);
        }
        if (this.inWater || this.isInWater() || this.isInLava() || this.isBurning()) {
            setSleeping(false);
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


    public void fall(float distance, float damageMultiplier)
    {
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }


    public boolean canMateWith(EntityAnimal otherAnimal)
    {

        if (otherAnimal == this)
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntitySwallow))
        {
            return false;
        }
        else
        {
            EntitySwallow entityswallow = (EntitySwallow)otherAnimal;
                return this.isInLove() && entityswallow.isInLove();
        }


    }

    @Nullable
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        EntitySwallow entityswallow = new EntitySwallow(this.world);
        entityswallow.setVariant(this.getVariant());

        return entityswallow;
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



    public boolean isFlying()
    {
        return !this.onGround;
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
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
        compound.setBoolean("Sleeping", this.isSleeping());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));
        this.setSleeping(compound.getBoolean("Sleeping"));
    }

    public void setSleeping(boolean value) {
        this.dataManager.set(SLEEPING, Boolean.valueOf(value));
    }

    public boolean isSleeping() {
        return this.dataManager.get(SLEEPING);
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

            return SoundsHandler.SWALLOW_AMBIENT;
        } else {
            return null;
        }
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return BREEDING_ITEMS.contains(stack.getItem());
    }

    public String getSpeciesName() {
        if (this.getVariant() == 1) {
            String s1 = I18n.format("message.creatures.welcome");
            return s1;
        }
        else if (this.getVariant() == 2) {
            String s1 = I18n.format("message.creatures.treeswallow");
            return s1;
        }
        else if (this.getVariant() == 3) {
            String s1 = I18n.format("message.creatures.violetgreen");
            return s1;
        }
        else if (this.getVariant() == 4) {
            String s1 = I18n.format("message.creatures.redrumped");
            return s1;
        }
        else if (this.getVariant() == 5) {
            String s1 = I18n.format("message.creatures.barn");
            return s1;
        } else {
            return "Unknown";
        }
    }

    public String getFoodName() {
        return net.minecraft.util.text.translation.I18n.translateToLocal(Items.WHEAT_SEEDS.getUnlocalizedName() + ".name").trim();
    }

}
