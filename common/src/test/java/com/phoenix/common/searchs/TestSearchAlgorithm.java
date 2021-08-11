/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.searchs;

import com.phoenix.common.searchs.imp.BinarySearch;
import com.phoenix.common.searchs.imp.IterativeBinarySearch;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.format;

public class TestSearchAlgorithm {
    @Test
    public void testBinarySearch() {
        // Just generate data
        Random r = ThreadLocalRandom.current();

        int size = 100;
        int maxElement = 100000;

        Integer[] integers =
                IntStream.generate(() -> r.nextInt(maxElement))
                        .limit(size)
                        .sorted()
                        .boxed()
                        .toArray(Integer[]::new);

        // The element that should be found
        int shouldBeFound = integers[r.nextInt(size - 1)];

        SearchAlgorithm search = new BinarySearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.println(
                format(
                        "Should be found: %d. Found %d at index %d. An array length %d",
                        shouldBeFound, integers[atIndex], atIndex, size));

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(
                format(
                        "Found by system method at an index: %d. Is equal: %b", toCheck, toCheck == atIndex));
    }

    @Test
    public void testIterativeBinarySearch() {
        Random r = new Random();
        int size = 100;
        int maxElement = 100000;
        Integer[] integers =
                Stream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().toArray(Integer[]::new);

        // the element that should be found
        Integer shouldBeFound = integers[r.nextInt(size - 1)];

        SearchAlgorithm search = new IterativeBinarySearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.println(
                String.format(
                        "Should be found: %d. Found %d at index %d. An array length %d",
                        shouldBeFound, integers[atIndex], atIndex, size));

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(
                format(
                        "Found by system method at an index: %d. Is equal: %b", toCheck, toCheck == atIndex));
    }

}

