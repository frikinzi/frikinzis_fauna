package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.KoiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KoiModel extends GeoModel<KoiEntity> {
    @Override
    public ResourceLocation getModelResource(KoiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/koi/koi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KoiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/koi/koi" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(KoiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.koi.swim.json");
    }
}
