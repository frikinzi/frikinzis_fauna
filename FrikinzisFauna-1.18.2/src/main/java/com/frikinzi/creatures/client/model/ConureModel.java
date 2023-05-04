package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ConureEntity;
import com.frikinzi.creatures.entity.LorikeetEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class ConureModel extends AnimatedTickingGeoModel<ConureEntity> {

    @Override
    public ResourceLocation getModelLocation(ConureEntity object) {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/conure/conurefly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/conure/conure.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ConureEntity object) {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/conure/conure" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/conure/conure" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/conure/conure" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ConureEntity object) {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.conure.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.conure.json");
    }

}
