package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DottybackEntity;
import com.frikinzi.creatures.entity.PikeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PikeModel extends GeoModel<PikeEntity> {
    @Override
    public ResourceLocation getModelResource(PikeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/pike/pike.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PikeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/pike/pike.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PikeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.pike.json");
    }
}
