package com.phoenix.text;

public class TextUtil {
    /**
     * Alphabetical order is a system whereby character strings are placed in order based on the
     * position of the characters in the conventional ordering of an alphabet. Wikipedia:
     * https://en.wikipedia.org/wiki/Alphabetical_order
     * <p>
     * Check if a string is alphabetical order or not
     *
     * @param s a string
     * @return {@code true} if given string is alphabetical order, otherwise {@code false}
     */
    public static boolean isAlphabetical(String s) {
        s = s.toLowerCase();
        for (int i = 0; i < s.length() - 1; ++i) {
            if (!Character.isLetter(s.charAt(i)) || !(s.charAt(i) <= s.charAt(i + 1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * check if all the characters of a string are same
     *
     * @param s the string to check
     * @return {@code true} if all characters of a string are same, otherwise {@code false}
     */
    public static boolean isAllCharactersSame(String s) {
        for (int i = 1, length = s.length(); i < length; ++i) {
            if (s.charAt(i) != s.charAt(0)) {
                return false;
            }
        }
        return true;
    }
}
