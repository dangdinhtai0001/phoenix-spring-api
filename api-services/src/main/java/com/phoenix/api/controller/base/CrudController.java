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
    public ResponseEntity add(Map payload) {
        return null;
    }

    @Override
    public ResponseEntity update(Map payload) {
        return null;
    }

    @Override
    public ResponseEntity remove(Map payload) {
        return null;
    }

    @Override
    public ResponseEntity findAll() {
        return null;
    }

    @Override
    public ResponseEntity findById() {
        return null;
    }
}
