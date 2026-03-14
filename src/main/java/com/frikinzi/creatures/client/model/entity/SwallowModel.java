package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.FairywrenEntity;
import com.frikinzi.creatures.entity.SwallowEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SwallowModel extends GeoModel<SwallowEntity> {
    @Override
    public ResourceLocation getModelResource(SwallowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/swallow/swallowfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/swallow/swallow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SwallowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/swallow/swallow" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/swallow/swallow" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SwallowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.swallowfly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.swallow.json");
    }
}
