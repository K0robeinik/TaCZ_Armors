package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.content.CombatArmorMaterials;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorMaterialInit {
    private static float[] createArray(float x){
        return new float[] {x, x, x, x};
    }
    private static float[] ZERO_ARRAY = new float[] {0, 0, 0, 0};
    public static final CombatArmorMaterials COMBAT_T1 = new CombatArmorMaterials(
            "combat_t1",
            20,
            new int[] {3, 6, 8, 4},
            10,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            Ingredient.of(Items.AMETHYST_SHARD),
            0,
            createArray(2),
            createArray(.1F),
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
            ZERO_ARRAY,
            createArray(.05F),
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
            ZERO_ARRAY,
            createArray(.1F),
            .1F,
            createArray(.1F),
            createArray(10F),
            .5F,
            createArray(-4)
    );

    public static final CombatArmorMaterials EXOSKELETON = new CombatArmorMaterials(
            "exoskeleton",
            50,
            new int[] {2, 5, 3, 2},
            30,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            Ingredient.of(Items.AMETHYST_SHARD),
            1,
            createArray(2), //health
            createArray(.2F), //speed
            .1F, //knockback res
            createArray(.2F),
            new float[] {0, 0, 0, .2F}, //jump
            .5F, //step
            createArray(-5),
            createArray(1),
            createArray(2),
            createArray(1)
    );

    public static final CombatArmorMaterials MODERN = new CombatArmorMaterials(
            "modern",
            35,
            new int[] {5, 10, 8, 4},
            30,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            2,
            ZERO_ARRAY,
            ZERO_ARRAY,
            1
    );

    public static final CombatArmorMaterials BERET = new CombatArmorMaterials(
            "beret",
            30,
            new int[] {5, 0, 0, 0},
            30,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            Ingredient.of(Items.AMETHYST_SHARD),
            0
    );
}
