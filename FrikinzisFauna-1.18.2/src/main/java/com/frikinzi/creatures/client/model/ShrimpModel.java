package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArowanaEntity;
import com.frikinzi.creatures.entity.ShrimpEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ShrimpModel extends AnimatedTickingGeoModel<ShrimpEntity> {

    @Override
    public ResourceLocation getModelLocation(ShrimpEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/shrimp/shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ShrimpEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/shrimp/shrimp" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ShrimpEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.shrimp.json");
    }

}
