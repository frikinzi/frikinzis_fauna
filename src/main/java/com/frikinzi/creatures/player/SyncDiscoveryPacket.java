package com.frikinzi.creatures.player;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncDiscoveryPacket {
    private final String key;

    public SyncDiscoveryPacket(String key) {
        this.key = key;
    }

    public SyncDiscoveryPacket(FriendlyByteBuf buf) {
        this.key = buf.readUtf();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(key);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                player.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                    cap.discover(key);
                    System.out.println("CLIENT received discovery: " + key);
                    System.out.println("CLIENT discovered set: " + cap.getAll());
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }
}