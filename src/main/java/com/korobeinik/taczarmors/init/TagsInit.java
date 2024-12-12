package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TagsInit {
    public static class Blocks {
        //public static final TagKey<Block> STEEL_BLOCKS = forgeTag("storage_blocks/steel");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(TaczArmors.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
