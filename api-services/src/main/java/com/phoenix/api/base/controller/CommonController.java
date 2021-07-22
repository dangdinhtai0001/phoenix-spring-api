package com.phoenix.api.base.controller;

import com.phoenix.api.core.controller.AbstractBaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("CommonController")
@RequestMapping("/common")
public class CommonController extends AbstractBaseController {

    @GetMapping("/ping")
    public ResponseEntity ping(@RequestBody Object parameter) {
        System.out.println(parameter);
        return sendResponse("ping");
    }
}
