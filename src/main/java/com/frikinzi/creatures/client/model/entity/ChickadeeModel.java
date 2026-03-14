package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ChickadeeEntity;
import com.frikinzi.creatures.entity.ConureEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChickadeeModel extends GeoModel<ChickadeeEntity> {
    @Override
    public ResourceLocation getModelResource(ChickadeeEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/chickadee/chickadeefly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/chickadee/chickadee.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ChickadeeEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/chickadee/chickadee" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/chickadee/chickadee" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ChickadeeEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.chickadeefly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.chickadee.json");
    }
}
