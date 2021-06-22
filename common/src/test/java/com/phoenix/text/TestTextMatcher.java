package com.phoenix.text;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TestTextMatcher {

    @Test
    public void testValidateDate() {
        List<String> list = new LinkedList<>();
        list.add("12/12/2021");
        list.add("12-12-2021");
        list.add("12/12//2021");
        list.add("56/12/2021");
        list.add("56/12/2021");
        list.add("1/12/2021");
        list.add("1/22/2021");
        list.add("1/12/21");

        for (String s : list) {
            System.out.println(String.format("String: %s, validate: %s",s, TextMatcher.isDateInFormat(s)));
        }
    }

    @Test
    public void testValidateTime() {
        List<String> list = new LinkedList<>();
        list.add("12/12/2021");
        list.add("12:13");
        list.add("12:13:66");
        list.add("12:13:60");
        list.add("12:13:50");
        list.add("12/13");
        list.add("12-13");


        for (String s : list) {
            System.out.println(String.format("String: %s, validate: %s",s, TextMatcher.isTimeIn24HFormat(s)));
        }
    }

    @Test
    public void testHexColor() {
        List<String> list = new LinkedList<>();
        list.add("#123456");
        list.add("#123456222");
        list.add("#Abc123");
        list.add("#Abcd123");
        list.add("#ABCDEF");
        list.add("#A11124");
        list.add("#A11M24");


        for (String s : list) {
            System.out.println(String.format("String: %s, validate: %s",s, TextMatcher.isHexColor(s)));
        }
    }
}
