package com.korobeinik.taczarmors.content;

import com.korobeinik.taczarmors.util.ColorUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ColorBottle extends Item implements DyeableLeatherItem {
    public ColorBottle(Properties pProperties) {
        super(pProperties);
    }

    public void setColor(ItemStack stack, int color) {
        CompoundTag tag = stack.getTag();
        if(tag==null){
            tag = new CompoundTag();
            stack.setTag(tag);
        }
        tag.putInt("color", color);
        stack.setTag(tag);
    }
    public static int getDyeColor(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag!=null) {
            if (tag.contains("color")) {
                return tag.getInt("color");
            }
        }
        return 0;
    }

    //public int getItemColor(ItemStack stack, int tint){ return tint > 0 ? 0xFFFFFF : getDyeColor(stack);}

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag pIsAdvanced) {
        list.add(Component.literal("Color: #"+ ColorUtil.intToHex(getDyeColor(stack))).withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, pLevel, list, pIsAdvanced);
    }


}
