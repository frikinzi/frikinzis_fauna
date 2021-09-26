package com.creatures.afrikinzi.entity.goldfish;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelRanchu extends AnimatedGeoModel<EntityRanchuGoldfish> {

    @Override
    public ResourceLocation getModelLocation(EntityRanchuGoldfish object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/ranchu/goldfishl.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityRanchuGoldfish object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/ranchu/goldfishl" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityRanchuGoldfish object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.goldfish.json");
    }
}
