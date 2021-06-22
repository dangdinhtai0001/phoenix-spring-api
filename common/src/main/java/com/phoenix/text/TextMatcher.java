package com.phoenix.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextMatcher {

    /**
     * Will match dates with dashes, slashes or with spaces (e.g. dd-mm-yyyy dd/mm/yyyy dd mm yyyy), and optional time
     * separated by a space or a dash (e.g. dd-mm-yyyy-hh:mm:ss or dd/mm/yyyy hh:mm:ss).
     */
    private static final String DATE_IN_FORMAT = "^(0?[1-9]|[12][0-9]|3[01])([ \\/\\-])(0?[1-9]|1[012])\\2([0-9][0-9][0-9][0-9])(([ -])([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9])?$";

    /**
     * Match times in 24 hour format
     */
    private static final String TIME_IN_24H_FORMAT = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";

    /**
     * RGB hex colors
     */
    private static final String HEX_COLOR_VALUE = "^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";

    /**
     *
     */
    private static final String EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    //===============================================================================================================
    //
    //===============================================================================================================

    public static boolean isDateInFormat(String raw) {
        return matchString(DATE_IN_FORMAT, raw);
    }

    public static boolean isTimeIn24HFormat(String raw) {
        return matchString(TIME_IN_24H_FORMAT, raw);
    }

    public static boolean isHexColor(String raw) {
        return matchString(HEX_COLOR_VALUE, raw);
    }

    public static boolean isEmailAddress(String raw){
        return matchString(EMAIL, raw);
    }

    //===============================================================================================================
    //
    //===============================================================================================================

    private static boolean matchString(String regex, String raw) {
        if (raw == null || raw.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(raw);
        return matcher.matches();
    }
}
