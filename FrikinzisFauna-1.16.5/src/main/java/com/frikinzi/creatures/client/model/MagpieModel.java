package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.MagpieEntity;
import com.frikinzi.creatures.entity.RobinEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MagpieModel extends AnimatedGeoModel<MagpieEntity> {
    @Override
    public ResourceLocation getModelLocation(MagpieEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/magpie/magpie_baby.geo.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/magpie/magpiefly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/magpie/magpie.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MagpieEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/magpie/magpie" + object.getVariant() + "_baby_sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/magpie/magpie" + object.getVariant() + "_baby.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/magpie/magpie" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/magpie/magpie" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MagpieEntity object)
    {
        if (object.isFlying() & !object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.magpie.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.magpie.json");
    }
}
