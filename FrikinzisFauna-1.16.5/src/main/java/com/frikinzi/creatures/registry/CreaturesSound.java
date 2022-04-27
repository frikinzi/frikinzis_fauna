package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("WeakerAccess")
public class CreaturesSound {
    public static final SoundEvent LOVEBIRD_AMBIENT = createSoundEvent("entity.lovebird.ambient");
    public static final SoundEvent SPOONBILL_AMBIENT = createSoundEvent("entity.creatures_spoonbill.ambient");
    public static final SoundEvent KAKAPO_AMBIENT = createSoundEvent("entity.kakapo.ambient");
    public static final SoundEvent MANDARIN_DUCK_AMBIENT = createSoundEvent("entity.mandarin_duck.ambient");
    public static final SoundEvent RAVEN_AMBIENT = createSoundEvent("entity.raven.ambient");
    public static final SoundEvent DOVE_AMBIENT = createSoundEvent("entity.dove.ambient");
    public static final SoundEvent GOLDEN_EAGLE_AMBIENT = createSoundEvent("entity.golden_eagle.ambient");
    public static final SoundEvent STELLERS_SEA_EAGLE_AMBIENT = createSoundEvent("entity.stellers_sea_eagle.ambient");
    public static final SoundEvent GYRFALCON_AMBIENT = createSoundEvent("entity.gyrfalcon.ambient");
    public static final SoundEvent RED_KITE_AMBIENT = createSoundEvent("entity.red_kite.ambient");
    public static final SoundEvent LORIKEET_AMBIENT = createSoundEvent("entity.lorikeet.ambient");
    public static final SoundEvent LORIKEET_AMBIENT2 = createSoundEvent("entity.lorikeet.ambient2");
    public static final SoundEvent CONURE_AMBIENT = createSoundEvent("entity.conure.ambient");
    public static final SoundEvent FAIRYWREN_AMBIENT = createSoundEvent("entity.fairywren.ambient");
    public static final SoundEvent PYGMY_FALCON_AMBIENT = createSoundEvent("entity.pygmyfalcon.ambient");
    public static final SoundEvent BARN_OWL_AMBIENT = createSoundEvent("entity.barn_owl.ambient");
    public static final SoundEvent WILD_DUCK_AMBIENT = createSoundEvent("entity.wild_duck.ambient");
    public static final SoundEvent ROLLER_AMBIENT = createSoundEvent("entity.roller.ambient");
    public static final SoundEvent CHICKADEE_AMBIENT = createSoundEvent("entity.chickadee.ambient");
    public static final SoundEvent CHICKADEE_AMBIENT2 = createSoundEvent("entity.chickadee.ambient2");
    public static final SoundEvent PYGMY_GOOSE_AMBIENT = createSoundEvent("entity.pygmy_goose.ambient");
    public static final SoundEvent SWALLOW_AMBIENT = createSoundEvent("entity.swallow.ambient");
    public static final SoundEvent IBIS_AMBIENT = createSoundEvent("entity.ibis.ambient");
    public static final SoundEvent WOOD_DUCK_AMBIENT1 = createSoundEvent("entity.wood_duck.ambient1");
    public static final SoundEvent WOOD_DUCK_AMBIENT2 = createSoundEvent("entity.wood_duck.ambient2");
    public static final SoundEvent PEAFOWL_AMBIENT = createSoundEvent("entity.peafowl.ambient");
    public static final SoundEvent SPARROW_AMBIENT = createSoundEvent("entity.sparrow.ambient");
    public static final SoundEvent LAUGHINGTHRUSH_AMBIENT = createSoundEvent("entity.laughingthrush.ambient");
    public static final SoundEvent BUSHTIT_AMBIENT = createSoundEvent("entity.bushtit.ambient");
    public static final SoundEvent EAGLEOWL_AMBIENT = createSoundEvent("entity.eagleowl.ambient");
    public static final SoundEvent ROBIN_AMBIENT = createSoundEvent("entity.robin.ambient");
    public static final SoundEvent MAGPIE_AMBIENT = createSoundEvent("entity.magpie.ambient");

    private static SoundEvent createSoundEvent(final String soundName) {
        final ResourceLocation soundID = new ResourceLocation(Creatures.MODID, soundName);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }
}
