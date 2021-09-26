package com.creatures.afrikinzi.config;

import com.creatures.afrikinzi.util.Reference;
import net.minecraftforge.common.config.Config;

@Config(modid = Reference.MOD_ID, name = "Creatures-" + Reference.VERSION+ "Config")
@Config.LangKey("config.creatures.title")
public class CreaturesConfig {
    @Config.Name("All spawns")
    @Config.Comment("Enables natural spawning of entities from Creatures.")
    public static boolean allSpawns = true;

    //water creatures

    //koi
    @Config.Name("Koi spawns")
    @Config.Comment("Enables natural spawning of Koi.")
    public static boolean koiSpawns = true;

    @Config.Name("Koi spawn rate")
    @Config.Comment("Spawn weight of Koi. (default: 80)")
    public static int koiSpawnRate = 80;

    //dottyback
    @Config.Name("Dottyback spawns")
    @Config.Comment("Enables natural spawning of Dottyback.")
    public static boolean dottybackSpawns = true;

    @Config.Name("Dottyback spawn rate")
    @Config.Comment("Spawn weight of Dottybacks. (default: 80)")
    public static int dottybackSpawnRate = 80;

    //pike
    @Config.Name("Pike spawns")
    @Config.Comment("Enables natural spawning of Pike.")
    public static boolean pikeSpawns = true;

    @Config.Name("Pike spawn rate")
    @Config.Comment("Spawn weight of Pike. (default: 80)")
    public static int pikeSpawnRate = 80;

    //arowana
    @Config.Name("Arowana spawns")
    @Config.Comment("Enables natural spawning of Arowana.")
    public static boolean arowanaSpawns = true;

    @Config.Name("Arowana spawn rate")
    @Config.Comment("Spawn weight of Arowana. (default: 10)")
    public static int arowanaSpawnRate = 10;

    //shrimp
    @Config.Name("Shrimp spawns")
    @Config.Comment("Enables natural spawning of Shrimp.")
    public static boolean shrimpSpawns = true;

    @Config.Name("Shrimp spawn rate")
    @Config.Comment("Spawn weight of Shrimp. (default: 80)")
    public static int shrimpSpawnRate = 80;

    //guppy
    @Config.Name("Guppy spawns")
    @Config.Comment("Enables natural spawning of Guppies.")
    public static boolean guppySpawns = true;

    @Config.Name("Guppy spawn rate")
    @Config.Comment("Spawn weight of Guppies. (default: 80)")
    public static int guppySpawnRate = 80;

    //gourami
    @Config.Name("Gourami spawns")
    @Config.Comment("Enables natural spawning of Gouramis.")
    public static boolean gouramiSpawns = true;

    @Config.Name("Gourami spawn rate")
    @Config.Comment("Spawn weight of Gouramis. (default: 80)")
    public static int gouramiSpawnRate = 80;

    //Ghost crab
    @Config.Name("Ghost crab spawns")
    @Config.Comment("Enables natural spawning of Ghost Crabs.")
    public static boolean ghostcrabSpawns = true;

    @Config.Name("Ghost crab spawn rate")
    @Config.Comment("Spawn weight of Ghost Crabs. (default: 80)")
    public static int ghostcrabSpawnRate = 80;

    //Goldfish
    @Config.Name("Goldfish spawns")
    @Config.Comment("Enables natural spawning of Goldfish.")
    public static boolean goldfishSpawns = true;

    @Config.Name("Goldfish spawn rate")
    @Config.Comment("Spawn weight of Goldfish. (default: 80)")
    public static int goldfishSpawnRate = 80;

    //Ranchu
    @Config.Name("Ranchu spawns")
    @Config.Comment("Enables natural spawning of Ranchu.")
    public static boolean ranchuSpawns = true;

    @Config.Name("Ranchu spawn rate")
    @Config.Comment("Spawn weight of Ranchu. (default: 80)")
    public static int ranchuSpawnRate = 80;

    //avians
    @Config.Name("Lovebird spawns")
    @Config.Comment("Enables natural spawning of Lovebirds.")
    public static boolean lovebirdSpawns = true;

    @Config.Name("Lovebird spawn rate")
    @Config.Comment("Spawn weight of Lovebirds. (default: 25)")
    public static int lovebirdSpawnRate = 25;

    //spoonbills
    @Config.Name("Spoonbill spawns")
    @Config.Comment("Enables natural spawning of Spoonbills.")
    public static boolean spoonbillSpawns = true;

    @Config.Name("Spoonbill spawn rate")
    @Config.Comment("Spawn weight of Spoonbills. (default: 15)")
    public static int spoonbillSpawnRate = 15;

    //mandarin duck
    @Config.Name("Mandarin Duck spawns")
    @Config.Comment("Enables natural spawning of Mandarin Ducks.")
    public static boolean mandarinduckSpawns = true;

    @Config.Name("Mandarin duck spawn rate")
    @Config.Comment("Spawn weight of Mandarin Ducks. (default: 15)")
    public static int mandarinduckSpawnRate = 15;

    //raven
    @Config.Name("Raven spawns")
    @Config.Comment("Enables natural spawning of Ravens.")
    public static boolean ravenSpawns = true;

    @Config.Name("Raven spawn rate")
    @Config.Comment("Spawn weight of Ravens. (default: 25)")
    public static int ravenSpawnRate = 25;

    //kakapo
    @Config.Name("Kakapo spawns")
    @Config.Comment("Enables natural spawning of Kakapos.")
    public static boolean kakapoSpawns = true;

    @Config.Name("Kakapo spawn rate")
    @Config.Comment("Spawn weight of Kakapos. (default: 3)")
    public static int kakapoSpawnRate = 3;

    //doves
    @Config.Name("Dove spawns")
    @Config.Comment("Enables natural spawning of Doves.")
    public static boolean doveSpawns = true;

    @Config.Name("Dove spawn rate")
    @Config.Comment("Spawn weight of Doves. (default: 25)")
    public static int doveSpawnRate = 25;

    //red kite
    @Config.Name("Red kite spawns")
    @Config.Comment("Enables natural spawning of Red Kites.")
    public static boolean redkiteSpawns = true;

    @Config.Name("Red kite spawn rate")
    @Config.Comment("Spawn weight of Red Kites. (default: 8)")
    public static int redkiteSpawnRate = 8;

    //golden eagle
    @Config.Name("Golden eagle spawns")
    @Config.Comment("Enables natural spawning of Golden Eagles.")
    public static boolean goldeneagleSpawns = true;

    @Config.Name("Golden eagle spawn rate")
    @Config.Comment("Spawn weight of Golden Eagles. (default: 8)")
    public static int goldeneagleSpawnRate = 8;

    //stellers sea eagle
    @Config.Name("Steller's sea eagle spawns")
    @Config.Comment("Enables natural spawning of Steller's Sea Eagle.")
    public static boolean stellersseaeagleSpawns = true;

    @Config.Name("Steller's sea eagle spawn rate")
    @Config.Comment("Spawn weight of Steller's Sea Eagle. (default: 3)")
    public static int stellersseaeagleSpawnRate = 3;

    //gyrfalcon
    @Config.Name("Gyrfalcon spawns")
    @Config.Comment("Enables natural spawning of Gyrfalcons.")
    public static boolean gyrfalconSpawns = true;

    @Config.Name("Gyrfalcon spawn rate")
    @Config.Comment("Spawn weight of Gyrfalcons. (default: 5)")
    public static int gyrfalconSpawnRate = 5;

    //conure
    @Config.Name("Conure spawns")
    @Config.Comment("Enables natural spawning of Conures.")
    public static boolean conureSpawns = true;

    @Config.Name("Conure spawn rate")
    @Config.Comment("Spawn weight of Conures. (default: 25)")
    public static int conureSpawnRate = 25;

    //lorikeet
    @Config.Name("Lorikeet spawns")
    @Config.Comment("Enables natural spawning of Lorikeets.")
    public static boolean lorikeetSpawns = true;

    @Config.Name("Lorikeet spawn rate")
    @Config.Comment("Spawn weight of Lorikeets. (default: 25)")
    public static int lorikeetSpawnRate = 25;

    //fairy wren
    @Config.Name("Fairy wren spawns")
    @Config.Comment("Enables natural spawning of Fairy Wrens.")
    public static boolean fairywrenSpawns = true;

    @Config.Name("Fairy wren spawn rate")
    @Config.Comment("Spawn weight of Fairywrens. (default: 5)")
    public static int fairywrenSpawnRate = 30;

    //pygmy falcon
    @Config.Name("Pygmy falcon spawns")
    @Config.Comment("Enables natural spawning of Pygmy Falcons.")
    public static boolean pygmyfalconSpawns = true;

    @Config.Name("pygmy falcon spawn rate")
    @Config.Comment("Spawn weight of Pygmy Falcons. (default: 5)")
    public static int pygmyfalconSpawnRate = 5;

    //barn owl
    @Config.Name("Barn owl spawns")
    @Config.Comment("Enables natural spawning of Barn Owls.")
    public static boolean barnowlSpawns = true;

    @Config.Name("Barn owl spawn rate")
    @Config.Comment("Spawn weight of Barn Owls. (default: 30)")
    public static int barnowlSpawnRate = 30;

    //duck
    @Config.Name("Duck spawns")
    @Config.Comment("Enables natural spawning of ducks.")
    public static boolean duckSpawns = true;

    @Config.Name("Duck spawn rate")
    @Config.Comment("Spawn weight of ducks. (default: 25)")
    public static int duckSpawnRate = 25;

    //roller
    @Config.Name("Roller spawns")
    @Config.Comment("Enables natural spawning of rollers.")
    public static boolean rollerSpawns = true;

    @Config.Name("Roller spawn rate")
    @Config.Comment("Spawn weight of rollers. (default: 25)")
    public static int rollerSpawnRate = 25;
}
