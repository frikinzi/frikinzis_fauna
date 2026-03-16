package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BrushTailedPenguinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BrushTailedPenguinModel extends GeoModel<BrushTailedPenguinEntity> {
    @Override
    public ResourceLocation getModelResource(BrushTailedPenguinEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/brushtailedpenguin/brushtailedpenguinbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/brushtailedpenguin/brushtailedpenguin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BrushTailedPenguinEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/brushtailedpenguin/brushtailedpenguinbaby" + object.getVariant() + "_1" + "sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/brushtailedpenguin/brushtailedpenguinbaby" + object.getVariant() + "_1"+ ".png");

        }
        if (object.onIceBlock(object) && object.moving()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/brushtailedpenguin/brushtailedpenguin" + object.getVariant() + "_"+  object.getSubVariant() + "swim.png");

        }
        if (object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/brushtailedpenguin/brushtailedpenguin" + object.getVariant() + "_"+  object.getSubVariant() + "swim.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/brushtailedpenguin/brushtailedpenguin" + object.getVariant() + "_"+  object.getSubVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/brushtailedpenguin/brushtailedpenguin" + object.getVariant() + "_"+  object.getSubVariant()+ ".png");

    }

    @Override
    public ResourceLocation getAnimationResource(BrushTailedPenguinEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.brushtailedpenguinbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.brushtailedpenguin.json");

    }
}
