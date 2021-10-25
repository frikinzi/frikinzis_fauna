package com.creatures.afrikinzi.entity.shrimp;

import com.creatures.afrikinzi.entity.ShrimpBase;
import com.creatures.afrikinzi.util.handlers.LootTableHandler;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
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

public class EntityShrimp extends ShrimpBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityShrimp.class, DataSerializers.VARINT);

    public EntityShrimp(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 0.7F);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (!this.inWater && !this.isInWater() && this.onGround) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("flop", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
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

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(this.rand.nextInt(9));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 1, 9);
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

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.SHRIMP;
    }
}
