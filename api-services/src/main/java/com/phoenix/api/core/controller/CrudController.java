package com.phoenix.api.core.controller;

import com.phoenix.api.core.entity.BaseEntity;
import com.phoenix.api.core.model.SearchCriteria;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudController<T extends BaseEntity<ID>, ID extends Serializable> {
    ResponseEntity add(T entity) throws Exception;

    ResponseEntity addAll(List<T> entities) throws Exception;

    ResponseEntity update(T entity) throws Exception;

    ResponseEntity delete(T entity) throws Exception;

    ResponseEntity deleteById(ID id) throws Exception;

    ResponseEntity deleteAllById(List<ID> ids) throws Exception;

    ResponseEntity findAll();

    ResponseEntity count();

    ResponseEntity findById(ID id) throws Exception;

    ResponseEntity findAllById(List<ID> ids) throws Exception;

    ResponseEntity findBy(int page, int size, List<SearchCriteria> conditional) throws Exception;
}
