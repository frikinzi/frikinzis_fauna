package com.frikinzi.creatures.client.gui;

import com.frikinzi.creatures.Creatures;
import com.frikinzi.creatures.entity.base.*;
import com.frikinzi.creatures.entity.egg.CreaturesEggEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.ChangePageButton;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class GUIBirdGuide extends Screen {
    private final int bookImageHeight = 245;
    private final int bookImageWidth = 390;
    protected int xSize = 176;
    protected int ySize = 166;
    public final int xGui = 390;
    public final int yGui = 320;
    public final CompoundNBT nbt;
    public ChangePageButton previousPage;
    public ChangePageButton nextPage;
    public Set<String> indexPages;
    public String[] pages;
    public int indexPagesTotal = 1;
    private int currPage = 0;

    private static final ResourceLocation TEXTURE = new ResourceLocation("creatures:textures/gui/creatures/book.png");

    public GUIBirdGuide(CompoundNBT nbt) {
        super(new TranslationTextComponent("creatures_gui"));
        xSize = 390;
        ySize = 245;
        this.nbt = nbt;
        this.indexPagesTotal = this.nbt.getAllKeys().size();
        this.indexPages = this.nbt.getAllKeys();
        this.pages = this.indexPages.toArray(new String[this.indexPages.size()]);
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

            InventoryScreen.renderEntityInInventory(offLeft + 215, 120 + offTop, 60, (float) (i) - mousePosX, (float) (j + 75 - 50) - mousePosY, entity);
            super.render(matrices, mouseX, mouseY, delta);

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
