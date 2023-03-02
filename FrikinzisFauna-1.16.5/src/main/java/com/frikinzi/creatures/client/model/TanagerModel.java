package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.TanagerEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TanagerModel extends AnimatedGeoModel<TanagerEntity> {
    @Override
    public ResourceLocation getModelLocation(TanagerEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/tanager/tanagerfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/tanager/tanager.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(TanagerEntity object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/tanager/tanager" + object.getVariant() + "sleep.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/tanager/tanager" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/tanager/tanager" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TanagerEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.tanager.json");
    }
}
