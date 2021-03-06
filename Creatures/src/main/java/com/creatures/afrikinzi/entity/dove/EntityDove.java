package com.creatures.afrikinzi.entity.dove;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.entity.AbstractCreaturesTameable;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
import com.creatures.afrikinzi.entity.ai.EntityAIFollowOwnerCreatures;
import com.creatures.afrikinzi.entity.wild_duck.EntityWildDuck;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import com.google.common.collect.Sets;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Set;

public class EntityDove extends AbstractCreaturesTameable implements IAnimatable, EntityFlying, ICreaturesEntity {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Item LEARN_ITEM = Items.BOOK;
    private static Set<Item> TAME_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.APPLE, Items.MELON, Items.BEETROOT_SEEDS, Items.MELON_SEEDS);
    private static Set<Item> FRUIT_FOOD = Sets.newHashSet(Items.APPLE, Items.MELON);
    private static Set<Item> SEED_FOOD = Sets.newHashSet(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityDove.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.<Integer>createKey(EntityDove.class, DataSerializers.VARINT);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntityDove.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Boolean> WANDERING = EntityDataManager.createKey(EntityDove.class, DataSerializers.BOOLEAN);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;

    public EntityDove(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 1F);
        this.moveHelper = new EntityFlyHelper(this);
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(getRandomDoveType());
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        if (CreaturesConfig.birdsFollow == true) {
        this.tasks.addTask(2, new EntityAIFollowOwnerCreatures(this, 1.0D, 5.0F, 1.0F));
        }
        this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        //this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
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


    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.GENERIC_BIRD;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
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
            setSleeping(world.getWorldTime() >= 13000 && world.getWorldTime() <= 23000);
        }
        if (this.inWater || this.isInWater() || this.isBurning()) {
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
        else if (itemstack.getItem() == LEARN_ITEM)
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
                mc.player.sendMessage(new TextComponentTranslation("message.creatures.dove.jambu"));}
            } else if (this.getVariant() == 2) {
                if (this.world.isRemote) {
                mc.player.sendMessage(new TextComponentTranslation("message.creatures.dove.release"));}
            } else if (this.getVariant() == 3) {
                if (this.world.isRemote) {
                mc.player.sendMessage(new TextComponentTranslation("message.creatures.dove.rose"));}
            }
            else if (this.getVariant() == 4) {
                if (this.world.isRemote) {
                mc.player.sendMessage(new TextComponentTranslation("message.creatures.dove.rock"));}
            } else if (this.getVariant() == 5) {
                if (this.world.isRemote) {
                mc.player.sendMessage(new TextComponentTranslation("message.creatures.dove.flame"));}
            }
            else if (this.getVariant() == 6) {
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.dove.goldenheart"));}
            }
            else if (this.getVariant() == 7) {
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.dove.mbleeding"));}
            }
            else if (this.getVariant() == 8) {
                if (this.world.isRemote) {
                    mc.player.sendMessage(new TextComponentTranslation("message.creatures.dove.orangebellied"));}
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
        if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 7 || this.getVariant() == 6) {
        return SEED_FOOD.contains(stack.getItem()); }
        else {
        return FRUIT_FOOD.contains(stack.getItem());
        }
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
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityDove))
        {
            return false;
        }
        else
        {
            EntityDove entitywildduck = (EntityDove)otherAnimal;
            if (this.getGender() == entitywildduck.getGender()) {
                return false;
            }
            return this.isInLove() && entitywildduck.isInLove();
        }
    }

    @Nullable
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        EntityDove entitywildduck = new EntityDove(this.world);
        entitywildduck.setVariant(this.getVariant());
        entitywildduck.setGender(this.rand.nextInt(2));

        return entitywildduck;
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
        this.dataManager.register(VARIANT, Integer.valueOf(0));
        this.dataManager.register(GENDER, Integer.valueOf(0));
        this.dataManager.register(WANDERING, Boolean.valueOf(false));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sleeping", this.isSleeping());
        compound.setInteger("Variant", this.getVariant());
        compound.setInteger("Gender", this.getGender());
        compound.setBoolean("Sleeping", this.isWandering());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setVariant(compound.getInteger("Variant"));
        this.setGender(compound.getInteger("Gender"));
        this.setWandering(compound.getBoolean("Wandering"));
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

    public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 1, 9);
    }

    public void setVariant(int p_191997_1_)
    {
        this.dataManager.set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    public SoundEvent getAmbientSound() {
        if (!this.isSleeping()) {

            return SoundsHandler.DOVE_AMBIENT;
        } else {
            return null;
        }
    }

    public void setWandering(boolean value) {
        this.dataManager.set(WANDERING, Boolean.valueOf(value));
    }

    public boolean isWandering() {
        return this.dataManager.get(WANDERING);
    }

    public Set<Item> getTameItems() {
        if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 7 || this.getVariant() == 6) {
        TAME_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.BREAD);
        } else {
            TAME_ITEMS = Sets.newHashSet(Items.APPLE, Items.MELON, Items.CHORUS_FRUIT);
        }
        return TAME_ITEMS;
    }

    private int getRandomDoveType()
    {
        if (CreaturesConfig.biomeSpecificVariants == true) {
        Biome biome = this.world.getBiome(new BlockPos(this));
        int i = this.rand.nextInt(100);

        if (BiomeDictionary.getTypes(biome).contains(BiomeDictionary.Type.JUNGLE)) {
            if (i < 20) {
                return 1;
            } else if (i < 40) {
                return 3;
            } else if (i < 60) {
                return 7;
            } else if (i < 80) {
                return 8;
            } else {
                return 5;
            }
        } else {
            if (i < 33) {
                return 2;
            } else if (i < 66) {
                return 6;
            } else {
                return 4;
            }
        }
    }
        else {
            return this.rand.nextInt(9);
        }
    }

    public String getSpeciesName() {
        if (this.getVariant() == 1) {
            String s1 = I18n.format("message.creatures.dove.jambu");
            return s1;
        }
        else if (this.getVariant() == 2) {
            String s1 = I18n.format("message.creatures.dove.release");
            return s1;
        }
        else if (this.getVariant() == 3) {
            String s1 = I18n.format("message.creatures.dove.rose");
            return s1;
        }
        else if (this.getVariant() == 4) {
            String s1 = I18n.format("message.creatures.dove.rock");
            return s1;
        }
        else if (this.getVariant() == 5) {
            String s1 = I18n.format("message.creatures.dove.flame");
            return s1;
        }
        else if (this.getVariant() == 6) {
            String s1 = I18n.format("message.creatures.dove.goldenheart");
            return s1;
        }  else if (this.getVariant() == 7) {
            String s1 = I18n.format("message.creatures.dove.mbleeding");
            return s1;
        }  else if (this.getVariant() == 8) {
            String s1 = I18n.format("message.creatures.dove.orangebellied");
            return s1;
        }
        else {
            return "Unknown";
        }
    }

    public String getFoodName() {
        if (this.getVariant() == 2 || this.getVariant() == 4 || this.getVariant() == 7 || this.getVariant() == 6) {
        return net.minecraft.util.text.translation.I18n.translateToLocal(Items.WHEAT_SEEDS.getUnlocalizedName() + ".name").trim(); }
        else {
            return net.minecraft.util.text.translation.I18n.translateToLocal(Items.APPLE.getUnlocalizedName() + ".name").trim(); }
        }

}
