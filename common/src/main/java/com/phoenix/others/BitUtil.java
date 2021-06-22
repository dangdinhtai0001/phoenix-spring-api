package com.phoenix.others;

public class BitUtil {
    public static int[] convertDecimal2BitArray(int decimal) {
        String[] bit = Integer.toBinaryString(decimal).split("");

        int[] arr = new int[bit.length];
        for (int i = 0; i < bit.length; i++) {
            arr[i] = Integer.parseInt(bit[i]);
        }

        return arr;
    }

    public static int[] getAllBitOnePosition(int decimal) {
        String[] bit = Integer.toBinaryString(decimal).split("");

        int[] arr = new int[bit.length];
        int index = 0;
        for (int i = 0; i < bit.length; i++) {
            arr[i] = -1;
            if (Integer.parseInt(bit[i]) > 0) {
                arr[index] = i;
                index++;
            }
        }

        return arr;
    }
}
