package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CrestedPenguinEntity;
import com.frikinzi.creatures.entity.LittlePenguinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LittlePenguinModel extends GeoModel<LittlePenguinEntity> {
    @Override
    public ResourceLocation getModelResource(LittlePenguinEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/littlepenguin/littlepenguinbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/littlepenguin/littlepenguin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LittlePenguinEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/littlepenguin/littlepenguinbaby" + object.getVariant() + "sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/littlepenguin/littlepenguinbaby" + object.getVariant() + ".png");

        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/littlepenguin/littlepenguin" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/littlepenguin/littlepenguin" + object.getVariant() + ".png");

    }

    @Override
    public ResourceLocation getAnimationResource(LittlePenguinEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.littlepenguinbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.littlepenguin.json");

    }
}
