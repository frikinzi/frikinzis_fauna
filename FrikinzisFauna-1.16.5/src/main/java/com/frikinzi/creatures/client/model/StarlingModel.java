package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ChickadeeEntity;
import com.frikinzi.creatures.entity.StarlingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StarlingModel extends AnimatedGeoModel<StarlingEntity> {
    @Override
    public ResourceLocation getModelLocation(StarlingEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/starling/starlingfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/starling/starling.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(StarlingEntity object)
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
    public ResourceLocation getAnimationFileLocation(StarlingEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.starling.json");
    }
}
