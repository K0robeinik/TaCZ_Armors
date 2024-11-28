package com.korobeinik.taczarmors.events;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.CombatArmorAbility;
import com.korobeinik.taczarmors.content.CombatArmorBonus;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import com.korobeinik.taczarmors.content.CombatArmorMaterials;
import com.korobeinik.taczarmors.util.MobEquipmentUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;


@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
    private static final UUID SPEED_MODIFIER = UUID.fromString("690292F2-48D5-9434-A29F-7773AF7DF28D");
    private static final UUID MAX_HEALTH_MODIFIER = UUID.fromString("700292F2-48D5-9434-A29F-7773AF7DF28D");
    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("710292F2-48D5-9434-A29F-7773AF7DF28D");
    private static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("720292F2-48D5-9434-A29F-7773AF7DF28D");
    private static final UUID ATTACK_KNOCKBACK_MODIFIER = UUID.fromString("730292F2-48D5-9434-A29F-7773AF7DF28D");
    @SubscribeEvent
    public static void onLivingJump(LivingEvent.@NotNull LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        CombatArmorItem item = MobEquipmentUtil.tryGetCombatArmor(entity);
        if (item != null) {
            CombatArmorMaterials material = item.getMaterial();
            float jumpHeight = material.getBonusForEntity(CombatArmorBonus.JUMPHEIGHT, entity);
            if (jumpHeight>0) {
                entity.addDeltaMovement(new Vec3(0, 0.15 * jumpHeight, 0));
                if(material.hasAbility(CombatArmorAbility.LONG_JUMP, entity) && entity.isSprinting()) {
                    float x = 1;
                    float f = entity.getYHeadRot() * ((float)Math.PI / 180F);
                    entity.addDeltaMovement(new Vec3((-Mth.sin(f) * x), 0, (Mth.cos(f) * x)));
                }
            }
        }
    }
    @SubscribeEvent
    public static void onLivingFall(@NotNull LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        CombatArmorItem item = MobEquipmentUtil.tryGetCombatArmor(entity);
        if (item != null) {
            float fallHeight = item.getMaterial().getBonusForEntity(CombatArmorBonus.FALLHEIGHT, entity);
            event.setDistance(event.getDistance()+fallHeight);
        }
    }

    protected static void setModifier(LivingEntity entity, Attribute attribute, UUID uuid, CombatArmorBonus bonus, String name, AttributeModifier.Operation operation){
        if (entity.getAttributes().hasAttribute(attribute)) {
            AttributeInstance attributeInstance = entity.getAttribute(attribute);
            assert attributeInstance != null;
            CombatArmorItem item = MobEquipmentUtil.tryGetCombatArmor(entity);
            if (attributeInstance.getModifier(uuid) != null) {
                attributeInstance.removeModifier(uuid);
            }
            if (item != null && attributeInstance.getModifier(uuid) == null){
                float x = item.getMaterial().getBonusForEntity(bonus, entity);
                if (x>0) attributeInstance.addTransientModifier(new AttributeModifier(uuid, name, x, operation));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED)) {
            AttributeInstance attribute = entity.getAttribute(Attributes.MOVEMENT_SPEED);
            assert attribute != null;
            CombatArmorItem item = MobEquipmentUtil.tryGetCombatArmor(entity);
            if (attribute.getModifier(SPEED_MODIFIER) != null) {
                attribute.removeModifier(SPEED_MODIFIER);
            }
            if (item != null && attribute.getModifier(SPEED_MODIFIER) == null){
                float speed = item.getMaterial().getBonusForEntity(CombatArmorBonus.SPEED, entity);
                speed = entity.isSprinting() ? speed*2 : speed;
                if (speed>0) attribute.addTransientModifier(new AttributeModifier(SPEED_MODIFIER, "Bonus Speed", speed, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
        //setModifier(entity, Attributes.MOVEMENT_SPEED, SPEED_MODIFIER, CombatArmorBonus.SPEED, "Bonus Speed", AttributeModifier.Operation.MULTIPLY_TOTAL);
        setModifier(entity, Attributes.MAX_HEALTH, MAX_HEALTH_MODIFIER, CombatArmorBonus.HEALTH, "Bonus Health", AttributeModifier.Operation.ADDITION);
        setModifier(entity, Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, CombatArmorBonus.ATTACK_DAMAGE, "Bonus Attack Damage", AttributeModifier.Operation.ADDITION);
        setModifier(entity, Attributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, CombatArmorBonus.ATTACK_SPEED, "Bonus Attack Speed", AttributeModifier.Operation.MULTIPLY_TOTAL);
        setModifier(entity, Attributes.ATTACK_KNOCKBACK, ATTACK_KNOCKBACK_MODIFIER, CombatArmorBonus.ATTACK_KNOCKBACK, "Bonus Attack Knockback", AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
