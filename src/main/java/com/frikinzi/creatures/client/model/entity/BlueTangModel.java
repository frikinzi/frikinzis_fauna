package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BlueTangEntity;
import com.frikinzi.creatures.entity.FireGobyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BlueTangModel extends GeoModel<BlueTangEntity> {
    @Override
    public ResourceLocation getModelResource(BlueTangEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/blue_tang/tang.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlueTangEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/blue_tang/tang" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlueTangEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tang.json");
    }
}
