package com.korobeinik.taczarmors.events;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.config.ServerConfig;
import com.korobeinik.taczarmors.init.ArmorItemInit;
import com.korobeinik.taczarmors.init.ItemInit;
import com.korobeinik.taczarmors.util.MobEquipmentUtil;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {
    @SubscribeEvent
    public static void onEntitySpawnFinal(MobSpawnEvent.@NotNull FinalizeSpawn event) {
        LivingEntity entity = event.getEntity();
        Difficulty difficulty = event.getDifficulty().getDifficulty();
        int chance = ServerConfig.SPAWN_WITH_ARMOR_CHANCE.get();
        boolean canSpawn = !ServerConfig.SPAWN_ONLY_IN_HARD.get() || difficulty == Difficulty.HARD;
        if (chance != 0 && canSpawn && chance >= (int) (Math.random() * 100)) {
            if (entity instanceof Zombie || entity instanceof Skeleton) {
                MobEquipmentUtil.dressEntity(entity, ArmorItemInit.MODERN_HELMET.get(), ArmorItemInit.MODERN_CHESTPLATE.get(), ArmorItemInit.MODERN_LEGGINGS.get(), ArmorItemInit.MODERN_BOOTS.get());
            }
        }
    }
}
