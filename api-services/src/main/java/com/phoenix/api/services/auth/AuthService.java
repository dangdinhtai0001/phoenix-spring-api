/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 6:07 PM
 */

package com.phoenix.api.services.auth;

import com.phoenix.api.model.auth.Token;

public interface AuthService {
    public Token login(Object payload);
}
