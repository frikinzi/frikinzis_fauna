package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.PygmyGooseEntity;
import com.frikinzi.creatures.entity.SwallowEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class SwallowModel extends AnimatedTickingGeoModel<SwallowEntity> {
    @Override
    public ResourceLocation getModelLocation(SwallowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/swallow/swallowfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/swallow/swallow.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(SwallowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/swallow/swallow" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/swallow/swallow" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SwallowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.swallowfly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.swallow.json");
    }

}
