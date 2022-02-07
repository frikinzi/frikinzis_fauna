package com.creatures.afrikinzi.gui;

import com.creatures.afrikinzi.Creatures;
import com.creatures.afrikinzi.entity.AbstractCreaturesNonTameable;
import com.creatures.afrikinzi.entity.AbstractCreaturesTameable;
import com.creatures.afrikinzi.entity.FishBase;
import com.creatures.afrikinzi.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUICreatures extends GuiScreen {
    private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation(Reference.MOD_ID,"textures/gui/book.png");
    private final int bookImageHeight = 320;
    private final int bookImageWidth = 390;
    protected int xSize = 176;
    protected int ySize = 166;
    private String renderText = "";
    protected int guiLeft;
    protected int guiTop;
    public final int xGui = 390;
    public final int yGui = 320;

    public GUICreatures()
    {
        super();
        xSize = 390;
        ySize = 245;
    }

    @Override
    public void initGui() {
        buttonList.clear();
        int offLeft = (int)((this.width - 292) / 2.0F);
        int offTop = (int)((this.height - 250) / 2.0F);
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        int centerX = (this.width - this.xGui) / 2;
        int centerY = (this.height - this.yGui) / 2;
        super.initGui();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (!this.mc.player.isEntityAlive() || this.mc.player.isDead) {
            this.mc.player.closeScreen();
        }
        renderText = "";
    }

    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_) {
        int offLeft = (int)((this.width - 292) / 2.0F);
        int offTop = (int)((this.height - 250) / 2.0F);
        int mousePosX = parWidth;
        int mousePosY = parHeight;

        if (mc != null && mc.world != null) {
            this.drawDefaultBackground();
        } else {
            return;
        }
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawGuiContainerBackgroundLayer(p_73863_3_, parWidth, parHeight);
        int i = (this.width - this.bookImageWidth) / 2;
        int j = (this.height - this.bookImageHeight) / 2;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawEntityOnScreen(offLeft + 247, 120 + offTop, 70, (float)(i + 51) - mousePosX, (float)(j + 75 - 50) - mousePosY, (EntityLivingBase) Creatures.CREATURES_OBJECT);
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        super.drawScreen(parWidth, parHeight, p_73863_3_);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) k, (float) l, 0.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableRescaleNormal();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        this.drawGuiContainerForegroundLayer(parWidth, parHeight);
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();

    }


    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        ent.onGround=true;
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(15, 0.0F, 1.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 100.0F)) * 90.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        drawModalRectWithCustomSizedTexture(k, l, 0, 0, this.xSize, this.ySize, (390.625F), (390.625F));
        GlStateManager.pushMatrix();
        GlStateManager.popMatrix();
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        if (Creatures.CREATURES_OBJECT instanceof EntityLivingBase) {
            renderFirstPage((EntityLivingBase) Creatures.CREATURES_OBJECT);
        }
    }

    public void renderFirstPage(Entity entity) {
        int wordLength = 70;
        if (entity instanceof AbstractCreaturesTameable) {
            AbstractCreaturesTameable bird = (AbstractCreaturesTameable) entity;
            GlStateManager.pushMatrix();
            String s = I18n.format(bird.getName());
            GlStateManager.scale(1.5F, 1.5F, 1.5F);
            printStringXY(I18n.format(bird.getName()), (-this.fontRenderer.getStringWidth(s) / 2) + 65, 60, 66, 48, 36);
            GlStateManager.popMatrix();
            {
                String s1 = bird.getGenderString();
                printStringXY(s1, wordLength / 2, 110, 157, 126, 103);
            }
            {
                String s1 = I18n.format("gui.health") + " " + Math.min(Math.round(bird.getHealth()), bird.getMaxHealth()) + "/" + bird.getMaxHealth();
                printStringXY(s1, wordLength / 2, 120, 157, 126, 103);
            }
            {
                String s1 = I18n.format("gui.species") + " " + bird.getSpeciesName();
                printStringXY(s1, wordLength / 2, 130, 157, 126, 103);
            }
            {
                String name = bird.getOwner() == null ? "" : bird.getOwner().getName();
                String s1 = I18n.format("gui.untamed");
                String s2 = I18n.format("gui.owner") + " " + name;
                printStringXY(!name.equals("") ? s2 : s1, wordLength / 2, 140, 157, 126, 103);
            }
        }
        if (entity instanceof AbstractCreaturesNonTameable) {
            AbstractCreaturesNonTameable bird = (AbstractCreaturesNonTameable) entity;
            GlStateManager.pushMatrix();
            String s = I18n.format(bird.getName());
            GlStateManager.scale(1.5F, 1.5F, 1.5F);
            printStringXY(I18n.format(bird.getName()), (-this.fontRenderer.getStringWidth(s) / 2) + 65, 60, 66, 48, 36);
            GlStateManager.popMatrix();
            {
                String s1 = bird.getGenderString();
                printStringXY(s1, wordLength / 2, 110, 157, 126, 103);
            }
            {
                String s1 = I18n.format("gui.health") + " " + Math.min(Math.round(bird.getHealth()), bird.getMaxHealth()) + "/" + bird.getMaxHealth();
                printStringXY(s1, wordLength / 2, 120, 157, 126, 103);
            }
            {
                String s1 = I18n.format("gui.species") + " " + bird.getSpeciesName();
                printStringXY(s1, wordLength / 2, 130, 157, 126, 103);
            }
        }
        if (entity instanceof FishBase) {
            FishBase fish = (FishBase) entity;
            GlStateManager.pushMatrix();
            String s = I18n.format(fish.getName());
            GlStateManager.scale(1.5F, 1.5F, 1.5F);
            printStringXY(I18n.format(fish.getName()), (-this.fontRenderer.getStringWidth(s) / 2) + 65, 60, 66, 48, 36);
            GlStateManager.popMatrix();
            {
                String s1 = fish.getGenderString();
                printStringXY(s1, wordLength / 2, 110, 157, 126, 103);
            }
            {
                String s1 = I18n.format("gui.health") + " " + Math.min(Math.round(fish.getHealth()), fish.getMaxHealth()) + "/" + fish.getMaxHealth();
                printStringXY(s1, wordLength / 2, 120, 157, 126, 103);
            }
            {
                String s1 = I18n.format("gui.species") + " " + fish.getSpeciesName();
                printStringXY(s1, wordLength / 2, 130, 157, 126, 103);
            }
        }
    }

    public void printStringXY(String str0, int x0, int y0, int r, int g, int b) {
        int col = (r << 16) | (g << 8) | b;
        this.fontRenderer.drawString(str0, x0, y0, col);
    }

}
