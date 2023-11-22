package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.MandarinDuckEntity;
import com.frikinzi.creatures.entity.SecretaryBirdEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SecretaryBirdModel extends AnimatedGeoModel<SecretaryBirdEntity> {
    @Override
    public ResourceLocation getModelLocation(SecretaryBirdEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/secretarybird/secretary_baby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/secretarybird/secretarybird.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SecretaryBirdEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/secretarybird/secretary_baby_sleep.png");

            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/secretarybird/secretary_baby.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/secretarybird/secretary" + object.getGenderName() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/secretarybird/secretary" + object.getGenderName() + ".png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(SecretaryBirdEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.secretary_baby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.secretarybird.json");

    }
}
