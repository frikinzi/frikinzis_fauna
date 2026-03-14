package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.WildDuckEntity;
import com.frikinzi.creatures.entity.WoodDuckEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WoodDuckModel extends GeoModel<WoodDuckEntity> {
    @Override
    public ResourceLocation getModelResource(WoodDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/woodduck/woodduck.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WoodDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/woodduck/wood_duckling.png");
        }
        else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/woodduck/woodduck" + object.getGenderName() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/woodduck/woodduck" + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(WoodDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.mandarin_duckling.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/woodduck.animation.json");
    }
}
