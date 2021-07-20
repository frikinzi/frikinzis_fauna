package com.creatures.afrikinzi.entity.gourami;

import com.creatures.afrikinzi.entity.guppy.EntityGuppy;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGourami extends AnimatedGeoModel<EntityGourami> {

    @Override
    public ResourceLocation getModelLocation(EntityGourami object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/gourami/gourami.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGourami object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/gourami/gourami" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityGourami object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.gourami.json");
    }

}
