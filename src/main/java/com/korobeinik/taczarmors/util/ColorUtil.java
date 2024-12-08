package com.korobeinik.taczarmors.util;

import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ColorUtil {
    public static final int[] dyeColor = new int[]{
            16383998,
            16351261,
            13061821,
            3847130,
            16701501,
            8439583,
            15961002,
            4673362,
            10329495,
            1481884,
            8991416,
            3949738,
            8606770,
            6192150,
            11546150,
            1908001
    };

    public static String intToHex(int x){
        return String.format("#%06X", (0xFFFFFF & x));
    }

    public static int hexToInt(@NotNull String h){
        return Integer.parseInt(h.substring(1), 16);
    }

    public static int mixColors(@NotNull Color a, @NotNull Color b){
        int r = average(a.getRed(), b.getRed());
        int g = average(a.getGreen(), b.getGreen());
        int bl = average(a.getBlue(), b.getBlue());
        return new Color(r, g, bl).getRGB();
    }

    public static int average(int a, int b){
        return average(a, b, 2);
    }

    public static int average(int a, int b, int c){
        return (a+b)/c;
    }
}
