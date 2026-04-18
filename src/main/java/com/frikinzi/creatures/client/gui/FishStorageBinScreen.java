package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.item.FishStorageBinItem;
import com.frikinzi.creatures.player.NetworkHandler;
import com.frikinzi.creatures.player.ReleaseFishPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FishStorageBinScreen extends Screen {
    private final ItemStack binStack;
    private final int inventorySlot;
    private final BlockPos releasePos;
    private final List<LivingEntity> cachedDummies = new ArrayList<>();

    private static final ResourceLocation BOOK_TEXTURE =
            new ResourceLocation("creatures:textures/gui/creatures/fishing_bin.png");

    private static final int BOOK_W = 390;
    private static final int BOOK_H = 245;

    private static final int COLS       = 3;
    private static final int CELL_W     = 88;  // cell width
    private static final int CELL_H     = 45;  // cell height
    private static final int CELL_PAD   = 3;   // gap between cells
    private static final int GRID_TOP   = 28;  // distance from bookY to first row
    private static final int BTN_H      = 11;  // height of per-cell Release button
    private static final int ENTITY_H   = CELL_H - BTN_H - 2; // px available for the entity

    public FishStorageBinScreen(ItemStack binStack, int inventorySlot, BlockPos releasePos) {
        super(Component.translatable("creatures.message.bin.title"));
        this.binStack     = binStack;
        this.inventorySlot = inventorySlot;
        this.releasePos   = releasePos;
    }

    private int gridX(int bookX) {
        int gridW = COLS * CELL_W + (COLS - 1) * CELL_PAD;
        return bookX + (BOOK_W - gridW) / 2;
    }

    private int cellX(int gridX, int col) { return gridX + col * (CELL_W + CELL_PAD); }
    private int cellY(int bookY,  int row) { return bookY + GRID_TOP + row * (CELL_H + CELL_PAD); }


    @Override
    protected void init() {
        super.init();
        int bookX = (this.width  - BOOK_W) / 2;
        int bookY = (this.height - BOOK_H) / 2;
        int gx    = gridX(bookX);

        ListTag list = FishStorageBinItem.getFishList(binStack);

        for (int i = 0; i < list.size(); i++) {
            final int index = i;
            int cx = cellX(gx, i % COLS);
            int cy = cellY(bookY, i / COLS);
            this.addRenderableWidget(
                    Button.builder(Component.literal(Component.translatable("creatures.fieldgui.release").getString()), b -> releaseFish(index))
                            .pos(cx + 1, cy + CELL_H - BTN_H - 1)
                            .size(CELL_W - 2, BTN_H)
                            .build()
            );
        }

        // Close button
        this.addRenderableWidget(
                Button.builder(Component.literal("X " + Component.translatable("creatures.fieldgui.close").getString()), b -> Minecraft.getInstance().setScreen(null))
                        .pos(bookX + 10, bookY + BOOK_H - 22)
                        .size(60, 16)
                        .build()
        );

        this.addRenderableWidget(
                Button.builder(Component.literal(Component.translatable("creatures.fieldgui.releaseall").getString()), b -> releaseAll())
                        .pos(bookX + BOOK_W - 90, bookY + BOOK_H - 22)
                        .size(80, 16)
                        .build()
        );

        cachedDummies.clear();
        for (int i = 0; i < list.size(); i++) {
            CompoundTag tag = list.getCompound(i);
            try {
                net.minecraft.world.entity.EntityType<?> type =
                        net.minecraft.world.entity.EntityType.by(tag).orElse(null);
                if (type != null) {
                    LivingEntity dummy = (LivingEntity) type.create(Minecraft.getInstance().level);
                    if (dummy instanceof FishBase fish) {
                        fish.setVariant(tag.getInt("Variant"));
                        fish.setSubVariant(tag.getInt("Subvariant"));
                        fish.setHeightMultiplier(tag.getFloat("SizeMultiplier"));
                        fish.setAge(tag.getInt("Age"));
                        fish.setForcedInWater(true);
                    }
                    cachedDummies.add(dummy);
                } else {
                    cachedDummies.add(null);
                }
            } catch (Exception e) {
                cachedDummies.add(null);
            }
        }
    }

    // ── Render ────────────────────────────────────────────────────────────────

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        int bookX = (this.width  - BOOK_W) / 2;
        int bookY = (this.height - BOOK_H) / 2;

        // Book background
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        graphics.blit(BOOK_TEXTURE, bookX, bookY, 0, 0, BOOK_W, BOOK_H, BOOK_W, BOOK_W);

        // Title
        String title = Component.translatable("creatures.fishbingui.title").getString();
        graphics.drawString(font, title,
                bookX + BOOK_W / 2 - font.width(title) / 2, bookY + 10, 0x3D2B1F, false);

        // Count
        String count = FishStorageBinItem.getFishCount(binStack) + "/" + FishStorageBinItem.MAX_CAPACITY;
        graphics.drawString(font, count, bookX + BOOK_W - 44, bookY + 10, 0x3D2B1F, false);

        ListTag list = FishStorageBinItem.getFishList(binStack);
        int gx = gridX(bookX);

        if (list.isEmpty()) {
            String msg = "No fish stored.";
            graphics.drawString(font, msg,
                    bookX + BOOK_W / 2 - font.width(msg) / 2,
                    bookY + BOOK_H / 2, 0x5C4033, false);
        } else {
            for (int i = 0; i < list.size(); i++) {
                int cx = cellX(gx, i % COLS);
                int cy = cellY(bookY, i / COLS);

                // Cell background + border
                //graphics.fill(cx, cy, cx + CELL_W, cy + CELL_H, 0x22000000);
                graphics.renderOutline(cx, cy, CELL_W, CELL_H, 0x555C4033);

                //graphics.drawString(font, String.valueOf(i + 1), cx + 3, cy + 2, 0x5C4033, false);

                if (i < cachedDummies.size() && cachedDummies.get(i) != null) {
                    renderEntityInCell(graphics, cachedDummies.get(i), cx, cy);
                }
            }
        }

        super.render(graphics, mouseX, mouseY, partialTick); // draws buttons on top
    }
//
//    private void renderEntityInCell(GuiGraphics graphics, LivingEntity dummy, int cellX, int cellY) {
//        double gs = Minecraft.getInstance().getWindow().getGuiScale();
//        int winH  = Minecraft.getInstance().getWindow().getGuiScaledHeight();
//        int sx = (int) ((cellX + 2)             * gs);
//        int sy = (int) ((winH - (cellY + ENTITY_H)) * gs);
//        int sw = (int) ((CELL_W - 4)            * gs);
//        int sh = (int) (ENTITY_H               * gs);
//        RenderSystem.enableScissor(sx, sy, sw, sh);
//
//        org.joml.Quaternionf rot = new org.joml.Quaternionf()
//                .rotateZ((float) Math.PI)
//                .rotateY((float) Math.toRadians(160));
//        int scale = 12;
//        if (dummy instanceof FishBase fish) {
//            rot   = fish.getRotforGUI();
//            scale = Math.min(fish.getScaleforGUI(), 13);
//        }
//
//        InventoryScreen.renderEntityInInventory(
//                graphics,
//                cellX + CELL_W / 2,
//                cellY + ENTITY_H - 1,
//                scale,
//                rot, null, dummy);
//
//        RenderSystem.disableScissor();
//    }

    private void renderEntityInCell(GuiGraphics graphics, LivingEntity dummy, int cellX, int cellY) {
        double gs = Minecraft.getInstance().getWindow().getGuiScale();
        int winH  = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        int sx = (int) ((cellX + 2)              * gs);
        int sy = (int) ((winH - (cellY + ENTITY_H)) * gs);
        int sw = (int) ((CELL_W - 4)             * gs);
        int sh = (int) (ENTITY_H                * gs);
        RenderSystem.enableScissor(sx, sy, sw, sh);

        dummy.yBodyRotO = dummy.yBodyRot;
        dummy.yHeadRotO = dummy.yHeadRot;
        dummy.yRotO     = dummy.getYRot();
        dummy.xRotO     = dummy.getXRot();

        org.joml.Quaternionf rot = new org.joml.Quaternionf()
                .rotateZ((float) Math.PI)
                .rotateY((float) Math.toRadians(160));

        float targetPixels = ENTITY_H * 0.8f;
        int scale = Math.max(5, (int)(targetPixels / dummy.getBbHeight()));

        if (dummy instanceof FishBase fish) {
            rot = fish.getRotforGUI();
            int entityScale = fish.getScaleforGUI();
            scale = Math.max(8, Math.min(entityScale, (int) targetPixels));
        }

        InventoryScreen.renderEntityInInventory(
                graphics,
                cellX + CELL_W / 2,
                cellY + ENTITY_H - 1,
                scale,
                rot, null, dummy);

        RenderSystem.disableScissor();
    }

    private void releaseFish(int index) {
        NetworkHandler.CHANNEL.sendToServer(new ReleaseFishPacket(inventorySlot, index, releasePos));
        Minecraft.getInstance().setScreen(null);
    }

    private void releaseAll() {
        NetworkHandler.CHANNEL.sendToServer(new ReleaseFishPacket(inventorySlot, -1, releasePos));
        Minecraft.getInstance().setScreen(null);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}