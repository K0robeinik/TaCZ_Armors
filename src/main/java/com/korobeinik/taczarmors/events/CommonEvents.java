package com.korobeinik.taczarmors.events;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.config.CommonConfig;
import com.korobeinik.taczarmors.content.CombatArmorBonus;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import com.korobeinik.taczarmors.init.ItemInit;
import com.korobeinik.taczarmors.util.MobEquipmentUtil;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.UUID;


@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {
    private static final UUID SPEED_MODIFIER = UUID.fromString("690292F2-48D5-9434-A29F-7773AF7DF28D");
    @SubscribeEvent
    public static void onLivingJump(LivingEvent.@NotNull LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        CombatArmorItem item = MobEquipmentUtil.tryGetCombatArmor(entity);
        if (item != null) {
            float jumpHeight = item.getMaterial().getBonusForEntity(CombatArmorBonus.JUMPHEIGHT, entity);
            if (jumpHeight>0) entity.addDeltaMovement(new Vec3(0, 0.15 * jumpHeight, 0));
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
            if (item != null){
                float speed = item.getMaterial().getBonusForEntity(CombatArmorBonus.SPEED, entity);
                speed = entity.isSprinting() ? speed*2 : speed;
                if (speed>0) attribute.addTransientModifier(new AttributeModifier(SPEED_MODIFIER, "Bonus Speed", speed, AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
    }
    static Random rand = new Random();
    @SubscribeEvent
    public static void onEntitySpawnFinal(MobSpawnEvent.FinalizeSpawn event) {
        LivingEntity entity = event.getEntity();
        Difficulty difficulty = event.getDifficulty().getDifficulty();
        int chance = difficulty == Difficulty.HARD ? 100 : CommonConfig.SPAWN_WITH_ARMOR_CHANCE.get();
        if(chance != 0 && chance > rand.nextInt(100)) {
            if (entity instanceof Zombie || entity instanceof Skeleton) {
                entity.setItemSlot(EquipmentSlot.HEAD, ItemInit.MODERN_HELMET.get().getDefaultInstance());
                entity.setItemSlot(EquipmentSlot.CHEST, ItemInit.MODERN_CHESTPLATE.get().getDefaultInstance());
                entity.setItemSlot(EquipmentSlot.LEGS, ItemInit.MODERN_LEGGINGS.get().getDefaultInstance());
                entity.setItemSlot(EquipmentSlot.FEET, ItemInit.MODERN_BOOTS.get().getDefaultInstance());
            }
        }
    }
}
