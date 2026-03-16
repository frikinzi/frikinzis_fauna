package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.TroutEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TroutModel extends GeoModel<TroutEntity> {
    @Override
    public ResourceLocation getModelResource(TroutEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/trout/trout.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TroutEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/trout/trout" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(TroutEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }
}
