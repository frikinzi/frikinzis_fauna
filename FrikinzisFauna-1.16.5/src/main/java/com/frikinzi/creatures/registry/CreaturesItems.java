package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.item.CreaturesFood;
import com.frikinzi.creatures.item.EggItem;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreaturesItems {
    public static Item KOI_BUCKET = new FishBucketItem(() -> ModEntityTypes.KOI.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item DOTTYBACK_BUCKET = new FishBucketItem(() -> ModEntityTypes.DOTTYBACK.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item PIKE_BUCKET = new FishBucketItem(() -> ModEntityTypes.PIKE.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item GUPPY_BUCKET = new FishBucketItem(() -> ModEntityTypes.GUPPY.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item AROWANA_BUCKET = new FishBucketItem(() -> ModEntityTypes.AROWANA.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item SHRIMP_BUCKET = new FishBucketItem(() -> ModEntityTypes.SHRIMP.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item GOURAMI_BUCKET = new FishBucketItem(() -> ModEntityTypes.GOURAMI.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item GOLDFISH_BUCKET = new FishBucketItem(() -> ModEntityTypes.GOLDFISH.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item RANCHU_BUCKET = new FishBucketItem(() -> ModEntityTypes.RANCHU.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item FIRE_GOBY_BUCKET = new FishBucketItem(() -> ModEntityTypes.FIRE_GOBY.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item BLUE_TANG_BUCKET = new FishBucketItem(() -> ModEntityTypes.BLUE_TANG.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item TROUT_BUCKET = new FishBucketItem(() -> ModEntityTypes.TROUT.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item FLAME_ANGELFISH_BUCKET = new FishBucketItem(() -> ModEntityTypes.FLAME_ANGELFISH.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));
    public static Item RED_SNAPPER_BUCKET = new FishBucketItem(() -> ModEntityTypes.RED_SNAPPER.get(), () -> Fluids.WATER, new Item.Properties().tab(Creatures.CreaturesItemGroup));

    static int i = 0;
    public static Item LOVEBIRD_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item SPOONBILL_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item KAKAPO_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item MANDARIN_DUCK_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item RAVEN_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item DOVE_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item RED_KITE_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item GOLDEN_EAGLE_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item STELLERS_SEA_EAGLE_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item GYRFALCON_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item LORIKEET_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item CONURE_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item FAIRYWREN_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item PYGMY_FALCON_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item BARN_OWL_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item WILD_DUCK_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item ROLLER_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item CHICKADEE_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item PYGMY_GOOSE_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item SWALLOW_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item IBIS_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item WOOD_DUCK_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item PEAFOWL_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item SPARROW_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item BUSHTIT_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item EAGLEOWL_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item ROBIN_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item LAUGHINGTHRUSH_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item MAGPIE_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item GOOSE_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item OSPREY_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item KINGFISHER_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item PELICAN_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item LAPWING_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));
    public static Item SKUA_EGG = new EggItem(i++,(new Item.Properties()).stacksTo(1).tab(Creatures.CreaturesItemGroup));

    public static Item RAW_KOI = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.RAW_KOI));
    public static Item ROASTED_KOI = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.ROASTED_KOI));
    public static Item RAW_PIKE = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.RAW_PIKE));
    public static Item ROASTED_PIKE = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.ROASTED_PIKE));
    public static Item RAW_AROWANA = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.RAW_AROWANA));
    public static Item COOKED_AROWANA = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.COOKED_AROWANA));
    public static Item SMALL_BIRD_MEAT = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.SMALL_BIRD_MEAT));
    public static Item COOKED_SMALL_BIRD_MEAT = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.COOKED_SMALL_BIRD_MEAT));
    public static Item NECTAR = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.NECTAR));
    public static Item RAW_SHRIMP = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.RAW_SHRIMP));
    public static Item COOKED_SHRIMP = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.COOKED_SHRIMP));
    public static Item GOURAMI = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.GOURAMI));
    public static Item LARGE_BIRD_MEAT = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.LARGE_BIRD_MEAT));
    public static Item ROASTED_LARGE_BIRD_MEAT = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.ROASTED_LARGE_BIRD_MEAT));
    public static Item RAW_TROUT = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.RAW_TROUT));
    public static Item COOKED_TROUT = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.COOKED_TROUT));
    public static Item RAW_RED_SNAPPER = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.RAW_RED_SNAPPER));
    public static Item COOKED_RED_SNAPPER = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup).food(CreaturesFood.COOKED_RED_SNAPPER));

    public static final Item GUPPY_TAIL = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup));
    public static final Item GOLDFISH = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup));
    public static final Item CRAB_PINCERS = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup));
    public static final Item DUCK_FEATHER = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup));
    public static final Item BIRD_OF_PREY_FEATHER = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup));
    public static final Item RAVEN_FEATHER = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup));
    public static final Item PARROT_FEATHER = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup));
    public static final Item FF_GUIDE = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup));
    public static final Item PEAFOWL_FEATHER = new Item((new Item.Properties()).tab(Creatures.CreaturesItemGroup));

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();

        //buckets
        registerItem(registry, setup(KOI_BUCKET, "bucket_of_koi"));
        registerItem(registry, setup(DOTTYBACK_BUCKET, "bucket_of_dottyback"));
        registerItem(registry, setup(PIKE_BUCKET, "bucket_of_pike"));
        registerItem(registry, setup(GUPPY_BUCKET, "bucket_of_guppy"));
        registerItem(registry, setup(AROWANA_BUCKET, "bucket_of_arowana"));
        registerItem(registry, setup(SHRIMP_BUCKET, "bucket_of_shrimp"));
        registerItem(registry, setup(GOURAMI_BUCKET, "bucket_of_gourami"));
        registerItem(registry, setup(GOLDFISH_BUCKET, "bucket_of_goldfish"));
        registerItem(registry, setup(RANCHU_BUCKET, "bucket_of_ranchu"));
        registerItem(registry, setup(TROUT_BUCKET, "bucket_of_trout"));
        registerItem(registry, setup(FIRE_GOBY_BUCKET, "bucket_of_fire_goby"));
        registerItem(registry, setup(BLUE_TANG_BUCKET, "bucket_of_blue_tang"));
        registerItem(registry, setup(FLAME_ANGELFISH_BUCKET, "bucket_of_flame_angelfish"));
        registerItem(registry, setup(RED_SNAPPER_BUCKET, "bucket_of_red_snapper"));

        //food
        registerItem(registry, setup(RAW_KOI, "raw_koi"));
        registerItem(registry, setup(ROASTED_KOI, "roasted_koi"));
        registerItem(registry, setup(RAW_PIKE, "raw_pike"));
        registerItem(registry, setup(ROASTED_PIKE, "cooked_pike"));
        registerItem(registry, setup(RAW_AROWANA, "raw_arowana"));
        registerItem(registry, setup(COOKED_AROWANA, "cooked_arowana"));
        registerItem(registry, setup(SMALL_BIRD_MEAT, "small_bird_meat"));
        registerItem(registry, setup(COOKED_SMALL_BIRD_MEAT, "cooked_small_bird_meat"));
        registerItem(registry, setup(NECTAR, "creatures_nectar"));
        registerItem(registry, setup(RAW_SHRIMP, "raw_shrimp"));
        registerItem(registry, setup(COOKED_SHRIMP, "cooked_shrimp"));
        registerItem(registry, setup(GOURAMI, "raw_gourami"));
        registerItem(registry, setup(LARGE_BIRD_MEAT, "raw_large_wild_bird_meat"));
        registerItem(registry, setup(ROASTED_LARGE_BIRD_MEAT, "roasted_large_wild_bird_meat"));
        registerItem(registry, setup(RAW_TROUT, "item_trout"));
        registerItem(registry, setup(COOKED_TROUT, "cooked_trout"));
        registerItem(registry, setup(RAW_RED_SNAPPER, "raw_red_snapper"));
        registerItem(registry, setup(COOKED_RED_SNAPPER, "cooked_red_snapper"));

        //egg
        registerItem(registry, setup(LOVEBIRD_EGG, "lovebird_egg"));
        registerItem(registry, setup(SPOONBILL_EGG, "spoonbill_egg"));
        registerItem(registry, setup(KAKAPO_EGG, "kakapo_egg"));
        registerItem(registry, setup(MANDARIN_DUCK_EGG, "mandarin_duck_egg"));
        registerItem(registry, setup(RAVEN_EGG, "raven_egg"));
        registerItem(registry, setup(DOVE_EGG, "dove_egg"));
        registerItem(registry, setup(RED_KITE_EGG, "red_kite_egg"));
        registerItem(registry, setup(GOLDEN_EAGLE_EGG, "golden_eagle_egg"));
        registerItem(registry, setup(STELLERS_SEA_EAGLE_EGG, "stellers_sea_eagle_egg"));
        registerItem(registry, setup(GYRFALCON_EGG, "gyrfalcon_egg"));
        registerItem(registry, setup(LORIKEET_EGG, "lorikeet_egg"));
        registerItem(registry, setup(CONURE_EGG, "conure_egg"));
        registerItem(registry, setup(FAIRYWREN_EGG, "fairywren_egg"));
        registerItem(registry, setup(PYGMY_FALCON_EGG, "pygmyfalcon_egg"));
        registerItem(registry, setup(BARN_OWL_EGG, "barn_owl_egg"));
        registerItem(registry, setup(WILD_DUCK_EGG, "wild_duck_egg"));
        registerItem(registry, setup(ROLLER_EGG, "roller_egg"));
        registerItem(registry, setup(CHICKADEE_EGG, "chickadee_egg"));
        registerItem(registry, setup(PYGMY_GOOSE_EGG, "pygmy_goose_egg"));
        registerItem(registry, setup(SWALLOW_EGG, "swallow_egg"));
        registerItem(registry, setup(IBIS_EGG, "ibis_egg"));
        registerItem(registry, setup(WOOD_DUCK_EGG, "wood_duck_egg"));
        registerItem(registry, setup(PEAFOWL_EGG, "peafowl_egg"));
        registerItem(registry, setup(SPARROW_EGG, "sparrow_egg"));
        registerItem(registry, setup(BUSHTIT_EGG, "bushtit_egg"));
        registerItem(registry, setup(EAGLEOWL_EGG, "eagleowl_egg"));
        registerItem(registry, setup(ROBIN_EGG, "robin_egg"));
        registerItem(registry, setup(LAUGHINGTHRUSH_EGG, "laughingthrush_egg"));
        registerItem(registry, setup(MAGPIE_EGG, "magpie_egg"));
        registerItem(registry, setup(GOOSE_EGG, "goose_egg"));
        registerItem(registry, setup(OSPREY_EGG, "osprey_egg"));
        registerItem(registry, setup(KINGFISHER_EGG, "kingfisher_egg"));
        registerItem(registry, setup(PELICAN_EGG, "pelican_egg"));
        registerItem(registry, setup(LAPWING_EGG, "lapwing_egg"));
        registerItem(registry, setup(SKUA_EGG, "skua_egg"));



        //other
        registerItem(registry, setup(GUPPY_TAIL, "guppy_tail"));
        registerItem(registry, setup(GOLDFISH, "item_goldfish"));
        registerItem(registry, setup(CRAB_PINCERS, "crab_pincers"));
        registerItem(registry, setup(DUCK_FEATHER, "duck_feather"));
        registerItem(registry, setup(RAVEN_FEATHER, "raven_feather"));
        registerItem(registry, setup(BIRD_OF_PREY_FEATHER, "bird_of_prey_feather"));
        registerItem(registry, setup(PARROT_FEATHER, "parrot_feather"));
        registerItem(registry, setup(FF_GUIDE, "ff_guide"));
        registerItem(registry, setup(PEAFOWL_FEATHER, "peafowl_feather"));

    }

    private static void registerItem(IForgeRegistry<Item> registry, Item item)
    {
        registerItem(registry, item, false);
    }

    private static void registerItem(IForgeRegistry<Item> registry, Item item, boolean variantBlock)
    {
        registry.register(item);
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(T entry, String name)
    {
        return setup(entry, new ResourceLocation(Creatures.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(T entry, ResourceLocation registryName)
    {
        entry.setRegistryName(registryName);
        return entry;
    }
}

