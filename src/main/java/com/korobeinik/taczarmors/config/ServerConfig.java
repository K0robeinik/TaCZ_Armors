package com.korobeinik.taczarmors.config;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ServerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.IntValue SPAWN_WITH_ARMOR_CHANCE = BUILDER
            .comment("The chances of zombies and skeletons to spawn with armors from this mod. 0 = Disables spawning with armor.")
            .defineInRange("spawnWithArmorChance", 10, 0, 100);

    public static final ForgeConfigSpec.BooleanValue SPAWN_ONLY_IN_HARD = BUILDER
            .comment("Whether should zombies and skeletons spawn with modded armors only in hard difficulty.")
            .define("spawnOnlyInHard", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
