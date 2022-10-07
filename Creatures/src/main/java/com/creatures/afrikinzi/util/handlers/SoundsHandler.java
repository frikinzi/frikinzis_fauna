package com.creatures.afrikinzi.util.handlers;


import com.creatures.afrikinzi.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
    public static SoundEvent WILD_DUCK_AMBIENT;
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
    public static SoundEvent BARN_OWL_AMBIENT;
    public static SoundEvent PYGMY_FALCON_AMBIENT;
    public static SoundEvent ROLLER_AMBIENT;
    public static SoundEvent CHICKADEE_AMBIENT;
    public static SoundEvent CHICKADEE_AMBIENT2;
    public static SoundEvent SWALLOW_AMBIENT;
    public static SoundEvent PYGMY_GOOSE_AMBIENT;
    public static SoundEvent IBIS_AMBIENT;
    public static SoundEvent WOOD_DUCK_AMBIENT1;
    public static SoundEvent WOOD_DUCK_AMBIENT2;
    public static SoundEvent PEAFOWL_AMBIENT;
    public static SoundEvent SPARROW_AMBIENT;
    public static SoundEvent BUSHTIT_AMBIENT;
    public static SoundEvent LAUGHINGTHRUSH_AMBIENT;
    public static SoundEvent EAGLEOWL_AMBIENT;
    public static SoundEvent ROBIN_AMBIENT;
    public static SoundEvent MAGPIE_AMBIENT;
    public static SoundEvent GOOSE_AMBIENT;
    public static SoundEvent GOOSE_AMBIENT_FLY;
    public static SoundEvent OSPREY_AMBIENT;
    public static SoundEvent KINGFISHER_AMBIENT;
    public static SoundEvent PELICAN_AMBIENT;
    public static SoundEvent LAPWING_AMBIENT;
    public static SoundEvent SKUA_AMBIENT;
    public static SoundEvent HATCH;

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
        WILD_DUCK_AMBIENT = registerSound("entity.wild_duck.wild_duck_ambient");
        BARN_OWL_AMBIENT = registerSound("entity.barn_owl.barn_owl_ambient");
        PYGMY_FALCON_AMBIENT = registerSound("entity.pygmyfalcon.pygmy_falcon_ambient");
        ROLLER_AMBIENT = registerSound("entity.roller.roller_ambient");
        CHICKADEE_AMBIENT = registerSound("entity.chickadee.chickadee_ambient");
        CHICKADEE_AMBIENT2 = registerSound("entity.chickadee.chickadee_ambient2");
        SWALLOW_AMBIENT = registerSound("entity.swallow.swallow_ambient");
        PYGMY_GOOSE_AMBIENT = registerSound("entity.pygmygoose.pygmy_goose_ambient");
        IBIS_AMBIENT = registerSound("entity.ibis.ibis_ambient");
        WOOD_DUCK_AMBIENT1 = registerSound("entity.wood_duck.wood_duck_ambient1");
        WOOD_DUCK_AMBIENT2 = registerSound("entity.wood_duck.wood_duck_ambient2");
        PEAFOWL_AMBIENT = registerSound("entity.peafowl.peafowl_ambient");
        SPARROW_AMBIENT = registerSound("entity.sparrow.sparrow_ambient");
        BUSHTIT_AMBIENT = registerSound("entity.bushtit.bushtit_ambient");
        LAUGHINGTHRUSH_AMBIENT = registerSound("entity.laughingthrush.laughingthrush_ambient");
        EAGLEOWL_AMBIENT = registerSound("entity.eagleowl.eagleowl_ambient");
        MAGPIE_AMBIENT = registerSound("entity.magpie.magpie_ambient");
        ROBIN_AMBIENT = registerSound("entity.robin.robin_ambient");
        GOOSE_AMBIENT = registerSound("entity.goose.goose_ambient");
        GOOSE_AMBIENT_FLY = registerSound("entity.goose.goose_ambient_fly");
        OSPREY_AMBIENT = registerSound("entity.osprey.osprey_ambient");
        KINGFISHER_AMBIENT = registerSound("entity.kingfisher.kingfisher_ambient");
        PELICAN_AMBIENT = registerSound("entity.pelican.pelican_ambient");
        LAPWING_AMBIENT = registerSound("entity.lapwing.lapwing_ambient");
        SKUA_AMBIENT = registerSound("entity.skua.skua_ambient");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
