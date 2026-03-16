package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.FireGobyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FireGobyModel extends GeoModel<FireGobyEntity> {
    @Override
    public ResourceLocation getModelResource(FireGobyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/fire_goby/fire_goby.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FireGobyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/fire_goby/fire_goby.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FireGobyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }
}
