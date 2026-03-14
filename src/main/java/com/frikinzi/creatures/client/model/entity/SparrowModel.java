package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.RollerEntity;
import com.frikinzi.creatures.entity.SparrowEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SparrowModel extends GeoModel<SparrowEntity> {
    @Override
    public ResourceLocation getModelResource(SparrowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/sparrow/sparrowfly.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/sparrow/sparrow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SparrowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/sparrow/sparrow" + object.getVariant() + object.getGenderName() + "fly.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/sparrow/sparrow" + object.getVariant() + object.getGenderName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SparrowEntity object)
    {
        if (object.isFlying()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.sparrow.fly.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.sparrow.json");
    }
}
