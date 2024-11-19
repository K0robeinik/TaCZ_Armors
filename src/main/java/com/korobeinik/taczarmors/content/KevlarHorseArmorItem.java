package com.korobeinik.taczarmors.content;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableHorseArmorItem;

public class KevlarHorseArmorItem extends DyeableHorseArmorItem {
    public KevlarHorseArmorItem(int pProtection, String pIdentifier, Properties pProperties) {
        this(pProtection, new ResourceLocation(TaczArmors.MODID, "textures/entity/horse/armor/horse_armor_" + pIdentifier + ".png"), pProperties);
    }

    public KevlarHorseArmorItem(int pProtection, ResourceLocation pIdentifier, Properties pProperties) {
        super(pProtection, pIdentifier, pProperties);
    }
}
