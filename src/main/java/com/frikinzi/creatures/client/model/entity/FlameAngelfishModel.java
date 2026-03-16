package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.FireGobyEntity;
import com.frikinzi.creatures.entity.FlameAngelfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FlameAngelfishModel extends GeoModel<FlameAngelfishEntity> {
    @Override
    public ResourceLocation getModelResource(FlameAngelfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/flame_angelfish/flame_angelfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlameAngelfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/flame_angelfish/flame_angelfish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlameAngelfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.goldfish.json");
    }
}
