package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.items.*;
import com.korobeinik.taczarmors.items.armor.CombatArmorItem;
import com.korobeinik.taczarmors.items.armor.DyeableCombatArmorItem;
import com.korobeinik.taczarmors.items.armor.PoweredCombatArmorItem;
import com.korobeinik.taczarmors.items.armor.VariedCombatArmorItem;
import com.korobeinik.taczarmors.items.energy.EnergyItem;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.korobeinik.taczarmors.init.CreativeTabInit.MATERIAL_TAB_ITEMS;
import static com.korobeinik.taczarmors.init.CreativeTabInit.addToTab;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TaczArmors.MODID);

    //=================================================================================(Crafting Materials)=================================================================================
    private static RegistryObject<Item> registerMaterial(String name) {
        return addToTab(ITEMS.register(name, () -> new Item(new Item.Properties())), MATERIAL_TAB_ITEMS);
    }
    public static final RegistryObject<Item> STEEL_INGOT = registerMaterial("steel_ingot");
    public static final RegistryObject<Item> PLASTIC_SHEET = registerMaterial("plastic_sheet");
    public static final RegistryObject<Item> COPPER_WIRE = registerMaterial("copper_wire");
    //==================================================================================(Other)============================================================================
    public static final RegistryObject<Item> COLOR_BOTTLE = addToTab(ITEMS.register("color_bottle", () -> new ColorBottleItem(new Item.Properties())));

    public static final RegistryObject<Item> KEVLAR_HORSE_ARMOR = addToTab(ITEMS.register("kevlar_horse_armor", () -> new KevlarHorseArmorItem(11, "kevlar", new Item.Properties().stacksTo(1))));

    public static final RegistryObject<Item> BATTERY = addToTab(ITEMS.register("battery", EnergyItem::new));
}