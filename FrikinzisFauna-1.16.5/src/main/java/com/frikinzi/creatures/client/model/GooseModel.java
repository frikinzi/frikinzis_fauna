package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GooseEntity;
import com.frikinzi.creatures.entity.MandarinDuckEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GooseModel extends AnimatedGeoModel<GooseEntity> {
    @Override
    public ResourceLocation getModelLocation(GooseEntity object) {
        if (object.isBaby()) {
                return new ResourceLocation(Creatures.MODID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        if (!object.isBaby() && object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/goose/goosefly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/goose/goose.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GooseEntity object)
    {
        if (object.isBaby() && !object.isInWater()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/goose/babygoose" + object.getVariant() + "sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/goose/babygoose" + object.getVariant() + ".png");
        } else {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/goose/goose" + object.getVariant() + "sleep.png");
        }
        if (!object.isBaby() && object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/goose/goose" + object.getVariant() + "fly.png");
            }
        return new ResourceLocation(Creatures.MODID, "textures/entity/goose/goose" + object.getVariant() + ".png");
    }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GooseEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.mandarin_duckling.json");
        } if (!object.isBaby() && object.isFlying() && !object.isInWater()) {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goosefly.json");
    }
        else {
            return new ResourceLocation(Creatures.MODID, "animations/animation.goose.json");
        }
    }
}
