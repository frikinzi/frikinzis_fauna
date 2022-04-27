package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BlueTangEntity;
import com.frikinzi.creatures.entity.RedSnapperEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RedSnapperModel extends AnimatedGeoModel<RedSnapperEntity> {
    @Override
    public ResourceLocation getModelLocation(RedSnapperEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/red_snapper/red_snapper.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RedSnapperEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/red_snapper/redsnapper.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RedSnapperEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.redsnapper.json");
    }
}
