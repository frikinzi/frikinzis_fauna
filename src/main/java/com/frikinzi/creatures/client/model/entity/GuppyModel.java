package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GuppyEntity;
import com.frikinzi.creatures.entity.KoiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GuppyModel extends GeoModel<GuppyEntity> {
    @Override
    public ResourceLocation getModelResource(GuppyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/guppy/guppy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GuppyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/guppy/guppy" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(GuppyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.guppy.json");
    }
}
