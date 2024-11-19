package com.korobeinik.taczarmors.config;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.IntValue SPAWN_WITH_ARMOR_CHANCE = BUILDER
            .comment("The chances of zombies and skeletons to spawn with armors from this mod. 0 = Disables spawning with armor.")
            .defineInRange("spawnWithArmorChance", 10, 0, 100);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
