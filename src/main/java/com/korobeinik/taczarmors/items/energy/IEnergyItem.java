package com.korobeinik.taczarmors.items.energy;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

public interface IEnergyItem {
    public default boolean isCharged(ItemStack stack){
        return this.getEnergy(stack) > 0;
    }

    private @NotNull IEnergyStorage getEnergyStorage(@NotNull ItemStack stack) {
        return (IEnergyStorage) stack.getCapability(ForgeCapabilities.ENERGY);
    }

    int getEnergy(ItemStack stack);
    int getCapacity(ItemStack stack);
    void receiveEnergy(ItemStack stack, int amount);
    void extractEnergy(ItemStack stack, int amount);
}
