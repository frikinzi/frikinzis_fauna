package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DoveEntity;
import com.frikinzi.creatures.entity.IbisEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class IbisModel extends AnimatedGeoModel<IbisEntity> {
    @Override
    public ResourceLocation getModelLocation(IbisEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/ibis/ibisfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/ibis/ibis.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(IbisEntity object)
    {
        if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/ibis/ibis" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/ibis/ibis" + object.getVariant() + "sleep.png");
        }
        else {
                return new ResourceLocation(Creatures.MODID, "textures/entity/ibis/ibis" + object.getVariant() + ".png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(IbisEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.ibis.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.ibis.json");
    }
}
