package com.korobeinik.taczarmors.events;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.CombatArmorBonus;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.NotNull;


@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
    @SubscribeEvent
    public static void onLivingJump(LivingEvent.@NotNull LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        Item item = entity.getItemBySlot(EquipmentSlot.FEET).getItem();
        if (item instanceof CombatArmorItem combatArmorItem ) {
            float jumpHeight = combatArmorItem.getMaterial().getBonusForEntity(CombatArmorBonus.JUMPHEIGHT, entity);
            if (jumpHeight>0) entity.setDeltaMovement(entity.getDeltaMovement().add(0, jumpHeight, 0));
        }
    }
    @SubscribeEvent
    public static void onLivingFall(@NotNull LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        Item item = entity.getItemBySlot(EquipmentSlot.FEET).getItem();
        if (item instanceof CombatArmorItem combatArmorItem ) {
            float fallHeight = combatArmorItem.getMaterial().getBonusForEntity(CombatArmorBonus.FALLHEIGHT, entity);
            event.setDistance(event.getDistance()+fallHeight);
        }
    }
//    @SubscribeEvent
//    public static void onGunLOL(@NotNull GunFireEvent event){
//        LivingEntity entity = event.getShooter();
//        entity.sendSystemMessage(Component.literal("Gun Fired"));
//    }
    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event){
        LivingEntity entity = event.getEntity();
        Item item = entity.getItemBySlot(EquipmentSlot.FEET).getItem();
    }


}
