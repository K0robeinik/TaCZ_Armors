package com.korobeinik.taczarmors.datagen;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.init.BlockInit;
import com.korobeinik.taczarmors.init.ItemInit;
import com.korobeinik.taczarmors.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DGItemTagProvider extends ItemTagsProvider {
    public DGItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, TaczArmors.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(TagsInit.Items.STORAGE_BLOCKS_STEEL).add(BlockInit.STEEL_BLOCK.get().asItem());
        this.tag(TagsInit.Items.INGOTS_STEEL).add(ItemInit.STEEL_INGOT.get());
    }
}
