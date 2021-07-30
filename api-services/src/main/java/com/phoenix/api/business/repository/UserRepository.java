package com.phoenix.api.business.repository;

import com.phoenix.api.business.model.User;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserRepository {
    long countByCondition(List<SearchCriteria> searchCriteriaList) throws SearchCriteriaException;

    List<User> findByCondition(List<SearchCriteria> searchCriteriaList) throws SearchCriteriaException, NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;
}
