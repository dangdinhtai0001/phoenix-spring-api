/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:14 PM
 */

package com.phoenix.api.base.repositories;

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
                if (arg instanceof String) {
                    return criteriaBuilder.like(root.<String>get(searchCriteria.getKey()), arg.toString().replace('*', '%'));
                } else if (arg == null) {
                    return criteriaBuilder.isNull(root.get(searchCriteria.getKey()));
                } else {
                    return criteriaBuilder.equal(root.get(searchCriteria.getKey()), arg);
                }
            case NOT_EQUAL: {
                if (arg instanceof String) {
                    return criteriaBuilder.notLike(root.<String>get(searchCriteria.getKey()), arg.toString().replace('*', '%'));
                } else if (arg == null) {
                    return criteriaBuilder.isNotNull(root.get(searchCriteria.getKey()));
                } else {
                    return criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), arg);
                }
            }
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()), (Comparable) arg);
            case LESS_THAN:
                return criteriaBuilder.lessThan(root.get(searchCriteria.getKey()), (Comparable) arg);
            case GREATER_THAN_OR_EQUAL:
                return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()), (Comparable) arg);
            case LESS_THAN_OR_EQUAL:
                return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()), (Comparable) arg);
            case IN:
                return root.get(searchCriteria.getKey()).in(arguments);
            case NOT_IN:
                return criteriaBuilder.not(root.get(searchCriteria.getKey()).in(arguments));

        }

        return null;
    }
}
