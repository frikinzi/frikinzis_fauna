package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.frikinzi.creatures.player.SpeciesEntry;
import com.frikinzi.creatures.registry.CreaturesEntities;
import com.frikinzi.creatures.registry.ModEventSubscriber;
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
import net.minecraft.world.item.ItemStack;
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
         bookW = 429;
         bookH = 270;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        graphics.blit(BOOK_TEXTURE, bookX, bookY, 0, 0, bookW, bookH, bookW, bookW);

        int rightPageX = bookX + 230;
        int pageY = bookY + 15;

        boolean hasM = discoveredGenders.contains("m");
        boolean hasF = discoveredGenders.contains("f");
        boolean hasBoth = hasM && hasF;

        if (hasBoth) {
            renderDummy(graphics, variant, 1, bookX + 80,  pageY + 100);
            renderDummy(graphics, variant, 0, bookX + 150, pageY + 100);
            graphics.drawString(font, "♂", bookX + 72,  pageY + 105, 0x5577FF, false);
            graphics.drawString(font, "♀", bookX + 142, pageY + 105, 0xFF77AA, false);
        } else {
            int gender = hasM ? 1 : 0;
            renderDummy(graphics, variant, gender, bookX + 95, pageY + 110);
            String genderSymbol = hasM ? "♂" : "♀";
            int genderColor = hasM ? 0x5577FF : 0xFF77AA;
            graphics.drawString(font, genderSymbol, bookX + 97, pageY + 115, genderColor, false);
        }

        int babyX = bookX + 125;
        int babyY = pageY + 150;
        renderDummy(graphics, variant, hasM ? 1 : 0, babyX, babyY, true);

        String babyLabel = Component.translatable("gui.baby").getString();
        graphics.drawString(font, babyLabel,
                babyX - font.width(babyLabel) / 2 + 5, babyY + 12, 0x5C4033, false);

        int infoX = rightPageX;
        int infoY = pageY + 30;

        LivingEntity dummyInfo = (LivingEntity) species.entityType.get().create(Minecraft.getInstance().level);
        if (dummyInfo != null) {
            setupDummy(dummyInfo, variant, hasM ? 1 : 0);

            String speciesName = species.getSpeciesName(variant);
            if (speciesName.isEmpty() || speciesName.equals("Unknown"))
                speciesName = species.displayName.getString();
            drawText(graphics, ChatFormatting.BOLD + speciesName, infoX, infoY, 0x3D2B1F);

            String sciName = species.getScientificName(variant);
            if (!sciName.isEmpty()) {
                drawText(graphics, ChatFormatting.ITALIC + sciName, infoX, infoY + 12, 0x5C4033);
            }

            int y = infoY + 28;

            if (dummyInfo instanceof CreaturesBirdEntity bird) {
                drawIUCN(graphics, bird.getIUCNText(), bird.getIUCNColor().getColor(), infoX, y);
                if (sciName.isEmpty()) {
                    drawText(graphics, ChatFormatting.ITALIC + bird.getScientificName(), infoX, infoY + 12, 0x5C4033);
                }
            } else if (dummyInfo instanceof FishBase fish) {
                drawIUCN(graphics, fish.getIUCNText(), fish.getIUCNColor(), infoX, y);
                if (sciName.isEmpty()) {
                    drawText(graphics, ChatFormatting.ITALIC + fish.getScientificName(), infoX, infoY + 12, 0x5C4033);
                }
            } else if (dummyInfo instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase crab) {
                drawIUCN(graphics, crab.getIUCNText(), crab.getIUCNColor(), infoX, y);
                if (sciName.isEmpty()) {
                    drawText(graphics, ChatFormatting.ITALIC + crab.getScientificName(), infoX, infoY + 12, 0x5C4033);
                }
            }
            y += 14;

            List<Region> variantRegions = species.regions.getOrDefault(variant, List.of());
            if (!variantRegions.isEmpty()) {
                String regionStr = variantRegions.stream()
                        .map(r -> r.getDisplayName().getString())
                        .collect(java.util.stream.Collectors.joining(", "));
                Component regionLabel = Component.literal(Component.translatable("creatures.fieldgui.region").getString() + " ")
                        .withStyle(ChatFormatting.BOLD);
                Component regionValue = Component.literal(regionStr)
                        .withStyle(net.minecraft.network.chat.Style.EMPTY.withBold(false));
                Component regionFull = regionLabel.copy().append(regionValue);
                for (net.minecraft.util.FormattedCharSequence line :
                        font.split(regionFull, bookW / 2 - 60)) {
                    graphics.drawString(font, line, infoX, y, 0x3D2B1F, false);
                    y += 10;
                }
                y += 12;
            }

            List<net.minecraft.world.item.ItemStack> foodItems = getAllFoodItems(dummyInfo);
            if (!foodItems.isEmpty()) {
                drawText(graphics, ChatFormatting.BOLD + Component.translatable("gui.food").getString(), infoX, y, 0x3D2B1F);
                for (int i = 0; i < Math.min(foodItems.size(), 6); i++) {
                    graphics.renderItem(foodItems.get(i), infoX + 35 + (i * 18), y - 3);
                }
                y += 14;
            }

            if (dummyInfo instanceof CreaturesBirdEntity) {
                Integer speciesIndex = ModEventSubscriber.getBirdEntityMap()
                        .inverse().get(species.entityType.get());
                if (speciesIndex != null) {
                    EggEntity tempEgg = new EggEntity(CreaturesEntities.EGG.get(),
                            Minecraft.getInstance().level);
                    tempEgg.setSpecies(speciesIndex);
                    ItemStack eggItem = tempEgg.getEggItem();
                    if (!eggItem.isEmpty()) {
                        drawText(graphics, ChatFormatting.BOLD +
                                Component.translatable("gui.egg").getString(), infoX, y, 0x3D2B1F);
                        graphics.renderItem(eggItem, infoX + 35, y - 3);
                        y += 14;
                    }
                }
            }

            Component funFact = getFunFact(dummyInfo);
            if (funFact != null) {
                y += 4;
                for (net.minecraft.util.FormattedCharSequence line :
                        font.split(funFact, bookW / 2 - 60)) {
                    graphics.drawString(font, line, infoX, y, 0x5C4033, false);
                    y += 10;
                }
            }
        }

        super.render(graphics, mouseX, mouseY, partialTick);
    }

//    private void renderDummy(GuiGraphics graphics, int variant, int gender, int x, int y) {
//        LivingEntity dummy = (LivingEntity) species.entityType.get().create(Minecraft.getInstance().level);
//        if (dummy == null) return;
//        setupDummy(dummy, variant, gender);
//
//        float h = dummy.getBbHeight();
//        int scale = (int)(40f / h);
//        Quaternionf rot = new Quaternionf().rotateZ((float) Math.PI).rotateY((float) Math.toRadians(160));
//
//        if (dummy instanceof CreaturesBirdEntity bird) { rot = bird.getRotforGUI(); scale = bird.getScaleforGUI(); }
//        else if (dummy instanceof FishBase fish) { rot = fish.getRotforGUI(); scale = fish.getScaleforGUI(); }
//        else if (dummy instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase crab) { rot = crab.getRotforGUI(); scale = crab.getScaleforGUI(); }
//        scale = scale * 2;
//
//        InventoryScreen.renderEntityInInventory(graphics, x, y, scale, rot, null, dummy);
//    }

    private void renderDummy(GuiGraphics graphics, int variant, int gender, int x, int y) {
        renderDummy(graphics, variant, gender, x, y, false);
    }

    private void renderDummy(GuiGraphics graphics, int variant, int gender, int x, int y, boolean baby) {
        LivingEntity dummy = (LivingEntity) species.entityType.get().create(Minecraft.getInstance().level);
        if (dummy == null) return;
        setupDummy(dummy, variant, gender);

        if (baby) {
            if (dummy instanceof CreaturesBirdEntity bird) bird.setBaby(true);
            else if (dummy instanceof FishBase fish) fish.setBaby(true);
            else if (dummy instanceof net.minecraft.world.entity.AgeableMob ageable) ageable.setBaby(true);
        }

        float h = dummy.getBbHeight();
        int offset = 0;
        int scale = (int)(40f / h);
        Quaternionf rot = new Quaternionf().rotateZ((float) Math.PI).rotateY((float) Math.toRadians(160));

        if (dummy instanceof CreaturesBirdEntity bird) { rot = bird.getRotforGUI(); scale = bird.getScaleforGUI(); offset= bird.getYOffsetForGUI(); }
        else if (dummy instanceof FishBase fish) { rot = fish.getRotforGUI(); scale = fish.getScaleforGUI(); offset = fish.getYOffsetForGUI(); }
        else if (dummy instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase crab) { rot = crab.getRotforGUI(); scale = crab.getScaleforGUI(); offset = crab.getYOffsetForGUI(); }

        scale = baby ? scale : scale * 2;

        InventoryScreen.renderEntityInInventory(graphics, x, y + offset, scale, rot, null, dummy);
    }

    private void setupDummy(LivingEntity dummy, int variant, int gender) {
        if (dummy == null) return;
        if (dummy instanceof CreaturesBirdEntity bird) {
            bird.setVariant(variant);
            bird.setGender(gender);
            //bird.setSubVariant(bird.getSubVariantBasedOnVariant(variant));
            bird.setSubVariant(1);
            bird.setOnGround(true);
        } else if (dummy instanceof FishBase fish) {
            fish.setForcedInWater(true);
            fish.setVariant(variant);
            fish.setGender(gender);
            //fish.setSubVariant(fish.getSubVariantBasedOnVariant(variant));
            fish.setSubVariant(1);
//            if (fish instanceof ClownfishEntity || fish instanceof SwordfishEntity) {
//                fish.setSubVariant(1);
//            }
        } else if (dummy instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase crab) {
            crab.setVariant(variant);
            crab.setGender(gender);
        }
    }

    private void drawText(GuiGraphics graphics, String text, int x, int y, int color) {
        graphics.drawString(font, text, x, y, color, false);
    }

    private void drawIUCN(GuiGraphics graphics, Component iucnText, int color, int x, int y) {
        drawText(graphics, ChatFormatting.BOLD + "IUCN: " + ChatFormatting.RESET, x, y, 0x3D2B1F);
        graphics.drawString(font, " " + iucnText.getString(), x + font.width("IUCN: "), y, color, false);
    }

    private net.minecraft.world.item.ItemStack getFoodItem(LivingEntity e) {
        if (e instanceof CreaturesBirdEntity b) return b.getFoodItem();
        if (e instanceof FishBase f) return new net.minecraft.world.item.ItemStack(f.getFoodItem());
        if (e instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase c) return c.getFoodItem();
        return net.minecraft.world.item.ItemStack.EMPTY;
    }

    private Component getFunFact(LivingEntity e) {
        if (e instanceof CreaturesBirdEntity b) return b.getFunFact();
        if (e instanceof FishBase f) return f.getFunFact();
        if (e instanceof com.frikinzi.creatures.entity.base.AbstractCrabBase c) return c.getFunFact();
        return null;
    }

    @Override
    protected void init() {
        super.init();
        int bookW = 390, bookH = 245;
        int bookX = (this.width - bookW) / 2;
        int bookY = (this.height - bookH) / 2;
        this.addRenderableWidget(Button.builder(
                        Component.literal("◀ " + Component.translatable("creatures.fieldgui.back").getString()),
                        b -> Minecraft.getInstance().setScreen(parent))
                .pos(bookX + 10, bookY + bookH - 35).size(50, 20).build());
    }

    private List<net.minecraft.world.item.ItemStack> getAllFoodItems(LivingEntity e) {
        if (e instanceof CreaturesBirdEntity b) return b.getAllFoodItems();
        if (e instanceof FishBase f) {
            return List.of(new net.minecraft.world.item.ItemStack(f.getFoodItem()));
        }
        if (e instanceof AbstractCrabBase c) return c.getAllFoodItems();
        return List.of();
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
