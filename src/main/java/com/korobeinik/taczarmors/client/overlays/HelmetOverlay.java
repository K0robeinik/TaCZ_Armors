package com.korobeinik.taczarmors.client.overlays;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.config.ClientConfig;
import com.korobeinik.taczarmors.items.armor.CombatArmorItem;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.Arrays;

public class HelmetOverlay {
    private static long lastTick = -1;
    private static double diffX = 0;
    private static double diffY = 0;
    private static float xHeadRot = 0;
    private static float yHeadRot = 0;
    private static final int angle = 10;
    public static final IGuiOverlay HUD_HELMET = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        if (ClientConfig.ENABLE_OVERLAY.get() && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            LocalPlayer player = Minecraft.getInstance().player;
            assert player != null;
            ItemStack itemstack = player.getInventory().getArmor(3);
            if (itemstack.getItem() instanceof CombatArmorItem) {
                String loc = String.format("textures/misc/overlay/%s.png", ClientConfig.OVERLAY_TEXTURE.get());
                ResourceLocation HELMET_HUD = new ResourceLocation(TaczArmors.MODID, loc);
                gui.setupOverlayRenderState(true, false);
                double intensity = ClientConfig.SHAKE_INTENSITY.get();
                if(intensity != 0.0D) {
                    assert Minecraft.getInstance().level != null;
                    //if paused
                    if (lastTick == -1 || Minecraft.getInstance().level.getGameTime() - lastTick > 1) {
                        xHeadRot = player.getXRot();
                        yHeadRot = player.yHeadRot;
                    }
                    lastTick = Minecraft.getInstance().level.getGameTime();
                    float changedX = player.getYHeadRot() - yHeadRot;
                    float changedY = player.getXRot() - xHeadRot;
                    diffX = Mth.clamp(diffX + changedX, -angle, angle)*intensity;
                    diffY = Mth.clamp(diffY + changedY, -angle, angle)*intensity;
                    xHeadRot = player.getXRot();
                    yHeadRot = player.yHeadRotO;
                    /*guiGraphics.drawString(gui.getFont(), "Angles Per Tick: " + diffX, 8, 8, 0xFFFFFF);
                    guiGraphics.drawString(gui.getFont(), "Radians Per Tick: " + Mth.DEG_TO_RAD*diffX, 8, 16, 0xFFFFFF);*/
                }
                blitHud(guiGraphics, HELMET_HUD, (int) diffX, (int) diffY, screenWidth, screenHeight);
                diffX = division(diffX);
                diffY = division(diffY);
            }
        }
    };

    private static void blitHud(GuiGraphics guiGraphics, ResourceLocation hud, int x, int y, int screenWidth, int screenHeight){
        guiGraphics.blit(hud, -x - HelmetOverlay.angle, -y - HelmetOverlay.angle, -90, 0, 0, screenWidth + 2* angle, screenHeight + 2* angle, screenWidth + 2* angle, screenHeight + 2* angle);
    }

    private static double division(double divide){
        return Math.abs(divide) < 0.05D ? 0: divide / 1.1D;
    }

    private static float powerLerp(float delta, float start){
        float sign = Mth.sign(delta);
        return Mth.lerp(Mth.sign(delta)-delta/start, Mth.sign(delta)*start, 0);
    }
}
