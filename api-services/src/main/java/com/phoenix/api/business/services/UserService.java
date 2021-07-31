package com.phoenix.api.business.services;

import com.phoenix.api.business.model.User;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.BasePagination;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchCriteriaRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserService {
    BasePagination findByCondition(List<SearchCriteriaRequest> conditions, int pageOffset, int pageSize, List<String> orderByKeys, String direction)
            throws SearchCriteriaException, NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    long countByCondition(List<SearchCriteriaRequest> listConditionRequests) throws SearchCriteriaException;
}
