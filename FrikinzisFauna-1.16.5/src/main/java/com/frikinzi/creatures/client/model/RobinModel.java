package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ChickadeeEntity;
import com.frikinzi.creatures.entity.RobinEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RobinModel extends AnimatedGeoModel<RobinEntity> {
    @Override
    public ResourceLocation getModelLocation(RobinEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/robin/robinfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/robin/robin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RobinEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/robin/robin" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/robin/robin" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RobinEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.robin.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.robin.json");
    }
}
