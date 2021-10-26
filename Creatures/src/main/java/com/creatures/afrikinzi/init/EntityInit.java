package com.creatures.afrikinzi.init;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.entity.arowana.EntityArowana;
import com.creatures.afrikinzi.entity.barn_owl.EntityBarnOwl;
import com.creatures.afrikinzi.entity.blue_tang.EntityBlueTang;
import com.creatures.afrikinzi.entity.chickadee.EntityChickadee;
import com.creatures.afrikinzi.entity.conure.EntityConure;
import com.creatures.afrikinzi.entity.dottyback.EntityDottyback;
import com.creatures.afrikinzi.entity.dove.EntityDove;
import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import com.creatures.afrikinzi.entity.fire_goby.EntityFireGoby;
import com.creatures.afrikinzi.entity.flame_angelfish.EntityFlameAngelfish;
import com.creatures.afrikinzi.entity.ghostcrab.EntityGhostCrab;
import com.creatures.afrikinzi.entity.golden_eagle.EntityGoldenEagle;
import com.creatures.afrikinzi.entity.goldfish.EntityGoldfish;
import com.creatures.afrikinzi.entity.goldfish.EntityRanchuGoldfish;
import com.creatures.afrikinzi.entity.gourami.EntityGourami;
import com.creatures.afrikinzi.entity.guppy.EntityGuppy;
import com.creatures.afrikinzi.entity.gyrfalcon.EntityGyrfalcon;
import com.creatures.afrikinzi.entity.kakapo.EntityKakapo;
import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.entity.lorikeet.EntityLorikeet;
import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.entity.mandarin_duck.EntityMandarinDuck;
import com.creatures.afrikinzi.entity.pike.EntityPike;
import com.creatures.afrikinzi.entity.pygmy_goose.EntityPygmyGoose;
import com.creatures.afrikinzi.entity.pygmyfalcon.EntityPygmyFalcon;
import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.entity.red_kite.EntityRedKite;
import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.entity.shrimp.EntityShrimp;
import com.creatures.afrikinzi.entity.creatures_spoonbill.EntityCreaturesSpoonbill;
import com.creatures.afrikinzi.entity.stellers_sea_eagle.EntityStellersSeaEagle;
import com.creatures.afrikinzi.entity.swallow.EntitySwallow;
import com.creatures.afrikinzi.entity.trout.EntityTrout;
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
    public static String[] FRESHWATER_BIOMES = {"RIVER", "SWAMP", "FOREST"};
    public static String[] MANDARIN_DUCK = {"FOREST", "RIVER"};
    public static String[] RED_KITE = {"FOREST", "SWAMP", "MESA"};
    public static String[] GOLDEN_EAGLE = {"FOREST", "PLAINS", "MOUNTAIN", "SANDY", "WASTELAND"};
    public static String[] CONURE = {"FOREST", "LUSH"};
    public static String[] FAIRY_WREN = {"FOREST", "DENSE", "LUSH"};
    public static String[] BARN_OWL = {"FOREST", "MOUNTAIN", "HILLS"};
    public static String[] DUCKS = {"RIVER", "SWAMP"};
    public static String[] SWALLOW = {"FOREST", "SWAMP", "PLAINS", "SAVANNAH"};
    public static String[] DOVE = {"FOREST", "JUNGLE", "PLAINS"};

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
        registerEntity("dove", EntityDove.class, Reference.ENTITY_DOVE, 50, 14935271, 15395538);
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
        registerEntity("roller", EntityRoller.class, Reference.ENTITY_ROLLER, 80, 1414724, 13192647);
        registerEntity("goldfish", EntityGoldfish.class, Reference.ENTITY_GOLDFISH, 80, 14501642, 14318603);
        registerEntity("ranchu", EntityRanchuGoldfish.class, Reference.ENTITY_RANCHU, 80, 14501642, 11);
        registerEntity("chickadee", EntityChickadee.class, Reference.ENTITY_CHICKADEE, 80, 13879499, 11);
        registerEntity("pygmygoose", EntityPygmyGoose.class, Reference.ENTITY_PYGMY_GOOSE, 80, 2772553, 15964498);
        registerEntity("fire_goby", EntityFireGoby.class, Reference.ENTITY_FIRE_GOBY, 80, 14080426, 14895873);
        registerEntity("blue_tang", EntityBlueTang.class, Reference.ENTITY_BLUE_TANG, 80, 3895524, 722696);
        registerEntity("flame_angelfish", EntityFlameAngelfish.class, Reference.ENTITY_FLAME_ANGELFISH, 80, 16396073, 16611846);
        registerEntity("creatures_trout", EntityTrout.class, Reference.ENTITY_TROUT, 80, 9861465, 11822456);
        registerEntity("swallow", EntitySwallow.class, Reference.ENTITY_SWALLOW, 80, 6588890, 11953480);

        //spawn placement
        EntitySpawnPlacementRegistry.setPlacementType(EntityKoi.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityDottyback.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityPike.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityArowana.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityShrimp.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityGuppy.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityGourami.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityGoldfish.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityRanchuGoldfish.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityFireGoby.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityBlueTang.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityTrout.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(EntityFlameAngelfish.class, EntityLiving.SpawnPlacementType.IN_WATER);

        if (CreaturesConfig.allSpawns == true) {
            //spawns

            //water creatures
            if (CreaturesConfig.koiSpawns) {
                EntityRegistry.addSpawn(EntityKoi.class, CreaturesConfig.koiSpawnRate, 1, 3, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.dottybackSpawns) {
                EntityRegistry.addSpawn(EntityDottyback.class, CreaturesConfig.dottybackSpawnRate, 2, 5, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.firegobySpawns) {
                EntityRegistry.addSpawn(EntityFireGoby.class, CreaturesConfig.firegobySpawnRate, 1, 1, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.flameangelfishSpawns) {
                EntityRegistry.addSpawn(EntityFlameAngelfish.class, CreaturesConfig.flameangelfishSpawnRate, 1, 1, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.bluetangSpawns) {
                EntityRegistry.addSpawn(EntityBlueTang.class, CreaturesConfig.bluetangSpawnRate, 1, 1, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.pikeSpawns) {
                EntityRegistry.addSpawn(EntityPike.class, CreaturesConfig.pikeSpawnRate, 1, 1, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.arowanaSpawns) {
                EntityRegistry.addSpawn(EntityArowana.class, CreaturesConfig.arowanaSpawnRate, 1, 1, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.shrimpSpawns) {
                EntityRegistry.addSpawn(EntityShrimp.class, CreaturesConfig.shrimpSpawnRate, 4, 10, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.guppySpawns) {
                EntityRegistry.addSpawn(EntityGuppy.class, CreaturesConfig.guppySpawnRate, 3, 8, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.troutSpawns) {
                EntityRegistry.addSpawn(EntityTrout.class, CreaturesConfig.troutSpawnRate, 1, 2, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.gouramiSpawns) {
                EntityRegistry.addSpawn(EntityGourami.class, CreaturesConfig.gouramiSpawnRate, 2, 3, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER)); }
            if (CreaturesConfig.ghostcrabSpawns) {
                EntityRegistry.addSpawn(EntityGhostCrab.class, CreaturesConfig.ghostcrabSpawnRate, 2, 5, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.BEACH)); }

            //avians
            if (CreaturesConfig.lovebirdSpawns) {
                EntityRegistry.addSpawn(EntityLovebird.class, CreaturesConfig.lovebirdSpawnRate, 2, 6, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.SAVANNA)); }
            if (CreaturesConfig.kakapoSpawns) {
                EntityRegistry.addSpawn(EntityKakapo.class, CreaturesConfig.kakapoSpawnRate, 1, 1, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST)); }
            if (CreaturesConfig.spoonbillSpawns) {
                EntityRegistry.addSpawn(EntityCreaturesSpoonbill.class, CreaturesConfig.spoonbillSpawnRate, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.SWAMP)); }
            if (CreaturesConfig.mandarinduckSpawns) {
            for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(MANDARIN_DUCK)) {
                EntityRegistry.addSpawn(EntityMandarinDuck.class, CreaturesConfig.mandarinduckSpawnRate, 2, 3, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
            } }
            if (CreaturesConfig.ravenSpawns) {
                EntityRegistry.addSpawn(EntityRaven.class, CreaturesConfig.ravenSpawnRate, 1, 3, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST)); }
            if (CreaturesConfig.doveSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(DOVE)) {
                EntityRegistry.addSpawn(EntityDove.class, CreaturesConfig.doveSpawnRate, 2, 5, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t)); }
            }
            if (CreaturesConfig.redkiteSpawns) {
            for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(RED_KITE)) {
                EntityRegistry.addSpawn(EntityRedKite.class, CreaturesConfig.redkiteSpawnRate, 1, 1, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
            } }
            if (CreaturesConfig.goldeneagleSpawns) {
            for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(GOLDEN_EAGLE)) {
                EntityRegistry.addSpawn(EntityGoldenEagle.class, CreaturesConfig.goldeneagleSpawnRate, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
            } }
            if (CreaturesConfig.stellersseaeagleSpawns) {
                EntityRegistry.addSpawn(EntityStellersSeaEagle.class, CreaturesConfig.stellersseaeagleSpawnRate, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.COLD));
            }
            if (CreaturesConfig.gyrfalconSpawns) {
                EntityRegistry.addSpawn(EntityGyrfalcon.class, CreaturesConfig.gyrfalconSpawnRate, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.COLD));
            }
            if (CreaturesConfig.conureSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(CONURE)) {
                    EntityRegistry.addSpawn(EntityConure.class, CreaturesConfig.conureSpawnRate, 3, 5, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.lorikeetSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(CONURE)) {
                    EntityRegistry.addSpawn(EntityLorikeet.class, CreaturesConfig.lorikeetSpawnRate, 2, 5, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.fairywrenSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(FAIRY_WREN)) {
                    EntityRegistry.addSpawn(EntityFairyWren.class, CreaturesConfig.fairywrenSpawnRate, 3, 6, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.pygmyfalconSpawns) {
                EntityRegistry.addSpawn(EntityPygmyFalcon.class, CreaturesConfig.pygmyfalconSpawnRate, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.DRY));
            }
            if (CreaturesConfig.barnowlSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(BARN_OWL)) {
                    EntityRegistry.addSpawn(EntityBarnOwl.class, CreaturesConfig.barnowlSpawnRate, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.duckSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(DUCKS)) {
                    EntityRegistry.addSpawn(EntityWildDuck.class, CreaturesConfig.duckSpawnRate, 2, 3, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.rollerSpawns) {
                EntityRegistry.addSpawn(EntityRoller.class, CreaturesConfig.rollerSpawnRate, 1, 2, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST));
            }
            if (CreaturesConfig.goldfishSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(FRESHWATER_BIOMES)) {
                    EntityRegistry.addSpawn(EntityGoldfish.class, CreaturesConfig.goldfishSpawnRate, 2, 5, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.ranchuSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(FRESHWATER_BIOMES)) {
                    EntityRegistry.addSpawn(EntityRanchuGoldfish.class, CreaturesConfig.ranchuSpawnRate, 2, 5, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.chickadeeSpawns) {
                EntityRegistry.addSpawn(EntityChickadee.class, CreaturesConfig.chickadeeSpawnRate, 2, 3, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST));
            }
            if (CreaturesConfig.pygmygooseSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(DUCKS)) {
                    EntityRegistry.addSpawn(EntityPygmyGoose.class, CreaturesConfig.pygmygooseSpawnRate, 2, 3, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.swallowSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(SWALLOW)) {
                    EntityRegistry.addSpawn(EntitySwallow.class, CreaturesConfig.swallowSpawnRate, 3, 5, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }

        }
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Creatures.instance, range, 1, true, color1, color2);
    }

    private static void registerNonMobEntity() {

    }
}
