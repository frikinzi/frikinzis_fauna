package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.FireGobyEntity;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FireGobyModel extends AnimatedGeoModel<FireGobyEntity> {
    @Override
    public ResourceLocation getModelLocation(FireGobyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/fire_goby/fire_goby.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FireGobyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/fire_goby/fire_goby.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FireGobyEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }
}
