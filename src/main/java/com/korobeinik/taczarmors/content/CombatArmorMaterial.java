package com.korobeinik.taczarmors.content;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public interface CombatArmorMaterial extends ArmorMaterial {
    /*int getHpBonus(ArmorItem.Type type);
    float getSpeed(ArmorItem.Type type);
    float getSwimSpeed(ArmorItem.Type type);
    float getJumpHeight(ArmorItem.Type type);
    float getStepHeight(ArmorItem.Type type);
    float getFallHeight(ArmorItem.Type type);
    float getAttackSpeed(ArmorItem.Type type);
    float getAttackKnockback(ArmorItem.Type type);
    float getAttackDamage(ArmorItem.Type type);*/
    float getBonusForType(CombatArmorBonus bonus, ArmorItem.Type type);
}
