package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GoldfishEntity;
import com.frikinzi.creatures.entity.RanchuEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class RanchuModel extends AnimatedTickingGeoModel<RanchuEntity> {

    @Override
    public ResourceLocation getModelLocation(RanchuEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/ranchu/goldfishl.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RanchuEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/ranchu/goldfishl" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RanchuEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }

}
