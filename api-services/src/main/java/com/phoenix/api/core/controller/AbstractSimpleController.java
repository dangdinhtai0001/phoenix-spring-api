package com.phoenix.api.core.controller;

import com.phoenix.api.core.exception.ApplicationException;
import com.phoenix.api.core.model.SearchCriteriaRequest;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSimpleController extends AbstractBaseController implements DefaultController {
    @Override
    public ResponseEntity create(Object entity) {
        return null;
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
    public ResponseEntity findByCondition(List<SearchCriteriaRequest> conditions, int pageOffset, int pageSize, List<String> orderByKeys, String direction) throws ApplicationException {
        return null;
    }

    @Override
    public ResponseEntity countByCondition(LinkedList<SearchCriteriaRequest> conditions) throws ApplicationException {
        return null;
    }
}
