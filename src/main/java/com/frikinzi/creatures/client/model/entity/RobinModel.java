package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ChickadeeEntity;
import com.frikinzi.creatures.entity.RobinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RobinModel extends GeoModel<RobinEntity> {
    @Override
    public ResourceLocation getModelResource(RobinEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/robin/robinfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/robin/robin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RobinEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/robin/robin" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/robin/robin" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RobinEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.robin.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.robin.json");
    }
}
