package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.ai.CormorantAttackablesSensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CreaturesSensorTypes {
    public static final DeferredRegister<SensorType<?>> SENSOR_TYPES =
            DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, Creatures.MODID);

    public static final RegistryObject<SensorType<CormorantAttackablesSensor>> CORMORANT_ATTACKABLES =
            SENSOR_TYPES.register("cormorant_attackables", () -> new SensorType<>(CormorantAttackablesSensor::new));
}

