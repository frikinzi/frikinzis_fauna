package com.frikinzi.creatures.player;

import com.frikinzi.creatures.Creatures;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = Creatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(FieldGuideCapability.class);
    }

    @Mod.EventBusSubscriber(modid = Creatures.MODID)
    public class CapabilityEvents {

        @SubscribeEvent
        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            Player player = event.getEntity();
            System.out.println("Player logged in: " + player.getName().getString() + " isClient: " + player.level().isClientSide());
            player.getCapability(FieldGuideCapability.CAPABILITY).ifPresent(cap -> {
                System.out.println("Sending sync with: " + cap.getAll());
                NetworkHandler.CHANNEL.send(
                        PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                        new SyncAllDiscoveriesPacket(cap.getAll()));
            });
        }

        @SubscribeEvent
        public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (!(event.getObject() instanceof Player)) return;

            event.addCapability(new ResourceLocation(Creatures.MODID, "field_guide"),
                    new ICapabilitySerializable<CompoundTag>() {
                        private final FieldGuideCapability instance = new FieldGuideCapability();
                        private final LazyOptional<FieldGuideCapability> lazyOptional = LazyOptional.of(() -> instance);

                        @Override
                        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                            return FieldGuideCapability.CAPABILITY.orEmpty(cap, lazyOptional);
                        }

                        @Override
                        public CompoundTag serializeNBT() {
                            CompoundTag tag = new CompoundTag();
                            instance.serializeNBT(tag);
                            return tag;
                        }

                        @Override
                        public void deserializeNBT(CompoundTag tag) {
                            instance.deserializeNBT(tag);
                        }
                    }
                    );
        }

        @SubscribeEvent
        public static void onPlayerClone(PlayerEvent.Clone event) {
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(FieldGuideCapability.CAPABILITY).ifPresent(oldCap ->
                    event.getEntity().getCapability(FieldGuideCapability.CAPABILITY).ifPresent(newCap ->
                            newCap.copyFrom(oldCap)));
            event.getOriginal().invalidateCaps();
        }
    }
}