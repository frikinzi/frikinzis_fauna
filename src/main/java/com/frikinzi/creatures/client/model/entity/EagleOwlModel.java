package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.EagleOwlEntity;
import com.frikinzi.creatures.entity.RollerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EagleOwlModel extends GeoModel<EagleOwlEntity> {
    @Override
    public ResourceLocation getModelResource(EagleOwlEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/eagleowl/eagleowl_baby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/eagleowl/eagleowlfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/eagleowl/eagleowl.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EagleOwlEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + "_baby_sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + "_baby.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + "fly.png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(EagleOwlEntity object)
    {
        if (object.isFlying() && !object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.eagleowl.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.eagleowl.json");
    }
}
