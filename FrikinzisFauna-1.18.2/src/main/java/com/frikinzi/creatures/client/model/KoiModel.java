package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.BarnOwlEntity;
import com.frikinzi.creatures.entity.KoiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class KoiModel extends AnimatedTickingGeoModel<KoiEntity> {

    @Override
    public ResourceLocation getModelLocation(KoiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/koi/koi.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KoiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/koi/koi" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KoiEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.koi.swim.json");
    }

}
