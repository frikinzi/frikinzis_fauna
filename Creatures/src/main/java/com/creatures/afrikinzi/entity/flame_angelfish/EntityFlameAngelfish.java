package com.creatures.afrikinzi.entity.flame_angelfish;

import com.creatures.afrikinzi.entity.FishBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityFlameAngelfish extends FishBase implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public EntityFlameAngelfish(World worldIn)
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
}
