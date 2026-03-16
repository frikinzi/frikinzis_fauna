package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BandedPenguinEntity;
import com.frikinzi.creatures.entity.CrestedPenguinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CrestedPenguinModel extends GeoModel<CrestedPenguinEntity> {
    @Override
    public ResourceLocation getModelResource(CrestedPenguinEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/crestedpenguin/crestedpenguinbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/crestedpenguin/crestedpenguin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CrestedPenguinEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/crestedpenguin/crestedpenguinbaby1_1sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/crestedpenguin/crestedpenguinbaby1_1.png");

        }
        if (object.isInWater() && object.getSubVariant() != 2) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/crestedpenguin/crestedpenguin" + object.getVariant() + "swim.png");

        }
        if (object.isSleeping()) {
            if (object.getSubVariant() == 2) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/crestedpenguin/crestedpenguin" + object.getVariant() + "_"+  object.getSubVariant()+ "sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/crestedpenguin/crestedpenguin" + object.getVariant() + "sleep.png");
        }
        if (object.getSubVariant() == 2) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/crestedpenguin/crestedpenguin" + object.getVariant() + "_"+  object.getSubVariant()+ ".png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/crestedpenguin/crestedpenguin" + object.getVariant() + ".png");

    }

    @Override
    public ResourceLocation getAnimationResource(CrestedPenguinEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.bandedpenguinbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.crestedpenguin.json");

    }
}
