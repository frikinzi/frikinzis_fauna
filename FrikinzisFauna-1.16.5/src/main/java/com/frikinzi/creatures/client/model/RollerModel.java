package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ConureEntity;
import com.frikinzi.creatures.entity.RollerEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RollerModel extends AnimatedGeoModel<RollerEntity> {
    @Override
    public ResourceLocation getModelLocation(RollerEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/roller/rollerfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/roller/roller.geo.json");
    }


    @Override
    public ResourceLocation getTextureLocation(RollerEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roller/roller" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/roller/roller" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RollerEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.roller.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.roller.json");
    }
}
