package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DottybackEntity;
import com.frikinzi.creatures.entity.KoiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DottybackModel extends GeoModel<DottybackEntity> {
    @Override
    public ResourceLocation getModelResource(DottybackEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/dottyback/dottyback.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DottybackEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/dottyback/dottyback" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(DottybackEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.dottyback.swim.json");
    }
}
