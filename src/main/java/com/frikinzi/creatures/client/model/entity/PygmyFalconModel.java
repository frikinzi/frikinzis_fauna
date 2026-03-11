package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GyrfalconEntity;
import com.frikinzi.creatures.entity.PygmyFalconEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PygmyFalconModel extends GeoModel<PygmyFalconEntity> {
    @Override
    public ResourceLocation getModelResource(PygmyFalconEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_raptor/babyraptor.geo.json");
        } else {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/pygmyfalcon/pygmyfalconfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/pygmyfalcon/pygmyfalcon.geo.json"); }
    }

    @Override
    public ResourceLocation getTextureResource(PygmyFalconEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/baby_raptor/pygmyfalconb.png");
        } else {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/pygmyfalcon/pygmyfalcon" + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/pygmyfalcon/pygmyfalcon" + object.getGenderName() + ".png"); }
    }

    @Override
    public ResourceLocation getAnimationResource(PygmyFalconEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.pygmyfalcon.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.pygmyfalcon.json");
    }
}
