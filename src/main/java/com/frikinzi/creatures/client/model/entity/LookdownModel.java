package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LookdownEntity;
import com.frikinzi.creatures.entity.RedSnapperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LookdownModel extends GeoModel<LookdownEntity> {
    @Override
    public ResourceLocation getModelResource(LookdownEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/lookdown/lookdownbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/lookdown/lookdown.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LookdownEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lookdown/lookdownbaby.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/lookdown/lookdown" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(LookdownEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.lookdownbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.lookdown.json");
    }
}
