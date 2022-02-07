package com.creatures.afrikinzi.util.handlers;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.gui.GUICreatures;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GUIHandler implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_CREATURES) {
            return new GUICreatures();
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_CREATURES) {
            return new GUICreatures();
        }
        return null;
    }
}
