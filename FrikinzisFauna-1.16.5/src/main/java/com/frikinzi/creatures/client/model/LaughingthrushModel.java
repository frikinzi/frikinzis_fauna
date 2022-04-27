package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LaughingthrushEntity;
import com.frikinzi.creatures.entity.SwallowEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LaughingthrushModel extends AnimatedGeoModel<LaughingthrushEntity> {
    @Override
    public ResourceLocation getModelLocation(LaughingthrushEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/laughingthrush/laughingthrushfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/laughingthrush/laughingthrush.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(LaughingthrushEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/laughingthrush/laughingthrush" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/laughingthrush/laughingthrush" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LaughingthrushEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.laughingthrush.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.laughingthrush.json");
    }
}
