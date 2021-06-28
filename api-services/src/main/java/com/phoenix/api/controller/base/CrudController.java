/*
 * @Author Đặng Đình Tài
 * @Date 6/28/21, 2:06 PM
 */

package com.phoenix.api.controller.base;

import com.phoenix.api.entities.base.BaseEntity;
import com.phoenix.api.services.base.CrudService;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public abstract class CrudController<T extends BaseEntity> extends AbstractController implements BaseController<T> {

    private final CrudService<T> service;

    protected CrudController(
            CrudService<T> service
    ) {
        this.service = service;
    }

    @Override
    public ResponseEntity add(Map payload) throws Exception {
        return successResponse(service.add(payload));
    }

    @Override
    public ResponseEntity update(Map payload) throws Exception {
        return successResponse(service.update(payload));
    }

    @Override
    public ResponseEntity remove(Map payload) throws Exception {
        return successResponse(service.remove(payload));
    }

    @Override
    public ResponseEntity findAll() {
        return successResponse(service.findAll());
    }

    @Override
    public ResponseEntity findById(Long id) throws Exception {
        return successResponse(service.findById(id));
    }
}
