package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.blocks.entity.FuelGeneratorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityTypeInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TaczArmors.MODID);

    public static final RegistryObject<BlockEntityType<FuelGeneratorBlockEntity>> FUEL_GENERATOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("fuel_generator_block_entity",
                    () -> BlockEntityType.Builder.of(FuelGeneratorBlockEntity::new, BlockInit.FUEL_GENERATOR.get()).build(null));
}
