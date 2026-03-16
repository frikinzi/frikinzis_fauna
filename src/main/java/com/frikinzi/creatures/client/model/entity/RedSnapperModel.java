package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BlueTangEntity;
import com.frikinzi.creatures.entity.RedSnapperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RedSnapperModel extends GeoModel<RedSnapperEntity> {
    @Override
    public ResourceLocation getModelResource(RedSnapperEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/red_snapper/red_snapper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RedSnapperEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/red_snapper/snapper" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RedSnapperEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.redsnapper.json");
    }
}
