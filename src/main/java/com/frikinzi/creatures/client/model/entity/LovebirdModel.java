package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LovebirdEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LovebirdModel extends GeoModel<LovebirdEntity> {
    @Override
    public ResourceLocation getModelResource(LovebirdEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_parrot/parrotchick.geo.json");
        }
        if (object.isFlying() || !object.onGround()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/lovebird/lovebirdfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/lovebird/lovebird.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LovebirdEntity object) {
        if (object.isBaby()) {
            if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/baby/lovebirdbaby" + object.getVariant() + object.getGender() + ".png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/baby/lovebirdbaby" + object.getVariant() + ".png");
        }
        if (object.isFlying() || !object.onGround()) {
            if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + object.getGenderString() + "fly.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + object.getGenderString() + "sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + "sleep.png");
        }
        if (object.getVariant() == 6 || object.getVariant() == 7 || object.getVariant() == 8) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + object.getGenderString() + ".png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/lovebird/lovebird" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(LovebirdEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.parrotbaby.json");
        }
        if (object.isFlying() || !object.onGround()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.lovebird.flying.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.lovebird.json");
    }
}
