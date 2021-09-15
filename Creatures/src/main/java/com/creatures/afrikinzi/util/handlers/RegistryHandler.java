package com.creatures.afrikinzi.util.handlers;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.init.BlockInit;
import com.creatures.afrikinzi.init.EntityInit;
import com.creatures.afrikinzi.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegistryHandler
{
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));

    }
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            Creatures.proxy.registerItemRenderer(item, 0, "inventory");
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

    }
    public static void postInitRegistries()
    {

    }
    public static void serverRegistries()
    {

    }

}

