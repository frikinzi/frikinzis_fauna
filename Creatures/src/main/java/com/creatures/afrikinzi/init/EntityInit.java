package com.creatures.afrikinzi.init;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.entity.arowana.EntityArowana;
import com.creatures.afrikinzi.entity.barn_owl.EntityBarnOwl;
import com.creatures.afrikinzi.entity.blue_tang.EntityBlueTang;
import com.creatures.afrikinzi.entity.bushtit.EntityBushtit;
import com.creatures.afrikinzi.entity.chickadee.EntityChickadee;
import com.creatures.afrikinzi.entity.conure.EntityConure;
import com.creatures.afrikinzi.entity.dottyback.EntityDottyback;
import com.creatures.afrikinzi.entity.dove.EntityDove;
import com.creatures.afrikinzi.entity.eagleowl.EntityEagleOwl;
import com.creatures.afrikinzi.entity.fairy_wren.EntityFairyWren;
import com.creatures.afrikinzi.entity.fiddler_crab.EntityFiddlerCrab;
import com.creatures.afrikinzi.entity.fire_goby.EntityFireGoby;
import com.creatures.afrikinzi.entity.flame_angelfish.EntityFlameAngelfish;
import com.creatures.afrikinzi.entity.ghostcrab.EntityGhostCrab;
import com.creatures.afrikinzi.entity.golden_eagle.EntityGoldenEagle;
import com.creatures.afrikinzi.entity.goldfish.EntityGoldfish;
import com.creatures.afrikinzi.entity.goldfish.EntityRanchuGoldfish;
import com.creatures.afrikinzi.entity.goose.EntityGoose;
import com.creatures.afrikinzi.entity.gourami.EntityGourami;
import com.creatures.afrikinzi.entity.guppy.EntityGuppy;
import com.creatures.afrikinzi.entity.gyrfalcon.EntityGyrfalcon;
import com.creatures.afrikinzi.entity.ibis.EntityIbis;
import com.creatures.afrikinzi.entity.kakapo.EntityKakapo;
import com.creatures.afrikinzi.entity.kingfisher.EntityKingfisher;
import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.entity.lapwing.EntityLapwing;
import com.creatures.afrikinzi.entity.laughingthrush.EntityLaughingthrush;
import com.creatures.afrikinzi.entity.lorikeet.EntityLorikeet;
import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.entity.magpie.EntityMagpie;
import com.creatures.afrikinzi.entity.mandarin_duck.EntityMandarinDuck;
import com.creatures.afrikinzi.entity.osprey.EntityOsprey;
import com.creatures.afrikinzi.entity.peafowl.EntityPeafowl;
import com.creatures.afrikinzi.entity.pelican.EntityPelican;
import com.creatures.afrikinzi.entity.pike.EntityPike;
import com.creatures.afrikinzi.entity.pygmy_goose.EntityPygmyGoose;
import com.creatures.afrikinzi.entity.pygmyfalcon.EntityPygmyFalcon;
import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.entity.red_kite.EntityRedKite;
import com.creatures.afrikinzi.entity.red_snapper.EntityRedSnapper;
import com.creatures.afrikinzi.entity.robin.EntityRobin;
import com.creatures.afrikinzi.entity.roller.EntityRoller;
import com.creatures.afrikinzi.entity.shrimp.EntityShrimp;
import com.creatures.afrikinzi.entity.creatures_spoonbill.EntityCreaturesSpoonbill;
import com.creatures.afrikinzi.entity.skua.EntitySkua;
import com.creatures.afrikinzi.entity.sparrow.EntitySparrow;
import com.creatures.afrikinzi.entity.stellers_sea_eagle.EntityStellersSeaEagle;
import com.creatures.afrikinzi.entity.swallow.EntitySwallow;
import com.creatures.afrikinzi.entity.trout.EntityTrout;
import com.creatures.afrikinzi.entity.wild_duck.EntityWildDuck;
import com.creatures.afrikinzi.entity.wood_duck.EntityWoodDuck;
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
    public static String[] CONURE = {"FOREST", "JUNGLE"};
    public static String[] FAIRY_WREN = {"FOREST", "DENSE", "JUNGLE"};
    public static String[] BARN_OWL = {"FOREST", "MOUNTAIN", "HILLS"};
    public static String[] DUCKS = {"RIVER", "SWAMP"};
    public static String[] SWALLOW = {"FOREST", "SWAMP", "PLAINS", "SAVANNAH"};
    public static String[] DOVE = {"FOREST", "JUNGLE", "PLAINS"};
    public static String[] SPARROW = {"FOREST", "PLAINS", "SANDY"};
    public static String[] THRUSH = {"FOREST", "JUNGLE"};

    public static void registerEntities()
    {
        registerEntity("koi", EntityKoi.class, Reference.ENTITY_KOI, 30, 16731716, 16777215);
        registerEntity("dottyback", EntityDottyback.class, Reference.ENTITY_DOTTYBACK, 30, 16740608, 6058495);
        registerEntity("lovebird", EntityLovebird.class, Reference.ENTITY_LOVEBIRD, 30, 16749375, 16765696);
        registerEntity("pike", EntityPike.class, Reference.ENTITY_PIKE, 30, 10973747, 16777215);
        registerEntity("creatures_kakapo", EntityKakapo.class, Reference.ENTITY_KAKAPO, 30, 9607980, 12299667);
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
        registerEntity("wild_duck", EntityWildDuck.class, Reference.ENTITY_WILD_DUCK, 50, 15702874, 7901340);
        registerEntity("roller", EntityRoller.class, Reference.ENTITY_ROLLER, 50, 1414724, 13192647);
        registerEntity("goldfish", EntityGoldfish.class, Reference.ENTITY_GOLDFISH, 50, 14501642, 14318603);
        registerEntity("ranchu", EntityRanchuGoldfish.class, Reference.ENTITY_RANCHU, 50, 14501642, 11);
        registerEntity("chickadee", EntityChickadee.class, Reference.ENTITY_CHICKADEE, 50, 13879499, 11);
        registerEntity("pygmygoose", EntityPygmyGoose.class, Reference.ENTITY_PYGMY_GOOSE, 50, 2772553, 15964498);
        registerEntity("fire_goby", EntityFireGoby.class, Reference.ENTITY_FIRE_GOBY, 50, 14080426, 14895873);
        registerEntity("blue_tang", EntityBlueTang.class, Reference.ENTITY_BLUE_TANG, 50, 3895524, 722696);
        registerEntity("flame_angelfish", EntityFlameAngelfish.class, Reference.ENTITY_FLAME_ANGELFISH, 50, 16396073, 16611846);
        registerEntity("creatures_trout", EntityTrout.class, Reference.ENTITY_TROUT, 50, 9861465, 11822456);
        registerEntity("swallow", EntitySwallow.class, Reference.ENTITY_SWALLOW, 50, 6588890, 11953480);
        registerEntity("fiddlercrab", EntityFiddlerCrab.class, Reference.ENTITY_FIDDLER_CRAB, 50, 526085, 9297374);
        registerEntity("ibis", EntityIbis.class, Reference.ENTITY_IBIS, 50, 13178131, 13847612);
        registerEntity("red_snapper", EntityRedSnapper.class, Reference.ENTITY_RED_SNAPPER, 50, 15320002, 13979723);
        registerEntity("wood_duck", EntityWoodDuck.class, Reference.ENTITY_WOOD_DUCK, 50, 2967328, 7548455);
        registerEntity("peafowl", EntityPeafowl.class, Reference.ENTITY_PEAFOWL, 50, 32466, 2450496);
        registerEntity("sparrow", EntitySparrow.class, Reference.ENTITY_SPARROW, 50, 9004362, 13743005);
        registerEntity("bushtit", EntityBushtit.class, Reference.ENTITY_BUSHTIT, 50, 16382707, 9004362);
        registerEntity("laughingthrush", EntityLaughingthrush.class, Reference.ENTITY_LAUGHINGTHRUSH, 50, 8873290, 15720079);
        registerEntity("eagleowl", EntityEagleOwl.class, Reference.ENTITY_EAGLE_OWL, 50, 13608552, 4798501);
        registerEntity("robin", EntityRobin.class, Reference.ENTITY_ROBIN, 50, 9600343, 13723915);
        registerEntity("magpie", EntityMagpie.class, Reference.ENTITY_MAGPIE, 50, 1908259, 4480395);
        registerEntity("wild_goose", EntityGoose.class, Reference.ENTITY_GOOSE, 50, 7888451, 525056);
        registerEntity("osprey", EntityOsprey.class, Reference.ENTITY_OSPREY, 50, 14869470, 4732721);
        registerEntity("kingfisher", EntityKingfisher.class, Reference.ENTITY_KINGFISHER, 50, 29897, 13722630);
        registerEntity("pelican", EntityPelican.class, Reference.ENTITY_PELICAN, 50, 15787740, 14585696);
        registerEntity("lapwing", EntityLapwing.class, Reference.ENTITY_LAPWING, 50, 1921595, 528151);
        registerEntity("creatures_skua", EntitySkua.class, Reference.ENTITY_SKUA, 50, 6182224, 4011831);

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
        EntitySpawnPlacementRegistry.setPlacementType(EntityRedSnapper.class, EntityLiving.SpawnPlacementType.IN_WATER);

        if (CreaturesConfig.allSpawns == true) {
            //spawns

            //water creatures
            if (CreaturesConfig.koiSpawns) {
                EntityRegistry.addSpawn(EntityKoi.class, CreaturesConfig.koiSpawnRate, CreaturesConfig.koiMinGroup, CreaturesConfig.koiMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.dottybackSpawns) {
                EntityRegistry.addSpawn(EntityDottyback.class, CreaturesConfig.dottybackSpawnRate, CreaturesConfig.dottybackMinGroup, CreaturesConfig.dottybackMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.redsnapperSpawns) {
                EntityRegistry.addSpawn(EntityRedSnapper.class, CreaturesConfig.redsnapperSpawnRate, CreaturesConfig.redsnapperMinGroup, CreaturesConfig.redsnapperMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.firegobySpawns) {
                EntityRegistry.addSpawn(EntityFireGoby.class, CreaturesConfig.firegobySpawnRate, CreaturesConfig.firegobyMinGroup, CreaturesConfig.firegobyMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.flameangelfishSpawns) {
                EntityRegistry.addSpawn(EntityFlameAngelfish.class, CreaturesConfig.flameangelfishSpawnRate, CreaturesConfig.flameangelfishMinGroup, CreaturesConfig.flameangelfishMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.bluetangSpawns) {
                EntityRegistry.addSpawn(EntityBlueTang.class, CreaturesConfig.bluetangSpawnRate, CreaturesConfig.bluetangMinGroup, CreaturesConfig.bluetangMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.OCEAN));
            }
            if (CreaturesConfig.pikeSpawns) {
                EntityRegistry.addSpawn(EntityPike.class, CreaturesConfig.pikeSpawnRate, CreaturesConfig.pikeMinGroup, CreaturesConfig.pikeMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.arowanaSpawns) {
                EntityRegistry.addSpawn(EntityArowana.class, CreaturesConfig.arowanaSpawnRate, CreaturesConfig.arowanaMinGroup, CreaturesConfig.arowanaMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.shrimpSpawns) {
                EntityRegistry.addSpawn(EntityShrimp.class, CreaturesConfig.shrimpSpawnRate, CreaturesConfig.shrimpMinGroup, CreaturesConfig.shrimpMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.guppySpawns) {
                EntityRegistry.addSpawn(EntityGuppy.class, CreaturesConfig.guppySpawnRate, CreaturesConfig.guppyMinGroup, CreaturesConfig.guppyMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.troutSpawns) {
                EntityRegistry.addSpawn(EntityTrout.class, CreaturesConfig.troutSpawnRate, CreaturesConfig.troutMinGroup, CreaturesConfig.troutMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.gouramiSpawns) {
                EntityRegistry.addSpawn(EntityGourami.class, CreaturesConfig.gouramiSpawnRate, CreaturesConfig.gouramiMinGroup, CreaturesConfig.gouramiMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER)); }
            if (CreaturesConfig.ghostcrabSpawns) {
                EntityRegistry.addSpawn(EntityGhostCrab.class, CreaturesConfig.ghostcrabSpawnRate, CreaturesConfig.ghostcrabMinGroup, CreaturesConfig.ghostcrabMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.BEACH)); }
            if (CreaturesConfig.ghostcrabSpawns) {
                EntityRegistry.addSpawn(EntityFiddlerCrab.class, CreaturesConfig.fiddlercrabSpawnRate, CreaturesConfig.fiddlercrabMinGroup, CreaturesConfig.fiddlercrabMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.BEACH)); }

            //avians
            if (CreaturesConfig.lovebirdSpawns) {
                EntityRegistry.addSpawn(EntityLovebird.class, CreaturesConfig.lovebirdSpawnRate, CreaturesConfig.lovebirdMinGroup, CreaturesConfig.lovebirdMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.SAVANNA)); }
            if (CreaturesConfig.kakapoSpawns) {
                EntityRegistry.addSpawn(EntityKakapo.class, CreaturesConfig.kakapoSpawnRate, CreaturesConfig.kakapoMinGroup, CreaturesConfig.kakapoMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST)); }
            if (CreaturesConfig.spoonbillSpawns) {
                EntityRegistry.addSpawn(EntityCreaturesSpoonbill.class, CreaturesConfig.spoonbillSpawnRate, CreaturesConfig.spoonbillMinGroup, CreaturesConfig.spoonbillMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.SWAMP)); }
            if (CreaturesConfig.mandarinduckSpawns) {
            for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(MANDARIN_DUCK)) {
                EntityRegistry.addSpawn(EntityMandarinDuck.class, CreaturesConfig.mandarinduckSpawnRate, CreaturesConfig.mandarinduckMinGroup, CreaturesConfig.mandarinduckMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
            } }
            if (CreaturesConfig.ravenSpawns) {
                EntityRegistry.addSpawn(EntityRaven.class, CreaturesConfig.ravenSpawnRate, CreaturesConfig.ravenMinGroup, CreaturesConfig.ravenMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST)); }
            if (CreaturesConfig.doveSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(DOVE)) {
                EntityRegistry.addSpawn(EntityDove.class, CreaturesConfig.doveSpawnRate, CreaturesConfig.doveMinGroup, CreaturesConfig.doveMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t)); }
            }
            if (CreaturesConfig.redkiteSpawns) {
            for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(RED_KITE)) {
                EntityRegistry.addSpawn(EntityRedKite.class, CreaturesConfig.redkiteSpawnRate, CreaturesConfig.redkiteMinGroup, CreaturesConfig.redkiteMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
            } }
            if (CreaturesConfig.goldeneagleSpawns) {
            for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(GOLDEN_EAGLE)) {
                EntityRegistry.addSpawn(EntityGoldenEagle.class, CreaturesConfig.goldeneagleSpawnRate, CreaturesConfig.goldeneagleMinGroup, CreaturesConfig.goldeneagleMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
            } }
            if (CreaturesConfig.stellersseaeagleSpawns) {
                EntityRegistry.addSpawn(EntityStellersSeaEagle.class, CreaturesConfig.stellersseaeagleSpawnRate, CreaturesConfig.stellersseaeagleMinGroup, CreaturesConfig.stellersseaeagleMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.COLD));
            }
            if (CreaturesConfig.gyrfalconSpawns) {
                EntityRegistry.addSpawn(EntityGyrfalcon.class, CreaturesConfig.gyrfalconSpawnRate, CreaturesConfig.gyrfalconMinGroup, CreaturesConfig.gyrfalconMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.COLD));
            }
            if (CreaturesConfig.conureSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(CONURE)) {
                    EntityRegistry.addSpawn(EntityConure.class, CreaturesConfig.conureSpawnRate, CreaturesConfig.conureMinGroup, CreaturesConfig.conureMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.lorikeetSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(CONURE)) {
                    EntityRegistry.addSpawn(EntityLorikeet.class, CreaturesConfig.lorikeetSpawnRate, CreaturesConfig.lorikeetMinGroup, CreaturesConfig.lorikeetMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.fairywrenSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(FAIRY_WREN)) {
                    EntityRegistry.addSpawn(EntityFairyWren.class, CreaturesConfig.fairywrenSpawnRate, CreaturesConfig.fairywrenMinGroup, CreaturesConfig.fairywrenMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.pygmyfalconSpawns) {
                EntityRegistry.addSpawn(EntityPygmyFalcon.class, CreaturesConfig.pygmyfalconSpawnRate, CreaturesConfig.pygmyfalconMinGroup, CreaturesConfig.pygmyfalconMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.DRY));
            }
            if (CreaturesConfig.barnowlSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(BARN_OWL)) {
                    EntityRegistry.addSpawn(EntityBarnOwl.class, CreaturesConfig.barnowlSpawnRate, CreaturesConfig.barnowlMinGroup, CreaturesConfig.barnowlMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.duckSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(DUCKS)) {
                    EntityRegistry.addSpawn(EntityWildDuck.class, CreaturesConfig.duckSpawnRate, CreaturesConfig.duckMinGroup, CreaturesConfig.duckMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.rollerSpawns) {
                EntityRegistry.addSpawn(EntityRoller.class, CreaturesConfig.rollerSpawnRate, CreaturesConfig.rollerMinGroup, CreaturesConfig.rollerMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST));
            }
            if (CreaturesConfig.goldfishSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(FRESHWATER_BIOMES)) {
                    EntityRegistry.addSpawn(EntityGoldfish.class, CreaturesConfig.goldfishSpawnRate, CreaturesConfig.goldfishMinGroup, CreaturesConfig.goldfishMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.ranchuSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(FRESHWATER_BIOMES)) {
                    EntityRegistry.addSpawn(EntityRanchuGoldfish.class, CreaturesConfig.ranchuSpawnRate, CreaturesConfig.ranchuMinGroup, CreaturesConfig.ranchuMaxGroup, EnumCreatureType.WATER_CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.chickadeeSpawns) {
                EntityRegistry.addSpawn(EntityChickadee.class, CreaturesConfig.chickadeeSpawnRate, CreaturesConfig.chickadeeMinGroup, CreaturesConfig.chickadeeMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST));
            }
            if (CreaturesConfig.pygmygooseSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(DUCKS)) {
                    EntityRegistry.addSpawn(EntityPygmyGoose.class, CreaturesConfig.pygmygooseSpawnRate, CreaturesConfig.pygmygooseMinGroup, CreaturesConfig.pygmygooseMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.swallowSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(SWALLOW)) {
                    EntityRegistry.addSpawn(EntitySwallow.class, CreaturesConfig.swallowSpawnRate, CreaturesConfig.swallowMinGroup, CreaturesConfig.swallowMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.ibisSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(DUCKS)) {
                    EntityRegistry.addSpawn(EntityIbis.class, CreaturesConfig.ibisSpawnRate, CreaturesConfig.ibisMinGroup, CreaturesConfig.ibisMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.woodduckSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(DUCKS)) {
                    EntityRegistry.addSpawn(EntityWoodDuck.class, CreaturesConfig.woodduckSpawnRate, CreaturesConfig.woodduckMinGroup, CreaturesConfig.woodduckMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.peafowlSpawns) {
                EntityRegistry.addSpawn(EntityPeafowl.class, CreaturesConfig.peafowlSpawnRate, CreaturesConfig.peafowlMinGroup, CreaturesConfig.peafowlMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST));
            }
            if (CreaturesConfig.sparrowSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(SPARROW)) {
                    EntityRegistry.addSpawn(EntitySparrow.class, CreaturesConfig.sparrowSpawnRate, CreaturesConfig.sparrowMinGroup, CreaturesConfig.sparrowMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.bushtitSpawns) {
                EntityRegistry.addSpawn(EntityBushtit.class, CreaturesConfig.bushtitSpawnRate, CreaturesConfig.bushtitMinGroup, CreaturesConfig.bushtitMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST));
            }
            if (CreaturesConfig.laughingthrushSpawns) {
                for (BiomeDictionary.Type t : RegistryHelper.Entities.getBiomeTypesFromString(THRUSH)) {
                    EntityRegistry.addSpawn(EntityLaughingthrush.class, CreaturesConfig.laughingthrushSpawnRate, CreaturesConfig.laughingthrushMinGroup, CreaturesConfig.laughingthrushMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(t));
                }
            }
            if (CreaturesConfig.eagleowlSpawns) {
                EntityRegistry.addSpawn(EntityEagleOwl.class, CreaturesConfig.eagleowlSpawnRate, CreaturesConfig.eagleowlMinGroup, CreaturesConfig.eagleowlMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST));
            }
            if (CreaturesConfig.robinSpawns) {
                EntityRegistry.addSpawn(EntityRobin.class, CreaturesConfig.robinSpawnRate, CreaturesConfig.robinMinGroup, CreaturesConfig.robinMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST));
            }
            if (CreaturesConfig.magpieSpawns) {
                EntityRegistry.addSpawn(EntityMagpie.class, CreaturesConfig.magpieSpawnRate, CreaturesConfig.magpieMinGroup, CreaturesConfig.magpieMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.FOREST));
            }
            if (CreaturesConfig.gooseSpawns) {
                EntityRegistry.addSpawn(EntityGoose.class, CreaturesConfig.gooseSpawnRate, CreaturesConfig.gooseMinGroup, CreaturesConfig.gooseMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.ospreySpawns) {
                EntityRegistry.addSpawn(EntityOsprey.class, CreaturesConfig.ospreySpawnRate, CreaturesConfig.ospreyMinGroup, CreaturesConfig.ospreyMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.kingfisherSpawns) {
                EntityRegistry.addSpawn(EntityKingfisher.class, CreaturesConfig.kingfisherSpawnRate, CreaturesConfig.kingfisherMinGroup, CreaturesConfig.kingfisherMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.RIVER));
            }
            if (CreaturesConfig.lapwingSpawns) {
                EntityRegistry.addSpawn(EntityLapwing.class, CreaturesConfig.lapwingSpawnRate, CreaturesConfig.lapwingMinGroup, CreaturesConfig.lapwingMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.PLAINS));
            }
            if (CreaturesConfig.pelicanSpawns) {
                EntityRegistry.addSpawn(EntityPelican.class, CreaturesConfig.pelicanSpawnRate, CreaturesConfig.pelicanMinGroup, CreaturesConfig.pelicanMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.BEACH));
            }
            if (CreaturesConfig.skuaSpawns) {
                EntityRegistry.addSpawn(EntitySkua.class, CreaturesConfig.skuaSpawnRate, CreaturesConfig.skuaMinGroup, CreaturesConfig.skuaMaxGroup, EnumCreatureType.CREATURE, RegistryHelper.Entities.grabBiomesFromType(BiomeDictionary.Type.COLD));
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
