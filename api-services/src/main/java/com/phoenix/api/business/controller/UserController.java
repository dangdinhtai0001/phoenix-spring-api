package com.phoenix.api.business.controller;

import com.phoenix.api.core.controller.AbstractCrudController;
import com.phoenix.api.core.model.SearchCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController("UserController")
@RequestMapping("/user")
public class UserController extends AbstractCrudController {
    @Override
    public ResponseEntity create(Object entity) {
        return sendResponse(null);
    }

    @Override
    public ResponseEntity createAll(Collection entities) {
        return null;
    }

    @Override
    public ResponseEntity update(Object entity) {
        return null;
    }

    @Override
    public ResponseEntity delete(Object entity) {
        return null;
    }

    @Override
    public ResponseEntity deleteAll(Collection entities) {
        return null;
    }

    @Override
    public ResponseEntity findByCondition(List<SearchCriteria> conditions, int pageOffset, int pageSize) {
        return null;
    }

    @Override
    public ResponseEntity countByCondition(List<SearchCriteria> conditions) {
        return null;
    }
}
