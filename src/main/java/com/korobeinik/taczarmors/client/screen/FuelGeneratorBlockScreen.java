package com.korobeinik.taczarmors.client.screen;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.client.menu.FuelGeneratorBlockMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class FuelGeneratorBlockScreen extends AbstractContainerScreen<FuelGeneratorBlockMenu> {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(TaczArmors.MODID, "textures/gui/generator.png");
    private final int imageWidth, imageHeight;

    public FuelGeneratorBlockScreen(FuelGeneratorBlockMenu menu, Inventory playerInventory, Component pTitle) {
        super(menu, playerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        if (this.menu.isBurning()) {
            renderFire(pGuiGraphics);
        }
        if (this.menu.getEnergyStored()>0);{
            renderEnergy(pGuiGraphics);
        }

        int energyStored = this.menu.getEnergy();
        int maxEnergy = this.menu.getMaxEnergy();

        Component text = Component.literal("Energy: " + energyStored + " / " + maxEnergy);
        if(isHovering(94, 16, 18, 54, pMouseX, pMouseY)) {
            pGuiGraphics.renderTooltip(this.font, text, pMouseX, pMouseY);
        }
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        renderBackground(pGuiGraphics);
        pGuiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    protected void renderFire(@NotNull GuiGraphics pGuiGraphics){
        int k = this.menu.getBurningTime();
        pGuiGraphics.blit(BACKGROUND, this.leftPos + 66, this.topPos + 36 + 12 - k, 176, 12 - k, 14, k + 1);
    }

    protected void renderEnergy(@NotNull GuiGraphics pGuiGraphics){
        int k = this.menu.getEnergyStored();
        pGuiGraphics.blit(BACKGROUND, this.leftPos + 94, this.topPos + 16 + 54 - k, 176, 68 - k, 18, k + 1);
    }
}
