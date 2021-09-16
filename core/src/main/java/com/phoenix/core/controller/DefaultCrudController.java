package com.phoenix.core.controller;

import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.query.SearchCriteriaRequest;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public interface DefaultCrudController {
    ResponseEntity create(Object entity);

    ResponseEntity createAll(Collection entities);

    ResponseEntity update(Object entity);

    ResponseEntity delete(Object entity);

    ResponseEntity deleteAll(Collection entities);

    ResponseEntity findByCondition(List<SearchCriteriaRequest> conditions, int pageOffset, int pageSize, List<String> orderByKeys, String direction)
            throws ApplicationException;

    ResponseEntity countByCondition(LinkedList<SearchCriteriaRequest> conditions) throws ApplicationException;
}
