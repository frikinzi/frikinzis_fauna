package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.TroutEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TroutModel extends AnimatedGeoModel<TroutEntity> {
    @Override
    public ResourceLocation getModelLocation(TroutEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/trout/trout.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TroutEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/trout/trout" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TroutEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }
}
