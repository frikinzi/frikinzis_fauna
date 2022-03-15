package com.creatures.afrikinzi.entity.mandarin_duck;

import com.creatures.afrikinzi.entity.AbstractCreaturesNonTameable;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import com.creatures.afrikinzi.util.handlers.SoundsHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
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
import java.util.List;

public class EntityMandarinDuck extends AbstractCreaturesNonTameable implements IAnimatable, ICreaturesEntity {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityMandarinDuck.class, DataSerializers.VARINT);
    protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntityMandarinDuck.class, DataSerializers.BOOLEAN);
    private EntityAITempt aiTempt;

    public EntityMandarinDuck(World worldIn)
    {
        super(worldIn);
        this.setSize(0.7F, 0.8F);
        this.setPathPriority(PathNodeType.WATER, 1.0F);
    }

    protected void initEntityAI()
    {
        this.aiTempt = new EntityAITempt(this, 0.6D, Items.BREAD, false);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, this.aiTempt);
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(10, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.isChild()) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", true));
            return PlayState.CONTINUE;
        } if (!this.onGround && !this.isInWater()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("flying", true));
        return PlayState.CONTINUE;
    } if (this.isSleeping()) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("sleep", true));
        return PlayState.CONTINUE;
    }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
        }
        else {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
                return PlayState.CONTINUE;
            } if (!this.onGround && !this.isInWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
                return PlayState.CONTINUE;
            } if (this.isInWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
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

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(this.rand.nextInt(3));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 1, 3);
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


    public EntityMandarinDuck createChild(EntityAgeable ageable)
    {
        EntityMandarinDuck entitymandarinduck = new EntityMandarinDuck(this.world);
        entitymandarinduck.setGender(this.rand.nextInt(2));

        return entitymandarinduck;
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Items.BREAD;
    }

    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityMandarinDuck))
        {
            return false;
        }
        else
        {
            EntityMandarinDuck entitymandarinduck = (EntityMandarinDuck)otherAnimal;
            if (entitymandarinduck.getVariant() == this.getVariant()) {
                return false;
            } else {
                return this.isInLove() && entitymandarinduck.isInLove();
            }
        }


    }

    public SoundEvent getAmbientSound()
    {
        if (this.isSleeping()) {
            return null;
        }
        return SoundsHandler.MANDARIN_DUCK_AMBIENT;
    }

    public void setSleeping(boolean value) {
        this.dataManager.set(SLEEPING, Boolean.valueOf(value));
    }

    public boolean isSleeping() {
        return this.dataManager.get(SLEEPING);
    }

    public void onLivingUpdate()
    {
        if (this.onGround) {
            setSleeping(world.getWorldTime() >= 12000 && world.getWorldTime() <= 23000);
        }
        if (this.shouldSleep() == false) {
            setSleeping(false);
        }
        super.onLivingUpdate();
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

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.DUCK;
    }

    public String getSpeciesName() {
        String s1 = I18n.format("entity.mandarin_duck.name");
        return s1;
    }

    public String getFoodName() {
        return net.minecraft.util.text.translation.I18n.translateToLocal(Items.BREAD.getUnlocalizedName() + ".name").trim();
    }

}
