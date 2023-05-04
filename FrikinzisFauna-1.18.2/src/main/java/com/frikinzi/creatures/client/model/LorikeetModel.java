package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LorikeetEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class LorikeetModel extends AnimatedTickingGeoModel<LorikeetEntity> {

    @Override
    public ResourceLocation getModelLocation(LorikeetEntity object) {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/lorikeet/lorikeetfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/lorikeet/lorikeet.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LorikeetEntity object) {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LorikeetEntity object) {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.lorikeet.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.lorikeet.json");
    }

}
