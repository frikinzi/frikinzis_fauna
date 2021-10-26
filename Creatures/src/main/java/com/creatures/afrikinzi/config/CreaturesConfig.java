package com.creatures.afrikinzi.config;

import com.creatures.afrikinzi.util.Reference;
import net.minecraftforge.common.config.Config;

@Config(modid = Reference.MOD_ID, name = "Frikinzis_Fauna_" + Reference.VERSION + "_Config")
@Config.LangKey("config.creatures.title")
public class CreaturesConfig {
    //Gameplay
    @Config.Name("Gameplay: Eagles attack other animals")
    @Config.Comment("Set to true if you want eagles to naturally attack other animals when untamed")
    public static boolean eagleAttacks = true;

    @Config.Name("Gameplay: Fishing")
    @Config.Comment("Set to true if you want this mod's fish to be in fishing loot")
    public static boolean fishLoot = true;

    @Config.Name("Gameplay: Fish Despawn")
    @Config.Comment("Set to true if you want fish to despawn naturally")
    public static boolean fishDespawns = true;

    @Config.Name("Gameplay: Villager trades")
    @Config.Comment("Enable or disable villager trades with Creatures items")
    public static boolean villagerTrades = true;

    @Config.Name("Gameplay: Eagle throwing prey up high")
    @Config.Comment("Enable or disable eagles throwing prey up high upon contact")
    public static boolean eagleThrows = true;

    @Config.Name("Gameplay: Raven giving gifts")
    @Config.Comment("Enable or disable ravens giving gifts")
    public static boolean ravenGifts = true;

    @Config.Name("Gameplay: Tamed birds follow")
    @Config.Comment("Enable or disable tamed birds (parrots, ravens, doves) following")
    public static boolean birdsFollow = true;

    @Config.Name("Gameplay: Tamed raptors follow")
    @Config.Comment("Enable or disable tamed raptors following")
    public static boolean raptorsFollow = true;

    @Config.Name("Gameplay: Mutation chance for lovebirds")
    @Config.Comment("Chance of mutation occuring when breeding lovebirds (1/x, so the higher the number the lower the chance)")
    public static int mutationLovebird = 10;

    @Config.Name("Gameplay: Mutation chance for lorikeets")
    @Config.Comment("Chance of mutation occuring when breeding lorikeets (1/x, so the higher the number the lower the chance)")
    public static int mutationLorikeet = 10;

    @Config.Name("Gameplay: Mutation chance for albino raven")
    @Config.Comment("Chance of mutation occuring when breeding ravens (1/x, so the higher the number the lower the chance)")
    public static int albinoRaven = 1000;

    @Config.Name("Spawns: All spawns")
    @Config.Comment("Enables natural spawning of entities from Creatures.")
    public static boolean allSpawns = true;

    //water creatures

    //koi
    @Config.Name("Spawns: Koi spawns")
    @Config.Comment("Enables natural spawning of Koi.")
    public static boolean koiSpawns = true;

    @Config.Name("Spawns: Koi spawn rate")
    @Config.Comment("Spawn weight of Koi. (default: 80)")
    public static int koiSpawnRate = 20;

    //dottyback
    @Config.Name("Spawns: Dottyback spawns")
    @Config.Comment("Enables natural spawning of Dottyback.")
    public static boolean dottybackSpawns = true;

    @Config.Name("Spawns: Dottyback spawn rate")
    @Config.Comment("Spawn weight of Dottybacks. (default: 80)")
    public static int dottybackSpawnRate = 20;

    //pike
    @Config.Name("Spawns: Pike spawns")
    @Config.Comment("Enables natural spawning of Pike.")
    public static boolean pikeSpawns = true;

    @Config.Name("Spawns: Pike spawn rate")
    @Config.Comment("Spawn weight of Pike. (default: 80)")
    public static int pikeSpawnRate = 20;

    //arowana
    @Config.Name("Spawns: Arowana spawns")
    @Config.Comment("Enables natural spawning of Arowana.")
    public static boolean arowanaSpawns = true;

    @Config.Name("Spawns: Arowana spawn rate")
    @Config.Comment("Spawn weight of Arowana. (default: 10)")
    public static int arowanaSpawnRate = 10;

    //shrimp
    @Config.Name("Spawns: Shrimp spawns")
    @Config.Comment("Enables natural spawning of Shrimp.")
    public static boolean shrimpSpawns = true;

    @Config.Name("Spawns: Shrimp spawn rate")
    @Config.Comment("Spawn weight of Shrimp. (default: 80)")
    public static int shrimpSpawnRate = 20;

    //guppy
    @Config.Name("Spawns: Guppy spawns")
    @Config.Comment("Enables natural spawning of Guppies.")
    public static boolean guppySpawns = true;

    @Config.Name("Spawns: Guppy spawn rate")
    @Config.Comment("Spawn weight of Guppies. (default: 80)")
    public static int guppySpawnRate = 20;

    //gourami
    @Config.Name("Spawns: Gourami spawns")
    @Config.Comment("Enables natural spawning of Gouramis.")
    public static boolean gouramiSpawns = true;

    @Config.Name("Spawns: Gourami spawn rate")
    @Config.Comment("Spawn weight of Gouramis. (default: 80)")
    public static int gouramiSpawnRate = 20;

    //Ghost crab
    @Config.Name("Spawns: Ghost crab spawns")
    @Config.Comment("Enables natural spawning of Ghost Crabs.")
    public static boolean ghostcrabSpawns = true;

    @Config.Name("Spawns: Ghost crab spawn rate")
    @Config.Comment("Spawn weight of Ghost Crabs. (default: 80)")
    public static int ghostcrabSpawnRate = 80;

    //Goldfish
    @Config.Name("Spawns: Goldfish spawns")
    @Config.Comment("Enables natural spawning of Goldfish.")
    public static boolean goldfishSpawns = true;

    @Config.Name("Spawns: Goldfish spawn rate")
    @Config.Comment("Spawn weight of Goldfish. (default: 80)")
    public static int goldfishSpawnRate = 20;

    //Ranchu
    @Config.Name("Spawns: Ranchu spawns")
    @Config.Comment("Enables natural spawning of Ranchu.")
    public static boolean ranchuSpawns = true;

    @Config.Name("Spawns: Ranchu spawn rate")
    @Config.Comment("Spawn weight of Ranchu. (default: 80)")
    public static int ranchuSpawnRate = 20;

    //Fire Goby
    @Config.Name("Spawns: Fire Goby spawns")
    @Config.Comment("Enables natural spawning of Fire Gobies.")
    public static boolean firegobySpawns = true;

    @Config.Name("Spawns: Ranchu spawn rate")
    @Config.Comment("Spawn weight of Fire Gobies. (default: 70)")
    public static int firegobySpawnRate = 20;

    //Blue Tang
    @Config.Name("Spawns: Blue Tang spawns")
    @Config.Comment("Enables natural spawning of Blue Tangs.")
    public static boolean bluetangSpawns = true;

    @Config.Name("Spawns: Blue Tang spawn rate")
    @Config.Comment("Spawn weight of Blue Tangs. (default: 70)")
    public static int bluetangSpawnRate = 20;

    //Flame Angelfish
    @Config.Name("Spawns: Flame Angelfish spawns")
    @Config.Comment("Enables natural spawning of Flame Angelfish.")
    public static boolean flameangelfishSpawns = true;

    @Config.Name("Spawns: Flame Angelfish spawn rate")
    @Config.Comment("Spawn weight of Flame Angelfish. (default: 70)")
    public static int flameangelfishSpawnRate = 20;

    //Trout
    @Config.Name("Spawns: Trout spawns")
    @Config.Comment("Enables natural spawning of Trout.")
    public static boolean troutSpawns = true;

    @Config.Name("Spawns: Trout spawn rate")
    @Config.Comment("Spawn weight of Trout. (default: 90)")
    public static int troutSpawnRate = 20;

    //avians
    @Config.Name("Spawns: Lovebird spawns")
    @Config.Comment("Enables natural spawning of Lovebirds.")
    public static boolean lovebirdSpawns = true;

    @Config.Name("Spawns: Lovebird spawn rate")
    @Config.Comment("Spawn weight of Lovebirds. (default: 25)")
    public static int lovebirdSpawnRate = 25;

    //spoonbills
    @Config.Name("Spawns: Spoonbill spawns")
    @Config.Comment("Enables natural spawning of Spoonbills.")
    public static boolean spoonbillSpawns = true;

    @Config.Name("Spawns: Spoonbill spawn rate")
    @Config.Comment("Spawn weight of Spoonbills. (default: 15)")
    public static int spoonbillSpawnRate = 15;

    //mandarin duck
    @Config.Name("Spawns: Mandarin Duck spawns")
    @Config.Comment("Enables natural spawning of Mandarin Ducks.")
    public static boolean mandarinduckSpawns = true;

    @Config.Name("Spawns: Mandarin duck spawn rate")
    @Config.Comment("Spawn weight of Mandarin Ducks. (default: 15)")
    public static int mandarinduckSpawnRate = 15;

    //raven
    @Config.Name("Spawns: Raven spawns")
    @Config.Comment("Enables natural spawning of Ravens.")
    public static boolean ravenSpawns = true;

    @Config.Name("Spawns: Raven spawn rate")
    @Config.Comment("Spawn weight of Ravens. (default: 25)")
    public static int ravenSpawnRate = 25;

    //kakapo
    @Config.Name("Spawns: Kakapo spawns")
    @Config.Comment("Enables natural spawning of Kakapos.")
    public static boolean kakapoSpawns = true;

    @Config.Name("Spawns: Kakapo spawn rate")
    @Config.Comment("Spawn weight of Kakapos. (default: 3)")
    public static int kakapoSpawnRate = 3;

    //doves
    @Config.Name("Spawns: Dove spawns")
    @Config.Comment("Enables natural spawning of Doves.")
    public static boolean doveSpawns = true;

    @Config.Name("Spawns: Dove spawn rate")
    @Config.Comment("Spawn weight of Doves. (default: 25)")
    public static int doveSpawnRate = 25;

    //red kite
    @Config.Name("Spawns: Red kite spawns")
    @Config.Comment("Enables natural spawning of Red Kites.")
    public static boolean redkiteSpawns = true;

    @Config.Name("Spawns: Red kite spawn rate")
    @Config.Comment("Spawn weight of Red Kites. (default: 8)")
    public static int redkiteSpawnRate = 8;

    //golden eagle
    @Config.Name("Spawns: Golden eagle spawns")
    @Config.Comment("Enables natural spawning of Golden Eagles.")
    public static boolean goldeneagleSpawns = true;

    @Config.Name("Spawns: Golden eagle spawn rate")
    @Config.Comment("Spawn weight of Golden Eagles. (default: 8)")
    public static int goldeneagleSpawnRate = 8;

    //stellers sea eagle
    @Config.Name("Spawns: Steller's sea eagle spawns")
    @Config.Comment("Enables natural spawning of Steller's Sea Eagle.")
    public static boolean stellersseaeagleSpawns = true;

    @Config.Name("Spawns: Steller's sea eagle spawn rate")
    @Config.Comment("Spawn weight of Steller's Sea Eagle. (default: 3)")
    public static int stellersseaeagleSpawnRate = 3;

    //gyrfalcon
    @Config.Name("Spawns: Gyrfalcon spawns")
    @Config.Comment("Enables natural spawning of Gyrfalcons.")
    public static boolean gyrfalconSpawns = true;

    @Config.Name("Spawns: Gyrfalcon spawn rate")
    @Config.Comment("Spawn weight of Gyrfalcons. (default: 5)")
    public static int gyrfalconSpawnRate = 5;

    //conure
    @Config.Name("Spawns: Conure spawns")
    @Config.Comment("Enables natural spawning of Conures.")
    public static boolean conureSpawns = true;

    @Config.Name("Spawns: Conure spawn rate")
    @Config.Comment("Spawn weight of Conures. (default: 25)")
    public static int conureSpawnRate = 25;

    //lorikeet
    @Config.Name("Spawns: Lorikeet spawns")
    @Config.Comment("Enables natural spawning of Lorikeets.")
    public static boolean lorikeetSpawns = true;

    @Config.Name("Spawns: Lorikeet spawn rate")
    @Config.Comment("Spawn weight of Lorikeets. (default: 25)")
    public static int lorikeetSpawnRate = 25;

    //fairy wren
    @Config.Name("Spawns: Fairy wren spawns")
    @Config.Comment("Enables natural spawning of Fairy Wrens.")
    public static boolean fairywrenSpawns = true;

    @Config.Name("Spawns: Fairy wren spawn rate")
    @Config.Comment("Spawn weight of Fairywrens. (default: 5)")
    public static int fairywrenSpawnRate = 30;

    //barn owl
    @Config.Name("Spawns: Barn owl spawns")
    @Config.Comment("Enables natural spawning of Barn Owls.")
    public static boolean barnowlSpawns = true;

    @Config.Name("Spawns: Barn owl spawn rate")
    @Config.Comment("Spawn weight of Barn Owls. (default: 30)")
    public static int barnowlSpawnRate = 30;

    //duck
    @Config.Name("Spawns: Duck spawns")
    @Config.Comment("Enables natural spawning of ducks.")
    public static boolean duckSpawns = true;

    @Config.Name("Spawns: Duck spawn rate")
    @Config.Comment("Spawn weight of ducks. (default: 25)")
    public static int duckSpawnRate = 25;

    //roller
    @Config.Name("Spawns: Roller spawns")
    @Config.Comment("Enables natural spawning of rollers.")
    public static boolean rollerSpawns = true;

    @Config.Name("Spawns: Roller spawn rate")
    @Config.Comment("Spawn weight of rollers. (default: 25)")
    public static int rollerSpawnRate = 25;

    //chickadees
    @Config.Name("Spawns: Chickadee spawns")
    @Config.Comment("Enables natural spawning of chickadees.")
    public static boolean chickadeeSpawns = true;

    @Config.Name("Spawns: Chickadee spawn rate")
    @Config.Comment("Spawn weight of chickadees. (default: 25)")
    public static int chickadeeSpawnRate = 25;

    //pygmy goose
    @Config.Name("Spawns: Pygmy Goose spawns")
    @Config.Comment("Enables natural spawning of pygmy geese.")
    public static boolean pygmygooseSpawns = true;

    @Config.Name("Spawns: Pygmy Goose spawn rate")
    @Config.Comment("Spawn weight of pygmy geese. (default: 25)")
    public static int pygmygooseSpawnRate = 25;

    //pygmy falcon
    @Config.Name("Spawns: Pygmy falcon spawns")
    @Config.Comment("Enables natural spawning of Pygmy Falcons.")
    public static boolean pygmyfalconSpawns = true;

    @Config.Name("Spawns: Pygmy falcon spawn rate")
    @Config.Comment("Spawn weight of Pygmy Falcons. (default: 5)")
    public static int pygmyfalconSpawnRate = 5;
    //avians
    @Config.Name("Spawns: Swallow spawns")
    @Config.Comment("Enables natural spawning of swallows.")
    public static boolean swallowSpawns = true;

    @Config.Name("Spawns: Swallow spawn rate")
    @Config.Comment("Spawn weight of swallows. (default: 25)")
    public static int swallowSpawnRate = 25;

}
