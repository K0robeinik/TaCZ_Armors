package com.korobeinik.taczarmors.util;

public class ColorUtil {
    public static String intToHex(int x){
        return Integer.toHexString(x).toUpperCase();
    }

    public static int mixColors(int a, int b){
        int result = (a+b)/2;
        return result;
    }

    public static int average(int a, int b){
        return (a+b)/2;
    }
}
