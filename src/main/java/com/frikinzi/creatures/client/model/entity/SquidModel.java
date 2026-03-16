package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ElephantNoseFishEntity;
import com.frikinzi.creatures.entity.SquidEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SquidModel extends GeoModel<SquidEntity> {
    @Override
    public ResourceLocation getModelResource(SquidEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/squid/squidbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/squid/squid.geo.json");    }

    @Override
    public ResourceLocation getTextureResource(SquidEntity object)
    {
        if (object.isBaby()) {
            if (object.isReef()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/squid/squidbaby2.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/squid/squidbaby1.png");
        }
        int sub= object.getSubVariant();
        return new ResourceLocation(Creatures.MODID, "textures/entity/squid/squid" + object.getVariant() + "_" + sub + ".png");    }

    @Override
    public ResourceLocation getAnimationResource(SquidEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.squidbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.squid.json");
    }
}
