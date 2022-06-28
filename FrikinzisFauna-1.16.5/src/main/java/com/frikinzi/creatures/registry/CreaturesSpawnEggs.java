package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.item.ModSpawnEgg;
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
            () -> new ModSpawnEgg(() -> ModEntityTypes.LOVEBIRD.get(), 16749375, 16765696,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> DOTTYBACK_SPAWN_EGG = ITEMS.register("dottyback_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.DOTTYBACK.get(), 16740608, 6058495,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PIKE_SPAWN_EGG = ITEMS.register("pike_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.PIKE.get(), 10973747, 16777215,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GUPPY_SPAWN_EGG = ITEMS.register("guppy_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.GUPPY.get(), 8578898, 8579010,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SPOONBILL_SPAWN_EGG = ITEMS.register("creatures_spoonbill_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.SPOONBILL.get(), 16490917, 16583198,
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
            () -> new ModSpawnEgg(() -> ModEntityTypes.RAVEN.get(), 9536, 4152450,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> SHRIMP_SPAWN_EGG = ITEMS.register("shrimp_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.SHRIMP.get(), 16583198, 16490917,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> DOVE_SPAWN_EGG = ITEMS.register("dove_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.DOVE.get(), 14935271, 15395538,
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
            () -> new ModSpawnEgg(() -> ModEntityTypes.LORIKEET.get(), 3093151, 16718368,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> CONURE_SPAWN_EGG = ITEMS.register("conure_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.CONURE.get(), 16170000, 2182420,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> FAIRYWREN_SPAWN_EGG = ITEMS.register("fairywren_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.FAIRYWREN.get(), 1650103, 41,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GHOST_CRAB_SPAWN_EGG = ITEMS.register("ghostcrab_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.GHOST_CRAB.get(), 13545576, 16777215,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GOURAMI_SPAWN_EGG = ITEMS.register("gourami_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.GOURAMI.get(), 4152450, 16777215,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PYGMY_FALCON_EGG = ITEMS.register("pygmy_falcon_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.PYGMY_FALCON.get(), 8097951, 15724012,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> BARN_OWL_SPAWN_EGG = ITEMS.register("barn_owl_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.BARN_OWL.get(), 16777215, 11897942,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> WILD_DUCK_SPAWN_EGG = ITEMS.register("wild_duck_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.WILD_DUCK.get(), 15702874, 7901340,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> ROLLER_SPAWN_EGG = ITEMS.register("roller_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.ROLLER.get(), 1414724, 13192647,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> GOLDFISH_SPAWN_EGG = ITEMS.register("goldfish_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.GOLDFISH.get(), 14501642, 14318603,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> RANCHU_SPAWN_EGG = ITEMS.register("ranchu_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.RANCHU.get(), 14501642, 11,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> CHICKADEE_SPAWN_EGG = ITEMS.register("chickadee_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.CHICKADEE.get(), 13879499, 11,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PYGMY_GOOSE_SPAWN_EGG = ITEMS.register("pygmy_goose_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.PYGMY_GOOSE.get(), 2772553, 15964498,
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
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> WOOD_DUCK_SPAWN_EGG = ITEMS.register("wood_duck_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.WOOD_DUCK.get(), 2967328, 7548455,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> PEAFOWL_SPAWN_EGG = ITEMS.register("peafowl_spawn_egg",
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
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> OSPREY_SPAWN_EGG = ITEMS.register("osprey_spawn_egg",
            () -> new ModSpawnEgg(() -> ModEntityTypes.OSPREY.get(), 14869470, 4732721,
                    new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    public static final RegistryObject<ModSpawnEgg> KINGFISHER_SPAWN_EGG = ITEMS.register("kingfisher_spawn_egg",
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

}
