package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.FiddlerCrabEntity;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FiddlerCrabModel extends GeoModel<FiddlerCrabEntity> {
    @Override
    public ResourceLocation getModelResource(FiddlerCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/fiddlercrab/fiddlercrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FiddlerCrabEntity object)
    {

        return new ResourceLocation(Creatures.MODID, "textures/entity/fiddlercrab/fiddlercrab" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(FiddlerCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.fiddlercrab.json");
    }
}
