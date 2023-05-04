package com.frikinzi.creatures.util.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.item.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
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
    public static final RegistryObject<Item> STORK_EGG = ITEMS.register("stork_egg", () -> new EggItem(i++, new Item.Properties().tab(Creatures.CREATURES_TAB)));

    public static final RegistryObject<Item> FF_GUIDE = ITEMS.register("ff_guide",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GUPPY_TAIL = ITEMS.register("guppy_tail",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> BIRD_OF_PREY_FEATHER = ITEMS.register("bird_of_prey_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PARROT_FEATHER = ITEMS.register("parrot_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> RAVEN_FEATHER = ITEMS.register("raven_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> CRAB_PINCERS = ITEMS.register("crab_pincers",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> DUCK_FEATHER = ITEMS.register("duck_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PEAFOWL_FEATHER = ITEMS.register("peafowl_feather",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GOLDFISH = ITEMS.register("item_goldfish",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> RAPTOR_HORN = ITEMS.register("raptor_horn",() -> new RaptorHornItem(new Item.Properties().tab(Creatures.CREATURES_TAB).stacksTo(1)));
    public static final RegistryObject<Item> BIRD_CARRIER = ITEMS.register("bird_carrier",() -> new BirdCarrierItem(new Item.Properties().tab(Creatures.CREATURES_TAB).stacksTo(1)));
    public static final RegistryObject<Item> CRITTER_KEEPER = ITEMS.register("critter_keeper",() -> new CritterKeeperItem(new Item.Properties().tab(Creatures.CREATURES_TAB).stacksTo(1)));


    //items
    public static final RegistryObject<Item> MEALWORMS = ITEMS.register("mealworms",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> GOURAMI = ITEMS.register("raw_gourami",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> RAW_AROWANA = ITEMS.register("raw_arowana",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_AROWANA = ITEMS.register("cooked_arowana",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> RAW_KOI = ITEMS.register("raw_koi",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_KOI = ITEMS.register("roasted_koi",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> RAW_PIKE = ITEMS.register("raw_pike",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> ROASTED_PIKE = ITEMS.register("cooked_pike",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> SMALL_BIRD_MEAT = ITEMS.register("small_bird_meat",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build())));
    public static final RegistryObject<Item> COOKED_SMALL_BIRD_MEAT = ITEMS.register("cooked_small_bird_meat",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).meat().build())));
    public static final RegistryObject<Item> NECTAR = ITEMS.register("creatures_nectar",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> RAW_SHRIMP = ITEMS.register("raw_shrimp",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_SHRIMP = ITEMS.register("cooked_shrimp",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> LARGE_BIRD_MEAT = ITEMS.register("raw_large_wild_bird_meat",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build())));
    public static final RegistryObject<Item> COOKED_LARGE_BIRD_MEAT = ITEMS.register("roasted_large_wild_bird_meat",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(8).saturationMod(0.6F).meat().build())));
    public static final RegistryObject<Item> RAW_TROUT = ITEMS.register("item_trout",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_TROUT = ITEMS.register("cooked_trout",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(7).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> RAW_RED_SNAPPER = ITEMS.register("raw_red_snapper",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_RED_SNAPPER = ITEMS.register("cooked_red_snapper",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(7).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> RAW_ARAPAIMA = ITEMS.register("raw_arapaima",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_ARAPAIMA = ITEMS.register("cooked_arapaima",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(10).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> RAW_PIRANHA = ITEMS.register("raw_piranha",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_PIRANHA = ITEMS.register("cooked_piranha",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> RAW_TAMBAQUI = ITEMS.register("raw_tambaqui",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> COOKED_TAMBAQUI = ITEMS.register("cooked_tambaqui",() -> new Item(new Item.Properties().tab(Creatures.CREATURES_TAB).food((new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build())));
    //public static final RegistryObject<Item> KOI_BUCKET = ITEMS.register("bucket_of_koi",() -> new MobBucketItem(CreaturesEntities.KOI.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    ;
    public static final RegistryObject<Item> KOI_BUCKET = ITEMS.register("bucket_of_koi",() -> new MobBucketItem(CreaturesEntities.KOI::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> DOTTYBACK_BUCKET = ITEMS.register("bucket_of_dottyback",() -> new MobBucketItem(CreaturesEntities.DOTTYBACK::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PIKE_BUCKET = ITEMS.register("bucket_of_pike",() -> new MobBucketItem(CreaturesEntities.PIKE::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GUPPY_BUCKET = ITEMS.register("bucket_of_guppy",() -> new MobBucketItem(CreaturesEntities.GUPPY::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> AROWANA_BUCKET = ITEMS.register("bucket_of_arowana",() -> new MobBucketItem(CreaturesEntities.AROWANA::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> SHRIMP_BUCKET = ITEMS.register("bucket_of_shrimp",() -> new MobBucketItem(CreaturesEntities.SHRIMP::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GOURAMI_BUCKET = ITEMS.register("bucket_of_gourami",() -> new MobBucketItem(CreaturesEntities.GOURAMI::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> GOLDFISH_BUCKET = ITEMS.register("bucket_of_goldfish",() -> new MobBucketItem(CreaturesEntities.GOLDFISH::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> RANCHU_BUCKET = ITEMS.register("bucket_of_ranchu",() -> new MobBucketItem(CreaturesEntities.RANCHU::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> FIRE_GOBY_BUCKET = ITEMS.register("bucket_of_fire_goby",() -> new MobBucketItem(CreaturesEntities.FIRE_GOBY::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> BLUE_TANG_BUCKET = ITEMS.register("bucket_of_blue_tang",() -> new MobBucketItem(CreaturesEntities.BLUE_TANG::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> FLAME_ANGELFISH_BUCKET = ITEMS.register("bucket_of_flame_angelfish",() -> new MobBucketItem(CreaturesEntities.FLAME_ANGELFISH::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> TROUT_BUCKET = ITEMS.register("bucket_of_trout",() -> new MobBucketItem(CreaturesEntities.TROUT::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> RED_SNAPPER_BUCKET = ITEMS.register("bucket_of_red_snapper",() -> new MobBucketItem(CreaturesEntities.RED_SNAPPER::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> TIGER_BARB_BUCKET = ITEMS.register("bucket_of_tigerbarb",() -> new MobBucketItem(CreaturesEntities.TIGER_BARB::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> ARAPAIMA_BUCKET = ITEMS.register("bucket_of_arapaima",() -> new MobBucketItem(CreaturesEntities.ARAPAIMA::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> PIRANHA_BUCKET = ITEMS.register("bucket_of_piranha",() -> new MobBucketItem(CreaturesEntities.PIRANHA::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> TAMBAQUI_BUCKET = ITEMS.register("bucket_of_tambaqui",() -> new MobBucketItem(CreaturesEntities.TAMBAQUI::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));
    public static final RegistryObject<Item> ELEPHANT_NOSE_BUCKET = ITEMS.register("bucket_of_elephant_nose",() -> new MobBucketItem(CreaturesEntities.ELEPHANT_NOSE::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1).tab(Creatures.CREATURES_TAB)));

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //spawn Eggs
        //@formatter:off
        ITEMS.register("lovebird_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.LOVEBIRD, 0xFF933F, 0xFFD300));
        ITEMS.register("creatures_spoonbill_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.SPOONBILL, 16490917, 16583198));
        ITEMS.register("kakapo_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.KAKAPO, 9607980, 12299667, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("mandarin_duck_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.MANDARIN_DUCK, 11798553, 16640178, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("raven_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.RAVEN, 9536, 4152450, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("dove_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.DOVE, 14935271, 15395538));
        ITEMS.register("red_kite_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.RED_KITE, 13651220, 4152450, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("golden_eagle_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.GOLDEN_EAGLE, 4525319, 10125934, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("stellers_sea_eagle_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.STELLERS_SEA_EAGLE, 5322812, 16515071, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("gyrfalcon_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.GYRFALCON, 16515071, 9536, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("lorikeet_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.LORIKEET, 3093151, 16718368));
        ITEMS.register("conure_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.CONURE, 16170000, 2182420));
        ITEMS.register("fairywren_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.FAIRYWREN, 1650103, 41));
        ITEMS.register("pygmy_falcon_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.PYGMY_FALCON, 8097951, 15724012, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("barn_owl_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.BARN_OWL, 16777215, 11897942, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("wild_duck_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.WILD_DUCK, 15702874, 7901340));
        ITEMS.register("roller_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.ROLLER, 1414724, 13192647));
        ITEMS.register("chickadee_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.CHICKADEE, 13879499, 11));
        ITEMS.register("pygmy_goose_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.PYGMY_GOOSE, 2772553, 15964498));
        ITEMS.register("swallow_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.SWALLOW, 6588890, 11953480));
        ITEMS.register("ibis_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.IBIS, 13178131, 13847612));
        ITEMS.register("wood_duck_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.WOOD_DUCK, 2967328, 7548455, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("peafowl_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.PEAFOWL, 32466, 2450496));
        ITEMS.register("sparrow_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.SPARROW, 9004362, 13743005));
        ITEMS.register("bushtit_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.BUSHTIT, 16382707, 9004362));
        ITEMS.register("eagleowl_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.EAGLEOWL, 13608552, 4798501));
        ITEMS.register("robin_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.ROBIN, 9600343, 13723915));
        ITEMS.register("laughingthrush_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.LAUGHINGTHRUSH, 8873290, 15720079));
        ITEMS.register("magpie_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.MAGPIE, 1908259, 4480395));
        ITEMS.register("goose_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.GOOSE, 7888451, 525056));
        ITEMS.register("osprey_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.OSPREY, 14869470, 4732721, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("kingfisher_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.KINGFISHER, 29897, 13722630));
        ITEMS.register("pelican_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.PELICAN, 15787740, 14585696));
        ITEMS.register("lapwing_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.LAPWING, 1921595, 528151));
        ITEMS.register("skua_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.SKUA, 6182224, 4011831));
        ITEMS.register("bunting_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.BUNTING, 4138700, 13379110));
        ITEMS.register("monal_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.MONAL, 1447449, 5493427));
        ITEMS.register("tanager_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.TANAGER, 1973286, 11066120));
        ITEMS.register("finch_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.FINCH, 10590093, 11557643));
        ITEMS.register("capercaillie_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.CAPERCAILLIE, 3685214, 16316664, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("pheasant_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.PHEASANT, 16306176, 11013921));
        ITEMS.register("stork_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.STORK, 16777215, 1710622));

        ITEMS.register("koi_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.KOI, 16731716, 16777215, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("dottyback_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.DOTTYBACK, 16740608, 6058495));
        ITEMS.register("pike_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.PIKE, 10973747, 16777215, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("guppy_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.GUPPY, 8578898, 8579010));
        ITEMS.register("arowana_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.AROWANA, 16771881, 16758578, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("shrimp_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.SHRIMP, 16583198, 16490917, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("gourami_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.GOURAMI, 4152450, 16777215));
        ITEMS.register("goldfish_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.GOLDFISH, 14501642, 14318603, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("ranchu_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.RANCHU, 14501642, 11, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("fire_goby_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.FIRE_GOBY, 14080426, 15964498, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("blue_tang_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.BLUE_TANG, 3895524, 722696, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("flame_angelfish_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.FLAME_ANGELFISH, 16396073, 16611846, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("trout_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.TROUT, 9861465, 11822456));
        ITEMS.register("red_snapper_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.RED_SNAPPER, 15320002, 13979723));
        ITEMS.register("tigerbarb_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.TIGER_BARB, 10718525, 1250617));
        ITEMS.register("arapaima_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.ARAPAIMA, 4739385, 11620159, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("piranha_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.PIRANHA, 8880781, 12006170));
        ITEMS.register("tambaqui_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.TAMBAQUI, 6709327, 986895, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("elephant_nose_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.ELEPHANT_NOSE, 9665641, 4274747, new Item.Properties().tab(Creatures.CREATURES_TAB)));

        ITEMS.register("ghostcrab_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.GHOST_CRAB, 13545576, 16777215));
        ITEMS.register("fiddler_crab_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.FIDDLER_CRAB, 526085, 9297374));
        ITEMS.register("vampirecrab_spawn_egg", () -> new ForgeSpawnEggItem(CreaturesEntities.VAMPIRE_CRAB, 1579024, 7024730, new Item.Properties().tab(Creatures.CREATURES_TAB)));
        ITEMS.register("tarantula_spawn_egg", () -> new CreaturesSpawnEgg(CreaturesEntities.TARANTULA, 657678, 11427379));

    }
    //public static final RegistryObject<Item> LOVEBIRD_SPAWN_EGG = ITEMS.register("lovebird_spawn_egg",
     //       () -> new CreaturesSpawnEgg(CreaturesEntities.LOVEBIRD, 16749375, 16765696));

}
