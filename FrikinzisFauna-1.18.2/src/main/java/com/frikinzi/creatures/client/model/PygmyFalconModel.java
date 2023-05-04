package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.PygmyFalconEntity;
import com.frikinzi.creatures.entity.RedKiteEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class PygmyFalconModel extends AnimatedTickingGeoModel<PygmyFalconEntity> {

    @Override
    public ResourceLocation getModelLocation(PygmyFalconEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/baby_raptor/babyraptor.geo.json");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "geo/entity/pygmyfalcon/pygmyfalconfly.geo.json");
            }
            return new ResourceLocation(Creatures.MODID, "geo/entity/pygmyfalcon/pygmyfalcon.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureLocation(PygmyFalconEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/baby_raptor/pygmyfalconb.png");
        } else {
            if (object.isFlying()) {
                return new ResourceLocation(Creatures.MODID, "textures/entity/pygmyfalcon/pygmyfalcon" + object.getGenderString() + "fly.png");
            }
            return new ResourceLocation(Creatures.MODID, "textures/entity/pygmyfalcon/pygmyfalcon" + object.getGenderString() + ".png"); }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PygmyFalconEntity object) {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.babyraptor.json");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.pygmyfalcon.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.pygmyfalcon.json");
    }

}
