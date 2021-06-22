package com.phoenix.text;

import org.apache.commons.codec.digest.DigestUtils;

public class HashingText {
    public static String hashingSha256(String original){
        return DigestUtils.sha256Hex(original);
    }
}
