package com.creatures.afrikinzi.util.handlers;


import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
    public static SoundEvent LOVEBIRD_AMBIENT;
    public static SoundEvent KAKAPO_AMBIENT;
    public static SoundEvent KAKAPO_HURT;
    public static SoundEvent SPOONBILL_AMBIENT;
    public static SoundEvent MANDARIN_DUCK_AMBIENT;
    public static SoundEvent RAVEN_AMBIENT;
    public static SoundEvent DOVE_AMBIENT;
    public static SoundEvent GOLDEN_EAGLE_AMBIENT;
    public static SoundEvent STELLERS_SEA_EAGLE_AMBIENT;
    public static SoundEvent GYRFALCON_AMBIENT;
    public static SoundEvent IBERIAN_LYNX_AMBIENT;
    public static SoundEvent CONURE_AMBIENT;
    public static SoundEvent LORIKEET_AMBIENT;
    public static SoundEvent LORIKEET_AMBIENT2;
    public static SoundEvent RED_KITE_AMBIENT;
    public static SoundEvent FAIRYWREN_AMBIENT;

    public static void registerSounds() {
        LOVEBIRD_AMBIENT = registerSound("entity.lovebird.lovebird_ambient");
        KAKAPO_AMBIENT = registerSound("entity.kakapo.kakapo_ambient");
        KAKAPO_HURT = registerSound("entity.kakapo.kakapo_hurt");
        SPOONBILL_AMBIENT = registerSound("entity.creatures_spoonbill.spoonbill_ambient");
        MANDARIN_DUCK_AMBIENT = registerSound("entity.mandarin_duck.mandarin_duck_ambient");
        RAVEN_AMBIENT = registerSound("entity.raven.raven_ambient");
        DOVE_AMBIENT = registerSound("entity.dove.dove_ambient");
        GOLDEN_EAGLE_AMBIENT = registerSound("entity.golden_eagle.golden_eagle_ambient");
        STELLERS_SEA_EAGLE_AMBIENT = registerSound("entity.stellers_sea_eagle.stellers_sea_eagle_ambient");
        GYRFALCON_AMBIENT = registerSound("entity.gyrfalcon.gyrfalcon_ambient");
        IBERIAN_LYNX_AMBIENT = registerSound("entity.iberian_lynx.lynx_ambient");
        CONURE_AMBIENT = registerSound("entity.conure.conure_ambient");
        LORIKEET_AMBIENT = registerSound("entity.lorikeet.lorikeet_ambient");
        LORIKEET_AMBIENT2 = registerSound("entity.lorikeet.lorikeet_ambient2");
        RED_KITE_AMBIENT = registerSound("entity.red_kite.red_kite_ambient");
        FAIRYWREN_AMBIENT = registerSound("entity.fairy_wren.fairy_wren_ambient");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
