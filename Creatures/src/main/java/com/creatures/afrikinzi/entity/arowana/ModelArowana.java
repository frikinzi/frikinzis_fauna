package com.creatures.afrikinzi.entity.arowana;

import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelArowana extends AnimatedGeoModel<EntityArowana> {
    @Override
    public ResourceLocation getModelLocation(EntityArowana object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/arowana/arowana.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityArowana object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/arowana/arowana" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityArowana object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.arowana.json");
    }
}
