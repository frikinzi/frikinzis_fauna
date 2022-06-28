package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LapwingEntity;
import com.frikinzi.creatures.entity.PelicanEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LapwingModel extends AnimatedGeoModel<LapwingEntity> {
    @Override
    public ResourceLocation getModelLocation(LapwingEntity object)
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
    public ResourceLocation getTextureLocation(LapwingEntity object)
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
    public ResourceLocation getAnimationFileLocation(LapwingEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babylapwing.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.lapwing.json");
    }
}
