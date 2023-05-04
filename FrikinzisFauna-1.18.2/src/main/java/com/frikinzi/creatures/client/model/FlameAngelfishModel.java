package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BlueTangEntity;
import com.frikinzi.creatures.entity.FlameAngelfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class FlameAngelfishModel extends AnimatedTickingGeoModel<FlameAngelfishEntity> {

    @Override
    public ResourceLocation getModelLocation(FlameAngelfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/flame_angelfish/flame_angelfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FlameAngelfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/flame_angelfish/flame_angelfish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FlameAngelfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }

}
