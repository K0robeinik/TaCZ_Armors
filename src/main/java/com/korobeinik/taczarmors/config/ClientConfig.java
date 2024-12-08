package com.korobeinik.taczarmors.config;

import com.electronwill.nightconfig.core.Config;
import com.korobeinik.taczarmors.TaczArmors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_OVERLAY = BUILDER
            .comment("Whether the helmet overlay should be rendered")
            .define("enable_overlay", true);

    public static final ForgeConfigSpec.ConfigValue<String> OVERLAY_TEXTURE = BUILDER
            .comment("What block will be used as overlay")
            .define("block_name", "glass");

    /*public static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> CUSTOM_COLORS = BUILDER
            .comment("Add custom colors you want to keep in the colors tab")
            .defineListAllowEmpty("custom_colors", List.of(696969), o -> true);*/

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
