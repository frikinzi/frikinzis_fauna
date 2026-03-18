package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.entity.Region;
import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.player.FieldGuideCapability;
import com.frikinzi.creatures.player.SpeciesEntry;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Quaternionf;

import java.util.*;

public class RegionSpeciesScreen extends Screen {
    private final Region region;
    private final Screen parent;
    private final List<int[]> entries; // [speciesIndex, variant]
    private int currentPage = 0;
    private static final ResourceLocation BOOK_TEXTURE =
            new ResourceLocation("creatures:textures/gui/creatures/book.png");
    private final Map<Integer, Integer> cachedGenders = new HashMap<>();
    private final Map<Integer, Integer> cachedSubVariants = new HashMap<>();

    public RegionSpeciesScreen(Region region, Screen parent) {
        super(region.getDisplayName());
        this.region = region;
        this.parent = parent;

        // Build flat list of [speciesIndex, variant] for this region
        this.entries = new ArrayList<>();
        List<SpeciesEntry> allSpecies = FieldGuideGUI.ALL_SPECIES;
        for (int s = 0; s < allSpecies.size(); s++) {
            SpeciesEntry species = allSpecies.get(s);
            for (int v = 1; v <= species.totalVariants; v++) {
                List<Region> variantRegions = species.regions.getOrDefault(v, List.of());
                if (variantRegions.contains(region)) {
                    entries.add(new int[]{s, v});
                }
            }
        }
    }

    private int getCellX(int i, int leftPageX, int rightPageX, int cellSize, int colsPerPage) {
        int globalCol = i % (colsPerPage * 2);
        boolean isRight = globalCol >= colsPerPage;
        int col = globalCol % colsPerPage;
        return (isRight ? rightPageX : leftPageX) + col * cellSize;
    }

    private int getCellY(int i, int pageY, int cellSize, int colsPerPage, int visibleRows) {
        int itemsPerPage = colsPerPage * 2 * visibleRows;
        int indexOnPage = i % itemsPerPage;
        int row = indexOnPage / (colsPerPage * 2);
        return pageY + row * cellSize;
    }

    private int getTotalPages(int colsPerPage, int visibleRows) {
        int itemsPerPage = colsPerPage * 2 * visibleRows;
        return Math.max(1, (int) Math.ceil((double) entries.size() / itemsPerPage));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        graphics.blit(BOOK_TEXTURE, bookX, bookY, 0, 0, bookW, bookH, bookW, bookW);

        int cellSize = 40, colsPerPage = 3;
        int leftPageX = bookX + 55, rightPageX = bookX + 200;
        int pageY = bookY + 35;
        int pageH = bookH - 65;
        int visibleRows = pageH / cellSize;
        int itemsPerPage = colsPerPage * 2 * visibleRows;

        FieldGuideCapability cap = Minecraft.getInstance().player
                .getCapability(FieldGuideCapability.CAPABILITY).orElse(null);

        Component hoveredTooltip = null;
        int tooltipX = 0, tooltipY = 0;

        int startIdx = currentPage * itemsPerPage;
        int endIdx = Math.min(startIdx + itemsPerPage, entries.size());

        for (int idx = startIdx; idx < endIdx; idx++) {
            int posOnPage = idx - startIdx;
            int[] entry = entries.get(idx);
            int speciesIdx = entry[0];
            int variant = entry[1];
            SpeciesEntry species = FieldGuideGUI.ALL_SPECIES.get(speciesIdx);

            int x = getCellX(posOnPage, leftPageX, rightPageX, cellSize, colsPerPage);
            int y = getCellY(posOnPage, pageY, cellSize, colsPerPage, visibleRows);

            boolean discovered = cap != null && cap.getDiscoveredVariants(species.entityKey).contains(variant);

            if (discovered) {
                LivingEntity dummy = (LivingEntity) species.entityType.get().create(Minecraft.getInstance().level);

                if (dummy != null) {
                    float h = dummy.getBbHeight();
                    int scale = (int)(25f / h);
                    Quaternionf rot = new Quaternionf().rotateZ((float)Math.PI).rotateY((float)Math.toRadians(140));
                    int cacheKey = idx; // stable per entry
                    if (dummy instanceof CreaturesBirdEntity bird) {
                        bird.setVariant(variant);
                        bird.setGender(cachedGenders.computeIfAbsent(cacheKey, k -> new Random().nextInt(2)));
                        bird.setSubVariant(cachedSubVariants.computeIfAbsent(cacheKey, k -> bird.getSubVariantBasedOnVariant(variant)));
                        bird.setOnGround(true);
                        rot = bird.getRotforGUI();
                        scale = bird.getScaleforGUI();
                    }
                    if (dummy instanceof FishBase fish) {
                        fish.setVariant(variant);
                        fish.setGender(cachedGenders.computeIfAbsent(cacheKey, k -> new Random().nextInt(2)));
                        fish.setSubVariant(cachedSubVariants.computeIfAbsent(cacheKey, k -> fish.getSubVariantBasedOnVariant(variant)));
                        fish.setForcedInWater(true);
                        rot = fish.getRotforGUI();
                        scale = fish.getScaleforGUI();
                    }
                    if (dummy instanceof AbstractCrabBase crab) {
                        crab.setVariant(variant);
                        crab.setGender(cachedGenders.computeIfAbsent(cacheKey, k -> new Random().nextInt(2)));
                        rot = crab.getRotforGUI();
                        scale = crab.getScaleforGUI();
                    }

                    InventoryScreen.renderEntityInInventory(graphics, x + cellSize / 2, y + cellSize - 5, scale, rot, null, dummy);

                    if (mouseX >= x && mouseX <= x + cellSize && mouseY >= y && mouseY <= y + cellSize) {
                        String name = species.getSpeciesName(variant);
                        if (name.isEmpty() || name.equals("Unknown")) name = species.displayName.getString();
                        hoveredTooltip = Component.literal(name);
                        tooltipX = mouseX; tooltipY = mouseY;
                    }
                }
            } else {
                graphics.fill(x, y, x + cellSize, y + cellSize, 0xBB888888);
                graphics.drawString(font, "?", x + 12, y + 11, 0xFFFFFF, true);
            }
        }

        if (entries.isEmpty()) {
            String msg = "No species found for " + region.getDisplayName();
            graphics.drawString(font, msg, bookX + bookW / 2 - font.width(msg) / 2,
                    bookY + bookH / 2, 0x3D2B1F, false);
        }

        if (hoveredTooltip != null) {
            graphics.renderTooltip(font, hoveredTooltip, tooltipX, tooltipY);
        }

        int totalPages = getTotalPages(colsPerPage, visibleRows);
        graphics.drawString(font, (currentPage + 1) + " / " + totalPages,
                bookX + bookW / 2 - 10, bookY + bookH - 18, 0x3D2B1F, false);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void init() {
        super.init();
        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;

        this.addRenderableWidget(Button.builder(Component.literal("◀ Back"),
                b -> Minecraft.getInstance().setScreen(parent))
                .pos(bookX + 10, bookY + bookH - 35).size(50, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("◀"), b -> {
            if (currentPage > 0) currentPage--;
        }).pos(bookX + 70, bookY + bookH - 35).size(20, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("▶"), b -> {
            int cellSize = 40, colsPerPage = 3, pageH = bookH - 65;
            int visibleRows = pageH / cellSize;
            if (currentPage < getTotalPages(colsPerPage, visibleRows) - 1) currentPage++;
        }).pos(bookX + bookW - 30, bookY + bookH - 35).size(20, 20).build());
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int cellSize = 40, colsPerPage = 3, pageH = 245 - 65;
        int visibleRows = pageH / cellSize;
        int totalPages = getTotalPages(colsPerPage, visibleRows);
        if (delta < 0 && currentPage < totalPages - 1) currentPage++;
        else if (delta > 0 && currentPage > 0) currentPage--;
        return true;
    }

    @Override
    public boolean isPauseScreen() { return false; }
}