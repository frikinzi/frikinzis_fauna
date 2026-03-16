package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.MonalEntity;
import com.frikinzi.creatures.entity.RailEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RailModel extends GeoModel<RailEntity> {
    @Override
    public ResourceLocation getModelResource(RailEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/lapwing/lapwingbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/rail/rail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RailEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/rail/railbaby.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/rail/rail" + object.getVariant() + "sleep.png");

        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/rail/rail" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RailEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babylapwing.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.rail.json");
    }
}
