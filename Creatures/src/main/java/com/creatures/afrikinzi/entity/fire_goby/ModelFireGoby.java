package com.creatures.afrikinzi.entity.fire_goby;

import com.creatures.afrikinzi.entity.pike.EntityPike;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelFireGoby extends AnimatedGeoModel<EntityFireGoby> {
    @Override
    public ResourceLocation getModelLocation(EntityFireGoby object)
    {
        return new ResourceLocation(Reference.MOD_ID, "geo/entity/fire_goby/fire_goby.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFireGoby object)
    {
        return new ResourceLocation(Reference.MOD_ID, "textures/entity/fire_goby/fire_goby.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityFireGoby object)
    {
        return new ResourceLocation(Reference.MOD_ID, "animations/animation.goldfish.json");
    }
}
