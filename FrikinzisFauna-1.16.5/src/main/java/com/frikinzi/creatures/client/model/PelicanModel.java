package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.PelicanEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PelicanModel extends AnimatedGeoModel<PelicanEntity> {
    @Override
    public ResourceLocation getModelLocation(PelicanEntity object)
    {
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/pelican/pelicanfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/pelican/pelican.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(PelicanEntity object)
    {
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/pelican/pelican" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/pelican/pelican" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/pelican/pelican" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PelicanEntity object)
    {
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.pelican.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.pelican.json");
    }
}
