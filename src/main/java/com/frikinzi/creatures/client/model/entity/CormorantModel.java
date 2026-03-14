package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CormorantEntity;
import com.frikinzi.creatures.entity.SecretaryBirdEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CormorantModel extends GeoModel<CormorantEntity> {
    @Override
    public ResourceLocation getModelResource(CormorantEntity object) {
        return new ResourceLocation(Creatures.MODID, "geo/entity/cormorant/cormorant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CormorantEntity object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/cormorant/cormorant" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/cormorant/cormorant" + object.getVariant() + ".png");

    }

    @Override
    public ResourceLocation getAnimationResource(CormorantEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.cormorant.json");

    }
}
