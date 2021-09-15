package com.creatures.afrikinzi.proxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class ServerProxy extends CommonProxy {
    public void onInit()
    {
        //GameRegistry.registerWorldGenerator(new CreaturesWorldGen(), 0);
    }
}
