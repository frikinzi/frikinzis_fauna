package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArapaimaEntity;
import com.frikinzi.creatures.entity.TrumpetfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TrumpetfishModel extends GeoModel<TrumpetfishEntity> {
    @Override
    public ResourceLocation getModelResource(TrumpetfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/trumpetfish/trumpetfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TrumpetfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/trumpetfish/trumpetfish_" + object.getVariant() + "_" + object.getSubVariant() +  ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(TrumpetfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.trumpetfish.json");
    }
}
