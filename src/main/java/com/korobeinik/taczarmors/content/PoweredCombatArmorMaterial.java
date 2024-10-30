package com.korobeinik.taczarmors.content;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

public class PoweredCombatArmorMaterial extends CombatArmorMaterials implements PoweredArmorMaterial{
    private float[] CHESTPLATE_ARRAY = new float[]{0, 1, 0, 0};
    private final float attackSpeed;
    private final float attackKnockback;
    private final float attackDamage;
    public PoweredCombatArmorMaterial(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness, int hpBonus, float speed, float knockbackResist, float swimSpeed, float jumpHeight, float stepHeight, float fallHeight, float attackSpeed, float attackKnockback, float attackDamage) {
        super(name, durability, damageReduction, encantability, sound, ingredient, toughness, hpBonus, speed, knockbackResist, swimSpeed, jumpHeight, stepHeight, fallHeight);
        this.attackSpeed = attackSpeed;
        this.attackKnockback = attackKnockback;
        this.attackDamage = attackDamage;
    }

    @Override
    public float getAttackSpeed(ArmorItem.Type type) {
        return CHESTPLATE_ARRAY[type.ordinal()] * attackSpeed;
    }

    @Override
    public float getAttackKnockback(ArmorItem.Type type) {
        return CHESTPLATE_ARRAY[type.ordinal()] * attackKnockback;
    }

    @Override
    public float getAttackDamage(ArmorItem.Type type) {
        return CHESTPLATE_ARRAY[type.ordinal()] * attackDamage;
    }
}
