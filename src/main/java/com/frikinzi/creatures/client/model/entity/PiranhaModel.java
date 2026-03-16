package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GoldfishEntity;
import com.frikinzi.creatures.entity.PiranhaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PiranhaModel extends GeoModel<PiranhaEntity> {
    @Override
    public ResourceLocation getModelResource(PiranhaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/piranha/piranhafry.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/piranha/piranha.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PiranhaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/piranha/piranhafry.png");
        }
        return new ResourceLocation(Creatures.MODID, "textures/entity/piranha/piranha" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(PiranhaEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "animations/animation.piranhafry.json");
        }
        return new ResourceLocation(Creatures.MODID, "animations/animation.piranha.json");
    }
}
