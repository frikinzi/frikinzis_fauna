package com.creatures.afrikinzi.entity.sparrow;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.entity.AbstractCreaturesNonTameable;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
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
import net.minecraft.init.Blocks;
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

public class EntitySparrow extends AbstractCreaturesNonTameable implements IAnimatable, EntityFlying, ICreaturesEntity {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntitySparrow.class, DataSerializers.VARINT);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntitySparrow.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> GENDER = EntityDataManager.<Integer>createKey(EntitySparrow.class, DataSerializers.VARINT);
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;

    public EntitySparrow(World worldIn)
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
        this.tasks.addTask(2, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 6.0F, 1.0D, 1.2D));
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
        this.setVariant(getRandomSparrowType());
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
        else if (!(otherAnimal instanceof EntitySparrow))
        {
            return false;
        }
        else
        {
            EntitySparrow entitysparrow = (EntitySparrow)otherAnimal;
            if (this.getGender() == entitysparrow.getGender()) {
                return false;
            } else {
                return this.isInLove() && entitysparrow.isInLove();
            }
        }


    }

    @Nullable
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        EntitySparrow entitysparrow = new EntitySparrow(this.world);
        entitysparrow.setVariant(this.getVariant());
        int j = this.rand.nextInt(2);
        entitysparrow.setGender(this.rand.nextInt(2));

        return entitysparrow;
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

            return SoundsHandler.SPARROW_AMBIENT;
        } else {
            return null;
        }
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return TAME_ITEMS.contains(stack.getItem());
    }

    public String getGenderName() {
        if (this.getGender() == 1) {
            return "m";
        } else {
            return "f";
        }
    }

    private int getRandomSparrowType()
    {
        if (CreaturesConfig.biomeSpecificVariants == true) {
            Biome biome = this.world.getBiome(new BlockPos(this));
            int i = this.rand.nextInt(100);

            if (BiomeDictionary.getTypes(biome).contains(BiomeDictionary.Type.DRY)) {
                if (i < 50) {
                    return 2;
                } else {
                    return 3;
                }
            } else if ((BiomeDictionary.getTypes(biome).contains(BiomeDictionary.Type.JUNGLE))) {
                return 4;
            }
            else {
                if (i < 33) {
                    return 1;
                } else if (i < 66) {
                    return 5;
                } else {
                    return 6;
                }
            }
        }
        else {
            return this.rand.nextInt(7);
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        return this.world.getBlockState(blockpos.down()).getBlock() == Blocks.SAND || this.world.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS || this.world.getBlockState(blockpos.down()).getBlock() == Blocks.LEAVES && super.getCanSpawnHere();
    }

    public String getSpeciesName() {
        if (this.getVariant() == 1) {
            String s1 = I18n.format("message.creatures.eurasiantree");
            return s1;
        }
        else if (this.getVariant() == 2) {
            String s1 = I18n.format("message.creatures.sudangolden");
            return s1;
        }
        else if (this.getVariant() == 3) {
            String s1 = I18n.format("message.creatures.desertsparrow");
            return s1;
        }
        else if (this.getVariant() == 4) {
            String s1 = I18n.format("message.creatures.saffronbilled");
            return s1;
        }
        else if (this.getVariant() == 5) {
            String s1 = I18n.format("message.creatures.house");
            return s1;
        }
        else if (this.getVariant() == 6) {
            String s1 = I18n.format("message.creatures.chipping");
            return s1;
        } else {
            return "Unknown";
        }
    }

    public String getFoodName() {
        return net.minecraft.util.text.translation.I18n.translateToLocal(Items.WHEAT_SEEDS.getUnlocalizedName() + ".name").trim();
    }

}
