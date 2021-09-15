package com.creatures.afrikinzi.init;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.entity.arowana.EntityArowana;
import com.creatures.afrikinzi.entity.barn_owl.EntityBarnOwl;
import com.creatures.afrikinzi.entity.conure.EntityConure;
import com.creatures.afrikinzi.entity.dottyback.EntityDottyback;
import com.creatures.afrikinzi.entity.dove.EntityDove;
import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import com.creatures.afrikinzi.entity.ghostcrab.EntityGhostCrab;
import com.creatures.afrikinzi.entity.golden_eagle.EntityGoldenEagle;
import com.creatures.afrikinzi.entity.gourami.EntityGourami;
import com.creatures.afrikinzi.entity.guppy.EntityGuppy;
import com.creatures.afrikinzi.entity.gyrfalcon.EntityGyrfalcon;
import com.creatures.afrikinzi.entity.kakapo.EntityKakapo;
import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.entity.lorikeet.EntityLorikeet;
import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.entity.mandarin_duck.EntityMandarinDuck;
import com.creatures.afrikinzi.entity.pike.EntityPike;
import com.creatures.afrikinzi.entity.pygmyfalcon.EntityPygmyFalcon;
import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.entity.red_kite.EntityRedKite;
import com.creatures.afrikinzi.entity.shrimp.EntityShrimp;
import com.creatures.afrikinzi.entity.creatures_spoonbill.EntityCreaturesSpoonbill;
import com.creatures.afrikinzi.entity.stellers_sea_eagle.EntityStellersSeaEagle;
import com.creatures.afrikinzi.entity.wild_duck.EntityWildDuck;
import com.creatures.afrikinzi.util.Reference;
import com.creatures.afrikinzi.util.handlers.helper.RegistryHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
    public String[] FRESHWATER_BIOMES = {"RIVER", "SWAMP"};
    public String[] SALTWATER_BIOMES = {"OCEAN"};
    public static String[] MANDARIN_DUCK = {"FOREST", "RIVER"};
    public static String[] RED_KITE = {"FOREST", "SWAMP", "MESA"};
    public static String[] GOLDEN_EAGLE = {"FOREST", "PLAINS", "MOUNTAIN", "SANDY", "WASTELAND"};
    public static String[] CONURE = {"FOREST", "LUSH"};
    public static String[] FAIRY_WREN = {"FOREST", "DENSE", "LUSH"};
    public static String[] BARN_OWL = {"FOREST", "MOUNTAIN", "HILLS"};

    public static void registerEntities()
    {
        registerEntity("koi", EntityKoi.class, Reference.ENTITY_KOI, 30, 16731716, 16777215);
        registerEntity("dottyback", EntityDottyback.class, Reference.ENTITY_DOTTYBACK, 30, 16740608, 6058495);
        registerEntity("lovebird", EntityLovebird.class, Reference.ENTITY_LOVEBIRD, 30, 16749375, 16765696);
        registerEntity("pike", EntityPike.class, Reference.ENTITY_PIKE, 30, 10973747, 16777215);
        registerEntity("kakapo", EntityKakapo.class, Reference.ENTITY_KAKAPO, 30, 9607980, 12299667);
        registerEntity("creatures_spoonbill", EntityCreaturesSpoonbill.class, Reference.ENTITY_SPOONBILL, 30, 16490917, 16583198);
        registerEntity("mandarin_duck", EntityMandarinDuck.class, Reference.ENTITY_MANDARIN_DUCK, 30, 11798553, 16640178);
        registerEntity("arowana", EntityArowana.class, Reference.ENTITY_AROWANA, 30, 16771881, 16758578);
        registerEntity("guppy", EntityGuppy.class, Reference.ENTITY_GUPPY, 30, 8578898, 8579010);
        registerEntity("shrimp", EntityShrimp.class, Reference.ENTITY_SHRIMP, 30, 16583198, 16490917);
        registerEntity("raven", EntityRaven.class, Reference.ENTITY_RAVEN, 50, 9536, 4152450);
        registerEntity("gourami", EntityGourami.class, Reference.ENTITY_GOURAMI, 50, 4152450, 16777215);
        registerEntity("dove", EntityDove.class, Reference.ENTITY_DOVE, 50, 14935271, 16755148);
        registerEntity("red_kite", EntityRedKite.class, Reference.ENTITY_RED_KITE, 50, 13651220, 4152450);
        registerEntity("golden_eagle", EntityGoldenEagle.class, Reference.ENTITY_GOLDEN_EAGLE, 80, 4525319, 10125934);
        registerEntity("stellers_sea_eagle", EntityStellersSeaEagle.class, Reference.ENTITY_STELLERS_SEA_EAGLE, 80, 5322812, 16515071);
        registerEntity("gyrfalcon", EntityGyrfalcon.class, Reference.ENTITY_GYRFALCON, 80, 16515071, 9536);
        registerEntity("conure", EntityConure.class, Reference.ENTITY_CONURE, 50, 16170000, 2182420);
        registerEntity("lorikeet", EntityLorikeet.class, Reference.ENTITY_LORIKEET, 50, 3093151, 16718368);
        //registerEntity("iberian_lynx", EntityIberianLynx.class, Reference.ENTITY_IBERIAN_LYNX, 50, 10846812, 0);
        registerEntity("fairy_wren", EntityFairyWren.class, Reference.ENTITY_FAIRY_WREN, 50, 1650103, 41);
        registerEntity("ghost_crab", EntityGhostCrab.class, Reference.ENTITY_GHOST_CRAB, 50, 13545576, 16777215);
        registerEntity("pygmy_falcon", EntityPygmyFalcon.class, Reference.ENTITY_PYGMY_FALCON, 50, 8097951, 15724012);
        registerEntity("barn_owl", EntityBarnOwl.class, Reference.ENTITY_BARN_OWL, 80, 16777215, 11897942);
        registerEntity("wild_duck", EntityWildDuck.class, Reference.ENTITY_WILD_DUCK, 80, 15702874, 7901340);

        //spawn placement
        EntitySpawnPlacementRegistry.setPlacementType(EntityKoi.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityDottyback.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityPike.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityArowana.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityShrimp.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityGuppy.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityGourami.class, EntityLiving.SpawnPlacementType.IN_WATER);

        if (CreaturesConfig.allSpawns == true) {
            //spawns

            //water creatures
            if (CreaturesConfig.koiSpawns == true) {
            EntityRegistry.addSpawn(EntityKoi.class, 80, 1, 3, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.dottybackSpawns == true) {
            EntityRegistry.addSpawn(EntityDottyback.class, 80, 2, 5, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.pikeSpawns == true) {
            EntityRegistry.addSpawn(EntityPike.class, 80, 1, 1, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.arowanaSpawns == true) {
            EntityRegistry.addSpawn(EntityArowana.class, 80, 1, 1, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER)); }
            if (CreaturesConfig.shrimpSpawns == true) {
            EntityRegistry.addSpawn(EntityShrimp.class, 80, 4, 10, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER)); }
            if (CreaturesConfig.guppySpawns == true) {
            EntityRegistry.addSpawn(EntityGuppy.class, 80, 3, 8, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER)); }
            if (CreaturesConfig.gouramiSpawns == true) {
            EntityRegistry.addSpawn(EntityGourami.class, 80, 2, 3, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER)); }
            if (CreaturesConfig.ghostcrabSpawns == true) {
            EntityRegistry.addSpawn(EntityGhostCrab.class, 80, 2, 5, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.BEACH)); }

            //avians
            if (CreaturesConfig.lovebirdSpawns == true) {
            EntityRegistry.addSpawn(EntityLovebird.class, 25, 2, 6, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.SAVANNA)); }
            if (CreaturesConfig.kakapoSpawns == true) {
            EntityRegistry.addSpawn(EntityKakapo.class, 5, 1, 1, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST)); }
            if (CreaturesConfig.spoonbillSpawns == true) {
            EntityRegistry.addSpawn(EntityCreaturesSpoonbill.class, 15, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.SWAMP)); }
            if (CreaturesConfig.mandarinduckSpawns == true) {
            for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(MANDARIN_DUCK)) {
                EntityRegistry.addSpawn(EntityMandarinDuck.class, 15, 2, 3, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
            } }
            if (CreaturesConfig.ravenSpawns == true) {
            EntityRegistry.addSpawn(EntityRaven.class, 25, 1, 3, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST)); }
            if (CreaturesConfig.doveSpawns == true) {
            EntityRegistry.addSpawn(EntityDove.class, 25, 1, 1, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.LUSH)); }
            if (CreaturesConfig.redkiteSpawns == true) {
            for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(RED_KITE)) {
                EntityRegistry.addSpawn(EntityRedKite.class, 8, 1, 1, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
            } }
            if (CreaturesConfig.goldeneagleSpawns == true) {
            for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(GOLDEN_EAGLE)) {
                EntityRegistry.addSpawn(EntityGoldenEagle.class, 8, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
            } }
            if (CreaturesConfig.stellersseaeagleSpawns == true) {
            EntityRegistry.addSpawn(EntityStellersSeaEagle.class, 5, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.COLD)); }
            if (CreaturesConfig.gyrfalconSpawns == true) {
                EntityRegistry.addSpawn(EntityGyrfalcon.class, 5, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.COLD));
            }
            if (CreaturesConfig.conureSpawns == true) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(CONURE)) {
                    EntityRegistry.addSpawn(EntityConure.class, 25, 3, 5, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.lorikeetSpawns == true) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(CONURE)) {
                    EntityRegistry.addSpawn(EntityLorikeet.class, 25, 2, 5, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.fairywrenSpawns == true) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(FAIRY_WREN)) {
                    EntityRegistry.addSpawn(EntityFairyWren.class, 30, 3, 6, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.pygmyfalconSpawns == true) {
                EntityRegistry.addSpawn(EntityPygmyFalcon.class, 5, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.DRY));
            }
            if (CreaturesConfig.barnowlSpawns == true) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(BARN_OWL)) {
                    EntityRegistry.addSpawn(EntityBarnOwl.class, 30, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }

        }
        //EntityRegistry.addSpawn(EntityConure.class, 80, 2, 3, EnumCreatureType.CREATURE, Biomes.PLAINS);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Creatures.instance, range, 1, true, color1, color2);
    }

    private static void registerNonMobEntity() {

    }
}
