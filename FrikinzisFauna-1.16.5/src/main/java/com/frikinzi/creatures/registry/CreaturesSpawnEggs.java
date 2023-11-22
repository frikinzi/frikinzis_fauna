package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.item.ModSpawnEgg;
<<<<<<< Updated upstream
=======
import com.frikinzi.creatures.item.ModSpawnEggVariants;
>>>>>>> Stashed changes
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CreaturesSpawnEggs {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Creatures.MODID);



    public static final RegistryObject<ModSpawnEgg> KOI_SPAWN_EGG = ITEMS.register("koi_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.KOI.get(), 16731716, 16777215,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> LOVEBIRD_SPAWN_EGG = ITEMS.register("lovebird_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.LOVEBIRD.get(), 16749375, 16765696,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> DOTTYBACK_SPAWN_EGG = ITEMS.register("dottyback_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.DOTTYBACK.get(), 16740608, 6058495,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.LOVEBIRD.get(), 16749375, 16765696,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> DOTTYBACK_SPAWN_EGG = ITEMS.register("dottyback_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.DOTTYBACK.get(), 16740608, 6058495,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PIKE_SPAWN_EGG = ITEMS.register("pike_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.PIKE.get(), 10973747, 16777215,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GUPPY_SPAWN_EGG = ITEMS.register("guppy_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.GUPPY.get(), 8578898, 8579010,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SPOONBILL_SPAWN_EGG = ITEMS.register("creatures_spoonbill_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.SPOONBILL.get(), 16490917, 16583198,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.GUPPY.get(), 8578898, 8579010,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SPOONBILL_SPAWN_EGG = ITEMS.register("creatures_spoonbill_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.SPOONBILL.get(), 16490917, 16583198,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> KAKAPO_SPAWN_EGG = ITEMS.register("kakapo_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.KAKAPO.get(), 9607980, 12299667,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> MANDARIN_DUCK_SPAWN_EGG = ITEMS.register("mandarin_duck_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.MANDARIN_DUCK.get(), 11798553, 16640178,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> AROWANA_SPAWN_EGG = ITEMS.register("arowana_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.AROWANA.get(), 16771881, 16758578,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> RAVEN_SPAWN_EGG = ITEMS.register("raven_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.RAVEN.get(), 9536, 4152450,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SHRIMP_SPAWN_EGG = ITEMS.register("shrimp_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.SHRIMP.get(), 16583198, 16490917,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> DOVE_SPAWN_EGG = ITEMS.register("dove_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.DOVE.get(), 14935271, 15395538,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.RAVEN.get(), 9536, 4152450,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SHRIMP_SPAWN_EGG = ITEMS.register("shrimp_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.SHRIMP.get(), 16583198, 16490917,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> DOVE_SPAWN_EGG = ITEMS.register("dove_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.DOVE.get(), 14935271, 15395538,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> RED_KITE_SPAWN_EGG = ITEMS.register("red_kite_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.RED_KITE.get(), 13651220, 4152450,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GOLDEN_EAGLE_SPAWN_EGG = ITEMS.register("golden_eagle_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.GOLDEN_EAGLE.get(), 4525319, 10125934,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> STELLERS_SEA_EAGLE_SPAWN_EGG = ITEMS.register("stellers_sea_eagle_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.STELLERS_SEA_EAGLE.get(), 5322812, 16515071,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GYRFALCON_SPAWN_EGG = ITEMS.register("gyrfalcon_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.GYRFALCON.get(), 16515071, 9536,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> LORIKEET_SPAWN_EGG = ITEMS.register("lorikeet_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.LORIKEET.get(), 3093151, 16718368,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> CONURE_SPAWN_EGG = ITEMS.register("conure_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.CONURE.get(), 16170000, 2182420,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> FAIRYWREN_SPAWN_EGG = ITEMS.register("fairywren_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.FAIRYWREN.get(), 1650103, 41,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.LORIKEET.get(), 3093151, 16718368,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> CONURE_SPAWN_EGG = ITEMS.register("conure_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.CONURE.get(), 16170000, 2182420,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> FAIRYWREN_SPAWN_EGG = ITEMS.register("fairywren_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.FAIRYWREN.get(), 1650103, 41,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GHOST_CRAB_SPAWN_EGG = ITEMS.register("ghostcrab_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.GHOST_CRAB.get(), 13545576, 16777215,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GOURAMI_SPAWN_EGG = ITEMS.register("gourami_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.GOURAMI.get(), 4152450, 16777215,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.GOURAMI.get(), 4152450, 16777215,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PYGMY_FALCON_EGG = ITEMS.register("pygmy_falcon_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.PYGMY_FALCON.get(), 8097951, 15724012,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> BARN_OWL_SPAWN_EGG = ITEMS.register("barn_owl_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.BARN_OWL.get(), 16777215, 11897942,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> WILD_DUCK_SPAWN_EGG = ITEMS.register("wild_duck_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.WILD_DUCK.get(), 15702874, 7901340,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> ROLLER_SPAWN_EGG = ITEMS.register("roller_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.ROLLER.get(), 1414724, 13192647,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.WILD_DUCK.get(), 15702874, 7901340,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> ROLLER_SPAWN_EGG = ITEMS.register("roller_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.ROLLER.get(), 1414724, 13192647,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GOLDFISH_SPAWN_EGG = ITEMS.register("goldfish_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.GOLDFISH.get(), 14501642, 14318603,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> RANCHU_SPAWN_EGG = ITEMS.register("ranchu_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.RANCHU.get(), 14501642, 11,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> CHICKADEE_SPAWN_EGG = ITEMS.register("chickadee_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.CHICKADEE.get(), 13879499, 11,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PYGMY_GOOSE_SPAWN_EGG = ITEMS.register("pygmy_goose_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.PYGMY_GOOSE.get(), 2772553, 15964498,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.CHICKADEE.get(), 13879499, 11,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PYGMY_GOOSE_SPAWN_EGG = ITEMS.register("pygmy_goose_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.PYGMY_GOOSE.get(), 2772553, 15964498,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> FIRE_GOBY_SPAWN_EGG = ITEMS.register("fire_goby_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.FIRE_GOBY.get(), 14080426, 15964498,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> BLUE_TANG_SPAWN_EGG = ITEMS.register("blue_tang_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.BLUE_TANG.get(), 3895524, 722696,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> FLAME_ANGELFISH_SPAWN_EGG = ITEMS.register("flame_angelfish_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.FLAME_ANGELFISH.get(), 16396073, 16611846,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> TROUT_SPAWN_EGG = ITEMS.register("trout_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.TROUT.get(), 9861465, 11822456,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SWALLOW_SPAWN_EGG = ITEMS.register("swallow_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.SWALLOW.get(), 6588890, 11953480,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> FIDDLER_CRAB_SPAWN_EGG = ITEMS.register("fiddler_crab_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.FIDDLER_CRAB.get(), 526085, 9297374,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> IBIS_SPAWN_EGG = ITEMS.register("ibis_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.IBIS.get(), 13178131, 13847612,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> RED_SNAPPER_SPAWN_EGG = ITEMS.register("red_snapper_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.RED_SNAPPER.get(), 15320002, 13979723,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.TROUT.get(), 9861465, 11822456,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SWALLOW_SPAWN_EGG = ITEMS.register("swallow_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.SWALLOW.get(), 6588890, 11953480,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> FIDDLER_CRAB_SPAWN_EGG = ITEMS.register("fiddler_crab_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.FIDDLER_CRAB.get(), 526085, 9297374,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> IBIS_SPAWN_EGG = ITEMS.register("ibis_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.IBIS.get(), 13178131, 13847612,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> RED_SNAPPER_SPAWN_EGG = ITEMS.register("red_snapper_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.RED_SNAPPER.get(), 15320002, 13979723,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> WOOD_DUCK_SPAWN_EGG = ITEMS.register("wood_duck_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.WOOD_DUCK.get(), 2967328, 7548455,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PEAFOWL_SPAWN_EGG = ITEMS.register("peafowl_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.PEAFOWL.get(), 32466, 2450496,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SPARROW_SPAWN_EGG = ITEMS.register("sparrow_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.SPARROW.get(), 9004362, 13743005,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> BUSHTIT_SPAWN_EGG = ITEMS.register("bushtit_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.BUSHTIT.get(), 16382707, 9004362,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> EAGLEOWL_SPAWN_EGG = ITEMS.register("eagleowl_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.EAGLEOWL.get(), 13608552, 4798501,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> ROBIN_SPAWN_EGG = ITEMS.register("robin_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.ROBIN.get(), 9600343, 13723915,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> LAUGHINGTHRUSH_SPAWN_EGG = ITEMS.register("laughingthrush_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.LAUGHINGTHRUSH.get(), 8873290, 15720079,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> MAGPIE_SPAWN_EGG = ITEMS.register("magpie_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.MAGPIE.get(), 1908259, 4480395,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GOOSE_SPAWN_EGG = ITEMS.register("goose_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.GOOSE.get(), 7888451, 525056,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.PEAFOWL.get(), 32466, 2450496,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SPARROW_SPAWN_EGG = ITEMS.register("sparrow_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.SPARROW.get(), 9004362, 13743005,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> BUSHTIT_SPAWN_EGG = ITEMS.register("bushtit_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.BUSHTIT.get(), 16382707, 9004362,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> EAGLEOWL_SPAWN_EGG = ITEMS.register("eagleowl_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.EAGLEOWL.get(), 13608552, 4798501,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> ROBIN_SPAWN_EGG = ITEMS.register("robin_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.ROBIN.get(), 9600343, 13723915,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> LAUGHINGTHRUSH_SPAWN_EGG = ITEMS.register("laughingthrush_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.LAUGHINGTHRUSH.get(), 8873290, 15720079,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> MAGPIE_SPAWN_EGG = ITEMS.register("magpie_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.MAGPIE.get(), 1908259, 4480395,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GOOSE_SPAWN_EGG = ITEMS.register("goose_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.GOOSE.get(), 7888451, 525056,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> OSPREY_SPAWN_EGG = ITEMS.register("osprey_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.OSPREY.get(), 14869470, 4732721,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> KINGFISHER_SPAWN_EGG = ITEMS.register("kingfisher_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.KINGFISHER.get(), 29897, 13722630,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PELICAN_SPAWN_EGG = ITEMS.register("pelican_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.PELICAN.get(), 15787740, 14585696,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> LAPWING_SPAWN_EGG = ITEMS.register("lapwing_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.LAPWING.get(), 1921595, 528151,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SKUA_SPAWN_EGG = ITEMS.register("skua_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.SKUA.get(), 6182224, 4011831,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> BUNTING_SPAWN_EGG = ITEMS.register("bunting_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.BUNTING.get(), 4138700, 13379110,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> MONAL_SPAWN_EGG  = ITEMS.register("monal_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.MONAL.get(), 1447449, 5493427,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> TANAGER_SPAWN_EGG  = ITEMS.register("tanager_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.TANAGER.get(), 1973286, 11066120,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> FINCH_SPAWN_EGG  = ITEMS.register("finch_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.FINCH.get(), 10590093, 11557643,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.KINGFISHER.get(), 29897, 13722630,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PELICAN_SPAWN_EGG = ITEMS.register("pelican_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.PELICAN.get(), 15787740, 14585696,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> LAPWING_SPAWN_EGG = ITEMS.register("lapwing_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.LAPWING.get(), 1921595, 528151,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SKUA_SPAWN_EGG = ITEMS.register("skua_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.SKUA.get(), 6182224, 4011831,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> BUNTING_SPAWN_EGG = ITEMS.register("bunting_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.BUNTING.get(), 4138700, 13379110,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> MONAL_SPAWN_EGG  = ITEMS.register("monal_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.MONAL.get(), 1447449, 5493427,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> TANAGER_SPAWN_EGG  = ITEMS.register("tanager_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.TANAGER.get(), 1973286, 11066120,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> FINCH_SPAWN_EGG  = ITEMS.register("finch_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.FINCH.get(), 10590093, 11557643,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> VAMPIRE_CRAB_SPAWN_EGG  = ITEMS.register("vampirecrab_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.VAMPIRECRAB.get(), 1579024, 7024730,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> TARANTULA_SPAWN_EGG  = ITEMS.register("tarantula_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.TARANTULA.get(), 657678, 11427379,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.TARANTULA.get(), 657678, 11427379,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> CAPERCAILLIE_SPAWN_EGG  = ITEMS.register("capercaillie_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.CAPERCAILLIE.get(), 3685214, 16316664,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> TIGERBARB_SPAWN_EGG  = ITEMS.register("tigerbarb_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.TIGERBARB.get(), 10718525, 1250617,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PHEASANT_SPAWN_EGG  = ITEMS.register("pheasant_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.PHEASANT.get(), 16306176, 11013921,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.TIGERBARB.get(), 10718525, 1250617,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PHEASANT_SPAWN_EGG  = ITEMS.register("pheasant_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.PHEASANT.get(), 16306176, 11013921,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> ARAPAIMA_SPAWN_EGG  = ITEMS.register("arapaima_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.ARAPAIMA.get(), 4739385, 11620159,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PIRANHA_SPAWN_EGG  = ITEMS.register("piranha_spawn_egg",
<<<<<<< Updated upstream
            () -> new ModSpawnEgg(() -> ModEntityTypes.PIRANHA.get(), 8880781, 12006170,
=======
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.PIRANHA.get(), 8880781, 12006170,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> STORK_SPAWN_EGG  = ITEMS.register("stork_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.STORK.get(), 16777215, 1710622,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> WHISTLINGDUCK_SPAWN_EGG  = ITEMS.register("whistlingduck_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.WHISTLINGDUCK.get(), 13741962, 8402969,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GROUND_HORNBILL_SPAWN_EGG  = ITEMS.register("groundhornbill_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.GROUND_HORNBILL.get(), 1842204, 12599604,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SECRETARYBIRD_SPAWN_EGG  = ITEMS.register("secretarybird_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.SECRETARYBIRD.get(), 10394523, 15555084,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SHOEBILL_SPAWN_EGG  = ITEMS.register("shoebill_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.SHOEBILL.get(), 10267065, 5071228,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> STARLING_SPAWN_EGG  = ITEMS.register("starling_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.STARLING.get(), 1385047, 14854402,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> TAMBAQUI_SPAWN_EGG  = ITEMS.register("tambaqui_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.TAMBAQUI.get(), 6709327, 986895,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> ELEPHANTNOSE_SPAWN_EGG  = ITEMS.register("elephantnose_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.ELEPHANTNOSE.get(), 9665641, 4274747,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> CORMORANT_SPAWN_EGG  = ITEMS.register("cormorant_spawn_egg",
            () -> new ModSpawnEggVariants(() -> ModEntityTypes.CORMORANT.get(), 3289400, 6314590,
>>>>>>> Stashed changes
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));

}
