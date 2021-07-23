package com.phoenix.api.base.controller;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.service.AuthService;
import com.phoenix.api.base.service.imp.AuthServiceImp;
import com.phoenix.api.core.controller.AbstractBaseController;
import com.phoenix.api.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public ResponseEntity login(@RequestBody Map loginRequest, HttpSession session) throws ServiceException {
        return sendResponse(authService.login(loginRequest, session));
    }
}
