package com.frikinzi.creatures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {
    public LivingEntity getReferencedMob() {
        return null;
    }

    public void setReferencedMob(LivingEntity mob) {
    }

    public void openCreaturesGui() {

    }
}
