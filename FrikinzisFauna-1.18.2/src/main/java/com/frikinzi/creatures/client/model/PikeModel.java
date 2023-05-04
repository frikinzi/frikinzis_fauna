package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.KoiEntity;
import com.frikinzi.creatures.entity.PikeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class PikeModel extends AnimatedTickingGeoModel<PikeEntity> {

    @Override
    public ResourceLocation getModelLocation(PikeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/pike/pike.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PikeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/pike/pike.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PikeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.pike.json");
    }

}
