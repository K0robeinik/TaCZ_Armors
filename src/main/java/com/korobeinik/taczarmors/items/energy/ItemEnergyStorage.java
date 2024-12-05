package com.korobeinik.taczarmors.items.energy;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.energy.EnergyStorage;

public class ItemEnergyStorage extends EnergyStorage {
    public static final String ENERGY_TAG = "Energy";
    private final ItemStack itemStack;public ItemEnergyStorage(ItemStack itemStack, int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
        itemStack.getOrCreateTag().putInt(ENERGY_TAG, energy);
        this.itemStack = itemStack;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extracted = super.extractEnergy(maxExtract, simulate);
        if(!simulate) itemStack.getOrCreateTag().putInt(ENERGY_TAG, energy);
        return extracted;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int received = super.receiveEnergy(maxReceive, simulate);
        if(!simulate) itemStack.getOrCreateTag().putInt(ENERGY_TAG, energy);
        return received;
    }
}
