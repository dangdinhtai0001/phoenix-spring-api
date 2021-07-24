package com.phoenix.api.core.controller;

import com.phoenix.api.core.model.SearchCriteria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController {
    ResponseEntity add(Object entity);

    ResponseEntity addAll(List entities);

    ResponseEntity update(Object entity);

    ResponseEntity delete(Object entity);

    ResponseEntity deleteById(Object id);

    ResponseEntity deleteAllById(List ids);

    ResponseEntity findBy(List<SearchCriteria> conditions, int page, int size);

    ResponseEntity count(List<SearchCriteria> conditions);
}
