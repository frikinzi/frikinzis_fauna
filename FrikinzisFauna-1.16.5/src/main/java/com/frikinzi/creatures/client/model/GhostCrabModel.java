package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import com.frikinzi.creatures.entity.GuppyEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GhostCrabModel extends AnimatedGeoModel<GhostCrabEntity> {
    @Override
    public ResourceLocation getModelLocation(GhostCrabEntity object)
    {
        if (object.isBaby()) {
            return new ResourceLocation(Creatures.MODID, "geo/entity/ghostcrab/ghostcrab.geo.json");
        }
        return new ResourceLocation(Creatures.MODID, "geo/entity/ghostcrab/ghostcrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GhostCrabEntity object)
    {

        return new ResourceLocation(Creatures.MODID, "textures/entity/ghostcrab/ghostcrab" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GhostCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.ghostcrab.json");
    }
}
