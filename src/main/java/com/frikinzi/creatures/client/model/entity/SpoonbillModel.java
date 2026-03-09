package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SpoonbillModel extends GeoModel<SpoonbillEntity> {
    @Override
    public ResourceLocation getModelResource(SpoonbillEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/spoonbill/spoonbill_baby.geo.json");

        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/spoonbill/spoonbill.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpoonbillEntity object) {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbillbaby" + object.getVariant() + "sleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbillbaby" + object.getVariant() + ".png");

        }
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/spoonbill/spoonbill" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpoonbillEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.spoonbillbaby.json");

        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.spoonbill.json");
    }
}
