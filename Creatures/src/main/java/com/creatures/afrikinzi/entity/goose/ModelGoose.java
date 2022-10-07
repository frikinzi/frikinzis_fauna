package com.creatures.afrikinzi.entity.goose;

import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGoose extends AnimatedGeoModel<EntityGoose> {
    @Override
    public ResourceLocation getModelLocation(EntityGoose object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        if (!object.isChild() && object.isFlying() && !object.isInWater()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/goose/goosefly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/goose/goose.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGoose object)
    {
        if (object.isChild()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/goose/babygoose" + object.getVariant() + "sleep.png");
            }
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/goose/babygoose" + object.getVariant() + ".png");
        } else {
            if (object.isSleeping()) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/goose/goose" + object.getVariant() + "sleep.png");
            }
            if (!object.isChild() && object.isFlying() && !object.isInWater()) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/goose/goose" + object.getVariant() + "fly.png");
            }
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/goose/goose" + object.getVariant() + ".png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityGoose object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.mandarin_duckling.json");
        } if (!object.isChild() && object.isFlying() && !object.isInWater()) {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.goosefly.json");
    }
    else {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.goose.json");
    }
    }

}
