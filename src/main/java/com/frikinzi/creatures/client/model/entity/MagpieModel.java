package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.MagpieEntity;
import com.frikinzi.creatures.entity.RobinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MagpieModel extends GeoModel<MagpieEntity> {
    @Override
    public ResourceLocation getModelResource(MagpieEntity object)
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
    public ResourceLocation getTextureResource(MagpieEntity object)
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
    public ResourceLocation getAnimationResource(MagpieEntity object)
    {
        if (object.isFlying() & !object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.magpie.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.magpie.json");
    }
}
