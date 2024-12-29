package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.items.armor.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.korobeinik.taczarmors.init.CreativeTabInit.addToTab;

public class ArmorItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TaczArmors.MODID);
    public static final List<Supplier<? extends ItemLike>> NORMAL = new ArrayList<>();
    public static final List<Supplier<? extends ItemLike>> DYEABLE = new ArrayList<>();

    public static final RegistryObject<Item> T1_COMBAT_HELMET = addArmor(ITEMS.register("combat_t1_helmet",
            () -> new PoweredCombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.HELMET
            )
    ));

    public static final RegistryObject<Item> T1_COMBAT_CHESTPLATE = addArmor(ITEMS.register("combat_t1_chestplate",
            () -> new PoweredCombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.CHESTPLATE
            )
    ));

    public static final RegistryObject<Item> T1_COMBAT_LEGGINGS = addArmor(ITEMS.register("combat_t1_leggings",
            () -> new PoweredCombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.LEGGINGS
            )
    ));

    public static final RegistryObject<Item> T1_COMBAT_BOOTS = addArmor(ITEMS.register("combat_t1_boots",
            () -> new PoweredCombatArmorItem(
                    ArmorMaterialInit.COMBAT_T1,
                    ArmorItem.Type.BOOTS
            )
    ));

    public static final RegistryObject<Item> GHILLIE_HELMET = dyeableArmor(ITEMS.register("ghillie_helmet",
            () -> new GhillieArmor(ArmorItem.Type.HELMET)
    ));

    public static final RegistryObject<Item> GHILLIE_CHESTPLATE = dyeableArmor(ITEMS.register("ghillie_chestplate",
            () -> new GhillieArmor(ArmorItem.Type.CHESTPLATE)
    ));

    public static final RegistryObject<Item> GHILLIE_LEGGINGS = dyeableArmor(ITEMS.register("ghillie_leggings",
            () -> new GhillieArmor(ArmorItem.Type.LEGGINGS)
    ));

    public static final RegistryObject<Item> GHILLIE_BOOTS = dyeableArmor(ITEMS.register("ghillie_boots",
            () -> new GhillieArmor(ArmorItem.Type.BOOTS)
    ));

    public static final RegistryObject<Item> SECURITY_HELMET = addArmor(ITEMS.register("security_helmet",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.HELMET
            )
    ));

    public static final RegistryObject<Item> SECURITY_CHESTPLATE = addArmor(ITEMS.register("security_chestplate",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.CHESTPLATE
            )
    ));

    public static final RegistryObject<Item> SECURITY_LEGGINGS = addArmor(ITEMS.register("security_leggings",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.LEGGINGS
            )
    ));

    public static final RegistryObject<Item> SECURITY_BOOTS = addArmor(ITEMS.register("security_boots",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.SECURITY,
                    ArmorItem.Type.BOOTS
            )
    ));

    public static final RegistryObject<Item> ASSASSIN_HELMET = addArmor(ITEMS.register("assassin_helmet",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.HELMET
            )
    ));

    public static final RegistryObject<Item> ASSASSIN_CHESTPLATE = addArmor(ITEMS.register("assassin_chestplate",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.CHESTPLATE
            )
    ));

    public static final RegistryObject<Item> ASSASSIN_LEGGINGS = addArmor(ITEMS.register("assassin_leggings",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.LEGGINGS
            )
    ));

    public static final RegistryObject<Item> ASSASSIN_BOOTS = addArmor(ITEMS.register("assassin_boots",
            () -> new CombatArmorItem(
                    ArmorMaterialInit.ASSASSIN,
                    ArmorItem.Type.BOOTS
            )
    ));

    private static final String[] exoVariants = {"light", "dark"};

    public static final RegistryObject<Item> EXOSKELETON_HELMET = dyeableArmor(ITEMS.register("exoskeleton_helmet",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.EXOSKELETON,
                    ArmorItem.Type.HELMET,
                    exoVariants
            )
    ));

    public static final RegistryObject<Item> EXOSKELETON_CHESTPLATE = dyeableArmor(ITEMS.register("exoskeleton_chestplate",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.EXOSKELETON,
                    ArmorItem.Type.CHESTPLATE,
                    exoVariants
            )
    ));

    public static final RegistryObject<Item> EXOSKELETON_LEGGINGS = dyeableArmor(ITEMS.register("exoskeleton_leggings",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.EXOSKELETON,
                    ArmorItem.Type.LEGGINGS,
                    exoVariants
            )
    ));

    public static final RegistryObject<Item> EXOSKELETON_BOOTS = dyeableArmor(ITEMS.register("exoskeleton_boots",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.EXOSKELETON,
                    ArmorItem.Type.BOOTS,
                    exoVariants
            )
    ));

    private static final String[] camoVariants = {"plain", "pixel", "flecktarn", "marpat"};

    public static final RegistryObject<Item> MODERN_HELMET = dyeableArmor(ITEMS.register("modern_helmet",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.MODERN,
                    ArmorItem.Type.HELMET,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> MODERN_CHESTPLATE = dyeableArmor(ITEMS.register("modern_chestplate",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.MODERN,
                    ArmorItem.Type.CHESTPLATE,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> MODERN_LEGGINGS = dyeableArmor(ITEMS.register("modern_leggings",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.MODERN,
                    ArmorItem.Type.LEGGINGS,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> MODERN_BOOTS = dyeableArmor(ITEMS.register("modern_boots",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.MODERN,
                    ArmorItem.Type.BOOTS,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> BERET = dyeableArmor(ITEMS.register("beret",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.BERET,
                    ArmorItem.Type.HELMET,
                    camoVariants
            )
    ));

    public static final RegistryObject<Item> GENERAL = dyeableArmor(ITEMS.register("general_cap",
            () -> new VariedCombatArmorItem(
                    ArmorMaterialInit.GENERAL,
                    ArmorItem.Type.HELMET,
                    camoVariants
            )
    ));

    private static <T extends Item> RegistryObject<T> addArmor(RegistryObject<T> itemLike) {
        NORMAL.add(itemLike);
        return addToTab(itemLike);
    }
    private static <T extends Item> RegistryObject<T> dyeableArmor(RegistryObject<T> itemLike) {
        DYEABLE.add(itemLike);
        return addToTab(itemLike);
    }
}
