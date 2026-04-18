package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CraneEntity;
import com.frikinzi.creatures.entity.MarabouEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CraneModel extends GeoModel<CraneEntity> {
    @Override
    public ResourceLocation getModelResource(CraneEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/crane/cranebaby.geo.json");

        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/crane/cranefly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/crane/crane.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CraneEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/crane/cranebabysleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/crane/cranebaby.png");

        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/crane/crane" + object.getVariant() +  "fly.png");

        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/crane/crane" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/crane/crane" + object.getVariant() + ".png");

    }

    @Override
    public ResourceLocation getAnimationResource(CraneEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.cranebaby.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.cranefly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.crane.json");

    }
}
