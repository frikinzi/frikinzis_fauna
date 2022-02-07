package com.creatures.afrikinzi.entity.wild_duck;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelWildDuck extends AnimatedGeoModel<EntityWildDuck> {
    @Override
    public ResourceLocation getModelLocation(EntityWildDuck object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/wildduck/wild_duck.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityWildDuck object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/wildduck/wildduckling" + object.getVariant() + ".png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/wildduck/duck" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityWildDuck object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.mandarin_duckling.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/wild_duck.animation.json");
    }
}
