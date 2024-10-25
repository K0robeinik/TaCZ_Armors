package com.korobeinik.taczarmors;

import com.korobeinik.taczarmors.init.ArmorMaterialInit;
import com.korobeinik.taczarmors.init.CreativeTabInit;
import com.korobeinik.taczarmors.init.ItemInit;
import com.mojang.realmsclient.util.JsonUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TaczArmors.MODID)
public class TaczArmors {
    public static final String MODID = "taczarmors";

    public TaczArmors() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
        CreativeTabInit.TABS.register(bus);
        System.out.println();
    }
}
