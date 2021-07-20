/*
 * @Author: Đặng Đình Tài
 * @Created date: 7/15/21, 10:12 AM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.util;

import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtil {
    public static String generateStringFromList(List<String> list, String delimiter, String prefix, String suffix) {
        return list.stream()
                .map(s -> String.valueOf(s))
                .collect(Collectors.joining(delimiter, prefix, suffix));
    }

    public static String generateStringFromList(List<String> list, String delimiter) {
        return generateStringFromList(list, delimiter, "", "");
    }
}
