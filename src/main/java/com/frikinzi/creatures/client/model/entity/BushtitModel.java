package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.SparrowEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BushtitModel extends GeoModel<BushtitEntity> {
    @Override
    public ResourceLocation getModelResource(BushtitEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/bushtit/bushtitfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/bushtit/bushtit.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BushtitEntity object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/bushtit/bushtit" + object.getVariant() + "sleep.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/bushtit/bushtit" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/bushtit/bushtit" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(BushtitEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.bushtit.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.bushtit.json");
    }
}
