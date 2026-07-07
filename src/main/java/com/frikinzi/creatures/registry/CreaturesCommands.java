package com.frikinzi.creatures.registry;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.client.gui.FieldGuideGUI;
import com.frikinzi.creatures.player.FieldGuideCapability;
import com.frikinzi.creatures.player.NetworkHandler;
import com.frikinzi.creatures.player.SpeciesEntry;
import com.frikinzi.creatures.player.SyncAllDiscoveriesPacket;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.HashSet;
@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CreaturesCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("discoverall")
                .requires(source -> source.hasPermission(2))
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    player.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                        for (SpeciesEntry species : FieldGuideGUI.ALL_SPECIES) {
                            for (int v = 1; v <= species.totalVariants; v++) {
                                cap.discover(species.entityKey + "_" + v + "_m");
                                cap.discover(species.entityKey + "_" + v + "_f");
                            }
                        }
                        NetworkHandler.CHANNEL.send(
                                PacketDistributor.PLAYER.with(() -> player),
                                new SyncAllDiscoveriesPacket(cap.getAll())
                        );
                    });
                    context.getSource().sendSuccess(
                            () -> Component.literal("Discovered all species!"), false);
                    return 1;
                }).then(Commands.literal("reset")
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            player.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                                cap.replaceAll(new HashSet<>());
                                NetworkHandler.CHANNEL.send(
                                        PacketDistributor.PLAYER.with(() -> player),
                                        new SyncAllDiscoveriesPacket(cap.getAll())
                                );
                            });
                            context.getSource().sendSuccess(
                                    () -> Component.literal("Reset all discoveries!"), false);
                            return 1;
                        }))
        );
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        register(event.getDispatcher());
    }
}
