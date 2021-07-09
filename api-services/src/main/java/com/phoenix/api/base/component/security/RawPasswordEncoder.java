/*
 * @Author Đặng Đình Tài
 * @Date 6/23/21, 10:20 AM
 */

package com.phoenix.api.base.component.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Implementation của PasswordEncoder. Dùng khi muốn check passwword được lwuu ở dạng raw. Chỉ nên dùng khi test.
 */
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
