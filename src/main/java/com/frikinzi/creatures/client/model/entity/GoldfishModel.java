package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BarnOwlEntity;
import com.frikinzi.creatures.entity.GoldfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GoldfishModel extends GeoModel<GoldfishEntity> {
    @Override
    public ResourceLocation getModelResource(GoldfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/goldfish/goldfishn.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GoldfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/goldfish/goldfishn" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(GoldfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }
}
