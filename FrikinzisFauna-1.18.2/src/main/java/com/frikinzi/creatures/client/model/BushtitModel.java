package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.SparrowEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class BushtitModel extends AnimatedTickingGeoModel<BushtitEntity> {

    @Override
    public ResourceLocation getModelLocation(BushtitEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/bushtit/bushtitfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/bushtit/bushtit.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(BushtitEntity object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/bushtit/bushtit" + object.getVariant() + "sleep.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/bushtit/bushtit" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/bushtit/bushtit" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BushtitEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.bushtit.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.bushtit.json");
    }

}
