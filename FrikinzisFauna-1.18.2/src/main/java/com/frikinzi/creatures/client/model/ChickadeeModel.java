package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ChickadeeEntity;
import com.frikinzi.creatures.entity.RollerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ChickadeeModel extends AnimatedTickingGeoModel<ChickadeeEntity> {
    @Override
    public ResourceLocation getModelLocation(ChickadeeEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/chickadee/chickadeefly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/chickadee/chickadee.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ChickadeeEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/chickadee/chickadee" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/chickadee/chickadee" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ChickadeeEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.chickadeefly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.chickadee.json");
    }

}
