package com.phoenix.api.base.controller;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.service.AuthService;
import com.phoenix.api.core.controller.AbstractBaseController;
import com.phoenix.api.core.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

@RestController("AuthController")
@RequestMapping("/auth")
public class AuthController extends AbstractBaseController {
    private final AuthService authService;

    public AuthController(
            @Qualifier(BeanIds.AUTH_SERVICES) AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map loginRequest, HttpSession session) throws ApplicationException {
        return sendResponse(authService.login(loginRequest, session));
    }

    @GetMapping("/profile")
    public ResponseEntity findProfile(HttpServletRequest request) {
        return sendResponse(authService.findProfile(request));
    }

}
