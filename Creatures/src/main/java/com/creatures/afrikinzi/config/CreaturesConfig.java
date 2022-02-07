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

    @Config.Name("Gameplay: Biome-specific variants")
    @Config.Comment("Enable or disable biome-specific variants")
    public static boolean biomeSpecificVariants = true;

    @Config.Name("Gameplay: Rare variants")
    @Config.Comment("Enable or disable rare variants")
    public static boolean rareVariants = true;

    @Config.Name("Gameplay: Pets teleport")
    @Config.Comment("Enable or disable pets teleporting")
    public static boolean teleporting = true;

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

    @Config.Name("Spawns: Koi min group")
    @Config.Comment("Min group of koi. (default: 11)")
    public static int koiMinGroup = 1;

    @Config.Name("Spawns: Koi max group")
    @Config.Comment("Max group of koi. (default: 3)")
    public static int koiMaxGroup = 3;

    //dottyback
    @Config.Name("Spawns: Dottyback spawns")
    @Config.Comment("Enables natural spawning of Dottyback.")
    public static boolean dottybackSpawns = true;

    @Config.Name("Spawns: Dottyback spawn rate")
    @Config.Comment("Spawn weight of Dottybacks. (default: 80)")
    public static int dottybackSpawnRate = 20;

    @Config.Name("Spawns: Dottyback min group")
    @Config.Comment("Min group of dottyback. (default: 2)")
    public static int dottybackMinGroup = 2;

    @Config.Name("Spawns: Ranchu max group")
    @Config.Comment("Max group of ranchu. (default: 5)")
    public static int dottybackMaxGroup = 5;

    //pike
    @Config.Name("Spawns: Pike spawns")
    @Config.Comment("Enables natural spawning of Pike.")
    public static boolean pikeSpawns = true;

    @Config.Name("Spawns: Pike spawn rate")
    @Config.Comment("Spawn weight of Pike. (default: 80)")
    public static int pikeSpawnRate = 20;

    @Config.Name("Spawns: Pike min group")
    @Config.Comment("Min group of pike. (default: 1)")
    public static int pikeMinGroup = 1;

    @Config.Name("Spawns: Ranchu max group")
    @Config.Comment("Max group of ranchu. (default: 1)")
    public static int pikeMaxGroup = 1;

    //arowana
    @Config.Name("Spawns: Arowana spawns")
    @Config.Comment("Enables natural spawning of Arowana.")
    public static boolean arowanaSpawns = true;

    @Config.Name("Spawns: Arowana spawn rate")
    @Config.Comment("Spawn weight of Arowana. (default: 10)")
    public static int arowanaSpawnRate = 10;

    @Config.Name("Spawns: Arowana min group")
    @Config.Comment("Min group of arowana. (default: 1)")
    public static int arowanaMinGroup = 1;

    @Config.Name("Spawns: Ranchu max group")
    @Config.Comment("Max group of ranchu. (default: 2)")
    public static int arowanaMaxGroup = 2;

    //shrimp
    @Config.Name("Spawns: Shrimp spawns")
    @Config.Comment("Enables natural spawning of Shrimp.")
    public static boolean shrimpSpawns = true;

    @Config.Name("Spawns: Shrimp spawn rate")
    @Config.Comment("Spawn weight of Shrimp. (default: 80)")
    public static int shrimpSpawnRate = 20;

    @Config.Name("Spawns: Shrimp min group")
    @Config.Comment("Min group of shrimp. (default: 4)")
    public static int shrimpMinGroup = 4;

    @Config.Name("Spawns: Shrimp max group")
    @Config.Comment("Max group of shrimp. (default: 10)")
    public static int shrimpMaxGroup = 10;

    //guppy
    @Config.Name("Spawns: Guppy spawns")
    @Config.Comment("Enables natural spawning of Guppies.")
    public static boolean guppySpawns = true;

    @Config.Name("Spawns: Guppy spawn rate")
    @Config.Comment("Spawn weight of Guppies. (default: 80)")
    public static int guppySpawnRate = 20;

    @Config.Name("Spawns: Guppy min group")
    @Config.Comment("Min group of guppies. (default: 3)")
    public static int guppyMinGroup = 3;

    @Config.Name("Spawns: Ranchu max group")
    @Config.Comment("Max group of ranchu. (default: 8)")
    public static int guppyMaxGroup = 8;

    //gourami
    @Config.Name("Spawns: Gourami spawns")
    @Config.Comment("Enables natural spawning of Gouramis.")
    public static boolean gouramiSpawns = true;

    @Config.Name("Spawns: Gourami spawn rate")
    @Config.Comment("Spawn weight of Gouramis. (default: 80)")
    public static int gouramiSpawnRate = 20;

    @Config.Name("Spawns: Gourami min group")
    @Config.Comment("Min group of gourami. (default: 2)")
    public static int gouramiMinGroup = 2;

    @Config.Name("Spawns: Gouramis max group")
    @Config.Comment("Max group of gouramis. (default: 3)")
    public static int gouramiMaxGroup = 3;

    //Ghost crab
    @Config.Name("Spawns: Ghost crab spawns")
    @Config.Comment("Enables natural spawning of Ghost Crabs.")
    public static boolean ghostcrabSpawns = true;

    @Config.Name("Spawns: Ghost crab spawn rate")
    @Config.Comment("Spawn weight of Ghost Crabs. (default: 80)")
    public static int ghostcrabSpawnRate = 80;

    @Config.Name("Spawns: Ghost Crab min group")
    @Config.Comment("Min group of ghost crabs. (default: 2)")
    public static int ghostcrabMinGroup = 2;

    @Config.Name("Spawns: Ghost Crab max group")
    @Config.Comment("Max group of ghost crabs. (default: 5)")
    public static int ghostcrabMaxGroup = 5;

    //Goldfish
    @Config.Name("Spawns: Goldfish spawns")
    @Config.Comment("Enables natural spawning of Goldfish.")
    public static boolean goldfishSpawns = true;

    @Config.Name("Spawns: Goldfish spawn rate")
    @Config.Comment("Spawn weight of Goldfish. (default: 80)")
    public static int goldfishSpawnRate = 20;

    @Config.Name("Spawns: Goldfish min group")
    @Config.Comment("Min group of goldfish. (default: 2)")
    public static int goldfishMinGroup = 2;

    @Config.Name("Spawns: Goldfish max group")
    @Config.Comment("Max group of goldfish. (default: 5)")
    public static int goldfishMaxGroup = 5;

    //Ranchu
    @Config.Name("Spawns: Ranchu spawns")
    @Config.Comment("Enables natural spawning of Ranchu.")
    public static boolean ranchuSpawns = true;

    @Config.Name("Spawns: Ranchu spawn rate")
    @Config.Comment("Spawn weight of Ranchu. (default: 80)")
    public static int ranchuSpawnRate = 20;

    @Config.Name("Spawns: Ranchu min group")
    @Config.Comment("Min group of ranchu. (default: 2)")
    public static int ranchuMinGroup = 2;

    @Config.Name("Spawns: Ranchu max group")
    @Config.Comment("Max group of ranchu. (default: 5)")
    public static int ranchuMaxGroup = 5;

    //Fire Goby
    @Config.Name("Spawns: Fire Goby spawns")
    @Config.Comment("Enables natural spawning of Fire Gobies.")
    public static boolean firegobySpawns = true;

    @Config.Name("Spawns: Fire Goby spawn rate")
    @Config.Comment("Spawn weight of Fire Gobies. (default: 70)")
    public static int firegobySpawnRate = 20;

    @Config.Name("Spawns: Fire Goby min group")
    @Config.Comment("Min group of fire gobies. (default: 1)")
    public static int firegobyMinGroup = 1;

    @Config.Name("Spawns: Fire Goby max group")
    @Config.Comment("Max group of fire gobies. (default: 1)")
    public static int firegobyMaxGroup = 1;

    //Blue Tang
    @Config.Name("Spawns: Blue Tang spawns")
    @Config.Comment("Enables natural spawning of Blue Tangs.")
    public static boolean bluetangSpawns = true;

    @Config.Name("Spawns: Blue Tang spawn rate")
    @Config.Comment("Spawn weight of Blue Tangs. (default: 70)")
    public static int bluetangSpawnRate = 20;

    @Config.Name("Spawns: Blue tang min group")
    @Config.Comment("Min group of blue tang. (default: 1)")
    public static int bluetangMinGroup = 1;

    @Config.Name("Spawns: Blue tang max group")
    @Config.Comment("Max group of blue tang. (default: 1)")
    public static int bluetangMaxGroup = 1;

    //Flame Angelfish
    @Config.Name("Spawns: Flame Angelfish spawns")
    @Config.Comment("Enables natural spawning of Flame Angelfish.")
    public static boolean flameangelfishSpawns = true;

    @Config.Name("Spawns: Flame Angelfish spawn rate")
    @Config.Comment("Spawn weight of Flame Angelfish. (default: 70)")
    public static int flameangelfishSpawnRate = 20;

    @Config.Name("Spawns: Flame angelfish min group")
    @Config.Comment("Min group of flame angelfish. (default: 1)")
    public static int flameangelfishMinGroup = 1;

    @Config.Name("Spawns: Flame angelfish max group")
    @Config.Comment("Max group of flame angelfish. (default: 1)")
    public static int flameangelfishMaxGroup = 1;

    //Trout
    @Config.Name("Spawns: Trout spawns")
    @Config.Comment("Enables natural spawning of Trout.")
    public static boolean troutSpawns = true;

    @Config.Name("Spawns: Trout spawn rate")
    @Config.Comment("Spawn weight of Trout. (default: 90)")
    public static int troutSpawnRate = 20;

    @Config.Name("Spawns: Trout min group")
    @Config.Comment("Min group of trout. (default: 1)")
    public static int troutMinGroup = 1;

    @Config.Name("Spawns: Trout max group")
    @Config.Comment("Max group of trout. (default: 2)")
    public static int troutMaxGroup = 2;

    //avians
    @Config.Name("Spawns: Lovebird spawns")
    @Config.Comment("Enables natural spawning of Lovebirds.")
    public static boolean lovebirdSpawns = true;

    @Config.Name("Spawns: Lovebird spawn rate")
    @Config.Comment("Spawn weight of Lovebirds. (default: 25)")
    public static int lovebirdSpawnRate = 25;

    @Config.Name("Spawns: Lovebird min group")
    @Config.Comment("Min group of lovebirds. (default: 2)")
    public static int lovebirdMinGroup = 2;

    @Config.Name("Spawns: Lovebird max group")
    @Config.Comment("Max group of lovebirds. (default: 6)")
    public static int lovebirdMaxGroup = 6;

    //spoonbills
    @Config.Name("Spawns: Spoonbill spawns")
    @Config.Comment("Enables natural spawning of Spoonbills.")
    public static boolean spoonbillSpawns = true;

    @Config.Name("Spawns: Spoonbill spawn rate")
    @Config.Comment("Spawn weight of Spoonbills. (default: 15)")
    public static int spoonbillSpawnRate = 15;

    @Config.Name("Spawns: Spoonbill min group")
    @Config.Comment("Min group of spoonbills. (default: 2")
    public static int spoonbillMinGroup = 2;

    @Config.Name("Spawns: Spoonbill max group")
    @Config.Comment("Max group of spoonbills. (default: 3)")
    public static int spoonbillMaxGroup = 3;

    //mandarin duck
    @Config.Name("Spawns: Mandarin Duck spawns")
    @Config.Comment("Enables natural spawning of Mandarin Ducks.")
    public static boolean mandarinduckSpawns = true;

    @Config.Name("Spawns: Mandarin duck spawn rate")
    @Config.Comment("Spawn weight of Mandarin Ducks. (default: 15)")
    public static int mandarinduckSpawnRate = 15;

    @Config.Name("Spawns: Mandarin duck min group")
    @Config.Comment("Min group of mandarin ducks. (default: 2)")
    public static int mandarinduckMinGroup = 2;

    @Config.Name("Spawns: Mandarin duck max group")
    @Config.Comment("Max group of mandarin ducks. (default: 3)")
    public static int mandarinduckMaxGroup = 3;

    //raven
    @Config.Name("Spawns: Raven spawns")
    @Config.Comment("Enables natural spawning of Ravens.")
    public static boolean ravenSpawns = true;

    @Config.Name("Spawns: Raven spawn rate")
    @Config.Comment("Spawn weight of Ravens. (default: 25)")
    public static int ravenSpawnRate = 25;

    @Config.Name("Spawns: Raven min group")
    @Config.Comment("Min group of ravens. (default: 1)")
    public static int ravenMinGroup = 1;

    @Config.Name("Spawns: Raven max group")
    @Config.Comment("Max group of ravens. (default: 3)")
    public static int ravenMaxGroup = 3;

    //kakapo
    @Config.Name("Spawns: Kakapo spawns")
    @Config.Comment("Enables natural spawning of Kakapos.")
    public static boolean kakapoSpawns = true;

    @Config.Name("Spawns: Kakapo spawn rate")
    @Config.Comment("Spawn weight of Kakapos. (default: 3)")
    public static int kakapoSpawnRate = 3;

    @Config.Name("Spawns: Kakapo min group")
    @Config.Comment("Min group of kakapos. (default: 1)")
    public static int kakapoMinGroup = 1;

    @Config.Name("Spawns: Kakapo max group")
    @Config.Comment("Max group of Kakapos. (default: 2)")
    public static int kakapoMaxGroup = 2;

    //doves
    @Config.Name("Spawns: Dove spawns")
    @Config.Comment("Enables natural spawning of Doves.")
    public static boolean doveSpawns = true;

    @Config.Name("Spawns: Dove spawn rate")
    @Config.Comment("Spawn weight of Doves. (default: 25)")
    public static int doveSpawnRate = 25;

    @Config.Name("Spawns: Dove min group")
    @Config.Comment("Min group of doves. (default: 2)")
    public static int doveMinGroup = 2;

    @Config.Name("Spawns: Dove max group")
    @Config.Comment("Max group of doves. (default: 5)")
    public static int doveMaxGroup = 5;

    //red kite
    @Config.Name("Spawns: Red kite spawns")
    @Config.Comment("Enables natural spawning of Red Kites.")
    public static boolean redkiteSpawns = true;

    @Config.Name("Spawns: Red kite spawn rate")
    @Config.Comment("Spawn weight of Red Kites. (default: 8)")
    public static int redkiteSpawnRate = 8;

    @Config.Name("Spawns: Red kites min group")
    @Config.Comment("Min group of red kites. (default: 1)")
    public static int redkiteMinGroup = 1;

    @Config.Name("Spawns: Red kite max group")
    @Config.Comment("Max group of red kites. (default: 2)")
    public static int redkiteMaxGroup = 2;

    //golden eagle
    @Config.Name("Spawns: Golden eagle spawns")
    @Config.Comment("Enables natural spawning of Golden Eagles.")
    public static boolean goldeneagleSpawns = true;

    @Config.Name("Spawns: Golden eagle spawn rate")
    @Config.Comment("Spawn weight of Golden Eagles. (default: 8)")
    public static int goldeneagleSpawnRate = 8;

    @Config.Name("Spawns: Golden eagle min group")
    @Config.Comment("Min group of golden eagles. (default: 1)")
    public static int goldeneagleMinGroup = 1;

    @Config.Name("Spawns: Gyrfalcon max group")
    @Config.Comment("Max group of gyrfalcon. (default: 2)")
    public static int goldeneagleMaxGroup = 2;

    //stellers sea eagle
    @Config.Name("Spawns: Steller's sea eagle spawns")
    @Config.Comment("Enables natural spawning of Steller's Sea Eagle.")
    public static boolean stellersseaeagleSpawns = true;

    @Config.Name("Spawns: Steller's sea eagle spawn rate")
    @Config.Comment("Spawn weight of Steller's Sea Eagle. (default: 3)")
    public static int stellersseaeagleSpawnRate = 3;

    @Config.Name("Spawns: Steller's sea eagle min group")
    @Config.Comment("Min group of Steller's sea eagle. (default: 1)")
    public static int stellersseaeagleMinGroup = 1;

    @Config.Name("Spawns: Steller's sea eagle max group")
    @Config.Comment("Max group of Steller's sea eagle. (default: 2)")
    public static int stellersseaeagleMaxGroup = 2;

    //gyrfalcon
    @Config.Name("Spawns: Gyrfalcon spawns")
    @Config.Comment("Enables natural spawning of Gyrfalcons.")
    public static boolean gyrfalconSpawns = true;

    @Config.Name("Spawns: Gyrfalcon spawn rate")
    @Config.Comment("Spawn weight of Gyrfalcons. (default: 5)")
    public static int gyrfalconSpawnRate = 5;

    @Config.Name("Spawns: Gyrfalcon min group")
    @Config.Comment("Min group of gyrfalcon. (default: 1)")
    public static int gyrfalconMinGroup = 1;

    @Config.Name("Spawns: Gyrfalcon max group")
    @Config.Comment("Max group of gyrfalcon. (default: 2)")
    public static int gyrfalconMaxGroup = 2;

    //conure
    @Config.Name("Spawns: Conure spawns")
    @Config.Comment("Enables natural spawning of Conures.")
    public static boolean conureSpawns = true;

    @Config.Name("Spawns: Conure spawn rate")
    @Config.Comment("Spawn weight of Conures. (default: 25)")
    public static int conureSpawnRate = 25;

    @Config.Name("Spawns: Conure min group")
    @Config.Comment("Min group of conures. (default: 3)")
    public static int conureMinGroup = 3;

    @Config.Name("Spawns: Conure max group")
    @Config.Comment("Max group of conures. (default: 5)")
    public static int conureMaxGroup = 5;

    //lorikeet
    @Config.Name("Spawns: Lorikeet spawns")
    @Config.Comment("Enables natural spawning of Lorikeets.")
    public static boolean lorikeetSpawns = true;

    @Config.Name("Spawns: Lorikeet spawn rate")
    @Config.Comment("Spawn weight of Lorikeets. (default: 25)")
    public static int lorikeetSpawnRate = 25;

    @Config.Name("Spawns: Lorikeet min group")
    @Config.Comment("Min group of lorikeets. (default: 2)")
    public static int lorikeetMinGroup = 2;

    @Config.Name("Spawns: Lorikeet max group")
    @Config.Comment("Max group of lorikeets. (default: 5)")
    public static int lorikeetMaxGroup = 5;

    //fairy wren
    @Config.Name("Spawns: Fairy wren spawns")
    @Config.Comment("Enables natural spawning of Fairy Wrens.")
    public static boolean fairywrenSpawns = true;

    @Config.Name("Spawns: Fairy wren spawn rate")
    @Config.Comment("Spawn weight of Fairywrens. (default: 5)")
    public static int fairywrenSpawnRate = 30;

    @Config.Name("Spawns: Fairywren min group")
    @Config.Comment("Min group of chickadee. (default: 3)")
    public static int fairywrenMinGroup = 3;

    @Config.Name("Spawns: Fairywren max group")
    @Config.Comment("Max group of fairywren. (default: 6)")
    public static int fairywrenMaxGroup = 6;

    //barn owl
    @Config.Name("Spawns: Barn owl spawns")
    @Config.Comment("Enables natural spawning of Barn Owls.")
    public static boolean barnowlSpawns = true;

    @Config.Name("Spawns: Barn owl spawn rate")
    @Config.Comment("Spawn weight of Barn Owls. (default: 9)")
    public static int barnowlSpawnRate = 9;

    @Config.Name("Spawns: Barn owl min group")
    @Config.Comment("Min group of barn owls. (default: 1)")
    public static int barnowlMinGroup = 1;

    @Config.Name("Spawns: Barn owl max group")
    @Config.Comment("Max group of chickadee. (default: 2)")
    public static int barnowlMaxGroup = 2;

    //duck
    @Config.Name("Spawns: Duck spawns")
    @Config.Comment("Enables natural spawning of ducks.")
    public static boolean duckSpawns = true;

    @Config.Name("Spawns: Duck spawn rate")
    @Config.Comment("Spawn weight of ducks. (default: 25)")
    public static int duckSpawnRate = 25;

    @Config.Name("Spawns: Duck min group")
    @Config.Comment("Min group of ducks. (default: 2)")
    public static int duckMinGroup = 2;

    @Config.Name("Spawns: Duck max group")
    @Config.Comment("Max group of ducks. (default: 3)")
    public static int duckMaxGroup = 3;

    //roller
    @Config.Name("Spawns: Roller spawns")
    @Config.Comment("Enables natural spawning of rollers.")
    public static boolean rollerSpawns = true;

    @Config.Name("Spawns: Roller spawn rate")
    @Config.Comment("Spawn weight of rollers. (default: 25)")
    public static int rollerSpawnRate = 25;

    @Config.Name("Spawns: Roller min group")
    @Config.Comment("Min group of rollers. (default: 2)")
    public static int rollerMinGroup = 2;

    @Config.Name("Spawns: Roller max group")
    @Config.Comment("Max group of roller. (default: 3)")
    public static int rollerMaxGroup = 3;

    //chickadees
    @Config.Name("Spawns: Chickadee spawns")
    @Config.Comment("Enables natural spawning of chickadees.")
    public static boolean chickadeeSpawns = true;

    @Config.Name("Spawns: Chickadee spawn rate")
    @Config.Comment("Spawn weight of chickadees. (default: 25)")
    public static int chickadeeSpawnRate = 25;

    @Config.Name("Spawns: Chickadee min group")
    @Config.Comment("Min group of chickadee. (default: 2)")
    public static int chickadeeMinGroup = 2;

    @Config.Name("Spawns: Chickadee max group")
    @Config.Comment("Max group of chickadee. (default: 3)")
    public static int chickadeeMaxGroup = 3;

    //pygmy goose
    @Config.Name("Spawns: Pygmy Goose spawns")
    @Config.Comment("Enables natural spawning of pygmy geese.")
    public static boolean pygmygooseSpawns = true;

    @Config.Name("Spawns: Pygmy Goose spawn rate")
    @Config.Comment("Spawn weight of pygmy geese. (default: 25)")
    public static int pygmygooseSpawnRate = 25;

    @Config.Name("Spawns: Pygmy Goose min group")
    @Config.Comment("Min group of pygmy geese. (default: 2)")
    public static int pygmygooseMinGroup = 2;

    @Config.Name("Spawns: Pygmy Goose max group")
    @Config.Comment("Max group of pygmy geese. (default: 3)")
    public static int pygmygooseMaxGroup = 3;

    //pygmy falcon
    @Config.Name("Spawns: Pygmy falcon spawns")
    @Config.Comment("Enables natural spawning of Pygmy Falcons.")
    public static boolean pygmyfalconSpawns = true;

    @Config.Name("Spawns: Pygmy falcon spawn rate")
    @Config.Comment("Spawn weight of Pygmy Falcons. (default: 5)")
    public static int pygmyfalconSpawnRate = 5;

    @Config.Name("Spawns: Pygmy falcon min group")
    @Config.Comment("Min group of Pygmy Falcons. (default: 1)")
    public static int pygmyfalconMinGroup = 1;

    @Config.Name("Spawns: Pygmy falcon max group")
    @Config.Comment("Max group of Pygmy Falcons. (default: 1)")
    public static int pygmyfalconMaxGroup = 2;
    //swallow
    @Config.Name("Spawns: Swallow spawns")
    @Config.Comment("Enables natural spawning of swallows.")
    public static boolean swallowSpawns = true;

    @Config.Name("Spawns: Swallow spawn rate")
    @Config.Comment("Spawn weight of swallows. (default: 25)")
    public static int swallowSpawnRate = 25;

    @Config.Name("Spawns: Swallow min group")
    @Config.Comment("Min group of swallows. (default: 3)")
    public static int swallowMinGroup = 3;

    @Config.Name("Spawns: Swallow max group")
    @Config.Comment("Max group of swallows. (default: 5)")
    public static int swallowMaxGroup = 5;

    //fiddler crab
    @Config.Name("Spawns: Fiddler crab spawns")
    @Config.Comment("Enables natural spawning of fiddler crabs.")
    public static boolean fiddlercrabSpawns = true;

    @Config.Name("Spawns: Fiddler crab spawn rate")
    @Config.Comment("Spawn weight of fiddler crabs. (default: 20)")
    public static int fiddlercrabSpawnRate = 20;

    @Config.Name("Spawns: Fiddler crab min group")
    @Config.Comment("Min group of swallows. (default: 2)")
    public static int fiddlercrabMinGroup = 2;

    @Config.Name("Spawns: Fiddler crab max group")
    @Config.Comment("Max group of fiddler crabs. (default: 4)")
    public static int fiddlercrabMaxGroup = 4;

    //ibis
    @Config.Name("Spawns: Ibis spawns")
    @Config.Comment("Enables natural spawning of ibises.")
    public static boolean ibisSpawns = true;

    @Config.Name("Spawns: Ibis spawn rate")
    @Config.Comment("Spawn weight of fiddler crabs. (default: 20)")
    public static int ibisSpawnRate = 20;

    @Config.Name("Spawns: Ibis min group")
    @Config.Comment("Min group of swallows. (default: 2)")
    public static int ibisMinGroup = 2;

    @Config.Name("Spawns: Ibis max group")
    @Config.Comment("Max group of ibises. (default: 4)")
    public static int ibisMaxGroup = 4;

    //red snapper
    @Config.Name("Spawns: Red snapper spawns")
    @Config.Comment("Enables natural spawning of ibises.")
    public static boolean redsnapperSpawns = true;

    @Config.Name("Spawns: Red snapper spawn rate")
    @Config.Comment("Spawn weight of red snappers. (default: 20)")
    public static int redsnapperSpawnRate = 20;

    @Config.Name("Spawns: Red snapper min group")
    @Config.Comment("Min group of red snappers. (default: 3)")
    public static int redsnapperMinGroup = 3;

    @Config.Name("Spawns: Red snapper max group")
    @Config.Comment("Max group of red snappers. (default: 7)")
    public static int redsnapperMaxGroup = 7;

    //wood duck
    @Config.Name("Spawns: Wood duck spawns")
    @Config.Comment("Enables natural spawning of wood ducks.")
    public static boolean woodduckSpawns = true;

    @Config.Name("Spawns: Wood duck spawn rate")
    @Config.Comment("Spawn weight of wood ducks. (default: 20)")
    public static int woodduckSpawnRate = 20;

    @Config.Name("Spawns: Wood duck min group")
    @Config.Comment("Min group of wood ducks. (default: 2)")
    public static int woodduckMinGroup = 2;

    @Config.Name("Spawns: Wood duck max group")
    @Config.Comment("Max group of wood ducks. (default: 3)")
    public static int woodduckMaxGroup = 3;

    //peafowl
    @Config.Name("Spawns: Peafowl spawns")
    @Config.Comment("Enables natural spawning of peafowl.")
    public static boolean peafowlSpawns = true;

    @Config.Name("Spawns: Peafowl spawn rate")
    @Config.Comment("Spawn weight of wood ducks. (default: 10)")
    public static int peafowlSpawnRate = 10;

    @Config.Name("Spawns: Peafowl min group")
    @Config.Comment("Min group of peafowl. (default: 2)")
    public static int peafowlMinGroup = 2;

    @Config.Name("Spawns: Peafowl max group")
    @Config.Comment("Max group of peafowl. (default: 3)")
    public static int peafowlMaxGroup = 3;

    //sparrow
    @Config.Name("Spawns: Sparrow spawns")
    @Config.Comment("Enables natural spawning of sparrows.")
    public static boolean sparrowSpawns = true;

    @Config.Name("Spawns: Sparrow spawn rate")
    @Config.Comment("Spawn weight of sparrows. (default: 30)")
    public static int sparrowSpawnRate = 30;

    @Config.Name("Spawns: Sparrow min group")
    @Config.Comment("Min group of sparrows. (default: 4)")
    public static int sparrowMinGroup = 4;

    @Config.Name("Spawns: Sparrow max group")
    @Config.Comment("Max group of sparrows. (default: 7)")
    public static int sparrowMaxGroup = 7;

    //bushtit
    @Config.Name("Spawns: Bushtit spawns")
    @Config.Comment("Enables natural spawning of bushtits.")
    public static boolean bushtitSpawns = true;

    @Config.Name("Spawns: Bushtit spawn rate")
    @Config.Comment("Spawn weight of bushtits. (default: 20)")
    public static int bushtitSpawnRate = 20;

    @Config.Name("Spawns: Bushtit min group")
    @Config.Comment("Min group of bushtits. (default: 3)")
    public static int bushtitMinGroup = 3;

    @Config.Name("Spawns: Bushtit max group")
    @Config.Comment("Max group of bushtits. (default: 5)")
    public static int bushtitMaxGroup = 5;

    //laughingthrush
    @Config.Name("Spawns: Laughingthrush spawns")
    @Config.Comment("Enables natural spawning of laughingthrushes.")
    public static boolean laughingthrushSpawns = true;

    @Config.Name("Spawns: Laughingthrush spawn rate")
    @Config.Comment("Spawn weight of laughingthrushes. (default: 20)")
    public static int laughingthrushSpawnRate = 20;

    @Config.Name("Spawns: Laughingthrush min group")
    @Config.Comment("Min group of laughingthrushes. (default: 3)")
    public static int laughingthrushMinGroup = 3;

    @Config.Name("Spawns: Laughingthrush max group")
    @Config.Comment("Max group of laughingthrushes. (default: 5)")
    public static int laughingthrushMaxGroup = 5;

    //eagleowl
    @Config.Name("Spawns: Eagle owl spawns")
    @Config.Comment("Enables natural spawning of eagle owls.")
    public static boolean eagleowlSpawns = true;

    @Config.Name("Spawns: Eagle owl spawn rate")
    @Config.Comment("Spawn weight of eagle owls. (default: 10)")
    public static int eagleowlSpawnRate = 10;

    @Config.Name("Spawns: Eagle owl min group")
    @Config.Comment("Min group of eagle owls. (default: 1)")
    public static int eagleowlMinGroup = 1;

    @Config.Name("Spawns: Eagle owl max group")
    @Config.Comment("Max group of eagle owls. (default: 2)")
    public static int eagleowlMaxGroup = 2;

    //robin
    @Config.Name("Spawns: Robin spawns")
    @Config.Comment("Enables natural spawning of robins.")
    public static boolean robinSpawns = true;

    @Config.Name("Spawns: Robin spawn rate")
    @Config.Comment("Spawn weight of robins. (default: 20)")
    public static int robinSpawnRate = 20;

    @Config.Name("Spawns: Robin min group")
    @Config.Comment("Min group of robins. (default: 3)")
    public static int robinMinGroup = 3;

    @Config.Name("Spawns: Robin max group")
    @Config.Comment("Max group of robins. (default: 4)")
    public static int robinMaxGroup = 4;

    //magpie
    @Config.Name("Spawns: Magpie spawns")
    @Config.Comment("Enables natural spawning of magpies.")
    public static boolean magpieSpawns = true;

    @Config.Name("Spawns: Magpie spawn rate")
    @Config.Comment("Spawn weight of magpies. (default: 20)")
    public static int magpieSpawnRate = 20;

    @Config.Name("Spawns: Magpie min group")
    @Config.Comment("Min group of magpies. (default: 3)")
    public static int magpieMinGroup = 3;

    @Config.Name("Spawns: Magpie max group")
    @Config.Comment("Max group of magpies. (default: 4)")
    public static int magpieMaxGroup = 4;

}
