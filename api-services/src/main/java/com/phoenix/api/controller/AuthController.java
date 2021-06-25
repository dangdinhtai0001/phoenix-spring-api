/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 4:32 PM
 */

package com.phoenix.api.controller;

import com.phoenix.api.component.exception.DefaultHandlerException;
import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.controller.base.BaseController;
import com.phoenix.api.services.auth.AuthServiceImp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping(value = "/auth")
public class AuthController extends BaseController {

    private final AuthServiceImp authService;

    public AuthController(
            @Qualifier(BeanIds.AUTH_SERVICES) AuthServiceImp authServiceImp) {
        this.authService = authServiceImp;
    }

    @GetMapping("/ping")
    public ResponseEntity ping() {
        return new ResponseEntity("pong", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Object loginRequest, HttpSession session) throws DefaultHandlerException {
        return authService.login(loginRequest, session);
    }

    @GetMapping("/current")
    public ResponseEntity currentSession(HttpSession session) {
        return authService.getCurrentSession(session);
    }
}
