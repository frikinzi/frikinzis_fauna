package com.creatures.afrikinzi.entity.pelican;

import com.creatures.afrikinzi.entity.pygmy_goose.EntityPygmyGoose;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelPelican extends AnimatedGeoModel<EntityPelican> {
    @Override
    public ResourceLocation getModelLocation(EntityPelican object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "geo/entity/pelican/pelicanfly.geo.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/pelican/pelican.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPelican object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/pelican/pelican" + object.getVariant() + "fly.png");
        }
        if (object.isSleeping()) {
            return new ResourceLocation(Reference.MOD_ID, "textures/entity/pelican/pelican" + object.getVariant() + "sleep.png");
        }
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/pelican/pelican" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityPelican object)
    {
        if (object.isFlying() || !object.onGround) {
            return new ResourceLocation(Reference.MOD_ID, "animations/animation.pelican.fly.json");
        }
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.pelican.json");
    }
}
