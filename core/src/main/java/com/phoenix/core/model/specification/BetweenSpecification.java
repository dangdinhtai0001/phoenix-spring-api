package com.phoenix.api.core.model.specification;

import javax.annotation.Nullable;
import javax.persistence.criteria.*;


public class BetweenSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final transient Comparable<Object> lower;
    private final transient Comparable<Object> upper;

    public BetweenSpecification(String property, Object lower, Object upper) {
        this.property = property;
        this.lower = (Comparable<Object>) lower;
        this.upper = (Comparable<Object>) upper;
    }

    @Override
    public Predicate toPredicate(@Nullable Root<T> root, @Nullable CriteriaQuery<?> query, CriteriaBuilder cb) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        return cb.between(from.get(field), lower, upper);
    }
}
