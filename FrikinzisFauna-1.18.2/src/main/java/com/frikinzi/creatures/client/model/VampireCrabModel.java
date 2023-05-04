package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.FiddlerCrabEntity;
import com.frikinzi.creatures.entity.VampireCrabEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class VampireCrabModel extends AnimatedTickingGeoModel<VampireCrabEntity> {

    @Override
    public ResourceLocation getModelLocation(VampireCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/vampirecrab/vampirecrab.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(VampireCrabEntity object)
    {

        return new ResourceLocation(Creatures.MODID, "textures/entity/vampirecrab/vampirecrab" + object.getVariant() + ".png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(VampireCrabEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.ghostcrab.json");
    }

}
