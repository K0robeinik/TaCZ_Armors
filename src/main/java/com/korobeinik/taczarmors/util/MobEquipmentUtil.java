package com.korobeinik.taczarmors.util;

import com.korobeinik.taczarmors.init.ArmorItemInit;
import com.korobeinik.taczarmors.items.armor.CombatArmorItem;
import com.korobeinik.taczarmors.init.ItemInit;
import com.korobeinik.taczarmors.items.armor.PoweredCombatArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
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

    public static void dressEntity(LivingEntity entity, Item helmet, Item chestplate, Item leggings, Item boots) {
        dressEntity(entity, helmet.getDefaultInstance(), chestplate.getDefaultInstance(), leggings.getDefaultInstance(), boots.getDefaultInstance());
    }

    public static void dressEntity(LivingEntity entity, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        entity.setItemSlot(EquipmentSlot.HEAD, helmet);
        entity.setItemSlot(EquipmentSlot.CHEST, chestplate);
        entity.setItemSlot(EquipmentSlot.LEGS, leggings);
        entity.setItemSlot(EquipmentSlot.FEET, boots);
    }

    private enum ArmorSet{
        SOLDIER(getRandomNbt(ArmorItemInit.MODERN_HELMET.get()), getRandomNbt(ArmorItemInit.MODERN_CHESTPLATE.get()), getRandomNbt(ArmorItemInit.MODERN_LEGGINGS.get()), getRandomNbt(ArmorItemInit.MODERN_BOOTS.get()), 1);
        ArmorSet(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, int weight){

        }
    }
}
