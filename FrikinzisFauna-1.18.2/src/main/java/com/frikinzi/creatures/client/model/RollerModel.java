package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.RollerEntity;
import com.frikinzi.creatures.entity.WildDuckEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class RollerModel extends AnimatedTickingGeoModel<RollerEntity> {

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
