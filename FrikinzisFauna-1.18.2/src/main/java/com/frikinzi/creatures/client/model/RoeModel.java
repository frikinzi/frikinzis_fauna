package com.frikinzi.creatures.client.model;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.egg.RoeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

import java.util.Arrays;

public class RoeModel extends AnimatedTickingGeoModel<RoeEntity> {
    // which roe texture should each entity use - number of each entity defined in ModEntityTypes
    Integer[] ONE = {3,6,11,17}; // light orange
    Integer[] TWO = {13,15,16,18,19}; // black
    Integer[] THREE = {2,4,5,9,10}; // white
    Integer[] FOUR = {1,7,8,12}; // darker orange
    @Override
    public ResourceLocation getModelLocation(RoeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "geo/entity/roe/roe.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RoeEntity object)
    {
        if (Arrays.asList(ONE).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe1.png"); }
        else if (Arrays.asList(TWO).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe2.png"); }
        else if (Arrays.asList(THREE).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe3.png"); }
        else if (Arrays.asList(FOUR).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe4.png"); }
        else {
            return new ResourceLocation(Creatures.MODID, "textures/entity/roe/fishroe1.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RoeEntity object)
    {
        return new ResourceLocation(Creatures.MODID, "animations/animation.egg.json");
    }

}
