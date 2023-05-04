package com.frikinzi.creatures;

import com.frikinzi.creatures.client.ClientProxy;
import com.frikinzi.creatures.client.block.CreaturesBlocks;
import com.frikinzi.creatures.client.painting.CreaturesPaintings;
import com.frikinzi.creatures.config.CreaturesConfig;
import com.frikinzi.creatures.util.FFLootModifier;
import com.frikinzi.creatures.util.registry.BrewingRecipe;
import com.frikinzi.creatures.util.registry.CreaturesEntities;
import com.frikinzi.creatures.util.registry.CreaturesItems;
import com.frikinzi.creatures.util.registry.ModEntitySpawn;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.network.GeckoLibNetwork;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Creatures.MODID)
public class Creatures
{
    // Directly reference a slf4j logger
    public static Creatures instance;
    public static final String MODID = "creatures";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public Creatures()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        CreaturesBlocks.register(modEventBus);
        CreaturesEntities.ENTITY_TYPES.register(modEventBus);
        CreaturesItems.ITEMS.register(modEventBus);
        CreaturesPaintings.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CreaturesConfig.SPEC, "frikinzis-fauna-common.toml");
        GeckoLib.initialize();
        GeckoLibNetwork.initialize();
    }

    //@SubscribeEvent
    //public void onBiomeLoad(BiomeLoadingEvent event) {
    //    ModEntitySpawn.onBiomesLoad(event);
    //}


    public static final CreativeModeTab CREATURES_TAB = (new CreativeModeTab("creaturesitems") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CreaturesItems.RAW_KOI.get());
        }
    });

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.AWKWARD,
                    CreaturesItems.GUPPY_TAIL.get(), Potions.WATER_BREATHING));
            BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.AWKWARD,
                    CreaturesItems.GOLDFISH.get(), Potions.LUCK));
            BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.AWKWARD,
                    CreaturesItems.GOURAMI.get(), Potions.SWIFTNESS));
            BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.AWKWARD,
                    CreaturesItems.RAW_AROWANA.get(), Potions.LONG_WATER_BREATHING));
            BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.AWKWARD,
                    CreaturesItems.BIRD_OF_PREY_FEATHER.get(), Potions.STRENGTH));
            BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.AWKWARD,
                    CreaturesItems.RAVEN_FEATHER.get(), Potions.NIGHT_VISION));
            BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.AWKWARD,
                    CreaturesItems.PARROT_FEATHER.get(), Potions.REGENERATION));
        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
        }

        @SubscribeEvent
        public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                               event) {
            event.getRegistry().registerAll(
                    new FFLootModifier.Serializer().setRegistryName
                            (new ResourceLocation(Creatures.MODID,"mealworms_dirt")),
                    new FFLootModifier.Serializer().setRegistryName
                            (new ResourceLocation(Creatures.MODID,"mealworms_farmland"))
            );
        }
    }
}
