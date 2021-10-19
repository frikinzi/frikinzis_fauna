package com.creatures.afrikinzi;

import com.creatures.afrikinzi.config.CreaturesConfig;
import com.creatures.afrikinzi.event.CreaturesFarmerTrade;
import com.creatures.afrikinzi.event.CreaturesFishermanTrade;
import com.creatures.afrikinzi.proxy.CommonProxy;
import com.creatures.afrikinzi.recipes.CreaturesBrewing;
import com.creatures.afrikinzi.recipes.CreaturesSmelting;
import com.creatures.afrikinzi.tabs.CreaturesTab;
import com.creatures.afrikinzi.util.Reference;
import com.creatures.afrikinzi.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import software.bernie.geckolib3.GeckoLib;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, useMetadata=true, name = Reference.MOD_NAME)
public class Creatures
{
    @Mod.Instance
    public static Creatures instance;

    public static final CreativeTabs itemsblockstab = new CreaturesTab("itemsblockstabcreatures");

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
        CreaturesSmelting.init();
        CreaturesBrewing.init();
        if (CreaturesConfig.villagerTrades == true) {
            VillagerRegistry.VillagerProfession farmer = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("minecraft:farmer"));
            farmer.getCareer(0).addTrade(1, new CreaturesFarmerTrade());
            VillagerRegistry.VillagerCareer fisherman = farmer.getCareer(1);
            fisherman.addTrade(1, new CreaturesFishermanTrade());
        }


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
