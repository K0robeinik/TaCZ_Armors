package com.korobeinik.taczarmors.items.energy;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnergyCapability implements ICapabilityProvider {
    private final ItemEnergyStorage energyStorage;
    private final LazyOptional<ItemEnergyStorage> lazyEnergy;

    public EnergyCapability(ItemEnergyStorage energyStorage) {
        this.energyStorage = energyStorage;
        lazyEnergy = LazyOptional.of(() -> this.energyStorage);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ForgeCapabilities.ENERGY ? lazyEnergy.cast() : LazyOptional.empty();
    }
}
