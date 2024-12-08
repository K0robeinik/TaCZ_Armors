package com.korobeinik.taczarmors;

import com.korobeinik.taczarmors.client.overlays.HelmetOverlay;
import com.korobeinik.taczarmors.client.screen.FuelGeneratorBlockScreen;
import com.korobeinik.taczarmors.config.ClientConfig;
import com.korobeinik.taczarmors.config.ServerConfig;
import com.korobeinik.taczarmors.items.ColorBottleItem;
import com.korobeinik.taczarmors.items.armor.DyeableCombatArmorItem;
import com.korobeinik.taczarmors.items.KevlarHorseArmorItem;
import com.korobeinik.taczarmors.init.*;
import com.korobeinik.taczarmors.util.ColorUtil;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
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

        AttributeInit.ATTRIBUTES.register(bus);
        BlockInit.BLOCKS.register(bus);
        BlockEntityTypeInit.BLOCK_ENTITIES.register(bus);
        CreativeTabInit.TABS.register(bus);
        ItemInit.ITEMS.register(bus);
        MenuInit.MENU_TYPES.register(bus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC, "taczarmors-server.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC, "taczarmors-client.toml");
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

            event.enqueueWork(() -> {
                MenuScreens.register(MenuInit.FUEL_GENERATOR_MENU.get(), FuelGeneratorBlockScreen::new);
            });
        }

        @SubscribeEvent
        public static void onRegisterItemColor(RegisterColorHandlersEvent.Item event){
            event.register(ColorBottleItem::getItemColor, ItemInit.COLOR_BOTTLE.get());
            event.register(KevlarHorseArmorItem::getItemColor, ItemInit.KEVLAR_HORSE_ARMOR.get());
            event.register(DyeableCombatArmorItem::getItemColor, ItemInit.MODERN_HELMET.get(), ItemInit.MODERN_CHESTPLATE.get(), ItemInit.MODERN_LEGGINGS.get(), ItemInit.MODERN_BOOTS.get(), ItemInit.BERET.get(), ItemInit.GENERAL.get());
        }

        @SubscribeEvent
        public static void addColorsTab(BuildCreativeModeTabContentsEvent event){
            if (event.getTab() == CreativeTabInit.COLOR_TAB.get()){
                for (int color : ColorUtil.dyeColor) event.accept(ColorBottleItem.addStack(color));
                //if (ClientConfig.CUSTOM_COLORS.get() != null) for (int color : ClientConfig.CUSTOM_COLORS.get()) event.accept(ColorBottleItem.addStack(color));
            }
        }

        @SubscribeEvent
        public static void registerOverlays(RegisterGuiOverlaysEvent event){
            event.registerBelowAll("helmet_overlay", HelmetOverlay.HUD_HELMET);
        }
    }
}
