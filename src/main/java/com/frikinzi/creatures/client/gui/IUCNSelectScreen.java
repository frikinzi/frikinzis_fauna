package com.frikinzi.creatures.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class IUCNSelectScreen extends Screen {
    private final Screen parent;
    private static final ResourceLocation BOOK_TEXTURE =
            new ResourceLocation("creatures:textures/gui/creatures/book.png");
    private static final int BOOK_W = 390, BOOK_H = 245;

    // IUCN status index -> display name + color
    public static final String[] IUCN_NAMES = {
        "Least Concern", "Near Threatened", "Vulnerable",
        "Endangered", "Critically Endangered", "Extinct in the Wild",
        "Extinct", "Data Deficient"
    };
    public static final int[] IUCN_COLORS = {
        0x00AA00, 0xFFAA00, 0xFFAA00,
        0xFF5555, 0xAA0000, 0xAA00AA,
        0x000000, 0xAAAAAA
    };

    public IUCNSelectScreen(Screen parent) {
        super(Component.translatable("creatures.fieldgui.iucn"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        int bookX = (this.width - BOOK_W) / 2;
        int bookY = (this.height - BOOK_H) / 2;

        // Two columns of buttons, 4 per column
        for (int i = 0; i < IUCN_NAMES.length; i++) {
            final int status = i;
            int col = i / 4;
            int row = i % 4;
            int bx = bookX + 40 + col * 165;
            int by = bookY + 50 + row * 38;

            this.addRenderableWidget(
                Button.builder(Component.literal(IUCN_NAMES[i]),
                    b -> Minecraft.getInstance().setScreen(
                            new IUCNSpeciesScreen(status, this)))
                .pos(bx, by).size(140, 28).build()
            );
        }

        this.addRenderableWidget(
            Button.builder(Component.literal("◀ " +
                    Component.translatable("creatures.fieldgui.back").getString()),
                b -> Minecraft.getInstance().setScreen(parent))
            .pos(bookX + 10, bookY + BOOK_H - 35).size(60, 20).build()
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        int bookX = (this.width - BOOK_W) / 2;
        int bookY = (this.height - BOOK_H) / 2;

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        graphics.blit(BOOK_TEXTURE, bookX, bookY, 0, 0, BOOK_W, BOOK_H, BOOK_W, BOOK_W);

        String title = Component.translatable("creatures.fieldgui.iucn").getString();
        graphics.drawString(font, title,
                bookX + BOOK_W / 2 - font.width(title) / 2,
                bookY + 18, 0x3D2B1F, false);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}