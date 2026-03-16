package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.TarantulaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TarantulaModel extends GeoModel<TarantulaEntity> {
    @Override
    public ResourceLocation getModelResource(TarantulaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/tarantula/tarantula.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TarantulaEntity object)
    {
        if (object.isBaby()) {
            if (object.getVariant() == 14) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/tarantula/tarantulababy2.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/tarantula/tarantulababy.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/tarantula/tarantula" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(TarantulaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tarantula.json");
    }
}
