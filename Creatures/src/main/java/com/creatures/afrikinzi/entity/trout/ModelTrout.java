package com.creatures.afrikinzi.entity.trout;

import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelTrout extends AnimatedGeoModel<EntityTrout> {
    @Override
    public ResourceLocation getModelLocation(EntityTrout object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/trout/trout.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityTrout object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/trout/trout" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityTrout object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.goldfish.json");
    }
}
