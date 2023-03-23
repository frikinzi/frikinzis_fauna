package com.frikinzi.creatures.util.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.item.EggItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreaturesItems {
    //public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Creatures.MODID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Creatures.MODID);
    static int i = 0;
    public static final RegistryObject<Item> LOVEBIRD_EGG = ITEMS.register("lovebird_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> SPOONBILL_EGG = ITEMS.register("spoonbill_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> KAKAPO_EGG = ITEMS.register("kakapo_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> MANDARIN_DUCK_EGG = ITEMS.register("mandarin_duck_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> RAVEN_EGG = ITEMS.register("raven_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> DOVE_EGG = ITEMS.register("dove_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> RED_KITE_EGG = ITEMS.register("red_kite_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GOLDEN_EAGLE_EGG = ITEMS.register("golden_eagle_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> STELLERS_SEA_EAGLE_EGG = ITEMS.register("stellers_sea_eagle_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GYRFALCON_EGG = ITEMS.register("gyrfalcon_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> LORIKEET_EGG = ITEMS.register("lorikeet_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> CONURE_EGG = ITEMS.register("conure_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> FAIRYWREN_EGG = ITEMS.register("fairywren_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PYGMY_FALCON_EGG = ITEMS.register("pygmyfalcon_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> BARN_OWL_EGG = ITEMS.register("barn_owl_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> WILD_DUCK_EGG = ITEMS.register("wild_duck_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> ROLLER_EGG = ITEMS.register("roller_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> CHICKADEE_EGG = ITEMS.register("chickadee_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PYGMY_GOOSE_EGG = ITEMS.register("pygmy_goose_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> SWALLOW_EGG = ITEMS.register("swallow_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> IBIS_EGG = ITEMS.register("ibis_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> WOOD_DUCK_EGG = ITEMS.register("wood_duck_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PEAFOWL_EGG = ITEMS.register("peafowl_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> SPARROW_EGG = ITEMS.register("sparrow_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> BUSHTIT_EGG = ITEMS.register("bushtit_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> EAGLEOWL_EGG = ITEMS.register("eagleowl_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> ROBIN_EGG = ITEMS.register("robin_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> LAUGHINGTHRUSH_EGG = ITEMS.register("laughingthrush_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> MAGPIE_EGG = ITEMS.register("magpie_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GOOSE_EGG = ITEMS.register("goose_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> OSPREY_EGG = ITEMS.register("osprey_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> KINGFISHER_EGG = ITEMS.register("kingfisher_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PELICAN_EGG = ITEMS.register("pelican_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> LAPWING_EGG = ITEMS.register("lapwing_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> SKUA_EGG = ITEMS.register("skua_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> BUNTING_EGG = ITEMS.register("bunting_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> MONAL_EGG = ITEMS.register("monal_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> TANAGER_EGG = ITEMS.register("tanager_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> FINCH_EGG = ITEMS.register("finch_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> CAPERCAILLIE_EGG = ITEMS.register("capercaillie_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PHEASANT_EGG = ITEMS.register("pheasant_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));


    public static final RegistryObject<Item> FF_GUIDE = ITEMS.register("ff_guide",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GUPPY_TAIL = ITEMS.register("guppy_tail",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> BIRD_OF_PREY_FEATHER = ITEMS.register("bird_of_prey_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PARROT_FEATHER = ITEMS.register("parrot_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> RAVEN_FEATHER = ITEMS.register("raven_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> CRAB_PINCERS = ITEMS.register("crab_pincers",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> DUCK_FEATHER = ITEMS.register("duck_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PEAFOWL_FEATHER = ITEMS.register("peafowl_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GOLDFISH = ITEMS.register("item_goldfish",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));

    //items
    public static final RegistryObject<Item> MEALWORMS = ITEMS.register("mealworms",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> GOURAMI = ITEMS.register("raw_gourami",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> RAW_AROWANA = ITEMS.register("raw_arowana",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_AROWANA = ITEMS.register("cooked_arowana",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> RAW_KOI = ITEMS.register("raw_koi",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_KOI = ITEMS.register("roasted_koi",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> RAW_PIKE = ITEMS.register("raw_pike",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> ROASTED_PIKE = ITEMS.register("cooked_pike",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> SMALL_BIRD_MEAT = ITEMS.register("small_bird_meat",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_SMALL_BIRD_MEAT = ITEMS.register("cooked_small_bird_meat",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> NECTAR = ITEMS.register("creatures_nectar",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> RAW_SHRIMP = ITEMS.register("raw_shrimp",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_SHRIMP = ITEMS.register("cooked_shrimp",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).build())));


    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //spawn Eggs
        //@formatter:off
        ITEMS.register("lovebird_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.LOVEBIRD, 0xFF933F, 0xFFD300, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("creatures_spoonbill_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.SPOONBILL, 16490917, 16583198, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("kakapo_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.KAKAPO, 9607980, 12299667, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("mandarin_duck_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.MANDARIN_DUCK, 11798553, 16640178, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("raven_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.RAVEN, 9536, 4152450, new Item.Properties().tab(Creatures.CREATURES_TAB)));

    }
    //public static final RegistryObject<Item> LOVEBIRD_SPAWN_EGG = ITEMS.register("lovebird_spawn_egg",
     //       () -> new CreaturesSpawnEgg(CreaturesEntities.LOVEBIRD, 16749375, 16765696));

}
