package com.korobeinik.taczarmors.content;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CombatArmorMaterials implements ArmorMaterial {
    protected static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durability;
    private final int[] damageReduction;
    private final int encantability;
    private final SoundEvent sound;
    private final float toughness;
    private final Ingredient ingredient;
    public float knockbackResistance;
    private final float[] speed;
    private final float[] swimSpeed;
    private final float[] hpBonus;
    private final float[] jumpHeight;
    private final float[] fallHeight;
    private final float[] attackDamage;
    private final float[] attackSpeed;
    private final float[] attackKnockback;
    private final ArrayList<CombatArmorAbility> abilityList;

    public CombatArmorMaterials(Builder builder) {
        this.name = builder.name;
        this.durability = builder.durability;
        this.damageReduction = builder.damageReduce;
        this.encantability = builder.encantability;
        this.sound = builder.sound;
        this.ingredient = builder.ingredient;
        this.toughness = builder.toughness;
        this.knockbackResistance = builder.knockbackResist;
        this.speed = builder.speed;
        this.swimSpeed = builder.swimSpeed;
        this.hpBonus = builder.hpBonus;
        this.jumpHeight = builder.jumpHeight;
        this.fallHeight = builder.fallHeight;
        this.attackDamage = builder.attackDamage;
        this.attackSpeed = builder.attackSpeed;
        this.attackKnockback = builder.attackKnockback;
        this.abilityList = builder.abilityList;
    }


    public static Builder builder(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness) {
        return builder(name, durability, damageReduction, encantability, sound, ingredient, toughness, 0.0F);
    }

    /*public static Builder builder(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness, float knockbackResist) {
        return builder(name, durability, damageReduction, encantability, sound, ingredient, toughness, knockbackResist, null);
    }*/

    public static Builder builder(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness, float knockbackResist) {
        return new Builder(name, durability, damageReduction, encantability, sound, ingredient, toughness, knockbackResist);
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

    public float getBonusForType(CombatArmorBonus bonus, ArmorItem.Type type) {
        switch (bonus){
            case SPEED -> {return speed[type.ordinal()];}
            case SWIM_SPEED -> {return swimSpeed == null ? 0 : swimSpeed[type.ordinal()];}
            case HEALTH -> {return hpBonus == null ? 0 : hpBonus[type.ordinal()];}
            case JUMPHEIGHT -> {return jumpHeight == null ? 0 : jumpHeight[type.ordinal()];}
            case FALLHEIGHT -> {return fallHeight == null ? 0 : fallHeight[type.ordinal()];}
            case ATTACK_DAMAGE -> {return attackDamage == null ? 0 : attackDamage[type.ordinal()];}
            case ATTACK_SPEED -> {return attackSpeed == null ? 0 : attackSpeed[type.ordinal()];}
            case ATTACK_KNOCKBACK -> {return attackKnockback == null ? 0 : attackKnockback[type.ordinal()];}
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

    public boolean hasAbility(CombatArmorAbility ability, LivingEntity entity){
        if (abilityList == null) return false;
        for (ArmorItem.Type type: ArmorItem.Type.values()) {
            Item item = entity.getItemBySlot(type.getSlot()).getItem();
            if(item instanceof CombatArmorItem && abilityList.contains(ability)){
                return true;
            }
        }
        return false;
    }

    public static class Builder {
        private final String name;
        private final int durability;
        private final int[] damageReduce;
        private final int encantability;
        private final SoundEvent sound;
        private final Ingredient ingredient;
        private final float toughness;
        private final float knockbackResist;
        private final float[] ZERO_ARRAY = new float[]{0, 0, 0, 0};
        private float[] speed = ZERO_ARRAY;
        private float[] swimSpeed = ZERO_ARRAY;
        private float[] hpBonus = ZERO_ARRAY;
        private float[] jumpHeight = ZERO_ARRAY;
        private float[] fallHeight = ZERO_ARRAY;
        private float[] attackDamage = ZERO_ARRAY;
        private float[] attackSpeed = ZERO_ARRAY;
        private float[] attackKnockback = ZERO_ARRAY;
        private ArrayList<CombatArmorAbility> abilityList = new ArrayList<>();

        private Builder(String name, int durability, int[] damageReduction, int encantability, SoundEvent sound, Ingredient ingredient, float toughness, float knockbackResist){
            this.name = name;
            this.durability = durability;
            this.damageReduce = damageReduction;
            this.encantability = encantability;
            this.sound = sound;
            this.ingredient = ingredient;
            this.toughness = toughness;
            this.knockbackResist = knockbackResist;
            //this.abilityList = abilityList;
        }

        private void switchBonus(CombatArmorBonus bonus, float[] value) {
            switch (bonus){
                case SPEED -> this.speed = value;
                case SWIM_SPEED -> this.swimSpeed = value;
                case HEALTH -> this.hpBonus = value;
                case JUMPHEIGHT -> this.jumpHeight = value;
                case FALLHEIGHT -> this.fallHeight = value;
                case ATTACK_DAMAGE -> this.attackDamage = value;
                case ATTACK_SPEED -> this.attackSpeed = value;
                case ATTACK_KNOCKBACK -> this.attackKnockback = value;
            }
        }

        public Builder setBonus(CombatArmorBonus bonus, float[] value) {
            switchBonus(bonus, value);
            return this;
        }

        public Builder setBonus(CombatArmorBonus bonus, float value){
            float[] arr = new float[]{value, value, value, value};
            switchBonus(bonus, arr);
            return this;
        }

        public Builder setBonus(CombatArmorBonus bonus, float v1, float v2, float v3, float v4){
            float[] arr = new float[]{v1, v2, v3, v4};
            switchBonus(bonus, arr);
            return this;
        }

        public Builder addAbility(CombatArmorAbility ability){
            abilityList.add(ability);
            return this;
        }

        //public Builder setSpeed
        public CombatArmorMaterials build(){ return new CombatArmorMaterials(this);}
    }
}
