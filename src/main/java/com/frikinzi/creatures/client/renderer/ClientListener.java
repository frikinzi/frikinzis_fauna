package com.frikinzi.creatures.client.renderer;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.model.entity.DottybackRenderer;
import com.frikinzi.creatures.client.model.entity.PikeRenderer;
import com.frikinzi.creatures.client.model.entity.ShrimpRenderer;
import com.frikinzi.creatures.client.renderer.entity.*;
import com.frikinzi.creatures.registry.CreaturesEntities;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

    @SubscribeEvent
    public static void addLayers(final EntityRenderersEvent.AddLayers event) {
        PlayerRenderer rendererDefault = event.getSkin("default");
        PlayerRenderer rendererSlim = event.getSkin("slim");

        if (rendererDefault != null) {
            rendererDefault.addLayer(new ShoulderLayer<>(rendererDefault));
        }
        if (rendererSlim != null) {
            rendererSlim.addLayer(new ShoulderLayer<>(rendererSlim));
        }
    }
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CreaturesEntities.LOVEBIRD.get(), LovebirdRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SPOONBILL.get(), SpoonbillRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.KAKAPO.get(), KakapoRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.KOI.get(), KoiRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.DOTTYBACK.get(), DottybackRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.PIKE.get(), PikeRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.GUPPY.get(), GuppyRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.MANDARIN_DUCK.get(), MandarinDuckRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.AROWANA.get(), ArowanaRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.RAVEN.get(), RavenRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SHRIMP.get(), ShrimpRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.DOVE.get(), DoveRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.RED_KITE.get(), RedKiteRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.GOLDEN_EAGLE.get(), GoldenEagleRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SEA_EAGLE.get(), SeaEagleRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.GYRFALCON.get(), GyrfalconRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.LORIKEET.get(), LorikeetRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.CONURE.get(), ConureRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.FAIRYWREN.get(), FairywrenRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.GHOST_CRAB.get(), GhostCrabRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.GOURAMI.get(), GouramiRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.PYGMY_FALCON.get(), PygmyFalconRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.BARN_OWL.get(), BarnOwlRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.WILD_DUCK.get(), WildDuckRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.ROLLER.get(), RollerRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.GOLDFISH.get(), GoldfishRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.RANCHU.get(), RanchuRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.CHICKADEE.get(), ChickadeeRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.PYGMY_GOOSE.get(), PygmyGooseRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.FIRE_GOBY.get(), FireGobyRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.BLUE_TANG.get(), BlueTangRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.TROUT.get(), TroutRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.FLAME_ANGELFISH.get(), FlameAngelfishRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SWALLOW.get(), SwallowRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.FIDDLER_CRAB.get(), FiddlerCrabRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.IBIS.get(), IbisRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.RED_SNAPPER.get(), RedSnapperRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.WOOD_DUCK.get(), WoodDuckRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.PEAFOWL.get(), PeafowlRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SPARROW.get(), SparrowRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.BUSHTIT.get(), BushtitRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.EAGLEOWL.get(), EagleOwlRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.ROBIN.get(), RobinRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.LAUGHINGTHRUSH.get(), LaughingthrushRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.MAGPIE.get(), MagpieRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.GOOSE.get(), GooseRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.OSPREY.get(), OspreyRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.KINGFISHER.get(), KingfisherRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.PELICAN.get(), PelicanRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.LAPWING.get(), LapwingRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SKUA.get(), SkuaRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.BUNTING.get(), BuntingRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.MONAL.get(), MonalRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.TANAGER.get(), TanagerRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.FINCH.get(), FinchRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.VAMPIRECRAB.get(), VampireCrabRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.TARANTULA.get(), TarantulaRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.CAPERCAILLIE.get(), CapercaillieRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.PHEASANT.get(), PheasantRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.TIGERBARB.get(), TigerBarbRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.ARAPAIMA.get(), ArapaimaRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.PIRANHA.get(), PiranhaRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.STORK.get(), StorkRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.WHISTLING_DUCK.get(), WhistlingDuckRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.GROUND_HORNBILL.get(), GroundHornbillRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SECRETARYBIRD.get(), SecretaryBirdRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SHOEBILL.get(), ShoebillRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.STARLING.get(), StarlingRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.TAMBAQUI.get(), TambaquiRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.ELEPHANTNOSE.get(), ElephantNoseFishRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.CORMORANT.get(), CormorantRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.STINGRAY.get(), StingrayRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.PUFFIN.get(), PuffinRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SAWFISH.get(), SawfishRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SEAGULL.get(), SeagullRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SWORDFISH.get(), SwordfishRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.BOOBY.get(), BoobyRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SQUID.get(), SquidRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.LOOKDOWN.get(), LookdownRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.BANDED_PENGUIN.get(), BandedPenguinRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.MANTIS_SHRIMP.get(), MantisShrimpRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.RAIL.get(), RailRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.BARRACUDA.get(), BarracudaRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.AVOCET.get(), AvocetRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SEADRAGON.get(), SeaDragonRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.TRUMPETFISH.get(), TrumpetfishRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.CRESTED_PENGUIN.get(), CrestedPenguinRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.PARROTFISH.get(), ParrotfishRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.YELLOW_EYED_PENGUIN.get(), YellowEyedPenguinRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.BRUSH_TAILED_PENGUIN.get(), BrushTailedPenguinRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.LARGE_PENGUIN.get(), LargePenguinRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.FRIGATE.get(), FrigateRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.CLOWNFISH.get(), ClownfishRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.STILT.get(), StiltRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.LUNGFISH.get(), LungfishRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.LITTLE_PENGUIN.get(), LittlePenguinRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.EDIBLE_CRAB.get(), EdibleCrabRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.MARABOU.get(), MarabouRenderer::new);

        event.registerEntityRenderer(CreaturesEntities.ROE.get(), RoeRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.EGG.get(), EggRenderer::new);

    }
}
