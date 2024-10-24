package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.content.CombatArmorMaterials;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorMaterialInit {
    public static final CombatArmorMaterials COMBAT_T1 = new CombatArmorMaterials(
            "combat_t1",
            20,
            new int[] {3, 6, 8, 4},
            10,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            Ingredient.of(Items.AMETHYST_SHARD),
            0,
            2,
            .1F,
            .1F
    );

    public static final CombatArmorMaterials GHILLIE = new CombatArmorMaterials(
            "ghillie",
            10,
            new int[] {1, 2, 1, 2},
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            0
    );

    public static final CombatArmorMaterials SECURITY = new CombatArmorMaterials(
            "security",
            30,
            new int[] {3, 8, 5, 2},
            15,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            2.0F,
            0,
            .05F,
            .1F
    );

    public static final CombatArmorMaterials ASSASSIN = new CombatArmorMaterials(
            "assassin",
            20,
            new int[] {0, 1, 0, 0},
            5,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            0,
            0,
            .25F,
            .1F,
            .5F,
            .4F,
            .5F,
            -4
    );
}
