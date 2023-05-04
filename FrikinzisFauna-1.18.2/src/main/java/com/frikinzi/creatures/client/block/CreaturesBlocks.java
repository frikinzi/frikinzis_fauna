package com.frikinzi.creatures.client.block;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CreaturesBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Creatures.MODID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        CreaturesItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(Creatures.CREATURES_TAB)));
    }

    public static final RegistryObject<Block> TOY1 = registerBlock("toya", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY2 = registerBlock("toyb", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY3 = registerBlock("toyc", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY4 = registerBlock("toyd", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY5 = registerBlock("toye", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY6 = registerBlock("toyf", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY7 = registerBlock("toyg", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY8 = registerBlock("toyh", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY9 = registerBlock("toyi", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY10 = registerBlock("toyj", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY11 = registerBlock("toyk", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOY12 = registerBlock("toyl", () -> new ToyBlock(Block.Properties.of(Material.WOOL).strength(0.8F).sound(SoundType.WOOL)));
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
