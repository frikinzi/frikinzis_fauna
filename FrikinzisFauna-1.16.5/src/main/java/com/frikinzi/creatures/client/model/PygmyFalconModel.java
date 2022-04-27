package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GyrfalconEntity;
import com.frikinzi.creatures.entity.PygmyFalconEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PygmyFalconModel extends AnimatedGeoModel<PygmyFalconEntity> {
    @Override
    public ResourceLocation getModelLocation(PygmyFalconEntity object)
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
    public ResourceLocation getTextureLocation(PygmyFalconEntity object)
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
    public ResourceLocation getAnimationFileLocation(PygmyFalconEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.pygmyfalcon.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.pygmyfalcon.json");
    }
}
