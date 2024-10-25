package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TaczArmors.MODID);

    public static final List<Supplier<? extends ItemLike>> ARMOR_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> ARMOR_TAB = TABS.register("armor_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.taczArmors"))
                    .icon(ItemInit.SECURITY_HELMET.get()::getDefaultInstance)
                    .displayItems((displayParameters, output) -> {
                        ARMOR_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()));
                    })
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        ARMOR_TAB_ITEMS.add(itemLike);
        return itemLike;
    }
}
