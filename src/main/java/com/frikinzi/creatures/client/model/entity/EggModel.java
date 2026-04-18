package com.frikinzi.creatures.client.model.entity;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.LovebirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import java.util.Arrays;

public class EggModel extends GeoModel<EggEntity> {
    // which egg texture should each entity use - number of each entity defined in ModEntityTypes
    Integer[] ONE = {2,3,5,10,11,13,25,29,31,32,29,48};
    Integer[] TWO = {16,19,12,35,63};
    Integer[] THREE = {26,1,34,23,33,28,53,59,49,62};
    Integer[] FOUR = {9};
    Integer[] FIVE = {14,0,15,13,18,38};
    Integer[] SIX = {20,4,27,37,50};
    Integer[] SEVEN = {7,22,6,30,17,24,39,52};
    Integer[] EIGHT = {8,21,36};
    @Override
    public ResourceLocation getModelResource(EggEntity object) {
        return new ResourceLocation(Creatures.MODID, "geo/entity/egg/egg.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EggEntity object) {
        if (Arrays.asList(ONE).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg1.png"); }
        else if (Arrays.asList(TWO).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg2.png"); }
        else if (Arrays.asList(THREE).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg3.png"); }
        else if (Arrays.asList(FOUR).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg4.png"); }
        else if (Arrays.asList(FIVE).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg5.png"); }
        else if (Arrays.asList(SIX).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg6.png"); }
        else if (Arrays.asList(SEVEN).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg7.png"); }
        else if (Arrays.asList(EIGHT).contains(object.getSpecies())) {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg8.png"); }
        else {
            return new ResourceLocation(Creatures.MODID, "textures/entity/egg/egg1.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(EggEntity object) {
        return new ResourceLocation(Creatures.MODID, "animations/animation.egg.json");
    }
}
