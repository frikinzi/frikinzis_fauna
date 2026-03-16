package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import com.frikinzi.creatures.entity.GuppyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GhostCrabModel extends GeoModel<GhostCrabEntity> {
    @Override
    public ResourceLocation getModelResource(GhostCrabEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/ghostcrab/ghostcrab.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/ghostcrab/ghostcrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GhostCrabEntity object)
    {

        return new ResourceLocation(Creatures.MODID, "textures/entity/ghostcrab/ghostcrab" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(GhostCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.ghostcrab.json");
    }
}
