package com.korobeinik.taczarmors.client.overlays;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.config.ClientConfig;
import com.korobeinik.taczarmors.items.armor.CombatArmorItem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HelmetOverlay {
    private static long lastTick = -1;
    private static double diffX = 0;
    private static double diffY = 0;
    private static float xHeadRot = 0;
    private static float yHeadRot = 0;
    private static final int angle = 45;
    private static final int offset = 32;
    public static final IGuiOverlay HUD_HELMET = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        if (ClientConfig.ENABLE_OVERLAY.get() && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer player = minecraft.player;
            Camera camera = minecraft.gameRenderer.getMainCamera();
            assert player != null;
            ItemStack itemstack = player.getInventory().getArmor(3);
            if (itemstack.getItem() instanceof CombatArmorItem) {
                String loc = String.format("textures/misc/overlay/%s.png", ClientConfig.OVERLAY_TEXTURE.get());
                ResourceLocation HELMET_HUD = new ResourceLocation(TaczArmors.MODID, loc);
                gui.setupOverlayRenderState(true, false);
                double intensity = ClientConfig.SHAKE_SENSITIVITY.get();

                if(intensity != 0.0D) {
                    assert minecraft.level != null;
                    //if paused
                    if (lastTick == -1 || minecraft.level.getGameTime() - lastTick > 1) {
                        xHeadRot = camera.getXRot();
                        yHeadRot = camera.getYRot();
                    }
                    lastTick = minecraft.level.getGameTime();
                    float changedX = camera.getYRot() - yHeadRot;
                    float changedY = camera.getXRot() - xHeadRot;
                    diffX = Mth.clampedMap(diffX + changedX, -angle, angle, -offset, offset)*intensity;
                    diffY = Mth.clampedMap(diffY + changedY, -angle, angle, -offset, offset)*intensity;
                    xHeadRot = camera.getXRot();
                    yHeadRot = camera.getYRot();
                    /*guiGraphics.drawString(gui.getFont(), "Angles Per Tick: " + diffX, 8, 8, 0xFFFFFF);
                    guiGraphics.drawString(gui.getFont(), "Radians Per Tick: " + Mth.DEG_TO_RAD*diffX, 8, 16, 0xFFFFFF);*/
                }

                blitHud(guiGraphics, HELMET_HUD, (int) diffX, (int) diffY, screenWidth, screenHeight);
                diffX = division(diffX);
                diffY = division(diffY);
            }
        }
    };

    private static void blitHud(GuiGraphics guiGraphics, ResourceLocation hud, int x, int y, int screenWidth, int screenHeight ){
        guiGraphics.blit(hud, -x - offset, -y - offset, -90, 0, 0, screenWidth + 2* offset, screenHeight + 2* offset, screenWidth + 2* offset, screenHeight + 2* offset);
    }

    private static double division(double divide) {
        return Math.abs(divide) < 0.05D ? 0: divide * ClientConfig.SHAKE_SENSITIVITY.get();
    }
}
