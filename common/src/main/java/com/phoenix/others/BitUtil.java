package com.phoenix.others;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    /**
     * @param decimal : số ở dạng decimal
     * @param length  : độ dài của chuỗi bit
     * @return 1 mảng int có dạng arr[-1, -1, -1, -1, 1,-1]
     * trong đó, arr[i] == 1 <=> bit tại vị trí đó bằng 1 và -1 nếu bit tại đó bằng 0
     */
    public static int[] getAllBitOnePosition(int decimal, int length) {
        String binaryString = String.format("%" + length + "s", Integer.toBinaryString(decimal)).replaceAll(" ", "0");
        int[] arr = new int[length];
        String[] bit = Arrays.copyOf(binaryString.split(""), length);

        for (int i = 0; i < length; i++) {
            if (bit[i] != null && Integer.parseInt(bit[i]) > 0) {
                arr[length - 1 - i] = Integer.parseInt(bit[i]);
            } else {
                arr[length - 1 - i] = -1;
            }
        }
        return arr;
    }
}
