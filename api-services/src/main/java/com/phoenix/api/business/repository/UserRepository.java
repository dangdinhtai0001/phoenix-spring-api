package com.phoenix.api.business.repository;

import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.BasePagination;
import com.phoenix.api.core.model.OrderBy;
import com.phoenix.api.core.model.SearchCriteria;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserRepository {
    long countByCondition(List<SearchCriteria> searchCriteriaList) throws SearchCriteriaException;

    BasePagination findByCondition(List<SearchCriteria> searchCriteriaList, int pageOffset, int pageSize, OrderBy orderBy)
            throws SearchCriteriaException, NoSuchFieldException, InvocationTargetException, IllegalAccessException,
            InstantiationException, NoSuchMethodException;
}
