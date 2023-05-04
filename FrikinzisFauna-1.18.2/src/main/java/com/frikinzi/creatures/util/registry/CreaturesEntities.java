package com.frikinzi.creatures.util.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.egg.RoeEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreaturesEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            Creatures.MODID);

    public static final RegistryObject<EntityType<LovebirdEntity>> LOVEBIRD = ENTITY_TYPES.register("lovebird",
            () -> EntityType.Builder.<LovebirdEntity>of(LovebirdEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "lovebird").toString()));
    public static final RegistryObject<EntityType<SpoonbillEntity>> SPOONBILL = ENTITY_TYPES.register("creatures_spoonbill",
            () -> EntityType.Builder.<SpoonbillEntity>of(SpoonbillEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "spoonbill").toString()));
    public static final RegistryObject<EntityType<KakapoEntity>> KAKAPO = ENTITY_TYPES.register("kakapo",
            () -> EntityType.Builder.<KakapoEntity>of(KakapoEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "kakapo").toString()));
    public static final RegistryObject<EntityType<MandarinDuckEntity>> MANDARIN_DUCK = ENTITY_TYPES.register("mandarin_duck",
            () -> EntityType.Builder.<MandarinDuckEntity>of(MandarinDuckEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "mandarin_duck").toString()));
    public static final RegistryObject<EntityType<RavenEntity>> RAVEN = ENTITY_TYPES.register("raven",
            () -> EntityType.Builder.<RavenEntity>of(RavenEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "raven").toString()));
    public static final RegistryObject<EntityType<DoveEntity>> DOVE = ENTITY_TYPES.register("dove",
            () -> EntityType.Builder.<DoveEntity>of(DoveEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "dove").toString()));
    public static final RegistryObject<EntityType<RedKiteEntity>> RED_KITE = ENTITY_TYPES.register("red_kite",
            () -> EntityType.Builder.<RedKiteEntity>of(RedKiteEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "red_kite").toString()));
    public static final RegistryObject<EntityType<GoldenEagleEntity>> GOLDEN_EAGLE = ENTITY_TYPES.register("golden_eagle",
            () -> EntityType.Builder.<GoldenEagleEntity>of(GoldenEagleEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "golden_eagle").toString()));
    public static final RegistryObject<EntityType<StellersSeaEagleEntity>> STELLERS_SEA_EAGLE = ENTITY_TYPES.register("stellers_sea_eagle",
            () -> EntityType.Builder.<StellersSeaEagleEntity>of(StellersSeaEagleEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "stellers_sea_eagle").toString()));
    public static final RegistryObject<EntityType<GyrfalconEntity>> GYRFALCON = ENTITY_TYPES.register("gyrfalcon",
            () -> EntityType.Builder.<GyrfalconEntity>of(GyrfalconEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "gyrfalcon").toString()));
    public static final RegistryObject<EntityType<LorikeetEntity>> LORIKEET = ENTITY_TYPES.register("lorikeet",
            () -> EntityType.Builder.<LorikeetEntity>of(LorikeetEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "lorikeet").toString()));
    public static final RegistryObject<EntityType<ConureEntity>> CONURE = ENTITY_TYPES.register("conure",
            () -> EntityType.Builder.<ConureEntity>of(ConureEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "conure").toString()));
    public static final RegistryObject<EntityType<FairywrenEntity>> FAIRYWREN = ENTITY_TYPES.register("fairywren",
            () -> EntityType.Builder.<FairywrenEntity>of(FairywrenEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "fairywren").toString()));
    public static final RegistryObject<EntityType<PygmyFalconEntity>> PYGMY_FALCON = ENTITY_TYPES.register("pygmyfalcon",
            () -> EntityType.Builder.<PygmyFalconEntity>of(PygmyFalconEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "pygmyfalcon").toString()));
    public static final RegistryObject<EntityType<BarnOwlEntity>> BARN_OWL = ENTITY_TYPES.register("barn_owl",
            () -> EntityType.Builder.<BarnOwlEntity>of(BarnOwlEntity::new, MobCategory.CREATURE).sized(0.7F, 0.7F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "barn_owl").toString()));
    public static final RegistryObject<EntityType<WildDuckEntity>> WILD_DUCK = ENTITY_TYPES.register("wild_duck",
            () -> EntityType.Builder.<WildDuckEntity>of(WildDuckEntity::new, MobCategory.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "wild_duck").toString()));
    public static final RegistryObject<EntityType<RollerEntity>> ROLLER = ENTITY_TYPES.register("roller",
            () -> EntityType.Builder.<RollerEntity>of(RollerEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "roller").toString()));
    public static final RegistryObject<EntityType<ChickadeeEntity>> CHICKADEE = ENTITY_TYPES.register("chickadee",
            () -> EntityType.Builder.<ChickadeeEntity>of(ChickadeeEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "chickadee").toString()));
    public static final RegistryObject<EntityType<PygmyGooseEntity>> PYGMY_GOOSE = ENTITY_TYPES.register("pygmy_goose",
            () -> EntityType.Builder.<PygmyGooseEntity>of(PygmyGooseEntity::new, MobCategory.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "pygmy_goose").toString()));
    public static final RegistryObject<EntityType<SwallowEntity>> SWALLOW = ENTITY_TYPES.register("swallow",
            () -> EntityType.Builder.<SwallowEntity>of(SwallowEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "swallow").toString()));
    public static final RegistryObject<EntityType<IbisEntity>> IBIS = ENTITY_TYPES.register("ibis",
            () -> EntityType.Builder.<IbisEntity>of(IbisEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "ibis").toString()));
    public static final RegistryObject<EntityType<WoodDuckEntity>> WOOD_DUCK = ENTITY_TYPES.register("woodduck",
            () -> EntityType.Builder.<WoodDuckEntity>of(WoodDuckEntity::new, MobCategory.CREATURE).sized(0.5F, 0.6F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "woodduck").toString()));
    public static final RegistryObject<EntityType<PeafowlEntity>> PEAFOWL = ENTITY_TYPES.register("peafowl",
            () -> EntityType.Builder.<PeafowlEntity>of(PeafowlEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "peafowl").toString()));
    public static final RegistryObject<EntityType<SparrowEntity>> SPARROW = ENTITY_TYPES.register("sparrow",
            () -> EntityType.Builder.<SparrowEntity>of(SparrowEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "sparrow").toString()));
    public static final RegistryObject<EntityType<BushtitEntity>> BUSHTIT = ENTITY_TYPES.register("bushtit",
            () -> EntityType.Builder.<BushtitEntity>of(BushtitEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "bushtit").toString()));
    public static final RegistryObject<EntityType<EagleOwlEntity>> EAGLEOWL = ENTITY_TYPES.register("eagleowl",
            () -> EntityType.Builder.<EagleOwlEntity>of(EagleOwlEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "eagleowl").toString()));
    public static final RegistryObject<EntityType<RobinEntity>> ROBIN = ENTITY_TYPES.register("robin",
            () -> EntityType.Builder.<RobinEntity>of(RobinEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "robin").toString()));
    public static final RegistryObject<EntityType<LaughingthrushEntity>> LAUGHINGTHRUSH = ENTITY_TYPES.register("laughingthrush",
            () -> EntityType.Builder.<LaughingthrushEntity>of(LaughingthrushEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "laughingthrush").toString()));
    public static final RegistryObject<EntityType<MagpieEntity>> MAGPIE = ENTITY_TYPES.register("magpie",
            () -> EntityType.Builder.<MagpieEntity>of(MagpieEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "magpie").toString()));
    public static final RegistryObject<EntityType<GooseEntity>> GOOSE = ENTITY_TYPES.register("goose",
            () -> EntityType.Builder.<GooseEntity>of(GooseEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "goose").toString()));
    public static final RegistryObject<EntityType<OspreyEntity>> OSPREY = ENTITY_TYPES.register("osprey",
            () -> EntityType.Builder.<OspreyEntity>of(OspreyEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "osprey").toString()));
    public static final RegistryObject<EntityType<KingfisherEntity>> KINGFISHER = ENTITY_TYPES.register("kingfisher",
            () -> EntityType.Builder.<KingfisherEntity>of(KingfisherEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "kingfisher").toString()));
    public static final RegistryObject<EntityType<PelicanEntity>> PELICAN = ENTITY_TYPES.register("pelican",
            () -> EntityType.Builder.<PelicanEntity>of(PelicanEntity::new, MobCategory.CREATURE).sized(0.7F, 0.7F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "pelican").toString()));
    public static final RegistryObject<EntityType<LapwingEntity>> LAPWING = ENTITY_TYPES.register("lapwing",
            () -> EntityType.Builder.<LapwingEntity>of(LapwingEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "lapwing").toString()));
    public static final RegistryObject<EntityType<SkuaEntity>> SKUA = ENTITY_TYPES.register("skua",
            () -> EntityType.Builder.<SkuaEntity>of(SkuaEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "skua").toString()));
    public static final RegistryObject<EntityType<BuntingEntity>> BUNTING = ENTITY_TYPES.register("bunting",
            () -> EntityType.Builder.<BuntingEntity>of(BuntingEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "bunting").toString()));
    public static final RegistryObject<EntityType<MonalEntity>> MONAL = ENTITY_TYPES.register("monal",
            () -> EntityType.Builder.<MonalEntity>of(MonalEntity::new, MobCategory.CREATURE).sized(0.7F, 0.7F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "monal").toString()));
    public static final RegistryObject<EntityType<TanagerEntity>> TANAGER = ENTITY_TYPES.register("tanager",
            () -> EntityType.Builder.<TanagerEntity>of(TanagerEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "tanager").toString()));
    public static final RegistryObject<EntityType<FinchEntity>> FINCH = ENTITY_TYPES.register("finch",
            () -> EntityType.Builder.<FinchEntity>of(FinchEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "finch").toString()));
    public static final RegistryObject<EntityType<CapercaillieEntity>> CAPERCAILLIE = ENTITY_TYPES.register("capercaillie",
            () -> EntityType.Builder.<CapercaillieEntity>of(CapercaillieEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "capercaillie").toString()));
    public static final RegistryObject<EntityType<PheasantEntity>> PHEASANT = ENTITY_TYPES.register("pheasant",
            () -> EntityType.Builder.<PheasantEntity>of(PheasantEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "pheasant").toString()));
    public static final RegistryObject<EntityType<StorkEntity>> STORK = ENTITY_TYPES.register("stork",
            () -> EntityType.Builder.<StorkEntity>of(StorkEntity::new, MobCategory.CREATURE).sized(0.5F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "stork").toString()));

    public static final RegistryObject<EntityType<KoiEntity>> KOI = ENTITY_TYPES.register("koi",
            () -> EntityType.Builder.<KoiEntity>of(KoiEntity::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "koi").toString()));
    public static final RegistryObject<EntityType<DottybackEntity>> DOTTYBACK = ENTITY_TYPES.register("dottyback",
            () -> EntityType.Builder.<DottybackEntity>of(DottybackEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "dottyback").toString()));
    public static final RegistryObject<EntityType<PikeEntity>> PIKE = ENTITY_TYPES.register("pike",
            () -> EntityType.Builder.<PikeEntity>of(PikeEntity::new, MobCategory.WATER_AMBIENT).sized(0.7F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "pike").toString()));
    public static final RegistryObject<EntityType<GuppyEntity>> GUPPY = ENTITY_TYPES.register("guppy",
            () -> EntityType.Builder.<GuppyEntity>of(GuppyEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "guppy").toString()));
    public static final RegistryObject<EntityType<ArowanaEntity>> AROWANA = ENTITY_TYPES.register("arowana",
            () -> EntityType.Builder.<ArowanaEntity>of(ArowanaEntity::new, MobCategory.WATER_AMBIENT).sized(0.6F, 0.4F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "arowana").toString()));
    public static final RegistryObject<EntityType<ShrimpEntity>> SHRIMP = ENTITY_TYPES.register("shrimp",
            () -> EntityType.Builder.<ShrimpEntity>of(ShrimpEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "shrimp").toString()));
    public static final RegistryObject<EntityType<GouramiEntity>> GOURAMI = ENTITY_TYPES.register("gourami",
            () -> EntityType.Builder.<GouramiEntity>of(GouramiEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "gourami").toString()));
    public static final RegistryObject<EntityType<GoldfishEntity>> GOLDFISH = ENTITY_TYPES.register("goldfish",
            () -> EntityType.Builder.<GoldfishEntity>of(GoldfishEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "goldfish").toString()));
    public static final RegistryObject<EntityType<RanchuEntity>> RANCHU = ENTITY_TYPES.register("ranchu",
            () -> EntityType.Builder.<RanchuEntity>of(RanchuEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "ranchu").toString()));
    public static final RegistryObject<EntityType<FireGobyEntity>> FIRE_GOBY = ENTITY_TYPES.register("fire_goby",
            () -> EntityType.Builder.<FireGobyEntity>of(FireGobyEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "fire_goby").toString()));
    public static final RegistryObject<EntityType<BlueTangEntity>> BLUE_TANG = ENTITY_TYPES.register("blue_tang",
            () -> EntityType.Builder.<BlueTangEntity>of(BlueTangEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "blue_tang").toString()));
    public static final RegistryObject<EntityType<FlameAngelfishEntity>> FLAME_ANGELFISH = ENTITY_TYPES.register("flame_angelfish",
            () -> EntityType.Builder.<FlameAngelfishEntity>of(FlameAngelfishEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "flame_angelfish").toString()));
    public static final RegistryObject<EntityType<TroutEntity>> TROUT = ENTITY_TYPES.register("trout",
            () -> EntityType.Builder.<TroutEntity>of(TroutEntity::new, MobCategory.WATER_AMBIENT).sized(0.6F, 0.4F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "trout").toString()));
    public static final RegistryObject<EntityType<RedSnapperEntity>> RED_SNAPPER = ENTITY_TYPES.register("red_snapper",
            () -> EntityType.Builder.<RedSnapperEntity>of(RedSnapperEntity::new, MobCategory.WATER_AMBIENT).sized(0.6F, 0.4F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "red_snapper").toString()));
    public static final RegistryObject<EntityType<TigerBarbEntity>> TIGER_BARB = ENTITY_TYPES.register("tigerbarb",
            () -> EntityType.Builder.<TigerBarbEntity>of(TigerBarbEntity::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "tigerbarb").toString()));
    public static final RegistryObject<EntityType<ArapaimaEntity>> ARAPAIMA = ENTITY_TYPES.register("arapaima",
            () -> EntityType.Builder.<ArapaimaEntity>of(ArapaimaEntity::new, MobCategory.WATER_AMBIENT).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "arapaima").toString()));
    public static final RegistryObject<EntityType<PiranhaEntity>> PIRANHA = ENTITY_TYPES.register("piranha",
            () -> EntityType.Builder.<PiranhaEntity>of(PiranhaEntity::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.5F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "piranha").toString()));
    public static final RegistryObject<EntityType<GhostCrabEntity>> GHOST_CRAB = ENTITY_TYPES.register("ghostcrab",
            () -> EntityType.Builder.<GhostCrabEntity>of(GhostCrabEntity::new, MobCategory.CREATURE).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "ghostcrab").toString()));
    public static final RegistryObject<EntityType<FiddlerCrabEntity>> FIDDLER_CRAB = ENTITY_TYPES.register("fiddlercrab",
            () -> EntityType.Builder.<FiddlerCrabEntity>of(FiddlerCrabEntity::new, MobCategory.CREATURE).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "fiddlercrab").toString()));
    public static final RegistryObject<EntityType<VampireCrabEntity>> VAMPIRE_CRAB = ENTITY_TYPES.register("vampirecrab",
            () -> EntityType.Builder.<VampireCrabEntity>of(VampireCrabEntity::new, MobCategory.CREATURE).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "vampirecrab").toString()));
    public static final RegistryObject<EntityType<TarantulaEntity>> TARANTULA = ENTITY_TYPES.register("tarantula",
            () -> EntityType.Builder.<TarantulaEntity>of(TarantulaEntity::new, MobCategory.CREATURE).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "tarantula").toString()));
    public static final RegistryObject<EntityType<TambaquiEntity>> TAMBAQUI = ENTITY_TYPES.register("tambaqui",
            () -> EntityType.Builder.<TambaquiEntity>of(TambaquiEntity::new, MobCategory.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "tambaqui").toString()));
    public static final RegistryObject<EntityType<ElephantNoseFishEntity>> ELEPHANT_NOSE = ENTITY_TYPES.register("elephant_nose",
            () -> EntityType.Builder.<ElephantNoseFishEntity>of(ElephantNoseFishEntity::new, MobCategory.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "tambaqui").toString()));

    public static final RegistryObject<EntityType<EggEntity>> EGG = ENTITY_TYPES.register("egg",
            () -> EntityType.Builder.<EggEntity>of(EggEntity::new, MobCategory.CREATURE).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "egg").toString()));
    public static final RegistryObject<EntityType<RoeEntity>> ROE = ENTITY_TYPES.register("roe",
            () -> EntityType.Builder.<RoeEntity>of(RoeEntity::new, MobCategory.CREATURE).sized(0.3F, 0.3F)
                    .clientTrackingRange(10).build(new ResourceLocation(Creatures.MODID, "roe").toString()));

//    public static int getIntFromBirdEntity(CreaturesBirdEntity T) {
//        if (T instanceof LovebirdEntity) {
//            return 0;
//        } else if (T instanceof SpoonbillEntity) {
//            return 1;
//        } else if (T instanceof KakapoEntity) {
//            return 2;
//        } else if (T instanceof MandarinDuckEntity) {
//            return 3;
//        } else if (T instanceof RavenEntity) {
//            return 4;
//        } else if (T instanceof DoveEntity) {
//            return 5;
//        } else if (T instanceof RedKiteEntity) {
//            return 6;
//        } else if (T instanceof GoldenEagleEntity) {
//            return 7;
//        } else if (T instanceof StellersSeaEagleEntity) {
//            return 8;
//        } else if (T instanceof GyrfalconEntity) {
//            return 9;
//        } else if (T instanceof LorikeetEntity) {
//            return 10;
//        } else if (T instanceof ConureEntity) {
//            return 11;
//        } else if (T instanceof FairywrenEntity) {
//            return 12;
//        } else if (T instanceof PygmyFalconEntity) {
//            return 13;
//        } return 0;
//    }

}
