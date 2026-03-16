package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BandedPenguinEntity;
import com.frikinzi.creatures.entity.CormorantEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BandedPenguinModel extends GeoModel<BandedPenguinEntity> {
    @Override
    public ResourceLocation getModelResource(BandedPenguinEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/bandedpenguin/bandedpenguinbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/bandedpenguin/bandedpenguin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BandedPenguinEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/bandedpenguin/bandedpenguinbaby" + object.getVariant() + "_1"+ "sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/bandedpenguin/bandedpenguinbaby" + object.getVariant() + "_1"+ ".png");

        }
        if (object.isInWater()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/bandedpenguin/bandedpenguin" + object.getVariant() + "_"+  object.getSubVariant()+  object.getGenderName() + "swim.png");

        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/bandedpenguin/bandedpenguin" + object.getVariant() + "_"+  object.getSubVariant()+  object.getGenderName() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/bandedpenguin/bandedpenguin" + object.getVariant() + "_"+  object.getSubVariant()+  object.getGenderName()  + ".png");

    }

    @Override
    public ResourceLocation getAnimationResource(BandedPenguinEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.bandedpenguinbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.bandedpenguin.json");

    }
}
