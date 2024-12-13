package com.korobeinik.taczarmors.datagen;

import com.korobeinik.taczarmors.datagen.loot.DGBlockLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;

import java.util.List;
import java.util.Set;

public class DGLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(DGBlockLoot::new, LootContextParamSet.builder().build())
        ));
    }
}
