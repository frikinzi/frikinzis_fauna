package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.base.CreaturesBirdEntity;
import com.frikinzi.creatures.entity.base.TameableFlyingBirdEntity;
import com.frikinzi.creatures.entity.egg.EggEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GUICreatures extends Screen {
    private final int bookImageHeight = 245;
    private final int bookImageWidth = 390;
    protected int xSize = 176;
    protected int ySize = 166;
    public final int xGui = 390;
    public final int yGui = 320;
    private static final ResourceLocation TEXTURE = new ResourceLocation("creatures:textures/gui/creatures/book.png");

    public GUICreatures() {
        super(new TranslatableComponent("creatures_gui"));
        xSize = 390;
        ySize = 245;
    }

    protected void init() {
        super.init();
    }

    public void render(@NotNull PoseStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        int offLeft = (int) ((this.width - 272) / 2.0F);
        int offTop = (int) ((this.height - 250) / 2.0F);
        int mousePosX = mouseX;
        int mousePosY = mouseY;
        int i = (this.width - this.bookImageWidth) / 2;
        int j = (this.height - this.bookImageHeight) / 2;
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        //this.getMinecraft().getInstance().getTextureManager().bind(TEXTURE);
        blit(matrices, i, j, 0, 0, xSize, ySize, xSize, ySize + 120);
        LivingEntity entity = Creatures.PROXY.getReferencedMob();
        if (entity instanceof TameableFlyingBirdEntity) {
            TameableFlyingBirdEntity bird = (TameableFlyingBirdEntity) entity;
            matrices.pushPose();
            this.font.draw(matrices, ChatFormatting.BOLD + bird.getDisplayName().getString(), offLeft, offTop + 60, 0);
            matrices.popPose();
            matrices.pushPose();
            Component g = new TranslatableComponent("gui.sex", bird.getGenderDisplayName());
            this.font.draw(matrices, g, offLeft, offTop + 80, 0);
            matrices.popPose();
            matrices.pushPose();
            //matrices.scale(0.9f,0.9f, 0.9f);
            Component h = new TranslatableComponent("gui.health",bird.getHealth(), bird.getMaxHealth());
            this.font.draw(matrices, h, offLeft, offTop + 90, 0);
            Component owner;
            if (bird.getOwner() == null) {
                owner = new TranslatableComponent("gui.untamed");
            } else {
                owner = bird.getOwner().getDisplayName();
            }
            Component o = new TranslatableComponent("gui.owner").withStyle(ChatFormatting.BOLD);
            this.font.draw(matrices, o.getString() + " " + owner.getString(), offLeft, offTop + 100, 0);
            //Component species = new TranslatableComponent("gui.species");
            this.font.draw(matrices, bird.getSpeciesName(), offLeft, offTop + 110, 0);
            Component height = new TranslatableComponent("gui.height").withStyle(ChatFormatting.BOLD);
            this.font.draw(matrices, ChatFormatting.BOLD + height.getString() + ChatFormatting.RESET + " " + bird.getHeightString(), offLeft, offTop + 120, 0);
            Component food = new TranslatableComponent("gui.food").withStyle(ChatFormatting.BOLD);
            this.font.draw(matrices, ChatFormatting.BOLD + food.getString() + " ", offLeft, offTop + 140, 0);
            this.itemRenderer.renderGuiItem(bird.getFoodItem(), offLeft + 30, 130 + offTop);
            matrices.popPose();

        } else if (entity instanceof EggEntity) {
            EggEntity bird = (EggEntity) entity;
            matrices.pushPose();
            this.font.draw(matrices, ChatFormatting.BOLD + bird.getDisplayName().getString(), offLeft, offTop + 60, 0);
            matrices.popPose();
            matrices.pushPose();
            //matrices.scale(0.9f,0.9f, 0.9f);
            Component h = new TranslatableComponent("gui.hatchtime");
            this.font.draw(matrices, h.getString() + " " + bird.hatchTime / 1200, offLeft, offTop + 80, 0);

        }
        else {
            CreaturesBirdEntity bird = (CreaturesBirdEntity) entity;
            matrices.pushPose();
            this.font.draw(matrices, ChatFormatting.BOLD + bird.getDisplayName().getString(), offLeft, offTop + 60, 0);
            matrices.popPose();
            matrices.pushPose();
            Component g = new TranslatableComponent("gui.sex");
            this.font.draw(matrices, g, offLeft, offTop + 80, 0);
            matrices.popPose();
            matrices.pushPose();
            //matrices.scale(0.9f,0.9f, 0.9f);
            Component h = new TranslatableComponent("gui.health");
            this.font.draw(matrices, h, offLeft, offTop + 90, 0);
            //Component species = new TranslatableComponent("gui.species");
            this.font.draw(matrices, bird.getSpeciesName(), offLeft, offTop + 100, 0);
            Component height = new TranslatableComponent("gui.height");
            this.font.draw(matrices, ChatFormatting.BOLD + height.getString() + ChatFormatting.RESET + " " + bird.getHeightString(), offLeft, offTop + 110, 0);
            Component food = new TranslatableComponent("gui.food").withStyle(ChatFormatting.BOLD);
            this.font.draw(matrices, ChatFormatting.BOLD + food.getString() + " ", offLeft, offTop + 130, 0);
            this.itemRenderer.renderGuiItem(bird.getFoodItem(), offLeft + 30, 120 + offTop);
            matrices.popPose();
        }
        InventoryScreen.renderEntityInInventory(offLeft + 215, 120 + offTop, 60, (float) (i) - mousePosX, (float) (j + 75 - 50) - mousePosY, entity);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
