package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.DoveEntity;
import com.frikinzi.creatures.entity.FairywrenEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FairywrenModel extends GeoModel<FairywrenEntity> {
    @Override
    public ResourceLocation getModelResource(FairywrenEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/fairy_wren/fairywrenfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/fairy_wren/fairywren.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FairywrenEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/fairy_wren/wren" + object.getVariant() + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/fairy_wren/wren" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(FairywrenEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.fairywren.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.fairywren.json");
    }
}
