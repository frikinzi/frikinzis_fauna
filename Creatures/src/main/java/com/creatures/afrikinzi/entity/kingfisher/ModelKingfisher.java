package com.creatures.afrikinzi.entity.kingfisher;

import com.creatures.afrikinzi.entity.laughingthrush.EntityLaughingthrush;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelKingfisher extends AnimatedGeoModel<EntityKingfisher> {
    @Override
    public ResourceLocation getModelLocation(EntityKingfisher object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/kingfisher/kingfisherfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/kingfisher/kingfisher.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntityKingfisher object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/kingfisher/kingfisher" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/kingfisher/kingfisher" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/kingfisher/kingfisher" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityKingfisher object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.kingfisher.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.kingfisher.json");
    }
}
