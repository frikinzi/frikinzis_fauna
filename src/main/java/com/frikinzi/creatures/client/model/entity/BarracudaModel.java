package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BarracudaEntity;
import com.frikinzi.creatures.entity.RanchuEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BarracudaModel extends GeoModel<BarracudaEntity> {
    @Override
    public ResourceLocation getModelResource(BarracudaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/barracuda/barracuda.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BarracudaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/barracuda/barracuda" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(BarracudaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.barracuda.json");
    }
}
