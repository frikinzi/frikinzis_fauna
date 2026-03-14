package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.PelicanEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PelicanModel extends GeoModel<PelicanEntity> {
    @Override
    public ResourceLocation getModelResource(PelicanEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/pelican/pelican_baby.geo.json");
        }
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/pelican/pelicanfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/pelican/pelican.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PelicanEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/pelican/pelican" + object.getVariant() + "_baby_sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/pelican/pelican" + object.getVariant() + "_baby.png");
        }
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/pelican/pelican" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/pelican/pelican" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/pelican/pelican" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(PelicanEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babypelican.json");
        }
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.pelican.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.pelican.json");
    }
}
