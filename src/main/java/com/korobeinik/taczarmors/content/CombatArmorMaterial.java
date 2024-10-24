package com.korobeinik.taczarmors.content;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public interface CombatArmorMaterial extends ArmorMaterial {
    int getHpBonus();
    float getSpeed();
    float getSwimSpeed();
    float getJumpHeight(ArmorItem.Type type);
    float getStepHeight(ArmorItem.Type type);
    float getFallHeight(ArmorItem.Type type);
}
