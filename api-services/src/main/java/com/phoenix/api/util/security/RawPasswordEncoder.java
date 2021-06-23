/*
 * @Author Đặng Đình Tài
 * @Date 6/23/21, 10:20 AM
 */

package com.phoenix.api.util.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class RawPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
