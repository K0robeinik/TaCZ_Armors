package com.korobeinik.taczarmors.datagen.loot;

import com.korobeinik.taczarmors.init.BlockInit;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class DGBlockLoot extends BlockLootSubProvider {
    public DGBlockLoot() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dys(BlockInit.STEEL_BLOCK);
        dys(BlockInit.FUEL_GENERATOR);
        dys(BlockInit.TEST_BENCH);
    }

    private void dys(@NotNull RegistryObject<Block> registryObject){
        this.dropSelf(registryObject.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockInit.BLOCKS.getEntries().stream().flatMap(RegistryObject::stream)::iterator;
    }
}
