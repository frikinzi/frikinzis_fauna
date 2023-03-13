package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.base.*;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.frikinzi.creatures.entity.egg.CreaturesRoeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

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
        super(new TranslationTextComponent("creatures_gui"));
        xSize = 390;
        ySize = 245;
    }

    protected void init() {
        super.init();
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            int offLeft = (int) ((this.width - 272) / 2.0F);
            int offTop = (int) ((this.height - 250) / 2.0F);
            int mousePosX = mouseX;
            int mousePosY = mouseY;
            int i = (this.width - this.bookImageWidth) / 2;
            int j = (this.height - this.bookImageHeight) / 2;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.getMinecraft().getInstance().getTextureManager().bind(TEXTURE);
            this.blit(matrices, i, j, 0, 0, xSize, ySize, xSize, ySize + 120);
            LivingEntity entity = Creatures.PROXY.getReferencedMob();
            if (entity instanceof TameableBirdBase) {
                TameableBirdBase bird = (TameableBirdBase) entity;
                matrices.pushPose();
                this.font.draw(matrices, bird.getDisplayName(), offLeft, offTop + 60, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent g = new TranslationTextComponent("gui.sex");
                this.font.draw(matrices, g.getString() + " " + bird.getGenderString(), offLeft, offTop + 80, 0);
                matrices.popPose();
                matrices.pushPose();
                //matrices.scale(0.9f,0.9f, 0.9f);
                ITextComponent h = new TranslationTextComponent("gui.health");
                this.font.draw(matrices, h.getString() + " " + bird.getHealth() + "/" + bird.getMaxHealth(), offLeft, offTop + 90, 0);
                ITextComponent owner;
                if (bird.getOwner() == null) {
                    owner = new TranslationTextComponent("gui.untamed");
                } else {
                    owner = bird.getOwner().getDisplayName();
                }
                ITextComponent o = new TranslationTextComponent("gui.owner");
                this.font.draw(matrices, o.getString() + " " + owner.getString(), offLeft, offTop + 100, 0);
                ITextComponent species = new TranslationTextComponent("gui.species");
                this.font.draw(matrices, bird.getSpeciesName(), offLeft, offTop + 110, 0);
                ITextComponent height = new TranslationTextComponent("gui.height");
                this.font.draw(matrices, height.getString() + " " + bird.getHeightString(), offLeft, offTop + 120, 0);
                ITextComponent food = new TranslationTextComponent("gui.food");
                this.font.draw(matrices, food.getString() + " ", offLeft, offTop + 140, 0);
                this.itemRenderer.renderGuiItem(bird.getFoodItem(), offLeft + 30, 130 + offTop);
                matrices.popPose();

            }
            if (entity instanceof TameableWalkingBirdBase) {
                TameableWalkingBirdBase bird = (TameableWalkingBirdBase) entity;
                matrices.pushPose();
                this.font.draw(matrices, bird.getDisplayName(), offLeft, offTop + 60, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent g = new TranslationTextComponent("gui.sex");
                this.font.draw(matrices, g.getString() + " " + bird.getGenderString(), offLeft, offTop + 80, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent h = new TranslationTextComponent("gui.health");
                this.font.draw(matrices, h.getString() + " " + bird.getHealth() + "/" + bird.getMaxHealth(), offLeft, offTop + 90, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent owner;
                if (bird.getOwner() == null) {
                    owner = new TranslationTextComponent("gui.untamed");
                } else {
                    owner = bird.getOwner().getDisplayName();
                }
                ITextComponent o = new TranslationTextComponent("gui.owner");
                this.font.draw(matrices, o.getString() + " " + owner.getString(), offLeft, offTop + 100, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent species = new TranslationTextComponent("gui.species");
                this.font.draw(matrices, bird.getSpeciesName(), offLeft, offTop + 110, 0);
                ITextComponent height = new TranslationTextComponent("gui.height");
                this.font.draw(matrices, height.getString() + " " + bird.getHeightString(), offLeft, offTop + 120, 0);
                ITextComponent food = new TranslationTextComponent("gui.food");
                this.font.draw(matrices, food.getString() + " ", offLeft, offTop + 140, 0);
                this.itemRenderer.renderGuiItem(bird.getFoodItem(), offLeft + 30, 130 + offTop);
                matrices.popPose();
            }
            if (entity instanceof NonTameableBirdBase) {
                NonTameableBirdBase bird = (NonTameableBirdBase) entity;
                matrices.pushPose();
                this.font.draw(matrices, bird.getDisplayName(), offLeft, offTop + 60, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent g = new TranslationTextComponent("gui.sex");
                this.font.draw(matrices, g.getString() + " " + bird.getGenderString(), offLeft, offTop + 80, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent h = new TranslationTextComponent("gui.health");
                this.font.draw(matrices, h.getString() + " " + bird.getHealth() + "/" + bird.getMaxHealth(), offLeft, offTop + 90, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent species = new TranslationTextComponent("gui.species");
                this.font.draw(matrices, bird.getSpeciesName(), offLeft, offTop + 100, 0);
                ITextComponent height = new TranslationTextComponent("gui.height");
                this.font.draw(matrices, height.getString() + " " + bird.getHeightString(), offLeft, offTop + 110, 0);
                ITextComponent food = new TranslationTextComponent("gui.food");
                this.font.draw(matrices, food.getString() + " ", offLeft, offTop + 130, 0);
                //this.font.draw(matrices, bird.getUUID().toString(), offLeft, offTop + 140, 0);
                this.itemRenderer.renderGuiItem(bird.getFoodItem(), offLeft + 30, 120 + offTop);
                matrices.popPose();
            }
            if (entity instanceof NonTameableFlyingBirdBase) {
                NonTameableFlyingBirdBase bird = (NonTameableFlyingBirdBase) entity;
                matrices.pushPose();
                this.font.draw(matrices, bird.getDisplayName(), offLeft, offTop + 60, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent g = new TranslationTextComponent("gui.sex");
                this.font.draw(matrices, g.getString() + " " + bird.getGenderString(), offLeft, offTop + 80, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent h = new TranslationTextComponent("gui.health");
                this.font.draw(matrices, h.getString() + " " + bird.getHealth() + "/" + bird.getMaxHealth(), offLeft, offTop + 90, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent species = new TranslationTextComponent("gui.species");
                this.font.draw(matrices, bird.getSpeciesName(), offLeft, offTop + 100, 0);
                ITextComponent height = new TranslationTextComponent("gui.height");
                this.font.draw(matrices, height.getString() + " " + bird.getHeightString(), offLeft, offTop + 110, 0);
                ITextComponent food = new TranslationTextComponent("gui.food");
                this.font.draw(matrices, food.getString() + " ", offLeft, offTop + 130, 0);
                this.itemRenderer.renderGuiItem(bird.getFoodItem(), offLeft + 30, 120 + offTop);
                matrices.popPose();
            }
            if (entity instanceof FishBase) {
                FishBase bird = (FishBase) entity;
                matrices.pushPose();
                this.font.draw(matrices, bird.getDisplayName(), offLeft, offTop + 60, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent h = new TranslationTextComponent("gui.health");
                this.font.draw(matrices, h.getString() + " " + bird.getHealth() + "/" + bird.getMaxHealth(), offLeft, offTop + 80, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent species = new TranslationTextComponent("gui.species");
                this.font.draw(matrices, species.getString() + " " + bird.getSpeciesName(), offLeft, offTop + 90, 0);
                ITextComponent height = new TranslationTextComponent("gui.height");
                this.font.draw(matrices, height.getString() + " " + bird.getHeightString(), offLeft, offTop + 100, 0);
                ITextComponent food = new TranslationTextComponent("gui.food");
                this.font.draw(matrices, food.getString() + " ", offLeft, offTop + 120, 0);
                this.itemRenderer.renderGuiItem(bird.getDisplayFood(), offLeft + 30, 110 + offTop);
                matrices.popPose();
            }
            if (entity instanceof GroupFishBase) {
                GroupFishBase bird = (GroupFishBase) entity;
                matrices.pushPose();
                this.font.draw(matrices, bird.getDisplayName(), offLeft, offTop + 60, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent h = new TranslationTextComponent("gui.health");
                this.font.draw(matrices, h.getString() + " " + bird.getHealth() + "/" + bird.getMaxHealth(), offLeft, offTop + 80, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent species = new TranslationTextComponent("gui.species");
                this.font.draw(matrices, species.getString() + " " + bird.getSpeciesName(), offLeft, offTop + 90, 0);
                ITextComponent height = new TranslationTextComponent("gui.height");
                this.font.draw(matrices, height.getString() + " " + bird.getHeightString(), offLeft, offTop + 100, 0);
                ITextComponent food = new TranslationTextComponent("gui.food");
                this.font.draw(matrices, food.getString() + " ", offLeft, offTop + 120, 0);
                this.itemRenderer.renderGuiItem(bird.getDisplayFood(), offLeft + 30, 110 + offTop);
                matrices.popPose();
            }
            if (entity instanceof AbstractCrabBase) {
                AbstractCrabBase bird = (AbstractCrabBase) entity;
                matrices.pushPose();
                this.font.draw(matrices, bird.getDisplayName(), offLeft, offTop + 60, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent h = new TranslationTextComponent("gui.health");
                this.font.draw(matrices, h.getString() + " " + bird.getHealth() + "/" + bird.getMaxHealth(), offLeft, offTop + 80, 0);
                matrices.popPose();
                matrices.pushPose();
                ITextComponent species = new TranslationTextComponent("gui.species");
                this.font.draw(matrices, bird.getSpeciesName(), offLeft, offTop + 90, 0);
                ITextComponent height = new TranslationTextComponent("gui.height");
                this.font.draw(matrices, height.getString() + " " + bird.getHeightString(), offLeft, offTop + 100, 0);
                ITextComponent g = new TranslationTextComponent("gui.sex");
                this.font.draw(matrices, g.getString() + " " + bird.getGenderString(), offLeft, offTop + 110, 0);
                ITextComponent food = new TranslationTextComponent("gui.food");
                this.font.draw(matrices, food.getString() + " ", offLeft, offTop + 130, 0);
                this.itemRenderer.renderGuiItem(bird.getFoodItem(), offLeft + 30, 120 + offTop);
                matrices.popPose();
            }  if (entity instanceof CreaturesEggEntity) {
            CreaturesEggEntity bird = (CreaturesEggEntity) entity;
            matrices.pushPose();
            ItemStack item = bird.getEggItem();
            this.font.draw(matrices, bird.getEggItem().getItem().getName(item), offLeft, offTop + 60, 0);
            matrices.popPose();
            matrices.pushPose();
            ITextComponent h = new TranslationTextComponent("gui.hatchtime");
            this.font.draw(matrices, h.getString() + " " + bird.getHatchTime(), offLeft, offTop + 80, 0);
            matrices.popPose();
            matrices.pushPose();
            ITextComponent species = new TranslationTextComponent("gui.species");
            this.font.draw(matrices, bird.getEggItem().getDisplayName(), offLeft, offTop + 90, 0);
            ITextComponent food = new TranslationTextComponent("gui.egg");
            //this.font.draw(matrices, bird.getParentUUID().toString(), offLeft, offTop + 100, 0);
            //this.font.draw(matrices, bird.getSpecies() + " ", offLeft, offTop + 110, 0);
            this.itemRenderer.renderGuiItem(bird.getEggItem(), offLeft + 0, 110 + offTop);
            matrices.popPose();
        }  if (entity instanceof CreaturesRoeEntity) {
            CreaturesRoeEntity bird = (CreaturesRoeEntity) entity;
            matrices.pushPose();
            ITextComponent h = new TranslationTextComponent("gui.hatchtime");
            this.font.draw(matrices, h.getString() + " " + bird.getHatchTime(), offLeft, offTop + 80, 0);
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
