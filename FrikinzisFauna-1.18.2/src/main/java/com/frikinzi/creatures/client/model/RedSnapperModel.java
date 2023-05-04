package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.RedSnapperEntity;
import com.frikinzi.creatures.entity.TroutEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class RedSnapperModel extends AnimatedTickingGeoModel<RedSnapperEntity> {

    @Override
    public ResourceLocation getModelLocation(RedSnapperEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/red_snapper/red_snapper.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RedSnapperEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/red_snapper/snapper" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RedSnapperEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.redsnapper.json");
    }

}
