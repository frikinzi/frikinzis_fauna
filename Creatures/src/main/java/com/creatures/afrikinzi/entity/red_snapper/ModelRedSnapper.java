package com.creatures.afrikinzi.entity.red_snapper;

import com.creatures.afrikinzi.entity.pike.EntityPike;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelRedSnapper extends AnimatedGeoModel<EntityRedSnapper>  {
    @Override
    public ResourceLocation getModelLocation(EntityRedSnapper object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/red_snapper/red_snapper.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityRedSnapper object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/red_snapper/redsnapper.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityRedSnapper object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.redsnapper.json");
    }
}
