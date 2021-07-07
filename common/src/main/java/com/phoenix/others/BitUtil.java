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


    @Deprecated
    public static int[] getAllBitOnePosition(int decimal) {
        return getAllBitOnePosition(decimal, String.valueOf(decimal).length());
    }

    public static int[] getAllBitOnePosition(int decimal, int length) {
        String[] bit = Integer.toBinaryString(decimal).split("");

        int[] arr = new int[length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            arr[i] = -1;
            try {
                if (Integer.parseInt(bit[i]) > 0) {
                    arr[index] = i;
                    index++;
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                arr[index] = -1;
                index++;
            }
        }

        return arr;
    }
}
