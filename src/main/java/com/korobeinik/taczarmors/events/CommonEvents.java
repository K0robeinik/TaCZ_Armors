package com.korobeinik.taczarmors.events;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import com.korobeinik.taczarmors.init.ArmorMaterialInit;
import com.korobeinik.taczarmors.init.ItemInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.UUID;
import java.util.Vector;


@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        Item item = entity.getItemBySlot(EquipmentSlot.FEET).getItem();
        if (item instanceof CombatArmorItem combatArmorItem ) {
            float jumpHeight = combatArmorItem.getMaterial().getJumpHeight(ArmorItem.Type.BOOTS);
            if (jumpHeight>0) entity.setDeltaMovement(entity.getDeltaMovement().add(0, jumpHeight, 0));
        }
    }
    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        Item item = entity.getItemBySlot(EquipmentSlot.FEET).getItem();
        if (item instanceof CombatArmorItem combatArmorItem ) {
            float fallHeight = combatArmorItem.getMaterial().getFallHeight(ArmorItem.Type.BOOTS);
            event.setDistance(event.getDistance()+fallHeight);
        }
    }
}
