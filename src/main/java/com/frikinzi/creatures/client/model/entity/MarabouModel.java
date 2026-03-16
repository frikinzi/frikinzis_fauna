package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BandedPenguinEntity;
import com.frikinzi.creatures.entity.MarabouEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MarabouModel extends GeoModel<MarabouEntity> {
    @Override
    public ResourceLocation getModelResource(MarabouEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/marabou/maraboubaby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/marabou/maraboufly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/marabou/marabou.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MarabouEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/marabou/marabou" + object.getVariant() + "babysleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/marabou/marabou" + object.getVariant() + "baby.png");

        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/marabou/marabou" + object.getVariant() + "_"+  object.getSubVariant()+  "fly.png");

        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/marabou/marabou" + object.getVariant() + "_"+  object.getSubVariant()+ "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/marabou/marabou" + object.getVariant() + "_"+  object.getSubVariant()+ ".png");

    }

    @Override
    public ResourceLocation getAnimationResource(MarabouEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.maraboubaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.marabou.json");

    }
}
