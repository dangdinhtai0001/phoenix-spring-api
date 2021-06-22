/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 4:32 PM
 */

package com.phoenix.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/auth")
public class AuthController {

    @GetMapping("/ping")
    public ResponseEntity ping(){
        return new ResponseEntity("pong",HttpStatus.OK);
    }
}
