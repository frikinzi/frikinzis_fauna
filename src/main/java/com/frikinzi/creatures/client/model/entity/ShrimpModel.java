package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.KoiEntity;
import com.frikinzi.creatures.entity.ShrimpEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShrimpModel extends GeoModel<ShrimpEntity> {
    @Override
    public ResourceLocation getModelResource(ShrimpEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/shrimp/shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShrimpEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/shrimp/shrimp" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShrimpEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.shrimp.json");
    }
}
