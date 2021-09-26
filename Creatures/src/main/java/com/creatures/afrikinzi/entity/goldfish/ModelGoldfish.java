package com.creatures.afrikinzi.entity.goldfish;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGoldfish extends AnimatedGeoModel<EntityGoldfish> {

    @Override
    public ResourceLocation getModelLocation(EntityGoldfish object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/goldfish/goldfishn.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGoldfish object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/goldfish/goldfishn" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityGoldfish object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.goldfish.json");
    }
}
