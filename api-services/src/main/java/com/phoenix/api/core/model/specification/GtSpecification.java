package com.phoenix.api.core.model.specification;

import javax.annotation.Nullable;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GtSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final transient Comparable<Object> compare;

    public GtSpecification(String property, Comparable<? extends Object> compare) {
        this.property = property;
        this.compare = (Comparable<Object>) compare;
    }

    @Override
    public Predicate toPredicate(@Nullable Root<T> root, @Nullable CriteriaQuery<?> query, CriteriaBuilder cb) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        return cb.greaterThan(from.get(field), compare);
    }
}