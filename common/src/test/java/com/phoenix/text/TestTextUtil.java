/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.text;

import com.phoenix.common.util.TextUtil;
import org.junit.Test;

public class TestTextUtil {
    @Test
    public void testAlphabetical() {
        assert !TextUtil.isAlphabetical("123abc");
        assert TextUtil.isAlphabetical("aBC");
        assert TextUtil.isAlphabetical("abc");
        assert !TextUtil.isAlphabetical("xyzabc");
        assert TextUtil.isAlphabetical("abcxyz");
    }
}
