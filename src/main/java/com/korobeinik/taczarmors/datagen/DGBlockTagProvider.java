package com.korobeinik.taczarmors.datagen;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.init.BlockInit;
import com.korobeinik.taczarmors.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DGBlockTagProvider extends BlockTagsProvider {
    public DGBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TaczArmors.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockInit.STEEL_BLOCK.get(),
                BlockInit.FUEL_GENERATOR.get(),
                BlockInit.TEST_BENCH.get()
        );
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
                BlockInit.STEEL_BLOCK.get(),
                BlockInit.FUEL_GENERATOR.get(),
                BlockInit.TEST_BENCH.get()
        );
        this.tag(TagsInit.Blocks.STORAGE_BLOCKS_STEEL).add(
                BlockInit.STEEL_BLOCK.get()
        );
    }
}
