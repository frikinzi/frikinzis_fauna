package com.frikinzi.creatures.client.painting;

import com.frikinzi.creatures.Creatures;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CreaturesPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Creatures.MODID);

    public static final RegistryObject<PaintingVariant> FISCHERS =
            PAINTING_VARIANTS.register("fischers", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> MANDARIN_DUCK =
            PAINTING_VARIANTS.register("mandarin_duck", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> PEAFOWL =
            PAINTING_VARIANTS.register("peafowl", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> GOLDEN_EAGLE =
            PAINTING_VARIANTS.register("golden_eagle", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> ARAPAIMA =
            PAINTING_VARIANTS.register("arapaima", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> VICTORIA_CROWNED =
            PAINTING_VARIANTS.register("victoria_crowned", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> ZOLDA =
            PAINTING_VARIANTS.register("zolda", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> C_ELEGANS =
            PAINTING_VARIANTS.register("c_elegans", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> TROUT_INFO =
            PAINTING_VARIANTS.register("trout_info", () -> new PaintingVariant(32, 32));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}