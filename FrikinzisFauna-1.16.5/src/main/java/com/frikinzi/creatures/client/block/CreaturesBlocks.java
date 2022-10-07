package com.frikinzi.creatures.client.block;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.registry.CreaturesItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

//@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreaturesBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Creatures.MODID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        CreaturesItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(Creatures.CreaturesItemGroup)));
    }

    public static final RegistryObject<Block> TOY1 = registerBlock("toya", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY2 = registerBlock("toyb", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY3 = registerBlock("toyc", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY4 = registerBlock("toyd", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY5 = registerBlock("toye", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY6 = registerBlock("toyf", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY7 = registerBlock("toyg", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY8 = registerBlock("toyh", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY9 = registerBlock("toyi", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY10 = registerBlock("toyj", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY11 = registerBlock("toyk", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY12 = registerBlock("toyl", () -> new ToyBlock(AbstractBlock.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
