package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsInit {
    public static class Blocks {
        public static final TagKey<Block> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(TaczArmors.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
    public static class Items {
        public static final TagKey<Item> STORAGE_BLOCKS_STEEL = forgeTag("storage_blocks/steel");
        public static final TagKey<Item> INGOTS_STEEL = forgeTag("ingots/steel");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(TaczArmors.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
