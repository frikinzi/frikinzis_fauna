package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.MandarinDuckEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MandarinDuckModel extends AnimatedGeoModel<MandarinDuckEntity> {
    @Override
    public ResourceLocation getModelLocation(MandarinDuckEntity object) {
        if (object.isBaby()) {
                return new ResourceLocation(Creatures.MODID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/mandarin_duck/mandarin_duck.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MandarinDuckEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/mandarin_duck/mandarin_ducklingsleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/mandarin_duck/mandarin_duckling.png");
        } else {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/mandarin_duck/mandarinduck" + object.getGender() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/mandarin_duck/mandarinduck" + object.getGender() + ".png");
    }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MandarinDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.mandarin_duckling.json");
        } else {
            return new ResourceLocation(Creatures.MODID, "animations/mandarin_duck.animation.json");
        }
    }
}
