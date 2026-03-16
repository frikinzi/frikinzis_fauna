package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArapaimaEntity;
import com.frikinzi.creatures.entity.ArowanaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ArapaimaModel extends GeoModel<ArapaimaEntity> {
    @Override
    public ResourceLocation getModelResource(ArapaimaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/arapaima/arapaimafry.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/arapaima/arapaima.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArapaimaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/arapaima/arapaimafry.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/arapaima/arapaima" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArapaimaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.arapaimafry.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.arapaima.json");
    }
}
