package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArapaimaEntity;
import com.frikinzi.creatures.entity.SeaDragonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeaDragonModel extends GeoModel<SeaDragonEntity> {
    @Override
    public ResourceLocation getModelResource(SeaDragonEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/seadragon/seadragon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeaDragonEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/seadragon/seadragon" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeaDragonEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.seadragon.json");
    }
}
