package com.creatures.afrikinzi;

import com.creatures.afrikinzi.entity.lovebird.EntityLovebird;
import com.creatures.afrikinzi.proxy.CommonProxy;
import com.creatures.afrikinzi.util.Reference;
import com.creatures.afrikinzi.util.handlers.RegistryHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, useMetadata=true, name = Reference.MOD_NAME)
public class Creatures
{
    public Creatures() {

        //GeckoLib.initialize();
    }
    @Mod.Instance
    public static Creatures instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries(event);
        GeckoLib.initialize();
    }



    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        RegistryHandler.initRegistries();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        RegistryHandler.postInitRegistries();
    }

    @Mod.EventHandler
    public static void serverStarting(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries();
    }

}
