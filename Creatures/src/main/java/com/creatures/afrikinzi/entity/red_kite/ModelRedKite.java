package com.creatures.afrikinzi.entity.red_kite;

import com.creatures.afrikinzi.entity.gyrfalcon.EntityGyrfalcon;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelRedKite extends AnimatedGeoModel<EntityRedKite> {
    @Override
    public ResourceLocation getModelLocation(EntityRedKite object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/red_kite/red_kitefly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/red_kite/red_kite.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityRedKite object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/red_kite/redkitefly.png");
        } else if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/red_kite/redkitesleep.png");
        } else {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/red_kite/redkite.png");}
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityRedKite object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.red_kite.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.red_kite.json");
    }
}
