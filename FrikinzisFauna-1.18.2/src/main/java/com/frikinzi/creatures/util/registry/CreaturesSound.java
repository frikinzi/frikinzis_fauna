package com.frikinzi.creatures.util.registry;

import com.frikinzi.creatures.Creatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.lang.reflect.Field;


@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreaturesSound {
    //public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Creatures.MODID);
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
    public static final SoundEvent GOOSE_AMBIENT = createSoundEvent("entity.goose.ambient");
    public static final SoundEvent GOOSE_FLY = createSoundEvent("entity.goose.fly");
    public static final SoundEvent OSPREY_AMBIENT = createSoundEvent("entity.osprey.ambient");
    public static final SoundEvent KINGFISHER_AMBIENT = createSoundEvent("entity.kingfisher.ambient");
    public static final SoundEvent PELICAN_AMBIENT = createSoundEvent("entity.pelican.ambient");
    public static final SoundEvent LAPWING_AMBIENT = createSoundEvent("entity.lapwing.ambient");
    public static final SoundEvent SKUA_AMBIENT = createSoundEvent("entity.skua.ambient");
    public static final SoundEvent BUNTING_AMBIENT = createSoundEvent("entity.bunting.ambient");
    public static final SoundEvent MONAL_AMBIENT = createSoundEvent("entity.monal.ambient");
    public static final SoundEvent TANAGER_AMBIENT = createSoundEvent("entity.tanager.ambient");
    public static final SoundEvent FINCH_AMBIENT = createSoundEvent("entity.finch.ambient");
    public static final SoundEvent CAPERCAILLIE_AMBIENT = createSoundEvent("entity.capercaillie.ambient");
    public static final SoundEvent PHEASANT_AMBIENT = createSoundEvent("entity.pheasant.ambient");
    public static final SoundEvent CRESTED_PIGEON = createSoundEvent("entity.dove.crested_pigeon_flying");
    public static final SoundEvent PIRANHA_AMBIENT = createSoundEvent("entity.piranha.piranha_ambient");
    public static final SoundEvent EGG_HATCH = createSoundEvent("entity.egg.hatch");
    public static final SoundEvent MOURNING_DOVE = createSoundEvent("entity.dove.mourning_dove_ambient");
    public static final SoundEvent STORK_AMBIENT = createSoundEvent("entity.stork.stork_ambient");
    public static final SoundEvent STORK_HURT = createSoundEvent("entity.stork.stork_hurt");

    private static SoundEvent createSoundEvent(final String soundName) {
        final ResourceLocation soundID = new ResourceLocation(Creatures.MODID, soundName);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }

    @SubscribeEvent
    public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
        try {
            for (Field f : CreaturesSound.class.getFields()) {
                Object obj = f.get(null);
                if (obj instanceof SoundEvent) {
                    event.getRegistry().register((SoundEvent) obj);
                } else if (obj instanceof SoundEvent[]) {
                    for (SoundEvent soundEvent : (SoundEvent[]) obj) {
                        event.getRegistry().register(soundEvent);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
