package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.RanchuEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RanchuModel extends GeoModel<RanchuEntity> {
    @Override
    public ResourceLocation getModelResource(RanchuEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/ranchu/goldfishl.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RanchuEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/ranchu/goldfishl" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RanchuEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }
}
