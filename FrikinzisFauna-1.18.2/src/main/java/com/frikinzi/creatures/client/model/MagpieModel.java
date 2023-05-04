package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.MagpieEntity;
import com.frikinzi.creatures.entity.SparrowEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class MagpieModel extends AnimatedTickingGeoModel<MagpieEntity> {

    @Override
    public ResourceLocation getModelLocation(MagpieEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/magpie/magpiefly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/magpie/magpie.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MagpieEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/magpie/magpie" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/magpie/magpie" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MagpieEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.magpie.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.magpie.json");
    }

}
