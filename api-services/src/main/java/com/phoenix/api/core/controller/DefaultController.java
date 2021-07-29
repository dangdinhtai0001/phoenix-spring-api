package com.phoenix.api.core.controller;

import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchCriteriaRequest;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public interface DefaultController {
    ResponseEntity create(Object entity);

    ResponseEntity createAll(Collection entities);

    ResponseEntity update(Object entity);

    ResponseEntity delete(Object entity);

    ResponseEntity deleteAll(Collection entities);

    ResponseEntity findByCondition(List<SearchCriteria> conditions, int pageOffset, int pageSize);

    ResponseEntity countByCondition(LinkedList<SearchCriteriaRequest> conditions);
}
