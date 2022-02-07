package com.creatures.afrikinzi.util.handlers;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.init.BlockInit;
import com.creatures.afrikinzi.init.EntityInit;
import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegistryHandler
{
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        //event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));

    }
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
            //Creatures.proxy.registerItemRenderer(item, 0, "inventory");
        }
        for (Block block : BlockInit.BLOCKS) {
            Creatures.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");

        }
        RenderHandler.registerEntityRenders();
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));

    }

    public static void preInitRegistries(FMLPreInitializationEvent event)
    {
        EntityInit.registerEntities();
        //RenderHandler.registerEntityRenders();
        SoundsHandler.registerSounds();
    }
    public static void initRegistries()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Creatures.instance, new GUIHandler());
    }
    public static void postInitRegistries()
    {

    }
    public static void serverRegistries()
    {

    }

}

