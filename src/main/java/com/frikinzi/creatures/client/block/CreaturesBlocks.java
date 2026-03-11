package com.frikinzi.creatures.client.block;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.registry.CreaturesItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CreaturesBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Creatures.MODID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        CreaturesItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static final BlockBehaviour.Properties TOY_PROPS =
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOL)
                    .strength(0.8F)
                    .sound(SoundType.WOOL);

    public static final RegistryObject<Block> TOY1  = registerBlock("toya",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY2  = registerBlock("toyb",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY3  = registerBlock("toyc",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY4  = registerBlock("toyd",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY5  = registerBlock("toye",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY6  = registerBlock("toyf",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY7  = registerBlock("toyg",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY8  = registerBlock("toyh",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY9  = registerBlock("toyi",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY10 = registerBlock("toyj",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY11 = registerBlock("toyk",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY12 = registerBlock("toyl",  () -> new ToyBlock(TOY_PROPS));
    public static final RegistryObject<Block> TOY13 = registerBlock("toym",  () -> new ToyBlock(TOY_PROPS));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
