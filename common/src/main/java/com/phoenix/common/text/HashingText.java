/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.text;

import org.apache.commons.codec.digest.DigestUtils;

public class HashingText {
    public static String hashingSha256(String original){
        return DigestUtils.sha256Hex(original);
    }
}
