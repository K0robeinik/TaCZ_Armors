package com.korobeinik.taczarmors.datagen;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.blocks.FuelGeneratorBlock;
import com.korobeinik.taczarmors.init.BlockInit;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class DGBlockStateProvider extends BlockStateProvider {
    public DGBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TaczArmors.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWItem(BlockInit.STEEL_BLOCK);
        generatorBS(BlockInit.FUEL_GENERATOR);
    }

    private void blockWItem(@NotNull RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void generatorBS(@NotNull RegistryObject<Block> blockRegistryObject){
        String name = "block/fuel_generator_front";
        generatorBS(
                blockRegistryObject.get(),
                models().orientable(
                        "fuel_generator",
                        modLoc("block/machine"),
                        blockTexture(blockRegistryObject.get()),
                        modLoc("block/machine")
                ),
                models().withExistingParent("fuel_generator_on", modLoc("fuel_generator"))
                        .texture("front", modLoc(name + "_on"))
        );
    }

    private void generatorBS(Block block, ModelFile off, ModelFile on) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(FuelGeneratorBlock.FACING);
            boolean lit = state.getValue(FuelGeneratorBlock.LIT);
            int yRot = (int) facing.getOpposite().toYRot();
            boolean uvLock = yRot == 0;
            return ConfiguredModel.builder()
                    .modelFile(lit ? on : off)
                    .rotationY(yRot)
                    .uvLock(!uvLock)
                    .build();
        });
        simpleBlockItem(block, off);
    }
}
