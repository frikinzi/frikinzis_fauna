package com.creatures.afrikinzi.entity.pygmyfalcon;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelPygmyFalcon extends AnimatedGeoModel<EntityPygmyFalcon> {
    @Override
    public ResourceLocation getModelLocation(EntityPygmyFalcon object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/baby_eagle/babyraptor.geo.json");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/pygmyfalcon/pygmyfalconfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/pygmyfalcon/pygmyfalcon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPygmyFalcon object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/baby_eagle/pygmyfalconb.png");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/pygmyfalcon/pygmyfalcon" + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/pygmyfalcon/pygmyfalcon" + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityPygmyFalcon object)
    {
        if (object.isChild()) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.babyraptor.json");
        }
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.pygmyfalcon.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.pygmyfalcon.json");
    }
}
