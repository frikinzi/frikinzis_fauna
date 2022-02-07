package com.creatures.afrikinzi.entity.ibis;

import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelIbis extends AnimatedGeoModel<EntityIbis> {
    @Override
    public ResourceLocation getModelLocation(EntityIbis object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/ibis/ibisfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/ibis/ibis.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntityIbis object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/ibis/ibis" + object.getVariant() + "fly.png");
        }
        else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/ibis/ibis" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/ibis/ibis" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityIbis object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.ibis.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.ibis.json");
    }
}
