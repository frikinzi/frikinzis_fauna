package com.frikinzi.creatures.client;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.render.*;
import com.frikinzi.creatures.entity.TanagerEntity;
import com.frikinzi.creatures.registry.ModEntityTypes;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KOI.get(), KoiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LOVEBIRD.get(), LovebirdRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DOTTYBACK.get(), DottybackRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PIKE.get(), PikeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GUPPY.get(), GuppyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SPOONBILL.get(), SpoonbillRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KAKAPO.get(), KakapoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MANDARIN_DUCK.get(), MandarinDuckRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.AROWANA.get(), ArowanaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RAVEN.get(), RavenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SHRIMP.get(), ShrimpRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DOVE.get(), DoveRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RED_KITE.get(), RedKiteRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GOLDEN_EAGLE.get(), GoldenEagleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STELLERS_SEA_EAGLE.get(), StellersSeaEagleRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GYRFALCON.get(), GyrfalconRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LORIKEET.get(), LorikeetRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CONURE.get(), ConureRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FAIRYWREN.get(), FairywrenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GHOST_CRAB.get(), GhostCrabRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GOURAMI.get(), GouramiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PYGMY_FALCON.get(), PygmyFalconRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BARN_OWL.get(), BarnOwlRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WILD_DUCK.get(), WildDuckRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ROLLER.get(), RollerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GOLDFISH.get(), GoldfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RANCHU.get(), RanchuRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CHICKADEE.get(), ChickadeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PYGMY_GOOSE.get(), PygmyGooseRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FIRE_GOBY.get(), FireGobyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BLUE_TANG.get(), BlueTangRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TROUT.get(), TroutRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SWALLOW.get(), SwallowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FIDDLER_CRAB.get(), FiddlerCrabRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FLAME_ANGELFISH.get(), FlameAngelfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.IBIS.get(), IbisRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RED_SNAPPER.get(), RedSnapperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.WOOD_DUCK.get(), WoodDuckRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PEAFOWL.get(), PeafowlRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SPARROW.get(), SparrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BUSHTIT.get(), BushtitRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.EAGLEOWL.get(), EagleOwlRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ROBIN.get(), RobinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LAUGHINGTHRUSH.get(), LaughingthrushRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MAGPIE.get(), MagpieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GOOSE.get(), GooseRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.OSPREY.get(), OspreyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KINGFISHER.get(), KingfisherRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PELICAN.get(), PelicanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LAPWING.get(), LapwingRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SKUA.get(), SkuaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BUNTING.get(), BuntingRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MONAL.get(), MonalRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TANAGER.get(), TanagerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FINCH.get(), FinchRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.VAMPIRECRAB.get(), VampireCrabRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TARANTULA.get(), TarantulaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CAPERCAILLIE.get(), CapercaillieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TIGERBARB.get(), TigerBarbRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PHEASANT.get(), PheasantRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ARAPAIMA.get(), ArapaimaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PIRANHA.get(), PiranhaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.EGG.get(), EggRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ROE.get(), RoeRenderer::new);
    }

}
