package com.frikinzi.creatures.player;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class SyncAllDiscoveriesPacket {
    private final Set<String> allDiscovered;

    public SyncAllDiscoveriesPacket(Set<String> allDiscovered) {
        this.allDiscovered = allDiscovered;
    }

    public SyncAllDiscoveriesPacket(FriendlyByteBuf buf) {
        int size = buf.readInt();
        allDiscovered = new HashSet<>();
        for (int i = 0; i < size; i++) {
            allDiscovered.add(buf.readUtf());
        }
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(allDiscovered.size());
        allDiscovered.forEach(buf::writeUtf);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            net.minecraftforge.fml.DistExecutor.unsafeRunWhenOn(
                    net.minecraftforge.api.distmarker.Dist.CLIENT,
                    () -> () -> {
                        net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
                        if (mc.player != null) {
                            mc.player.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                                cap.replaceAll(allDiscovered);
                            });
                        }
                    });
        });
        ctx.get().setPacketHandled(true);
    }
}