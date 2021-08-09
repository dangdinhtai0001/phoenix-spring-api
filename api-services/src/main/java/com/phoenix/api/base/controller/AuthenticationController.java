package com.phoenix.api.base.controller;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.service.AuthenticationService;
import com.phoenix.api.core.controller.AbstractBaseController;
import com.phoenix.api.core.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController("AuthenticationController")
@RequestMapping("/auth")
public class AuthenticationController extends AbstractBaseController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(
            @Qualifier(BeanIds.AUTHENTICATION_SERVICES) AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map loginRequest, HttpSession session) throws ApplicationException {
        return sendResponse(authenticationService.login(loginRequest, session));
    }

    @GetMapping("/profile")
    public ResponseEntity findProfile(HttpServletRequest request) {
        return sendResponse(authenticationService.findProfile(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@RequestBody Map refreshTokenRequest, HttpSession session) throws ApplicationException {
        return sendResponse(authenticationService.refreshToken(refreshTokenRequest, session));
    }

}
