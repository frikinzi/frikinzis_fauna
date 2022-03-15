package com.creatures.afrikinzi.entity;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class AbstractCreaturesNonTameable extends EntityAnimal {
    private static final DataParameter<Integer> GENDER = EntityDataManager.<Integer>createKey(AbstractCreaturesNonTameable.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(AbstractCreaturesNonTameable.class, DataSerializers.VARINT);
    public AbstractCreaturesNonTameable(World worldIn)
    {
        super(worldIn);
        this.setSize(0.7F, 0.8F);
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setGender(this.rand.nextInt(2));
        return super.onInitialSpawn(difficulty, livingdata);
    }


    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(VARIANT, Integer.valueOf(0));
        this.dataManager.register(GENDER, Integer.valueOf(0));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
        compound.setInteger("Gender", this.getGender());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));
        this.setGender(compound.getInteger("Gender"));
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
        return this.dataManager.get(GENDER);
    }

    public void setGender(int p_191997_1_)
    {
        this.dataManager.set(GENDER, Integer.valueOf(p_191997_1_));
    }

    public String getGenderString() {
        if (this.getGender() == 1) {
            String s1 = I18n.format("gui.male");
            return s1;
        } else {
            String s1 = I18n.format("gui.female");
            return s1;
        }
    }

    public String getSpeciesName() {
        return "";
    }

    public String getFoodName() {
        return "";
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemInit.FF_GUIDE)
        {
            if (this.world.isRemote) {
                this.setObject();
                player.openGui(Creatures.instance, 1, this.world, (int) this.posX, (int) this.posY, (int) this.posZ);
            }
            return true;
        }
        else
        {

            return super.processInteract(player, hand);
        }
    }

    protected void setObject() {
        Creatures.CREATURES_OBJECT = this;
    }


}
