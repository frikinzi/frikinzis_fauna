package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.KakapoEntity;
import com.frikinzi.creatures.entity.LovebirdEntity;
import com.frikinzi.creatures.entity.MandarinDuckEntity;
import com.frikinzi.creatures.entity.SpoonbillEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CreaturesEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            Creatures.MODID);

    public static final RegistryObject<EntityType<LovebirdEntity>> LOVEBIRD = registerMob("lovebird", LovebirdEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SpoonbillEntity>> SPOONBILL = registerMob("spoonbill", SpoonbillEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<KakapoEntity>> KAKAPO = registerMob("kakapo", KakapoEntity::new,
            0.7f, 1.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<KoiEntity>> KOI = registerMob("koi", KoiEntity::new,
//            0.5f, 0.4f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<DottybackEntity>> DOTTYBACK = registerMob("dottyback", DottybackEntity::new,
//            0.4f, 0.2f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<PikeEntity>> PIKE = registerMob("pike", PikeEntity::new,
//            0.8f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<GuppyEntity>> GUPPY = registerMob("guppy", GuppyEntity::new,
//            0.3f, 0.2f, 16749375, 16765696);
    public static final RegistryObject<EntityType<MandarinDuckEntity>> MANDARIN_DUCK = registerMob("mandarin_duck", MandarinDuckEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<ArowanaEntity>> AROWANA = registerMob("arowana", ArowanaEntity::new,
//            0.5f, 0.4f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<RavenEntity>> RAVEN = registerMob("raven", RavenEntity::new,
//            0.8f, 0.8f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<ShrimpEntity>> SHRIMP = registerMob("shrimp", ShrimpEntity::new,
//            0.3f, 0.2f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<DoveEntity>> DOVE = registerMob("dove", DoveEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<RedKiteEntity>> RED_KITE = registerMob("red_kite", RedKiteEntity::new,
//            0.8f, 0.8f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<GoldenEagleEntity>> GOLDEN_EAGLE = registerMob("golden_eagle", GoldenEagleEntity::new,
//            1.0f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SeaEagleEntity>> SEA_EAGLE = registerMob("stellers_sea_eagle", SeaEagleEntity::new,
//            1.0f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<GyrfalconEntity>> GYRFALCON = registerMob("gyrfalcon", GyrfalconEntity::new,
//            1.0f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<LorikeetEntity>> LORIKEET = registerMob("lorikeet", LorikeetEntity::new,
//            0.7f, 0.7f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<ConureEntity>> CONURE = registerMob("conure", ConureEntity::new,
//            0.7f, 0.7f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<FairywrenEntity>> FAIRYWREN = registerMob("fairywren", FairywrenEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<GhostCrabEntity>> GHOST_CRAB = registerMob("ghostcrab", GhostCrabEntity::new,
//            0.4f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<GouramiEntity>> GOURAMI = registerMob("gourami", GouramiEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<PygmyFalconEntity>> PYGMY_FALCON = registerMob("pygmyfalcon", PygmyFalconEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<BarnOwlEntity>> BARN_OWL = registerMob("barn_owl", BarnOwlEntity::new,
//            0.6f, 0.7f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<WildDuckEntity>> WILD_DUCK = registerMob("wild_duck", WildDuckEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<RollerEntity>> ROLLER = registerMob("roller", RollerEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<GoldfishEntity>> GOLDFISH = registerMob("goldfish", GoldfishEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<RanchuEntity>> RANCHU = registerMob("ranchu", RanchuEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<ChickadeeEntity>> CHICKADEE = registerMob("chickadee", ChickadeeEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<PygmyGooseEntity>> PYGMY_GOOSE = registerMob("pygmy_goose", PygmyGooseEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<FireGobyEntity>> FIRE_GOBY = registerMob("fire_goby", FireGobyEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<BlueTangEntity>> BLUE_TANG = registerMob("blue_tang", BlueTangEntity::new,
//            0.2f, 0.2f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<TroutEntity>> TROUT = registerMob("trout", TroutEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<FlameAngelfishEntity>> FLAME_ANGELFISH = registerMob("flame_angelfish", FlameAngelfishEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SwallowEntity>> SWALLOW = registerMob("swallow", SwallowEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<FiddlerCrabEntity>> FIDDLER_CRAB = registerMob("fiddlercrab", FiddlerCrabEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<IbisEntity>> IBIS = registerMob("ibis", IbisEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<RedSnapperEntity>> RED_SNAPPER = registerMob("red_snapper", RedSnapperEntity::new,
//            0.6f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<WoodDuckEntity>> WOOD_DUCK = registerMob("woodduck", WoodDuckEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<PeafowlEntity>> PEAFOWL = registerMob("peafowl", PeafowlEntity::new,
//            1.0f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SparrowEntity>> SPARROW = registerMob("sparrow", SparrowEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<BushtitEntity>> BUSHTIT = registerMob("bushtit", BushtitEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<EagleOwlEntity>> EAGLEOWL = registerMob("eagleowl", EagleOwlEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<RobinEntity>> ROBIN = registerMob("robin", RobinEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<LaughingthrushEntity>> LAUGHINGTHRUSH = registerMob("laughingthrush", LaughingthrushEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<MagpieEntity>> MAGPIE = registerMob("magpie", MagpieEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<GooseEntity>> GOOSE = registerMob("goose", GooseEntity::new,
//            0.8f, 0.8f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<OspreyEntity>> OSPREY = registerMob("osprey", OspreyEntity::new,
//            1.0f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<KingfisherEntity>> KINGFISHER = registerMob("kingfisher", KingfisherEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<PelicanEntity>> PELICAN = registerMob("pelican", PelicanEntity::new,
//            0.8f, 0.8f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<LapwingEntity>> LAPWING = registerMob("lapwing", LapwingEntity::new,
//            0.6f, 0.7f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SkuaEntity>> SKUA = registerMob("skua", SkuaEntity::new,
//            1.0f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<BuntingEntity>> BUNTING = registerMob("bunting", BuntingEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<MonalEntity>> MONAL = registerMob("monal", MonalEntity::new,
//            1.0f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<TanagerEntity>> TANAGER = registerMob("tanager", TanagerEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<FinchEntity>> FINCH = registerMob("finch", FinchEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<VampireCrabEntity>> VAMPIRECRAB = registerMob("vampirecrab", VampireCrabEntity::new,
//            0.3f, 0.2f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<TarantulaEntity>> TARANTULA = registerMob("tarantula", TarantulaEntity::new,
//            0.5f, 0.2f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<CapercaillieEntity>> CAPERCAILLIE = registerMob("capercaillie", CapercaillieEntity::new,
//            1.0f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<PheasantEntity>> PHEASANT = registerMob("pheasant", PheasantEntity::new,
//            1.0f, 0.8f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<TigerBarbEntity>> TIGERBARB = registerMob("tigerbarb", TigerBarbEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<ArapaimaEntity>> ARAPAIMA = registerMob("arapaima", ArapaimaEntity::new,
//            1.0f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<PiranhaEntity>> PIRANHA = registerMob("piranha", PiranhaEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<StorkEntity>> STORK = registerMob("stork", StorkEntity::new,
//            0.6f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<WhistlingDuckEntity>> WHISTLINGDUCK = registerMob("whistlingduck", WhistlingDuckEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<GroundHornbillEntity>> GROUND_HORNBILL = registerMob("groundhornbill", GroundHornbillEntity::new,
//            0.8f, 0.8f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SecretaryBirdEntity>> SECRETARYBIRD = registerMob("secretarybird", SecretaryBirdEntity::new,
//            0.8f, 1.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<ShoebillEntity>> SHOEBILL = registerMob("shoebill", ShoebillEntity::new,
//            0.8f, 1.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<StarlingEntity>> STARLING = registerMob("starling", StarlingEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<TambaquiEntity>> TAMBAQUI = registerMob("tambaqui", TambaquiEntity::new,
//            0.8f, 0.8f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<ElephantNoseFishEntity>> ELEPHANTNOSE = registerMob("elephantnose", ElephantNoseFishEntity::new,
//            0.4f, 0.4f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<CormorantEntity>> CORMORANT = registerMob("cormorant", CormorantEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<StingrayEntity>> STINGRAY = registerMob("stingray", StingrayEntity::new,
//            0.4f, 0.7f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<PuffinEntity>> PUFFIN = registerMob("puffin", PuffinEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SawfishEntity>> SAWFISH = registerMob("sawfish", SawfishEntity::new,
//            1.5f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SeagullEntity>> SEAGULL = registerMob("seagull", SeagullEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SwordfishEntity>> SWORDFISH = registerMob("swordfish", SwordfishEntity::new,
//            1.5f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<BoobyEntity>> BOOBY = registerMob("booby", BoobyEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SquidEntity>> SQUID = registerMob("squid", SquidEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<LookdownEntity>> LOOKDOWN = registerMob("lookdown", LookdownEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<BandedPenguinEntity>> BANDED_PENGUIN = registerMob("bandedpenguin", BandedPenguinEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<MantisShrimpEntity>> MANTIS_SHRIMP = registerMob("mantisshrimp", MantisShrimpEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<RailEntity>> RAIL = registerMob("rail", RailEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<BarracudaEntity>> BARRACUDA = registerMob("barracuda", BarracudaEntity::new,
//            1.5f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<AvocetEntity>> AVOCET = registerMob("avocet", AvocetEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<SeaDragonEntity>> SEADRAGON = registerMob("seadragon", SeaDragonEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<TrumpetfishEntity>> TRUMPETFISH = registerMob("trumpetfish", TrumpetfishEntity::new,
//            0.5f, 1.0f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<CrestedPenguinEntity>> CRESTED_PENGUIN = registerMob("crestedpenguin", CrestedPenguinEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<ParrotfishEntity>> PARROTFISH = registerMob("parrotfish", ParrotfishEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<YellowEyedPenguinEntity>> YELLOW_EYED_PENGUIN = registerMob("yelloweyedpenguin", YellowEyedPenguinEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<BrushTailedPenguinEntity>> BRUSH_TAILED_PENGUIN = registerMob("brushtailedpenguin", BrushTailedPenguinEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<LargePenguinEntity>> LARGE_PENGUIN = registerMob("largepenguin", LargePenguinEntity::new,
//            0.8f, 1.3f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<FrigateBirdEntity>> FRIGATE = registerMob("frigate", FrigateBirdEntity::new,
//            0.8f, 0.8f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<ClownfishEntity>> CLOWNFISH = registerMob("clownfish", ClownfishEntity::new,
//            0.2f, 0.2f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<StiltEntity>> STILT = registerMob("stilt", StiltEntity::new,
//            0.6f, 0.6f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<LungfishEntity>> LUNGFISH = registerMob("lungfish", LungfishEntity::new,
//            0.4f, 0.4f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<LittlePenguinEntity>> LITTLE_PENGUIN = registerMob("littlepenguin", LittlePenguinEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<EdibleCrabEntity>> EDIBLE_CRAB = registerMob("ediblecrab", EdibleCrabEntity::new,
//            0.5f, 0.5f, 16749375, 16765696);
//    public static final RegistryObject<EntityType<CreaturesRoeEntity>> ROE = registerMob("roe", CreaturesRoeEntity::new,
//            0.3f, 0.3f, 16749375, 16765696);

    public static final RegistryObject<EntityType<EggEntity>> EGG = registerMob("egg", EggEntity::new,
            0.2F, 0.2F, 16749375, 16765696);

    public static <T extends Mob> RegistryObject<EntityType<T>> registerMob(String name, EntityType.EntityFactory<T> entity,
                                                                            float width, float height, int primaryEggColor, int secondaryEggColor) {
        RegistryObject<EntityType<T>> entityType = ENTITIES.register(name,
                () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));

        return entityType;
    }
}
