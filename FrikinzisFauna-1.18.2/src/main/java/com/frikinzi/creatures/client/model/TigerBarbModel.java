package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.RedSnapperEntity;
import com.frikinzi.creatures.entity.TigerBarbEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class TigerBarbModel extends AnimatedTickingGeoModel<TigerBarbEntity> {

    @Override
    public ResourceLocation getModelLocation(TigerBarbEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/tigerbarb/tigerbarb.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TigerBarbEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/tigerbarb/tigerbarb" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TigerBarbEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tigerbarb.json");
    }

}
