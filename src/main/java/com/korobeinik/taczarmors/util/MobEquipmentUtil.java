package com.korobeinik.taczarmors.util;

import com.korobeinik.taczarmors.items.armor.CombatArmorItem;
import com.korobeinik.taczarmors.init.ItemInit;
import com.korobeinik.taczarmors.items.armor.PoweredCombatArmorItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MobEquipmentUtil {
    public void setEquipment(List<ItemStack> list){

    }

    public static @NotNull ItemStack getRandomNbt(@NotNull Item item){
        return item.getDefaultInstance();
    }

    public static @Nullable CombatArmorItem tryGetCombatArmor(@NotNull LivingEntity entity){
        Iterable<ItemStack> iterable = entity.getArmorSlots();
        for (ItemStack stack : iterable) {
            if (!stack.isEmpty() && stack.getItem() instanceof CombatArmorItem) return (CombatArmorItem) stack.getItem();
        }
        return null;
    }

    private enum ArmorSet{
        SOLDIER(getRandomNbt(ItemInit.MODERN_HELMET.get()), getRandomNbt(ItemInit.MODERN_CHESTPLATE.get()), getRandomNbt(ItemInit.MODERN_LEGGINGS.get()), getRandomNbt(ItemInit.MODERN_BOOTS.get()), 1);
        ArmorSet(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, int weight){

        }
    }
}
