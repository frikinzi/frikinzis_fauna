package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.MantisShrimpEntity;
import com.frikinzi.creatures.entity.ShrimpEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MantisShrimpModel extends GeoModel<MantisShrimpEntity> {
    @Override
    public ResourceLocation getModelResource(MantisShrimpEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/mantisshrimp/mantis_shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MantisShrimpEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/mantisshrimp/mantisshrimp" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(MantisShrimpEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.mantis_shrimp.json");
    }
}
