package com.phoenix.api.core.service;

import com.phoenix.api.core.entity.BaseEntity;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.specification.PredicateBuilder;

import javax.persistence.criteria.Predicate;
import java.util.List;

public interface JpaBaseService<T extends BaseEntity> extends BaseService{
    PredicateBuilder<T> getPredicateBuilderFromSearchCriteria(PredicateBuilder<T> predicate, List<SearchCriteria> conditions);

    PredicateBuilder<T> getPredicateBuilderFromSearchCriteria(List<SearchCriteria> conditions, Predicate.BooleanOperator booleanOperator);
}
