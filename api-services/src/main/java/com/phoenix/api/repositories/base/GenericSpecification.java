package com.phoenix.api.repositories.base;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {
    private final SearchCriteria searchCriteria;

    public GenericSpecification(SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Object> arguments = searchCriteria.getArguments();
        Object arg = arguments.get(0);

        switch (searchCriteria.getSearchOperation()) {
            case EQUALITY:
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), arg);
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()), (Comparable) arg);
            case LESS_THAN:
                return criteriaBuilder.lessThan(root.get(searchCriteria.getKey()), (Comparable) arg);
            case IN:
                return root.get(searchCriteria.getKey()).in(arguments);
        }
        return null;
    }
}
