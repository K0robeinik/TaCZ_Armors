package com.korobeinik.taczarmors.items.armor;

import com.korobeinik.taczarmors.init.ArmorMaterialInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GrassColor;
import org.jetbrains.annotations.NotNull;

public class GhillieArmor extends VariedCombatArmorItem{
    private static final String[] ghillieVariants = {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak"};

    //private static final int[] ghillieDefaultColors = new int[]{1};
    public GhillieArmor(Type type) {
        super(ArmorMaterialInit.GHILLIE, type, ghillieVariants);
    }

    @Override
    public int getColor(@NotNull ItemStack pStack) {
        return hasCustomColor(pStack) ? super.getColor(pStack) : getColorFromVariant(pStack);
    }

    private static int getColorFromVariant(ItemStack stack) {
        return switch (VariedCombatArmorItem.getCurrentVariant(stack)){
            case 0, 5 -> GrassColor.get(0.7D, 0.8D); //oaks
            case 1 -> GrassColor.get(0.25D, 0.8D); //spruce
            case 2 -> GrassColor.get(0.6D, 0.6D); //birch
            case 3 -> GrassColor.get(0.95D, 0.9D); //jungle
            case 4 -> GrassColor.get(1.0D, 0.0D); //acacia
            default -> 0xFF00FF;
        };
    }
}
