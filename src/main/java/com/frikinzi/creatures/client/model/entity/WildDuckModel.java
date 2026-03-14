package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GoldenEagleEntity;
import com.frikinzi.creatures.entity.WildDuckEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WildDuckModel extends GeoModel<WildDuckEntity> {
    @Override
    public ResourceLocation getModelResource(WildDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/wildduck/wild_duck.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WildDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/wildduck/wildduckling" + object.getVariant() + ".png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/wildduck/duck" + object.getVariant() + object.getGenderName() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/wildduck/duck" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(WildDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.mandarin_duckling.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/wild_duck.animation.json");
    }
}
