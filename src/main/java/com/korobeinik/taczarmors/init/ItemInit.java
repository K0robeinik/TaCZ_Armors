package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import com.korobeinik.taczarmors.content.VariedCombatArmorItem;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.korobeinik.taczarmors.init.CreativeTabInit.addToTab;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TaczArmors.MODID);

    public static final RegistryObject<Item> T1_COMBAT_HELMET = addToTab(ITEMS.register("combat_t1_helmet",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.HELMET
            )
    ));

    public static final RegistryObject<Item> T1_COMBAT_CHESTPLATE = addToTab(ITEMS.register("combat_t1_chestplate",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.CHESTPLATE
            )
    ));

    public static final RegistryObject<Item> T1_COMBAT_LEGGINGS = addToTab(ITEMS.register("combat_t1_leggings",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.LEGGINGS
            )
    ));

    public static final RegistryObject<Item> T1_COMBAT_BOOTS = addToTab(ITEMS.register("combat_t1_boots",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.BOOTS
            )
    ));
    private static final String[] ghillieVariants = {"Oak", "Spruce", "Birch", "Jungle", "Acacia", "Dark Oak"};

    public static final RegistryObject<Item> GHILLIE_HELMET = addToTab(ITEMS.register("ghillie_helmet",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.GHILLIE,
                    ArmorItem.Type.HELMET,
                    ghillieVariants
            )
    ));

    public static final RegistryObject<Item> GHILLIE_CHESTPLATE = addToTab(ITEMS.register("ghillie_chestplate",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.GHILLIE,
                    ArmorItem.Type.CHESTPLATE,
                    ghillieVariants
            )
    ));

    public static final RegistryObject<Item> GHILLIE_LEGGINGS = addToTab(ITEMS.register("ghillie_leggings",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.GHILLIE,
                    ArmorItem.Type.LEGGINGS,
                    ghillieVariants
            )
    ));

    public static final RegistryObject<Item> GHILLIE_BOOTS = addToTab(ITEMS.register("ghillie_boots",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.GHILLIE,
                    ArmorItem.Type.BOOTS,
                    ghillieVariants
            )
    ));

    public static final RegistryObject<Item> SECURITY_HELMET = addToTab(ITEMS.register("security_helmet",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.HELMET
            )
    ));

    public static final RegistryObject<Item> SECURITY_CHESTPLATE = addToTab(ITEMS.register("security_chestplate",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.CHESTPLATE
            )
    ));

    public static final RegistryObject<Item> SECURITY_LEGGINGS = addToTab(ITEMS.register("security_leggings",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.LEGGINGS
            )
    ));

    public static final RegistryObject<Item> SECURITY_BOOTS = addToTab(ITEMS.register("security_boots",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.BOOTS
            )
    ));

    public static final RegistryObject<Item> ASSASSIN_HELMET = addToTab(ITEMS.register("assassin_helmet",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.HELMET
            )
    ));

    public static final RegistryObject<Item> ASSASSIN_CHESTPLATE = addToTab(ITEMS.register("assassin_chestplate",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.CHESTPLATE
            )
    ));

    public static final RegistryObject<Item> ASSASSIN_LEGGINGS = addToTab(ITEMS.register("assassin_leggings",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.LEGGINGS
            )
    ));

    public static final RegistryObject<Item> ASSASSIN_BOOTS = addToTab(ITEMS.register("assassin_boots",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.BOOTS
            )
    ));
}