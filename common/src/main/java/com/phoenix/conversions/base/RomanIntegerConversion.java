package com.phoenix.conversions.base;

import java.util.Arrays;

public class RomanIntegerConversion {
    private static int[] allArabianRomanNumbers =
            {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] allRomanNumbers =
            {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * This function convert Roman number into Integer
     *
     * @param num : integer umber
     * @return roman number string
     * <p>
     * Value must be > 0
     */
    public static String integer2Roman(int num) {
        if (num <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (int a = 0; a < allArabianRomanNumbers.length; a++) {
            int times = num / allArabianRomanNumbers[a];
            for (int b = 0; b < times; b++) {
                builder.append(allRomanNumbers[a]);
            }

            num -= times * allArabianRomanNumbers[a];
        }

        return builder.toString();
    }

    /**
     * This function convert Roman number into Integer
     *
     * @param romanNumber Roman number string
     * @return integer
     */
    public static int Roman2Integer(String romanNumber) {
        romanNumber = romanNumber.toUpperCase();

        char prev = ' ';
        int sum = 0;
        int newPrev = 0;

        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            char c = romanNumber.charAt(i);

            if (prev != ' ') {
                //checking current Number greater then previous or not
                if (getArabianNumberFromRomanChar(prev) > newPrev) {
                    newPrev = getArabianNumberFromRomanChar(prev);
                }
            }

            int currentNum = getArabianNumberFromRomanChar(c);

            // if current number greater then prev max previous then add
            if (currentNum >= newPrev) {
                sum += currentNum;
            } else {
                // subtract upcoming number until upcoming number not greater then prev max
                sum -= currentNum;
            }

            prev = c;
        }

        return sum;
    }


    private static int getArabianNumberFromRomanChar(char c) {
        try {
            int index = Arrays.asList(allRomanNumbers).indexOf(String.valueOf(c));
            return allArabianRomanNumbers[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

}
