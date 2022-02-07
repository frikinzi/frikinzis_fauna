package com.creatures.afrikinzi.entity.wood_duck;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelWoodDuck extends AnimatedGeoModel<EntityWoodDuck> {
    @Override
    public ResourceLocation getModelLocation(EntityWoodDuck object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/wood_duck/woodduck.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityWoodDuck object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/wood_duck/wood_duckling.png");
        }
        else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/wood_duck/woodduck" + object.getGenderName() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/wood_duck/woodduck" + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityWoodDuck object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.mandarin_duckling.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/woodduck.animation.json");
    }
}
