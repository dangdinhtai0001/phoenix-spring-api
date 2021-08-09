package com.phoenix.api.base.controller;

import com.phoenix.api.core.controller.AbstractBaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("CommonController")
@RequestMapping("/common")
public class CommonController extends AbstractBaseController {

    @GetMapping("/ping")
    public ResponseEntity ping(@RequestParam(value = "sort", required = false) List<String> sort) {
        System.out.println(sort.get(0));
        return sendResponse("ping");
    }
}
