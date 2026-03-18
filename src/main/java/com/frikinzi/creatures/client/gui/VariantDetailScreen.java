package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.entity.Region;
import com.frikinzi.creatures.player.SpeciesEntry;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Quaternionf;

import java.util.List;
import java.util.Set;

public class VariantDetailScreen extends Screen {
    private final Screen parent;
    private final SpeciesEntry species;
    private final int variant;
    private final Set<String> discoveredGenders;
    private static final ResourceLocation BOOK_TEXTURE =
            new ResourceLocation("creatures:textures/gui/creatures/book.png");

    public VariantDetailScreen(Screen parent, SpeciesEntry species, int variant, Set<String> discoveredGenders) {
        super(Component.literal(species.getSpeciesName(variant)));
        this.parent = parent;
        this.species = species;
        this.variant = variant;
        this.discoveredGenders = discoveredGenders;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        graphics.blit(BOOK_TEXTURE, bookX, bookY, 0, 0, bookW, bookH, bookW, bookW);

        int leftPageX = bookX + 20;
        int rightPageX = bookX + 210;
        int pageY = bookY + 20;

        boolean hasM = discoveredGenders.contains("m");
        boolean hasF = discoveredGenders.contains("f");
        boolean hasBoth = hasM && hasF;

        if (hasBoth) {
            renderDummy(graphics, species, variant, 1, bookX + 80,  pageY + 100);
            renderDummy(graphics, species, variant, 0, bookX + 150, pageY + 100);
            graphics.drawString(font, "♂", bookX + 72,  pageY + 105, 0x5577FF, false);
            graphics.drawString(font, "♀", bookX + 142, pageY + 105, 0xFF77AA, false);
        } else {
            // Single entity centered on left page
            int gender = hasM ? 1 : 0;
            renderDummy(graphics, species, variant, gender, bookX + 95, pageY + 110);
            String genderSymbol = hasM ? "♂" : "♀";
            int genderColor = hasM ? 0x5577FF : 0xFF77AA;
            graphics.drawString(font, genderSymbol, bookX + 97, pageY + 115, genderColor, false);
        }

        int infoX = rightPageX;
        int infoY = pageY + 30;

        LivingEntity dummy = (LivingEntity) species.entityType.get().create(Minecraft.getInstance().level);
        if (dummy != null) {
            setupDummy(dummy, variant, hasM ? 1 : 0);

            String speciesName = species.getSpeciesName(variant);
            if (speciesName.isEmpty() || speciesName.equals("Unknown"))
                speciesName = species.displayName.getString();
            drawText(graphics, net.minecraft.ChatFormatting.BOLD + speciesName, infoX, infoY, 0x3D2B1F);

            // Scientific name (italic)
            String sciName = species.getScientificName(variant);
            if (!sciName.isEmpty()) {
                drawText(graphics, net.minecraft.ChatFormatting.ITALIC + sciName, infoX, infoY + 12, 0x5C4033);
            }

            int y = infoY + 28;

            // IUCN status
            if (dummy instanceof com.frikinzi.creatures.entity.base.CreaturesBirdEntity bird) {
                drawIUCN(graphics, bird.getIUCNText(), bird.getIUCNColor().getColor(), infoX, y);
            } else if (dummy instanceof com.frikinzi.creatures.entity.base.FishBase fish) {
                drawIUCN(graphics, fish.getIUCNText(), fish.getIUCNColor(), infoX, y);
            } else if (dummy instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase crab) {
                drawIUCN(graphics, crab.getIUCNText(), crab.getIUCNColor(), infoX, y);
            }
            y += 14;

            List<Region> variantRegions = species.regions.getOrDefault(variant, List.of());
            if (!variantRegions.isEmpty()) {
                String regionStr = variantRegions.stream()
                        .map(r -> r.getDisplayName().getString())
                        .collect(java.util.stream.Collectors.joining(", "));
                Component regionLabel = Component.literal("Region: ")
                        .withStyle(net.minecraft.ChatFormatting.BOLD);
                Component regionValue = Component.literal(regionStr).withStyle(net.minecraft.network.chat.Style.EMPTY.withBold(false));
                Component regionFull = regionLabel.copy().append(regionValue);
                for (net.minecraft.util.FormattedCharSequence line :
                        font.split(regionFull, bookW / 2 - 60)) {
                    graphics.drawString(font, line, infoX, y, 0x3D2B1F, false);
                    y += 10;
                }
                y += 12;
            }

            // Food item
            net.minecraft.world.item.ItemStack foodItem = getFoodItem(dummy);
            if (foodItem != null && !foodItem.isEmpty()) {
                drawText(graphics, net.minecraft.ChatFormatting.BOLD + "Food:", infoX, y, 0x3D2B1F);
                graphics.renderItem(foodItem, infoX + 35, y - 3);
                y += 14;
            }

            Component funFact = getFunFact(dummy);
            if (funFact != null) {
                y += 4;
//                drawText(graphics, net.minecraft.ChatFormatting.BOLD + "Fun Fact:", infoX, y, 0x3D2B1F);
//                y += 12;
                for (net.minecraft.util.FormattedCharSequence line : 
                        font.split(funFact, bookW / 2 - 60)) {
                    graphics.drawString(font, line, infoX, y, 0x5C4033, false);
                    y += 10;
                }
            }
        }

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void renderDummy(GuiGraphics graphics, SpeciesEntry species, int variant, int gender, int x, int y) {
        LivingEntity dummy = (LivingEntity) species.entityType.get().create(Minecraft.getInstance().level);
        if (dummy == null) return;
        setupDummy(dummy, variant, gender);

        float h = dummy.getBbHeight();
        int scale = (int)(40f / h); // larger scale than grid
        Quaternionf rot = new Quaternionf().rotateZ((float) Math.PI).rotateY((float) Math.toRadians(160));

        if (dummy instanceof com.frikinzi.creatures.entity.base.CreaturesBirdEntity bird)
            rot = bird.getRotforGUI();
        else if (dummy instanceof com.frikinzi.creatures.entity.base.FishBase fish)
            rot = fish.getRotforGUI();
        else if (dummy instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase crab)
            rot = crab.getRotforGUI();

        InventoryScreen.renderEntityInInventory(graphics, x, y, scale, rot, null, dummy);
    }

    private void setupDummy(LivingEntity dummy, int variant, int gender) {
        if (dummy instanceof com.frikinzi.creatures.entity.base.CreaturesBirdEntity bird) {
            bird.setVariant(variant);
            bird.setGender(gender);
            bird.setSubVariant(bird.getSubVariantBasedOnVariant(variant));
            bird.setOnGround(true);
        } else if (dummy instanceof com.frikinzi.creatures.entity.base.FishBase fish) {
            fish.setVariant(variant);
            fish.setGender(gender);
            fish.setSubVariant(fish.getSubVariantBasedOnVariant(variant));
            fish.setForcedInWater(true);
        } else if (dummy instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase crab) {
            crab.setVariant(variant);
            crab.setGender(gender);
        }
    }

    private void drawText(GuiGraphics graphics, String text, int x, int y, int color) {
        graphics.drawString(font, text, x, y, color, false);
    }

    private void drawIUCN(GuiGraphics graphics, Component iucnText, int color, int x, int y) {
        drawText(graphics, net.minecraft.ChatFormatting.BOLD + "IUCN: " 
                + net.minecraft.ChatFormatting.RESET, x, y, 0x3D2B1F);
        graphics.drawString(font, " "+iucnText.getString(), x + font.width("IUCN: "), y, color, false);
    }

    private net.minecraft.world.item.ItemStack getFoodItem(LivingEntity e) {
        if (e instanceof com.frikinzi.creatures.entity.base.CreaturesBirdEntity b) return b.getFoodItem();
        if (e instanceof com.frikinzi.creatures.entity.base.FishBase f) return new net.minecraft.world.item.ItemStack(f.getFoodItem());
        if (e instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase c) return c.getFoodItem();
        return net.minecraft.world.item.ItemStack.EMPTY;
    }

    private Component getFunFact(LivingEntity e) {
        if (e instanceof com.frikinzi.creatures.entity.base.CreaturesBirdEntity b) return b.getFunFact();
        if (e instanceof com.frikinzi.creatures.entity.base.FishBase f) return f.getFunFact();
        if (e instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase c) return c.getFunFact();
        return null;
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
    }

    @Override
    public boolean isPauseScreen() { return false; }
}