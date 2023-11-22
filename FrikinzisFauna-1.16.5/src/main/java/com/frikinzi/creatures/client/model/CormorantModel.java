package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.CormorantEntity;
import com.frikinzi.creatures.entity.SecretaryBirdEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CormorantModel extends AnimatedGeoModel<CormorantEntity> {
    @Override
    public ResourceLocation getModelLocation(CormorantEntity object) {
        return new ResourceLocation(Creatures.MODID, "geo/entity/cormorant/cormorant.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CormorantEntity object)
    {
        if (object.isSleeping()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/cormorant/cormorant" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/cormorant/cormorant" + object.getVariant() + ".png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(CormorantEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.cormorant.json");

    }
}
