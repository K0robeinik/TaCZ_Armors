package com.korobeinik.taczarmors.content;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableHorseArmorItem;
import net.minecraft.world.item.ItemStack;

public class KevlarHorseArmorItem extends DyeableHorseArmorItem {
    public KevlarHorseArmorItem(int pProtection, String pIdentifier, Properties pProperties) {
        this(pProtection, new ResourceLocation(TaczArmors.MODID, "textures/entity/horse/armor/horse_armor_" + pIdentifier + ".png"), pProperties);
    }

    public KevlarHorseArmorItem(int pProtection, ResourceLocation pIdentifier, Properties pProperties) {
        super(pProtection, pIdentifier, pProperties);
    }

    public int getColor(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : 8421376;
    }

    public static int getItemColor(ItemStack stack, int ignoredTint){
        return ((KevlarHorseArmorItem) stack.getItem()).getColor(stack);
    }
}
