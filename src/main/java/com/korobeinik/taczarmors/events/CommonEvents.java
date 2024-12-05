package com.korobeinik.taczarmors.events;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.init.AttributeInit;
import com.korobeinik.taczarmors.items.armor.*;
import com.korobeinik.taczarmors.util.MobEquipmentUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.puffish.attributesmod.AttributesMod;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;


@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
    /*private static final UUID SPEED_MODIFIER = UUID.fromString("690292F2-48D5-9434-A29F-7773AF7DF28D");
    private static final UUID MAX_HEALTH_MODIFIER = UUID.fromString("700292F2-48D5-9434-A29F-7773AF7DF28D");
    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("710292F2-48D5-9434-A29F-7773AF7DF28D");
    private static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("720292F2-48D5-9434-A29F-7773AF7DF28D");
    private static final UUID ATTACK_KNOCKBACK_MODIFIER = UUID.fromString("730292F2-48D5-9434-A29F-7773AF7DF28D");*/

//    @SubscribeEvent
//    public static void onLivingJump(LivingEvent.@NotNull LivingJumpEvent event) {
//        LivingEntity entity = event.getEntity();
//        entity.setDeltaMovement(
//            entity.getDeltaMovement().x * sprintMultiplier(entity),
//            entity.getDeltaMovement().y,
//            entity.getDeltaMovement().z * sprintMultiplier(entity)
//        );
//    }

    protected static float sprintMultiplier(LivingEntity entity){
        return entity.isSprinting() ? 2 : 1;
    }

    @SubscribeEvent
    public static void onLivingFall(@NotNull LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        float fallHeight = (float) entity.getAttributeValue(AttributeInit.FALL_HEIGHT.get());
        if (fallHeight != 0) {
            event.setDistance(event.getDistance()+fallHeight);
        }
    }

    /*@SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED)) {
            AttributeInstance attribute = entity.getAttribute(Attributes.MOVEMENT_SPEED);
            assert attribute != null;
            CombatArmorItem item = MobEquipmentUtil.tryGetCombatArmor(entity);
            if (attribute.getModifier(SPEED_MODIFIER) != null) {
                attribute.removeModifier(SPEED_MODIFIER);
            }
            if (item != null && attribute.getModifier(SPEED_MODIFIER) == null && item.getMaterial().getBonusForEntity(CombatArmorBonus.SPEED, entity) > 0)
                attribute.addTransientModifier(new AttributeModifier(SPEED_MODIFIER, "Bonus Speed", sprintMultiplier(entity), AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
    }*/
}
