package com.creatures.afrikinzi.entity.swallow;

import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelSwallow extends AnimatedGeoModel<EntitySwallow> {
    @Override
    public ResourceLocation getModelLocation(EntitySwallow object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/swallow/swallowfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/swallow/swallow.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(EntitySwallow object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/swallow/swallow" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/swallow/swallow" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntitySwallow object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.swallowfly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.swallow.json");
    }
}
