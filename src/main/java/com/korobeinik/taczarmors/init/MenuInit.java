package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.client.menu.FuelGeneratorBlockMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TaczArmors.MODID);

    public static final RegistryObject<MenuType<FuelGeneratorBlockMenu>> FUEL_GENERATOR_MENU = MENU_TYPES.register("fuel_generator_menu", () -> IForgeMenuType.create(FuelGeneratorBlockMenu::new));
}
