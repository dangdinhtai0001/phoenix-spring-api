package com.phoenix.api.core.controller;

import com.phoenix.api.core.model.SearchCriteria;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface CrudController {
    ResponseEntity create(Object entity);

    ResponseEntity createAll(Collection entities);

    ResponseEntity update(Object entity);

    ResponseEntity delete(Object entity);

    ResponseEntity deleteAll(Collection entities);

    ResponseEntity findByCondition(List<SearchCriteria> conditions, int pageOffset, int pageSize);

    ResponseEntity countByCondition(List<SearchCriteria> conditions);
}
