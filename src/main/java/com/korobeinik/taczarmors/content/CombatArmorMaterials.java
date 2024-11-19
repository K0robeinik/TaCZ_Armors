package com.korobeinik.taczarmors.content;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class CombatArmorMaterials implements CombatArmorMaterial{
    protected static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    protected static final float[] STEP_HEIGHT_ARRAY = new float[]{0, 0, 0, 1};
    protected static final float[] ONE_ARRAY = new float[]{1, 1, 1, 1};
    protected static final float[] ZERO_ARRAY = new float[]{0, 0, 0, 0};
    protected static final float[] CHESTPLATE_ARRAY = new float[]{0, 1, 0, 0};
    private final String name;
    private final int durability;
    private final int[] damageReduction;
    private final int encantability;
    private final SoundEvent sound;
    private final float toughness;
    private final Ingredient ingredient;
    public float knockbackResistance;
    private final float[] hpBonus;
    private final float[] speed;
    private final float[] jumpHeight;
    private final float stepHeight;
    private final float[] fallHeight;
    private final float[] swimSpeed;
    private final float[] attackDamage;
    private final float[] attackSpeed;
    private final float[] attackKnockback;

    public CombatArmorMaterials(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness) {
        this(name, durability, damageReduction, encantability, sound, ingredient, toughness, ZERO_ARRAY, ZERO_ARRAY, 0F, ZERO_ARRAY, ZERO_ARRAY, 0F, ZERO_ARRAY);
    }

    public CombatArmorMaterials(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness, float[] hpBonus, float[] speed, float knockbackResist) {
        this(name, durability, damageReduction, encantability, sound, ingredient, toughness, hpBonus, speed, knockbackResist, ZERO_ARRAY, ZERO_ARRAY, 0F, ZERO_ARRAY);
    }

    public CombatArmorMaterials(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness, float[] hpBonus, float[] speed, float knockbackResist, float[] swimSpeed, float[] jumpHeight, float stepHeight, float[] fallHeight) {
        this(name, durability, damageReduction, encantability, sound, ingredient, toughness, hpBonus, speed, knockbackResist, ZERO_ARRAY, ZERO_ARRAY, 0F, ZERO_ARRAY, ZERO_ARRAY, ZERO_ARRAY, ZERO_ARRAY);
    }

    public CombatArmorMaterials(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness, float[] hpBonus, float[] speed, float knockbackResist, float[] swimSpeed, float[] jumpHeight, float stepHeight, float[] fallHeight, float[] attackSpeed, float[] attackKnockback, float[] attackDamage) {
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
        this.attackSpeed = attackSpeed;
        this.attackKnockback = attackKnockback;
        this.attackDamage = attackDamage;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) { return MAX_DAMAGE_ARRAY[type.ordinal()] * this.durability; }

    @Override
    public int getDefenseForType(ArmorItem.Type type) { return this.damageReduction[type.ordinal()]; }

    @Override
    public int getEnchantmentValue() { return this.encantability; }

    @Override
    public @NotNull SoundEvent getEquipSound() { return this.sound == null ? SoundEvents.EMPTY : this.sound; }

    @Override
    public @NotNull Ingredient getRepairIngredient() { return this.ingredient == null ? Ingredient.EMPTY : this.ingredient; }

    @Override
    public @NotNull String getName() { return name; }

    @Override
    public float getToughness() { return toughness; }

    @Override
    public float getKnockbackResistance() { return knockbackResistance; }

    @Override
    public float getBonusForType(CombatArmorBonus bonus, ArmorItem.Type type) {
        switch (bonus){
            case SPEED -> {return speed[type.ordinal()];}
            case SWIM_SPEED -> {return swimSpeed[type.ordinal()];}
            case HEALTH -> {return hpBonus[type.ordinal()];}
            case JUMPHEIGHT -> {return jumpHeight[type.ordinal()];}
            case FALLHEIGHT -> {return fallHeight[type.ordinal()];}
            case ATTACK_DAMAGE -> {return attackDamage[type.ordinal()];}
            case ATTACK_SPEED -> {return attackSpeed[type.ordinal()];}
            case ATTACK_KNOCKBACK -> {return attackKnockback[type.ordinal()];}
        }
        return 0;
    }

    public float getBonusForEntity(CombatArmorBonus bonus, LivingEntity entity){
        float sum = 0;
        for (ArmorItem.Type type: ArmorItem.Type.values()) {
            Item item = entity.getItemBySlot(type.getSlot()).getItem();
            if(item instanceof CombatArmorItem){
                sum+=((CombatArmorItem) item).getMaterial().getBonusForType(bonus, type);
            }
        }
        return sum;
    }

    /*@Override
    public int getHpBonus(ArmorItem.Type type){
        return (int) ONE_ARRAY[type.ordinal()] * hpBonus;
    }
    @Override
    public float getSpeed(ArmorItem.Type type){
        return ONE_ARRAY[type.ordinal()] * speed;
    }
    @Override
    public float getSwimSpeed(ArmorItem.Type type){
        return ONE_ARRAY[type.ordinal()] * swimSpeed;
    }
    @Override
    public float getJumpHeight(ArmorItem.Type type) {
        return STEP_HEIGHT_ARRAY[type.ordinal()] * jumpHeight;
    }
    @Override
    public float getStepHeight(ArmorItem.Type type) {
        return STEP_HEIGHT_ARRAY[type.ordinal()] * stepHeight;
    }
    @Override
    public float getFallHeight(ArmorItem.Type type) {
        return STEP_HEIGHT_ARRAY[type.ordinal()] * fallHeight;
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
    }*/
}
