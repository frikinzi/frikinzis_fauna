package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GoldenEagleEntity;
import com.frikinzi.creatures.entity.RedKiteEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GoldenEagleModel extends GeoModel<GoldenEagleEntity> {
    @Override
    public ResourceLocation getModelResource(GoldenEagleEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_raptor/babyraptor.geo.json");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "geo/entity/golden_eagle/golden_eaglefly.geo.json");
            }
            return new ResourceLocation(Creatures.MODID, "geo/entity/golden_eagle/golden_eagle.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(GoldenEagleEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/baby_raptor/goldeneagleb.png");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/golden_eagle/golden_eaglefly.png");
            } else if (object.isSleeping() && !object.isBaby()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/golden_eagle/golden_eaglesleep.png");
            } else {
                return new ResourceLocation(Creatures.MODID, "textures/entity/golden_eagle/golden_eagle.png");
            }
        }
    }

    @Override
    public ResourceLocation getAnimationResource(GoldenEagleEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babyraptor.json");
        } else {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.golden_eagle.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.golden_eagle.json");
    }
    }
}
