package com.creatures.afrikinzi.util.handlers;

import com.creatures.afrikinzi.entity.arowana.EntityArowana;
import com.creatures.afrikinzi.entity.arowana.RenderArowana;
import com.creatures.afrikinzi.entity.dottyback.EntityDottyback;
import com.creatures.afrikinzi.entity.dottyback.RenderDottyback;
import com.creatures.afrikinzi.entity.dove.EntityDove;
import com.creatures.afrikinzi.entity.dove.RenderDove;
import com.creatures.afrikinzi.entity.gourami.EntityGourami;
import com.creatures.afrikinzi.entity.gourami.RenderGourami;
import com.creatures.afrikinzi.entity.guppy.EntityGuppy;
import com.creatures.afrikinzi.entity.guppy.RenderGuppy;
import com.creatures.afrikinzi.entity.kakapo.EntityKakapo;
import com.creatures.afrikinzi.entity.kakapo.RenderKakapo;
import com.creatures.afrikinzi.entity.koi.EntityKoi;
import com.creatures.afrikinzi.entity.koi.RenderKoi;
import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.entity.lovebird.RenderLovebird;
import com.creatures.afrikinzi.entity.mandarin_duck.EntityMandarinDuck;
import com.creatures.afrikinzi.entity.mandarin_duck.RenderMandarinDuck;
import com.creatures.afrikinzi.entity.pike.EntityPike;
import com.creatures.afrikinzi.entity.pike.RenderPike;
import com.creatures.afrikinzi.entity.raven.EntityRaven;
import com.creatures.afrikinzi.entity.raven.RenderRaven;
import com.creatures.afrikinzi.entity.shrimp.EntityShrimp;
import com.creatures.afrikinzi.entity.shrimp.RenderShrimp;
import com.creatures.afrikinzi.entity.spoonbill.EntitySpoonbill;
import com.creatures.afrikinzi.entity.spoonbill.RenderSpoonbill;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityKoi.class, new IRenderFactory<EntityKoi>()
    {
        @Override
        public Render<? super EntityKoi> createRenderFor(RenderManager manager) {
            return new RenderKoi(manager);
        }
    });
        RenderingRegistry.registerEntityRenderingHandler(EntityDottyback.class, new IRenderFactory<EntityDottyback>()
        {
            @Override
            public Render<? super EntityDottyback> createRenderFor(RenderManager manager) {
                return new RenderDottyback(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityLovebird.class, new IRenderFactory<EntityLovebird>()
        {
            @Override
            public Render<? super EntityLovebird> createRenderFor(RenderManager manager) {
                return new RenderLovebird(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPike.class, new IRenderFactory<EntityPike>()
        {
            @Override
            public Render<? super EntityPike> createRenderFor(RenderManager manager) {
                return new RenderPike(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityKakapo.class, new IRenderFactory<EntityKakapo>()
        {
            @Override
            public Render<? super EntityKakapo> createRenderFor(RenderManager manager) {
                return new RenderKakapo(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntitySpoonbill.class, new IRenderFactory<EntitySpoonbill>()
        {
            @Override
            public Render<? super EntitySpoonbill> createRenderFor(RenderManager manager) {
                return new RenderSpoonbill(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityMandarinDuck.class, new IRenderFactory<EntityMandarinDuck>()
        {
            @Override
            public Render<? super EntityMandarinDuck> createRenderFor(RenderManager manager) {
                return new RenderMandarinDuck(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityArowana.class, new IRenderFactory<EntityArowana>()
        {
            @Override
            public Render<? super EntityArowana> createRenderFor(RenderManager manager) {
                return new RenderArowana(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityGuppy.class, new IRenderFactory<EntityGuppy>()
        {
            @Override
            public Render<? super EntityGuppy> createRenderFor(RenderManager manager) {
                return new RenderGuppy(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityShrimp.class, new IRenderFactory<EntityShrimp>()
        {
            @Override
            public Render<? super EntityShrimp> createRenderFor(RenderManager manager) {
                return new RenderShrimp(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityRaven.class, new IRenderFactory<EntityRaven>()
        {
            @Override
            public Render<? super EntityRaven> createRenderFor(RenderManager manager) {
                return new RenderRaven(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityGourami.class, new IRenderFactory<EntityGourami>()
        {
            @Override
            public Render<? super EntityGourami> createRenderFor(RenderManager manager) {
                return new RenderGourami(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityDove.class, new IRenderFactory<EntityDove>()
        {
            @Override
            public Render<? super EntityDove> createRenderFor(RenderManager manager) {
                return new RenderDove(manager);
            }
        });
    }
}
