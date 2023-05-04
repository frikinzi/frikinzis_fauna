package com.frikinzi.creatures.client.painting;

import com.frikinzi.creatures.Creatures;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CreaturesPaintings {
    public static final DeferredRegister<Motive> PAINTING_MOVTIES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, Creatures.MODID);

    public static final RegistryObject<Motive> FISCHERS =
            PAINTING_MOVTIES.register("fischers", () -> new Motive(16, 16));
    public static final RegistryObject<Motive> MANDARIN_DUCK =
            PAINTING_MOVTIES.register("mandarin_duck", () -> new Motive(16, 16));
    public static final RegistryObject<Motive> PEAFOWL =
            PAINTING_MOVTIES.register("peafowl", () -> new Motive(16, 16));
    public static final RegistryObject<Motive> GOLDEN_EAGLE =
            PAINTING_MOVTIES.register("golden_eagle", () -> new Motive(16, 16));
    public static final RegistryObject<Motive> ARAPAIMA =
            PAINTING_MOVTIES.register("arapaima", () -> new Motive(32, 16));
    public static final RegistryObject<Motive> VICTORIA_CROWNED =
            PAINTING_MOVTIES.register("victoria_crowned", () -> new Motive(16, 16));
    public static final RegistryObject<Motive> ZOLDA =
            PAINTING_MOVTIES.register("zolda", () -> new Motive(32, 32));
    public static final RegistryObject<Motive> C_ELEGANS =
            PAINTING_MOVTIES.register("c_elegans", () -> new Motive(32, 16));
    public static final RegistryObject<Motive> TROUT_INFO =
            PAINTING_MOVTIES.register("trout_info", () -> new Motive(32, 32));

    public static void register(IEventBus eventBus) {
        PAINTING_MOVTIES.register(eventBus);
    }
}
