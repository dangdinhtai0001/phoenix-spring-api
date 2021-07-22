package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.core.exception.ServiceException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.specification.PredicateBuilder;

import java.util.List;

public interface BaseService {
    ServiceException getServiceException(String code);

    ExceptionEntity findExceptionByCode(String code);

    PredicateBuilder getPredicateBuilderFromSearchCriteria(PredicateBuilder predicate, List<SearchCriteria> conditions);
}
