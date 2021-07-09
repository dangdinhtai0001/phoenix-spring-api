package com.phoenix.others;

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
