package com.frikinzi.creatures.client;

import com.frikinzi.creatures.CommonProxy;
import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.gui.GUICreatures;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Creatures.MODID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
    private LivingEntity referencedMob = null;
    public LivingEntity getReferencedMob() {
        return referencedMob;
    }

    public void setReferencedMob(LivingEntity mob) {
        referencedMob = mob;
    }

    @OnlyIn(Dist.CLIENT)
    public void openCreaturesGui() {
        Minecraft.getInstance().setScreen(new GUICreatures());
    }
}
