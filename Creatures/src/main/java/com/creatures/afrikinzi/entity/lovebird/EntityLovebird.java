package com.creatures.afrikinzi.entity.lovebird;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import com.google.common.collect.Sets;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
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

public class EntityLovebird extends EntityShoulderRiding implements IAnimatable, EntityFlying {

    private AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityLovebird.class, DataSerializers.VARINT);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntityLovebird.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> WANDERING = EntityDataManager.createKey(EntityLovebird.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> GENDER = EntityDataManager.<Integer>createKey(EntityLovebird.class, DataSerializers.VARINT);
    private static final Item DEADLY_ITEM = Items.COOKIE;
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;

    public EntityLovebird(World worldIn)
    {
        super(worldIn);
        this.setSize(0.8F, 0.8F);
        this.moveHelper = new EntityFlyHelper(this);
    }

    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        if (CreaturesConfig.birdsFollow == true) {
        this.tasks.addTask(2, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F)); }
        this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        //this.targetTasks.addTask(6, new EntityAITargetNonTamed(this, EntityChicken.class, false, (Predicate)null));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lovebird.walking", true));
            return PlayState.CONTINUE;
        } if (!this.onGround || this.isFlying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("flying", true));
            return PlayState.CONTINUE;
        } if (this.isSleeping()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lovebird.sleep", true));
        return PlayState.CONTINUE;
    } if (this.isSitting()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", true));
        return PlayState.CONTINUE;
    }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lovebird.idle", true));
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
        return LootTableHandler.PARROT;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(getWildVariant());
        this.setGender(this.rand.nextInt(3));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
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
        else if (itemstack.getItem() == DEADLY_ITEM)
        {
            if (!player.capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }

            this.addPotionEffect(new PotionEffect(MobEffects.POISON, 900));

            if (player.isCreative() || !this.getIsInvulnerable())
            {
                this.attackEntityFrom(DamageSource.causePlayerDamage(player), Float.MAX_VALUE);
            }

            return true;
        }
        else if (itemstack.getItem() == Items.BOOK)
        {
            if (this.getGender() == 1) {
                Minecraft mc = Minecraft.getMinecraft();
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.male"));
                }

            }
            else {
                Minecraft mc = Minecraft.getMinecraft();
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.female"));
                }
            }
            Minecraft mc = Minecraft.getMinecraft();
            if (this.getVariant() == 1) {
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.lovebird.fischers"));}
            } else if (this.getVariant() == 2) {
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.lovebird.fischersmutation"));}
            } else if (this.getVariant() == 3) {
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.lovebird.masked"));}
            }
            else if (this.getVariant() == 4) {
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.lovebird.maskedmutation"));}
            } else if (this.getVariant() == 5) {
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.lovebird.peach"));}
            } else if (this.getVariant() == 6) {
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.lovebird.madagascar"));}
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

    public boolean isBreedingItem(ItemStack stack)
    {
        return TAME_ITEMS.contains(stack.getItem());
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
        else if (!(otherAnimal instanceof EntityLovebird))
        {
            return false;
        }
        else
        {
            EntityLovebird entitylovebird = (EntityLovebird)otherAnimal;
            if (this.getGender() == entitylovebird.getGender()) {
                return false;
            } else {
            return this.isInLove() && entitylovebird.isInLove();
            }
        }


    }

    @Nullable
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        EntityLovebird entitylovebird = new EntityLovebird(this.world);
        int i = this.rand.nextInt(CreaturesConfig.mutationLovebird);
        if (this.getVariant() == 1) {
            if (i == 1) {
                entitylovebird.setVariant(2);
            } else {
                entitylovebird.setVariant(this.getVariant());
            }
        }
        else if (this.getVariant() == 3) {
            if (i == 1) {
                entitylovebird.setVariant(4);
            } else {
                entitylovebird.setVariant(this.getVariant());
            }
        }
        else {
        entitylovebird.setVariant(this.getVariant());
        }
        int j = this.rand.nextInt(2);
        if (j == 0) {
            entitylovebird.setGender(1);
        } else {
            entitylovebird.setGender(2);
        }

        return entitylovebird;
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

            return super.attackEntityFrom(source, amount);
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
        this.dataManager.register(VARIANT, Integer.valueOf(0));
        this.dataManager.register(SLEEPING, Boolean.valueOf(false));
        this.dataManager.register(WANDERING, Boolean.valueOf(false));
        this.dataManager.register(GENDER, Integer.valueOf(0));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
        compound.setBoolean("Sleeping", this.isSleeping());
        compound.setBoolean("Wandering", this.isWandering());
        compound.setInteger("Gender", this.getGender());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setWandering(compound.getBoolean("Wandering"));
        this.setGender(compound.getInteger("Gender"));
    }

    public SoundEvent getAmbientSound()
    {
        if (!this.isSleeping()) {

        return SoundsHandler.LOVEBIRD_AMBIENT;
        } else {
            return null;
        }
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
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

    public void setWandering(boolean value) {
        this.dataManager.set(WANDERING, Boolean.valueOf(value));
    }

    public boolean isWandering() {
        return this.dataManager.get(WANDERING);
    }

    public int getWildVariant() {
        int e = this.rand.nextInt(101);

        if (e < 25) {
            return 1;
        } else if (e < 50) {
            return 3;
        } else if (e < 75) {
            return 5;
        } else {
            return 6;
        }

    }


}
