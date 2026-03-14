package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ChickadeeEntity;
import com.frikinzi.creatures.entity.StarlingEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StarlingModel extends GeoModel<StarlingEntity> {
    @Override
    public ResourceLocation getModelResource(StarlingEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/starling/starlingfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/starling/starling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StarlingEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/starling/starling" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/starling/starling" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/starling/starling" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(StarlingEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.starling.json");
    }
}
