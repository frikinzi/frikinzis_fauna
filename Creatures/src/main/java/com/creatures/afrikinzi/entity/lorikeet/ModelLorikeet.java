package com.creatures.afrikinzi.entity.lorikeet;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelLorikeet extends AnimatedGeoModel<EntityLorikeet> {
    @Override
    public ResourceLocation getModelLocation(EntityLorikeet object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/lorikeet/lorikeetfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/lorikeet/lorikeet.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityLorikeet object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "fly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/lorikeet/lorikeet" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityLorikeet object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.lorikeet.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.lorikeet.json");
    }
}
