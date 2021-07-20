package com.creatures.afrikinzi.entity.guppy;

import com.creatures.afrikinzi.entity.arowana.EntityArowana;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGuppy extends AnimatedGeoModel<EntityGuppy> {

    @Override
    public ResourceLocation getModelLocation(EntityGuppy object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/guppy/guppy.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGuppy object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/guppy/guppy" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityGuppy object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.guppy.json");
    }

}
