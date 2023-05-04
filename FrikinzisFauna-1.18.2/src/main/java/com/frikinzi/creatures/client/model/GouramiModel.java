package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GouramiEntity;
import com.frikinzi.creatures.entity.ShrimpEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class GouramiModel extends AnimatedTickingGeoModel<GouramiEntity> {

    @Override
    public ResourceLocation getModelLocation(GouramiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/gourami/gourami.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GouramiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/gourami/gourami" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GouramiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.gourami.json");
    }

}
