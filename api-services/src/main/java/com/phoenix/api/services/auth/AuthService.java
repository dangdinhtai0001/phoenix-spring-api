/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 6:07 PM
 */

package com.phoenix.api.services.auth;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity login(Object payload);
}
