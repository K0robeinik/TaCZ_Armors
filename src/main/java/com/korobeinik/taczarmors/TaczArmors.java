package com.korobeinik.taczarmors;

import com.korobeinik.taczarmors.config.ServerConfig;
import com.korobeinik.taczarmors.content.ColorBottle;
import com.korobeinik.taczarmors.content.DyeableCombatArmorItem;
import com.korobeinik.taczarmors.content.KevlarHorseArmorItem;
import com.korobeinik.taczarmors.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TaczArmors.MODID)
public class TaczArmors {
    public static final String MODID = "taczarmors";
    private static final Logger LOGGER = LogUtils.getLogger();
    public TaczArmors() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockInit.BLOCKS.register(bus);
        CreativeTabInit.TABS.register(bus);
        ItemInit.ITEMS.register(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC, "taczarmors-server.toml");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void onRegisterItemColor(RegisterColorHandlersEvent.Item event){
            event.register(ColorBottle::getItemColor, ItemInit.COLOR_BOTTLE.get());
            event.register(KevlarHorseArmorItem::getItemColor, ItemInit.KEVLAR_HORSE_ARMOR.get());
            event.register(DyeableCombatArmorItem::getItemColor, ItemInit.MODERN_HELMET.get(), ItemInit.MODERN_CHESTPLATE.get(), ItemInit.MODERN_LEGGINGS.get(), ItemInit.MODERN_BOOTS.get(), ItemInit.BERET.get(), ItemInit.GENERAL.get());
        }

        @SubscribeEvent
        public static void addColorsTab(BuildCreativeModeTabContentsEvent event){
            if (event.getTab() == CreativeTabInit.COLOR_TAB.get()){
                ItemStack stack = ItemInit.COLOR_BOTTLE.get().getDefaultInstance();
                stack.getOrCreateTagElement("display").putInt("color", 0xFF0000);
                event.accept(stack);
//                for (DyeColor color : DyeColor.values()) {
//                    stack.getOrCreateTagElement("display").putInt("color", color.getFireworkColor());
//                    event.accept(stack);
//                }
            }
        }
    }
}
