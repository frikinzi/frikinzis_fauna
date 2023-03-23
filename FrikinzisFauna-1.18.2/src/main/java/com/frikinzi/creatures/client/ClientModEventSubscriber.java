package com.frikinzi.creatures.client;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.render.*;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CreaturesEntities.LOVEBIRD.get(), LovebirdRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.SPOONBILL.get(), SpoonbillRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.KAKAPO.get(), KakapoRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.MANDARIN_DUCK.get(), MandarinDuckRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.RAVEN.get(), RavenRenderer::new);
        event.registerEntityRenderer(CreaturesEntities.EGG.get(), EggRenderer::new);
    }
}
