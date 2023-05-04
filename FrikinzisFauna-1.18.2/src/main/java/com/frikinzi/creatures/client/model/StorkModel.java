package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BushtitEntity;
import com.frikinzi.creatures.entity.StorkEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class StorkModel extends AnimatedTickingGeoModel<StorkEntity> {

    @Override
    public ResourceLocation getModelLocation(StorkEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/stork/stork" + object.getModelNumberFromVariant() + "fly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/stork/stork" + object.getModelNumberFromVariant() + ".geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(StorkEntity object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/stork/stork" + object.getModelNumberFromVariant()+ "_" + object.getVariant() + "sleep.png");
        }
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/stork/stork" + object.getModelNumberFromVariant()+ "_"  + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/stork/stork" + object.getModelNumberFromVariant() + "_"  + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(StorkEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.stork" + object.getModelNumberFromVariant() + ".json");
    }

}
