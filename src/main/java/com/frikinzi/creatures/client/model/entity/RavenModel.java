package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.RavenEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RavenModel extends GeoModel<RavenEntity> {
    @Override
    public ResourceLocation getModelResource(RavenEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/raven/raven_baby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/raven/ravenfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/raven/raven.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RavenEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/raven/raven" + object.getVariant() + "_baby_sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/raven/raven" + object.getVariant() + "_baby.png");

        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/raven/raven" + object.getVariant() + "fly.png");

        } else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/raven/raven" + object.getVariant() + "sleep.png");

        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/raven/raven" + object.getVariant() + ".png");

    }

    @Override
    public ResourceLocation getAnimationResource(RavenEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.raven_baby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.raven.json");
    }
}
