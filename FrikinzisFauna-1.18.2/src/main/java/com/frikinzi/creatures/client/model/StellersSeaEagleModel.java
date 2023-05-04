package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.RedKiteEntity;
import com.frikinzi.creatures.entity.StellersSeaEagleEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class StellersSeaEagleModel extends AnimatedTickingGeoModel<StellersSeaEagleEntity> {

    @Override
    public ResourceLocation getModelLocation(StellersSeaEagleEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_raptor/babyraptor.geo.json");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "geo/entity/stellers_sea_eagle/stellers_sea_eaglefly.geo.json");
            }
            return new ResourceLocation(Creatures.MODID, "geo/entity/stellers_sea_eagle/stellers_sea_eagle.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureLocation(StellersSeaEagleEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/baby_raptor/stellersseaeagleb.png");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/stellers_sea_eagle/stellers_sea_eaglefly.png");
            } else if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/stellers_sea_eagle/stellers_sea_eaglesleep.png");
            } else {
                return new ResourceLocation(Creatures.MODID, "textures/entity/stellers_sea_eagle/stellers_sea_eagle.png");
            }
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(StellersSeaEagleEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babyraptor.json");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "animations/animation.stellers_sea_eaglefly.json");
            }
            return new ResourceLocation(Creatures.MODID, "animations/animation.stellers_sea_eagle.json");
        }
    }

}
