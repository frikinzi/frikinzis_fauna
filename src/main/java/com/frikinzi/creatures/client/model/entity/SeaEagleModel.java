package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.SeaEagleEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeaEagleModel extends GeoModel<SeaEagleEntity> {
    @Override
    public ResourceLocation getModelResource(SeaEagleEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_raptor/babyraptor.geo.json");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "geo/entity/stellers_sea_eagle/sea_eaglefly.geo.json");
            }
            return new ResourceLocation(Creatures.MODID, "geo/entity/stellers_sea_eagle/sea_eagle.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(SeaEagleEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/baby_raptor/seaeaglebaby" + object.getVariant() + ".png");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/stellers_sea_eagle/seaeagle" + object.getVariant()+ "fly.png");
            } else if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/stellers_sea_eagle/seaeagle" + object.getVariant()+ "sleep.png");
            } else {
                return new ResourceLocation(Creatures.MODID, "textures/entity/stellers_sea_eagle/seaeagle" + object.getVariant()+ ".png");
            }
        }
    }

    @Override
    public ResourceLocation getAnimationResource(SeaEagleEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babyraptor.json");
        } else {
            return new ResourceLocation(Creatures.MODID, "animations/animation.sea_eagle.json");
        }
    }
}
