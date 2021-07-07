package com.phoenix.others;

import org.junit.Test;

public class TestBitUtil {
    @Test
    public void testGetAllBitOnePosition() {
        int[] array = BitUtil.getAllBitOnePosition(1,10);

        for (int i : array) {
            System.out.println(i);
        }
    }

    @Test
    public void testConvertDecimal2BitArray() {
        int[] array = BitUtil.convertDecimal2BitArray(1);

        for (int i : array) {
            System.out.println(i);
        }
    }
}
