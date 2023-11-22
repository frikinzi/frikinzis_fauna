package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ArowanaEntity;
import com.frikinzi.creatures.entity.HpapilioEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HpapilioModel extends AnimatedGeoModel<HpapilioEntity> {
    @Override
    public ResourceLocation getModelLocation(HpapilioEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/hpapilio/hpapilio.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HpapilioEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "textures/entity/hpapilio/hpapilio.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HpapilioEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.hpapilio.json");
    }
}
