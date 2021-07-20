package com.creatures.afrikinzi.entity.dottyback;

import com.creatures.afrikinzi.entity.dottyback.EntityDottyback;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelDottyback extends AnimatedGeoModel<EntityDottyback> {
    @Override
    public ResourceLocation getModelLocation(EntityDottyback object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/dottyback/dottyback.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDottyback object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/dottyback/dottyback" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityDottyback object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.dottyback.swim.json");
    }

}
