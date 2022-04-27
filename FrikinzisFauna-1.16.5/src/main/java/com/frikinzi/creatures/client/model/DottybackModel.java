package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DottybackEntity;
import com.frikinzi.creatures.entity.KoiEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DottybackModel extends AnimatedGeoModel<DottybackEntity> {
    @Override
    public ResourceLocation getModelLocation(DottybackEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/dottyback/dottyback.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DottybackEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/dottyback/dottyback" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DottybackEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.dottyback.swim.json");
    }
}
