package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CrestedPenguinEntity;
import com.frikinzi.creatures.entity.YellowEyedPenguinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class YellowEyedPenguinModel extends GeoModel<YellowEyedPenguinEntity> {
    @Override
    public ResourceLocation getModelResource(YellowEyedPenguinEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/yelloweyedpenguin/yelloweyedpenguinbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/yelloweyedpenguin/yelloweyedpenguin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(YellowEyedPenguinEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/yelloweyedpenguin/yelloweyedpenguinbaby1sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/yelloweyedpenguin/yelloweyedpenguinbaby1.png");

        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/yelloweyedpenguin/yelloweyedpenguin1sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/yelloweyedpenguin/yelloweyedpenguin1.png");

    }

    @Override
    public ResourceLocation getAnimationResource(YellowEyedPenguinEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.yelloweyedpenguinbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.yelloweyedpenguin.json");

    }
}
