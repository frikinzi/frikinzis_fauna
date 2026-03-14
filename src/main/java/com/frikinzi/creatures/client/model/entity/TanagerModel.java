package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.TanagerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TanagerModel extends GeoModel<TanagerEntity> {
    @Override
    public ResourceLocation getModelResource(TanagerEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/tanager/tanagerfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/tanager/tanager.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TanagerEntity object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/tanager/tanager" + object.getVariant() + "sleep.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/tanager/tanager" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/tanager/tanager" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(TanagerEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tanager.json");
    }
}
