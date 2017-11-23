package com.naari3.exampleMod;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RubyGuiContainer extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(ExampleMod.MOD_ID, "textures/gui/gui_texture.png");

    public RubyGuiContainer(RubyContainerTileEntity tileEntity, RubyContainer container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    /*GUIの文字等の描画処理*/
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseZ) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseZ);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    /*GUIが開いている時にゲームの処理を止めるかどうか。*/
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
