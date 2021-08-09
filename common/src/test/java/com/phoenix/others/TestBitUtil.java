/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.others;

import com.phoenix.common.util.BitUtil;
import org.junit.Test;

public class TestBitUtil {
    @Test
    public void testGetAllBitOnePosition() {
        int[] array = BitUtil.getAllBitOnePosition(1,3);

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
