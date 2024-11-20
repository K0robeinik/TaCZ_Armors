package com.korobeinik.taczarmors.events;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.ColorBottle;
import com.korobeinik.taczarmors.content.KevlarHorseArmorItem;
import com.korobeinik.taczarmors.init.ItemInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    //@OnlyIn(Dist.CLIENT)
    public static void onRegisterItemColor(RegisterColorHandlersEvent.Item event){
        //event.register(((stack, tint) -> tint > 0 ? 0xFFFFFF : ColorBottle.getDyeColor(stack)), ItemInit.COLOR_BOTTLE.get());
        event.register(ColorBottle::getItemColor, ItemInit.COLOR_BOTTLE.get());
        event.register(KevlarHorseArmorItem::getItemColor, ItemInit.KEVLAR_HORSE_ARMOR.get());
    }
}
