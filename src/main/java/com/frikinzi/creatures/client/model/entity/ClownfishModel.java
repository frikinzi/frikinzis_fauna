package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BlueTangEntity;
import com.frikinzi.creatures.entity.ClownfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ClownfishModel extends GeoModel<ClownfishEntity> {
    @Override
    public ResourceLocation getModelResource(ClownfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/clownfish/clownfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ClownfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/clownfish/clownfish" + object.getVariant() + "_" + object.getSubVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(ClownfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.clownfish.json");
    }
}
