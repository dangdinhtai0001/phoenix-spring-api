package com.phoenix.api.base.model;

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
