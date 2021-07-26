package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.core.exception.ApplicationException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.specification.PredicateBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class AbstractBaseService implements BaseService {
    private final List<ExceptionEntity> exceptionEntities;

    protected AbstractBaseService(List<ExceptionEntity> exceptionEntities) {
        this.exceptionEntities = exceptionEntities;
    }

    public ApplicationException getApplicationException(String code) {
        ExceptionEntity exceptionEntity = findExceptionByCode(code);

        return new ApplicationException(exceptionEntity.getMessage(), code, this.getClass().getName(),
                HttpStatus.valueOf(exceptionEntity.getHttpCode()));
    }

    public ExceptionEntity findExceptionByCode(String code) {
        return exceptionEntities
                .stream()
                .filter(exceptionEntity -> code.equals(exceptionEntity.getCode()))
                .findFirst().orElse(null);
    }

    @Override
    public PredicateBuilder getPredicateBuilderFromSearchCriteria(PredicateBuilder predicate, List<SearchCriteria> conditions) {
        for (SearchCriteria criteria : conditions) {
            switch (criteria.getSearchOperation()) {
                case BETWEEN:
                    predicate.between(criteria.getKey(), criteria.getArguments().get(0), criteria.getArguments().get(1));
                    break;
                case EQUAL:
                    predicate.eq(criteria.getKey(), criteria.getArguments().get(0));
                    break;
                case GREATER_THAN_OR_EQUAL:
                    predicate.ge(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case GREATER_THAN:
                    predicate.gt(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case IN:
                    predicate.in(criteria.getKey(), criteria.getArguments());
                    break;
                case LESS_THAN_OR_EQUAL:
                    predicate.le(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case LIKE:
                    predicate.like(criteria.getKey(), String.valueOf(criteria.getArguments().get(0)));
                    break;
                case LESS_THAN:
                    predicate.lt(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case NOT_EQUAL:
                    predicate.ne(criteria.getKey(), criteria.getArguments().get(0));
                    break;
                case NOT_IN:
                    predicate.notIn(criteria.getKey(), criteria.getArguments());
                    break;
                case NOT_LIKE:
                    predicate.notLike(criteria.getKey(), String.valueOf(criteria.getArguments().get(0)));
                    break;
            }

        }

        return predicate;
    }

    @Override
    public String getRequestBodyByKey(Map requestBody, String key) {
        Object value = requestBody.get(key);

        if (value != null) {
            return String.valueOf(value);
        }
        return null;
    }
}
