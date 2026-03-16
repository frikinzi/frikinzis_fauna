package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DottybackEntity;
import com.frikinzi.creatures.entity.TigerBarbEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TigerBarbModel extends GeoModel<TigerBarbEntity> {
    @Override
    public ResourceLocation getModelResource(TigerBarbEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/tigerbarb/tigerbarb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TigerBarbEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/tigerbarb/tigerbarb" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(TigerBarbEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tigerbarb.json");
    }
}
