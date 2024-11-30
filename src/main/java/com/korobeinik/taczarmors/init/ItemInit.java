package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.items.*;
import com.korobeinik.taczarmors.items.armor.CombatArmorItem;
import com.korobeinik.taczarmors.items.armor.DyeableCombatArmorItem;
import com.korobeinik.taczarmors.items.armor.VariedCombatArmorItem;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.korobeinik.taczarmors.init.CreativeTabInit.MATERIAL_TAB_ITEMS;
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
    private static final String[] ghillieVariants = {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak"};

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

    private static final String[] exoVariants = {"light", "dark"};

    public static final RegistryObject<Item> EXOSKELETON_HELMET = addToTab(ITEMS.register("exoskeleton_helmet",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.EXOSKELETON,
                    ArmorItem.Type.HELMET,
                    exoVariants
            )
    ));

    public static final RegistryObject<Item> EXOSKELETON_CHESTPLATE = addToTab(ITEMS.register("exoskeleton_chestplate",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.EXOSKELETON,
                    ArmorItem.Type.CHESTPLATE,
                    exoVariants
            )
    ));

    public static final RegistryObject<Item> EXOSKELETON_LEGGINGS = addToTab(ITEMS.register("exoskeleton_leggings",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.EXOSKELETON,
                    ArmorItem.Type.LEGGINGS,
                    exoVariants
            )
    ));

    public static final RegistryObject<Item> EXOSKELETON_BOOTS = addToTab(ITEMS.register("exoskeleton_boots",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.EXOSKELETON,
                    ArmorItem.Type.BOOTS,
                    exoVariants
            )
    ));

    private static final String[] camoVariants = {"plain", "pixel", "flecktarn", "marpat"};

    public static final RegistryObject<Item> MODERN_HELMET = addToTab(ITEMS.register("modern_helmet",
            () -> new DyeableCombatArmorItem(
                    ArmorMaterialInit.MODERN,
                    ArmorItem.Type.HELMET,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> MODERN_CHESTPLATE = addToTab(ITEMS.register("modern_chestplate",
            () -> new DyeableCombatArmorItem(
                    ArmorMaterialInit.MODERN,
                    ArmorItem.Type.CHESTPLATE,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> MODERN_LEGGINGS = addToTab(ITEMS.register("modern_leggings",
            () -> new DyeableCombatArmorItem(
                    ArmorMaterialInit.MODERN,
                    ArmorItem.Type.LEGGINGS,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> MODERN_BOOTS = addToTab(ITEMS.register("modern_boots",
            () -> new DyeableCombatArmorItem(
                    ArmorMaterialInit.MODERN,
                    ArmorItem.Type.BOOTS,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> BERET = addToTab(ITEMS.register("beret",
            () -> new DyeableCombatArmorItem(
                    ArmorMaterialInit.BERET,
                    ArmorItem.Type.HELMET,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> GENERAL = addToTab(ITEMS.register("general_cap",
            () -> new DyeableCombatArmorItem(
                    ArmorMaterialInit.GENERAL,
                    ArmorItem.Type.HELMET,
                    camoVariants
            )
    ));

    //=================================================================================(Crafting Materials)=================================================================================
    private static RegistryObject<Item> registerMaterial(String name) {
        return addToTab(ITEMS.register(name, () -> new Item(new Item.Properties())), MATERIAL_TAB_ITEMS);
    }
    public static final RegistryObject<Item> STEEL_INGOT = registerMaterial("steel_ingot");
    public static final RegistryObject<Item> PLASTIC_SHEET = registerMaterial("plastic_sheet");
    public static final RegistryObject<Item> COPPER_WIRE = registerMaterial("copper_wire");
    //==================================================================================(Other)============================================================================
    public static final RegistryObject<Item> COLOR_BOTTLE = addToTab(ITEMS.register("color_bottle", () -> new ColorBottle(new Item.Properties())));

    public static final RegistryObject<Item> KEVLAR_HORSE_ARMOR = addToTab(ITEMS.register("kevlar_horse_armor", () -> new KevlarHorseArmorItem(11, "kevlar", new Item.Properties().stacksTo(1))
    ));
}