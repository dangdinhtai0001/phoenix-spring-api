/*
 * @Author Đặng Đình Tài
 * @Date 6/28/21, 2:06 PM
 */

package com.phoenix.api.controller.base;

import com.phoenix.api.entities.base.BaseEntity;
import com.phoenix.api.services.base.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public abstract class CrudController<T extends BaseEntity> extends AbstractController implements BaseController<T> {

    private final CrudService<T> service;

    protected CrudController(
            CrudService<T> service
    ) {
        this.service = service;
    }

    @PostMapping(value = "/add")
    @Override
    public ResponseEntity add(@RequestBody Map payload) throws Exception {
        return successResponse(service.add(payload));
    }

    @PostMapping(value = "/update")
    @Override
    public ResponseEntity update(@RequestBody Map payload) throws Exception {
        return successResponse(service.update(payload));
    }

    @PostMapping(value = "/remove")
    @Override
    public ResponseEntity remove(@RequestBody Map payload) throws Exception {
        return successResponse(service.remove(payload));
    }

    @GetMapping(value = "/all")
    @Override
    public ResponseEntity findAll() {
        return successResponse(service.findAll());
    }

    @GetMapping(value = "/find")
    @Override
    public ResponseEntity findById(@RequestParam("id") Long id) throws Exception {
        return successResponse(service.findById(id));
    }
}
