package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LovebirdEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class SpoonbillModel extends AnimatedTickingGeoModel<SpoonbillEntity> {

    @Override
    public ResourceLocation getModelLocation(SpoonbillEntity object) {
        return new ResourceLocation(Creatures.MODID, "geo/entity/spoonbill/spoonbill.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpoonbillEntity object) {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpoonbillEntity object) {
        return new ResourceLocation(Creatures.MODID, "animations/animation.spoonbill.json");
    }

}