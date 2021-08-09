package com.phoenix.api.core.service;

import com.phoenix.api.core.exception.ApplicationException;
import com.phoenix.api.core.model.BasePagination;
import com.phoenix.api.core.model.SearchCriteriaRequest;

import java.util.Collection;
import java.util.List;

public interface DefaultService {
    Object create(Object entity) throws ApplicationException;

    Object update(Object entity) throws ApplicationException;

    void delete(Object entity) throws ApplicationException;

    void deleteAll(Collection entities) throws ApplicationException;

    BasePagination findByCondition(List<SearchCriteriaRequest> conditions, int pageOffset, int pageSize,
                                   List<String> orderByKeys, String direction) throws ApplicationException;

    long countByCondition(List<SearchCriteriaRequest> listConditionRequests) throws ApplicationException;
}
