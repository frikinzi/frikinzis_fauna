package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ChickadeeEntity;
import com.frikinzi.creatures.entity.PygmyGooseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class PygmyGooseModel extends AnimatedTickingGeoModel<PygmyGooseEntity> {
    @Override
    public ResourceLocation getModelLocation(PygmyGooseEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/pygmygoose/pygmygoose.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PygmyGooseEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/pygmygoose/pygmygooseduckling.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/pygmygoose/pygmygoose" + object.getVariant() + object.getGenderString() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/pygmygoose/pygmygoose" + object.getVariant() + object.getGenderString() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PygmyGooseEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.mandarin_duckling.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.pygmygoose.json");
    }

}
