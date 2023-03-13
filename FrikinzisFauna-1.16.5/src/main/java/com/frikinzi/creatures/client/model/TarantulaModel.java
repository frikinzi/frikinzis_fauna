package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.TarantulaEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TarantulaModel extends AnimatedGeoModel<TarantulaEntity> {
    @Override
    public ResourceLocation getModelLocation(TarantulaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/tarantula/tarantula.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TarantulaEntity object)
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
    public ResourceLocation getAnimationFileLocation(TarantulaEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tarantula.json");
    }
}
