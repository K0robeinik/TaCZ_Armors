package com.korobeinik.taczarmors.util;

import java.awt.*;

public class ColorUtil {
    public static String intToHex(int x){
        return String.format("#%06X", (0xFFFFFF & x));
    }

    public static int hexToInt(String h){
        return Integer.parseInt(h.substring(1), 16);
    }

    public static int mixColors(Color a, Color b){
        int r = average(a.getRed(), b.getRed());
        int g = average(a.getGreen(), b.getGreen());
        int bl = average(a.getBlue(), b.getBlue());
        return new Color(r, g, bl).getRGB();
    }

    public static int average(int a, int b){
        return (a+b)/2;
    }

    public static int average(int a, int b, int c){
        return (a+b)/c;
    }
}
