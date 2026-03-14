package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DoveEntity;
import com.frikinzi.creatures.entity.IbisEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class IbisModel extends GeoModel<IbisEntity> {
    @Override
    public ResourceLocation getModelResource(IbisEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/ibis/ibis_baby.geo.json");

        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/ibis/ibisfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/ibis/ibis.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IbisEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/ibis/ibisbaby" + object.getVariant() + "sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/ibis/ibisbaby" + object.getVariant() + ".png");

        }
        if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/ibis/ibis" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/ibis/ibis" + object.getVariant() + "sleep.png");
        }
        else {
                return new ResourceLocation(Creatures.MODID, "textures/entity/ibis/ibis" + object.getVariant() + ".png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(IbisEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.ibisbaby.json");

        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.ibis.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.ibis.json");
    }
}
