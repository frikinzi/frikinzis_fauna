package com.creatures.afrikinzi.entity.koi;

import com.creatures.afrikinzi.entity.FishBase;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
import com.creatures.afrikinzi.entity.peafowl.EntityPeafowl;
import com.creatures.afrikinzi.init.ItemInit;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
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


public class EntityKoi extends FishBase implements IAnimatable, ICreaturesEntity
{
    private AnimationFactory factory = new AnimationFactory(this);

    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityKoi.class, DataSerializers.VARINT);
    public EntityKoi(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 0.5F);
    }



    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.koi.swim", true));
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
        return LootTableHandler.KOI;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(this.rand.nextInt(11));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 1, 11);
    }

    public void setVariant(int p_191997_1_)
    {
        this.dataManager.set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(VARIANT, Integer.valueOf(0));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));
    }

    public String getSpeciesName() {
        String s1 = I18n.format("entity.koi.name");
        return s1;
    }


    public EntityKoi createChild(EntityAgeable ageable)
    {
        EntityKoi entitypeafowl = new EntityKoi(this.world);
        entitypeafowl.setVariant(this.getVariant());
        entitypeafowl.setGender(this.rand.nextInt(2));

        return entitypeafowl;
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == ItemInit.RAW_SHRIMP;
    }

    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityKoi))
        {
            return false;
        }
        else
        {
            EntityKoi entitykakapo = (EntityKoi)otherAnimal;
            return this.isInLove() && entitykakapo.isInLove();
        }
    }

    public String getFoodName() {
        return net.minecraft.util.text.translation.I18n.translateToLocal(ItemInit.RAW_SHRIMP.getUnlocalizedName() + ".name").trim();
    }

}

