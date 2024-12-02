package com.korobeinik.taczarmors.blocks;

import com.korobeinik.taczarmors.blocks.entity.FuelGeneratorBlockEntity;
import com.korobeinik.taczarmors.init.BlockEntityTypeInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FuelGeneratorBlock extends AbstractFurnaceBlock {
    public FuelGeneratorBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void openContainer(Level level, @NotNull BlockPos pos, @NotNull Player player) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof FuelGeneratorBlockEntity fuelEntity && player instanceof ServerPlayer serverPlayer){
            NetworkHooks.openScreen(serverPlayer, fuelEntity, pos);
        }
        else {
            throw new IllegalStateException("Bababooy!");
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return BlockEntityTypeInit.FUEL_GENERATOR_BLOCK_ENTITY.get().create(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, BlockEntityTypeInit.FUEL_GENERATOR_BLOCK_ENTITY.get(), (pLevel, pPos, pState, pBlockEntity) -> pBlockEntity.tick(pLevel, pPos, pState));
    }
}
