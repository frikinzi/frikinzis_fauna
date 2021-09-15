package com.creatures.afrikinzi.entity.pygmyfalcon;

import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelPygmyFalcon extends AnimatedGeoModel<EntityPygmyFalcon> {
    @Override
    public ResourceLocation getModelLocation(EntityPygmyFalcon object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/pygmyfalcon/pygmyfalconfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/pygmyfalcon/pygmyfalcon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPygmyFalcon object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/pygmyfalcon/pygmyfalcon" + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/pygmyfalcon/pygmyfalcon" + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityPygmyFalcon object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.pygmyfalcon.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.pygmyfalcon.json");
    }
}
