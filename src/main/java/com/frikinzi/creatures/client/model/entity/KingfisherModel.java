package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ChickadeeEntity;
import com.frikinzi.creatures.entity.KingfisherEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KingfisherModel extends GeoModel<KingfisherEntity> {
    @Override
    public ResourceLocation getModelResource(KingfisherEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/kingfisher/kingfisherfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/kingfisher/kingfisher.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KingfisherEntity object)
    {
        if (object.isFlying() || !object.onGround()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/kingfisher/kingfisher" + object.getVariant() + object.getGenderIndicator() + "fly.png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/kingfisher/kingfisher" + object.getVariant() + object.getGenderIndicator() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/kingfisher/kingfisher" + object.getVariant() + object.getGenderIndicator() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(KingfisherEntity object)
    {
        if (object.isFlying() || !object.onGround()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.kingfisher.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.kingfisher.json");
    }
}
