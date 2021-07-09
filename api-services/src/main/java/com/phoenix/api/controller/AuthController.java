/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 4:32 PM
 */

package com.phoenix.api.controller;

import com.phoenix.api.base.component.exception.DefaultHandlerException;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.controller.AbstractBaseController;
import com.phoenix.api.services.auth.AuthServiceImp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController(value = BeanIds.AUTH_CONTROLLER)
@RequestMapping(value = "/auth")
public class AuthController extends AbstractBaseController {

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
    public ResponseEntity login(@RequestBody Map loginRequest, HttpSession session) throws DefaultHandlerException {
        return authService.login(loginRequest, session);
    }
}
