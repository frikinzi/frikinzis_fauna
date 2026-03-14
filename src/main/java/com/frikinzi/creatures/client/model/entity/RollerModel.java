package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ConureEntity;
import com.frikinzi.creatures.entity.RollerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RollerModel extends GeoModel<RollerEntity> {
    @Override
    public ResourceLocation getModelResource(RollerEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/roller/rollerfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/roller/roller.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RollerEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roller/roller" + object.getVariant() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/roller/roller" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RollerEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.roller.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.roller.json");
    }
}
