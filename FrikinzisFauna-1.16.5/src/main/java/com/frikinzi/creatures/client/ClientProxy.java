package com.frikinzi.creatures.client;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.gui.GUICreatures;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Creatures.MODID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
    private LivingEntity referencedMob = null;

    @Override
    public void openCreaturesGUI(ItemStack book) {
        Minecraft.getInstance().setScreen(new GUICreatures());
    }

    public LivingEntity getReferencedMob() {
        return referencedMob;
    }

    public void setReferencedMob(LivingEntity base) {
        referencedMob = base;
    }
}
