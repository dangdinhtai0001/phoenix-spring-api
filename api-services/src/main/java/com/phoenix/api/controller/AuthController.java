/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 4:32 PM
 */

package com.phoenix.api.controller;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.model.payload.LoginRequest;
import com.phoenix.api.services.auth.AuthService;
import com.phoenix.api.services.auth.AuthServiceImp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            @Qualifier(BeanIds.AUTH_SERVICES) AuthServiceImp authServiceImp) {
        this.authService = authServiceImp;
    }

    @GetMapping("/ping")
    public ResponseEntity ping() {
        return new ResponseEntity("pong", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity(authService.login(loginRequest), HttpStatus.OK);
    }
}
