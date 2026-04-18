package com.frikinzi.creatures.player;

import net.minecraft.network.FriendlyByteBuf;
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
            net.minecraftforge.fml.DistExecutor.unsafeRunWhenOn(
                    net.minecraftforge.api.distmarker.Dist.CLIENT,
                    () -> () -> {
                        net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
                        if (mc.player != null) {
                            mc.player.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                                cap.discover(key);
                            });
                            mc.player.playSound(
                                    net.minecraft.sounds.SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                        }
                    });
        });
        ctx.get().setPacketHandled(true);
    }
}