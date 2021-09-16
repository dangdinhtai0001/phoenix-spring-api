/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.base;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TestRomanIntegerConversion {
    @Test
    public void testRomanToInteger() {
        List<String> list = new LinkedList<>();
        list.add("MDCCCIV");
        list.add("IX");
        list.add("XXI");
        list.add("XXXXXX");

        for (String str : list) {
            System.out.println(String.format("Roman: %s, int: %d", str, RomanIntegerConversion.Roman2Integer(str)));
        }
    }

    @Test
    public void testInteger2Roman() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(10);
        list.add(20);
        list.add(1000);
        list.add(800);
        list.add(804);
        list.add(4);
        list.add(1804);

        for (Integer i : list) {
            System.out.println(String.format("Int: %d, Roman: %s", i, RomanIntegerConversion.integer2Roman(i)));
        }
    }
}
