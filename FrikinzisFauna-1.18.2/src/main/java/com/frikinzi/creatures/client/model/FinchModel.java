package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.FinchEntity;
import com.frikinzi.creatures.entity.TanagerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class FinchModel extends AnimatedTickingGeoModel<FinchEntity> {

    @Override
    public ResourceLocation getModelLocation(FinchEntity object)
    {
        if (object.isFlying() || !object.isOnGround()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/finch/finchfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/finch/finch.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FinchEntity object)
    {
        if (object.isFlying() || !object.isOnGround()) {
            if (object.getVariant() == 7 || object.getVariant() == 2) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + object.getGenderString() + "fly.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            if (object.getVariant() == 7 || object.getVariant() == 2) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + object.getGenderString() + "sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + "sleep.png");
        }
        if (object.getVariant() == 7 || object.getVariant() == 2) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + object.getGenderString() + ".png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/finch/finch" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FinchEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.finch.json");
    }

}
