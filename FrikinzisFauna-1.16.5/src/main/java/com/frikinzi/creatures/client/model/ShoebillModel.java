package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ShoebillEntity;
import com.frikinzi.creatures.entity.WildDuckEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShoebillModel extends AnimatedGeoModel<ShoebillEntity> {
    @Override
    public ResourceLocation getModelLocation(ShoebillEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/shoebill/shoebillbaby.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/shoebill/shoebill.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ShoebillEntity object)
    {
        if (object.isBaby()) {
            if (object.isSleeping()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/shoebill/shoebillbabysleep.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/shoebill/shoebillbaby.png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Creatures.MODID, "textures/entity/shoebill/shoebill" + object.getVariant() + "sleep.png");
    }
        return new ResourceLocation(Creatures.MODID, "textures/entity/shoebill/shoebill" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ShoebillEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.shoebillbaby.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.shoebill.json");
    }
}
