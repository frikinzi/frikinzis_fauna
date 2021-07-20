package com.creatures.afrikinzi.entity.spoonbill;

import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelSpoonbill extends AnimatedGeoModel<EntitySpoonbill> {
    @Override
    public ResourceLocation getModelLocation(EntitySpoonbill object) {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/spoonbill/spoonbill.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySpoonbill object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntitySpoonbill object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.spoonbill.json");
    }
}
