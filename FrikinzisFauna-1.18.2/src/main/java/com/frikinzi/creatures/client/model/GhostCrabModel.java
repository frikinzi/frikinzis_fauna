package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.FireGobyEntity;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class GhostCrabModel extends AnimatedTickingGeoModel<GhostCrabEntity> {

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
