package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.*;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
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

    public static final RegistryObject<EntityType<LovebirdEntity>> LOVEBIRD = registerMob("lovebird", MobCategory.CREATURE,LovebirdEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SpoonbillEntity>> SPOONBILL = registerMob("spoonbill", MobCategory.CREATURE,SpoonbillEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<KakapoEntity>> KAKAPO = registerMob("kakapo", MobCategory.CREATURE,KakapoEntity::new,
            0.7f, 1.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<KoiEntity>> KOI = registerMob("koi", MobCategory.WATER_AMBIENT,KoiEntity::new,
            0.5f, 0.4f, 16749375, 16765696);
    public static final RegistryObject<EntityType<DottybackEntity>> DOTTYBACK = registerMob("dottyback", MobCategory.WATER_AMBIENT,DottybackEntity::new,
            0.4f, 0.2f, 16749375, 16765696);
    public static final RegistryObject<EntityType<PikeEntity>> PIKE = registerMob("pike", MobCategory.WATER_AMBIENT,PikeEntity::new,
            0.8f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<GuppyEntity>> GUPPY = registerMob("guppy", MobCategory.WATER_AMBIENT,GuppyEntity::new,
            0.3f, 0.2f, 16749375, 16765696);
    public static final RegistryObject<EntityType<MandarinDuckEntity>> MANDARIN_DUCK = registerMob("mandarin_duck", MobCategory.CREATURE,MandarinDuckEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<ArowanaEntity>> AROWANA = registerMob("arowana", MobCategory.WATER_AMBIENT,ArowanaEntity::new,
            0.5f, 0.4f, 16749375, 16765696);
    public static final RegistryObject<EntityType<RavenEntity>> RAVEN = registerMob("raven", MobCategory.CREATURE,RavenEntity::new,
            0.8f, 0.8f, 16749375, 16765696);
    public static final RegistryObject<EntityType<ShrimpEntity>> SHRIMP = registerMob("shrimp", MobCategory.WATER_AMBIENT,ShrimpEntity::new,
            0.3f, 0.2f, 16749375, 16765696);
    public static final RegistryObject<EntityType<DoveEntity>> DOVE = registerMob("dove", MobCategory.CREATURE,DoveEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<RedKiteEntity>> RED_KITE = registerMob("red_kite", MobCategory.CREATURE,RedKiteEntity::new,
            0.8f, 0.8f, 16749375, 16765696);
    public static final RegistryObject<EntityType<GoldenEagleEntity>> GOLDEN_EAGLE = registerMob("golden_eagle", MobCategory.CREATURE,GoldenEagleEntity::new,
            1.0f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SeaEagleEntity>> SEA_EAGLE = registerMob("stellers_sea_eagle", MobCategory.CREATURE,SeaEagleEntity::new,
            1.0f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<GyrfalconEntity>> GYRFALCON = registerMob("gyrfalcon", MobCategory.CREATURE,GyrfalconEntity::new,
            1.0f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<LorikeetEntity>> LORIKEET = registerMob("lorikeet", MobCategory.CREATURE,LorikeetEntity::new,
            0.7f, 0.7f, 16749375, 16765696);
    public static final RegistryObject<EntityType<ConureEntity>> CONURE = registerMob("conure", MobCategory.CREATURE,ConureEntity::new,
            0.7f, 0.7f, 16749375, 16765696);
    public static final RegistryObject<EntityType<FairywrenEntity>> FAIRYWREN = registerMob("fairywren", MobCategory.CREATURE,FairywrenEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<GhostCrabEntity>> GHOST_CRAB = registerMob("ghostcrab", MobCategory.CREATURE,GhostCrabEntity::new,
            0.4f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<GouramiEntity>> GOURAMI = registerMob("gourami", MobCategory.WATER_AMBIENT,GouramiEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<PygmyFalconEntity>> PYGMY_FALCON = registerMob("pygmyfalcon", MobCategory.CREATURE,PygmyFalconEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<BarnOwlEntity>> BARN_OWL = registerMob("barn_owl", MobCategory.CREATURE,BarnOwlEntity::new,
            0.6f, 0.7f, 16749375, 16765696);
    public static final RegistryObject<EntityType<WildDuckEntity>> WILD_DUCK = registerMob("wild_duck", MobCategory.CREATURE,WildDuckEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<RollerEntity>> ROLLER = registerMob("roller", MobCategory.CREATURE,RollerEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<GoldfishEntity>> GOLDFISH = registerMob("goldfish", MobCategory.WATER_AMBIENT,GoldfishEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<RanchuEntity>> RANCHU = registerMob("ranchu", MobCategory.CREATURE, RanchuEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<ChickadeeEntity>> CHICKADEE = registerMob("chickadee", MobCategory.CREATURE,ChickadeeEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<PygmyGooseEntity>> PYGMY_GOOSE = registerMob("pygmy_goose", MobCategory.CREATURE,PygmyGooseEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<FireGobyEntity>> FIRE_GOBY = registerMob("fire_goby", MobCategory.WATER_AMBIENT, FireGobyEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<BlueTangEntity>> BLUE_TANG = registerMob("blue_tang", MobCategory.WATER_AMBIENT, BlueTangEntity::new,
            0.2f, 0.2f, 16749375, 16765696);
    public static final RegistryObject<EntityType<TroutEntity>> TROUT = registerMob("trout", MobCategory.WATER_AMBIENT, TroutEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<FlameAngelfishEntity>> FLAME_ANGELFISH = registerMob("flame_angelfish", MobCategory.WATER_AMBIENT, FlameAngelfishEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SwallowEntity>> SWALLOW = registerMob("swallow", MobCategory.CREATURE,SwallowEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<FiddlerCrabEntity>> FIDDLER_CRAB = registerMob("fiddlercrab", MobCategory.CREATURE, FiddlerCrabEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<IbisEntity>> IBIS = registerMob("ibis", MobCategory.CREATURE,IbisEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<RedSnapperEntity>> RED_SNAPPER = registerMob("red_snapper", MobCategory.WATER_AMBIENT,RedSnapperEntity::new,
            0.6f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<WoodDuckEntity>> WOOD_DUCK = registerMob("woodduck", MobCategory.CREATURE,WoodDuckEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<PeafowlEntity>> PEAFOWL = registerMob("peafowl", MobCategory.CREATURE, PeafowlEntity::new,
            1.0f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SparrowEntity>> SPARROW = registerMob("sparrow", MobCategory.CREATURE, SparrowEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<BushtitEntity>> BUSHTIT = registerMob("bushtit", MobCategory.CREATURE, BushtitEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<EagleOwlEntity>> EAGLEOWL = registerMob("eagleowl", MobCategory.CREATURE, EagleOwlEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<RobinEntity>> ROBIN = registerMob("robin", MobCategory.CREATURE, RobinEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<LaughingthrushEntity>> LAUGHINGTHRUSH = registerMob("laughingthrush", MobCategory.CREATURE, LaughingthrushEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<MagpieEntity>> MAGPIE = registerMob("magpie", MobCategory.CREATURE, MagpieEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<GooseEntity>> GOOSE = registerMob("goose", MobCategory.CREATURE, GooseEntity::new,
            0.8f, 0.8f, 16749375, 16765696);
    public static final RegistryObject<EntityType<OspreyEntity>> OSPREY = registerMob("osprey", MobCategory.CREATURE, OspreyEntity::new,
            1.0f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<KingfisherEntity>> KINGFISHER = registerMob("kingfisher", MobCategory.CREATURE, KingfisherEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<PelicanEntity>> PELICAN = registerMob("pelican", MobCategory.CREATURE, PelicanEntity::new,
            0.8f, 0.8f, 16749375, 16765696);
    public static final RegistryObject<EntityType<LapwingEntity>> LAPWING = registerMob("lapwing", MobCategory.CREATURE, LapwingEntity::new,
            0.6f, 0.7f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SkuaEntity>> SKUA = registerMob("skua", MobCategory.CREATURE, SkuaEntity::new,
            1.0f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<BuntingEntity>> BUNTING = registerMob("bunting", MobCategory.CREATURE, BuntingEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<MonalEntity>> MONAL = registerMob("monal", MobCategory.CREATURE, MonalEntity::new,
            1.0f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<TanagerEntity>> TANAGER = registerMob("tanager", MobCategory.CREATURE, TanagerEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<FinchEntity>> FINCH = registerMob("finch", MobCategory.CREATURE, FinchEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<VampireCrabEntity>> VAMPIRECRAB = registerMob("vampirecrab", MobCategory.CREATURE, VampireCrabEntity::new,
            0.3f, 0.2f, 16749375, 16765696);
    public static final RegistryObject<EntityType<TarantulaEntity>> TARANTULA = registerMob("tarantula", MobCategory.CREATURE, TarantulaEntity::new,
            0.5f, 0.2f, 16749375, 16765696);
    public static final RegistryObject<EntityType<CapercaillieEntity>> CAPERCAILLIE = registerMob("capercaillie", MobCategory.CREATURE, CapercaillieEntity::new,
            1.0f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<PheasantEntity>> PHEASANT = registerMob("pheasant", MobCategory.CREATURE, PheasantEntity::new,
            1.0f, 0.8f, 16749375, 16765696);
    public static final RegistryObject<EntityType<TigerBarbEntity>> TIGERBARB = registerMob("tigerbarb", MobCategory.WATER_AMBIENT, TigerBarbEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<ArapaimaEntity>> ARAPAIMA = registerMob("arapaima", MobCategory.WATER_AMBIENT, ArapaimaEntity::new,
            1.0f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<PiranhaEntity>> PIRANHA = registerMob("piranha", MobCategory.WATER_AMBIENT, PiranhaEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<StorkEntity>> STORK = registerMob("stork", MobCategory.CREATURE, StorkEntity::new,
            0.6f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<WhistlingDuckEntity>> WHISTLING_DUCK = registerMob("whistlingduck", MobCategory.CREATURE, WhistlingDuckEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<GroundHornbillEntity>> GROUND_HORNBILL = registerMob("groundhornbill", MobCategory.CREATURE, GroundHornbillEntity::new,
            0.8f, 0.8f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SecretaryBirdEntity>> SECRETARYBIRD = registerMob("secretarybird", MobCategory.CREATURE, SecretaryBirdEntity::new,
            0.8f, 1.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<ShoebillEntity>> SHOEBILL = registerMob("shoebill", MobCategory.CREATURE, ShoebillEntity::new,
            0.8f, 1.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<StarlingEntity>> STARLING = registerMob("starling",MobCategory.CREATURE,  StarlingEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<TambaquiEntity>> TAMBAQUI = registerMob("tambaqui", MobCategory.CREATURE, TambaquiEntity::new,
            0.8f, 0.8f, 16749375, 16765696);
    public static final RegistryObject<EntityType<ElephantNoseFishEntity>> ELEPHANTNOSE = registerMob("elephantnose", MobCategory.CREATURE, ElephantNoseFishEntity::new,
            0.4f, 0.4f, 16749375, 16765696);
    public static final RegistryObject<EntityType<CormorantEntity>> CORMORANT = registerMob("cormorant", MobCategory.CREATURE, CormorantEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<StingrayEntity>> STINGRAY = registerMob("stingray", MobCategory.WATER_AMBIENT, StingrayEntity::new,
            0.4f, 0.7f, 16749375, 16765696);
    public static final RegistryObject<EntityType<PuffinEntity>> PUFFIN = registerMob("puffin", MobCategory.CREATURE, PuffinEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SawfishEntity>> SAWFISH = registerMob("sawfish",  MobCategory.WATER_AMBIENT, SawfishEntity::new,
            1.5f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SeagullEntity>> SEAGULL = registerMob("seagull", MobCategory.CREATURE, SeagullEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SwordfishEntity>> SWORDFISH = registerMob("swordfish", MobCategory.WATER_AMBIENT, SwordfishEntity::new,
            1.5f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<BoobyEntity>> BOOBY = registerMob("booby", MobCategory.CREATURE, BoobyEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SquidEntity>> SQUID = registerMob("squid", MobCategory.WATER_AMBIENT, SquidEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<LookdownEntity>> LOOKDOWN = registerMob("lookdown", MobCategory.WATER_AMBIENT, LookdownEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<BandedPenguinEntity>> BANDED_PENGUIN = registerMob("bandedpenguin", MobCategory.CREATURE,BandedPenguinEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<MantisShrimpEntity>> MANTIS_SHRIMP = registerMob("mantisshrimp", MobCategory.WATER_AMBIENT, MantisShrimpEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<RailEntity>> RAIL = registerMob("rail", MobCategory.CREATURE,RailEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<BarracudaEntity>> BARRACUDA = registerMob("barracuda", MobCategory.WATER_AMBIENT, BarracudaEntity::new,
            1.5f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<AvocetEntity>> AVOCET = registerMob("avocet", MobCategory.CREATURE,AvocetEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<SeaDragonEntity>> SEADRAGON = registerMob("seadragon", MobCategory.WATER_AMBIENT, SeaDragonEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<TrumpetfishEntity>> TRUMPETFISH = registerMob("trumpetfish", MobCategory.WATER_AMBIENT, TrumpetfishEntity::new,
            0.5f, 1.0f, 16749375, 16765696);
    public static final RegistryObject<EntityType<CrestedPenguinEntity>> CRESTED_PENGUIN = registerMob("crestedpenguin", MobCategory.CREATURE,CrestedPenguinEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<ParrotfishEntity>> PARROTFISH = registerMob("parrotfish", MobCategory.WATER_AMBIENT, ParrotfishEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<YellowEyedPenguinEntity>> YELLOW_EYED_PENGUIN = registerMob("yelloweyedpenguin", MobCategory.CREATURE, YellowEyedPenguinEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<BrushTailedPenguinEntity>> BRUSH_TAILED_PENGUIN = registerMob("brushtailedpenguin", MobCategory.CREATURE, BrushTailedPenguinEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<LargePenguinEntity>> LARGE_PENGUIN = registerMob("largepenguin", MobCategory.CREATURE, LargePenguinEntity::new,
            0.8f, 1.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<FrigateEntity>> FRIGATE = registerMob("frigate", MobCategory.CREATURE, FrigateEntity::new,
            0.8f, 0.8f, 16749375, 16765696);
    public static final RegistryObject<EntityType<ClownfishEntity>> CLOWNFISH = registerMob("clownfish", MobCategory.WATER_AMBIENT, ClownfishEntity::new,
            0.2f, 0.2f, 16749375, 16765696);
    public static final RegistryObject<EntityType<StiltEntity>> STILT = registerMob("stilt", MobCategory.CREATURE, StiltEntity::new,
            0.6f, 0.6f, 16749375, 16765696);
    public static final RegistryObject<EntityType<LungfishEntity>> LUNGFISH = registerMob("lungfish", MobCategory.WATER_AMBIENT, LungfishEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<LittlePenguinEntity>> LITTLE_PENGUIN = registerMob("littlepenguin", MobCategory.CREATURE, LittlePenguinEntity::new,
            0.5f, 0.5f, 16749375, 16765696);
    public static final RegistryObject<EntityType<MarabouEntity>> MARABOU = registerMob("marabou", MobCategory.CREATURE, MarabouEntity::new,
            0.5f, 0.6f, 6318976, 16765696);
    public static final RegistryObject<EntityType<EdibleCrabEntity>> EDIBLE_CRAB = registerMob("ediblecrab", MobCategory.CREATURE, EdibleCrabEntity::new,
            0.5f, 0.5f, 16749375, 16765696);

    public static final RegistryObject<EntityType<CreaturesRoeEntity>> ROE = registerMob("roe", MobCategory.CREATURE, CreaturesRoeEntity::new,
            0.3f, 0.3f, 16749375, 16765696);
    public static final RegistryObject<EntityType<EggEntity>> EGG = registerMob("egg", MobCategory.CREATURE, EggEntity::new,
            0.2F, 0.2F, 16749375, 16765696);

    public static <T extends Mob> RegistryObject<EntityType<T>> registerMob(String name, MobCategory category, EntityType.EntityFactory<T> entity,
                                                                            float width, float height, int primaryEggColor, int secondaryEggColor) {
        RegistryObject<EntityType<T>> entityType = ENTITIES.register(name,
                () -> EntityType.Builder.of(entity, category).sized(width, height).build(name));

        return entityType;
    }

    public static int getIntFromFishEntity(FishBase T) {
        if (T instanceof KoiEntity) {
            return 0;
        } else if (T instanceof DottybackEntity) {
            return 1;
        }
        else if (T instanceof PikeEntity) {
            return 2;
        } else if (T instanceof ShrimpEntity) {
            return 3;
        } else if (T instanceof GuppyEntity) {
            return 4;
        }
        else if (T instanceof GouramiEntity) {
            return 5;
        } else if (T instanceof ArowanaEntity) {
            return 6;
        }
        else if (T instanceof GoldfishEntity) {
            return 7;
        } else if (T instanceof RanchuEntity) {
            return 8;
        } else if (T instanceof FireGobyEntity) {
            return 9;
        } else if (T instanceof BlueTangEntity) {
            return 10;
        } else if (T instanceof FlameAngelfishEntity) {
            return 11;
        } else if (T instanceof TroutEntity) {
            return 12;
        }
        else if (T instanceof TigerBarbEntity) {
            return 13;
        }
        else if (T instanceof RedSnapperEntity) {
            return 14;
        } else if (T instanceof ArapaimaEntity) {
            return 15;
        }
        else if (T instanceof PiranhaEntity) {
            return 16;
        } else if (T instanceof TambaquiEntity) {
            return 17;
        } else if (T instanceof ElephantNoseFishEntity) {
            return 18;
        } else if (T instanceof StingrayEntity) {
            return 19;
        } else if (T instanceof SawfishEntity) {
            return 20;
        } else if (T instanceof SwordfishEntity) {
            return 21;
        } else if (T instanceof MantisShrimpEntity) {
            return 22;
        }
        else if (T instanceof LookdownEntity) {
            return 23;
        }  else if (T instanceof BarracudaEntity) {
            return 24;
        } else if (T instanceof SeaDragonEntity) {
            return 25;
        } else if (T instanceof TrumpetfishEntity) {
            return 26;
        } else if (T instanceof ParrotfishEntity) {
            return 27;
        } else if (T instanceof ClownfishEntity) {
            return 28;
        } else if (T instanceof LungfishEntity) {
            return 29;
        }
        return 0;
    }
}
