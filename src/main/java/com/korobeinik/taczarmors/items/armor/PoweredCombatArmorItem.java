package com.korobeinik.taczarmors.items.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.korobeinik.taczarmors.items.energy.EnergyCapability;
import com.korobeinik.taczarmors.items.energy.IEnergyItem;
import com.korobeinik.taczarmors.items.energy.ItemEnergyStorage;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PoweredCombatArmorItem extends CombatArmorItem implements IEnergyItem {
    public PoweredCombatArmorItem(CombatArmorMaterials armorMaterial, Type type) {
        super(armorMaterial, type);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        int energy = stack.getOrCreateTag().getInt("Energy");
        return new EnergyCapability(new ItemEnergyStorage(stack, 1000000, 1000, 1000, energy));
    }

    @Override
    public void appendMain(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag pIsAdvanced) {
        pStack.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(iEnergyStorage -> list.add(Component.literal("Energy: " + iEnergyStorage.getEnergyStored() + "/ " + iEnergyStorage.getMaxEnergyStored()).withStyle(ChatFormatting.AQUA)));
        super.appendMain(pStack, pLevel, list, pIsAdvanced);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return isCharged(stack) ? super.getAttributeModifiers(slot, stack) : ImmutableMultimap.of();
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (player.isShiftKeyDown() && slotIndex == type.getSlot().getIndex(36)) {
            tryConsumeEnergy(stack, 1000);
        }
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }


    @Override
    public boolean tryConsumeEnergy(@NotNull ItemStack stack, int amount){
        if (stack.getItem() instanceof PoweredCombatArmorItem poweredItem) {
            if (poweredItem.isCharged(stack)) {
                poweredItem.extractEnergy(stack, amount);
                return true;
            }
            return false;
        }
        return super.tryConsumeEnergy(stack, amount);
    }

//    public static boolean consumeForAttribute(Attribute attribute, LivingEntity entity, int amount) {
//        boolean flag = false;
//        if (entity instanceof Player player) {
//            Iterable<ItemStack> armors = player.getArmorSlots();
//            for (ItemStack stack : armors) {
//                if (!stack.isEmpty() && stack.getItem() instanceof CombatArmorItem poweredItem && stack.getAttributeModifiers(poweredItem.getEquipmentSlot()).containsKey(attribute)) {
//                    flag = flag || tryConsumeEnergy(stack, amount);
//                }
//            }
//            return flag;
//        }
//        return true;
//    }

    public static boolean consumeEnergy(LivingEntity entity, int amount) {
        boolean flag = false;
        if (entity instanceof Player player) {
            Iterable<ItemStack> armors = player.getArmorSlots();
            for (ItemStack stack : armors) {
                if (!stack.isEmpty() && stack.getItem() instanceof CombatArmorItem armorItem) {
                    flag = flag || armorItem.tryConsumeEnergy(stack, amount);
                }
            }
            return flag;
        }
        return true;
    }

    /*@Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }*/
}
