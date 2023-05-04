package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.KingfisherEntity;
import com.frikinzi.creatures.entity.OspreyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class KingfisherModel extends AnimatedTickingGeoModel<KingfisherEntity> {

    @Override
    public ResourceLocation getModelLocation(KingfisherEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/kingfisher/kingfisherfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/kingfisher/kingfisher.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KingfisherEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/kingfisher/kingfisher" + object.getVariant() + "fly.png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/kingfisher/kingfisher" + object.getVariant() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/kingfisher/kingfisher" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KingfisherEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.kingfisher.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.kingfisher.json");
    }

}
