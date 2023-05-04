package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArapaimaEntity;
import com.frikinzi.creatures.entity.FiddlerCrabEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class FiddlerCrabModel extends AnimatedTickingGeoModel<FiddlerCrabEntity> {

    @Override
    public ResourceLocation getModelLocation(FiddlerCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/fiddlercrab/fiddlercrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FiddlerCrabEntity object)
    {

        return new ResourceLocation(Creatures.MODID, "textures/entity/fiddlercrab/fiddlercrab" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FiddlerCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.fiddlercrab.json");
    }

}
