package com.phoenix.api.business.services;

import com.phoenix.api.business.model.User;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchCriteriaRequest;

import java.util.List;

public interface UserService {
    List<User> findByCondition(List<SearchCriteriaRequest> conditions, int pageOffset, int pageSize);

    long countByCondition(List<SearchCriteriaRequest> listConditionRequests);
}
