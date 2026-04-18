package com.frikinzi.creatures.player;

import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.item.FishStorageBinItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ReleaseFishPacket {
    private final int slot;
    private final int index;
    private final BlockPos releasePos;

    public ReleaseFishPacket(int slot, int index, BlockPos releasePos) {
        this.slot = slot;
        this.index = index;
        this.releasePos = releasePos;
    }

    public ReleaseFishPacket(FriendlyByteBuf buf) {
        this.slot = buf.readInt();
        this.index = buf.readInt();
        this.releasePos = buf.readBlockPos();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(slot);
        buf.writeInt(index);
        buf.writeBlockPos(releasePos);
    }
    private void spawnFish(CompoundTag tag, ServerPlayer player, ServerLevel level) {
        Entity entity = EntityType.loadEntityRecursive(tag, level, e -> e);
        if (entity != null) {
            entity.absMoveTo(releasePos.getX() + 0.5, releasePos.getY(),
                    releasePos.getZ() + 0.5, 0, 0);
            entity.setDeltaMovement(0, 0, 0);
            entity.hurtMarked = true;
            if (entity instanceof FishBase) {
                ((FishBase) entity).setPersistenceRequired();
            }
            level.addFreshEntity(entity);
        }
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            ItemStack bin = player.getInventory().getItem(slot);
            if (!(bin.getItem() instanceof FishStorageBinItem)) return;

            ServerLevel level = (ServerLevel) player.level();
            ListTag list = FishStorageBinItem.getFishList(bin);

            if (index == -1) {
                // Release all
                for (int i = 0; i < list.size(); i++) {
                    spawnFish(list.getCompound(i), player, level);
                }
                bin.getOrCreateTag().remove("StoredFish");
            } else if (index >= 0 && index < list.size()) {
                spawnFish(list.getCompound(index), player, level);
                //release one
                list.remove(index);
                bin.getOrCreateTag().put("StoredFish", list);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}