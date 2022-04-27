package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BarnOwlEntity;
import com.frikinzi.creatures.entity.GoldfishEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoldfishModel extends AnimatedGeoModel<GoldfishEntity> {
    @Override
    public ResourceLocation getModelLocation(GoldfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/goldfish/goldfishn.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GoldfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/goldfish/goldfishn" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GoldfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }
}
