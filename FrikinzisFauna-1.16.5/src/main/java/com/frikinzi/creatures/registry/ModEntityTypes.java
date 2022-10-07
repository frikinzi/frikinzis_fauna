package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.ArowanaEntity;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            Creatures.MODID);

    public static final RegistryObject<EntityType<KoiEntity>> KOI = ENTITY_TYPES.register("koi",
            () -> EntityType.Builder.of(KoiEntity::new, EntityClassification.WATER_AMBIENT).sized(0.5f, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "koi").toString()));
    public static final RegistryObject<EntityType<LovebirdEntity>> LOVEBIRD = ENTITY_TYPES.register("lovebird",
            () -> EntityType.Builder.of(LovebirdEntity::new, EntityClassification.CREATURE).sized(0.6f, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "lovebird").toString()));
    public static final RegistryObject<EntityType<DottybackEntity>> DOTTYBACK = ENTITY_TYPES.register("dottyback",
            () -> EntityType.Builder.of(DottybackEntity::new, EntityClassification.WATER_AMBIENT).sized(0.4f, 0.4F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "dottyback").toString()));
    public static final RegistryObject<EntityType<PikeEntity>> PIKE = ENTITY_TYPES.register("pike",
            () -> EntityType.Builder.of(PikeEntity::new, EntityClassification.WATER_AMBIENT).sized(1F, 0.5F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "pike").toString()));
    public static final RegistryObject<EntityType<GuppyEntity>> GUPPY = ENTITY_TYPES.register("guppy",
            () -> EntityType.Builder.of(GuppyEntity::new, EntityClassification.WATER_AMBIENT).sized(0.5F, 0.5F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "guppy").toString()));
    public static final RegistryObject<EntityType<SpoonbillEntity>> SPOONBILL = ENTITY_TYPES.register("creatures_spoonbill",
            () -> EntityType.Builder.of(SpoonbillEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "spoonbill").toString()));
    public static final RegistryObject<EntityType<KakapoEntity>> KAKAPO = ENTITY_TYPES.register("kakapo",
            () -> EntityType.Builder.of(KakapoEntity::new, EntityClassification.CREATURE).sized(1F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "kakapo").toString()));
    public static final RegistryObject<EntityType<MandarinDuckEntity>> MANDARIN_DUCK = ENTITY_TYPES.register("mandarin_duck",
            () -> EntityType.Builder.of(MandarinDuckEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "mandarin_duck").toString()));
    public static final RegistryObject<EntityType<ArowanaEntity>> AROWANA = ENTITY_TYPES.register("arowana",
            () -> EntityType.Builder.of(ArowanaEntity::new, EntityClassification.WATER_AMBIENT).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "arowana").toString()));
    public static final RegistryObject<EntityType<RavenEntity>> RAVEN = ENTITY_TYPES.register("raven",
            () -> EntityType.Builder.of(RavenEntity::new, EntityClassification.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "raven").toString()));
    public static final RegistryObject<EntityType<ShrimpEntity>> SHRIMP = ENTITY_TYPES.register("shrimp",
            () -> EntityType.Builder.of(ShrimpEntity::new, EntityClassification.WATER_AMBIENT).sized(0.8F, 0.8F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "shrimp").toString()));
    public static final RegistryObject<EntityType<DoveEntity>> DOVE = ENTITY_TYPES.register("dove",
            () -> EntityType.Builder.of(DoveEntity::new, EntityClassification.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "dove").toString()));
    public static final RegistryObject<EntityType<RedKiteEntity>> RED_KITE = ENTITY_TYPES.register("red_kite",
            () -> EntityType.Builder.of(RedKiteEntity::new, EntityClassification.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "red_kite").toString()));
    public static final RegistryObject<EntityType<GoldenEagleEntity>> GOLDEN_EAGLE = ENTITY_TYPES.register("golden_eagle",
            () -> EntityType.Builder.of(GoldenEagleEntity::new, EntityClassification.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "golden_eagle").toString()));
    public static final RegistryObject<EntityType<StellersSeaEagleEntity>> STELLERS_SEA_EAGLE = ENTITY_TYPES.register("stellers_sea_eagle",
            () -> EntityType.Builder.of(StellersSeaEagleEntity::new, EntityClassification.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "stellers_sea_eagle").toString()));
    public static final RegistryObject<EntityType<GyrfalconEntity>> GYRFALCON = ENTITY_TYPES.register("gyrfalcon",
            () -> EntityType.Builder.of(GyrfalconEntity::new, EntityClassification.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "gyrfalcon").toString()));
    public static final RegistryObject<EntityType<LorikeetEntity>> LORIKEET = ENTITY_TYPES.register("lorikeet",
            () -> EntityType.Builder.of(LorikeetEntity::new, EntityClassification.CREATURE).sized(0.7F, 0.7F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "lorikeet").toString()));
    public static final RegistryObject<EntityType<ConureEntity>> CONURE = ENTITY_TYPES.register("conure",
            () -> EntityType.Builder.of(ConureEntity::new, EntityClassification.CREATURE).sized(0.7F, 0.7F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "conure").toString()));
    public static final RegistryObject<EntityType<FairywrenEntity>> FAIRYWREN = ENTITY_TYPES.register("fairywren",
            () -> EntityType.Builder.of(FairywrenEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "fairy_wren").toString()));
    public static final RegistryObject<EntityType<GhostCrabEntity>> GHOST_CRAB = ENTITY_TYPES.register("ghostcrab",
            () -> EntityType.Builder.of(GhostCrabEntity::new, EntityClassification.CREATURE).sized(0.5F, 0.4F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "ghostcrab").toString()));
    public static final RegistryObject<EntityType<GouramiEntity>> GOURAMI = ENTITY_TYPES.register("gourami",
            () -> EntityType.Builder.of(GouramiEntity::new, EntityClassification.WATER_AMBIENT).sized(0.3F, 0.3F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "gourami").toString()));
    public static final RegistryObject<EntityType<PygmyFalconEntity>> PYGMY_FALCON = ENTITY_TYPES.register("pygmyfalcon",
            () -> EntityType.Builder.of(PygmyFalconEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "pygmyfalcon").toString()));
    public static final RegistryObject<EntityType<BarnOwlEntity>> BARN_OWL = ENTITY_TYPES.register("barn_owl",
            () -> EntityType.Builder.of(BarnOwlEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.7F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "barn_owl").toString()));
    public static final RegistryObject<EntityType<WildDuckEntity>> WILD_DUCK = ENTITY_TYPES.register("wild_duck",
            () -> EntityType.Builder.of(WildDuckEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "wildduck").toString()));
    public static final RegistryObject<EntityType<RollerEntity>> ROLLER = ENTITY_TYPES.register("roller",
            () -> EntityType.Builder.of(RollerEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "roller").toString()));
    public static final RegistryObject<EntityType<GoldfishEntity>> GOLDFISH = ENTITY_TYPES.register("goldfish",
            () -> EntityType.Builder.of(GoldfishEntity::new, EntityClassification.WATER_AMBIENT).sized(0.4F, 0.4F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "goldfish").toString()));
    public static final RegistryObject<EntityType<RanchuEntity>> RANCHU = ENTITY_TYPES.register("ranchu",
            () -> EntityType.Builder.of(RanchuEntity::new, EntityClassification.WATER_AMBIENT).sized(0.4F, 0.4F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "ranchu").toString()));
    public static final RegistryObject<EntityType<ChickadeeEntity>> CHICKADEE = ENTITY_TYPES.register("chickadee",
            () -> EntityType.Builder.of(ChickadeeEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "chickadee").toString()));
    public static final RegistryObject<EntityType<PygmyGooseEntity>> PYGMY_GOOSE = ENTITY_TYPES.register("pygmy_goose",
            () -> EntityType.Builder.of(PygmyGooseEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "pygmy_goose").toString()));
    public static final RegistryObject<EntityType<FireGobyEntity>> FIRE_GOBY = ENTITY_TYPES.register("fire_goby",
            () -> EntityType.Builder.of(FireGobyEntity::new, EntityClassification.WATER_AMBIENT).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "fire_goby").toString()));
    public static final RegistryObject<EntityType<BlueTangEntity>> BLUE_TANG = ENTITY_TYPES.register("blue_tang",
            () -> EntityType.Builder.of(BlueTangEntity::new, EntityClassification.WATER_AMBIENT).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "blue_tang").toString()));
    public static final RegistryObject<EntityType<TroutEntity>> TROUT = ENTITY_TYPES.register("trout",
            () -> EntityType.Builder.of(TroutEntity::new, EntityClassification.WATER_AMBIENT).sized(1F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "trout").toString()));
    public static final RegistryObject<EntityType<FlameAngelfishEntity>> FLAME_ANGELFISH = ENTITY_TYPES.register("flame_angelfish",
            () -> EntityType.Builder.of(FlameAngelfishEntity::new, EntityClassification.WATER_AMBIENT).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "flame_angelfish").toString()));
    public static final RegistryObject<EntityType<SwallowEntity>> SWALLOW = ENTITY_TYPES.register("swallow",
            () -> EntityType.Builder.of(SwallowEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "swallow").toString()));
    public static final RegistryObject<EntityType<FiddlerCrabEntity>> FIDDLER_CRAB = ENTITY_TYPES.register("fiddlercrab",
            () -> EntityType.Builder.of(FiddlerCrabEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "fiddlercrab").toString()));
    public static final RegistryObject<EntityType<IbisEntity>> IBIS = ENTITY_TYPES.register("ibis",
            () -> EntityType.Builder.of(IbisEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "ibis").toString()));
    public static final RegistryObject<EntityType<RedSnapperEntity>> RED_SNAPPER = ENTITY_TYPES.register("red_snapper",
            () -> EntityType.Builder.of(RedSnapperEntity::new, EntityClassification.WATER_AMBIENT).sized(1F, 1F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "red_snapper").toString()));
    public static final RegistryObject<EntityType<WoodDuckEntity>> WOOD_DUCK = ENTITY_TYPES.register("woodduck",
            () -> EntityType.Builder.of(WoodDuckEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "woodduck").toString()));
    public static final RegistryObject<EntityType<PeafowlEntity>> PEAFOWL = ENTITY_TYPES.register("peafowl",
            () -> EntityType.Builder.of(PeafowlEntity::new, EntityClassification.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "peafowl").toString()));
    public static final RegistryObject<EntityType<SparrowEntity>> SPARROW = ENTITY_TYPES.register("sparrow",
            () -> EntityType.Builder.of(SparrowEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "sparrow").toString()));
    public static final RegistryObject<EntityType<BushtitEntity>> BUSHTIT = ENTITY_TYPES.register("bushtit",
            () -> EntityType.Builder.of(BushtitEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "bushtit").toString()));
    public static final RegistryObject<EntityType<EagleOwlEntity>> EAGLEOWL = ENTITY_TYPES.register("eagleowl",
            () -> EntityType.Builder.of(EagleOwlEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "eagleowl").toString()));
    public static final RegistryObject<EntityType<RobinEntity>> ROBIN = ENTITY_TYPES.register("robin",
            () -> EntityType.Builder.of(RobinEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "robin").toString()));
    public static final RegistryObject<EntityType<LaughingthrushEntity>> LAUGHINGTHRUSH = ENTITY_TYPES.register("laughingthrush",
            () -> EntityType.Builder.of(LaughingthrushEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "laughingthrush").toString()));
    public static final RegistryObject<EntityType<MagpieEntity>> MAGPIE = ENTITY_TYPES.register("magpie",
            () -> EntityType.Builder.of(MagpieEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "magpie").toString()));
    public static final RegistryObject<EntityType<GooseEntity>> GOOSE = ENTITY_TYPES.register("goose",
            () -> EntityType.Builder.of(GooseEntity::new, EntityClassification.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "goose").toString()));
    public static final RegistryObject<EntityType<OspreyEntity>> OSPREY = ENTITY_TYPES.register("osprey",
            () -> EntityType.Builder.of(OspreyEntity::new, EntityClassification.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "osprey").toString()));
    public static final RegistryObject<EntityType<KingfisherEntity>> KINGFISHER = ENTITY_TYPES.register("kingfisher",
            () -> EntityType.Builder.of(KingfisherEntity::new, EntityClassification.CREATURE).sized(0.6F, 0.6F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "kingfisher").toString()));
    public static final RegistryObject<EntityType<PelicanEntity>> PELICAN = ENTITY_TYPES.register("pelican",
            () -> EntityType.Builder.of(PelicanEntity::new, EntityClassification.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "pelican").toString()));
    public static final RegistryObject<EntityType<LapwingEntity>> LAPWING = ENTITY_TYPES.register("lapwing",
            () -> EntityType.Builder.of(LapwingEntity::new, EntityClassification.CREATURE).sized(0.8F, 0.8F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "lapwing").toString()));
    public static final RegistryObject<EntityType<SkuaEntity>> SKUA = ENTITY_TYPES.register("skua",
            () -> EntityType.Builder.of(SkuaEntity::new, EntityClassification.CREATURE).sized(1F, 1F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "skua").toString()));

    public static final RegistryObject<EntityType<CreaturesEggEntity>> EGG = ENTITY_TYPES.register("egg",
            () -> EntityType.Builder.of(CreaturesEggEntity::new, EntityClassification.CREATURE).sized(0.3F, 0.3F)
                    .clientTrackingRange(9)
                    .build(new ResourceLocation(Creatures.MODID, "egg").toString()));

    public static int getIntFromBirdEntity(AnimalEntity T) {
        if (T instanceof LovebirdEntity) {
            return 0;
        } else if (T instanceof SpoonbillEntity) {
            return 1;
        }
        else if (T instanceof KakapoEntity) {
            return 2;
        } else if (T instanceof MandarinDuckEntity) {
            return 3;
        } else if (T instanceof RavenEntity) {
            return 4;
        } else if (T instanceof DoveEntity) {
            return 5;
        } else if (T instanceof RedKiteEntity) {
            return 6;
        } else if (T instanceof GoldenEagleEntity) {
            return 7;
        } else if (T instanceof StellersSeaEagleEntity) {
            return 8;
        } else if (T instanceof GyrfalconEntity) {
            return 9;
        } else if (T instanceof LorikeetEntity) {
            return 10;
        } else if (T instanceof ConureEntity) {
            return 11;
        } else if (T instanceof FairywrenEntity) {
            return 12;
        } else if (T instanceof PygmyFalconEntity) {
            return 13;
        } else if (T instanceof BarnOwlEntity) {
            return 14;
        } else if (T instanceof WildDuckEntity) {
            return 15;
        } else if (T instanceof RollerEntity) {
            return 16;
        } else if (T instanceof ChickadeeEntity) {
            return 17;
        } else if (T instanceof PygmyGooseEntity) {
            return 18;
        } else if (T instanceof SwallowEntity) {
            return 19;
        } else if (T instanceof IbisEntity) {
            return 20;
        } else if (T instanceof WoodDuckEntity) {
            return 21;
        } else if (T instanceof PeafowlEntity) {
            return 22;
        } else if (T instanceof SparrowEntity) {
            return 23;
        } else if (T instanceof BushtitEntity) {
            return 24;
        } else if (T instanceof EagleOwlEntity) {
            return 25;
        } else if (T instanceof RobinEntity) {
            return 26;
        } else if (T instanceof LaughingthrushEntity) {
            return 27;
        } else if (T instanceof MagpieEntity) {
            return 28;
        } else if (T instanceof GooseEntity) {
            return 29;
        } else if (T instanceof OspreyEntity) {
            return 30;
        } else if (T instanceof KingfisherEntity) {
            return 31;
        } else if (T instanceof PelicanEntity) {
            return 32;
        } else if (T instanceof LapwingEntity) {
            return 33;
        } else if (T instanceof SkuaEntity) {
            return 34;
        } else {
            return 0;
        }
    }

}
