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
            .comment("What block will be used as overlay")
            .define("block_name", "glass");

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
