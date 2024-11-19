package com.korobeinik.taczarmors.util;

import com.korobeinik.taczarmors.init.ItemInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class MobEquipmentUtil {
    public void setEquipment(List<ItemStack> list){

    }

    public static ItemStack getRandomNbt(Item item){
        return item.getDefaultInstance();
    }

    private enum ArmorSet{
        SOLDIER(getRandomNbt(ItemInit.MODERN_HELMET.get()), getRandomNbt(ItemInit.MODERN_CHESTPLATE.get()), getRandomNbt(ItemInit.MODERN_LEGGINGS.get()), getRandomNbt(ItemInit.MODERN_BOOTS.get()), 1);
        private ArmorSet(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, int weight){

        }
    }
}
