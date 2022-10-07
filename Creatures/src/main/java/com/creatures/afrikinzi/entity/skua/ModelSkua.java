package com.creatures.afrikinzi.entity.skua;

import com.creatures.afrikinzi.entity.sparrow.EntitySparrow;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelSkua extends AnimatedGeoModel<EntitySkua> {
    @Override
    public ResourceLocation getModelLocation(EntitySkua object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/skua/skuafly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/skua/skua.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySkua object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/skua/skua" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/skua/skua" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/skua/skua" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntitySkua object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.skua.json");
    }

}
