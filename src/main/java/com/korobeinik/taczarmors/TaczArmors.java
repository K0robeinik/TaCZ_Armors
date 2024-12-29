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
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.resource.PathPackResources;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Mod(TaczArmors.MODID)
public class TaczArmors {
    public static final String MODID = "taczarmors";
    private static final Logger LOGGER = LogUtils.getLogger();
    public TaczArmors() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ArmorItemInit.ITEMS.register(bus);
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
            ArmorItemInit.DYEABLE.forEach(supplier -> event.register(DyeableCombatArmorItem::getItemColor, supplier.get()));
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

        @SubscribeEvent
        public static void addResourcePacks(AddPackFindersEvent event) {
            if (event.getPackType() == PackType.CLIENT_RESOURCES){
                IModFileInfo modFileInfo = ModList.get().getModFileById(MODID);
                if (modFileInfo == null) {
                    LOGGER.error("There ain't no danged modfileinfoes! There'd be no resaucepacks out there!");
                    return;
                }
                IModFile modFile = modFileInfo.getFile();
                event.addRepositorySource(pOnLoad -> {
                    Pack pack = Pack.readMetaAndCreate(
                            TaczArmors.MODID + ":32ghillie",
                            Component.literal("32x Ghillie Armor Texture"),
                            false,
                            pId -> new PathPackResources(
                                    pId,
                                    true,
                                    modFile.findResource("resourcepacks/32ghillie")
                            ),
                            PackType.CLIENT_RESOURCES,
                            Pack.Position.TOP,
                            PackSource.BUILT_IN
                    );
                    if (pack != null) pOnLoad.accept(pack);
                });
            }
        }
    }
}
