package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.PygmyFalconEntity;
import com.frikinzi.creatures.entity.WildDuckEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class WildDuckModel extends AnimatedTickingGeoModel<WildDuckEntity> {

    @Override
    public ResourceLocation getModelLocation(WildDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/wildduck/wild_duck.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WildDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/wildduck/wildduckling" + object.getVariant() + ".png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/wildduck/duck" + object.getVariant() + object.getGenderString() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/wildduck/duck" + object.getVariant() + object.getGenderString() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WildDuckEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.mandarin_duckling.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/wild_duck.animation.json");
    }

}
