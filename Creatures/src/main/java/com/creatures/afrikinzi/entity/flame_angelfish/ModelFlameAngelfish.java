package com.creatures.afrikinzi.entity.flame_angelfish;

import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelFlameAngelfish extends AnimatedGeoModel<EntityFlameAngelfish> {
    @Override
    public ResourceLocation getModelLocation(EntityFlameAngelfish object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/flame_angelfish/flame_angelfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFlameAngelfish object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/flame_angelfish/flame_angelfish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityFlameAngelfish object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.goldfish.json");
    }
}
