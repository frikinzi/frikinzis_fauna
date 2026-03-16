package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BarracudaEntity;
import com.frikinzi.creatures.entity.LungfishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LungfishModel extends GeoModel<LungfishEntity> {
    @Override
    public ResourceLocation getModelResource(LungfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/lungfish/lungfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LungfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/lungfish/lungfish" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(LungfishEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.lungfish.json");
    }
}
