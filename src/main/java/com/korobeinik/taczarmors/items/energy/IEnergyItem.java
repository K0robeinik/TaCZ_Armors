package com.korobeinik.taczarmors.items.energy;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;

public interface IEnergyItem {
    default boolean isCharged(ItemStack stack){
        return this.getEnergy(stack) > 0;
    }

    default int getEnergy(ItemStack stack){
        IEnergyStorage energyStorage = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        return energyStorage.getEnergyStored();
    }
    default int getCapacity(ItemStack stack) {
        IEnergyStorage energyStorage = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        return energyStorage.getMaxEnergyStored();
    }
    default void receiveEnergy(ItemStack stack, int amount) {
        IEnergyStorage energyStorage = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        energyStorage.receiveEnergy(amount, false);
    }
    default void extractEnergy(ItemStack stack, int amount) {
        IEnergyStorage energyStorage = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        energyStorage.extractEnergy(amount, false);
    }
}
