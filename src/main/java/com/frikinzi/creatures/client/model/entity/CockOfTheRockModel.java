package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CockOfTheRockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CockOfTheRockModel extends GeoModel<CockOfTheRockEntity> {
    @Override
    public ResourceLocation getModelResource(CockOfTheRockEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/cockoftherock/cockoftherockbaby.geo.json");
        }
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/cockoftherock/cockoftherockfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/cockoftherock/cockoftherock.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CockOfTheRockEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/cockoftherock/cockoftherockbabysleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/cockoftherock/cockoftherockbaby.png");

        }
        if (object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/cockoftherock/loudbird" + object.getVariant() + object.getGenderName() + "_" + object.getSubVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/cockoftherock/loudbird" + object.getVariant() + object.getGenderName() + "_" + object.getSubVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/cockoftherock/loudbird" + object.getVariant() + object.getGenderName() + "_" + object.getSubVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(CockOfTheRockEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.cockoftherockbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.cockoftherock.json");
    }
}
