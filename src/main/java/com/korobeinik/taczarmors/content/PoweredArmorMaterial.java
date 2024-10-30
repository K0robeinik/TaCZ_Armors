package com.korobeinik.taczarmors.content;

import net.minecraft.world.item.ArmorItem;

public interface PoweredArmorMaterial extends CombatArmorMaterial{
    float getAttackSpeed(ArmorItem.Type type);
    float getAttackKnockback(ArmorItem.Type type);
    float getAttackDamage(ArmorItem.Type type);
}
