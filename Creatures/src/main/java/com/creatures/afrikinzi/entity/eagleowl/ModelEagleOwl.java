package com.creatures.afrikinzi.entity.eagleowl;

import com.creatures.afrikinzi.entity.ibis.EntityIbis;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelEagleOwl extends AnimatedGeoModel<EntityEagleOwl> {
    @Override
    public ResourceLocation getModelLocation(EntityEagleOwl object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/eagleowl/eagleowlfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/eagleowl/eagleowl.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntityEagleOwl object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + "fly.png");
        }
        else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/eagleowl/eagleowl" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityEagleOwl object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.eagleowl.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.eagleowl.json");
    }
}
