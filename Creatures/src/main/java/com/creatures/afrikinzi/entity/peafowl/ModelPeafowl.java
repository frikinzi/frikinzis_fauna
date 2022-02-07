package com.creatures.afrikinzi.entity.peafowl;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelPeafowl extends AnimatedGeoModel<EntityPeafowl> {
    @Override
    public ResourceLocation getModelLocation(EntityPeafowl object)
    {
        if (object.getGender() == 0) {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/peafowl/peafowlf.geo.json"); }
        else {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/peafowl/peafowlm.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPeafowl object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/peafowl/peafowl" + object.getVariant() + object.getGenderName() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/peafowl/peafowl" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityPeafowl object)
    {
        if (object.getGender() == 1) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.peafowlm.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.peafowlf.json");
    }

}
