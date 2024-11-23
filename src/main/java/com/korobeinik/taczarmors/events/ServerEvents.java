package com.korobeinik.taczarmors.events;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.config.ServerConfig;
import com.korobeinik.taczarmors.init.ItemInit;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {
    @SubscribeEvent
    public static void onEntitySpawnFinal(MobSpawnEvent.FinalizeSpawn event) {
        LivingEntity entity = event.getEntity();
        Difficulty difficulty = event.getDifficulty().getDifficulty();
        int chance = ServerConfig.SPAWN_WITH_ARMOR_CHANCE.get();
        boolean canSpawn = !ServerConfig.SPAWN_ONLY_IN_HARD.get() || difficulty == Difficulty.HARD;
        if (chance != 0 && canSpawn && chance >= (int) (Math.random() * 100)) {
            if (entity instanceof Zombie || entity instanceof Skeleton) {
                entity.setItemSlot(EquipmentSlot.HEAD, ItemInit.MODERN_HELMET.get().getDefaultInstance());
                entity.setItemSlot(EquipmentSlot.CHEST, ItemInit.MODERN_CHESTPLATE.get().getDefaultInstance());
                entity.setItemSlot(EquipmentSlot.LEGS, ItemInit.MODERN_LEGGINGS.get().getDefaultInstance());
                entity.setItemSlot(EquipmentSlot.FEET, ItemInit.MODERN_BOOTS.get().getDefaultInstance());
            }
        }
    }
}