package com.phoenix.text;

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
