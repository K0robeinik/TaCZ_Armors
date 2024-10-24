package com.korobeinik.taczarmors.content;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.function.Supplier;

public class CombatArmorMaterials implements CombatArmorMaterial{
    protected static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

    protected static final float[] JUMP_HEIGHT_ARRAY = new float[]{0, 0, 1, 0};
    protected static final float[] STEP_HEIGHT_ARRAY = new float[]{0, 0, 0, 1};
    private final String name;
    private final int durability;
    private final int[] damageReduction;
    private final int encantability;
    private final SoundEvent sound;
    private final float toughness;
    private final Ingredient ingredient;
    public float knockbackResistance;
    private final int hpBonus;
    private final float speed;
    private final float swimSpeed;
    private final float jumpHeight;
    private final float stepHeight;
    private final float fallHeight;

    public CombatArmorMaterials(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness) {
        this(name, durability, damageReduction, encantability, sound, ingredient, toughness, 0, 0F, 0F, 0F, 0F, 0F, 0);
    }

    public CombatArmorMaterials(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness, int hpBonus, float speed, float knockbackResist) {
        this(name, durability, damageReduction, encantability, sound, ingredient, toughness, hpBonus, speed, knockbackResist, 0F, 0F, 0F, 0);
    }

    public CombatArmorMaterials(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness, int hpBonus, float speed, float knockbackResist, float swimSpeed, float jumpHeight, float stepHeight, float fallHeight) {
        this.name = name;
        this.durability = durability;
        this.damageReduction = damageReduction;
        this.encantability = encantability;
        this.sound = sound;
        this.ingredient = ingredient;
        this.toughness = toughness;
        this.hpBonus = hpBonus;
        this.speed = speed;
        this.knockbackResistance = knockbackResist;
        this.swimSpeed = swimSpeed;
        this.jumpHeight = jumpHeight;
        this.stepHeight = stepHeight;
        this.fallHeight = fallHeight;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) { return MAX_DAMAGE_ARRAY[type.ordinal()] * this.durability; }

    @Override
    public int getDefenseForType(ArmorItem.Type type) { return this.damageReduction[type.ordinal()]; }

    @Override
    public int getEnchantmentValue() { return this.encantability; }

    @Override
    public SoundEvent getEquipSound() { return this.sound; }

    @Override
    public Ingredient getRepairIngredient() { return this.ingredient == null ? Ingredient.EMPTY : this.ingredient; }

    //public void setRepairMaterial(Ingredient ingredient) { this.ingredient = ingredient; }

    @Override
    public @NotNull String getName() { return name; }

    @Override
    public float getToughness() { return toughness; }

    @Override
    public float getKnockbackResistance() { return knockbackResistance; }
    @Override
    public int getHpBonus(){
        return hpBonus;
    }
    @Override
    public float getSpeed(){
        return speed;
    }
    @Override
    public float getSwimSpeed(){
        return swimSpeed;
    }
    @Override
    public float getJumpHeight(ArmorItem.Type type) {
        return JUMP_HEIGHT_ARRAY[type.ordinal()] * jumpHeight;
    }
    @Override
    public float getStepHeight(ArmorItem.Type type) {
        return STEP_HEIGHT_ARRAY[type.ordinal()] * stepHeight;
    }
    public float getFallHeight(ArmorItem.Type type) {
        return STEP_HEIGHT_ARRAY[type.ordinal()] * fallHeight;
    }
}
