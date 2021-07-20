package com.creatures.afrikinzi.entity.pike;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelPike extends AnimatedGeoModel<EntityPike>  {
    @Override
    public ResourceLocation getModelLocation(EntityPike object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/pike/pike.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPike object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/pike/pike.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityPike object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.pike.json");
    }
}
