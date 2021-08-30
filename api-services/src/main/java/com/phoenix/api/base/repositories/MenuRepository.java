package com.phoenix.api.base.repositories;

import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.BaseQueryDslRepository;

import java.util.List;

public interface MenuRepository extends BaseQueryDslRepository {
    List findAll();

    List findAll(List<SearchCriteria> searchCriteriaList);
}
