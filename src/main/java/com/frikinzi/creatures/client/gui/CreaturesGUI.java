package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.base.AbstractCrabBase;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.FishBase;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CreaturesGUI extends Screen {
    private final int bookImageHeight = 245;
    private final int bookImageWidth = 390;
    protected int xSize;
    protected int ySize;
    private static final ResourceLocation TEXTURE = new ResourceLocation("creatures:textures/gui/creatures/book.png");

    public CreaturesGUI() {
        super(Component.translatable("creatures_gui"));
//        xSize = 390;
//        ySize = 245;
        xSize = 429;
        ySize = 270;
    }

    protected void init() {
        super.init();
    }

    public void render(@NotNull GuiGraphics matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        int offLeft = (int) ((this.width - 272) / 2.0F);
        int offTop = (int) ((this.height - 250) / 2.0F);
        int j = (this.width - this.bookImageWidth) / 2;
        int k = (this.height - this.bookImageHeight) / 2;
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        matrices.blit(TEXTURE, j, k, 0, 0, xSize, ySize, xSize, ySize + 120);
        LivingEntity entity = Creatures.PROXY.getReferencedMob();
        if (entity instanceof CreaturesBirdEntity) {
            Font font = this.getMinecraft().font;
            CreaturesBirdEntity bird = (CreaturesBirdEntity) entity;
            String s1 = ChatFormatting.BOLD + bird.getDisplayName().getString();
            font.drawInBatch(s1, offLeft, 50 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            String scientificname = ChatFormatting.ITALIC + bird.getScientificName();
            font.drawInBatch(ChatFormatting.ITALIC+scientificname, offLeft, 60 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);

            Component g = Component.translatable("gui.sex", bird.getGenderString()).withStyle(ChatFormatting.BOLD);
            Component s = bird.getGenderText();
            font.drawInBatch(ChatFormatting.BOLD + g.getString() + ChatFormatting.RESET + " " + s.getString(), offLeft, 80 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component h = Component.translatable("gui.health",bird.getHealth(), bird.getMaxHealth());
            font.drawInBatch(h.getString(), offLeft, 95 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            font.drawInBatch(bird.getSpeciesName(), offLeft, 110 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component height = Component.translatable("gui.height").withStyle(ChatFormatting.BOLD);
            String height_s = bird.getHeightString();
            font.drawInBatch(ChatFormatting.BOLD + height.getString() + " " + ChatFormatting.RESET + height_s, offLeft, 125 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component food = Component.translatable("gui.food").withStyle(ChatFormatting.BOLD);
            String s6 = ChatFormatting.BOLD + food.getString() + " ";
            font.drawInBatch(s6, offLeft, 140 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
           //matrices.renderItem(bird.getFoodItem(), offLeft + 30, 140 + offTop);

// Render all food items in a row
            List<ItemStack> foodItems = bird.getAllFoodItems();
            for (int i = 0; i < foodItems.size(); i++) {
                matrices.renderItem(foodItems.get(i), offLeft + 30 + (i * 18), 140 + offTop);
            }
//            Component IUCN1 = Component.translatable("gui.iucn").withStyle(ChatFormatting.BOLD);;
//            font.drawInBatch(IUCN1.getString(), offLeft, 160 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            font.drawInBatch(ChatFormatting.BOLD + Component.translatable("gui.iucn").getString(),
                    offLeft, 160 + offTop, 0X000000, false,
                    matrices.pose().last().pose(), matrices.bufferSource(),
                    Font.DisplayMode.NORMAL, 0, 15728880);
            Component IUCN = bird.getIUCNText();
            font.drawInBatch(bird.getIUCNColor() + IUCN.getString(), offLeft+30, 160 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component funfact = bird.getFunFact();
            drawSplitText(font, funfact, 144, matrices, offLeft + 160, 120 + offTop);

        }
        if (entity instanceof FishBase) {
            Font font = this.getMinecraft().font;
            FishBase fish = (FishBase) entity;
            String s1 = ChatFormatting.BOLD + fish.getDisplayName().getString();
            font.drawInBatch(s1, offLeft, 50 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            String scientificname = ChatFormatting.ITALIC + fish.getScientificName();
            font.drawInBatch(ChatFormatting.ITALIC+scientificname, offLeft, 60 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);

            Component g = Component.translatable("gui.sex", fish.getGenderString()).withStyle(ChatFormatting.BOLD);
            Component s = fish.getGenderText();
            font.drawInBatch(ChatFormatting.BOLD + g.getString() + ChatFormatting.RESET + " " + s.getString(), offLeft, 80 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component h = Component.translatable("gui.health",fish.getHealth(), fish.getMaxHealth());
            font.drawInBatch(h.getString(), offLeft, 95 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            font.drawInBatch(fish.getSpeciesName(), offLeft, 110 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component height = Component.translatable("gui.height").withStyle(ChatFormatting.BOLD);
            String height_s = fish.getHeightString();
            font.drawInBatch(ChatFormatting.BOLD + height.getString() + " " + height_s, offLeft, 125 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component food = Component.translatable("gui.food").withStyle(ChatFormatting.BOLD);
            String s6 = ChatFormatting.BOLD + food.getString() + " ";
            font.drawInBatch(s6, offLeft, 140 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            matrices.renderItem(new ItemStack(fish.getFoodItem()), offLeft + 30, 140 + offTop);
            Component IUCN1 = Component.translatable("gui.iucn").withStyle(ChatFormatting.BOLD);
            font.drawInBatch(IUCN1.getString(), offLeft, 160 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component IUCN = fish.getIUCNText();
            font.drawInBatch(IUCN.getString(), offLeft + 30, 160 + offTop, fish.getIUCNColor(), false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            //font.drawInBatch(fish.getIUCNColor() + IUCN.getString(), offLeft+30, 160 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component funfact = fish.getFunFact();
            drawSplitText(font, funfact, 114, matrices, offLeft + 160, 120 + offTop);

        }
        if (entity instanceof AbstractCrabBase) {
            Font font = this.getMinecraft().font;
            AbstractCrabBase fish = (AbstractCrabBase) entity;
            String s1 = ChatFormatting.BOLD + fish.getDisplayName().getString();
            font.drawInBatch(s1, offLeft, 50 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            String scientificname = ChatFormatting.ITALIC + fish.getScientificName();
            font.drawInBatch(ChatFormatting.ITALIC+scientificname, offLeft, 60 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);

            Component g = Component.translatable("gui.sex", fish.getGenderString()).withStyle(ChatFormatting.BOLD);
            Component s = fish.getGenderText();
            font.drawInBatch(ChatFormatting.BOLD + g.getString() + ChatFormatting.RESET + " " + s.getString(), offLeft, 80 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component h = Component.translatable("gui.health",fish.getHealth(), fish.getMaxHealth());
            font.drawInBatch(h.getString(), offLeft, 95 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            font.drawInBatch(fish.getSpeciesName(), offLeft, 110 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component height = Component.translatable("gui.height").withStyle(ChatFormatting.BOLD);
            String height_s = fish.getHeightString();
            font.drawInBatch(ChatFormatting.BOLD + height.getString() + " " + height_s, offLeft, 125 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component food = Component.translatable("gui.food").withStyle(ChatFormatting.BOLD);
            String s6 = ChatFormatting.BOLD + food.getString() + " ";
            font.drawInBatch(s6, offLeft, 140 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            matrices.renderItem(fish.getFoodItem(), offLeft + 30, 140 + offTop);
            Component IUCN1 = Component.translatable("gui.iucn").withStyle(ChatFormatting.BOLD);
            font.drawInBatch(IUCN1.getString(), offLeft, 160 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component IUCN = fish.getIUCNText();
            font.drawInBatch(IUCN.getString(), offLeft + 30, 160 + offTop, fish.getIUCNColor(), false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            //font.drawInBatch(fish.getIUCNColor() + IUCN.getString(), offLeft+30, 160 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component funfact = fish.getFunFact();
            drawSplitText(font, funfact, 114, matrices, offLeft + 160, 120 + offTop);

        }
        if (entity instanceof EggEntity egg) {
            ItemStack item = egg.getEggItem();

            matrices.renderItem(item, offLeft, offTop + 107);

            matrices.drawString(font, item.getDisplayName(), offLeft + 20, offTop + 112, 0x3D2B1F, false);

            matrices.drawString(font,
                    Component.translatable("gui.hatchtime").getString() + " " + egg.hatchTime,
                    offLeft, offTop + 80, 0x3D2B1F, false);

        } else if (entity instanceof CreaturesRoeEntity roe) {
            matrices.drawString(font,
                    Component.translatable("gui.hatchtime").getString() + " " + roe.getHatchTime(),
                    offLeft, offTop + 80, 0x3D2B1F, false);
        }
        if (entity instanceof LivingEntity) {
            Font font = this.getMinecraft().font;
            LivingEntity mob = (LivingEntity) entity;
            String s1 = ChatFormatting.BOLD + mob.getDisplayName().getString();
            font.drawInBatch(s1, offLeft, 50 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
            Component h = Component.translatable("gui.health",mob.getHealth(), mob.getMaxHealth());
            font.drawInBatch(h.getString(), offLeft, 95 + offTop, 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);

        }
        Quaternionf quaternionf = (new Quaternionf()).rotateY((float) Mth.lerp((float) mouseX / this.width, 0, Math.PI)).rotateZ((float) Mth.lerp((float) mouseY / this.width, Math.PI, Math.PI + 0.2));
        InventoryScreen.renderEntityInInventory(matrices,offLeft + 235, 120 + offTop, 60, quaternionf, null,entity);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void drawSplitText(Font font, Component text, int width, GuiGraphics matrices, int x, int y) {
        List<FormattedCharSequence> lines = font.split(text, width);

        int lineSpacing = font.lineHeight + 2;
        for (int i = 0; i < lines.size(); i++) {
            font.drawInBatch(lines.get(i), x, y + (i * lineSpacing), 0X000000, false, matrices.pose().last().pose(), matrices.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880);
        }

    }
}