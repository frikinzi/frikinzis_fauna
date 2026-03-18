package com.frikinzi.creatures.player;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AwardXPPacket {
    private final int amount;
    public AwardXPPacket(int amount) { this.amount = amount; }
    public static void encode(AwardXPPacket p, FriendlyByteBuf buf) { buf.writeInt(p.amount); }
    public static AwardXPPacket decode(FriendlyByteBuf buf) { return new AwardXPPacket(buf.readInt()); }
    public static void handle(AwardXPPacket p, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) player.giveExperiencePoints(p.amount);
        });
        ctx.get().setPacketHandled(true);
    }
}
