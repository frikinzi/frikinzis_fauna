package com.frikinzi.creatures.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class RegionSelectScreen extends Screen {
    private final Screen parent;
    private static final ResourceLocation BOOK_TEXTURE =
            new ResourceLocation("creatures:textures/gui/creatures/book.png");

    public RegionSelectScreen(Screen parent) {
        super(Component.literal("Regions"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;

        int btnW = 140, btnH = 20, gap = 5;
        int col1X = bookX + 40;
        int col2X = bookX + 210;
        int startY = bookY + 40;

        Region[] regions = Region.values();
        for (int i = 0; i < regions.length; i++) {
            Region region = regions[i];
            int x = (i % 2 == 0) ? col1X : col2X;
            int y = startY + (i / 2) * (btnH + gap);
            this.addRenderableWidget(Button.builder(
                            region.getDisplayName(), b ->
                            Minecraft.getInstance().setScreen(new RegionSpeciesScreen(region, this)))
                    .pos(x, y).size(btnW, btnH).build());
        }

        this.addRenderableWidget(Button.builder(Component.literal("◀ Back"),
                b -> Minecraft.getInstance().setScreen(parent))
                .pos(bookX + 10, bookY + bookH - 35).size(50, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        graphics.blit(BOOK_TEXTURE, bookX, bookY, 0, 0, bookW, bookH, bookW, bookW);
        String title = "View by Region";
        graphics.drawString(font, title, bookX + bookW / 2 - font.width(title) / 2,
                bookY + 20, 0x3D2B1F, false);
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}