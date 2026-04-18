package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.TetraEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TetraModel extends GeoModel<TetraEntity> {
    @Override
    public ResourceLocation getModelResource(TetraEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/tetra/tetra.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TetraEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/tetra/tetra" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(TetraEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tetra.json");
    }
}
