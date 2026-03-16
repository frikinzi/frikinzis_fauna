package com.frikinzi.creatures.registry;
import com.frikinzi.creatures.Creatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Services;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.function.Supplier;


public class CreaturesSound {
    public static final DeferredRegister<SoundEvent> REGISTRAR = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Creatures.MODID);
    public static RegistryObject<SoundEvent> LOVEBIRD_AMBIENT = registerSound("entity.lovebird.ambient");
    public static RegistryObject<SoundEvent> SPOONBILL_AMBIENT = registerSound("entity.creatures_spoonbill.ambient");
    public static RegistryObject<SoundEvent> KAKAPO_AMBIENT = registerSound("entity.kakapo.ambient");
    public static RegistryObject<SoundEvent> KAKAPO_HURT = registerSound("entity.kakapo.hurt");
    public static RegistryObject<SoundEvent> MANDARIN_DUCK_AMBIENT = registerSound("entity.mandarin_duck.ambient");
    public static RegistryObject<SoundEvent> RAVEN_AMBIENT = registerSound("entity.raven.ambient");
    public static RegistryObject<SoundEvent> DOVE_AMBIENT = registerSound("entity.dove.ambient");
    public static RegistryObject<SoundEvent> GOLDEN_EAGLE_AMBIENT = registerSound("entity.golden_eagle.ambient");
    public static RegistryObject<SoundEvent> STELLERS_SEA_EAGLE_AMBIENT = registerSound("entity.stellers_sea_eagle.ambient");
    public static RegistryObject<SoundEvent> GYRFALCON_AMBIENT = registerSound("entity.gyrfalcon.ambient");
    public static RegistryObject<SoundEvent> RED_KITE_AMBIENT = registerSound("entity.red_kite.ambient");
    public static RegistryObject<SoundEvent> LORIKEET_AMBIENT = registerSound("entity.lorikeet.ambient");
    public static RegistryObject<SoundEvent> LORIKEET_AMBIENT2 = registerSound("entity.lorikeet.ambient2");
    public static RegistryObject<SoundEvent> CONURE_AMBIENT = registerSound("entity.conure.ambient");
    public static RegistryObject<SoundEvent> FAIRYWREN_AMBIENT = registerSound("entity.fairywren.ambient");
    public static RegistryObject<SoundEvent> PYGMY_FALCON_AMBIENT = registerSound("entity.pygmyfalcon.ambient");
    public static RegistryObject<SoundEvent> BARN_OWL_AMBIENT = registerSound("entity.barn_owl.ambient");
    public static RegistryObject<SoundEvent> WILD_DUCK_AMBIENT = registerSound("entity.wild_duck.ambient");
    public static RegistryObject<SoundEvent> MALLARD_AMBIENT = registerSound("entity.wild_duck.mallard_ambient");
    public static RegistryObject<SoundEvent> ROLLER_AMBIENT = registerSound("entity.roller.ambient");
    public static RegistryObject<SoundEvent> CHICKADEE_AMBIENT = registerSound("entity.chickadee.ambient");
    public static RegistryObject<SoundEvent> CHICKADEE_AMBIENT2 = registerSound("entity.chickadee.ambient2");
    public static RegistryObject<SoundEvent> PYGMY_GOOSE_AMBIENT = registerSound("entity.pygmy_goose.ambient");
    public static RegistryObject<SoundEvent> SWALLOW_AMBIENT = registerSound("entity.swallow.ambient");
    public static RegistryObject<SoundEvent> IBIS_AMBIENT = registerSound("entity.ibis.ambient");
    public static RegistryObject<SoundEvent> WOOD_DUCK_AMBIENT1 = registerSound("entity.wood_duck.ambient1");
    public static RegistryObject<SoundEvent> WOOD_DUCK_AMBIENT2 = registerSound("entity.wood_duck.ambient2");
    public static RegistryObject<SoundEvent> PEAFOWL_AMBIENT = registerSound("entity.peafowl.ambient");
    public static RegistryObject<SoundEvent> SPARROW_AMBIENT = registerSound("entity.sparrow.ambient");
    public static RegistryObject<SoundEvent> LAUGHINGTHRUSH_AMBIENT = registerSound("entity.laughingthrush.ambient");
    public static RegistryObject<SoundEvent> BUSHTIT_AMBIENT = registerSound("entity.bushtit.ambient");
    public static RegistryObject<SoundEvent> EAGLEOWL_AMBIENT = registerSound("entity.eagleowl.ambient");
    public static RegistryObject<SoundEvent> ROBIN_AMBIENT = registerSound("entity.robin.ambient");
    public static RegistryObject<SoundEvent> MAGPIE_AMBIENT = registerSound("entity.magpie.ambient");
    public static RegistryObject<SoundEvent> GOOSE_AMBIENT = registerSound("entity.goose.ambient");
    public static RegistryObject<SoundEvent> GOOSE_FLY = registerSound("entity.goose.fly");
    public static RegistryObject<SoundEvent> OSPREY_AMBIENT = registerSound("entity.osprey.ambient");
    public static RegistryObject<SoundEvent> KINGFISHER_AMBIENT = registerSound("entity.kingfisher.ambient");
    public static RegistryObject<SoundEvent> PELICAN_AMBIENT = registerSound("entity.pelican.ambient");
    public static RegistryObject<SoundEvent> LAPWING_AMBIENT = registerSound("entity.lapwing.ambient");
    public static RegistryObject<SoundEvent> SKUA_AMBIENT = registerSound("entity.skua.ambient");
    public static RegistryObject<SoundEvent> BUNTING_AMBIENT = registerSound("entity.bunting.ambient");
    public static RegistryObject<SoundEvent> MONAL_AMBIENT = registerSound("entity.monal.ambient");
    public static RegistryObject<SoundEvent> TANAGER_AMBIENT = registerSound("entity.tanager.ambient");
    public static RegistryObject<SoundEvent> FINCH_AMBIENT = registerSound("entity.finch.ambient");
    public static RegistryObject<SoundEvent> CAPERCAILLIE_AMBIENT = registerSound("entity.capercaillie.ambient");
    public static RegistryObject<SoundEvent> PHEASANT_AMBIENT = registerSound("entity.pheasant.ambient");
    public static RegistryObject<SoundEvent> CRESTED_PIGEON = registerSound("entity.dove.crested_pigeon_flying");
    public static RegistryObject<SoundEvent> PIRANHA_AMBIENT = registerSound("entity.piranha.piranha_ambient");
    public static RegistryObject<SoundEvent> EGG_HATCH = registerSound("entity.egg.hatch");
    public static RegistryObject<SoundEvent> MOURNING_DOVE = registerSound("entity.dove.mourning_dove_ambient");
    public static RegistryObject<SoundEvent> STORK_AMBIENT = registerSound("entity.stork.stork_ambient");
    public static RegistryObject<SoundEvent> STORK_HURT = registerSound("entity.stork.stork_hurt");
    public static RegistryObject<SoundEvent> LADY_AMHERST = registerSound("entity.pheasant.lady_amherst_ambient");
    public static RegistryObject<SoundEvent> WHISTLING_DUCK = registerSound("entity.whistlingduck.whistlingduck_ambient");
    public static RegistryObject<SoundEvent> WHITE_FACED = registerSound("entity.whistlingduck.white_faced_ambient");
    public static RegistryObject<SoundEvent> GROUND_HORNBILL = registerSound("entity.groundhornbill.groundhornbill_ambient");
    public static RegistryObject<SoundEvent> SECRETARYBIRD = registerSound("entity.secretarybird.secretarybird_ambient");
    public static RegistryObject<SoundEvent> SHOEBILL = registerSound("entity.shoebill.shoebill_ambient");
    public static RegistryObject<SoundEvent> STARLING = registerSound("entity.starling.starling_ambient");
    public static RegistryObject<SoundEvent> CORMORANT = registerSound("entity.cormorant.cormorant_ambient");
    public static RegistryObject<SoundEvent> PUFFIN = registerSound("entity.puffin.puffin_ambient");
    public static RegistryObject<SoundEvent> PUFFIN_HURT = registerSound("entity.puffin.puffin_hurt");
    public static RegistryObject<SoundEvent> SEAGULL = registerSound("entity.seagull.seagull_ambient");
    public static RegistryObject<SoundEvent> BOOBY = registerSound("entity.booby.booby_ambient");
    public static RegistryObject<SoundEvent> BOOBY_HURT = registerSound("entity.booby.booby_hurt");
    public static RegistryObject<SoundEvent> BANDEDPENGUIN = registerSound("entity.bandedpenguin.bandedpenguin_ambient");
    public static RegistryObject<SoundEvent> RAIL = registerSound("entity.rail.rail_ambient");
    public static RegistryObject<SoundEvent> AVOCET = registerSound("entity.avocet.avocet_ambient");
    public static RegistryObject<SoundEvent> CRESTED_PENGUIN = registerSound("entity.crestedpenguin.crestedpenguin_ambient");
    public static RegistryObject<SoundEvent> YELLOWEYED_PENGUIN = registerSound("entity.yelloweyedpenguin.yelloweyedpenguin_ambient");
    public static RegistryObject<SoundEvent> BRUSHTAILED_PENGUIN = registerSound("entity.brushtailedpenguin.brushtailedpenguin_ambient");
    public static RegistryObject<SoundEvent> LARGE_PENGUIN = registerSound("entity.largepenguin.largepenguin_ambient");
    public static RegistryObject<SoundEvent> FRIGATE_AMBIENT = registerSound("entity.frigate.frigate_ambient");
    public static RegistryObject<SoundEvent> STILT_AMBIENT = registerSound("entity.stilt.stilt_ambient");
    public static RegistryObject<SoundEvent> STILT_HURT = registerSound("entity.stilt.stilt_hurt");
    public static RegistryObject<SoundEvent> LITTLE_PENGUIN = registerSound("entity.littlepenguin.littlepenguin_ambient");
    public static RegistryObject<SoundEvent> MARABOU = registerSound("entity.marabou.marabou_ambient");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return REGISTRAR.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Creatures.MODID, name)));
    }


}
