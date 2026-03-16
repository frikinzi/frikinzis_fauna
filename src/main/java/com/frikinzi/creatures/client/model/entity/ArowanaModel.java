package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArowanaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ArowanaModel extends GeoModel<ArowanaEntity> {
    @Override
    public ResourceLocation getModelResource(ArowanaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/arowana/arowana.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArowanaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/arowana/arowana" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArowanaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.arowana.json");
    }
}
