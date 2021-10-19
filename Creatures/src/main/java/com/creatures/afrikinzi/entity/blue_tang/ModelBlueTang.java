package com.creatures.afrikinzi.entity.blue_tang;

import com.creatures.afrikinzi.entity.fire_goby.EntityFireGoby;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelBlueTang extends AnimatedGeoModel<EntityBlueTang> {
    @Override
    public ResourceLocation getModelLocation(EntityBlueTang object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/blue_tang/blue_tang.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBlueTang object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/blue_tang/blue_tang.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityBlueTang object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.goldfish.json");
    }
}
