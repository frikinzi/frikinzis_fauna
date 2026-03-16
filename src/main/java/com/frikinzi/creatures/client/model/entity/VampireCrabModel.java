package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.GhostCrabEntity;
import com.frikinzi.creatures.entity.VampireCrabEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VampireCrabModel extends GeoModel<VampireCrabEntity> {
    @Override
    public ResourceLocation getModelResource(VampireCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/vampirecrab/vampirecrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VampireCrabEntity object)
    {

        return new ResourceLocation(Creatures.MODID, "textures/entity/vampirecrab/vampirecrab" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(VampireCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.ghostcrab.json");
    }
}
