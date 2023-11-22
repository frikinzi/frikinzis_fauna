package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.WhistlingDuckEntity;
import com.frikinzi.creatures.entity.WildDuckEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WhistlingDuckModel extends AnimatedGeoModel<WhistlingDuckEntity> {
    @Override
    public ResourceLocation getModelLocation(WhistlingDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/whistlingduck/whistlingduck.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WhistlingDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/whistlingduck/whistlingduckling" + object.getVariant() + ".png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/whistlingduck/whistlingduck" + object.getVariant() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/whistlingduck/whistlingduck" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WhistlingDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.mandarin_duckling.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.whistlingduck.json");
    }
}
