package com.korobeinik.taczarmors.client.overlays;

import com.korobeinik.taczarmors.config.ClientConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HelmetOverlay {
    //private static final ResourceLocation HELMET_HUD = new ResourceLocation("textures/block/glass.png");

    public static final IGuiOverlay HUD_HELMET = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        if (ClientConfig.ENABLE_OVERLAY.get()) {
            String loc = String.format("textures/block/%s.png", ClientConfig.OVERLAY_TEXTURE.get());
            ResourceLocation HELMET_HUD = new ResourceLocation(loc);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.setShaderTexture(0, HELMET_HUD);
            guiGraphics.blit(HELMET_HUD, 0, 0, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);
        }
    };
}
