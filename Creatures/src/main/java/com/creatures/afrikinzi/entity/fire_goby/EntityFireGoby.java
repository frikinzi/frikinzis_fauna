package com.creatures.afrikinzi.entity.fire_goby;

import com.creatures.afrikinzi.entity.FishBase;
import com.creatures.afrikinzi.entity.ICreaturesEntity;
import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityFireGoby extends FishBase implements IAnimatable, ICreaturesEntity {
    private AnimationFactory factory = new AnimationFactory(this);

    public EntityFireGoby(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.7F);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    public String getSpeciesName() {
        String s1 = I18n.format("entity.fire_goby.name");
        return s1;
    }


    public EntityFireGoby createChild(EntityAgeable ageable)
    {
        EntityFireGoby entitypeafowl = new EntityFireGoby(this.world);
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
        else if (!(otherAnimal instanceof EntityFireGoby))
        {
            return false;
        }
        else
        {
            EntityFireGoby entitykakapo = (EntityFireGoby)otherAnimal;
            return this.isInLove() && entitykakapo.isInLove();
        }
    }

}
