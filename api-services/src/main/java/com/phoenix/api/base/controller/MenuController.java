package com.phoenix.api.base.controller;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.service.MenuService;
import com.phoenix.api.core.controller.AbstractBaseController;
import com.phoenix.api.core.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("MenuController")
@RequestMapping("/menu")
public class MenuController extends AbstractBaseController {

    private final MenuService menuService;

    public MenuController(
            @Qualifier(BeanIds.MENU_SERVICES) MenuService menuService
    ) {
        this.menuService = menuService;
    }

    @GetMapping("/all")
    public ResponseEntity findAll() throws ApplicationException {
        return sendResponse(menuService.findAll());
    }
}
