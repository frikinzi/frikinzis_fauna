package com.creatures.afrikinzi.entity.creatures_spoonbill;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelCreaturesSpoonbill extends AnimatedGeoModel<EntityCreaturesSpoonbill> {
    @Override
    public ResourceLocation getModelLocation(EntityCreaturesSpoonbill object) {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/spoonbill/spoonbill.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCreaturesSpoonbill object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityCreaturesSpoonbill object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.spoonbill.json");
    }
}
