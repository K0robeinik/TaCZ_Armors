package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import com.korobeinik.taczarmors.content.VariedCombatArmorItem;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TaczArmors.MODID);
    private static final String[] ghillieVariants = {"Oak", "Spruce"};

    public static final RegistryObject<Item> T1_COMBAT_HELMET = ITEMS.register("combat_t1_helmet",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.HELMET
            )
    );

    public static final RegistryObject<Item> T1_COMBAT_CHESTPLATE = ITEMS.register("combat_t1_chestplate",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.CHESTPLATE
            )
    );

    public static final RegistryObject<Item> T1_COMBAT_LEGGINGS = ITEMS.register("combat_t1_leggings",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.LEGGINGS
            )
    );

    public static final RegistryObject<Item> T1_COMBAT_BOOTS = ITEMS.register("combat_t1_boots",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.BOOTS
            )
    );

    public static final RegistryObject<Item> GHILLIE_HELMET = ITEMS.register("ghillie_helmet",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.GHILLIE,
                    ArmorItem.Type.HELMET,
                    ghillieVariants
            )
    );

    public static final RegistryObject<Item> GHILLIE_CHESTPLATE = ITEMS.register("ghillie_chestplate",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.GHILLIE,
                    ArmorItem.Type.CHESTPLATE,
                    ghillieVariants
            )
    );

    public static final RegistryObject<Item> GHILLIE_LEGGINGS = ITEMS.register("ghillie_leggings",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.GHILLIE,
                    ArmorItem.Type.LEGGINGS,
                    ghillieVariants
            )
    );

    public static final RegistryObject<Item> GHILLIE_BOOTS = ITEMS.register("ghillie_boots",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.GHILLIE,
                    ArmorItem.Type.BOOTS,
                    ghillieVariants
            )
    );

    public static final RegistryObject<Item> SECURITY_HELMET = ITEMS.register("security_helmet",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.HELMET
            )
    );

    public static final RegistryObject<Item> SECURITY_CHESTPLATE = ITEMS.register("security_chestplate",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.CHESTPLATE
            )
    );

    public static final RegistryObject<Item> SECURITY_LEGGINGS = ITEMS.register("security_leggings",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.LEGGINGS
            )
    );

    public static final RegistryObject<Item> SECURITY_BOOTS = ITEMS.register("security_boots",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.BOOTS
            )
    );

    public static final RegistryObject<Item> ASSASSIN_HELMET = ITEMS.register("assassin_helmet",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.HELMET
            )
    );

    public static final RegistryObject<Item> ASSASSIN_CHESTPLATE = ITEMS.register("assassin_chestplate",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.CHESTPLATE
            )
    );

    public static final RegistryObject<Item> ASSASSIN_LEGGINGS = ITEMS.register("assassin_leggings",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.LEGGINGS
            )
    );

    public static final RegistryObject<Item> ASSASSIN_BOOTS = ITEMS.register("assassin_boots",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.BOOTS
            )
    );
}