package com.phoenix.core.service;

import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.query.SearchCriteriaRequest;

import java.util.List;

public interface DefaultCrudService {
    <T> Long create(T object, String... fields) throws ApplicationException;

    <T> Long createAll(List<T> list, String... fields) throws ApplicationException;

    <T> Long update(T object) throws ApplicationException;

    <T> Long delete(T object) throws ApplicationException;

    <T> Long deleteAll(List<T> list) throws ApplicationException;

    long countByCondition(List<SearchCriteriaRequest> listConditionRequests) throws ApplicationException;
}
