package com.korobeinik.taczarmors.items.energy;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnergyItem extends Item implements IEnergyItem{
    public EnergyItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        int energy = stack.getOrCreateTag().getInt("Energy");
        return new EnergyCapability(new ItemEnergyStorage(stack, 10000, 1000, 1000, energy));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        ItemStack heldItem = pContext.getItemInHand();
        if (!pContext.getLevel().isClientSide() && pContext.getPlayer() != null){
            if(heldItem.getItem() instanceof EnergyItem) {
                this.extractEnergy(heldItem, 500);
                pContext.getPlayer().sendSystemMessage(Component.literal("Position: " + pContext.getClickedPos() + ", Energy Remaining: " + getEnergy(heldItem)));
            }
        }
        return super.useOn(pContext);
    }

    public boolean isCharged(ItemStack stack){
        return this.getEnergy(stack) > 0;
    }

    @Override
    public int getEnergy(ItemStack stack){
        IEnergyStorage energyStorage = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getCapacity(ItemStack stack){
        IEnergyStorage energyStorage = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public void receiveEnergy(ItemStack stack, int amount) {
        IEnergyStorage energyStorage = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        energyStorage.receiveEnergy(amount, false);
    }

    @Override
    public void extractEnergy(ItemStack stack, int amount){
        IEnergyStorage energyStorage = stack.getCapability(ForgeCapabilities.ENERGY, null).orElse(null);
        energyStorage.extractEnergy(amount, false);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag pIsAdvanced) {
        pStack.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(iEnergyStorage -> list.add(Component.literal("Energy: " + iEnergyStorage.getEnergyStored() + "/ " + iEnergyStorage.getMaxEnergyStored()).withStyle(ChatFormatting.AQUA)));
        super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
    }
}
