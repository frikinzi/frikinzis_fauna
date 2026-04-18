package com.frikinzi.creatures.client.gui;

import net.minecraft.network.chat.Component;

public enum Region {
    NORTH_AMERICA("region.creatures.north_america"),
    SOUTH_AMERICA("region.creatures.south_america"),
    ASIA("region.creatures.asia"),
    OCEANIA("region.creatures.oceania"),
    ANTARCTICA("region.creatures.antarctica"),
    EUROPE("region.creatures.europe"),
    AFRICA("region.creatures.africa");

    public final String translationKey;

    Region(String translationKey) {
        this.translationKey = translationKey;
    }

    public Component getDisplayName() {
        return Component.translatable(translationKey);
    }
}