package com.creatures.afrikinzi.entity.pygmyfalcon;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.entity.RaptorBase;
import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
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

public class EntityPygmyFalcon extends RaptorBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Integer> GENDER = EntityDataManager.<Integer>createKey(EntityPygmyFalcon.class, DataSerializers.VARINT);

    public EntityPygmyFalcon(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
        this.moveHelper = new EntityFlyHelper(this);
    }

    @Override
    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.4D, true));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        if (CreaturesConfig.raptorsFollow == true) {
        this.tasks.addTask(2, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F)); }
        this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if (!this.onGround || this.isFlying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
            return PlayState.CONTINUE;
        }
        if (this.isSleeping() && !this.isDead) {
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
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40000000298023224D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
    }

    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setGender(this.rand.nextInt(3));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public int getGender()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(GENDER)).intValue(), 1, 3);
    }

    public void setGender(int p_191997_1_)
    {
        this.dataManager.set(GENDER, Integer.valueOf(p_191997_1_));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(GENDER, Integer.valueOf(0));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Gender", this.getGender());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setGender(compound.getInteger("Gender"));
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {

            return SoundsHandler.PYGMY_FALCON_AMBIENT;
        } else {
            return null;
        }
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.BIRD_OF_PREY_SMALL;
    }





}
