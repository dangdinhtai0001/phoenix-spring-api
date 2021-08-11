/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.base;

import org.junit.Test;

import java.util.Arrays;

public class TestRgbHsvConversion {
    @Test
    public void testRgbHsvConversion() {
        // Expected RGB-values taken from https://www.rapidtables.com/convert/color/hsv-to-rgb.html

        // Test RgbHsvConversion.hsvToRgb-method
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(0, 0, 0), new int[]{0, 0, 0});
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(0, 0, 1), new int[]{255, 255, 255});
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(0, 1, 1), new int[]{255, 0, 0});
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(60, 1, 1), new int[]{255, 255, 0});
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(120, 1, 1), new int[]{0, 255, 0});
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(240, 1, 1), new int[]{0, 0, 255});
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(300, 1, 1), new int[]{255, 0, 255});
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(180, 0.5, 0.5), new int[]{64, 128, 128});
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(234, 0.14, 0.88), new int[]{193, 196, 224});
        assert Arrays.equals(RgbHsvConversion.hsvToRgb(330, 0.75, 0.5), new int[]{128, 32, 80});

        // Test RgbHsvConversion.rgbToHsv-method
        // approximate-assertions needed because of small deviations due to converting between
        // int-values and double-values.
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(0, 0, 0), new double[]{0, 0, 0});
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(255, 255, 255), new double[]{0, 0, 1});
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(255, 0, 0), new double[]{0, 1, 1});
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(255, 255, 0), new double[]{60, 1, 1});
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(0, 255, 0), new double[]{120, 1, 1});
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(0, 0, 255), new double[]{240, 1, 1});
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(255, 0, 255), new double[]{300, 1, 1});
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(64, 128, 128), new double[]{180, 0.5, 0.5});
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(193, 196, 224), new double[]{234, 0.14, 0.88});
        assert RgbHsvConversion.approximatelyEqualHsv(RgbHsvConversion.rgbToHsv(128, 32, 80), new double[]{330, 0.75, 0.5});
    }
}
