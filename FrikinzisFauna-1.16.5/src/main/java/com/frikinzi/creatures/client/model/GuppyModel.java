package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GuppyEntity;
import com.frikinzi.creatures.entity.KoiEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GuppyModel extends AnimatedGeoModel<GuppyEntity> {
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
