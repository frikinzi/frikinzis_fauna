package com.creatures.afrikinzi.entity.bushtit;

import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelBushtit extends AnimatedGeoModel<EntityBushtit> {
    @Override
    public ResourceLocation getModelLocation(EntityBushtit object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/bushtit/bushtitfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/bushtit/bushtit.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntityBushtit object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/bushtit/bushtit" + object.getVariant() + "fly.png");
        } if (object.isSleeping()) {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/bushtit/bushtit" + object.getVariant() + "sleep.png");
    }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/bushtit/bushtit" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityBushtit object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.bushtit.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.bushtit.json");
    }
}
