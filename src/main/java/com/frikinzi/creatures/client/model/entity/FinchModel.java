package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.FinchEntity;
import com.frikinzi.creatures.entity.LovebirdEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FinchModel extends GeoModel<FinchEntity> {
    @Override
    public ResourceLocation getModelResource(FinchEntity object)
    {
        if (object.isFlying() || !object.onGround()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/finch/finchfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/finch/finch.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FinchEntity object)
    {
        if (object.isFlying() || !object.onGround()) {
            if (object.isSexuallyDimorphic()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + object.getGenderName() + "fly.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            if (object.isSexuallyDimorphic()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + object.getGenderName() + "sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + "sleep.png");
        }
        if (object.isSexuallyDimorphic()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + object.getGenderName() + ".png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(FinchEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.finch.json");
    }
}
