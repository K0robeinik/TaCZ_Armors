package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.items.armor.CombatArmorBonus;
import com.korobeinik.taczarmors.items.armor.CombatArmorMaterials;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorMaterialInit {

    public static final CombatArmorMaterials COMBAT_T1 = CombatArmorMaterials.builder(
            "combat_t1",
            20,
            new int[] {3, 6, 8, 4},
            10,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            Ingredient.of(Items.AMETHYST_SHARD),
            0,
            .1F)
            .setBonus(CombatArmorBonus.JUMPHEIGHT, 0, 1, 0, 0)
            .build();

    public static final CombatArmorMaterials GHILLIE = CombatArmorMaterials.builder(
            "ghillie",
            10,
            new int[] {1, 2, 1, 2},
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            0)
            .build();

    public static final CombatArmorMaterials SECURITY = CombatArmorMaterials.builder(
            "security",
            30,
            new int[] {3, 8, 5, 2},
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            2.0F,
            .1F)
            .setBonus(CombatArmorBonus.SPEED, .05F)
            .setBonus(CombatArmorBonus.HEALTH, 1, 2, 2, 1)
            .build();

    public static final CombatArmorMaterials ASSASSIN = CombatArmorMaterials.builder(
            "assassin",
            20,
            new int[] {0, 1, 0, 0},
            5,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            0,
            .1F)
            .setBonus(CombatArmorBonus.SPEED, .2F)
            .setBonus(CombatArmorBonus.JUMPHEIGHT, 0, 0, 0,1)
            .setBonus(CombatArmorBonus.FALLHEIGHT, 0, 0, 0,-2)
            .setBonus(CombatArmorBonus.STEP_HEIGHT, 0, 0, 0, .5F)
            .build();

    public static final CombatArmorMaterials EXOSKELETON = CombatArmorMaterials.builder(
            "exoskeleton",
            50,
            new int[] {2, 5, 3, 2},
            30,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            Ingredient.of(Items.AMETHYST_SHARD),
            1)
            .setBonus(CombatArmorBonus.SPEED, .1F)
            .setBonus(CombatArmorBonus.ATTACK_DAMAGE, 0, 9, 0, 0)
            .setBonus(CombatArmorBonus.ATTACK_SPEED, 0, 2, 0, 0)
            .setBonus(CombatArmorBonus.ATTACK_KNOCKBACK, 0, 1, 0, 0)
            .setBonus(CombatArmorBonus.JUMPHEIGHT, 0, 0, 0, .25F)
            .setBonus(CombatArmorBonus.FALLHEIGHT, 0, 0, 0,-5)
            .setBonus(CombatArmorBonus.STEP_HEIGHT, 0, 0, 0, .5F)
            .setBonus(CombatArmorBonus.SPRINT_SPEED, 0, 0, 0, 0.25F)
            .build();

    public static final CombatArmorMaterials MODERN = CombatArmorMaterials.builder(
            "modern",
            35,
            new int[] {5, 10, 8, 4},
            30,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            2,
            .1F)
            .build();

    public static final CombatArmorMaterials BERET = CombatArmorMaterials.builder(
            "beret",
            25,
            new int[] {5, 0, 0, 0},
            30,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            0)
            .build();
    public static final CombatArmorMaterials GENERAL = CombatArmorMaterials.builder(
            "general",
            25,
            new int[] {5, 0, 0, 0},
            30,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            0)
            .build();
}
