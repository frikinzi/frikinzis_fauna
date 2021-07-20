package com.creatures.afrikinzi.entity.shrimp;

import com.creatures.afrikinzi.entity.guppy.EntityGuppy;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelShrimp extends AnimatedGeoModel<EntityShrimp> {
    @Override
    public ResourceLocation getModelLocation(EntityShrimp object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/shrimp/shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityShrimp object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/shrimp/shrimp" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityShrimp object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.shrimp.json");
    }
}
