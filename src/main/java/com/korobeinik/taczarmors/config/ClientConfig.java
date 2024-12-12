package com.korobeinik.taczarmors.config;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_OVERLAY = BUILDER
            .comment("Whether the helmet overlay should be rendered")
            .define("enable_overlay", true);

    public static final ForgeConfigSpec.ConfigValue<String> OVERLAY_TEXTURE = BUILDER
            .comment("What HUD will be used as overlay")
            .define("block_name", "hud_0");

    public static final ForgeConfigSpec.DoubleValue SHAKE_SENSITIVITY = BUILDER
            .comment("How sensitive should the HUD shake be.\nSet 0 to disable shaking")
            .defineInRange("shake_sensitivity", 1.0D, 0.0D, 1.0D);

    public static final ForgeConfigSpec.DoubleValue SHAKE_STIFFNESS = BUILDER
            .comment("How stiff should the HUD be. The smaller the value, the faster the hud will return to place.")
            .defineInRange("shake_stiffness", 0.9D, 0.01D, 0.99D);

    /*public static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> CUSTOM_COLORS = BUILDER
            .comment("Add custom colors you want to keep in the colors tab")
            .defineListAllowEmpty("custom_colors", List.of(696969), o -> true);*/

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
