package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.core.entity.BaseEntity;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.specification.PredicateBuilder;

import javax.persistence.criteria.Predicate;
import java.util.List;

public abstract class AbstractJpaBaseService<T extends BaseEntity> extends AbstractBaseService implements JpaBaseService<T>{

    protected AbstractJpaBaseService(List<ExceptionEntity> exceptionEntities) {
        super(exceptionEntities);
    }

    @Override
    public PredicateBuilder<T> getPredicateBuilderFromSearchCriteria(PredicateBuilder<T> predicateBuilder, List<SearchCriteria> conditions) {
        for (SearchCriteria criteria : conditions) {
            switch (criteria.getSearchOperation()) {
                case BETWEEN:
                    predicateBuilder.between(criteria.getKey(), criteria.getArguments().get(0), criteria.getArguments().get(1));
                    break;
                case EQUAL:
                    predicateBuilder.eq(criteria.getKey(), criteria.getArguments().get(0));
                    break;
                case GREATER_THAN_OR_EQUAL:
                    predicateBuilder.ge(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case GREATER_THAN:
                    predicateBuilder.gt(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case IN:
                    predicateBuilder.in(criteria.getKey(), criteria.getArguments());
                    break;
                case LESS_THAN_OR_EQUAL:
                    predicateBuilder.le(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case LIKE:
                    predicateBuilder.like(criteria.getKey(), String.valueOf(criteria.getArguments().get(0)));
                    break;
                case LESS_THAN:
                    predicateBuilder.lt(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case NOT_EQUAL:
                    predicateBuilder.ne(criteria.getKey(), criteria.getArguments().get(0));
                    break;
                case NOT_IN:
                    predicateBuilder.notIn(criteria.getKey(), criteria.getArguments());
                    break;
                case NOT_LIKE:
                    predicateBuilder.notLike(criteria.getKey(), String.valueOf(criteria.getArguments().get(0)));
                    break;
            }
        }
        return predicateBuilder;
    }

    @Override
    public PredicateBuilder<T> getPredicateBuilderFromSearchCriteria(List<SearchCriteria> conditions, Predicate.BooleanOperator booleanOperator) {
        PredicateBuilder<T> predicateBuilder = new PredicateBuilder<>(booleanOperator);
        return getPredicateBuilderFromSearchCriteria(predicateBuilder, conditions);
    }


}
