package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BlueTangEntity;
import com.frikinzi.creatures.entity.FireGobyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class BlueTangModel extends AnimatedTickingGeoModel<BlueTangEntity> {

    @Override
    public ResourceLocation getModelLocation(BlueTangEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/blue_tang/blue_tang.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BlueTangEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/blue_tang/blue_tang.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BlueTangEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }

}
