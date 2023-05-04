package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ConureEntity;
import com.frikinzi.creatures.entity.GuppyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class GuppyModel extends AnimatedTickingGeoModel<GuppyEntity> {

    @Override
    public ResourceLocation getModelLocation(GuppyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/guppy/guppy.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GuppyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/guppy/guppy" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GuppyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.guppy.json");
    }

}
