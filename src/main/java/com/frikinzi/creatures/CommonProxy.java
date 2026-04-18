package com.frikinzi.creatures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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

    public void openFieldGuideGUI() {

    }

    public void openBinScreen(ItemStack bin, int slot, BlockPos pos) {}
}