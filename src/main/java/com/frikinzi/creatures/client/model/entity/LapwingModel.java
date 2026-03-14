package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LapwingEntity;
import com.frikinzi.creatures.entity.PelicanEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LapwingModel extends GeoModel<LapwingEntity> {
    @Override
    public ResourceLocation getModelResource(LapwingEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/lapwing/lapwingbaby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/lapwing/lapwingfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/lapwing/lapwing.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LapwingEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lapwing/lapwingbaby" + object.getVariant() + ".png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lapwing/lapwing" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lapwing/lapwing" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/lapwing/lapwing" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(LapwingEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babylapwing.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.lapwing.json");
    }
}
