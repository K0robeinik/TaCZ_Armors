package com.korobeinik.taczarmors.events;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.config.CommonConfig;
import com.korobeinik.taczarmors.content.CombatArmorBonus;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import com.korobeinik.taczarmors.init.ItemInit;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.Random;


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

    static Random rand = new Random();
    @SubscribeEvent
    public static void onEntitySpawnFinal(MobSpawnEvent.FinalizeSpawn event){
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
