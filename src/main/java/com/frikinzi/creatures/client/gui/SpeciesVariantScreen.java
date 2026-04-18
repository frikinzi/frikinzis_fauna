package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.entity.CormorantEntity;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SpeciesVariantScreen extends Screen {
    private final SpeciesEntry species;
    private final Set<Integer> discoveredVariants;
    private final Screen parent;

    private static final ResourceLocation BOOK_TEXTURE = new ResourceLocation("creatures:textures/gui/creatures/book.png");
    private final Map<Integer, String> cachedGenders = new HashMap<>();
    private static final int BOOK_W = 390;
    private static final int BOOK_H = 245;
    private static final int CELL_SIZE = 40;
    private static final int COLS_PER_PAGE = 3;

    public SpeciesVariantScreen(SpeciesEntry species, Set<Integer> discoveredVariants, Screen parent) {
        super(Component.literal(species.displayName.getString()));
        this.species = species;
        this.discoveredVariants = discoveredVariants;
        this.parent = parent;
    }

    private int getBookX() { return (this.width - BOOK_W) / 2; }
    private int getBookY() { return (this.height - BOOK_H) / 2; }
    private int getLeftPageX() { return getBookX() + 45; }
    private int getRightPageX() { return getBookX() + 200; }
    private int getPageY() { return getBookY() + 35; }

    private int getCellX(int variant) {
        int zeroIndex = variant - 1;
        int globalCol = zeroIndex % (COLS_PER_PAGE * 2);
        boolean isRightPage = globalCol >= COLS_PER_PAGE;
        int colInPage = globalCol % COLS_PER_PAGE;
        return (isRightPage ? getRightPageX() : getLeftPageX()) + colInPage * CELL_SIZE;
    }

    private int getCellY(int variant) {
        int zeroIndex = variant - 1;
        int row = zeroIndex / (COLS_PER_PAGE * 2);
        return getPageY() + row * CELL_SIZE;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        int bookX = getBookX();
        int bookY = getBookY();

        // Draw book background
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        graphics.blit(BOOK_TEXTURE, bookX, bookY, 0, 0, BOOK_W, BOOK_H, BOOK_W, BOOK_W);

        //graphics.drawString(font, species.displayName, getLeftPageX(), bookY + 20, 0x3D2B1F, false);

        FieldGuideCapability cap = Minecraft.getInstance().player
                .getCapability(FieldGuideCapability.CAPABILITY).orElse(null);

        Component hoveredTooltip = null;
        int tooltipX = 0, tooltipY = 0;

        for (int variant = 1; variant <= species.totalVariants; variant++) {
            int x = getCellX(variant);
            int y = getCellY(variant);

            boolean discovered = discoveredVariants.contains(variant);

            if (discovered) {
                LivingEntity dummy = (LivingEntity) species.entityType.get().create(Minecraft.getInstance().level);
                if (dummy != null) {
                    float entityHeight = dummy.getBbHeight();
                    int scale = (int) (20.0f / entityHeight);
                    if (dummy instanceof CormorantEntity) {
                        scale = scale / 2;
                    }
                    Quaternionf rotation = new Quaternionf()
                            .rotateZ((float) Math.PI)
                            .rotateY((float) Math.toRadians(140));
                    int offset = 0;
                    if (dummy instanceof CreaturesBirdEntity bird) {
                        Set<String> genders = cap.getDiscoveredGenders(species.entityKey, variant);
                        String gender;
                        if (genders.size() > 1) {
                            // Cycle between m and f every 3 seconds
                            long tick = System.currentTimeMillis() / 1000;
                            gender = (tick % 2 == 0) ? "m" : "f";
                        } else {
                            gender = cachedGenders.computeIfAbsent(variant, k ->
                                    genders.isEmpty() ? "m" : genders.iterator().next());
                        }
                        bird.setVariant(variant);
                        bird.setOnGround(true);
                        bird.setGender(gender.equals("m") ? 1 : 0);
                        rotation = bird.getRotforGUI();
                        scale = bird.getScaleforGUI();
                        offset = bird.getYOffsetForGUI();
                    }
                    if (dummy instanceof FishBase fish) {
                        Set<String> genders = cap.getDiscoveredGenders(species.entityKey, variant);
                        String gender;
                        if (genders.size() > 1) {
                            // Cycle between m and f every 3 seconds
                            long tick = System.currentTimeMillis() / 1000;
                            gender = (tick % 2 == 0) ? "m" : "f";
                        } else {
                            gender = cachedGenders.computeIfAbsent(variant, k ->
                                    genders.isEmpty() ? "m" : genders.iterator().next());
                        }
                        fish.setForcedInWater(true);
                        fish.setVariant(variant);
                        fish.setGender(gender.equals("m") ? 1 : 0);
                        rotation = fish.getRotforGUI();
                        scale = fish.getScaleforGUI();
                        offset = fish.getYOffsetForGUI();
                    }
                    if (dummy instanceof AbstractCrabBase crab) {
                        Set<String> genders = cap.getDiscoveredGenders(species.entityKey, variant);
                        String gender;
                        if (genders.size() > 1) {
                            // Cycle between m and f every 3 seconds
                            long tick = System.currentTimeMillis() / 1000;
                            gender = (tick % 2 == 0) ? "m" : "f";
                        } else {
                            gender = cachedGenders.computeIfAbsent(variant, k ->
                                    genders.isEmpty() ? "m" : genders.iterator().next());
                        }
                        crab.setVariant(variant);
                        crab.setOnGround(true);
                        crab.setGender(gender.equals("m") ? 1 : 0);
                        rotation = crab.getRotforGUI();
                        scale = crab.getScaleforGUI();
                        offset = crab.getYOffsetForGUI();
                    }
                    //graphics.enableScissor(x, y, x + CELL_SIZE, y + CELL_SIZE);
                    InventoryScreen.renderEntityInInventory(graphics,
                            x + CELL_SIZE / 2, y + CELL_SIZE - 5 + offset, scale, rotation, null, dummy);
                    //graphics.disableScissor();

                    // discovered gender symbols
                    if (cap != null) {
                        Set<String> genders = cap.getDiscoveredGenders(species.entityKey, variant);
                        String genderText = (genders.contains("m") ? "♂" : "·") + (genders.contains("f") ? "♀" : "·");
                        graphics.drawString(font, genderText, x + 1, y + CELL_SIZE - 9, 0x3D2B1F, false);
                    }

                    if (mouseX >= x && mouseX <= x + CELL_SIZE && mouseY >= y && mouseY <= y + CELL_SIZE) {
                        String name = species.getSpeciesName(variant);
                        String sci = species.getScientificName(variant);
                        hoveredTooltip = name.isEmpty()
                                ? Component.literal("Variant " + variant)
                                : Component.literal(name);
                        tooltipX = mouseX;
                        tooltipY = mouseY;
                    }
                }
            } else {
                graphics.fill(x, y, x + CELL_SIZE, y + CELL_SIZE, 0xBB888888);
                graphics.drawString(font, "?", x + 12, y + 11, 0xFFFFFF, true);
                graphics.fill(x, y, x + CELL_SIZE, y + 1, 0x55000000);
                graphics.fill(x, y, x + 1, y + CELL_SIZE, 0x55000000);
                graphics.fill(x, y + CELL_SIZE - 1, x + CELL_SIZE, y + CELL_SIZE, 0x55000000);
                graphics.fill(x + CELL_SIZE - 1, y, x + CELL_SIZE, y + CELL_SIZE, 0x55000000);

                if (mouseX >= x && mouseX <= x + CELL_SIZE && mouseY >= y && mouseY <= y + CELL_SIZE) {
                    graphics.renderTooltip(font, Component.literal("???"), mouseX, mouseY);
                }
            }
        }

        if (hoveredTooltip != null) {
            graphics.renderTooltip(font, hoveredTooltip, tooltipX, tooltipY);
        }

// Progress bar — bottom of left page
        int barWidth = 120;
        int barHeight = 6;
        int barX = getLeftPageX();
        int barY = getBookY() + BOOK_H - 65;

//        int totalPossible = species.totalVariants; // both genders
//        int totalDiscovered = 0;
        int totalPossible = species.totalVariants;
        int totalDiscovered = discoveredVariants.size();


        graphics.drawString(font, totalDiscovered + " / " + totalPossible + " " + Component.translatable("creatures.fieldgui.discover").getString(),
                barX, barY - 10, 0x3D2B1F, false);


        graphics.fill(barX, barY, barX + barWidth, barY + barHeight, 0x55000000);

        int fillWidth = totalPossible > 0 ? (int) ((float) totalDiscovered / totalPossible * barWidth) : 0;
        graphics.fill(barX, barY, barX + fillWidth, barY + barHeight, 0xFF5C8A3C);

// Border
        graphics.fill(barX, barY, barX + barWidth, barY + 1, 0x88000000);
        graphics.fill(barX, barY + barHeight - 1, barX + barWidth, barY + barHeight, 0x88000000);
        graphics.fill(barX, barY, barX + 1, barY + barHeight, 0x88000000);
        graphics.fill(barX + barWidth - 1, barY, barX + barWidth, barY + barHeight, 0x88000000);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void init() {
        super.init();
        int bookX = getBookX();
        int bookY = getBookY();

        this.addRenderableWidget(Button.builder(Component.literal("◀ " + Component.translatable("creatures.fieldgui.back").getString()), b ->
                        this.minecraft.setScreen(parent))
                .pos(bookX + 10, bookY + BOOK_H - 40).size(50, 20).build());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        FieldGuideCapability cap = Minecraft.getInstance().player
                .getCapability(FieldGuideCapability.CAPABILITY).orElse(null);

        for (int variant = 1; variant <= species.totalVariants; variant++) {
            int x = getCellX(variant);
            int y = getCellY(variant);

            if (mouseX >= x && mouseX <= x + CELL_SIZE && mouseY >= y && mouseY <= y + CELL_SIZE) {
                boolean discovered = discoveredVariants.contains(variant);
                if (discovered && cap != null) {
                    Set<String> genders = cap.getDiscoveredGenders(species.entityKey, variant);
                    Minecraft.getInstance().setScreen(
                            new VariantDetailScreen(this, species, variant, genders));
                    return true;
                }
                // clicking undiscovered variant does nothing
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}