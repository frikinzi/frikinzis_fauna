package com.frikinzi.creatures.player;

import com.frikinzi.creatures.Creatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Creatures.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int id = 0;

    public static void register() {
        CHANNEL.registerMessage(id++, SyncDiscoveryPacket.class,
                SyncDiscoveryPacket::encode,
                SyncDiscoveryPacket::new,
                SyncDiscoveryPacket::handle);

        CHANNEL.registerMessage(id++, SyncAllDiscoveriesPacket.class,
                SyncAllDiscoveriesPacket::encode,
                SyncAllDiscoveriesPacket::new,
                SyncAllDiscoveriesPacket::handle);
    }
}