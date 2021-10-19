package com.creatures.afrikinzi.entity.pygmy_goose;

import com.creatures.afrikinzi.entity.wild_duck.EntityWildDuck;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelPygmyGoose extends AnimatedGeoModel<EntityPygmyGoose> {
    @Override
    public ResourceLocation getModelLocation(EntityPygmyGoose object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/pygmygoose/pygmygoose.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPygmyGoose object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/pygmygoose/pygmygooseduckling.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/pygmygoose/pygmygoose" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityPygmyGoose object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.mandarin_duckling.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.pygmygoose.json");
    }
}
