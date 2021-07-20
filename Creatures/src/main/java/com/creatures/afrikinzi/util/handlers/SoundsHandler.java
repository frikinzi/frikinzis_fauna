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

    public static void registerSounds() {
        LOVEBIRD_AMBIENT = registerSound("entity.lovebird.lovebird_ambient");
        KAKAPO_AMBIENT = registerSound("entity.kakapo.kakapo_ambient");
        KAKAPO_HURT = registerSound("entity.kakapo.kakapo_hurt");
        SPOONBILL_AMBIENT = registerSound("entity.spoonbill.spoonbill_ambient");
        MANDARIN_DUCK_AMBIENT = registerSound("entity.mandarin_duck.mandarin_duck_ambient");
        RAVEN_AMBIENT = registerSound("entity.raven.raven_ambient");
        DOVE_AMBIENT = registerSound("entity.dove.dove_ambient");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
