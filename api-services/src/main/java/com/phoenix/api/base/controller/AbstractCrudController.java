/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:35 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/28/21, 2:06 PM
 */

package com.phoenix.api.base.controller;

import com.phoenix.api.base.entities.BaseEntity;
import com.phoenix.api.base.services.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @param <T> Đối tượng Entity để map với bảng trong csdl
 */
public abstract class AbstractCrudController<T extends BaseEntity> extends AbstractBaseController implements CrudController<T> {

    private final CrudService<T> service;

    protected AbstractCrudController(
            CrudService<T> service
    ) {
        this.service = service;
    }

    @PostMapping(value = "/add")
    @Override
    public ResponseEntity add(@RequestBody Map payload) throws Exception {
        return sendResponse(service.add(payload));
    }

    @PostMapping(value = "/update")
    @Override
    public ResponseEntity update(@RequestBody Map payload) throws Exception {
        return sendResponse(service.update(payload));
    }

    @PostMapping(value = "/remove")
    @Override
    public ResponseEntity remove(@RequestBody Map payload) throws Exception {
        return sendResponse(service.remove(payload));
    }

    @GetMapping(value = "/all")
    @Override
    public ResponseEntity findAll() {
        return sendResponse(service.findAll());
    }

    @GetMapping(value = "/find")
    @Override
    public ResponseEntity findById(@RequestParam("id") Long id) throws Exception {
        return sendResponse(service.findById(id));
    }
}
