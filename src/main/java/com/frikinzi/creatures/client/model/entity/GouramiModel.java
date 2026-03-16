package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GouramiEntity;
import com.frikinzi.creatures.entity.GuppyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GouramiModel extends GeoModel<GouramiEntity> {
    @Override
    public ResourceLocation getModelResource(GouramiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/gourami/gourami.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GouramiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/gourami/gourami" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(GouramiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.gourami.json");
    }
}
