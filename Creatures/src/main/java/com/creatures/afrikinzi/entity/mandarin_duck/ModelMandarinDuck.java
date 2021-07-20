package com.creatures.afrikinzi.entity.mandarin_duck;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelMandarinDuck extends AnimatedGeoModel<EntityMandarinDuck> {
    @Override
    public ResourceLocation getModelLocation(EntityMandarinDuck object) {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/mandarin_duck/mandarin_duckling.geo.json");
        } else {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/mandarin_duck/mandarin_duck.geo.json");
        }
    }

    public ResourceLocation getTextureLocation(EntityMandarinDuck object) {
        if (object.isChild()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/mandarin_duck/mandarin_ducklingsleep.png");
            }
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/mandarin_duck/mandarin_duckling.png");
        } else {
            if (object.isSleeping()) {
                return new ResourceLocation(Reference.MOD_ID, "textures/entity/mandarin_duck/mandarin_duck_" + object.getVariant() + "_sleep.png");
            }
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/mandarin_duck/mandarin_duck_" + object.getVariant() + ".png");
        }
    }

    public ResourceLocation getAnimationFileLocation(EntityMandarinDuck object) {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.mandarin_duckling.json");
        } else {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.mandarin_duck.json");
        }
    }

}
