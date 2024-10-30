package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.blocks.ArmorWorkbenchBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TaczArmors.MODID);

    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> TEST_BENCH = registerBlock("test_bench", () -> new ArmorWorkbenchBlock(BlockBehaviour.Properties.copy(Blocks.SMITHING_TABLE)));
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, CreativeTabInit.MATERIAL_TAB_ITEMS);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, List<Supplier<? extends ItemLike>> list) {
        return CreativeTabInit.addToTab(ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties())), list);
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
