package com.korobeinik.taczarmors.content;

import com.korobeinik.taczarmors.util.ColorUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class ColorBottle extends Item implements DyeableLeatherItem {
    public ColorBottle(Properties pProperties) {
        super(pProperties);
    }

    public int getColor(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : 0;
    }

    public static int getItemColor(ItemStack stack, int tint){ return tint > 0 ? 0xFFFFFF : ((ColorBottle)stack.getItem()).getColor(stack);}

//    static ItemStack dyeArmor(ItemStack pStack, List<DyeItem> pDyes) {
//        ItemStack resultStack = ItemStack.EMPTY;
//        int[] colorArray = new int[3];
//        int $$4 = 0;
//        int $$5 = 0;
//        Item item = pStack.getItem();
//        int $$17;
//        float $$20;
//        int $$16;
//        if (item instanceof DyeableLeatherItem leatherItem) {
//            resultStack = pStack.copyWithCount(1);
//            if (leatherItem.hasCustomColor(pStack)) {
//                $$17 = leatherItem.getColor(resultStack);
//                float $$9 = (float)($$17 >> 16 & 255) / 255.0F;
//                float $$10 = (float)($$17 >> 8 & 255) / 255.0F;
//                $$20 = (float)($$17 & 255) / 255.0F;
//                $$4 += (int)(Math.max($$9, Math.max($$10, $$20)) * 255.0F);
//                colorArray[0] += (int)($$9 * 255.0F);
//                colorArray[1] += (int)($$10 * 255.0F);
//                colorArray[2] += (int)($$20 * 255.0F);
//                ++$$5;
//            }
//
//            for(Iterator<DyeItem> var14 = pDyes.iterator(); var14.hasNext(); ++$$5) {
//                DyeItem $$12 = (DyeItem)var14.next();
//                float[] $$13 = $$12.getDyeColor().getTextureDiffuseColors();
//                int $$14 = (int)($$13[0] * 255.0F);
//                int $$15 = (int)($$13[1] * 255.0F);
//                $$16 = (int)($$13[2] * 255.0F);
//                $$4 += Math.max($$14, Math.max($$15, $$16));
//                colorArray[0] += $$14;
//                colorArray[1] += $$15;
//                colorArray[2] += $$16;
//            }
//        }
//
//        /*if ($$6 == null) {
//            return ItemStack.EMPTY;
//        } else {
//            $$17 = $$3[0] / $$5;
//            int $$18 = $$3[1] / $$5;
//            int $$19 = $$3[2] / $$5;
//            $$20 = (float)$$4 / (float)$$5;
//            float $$21 = (float)Math.max($$17, Math.max($$18, $$19));
//            $$17 = (int)((float)$$17 * $$20 / $$21);
//            $$18 = (int)((float)$$18 * $$20 / $$21);
//            $$19 = (int)((float)$$19 * $$20 / $$21);
//            $$16 = ($$17 << 8) + $$18;
//            $$16 = ($$16 << 8) + $$19;
//            $$6.setColor(resultStack, $$16);
//            return resultStack;
//        }*/
//        return resultStack;
//    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag pIsAdvanced) {
        //list.add(Component.literal("Color: "+ ColorUtil.intToHex(getColor(stack))).withStyle(ChatFormatting.GRAY));
        //list.add(Component.literal("Color: "+ ColorUtil.hexToInt(ColorUtil.intToHex(getDyeColor(stack)))).withStyle(ChatFormatting.GRAY)); for reverse function testing
        super.appendHoverText(stack, pLevel, list, pIsAdvanced);
    }
}
