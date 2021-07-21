package com.phoenix.api.core.model.specification;

import javax.annotation.Nullable;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class InSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final transient Collection<?> values;

    public InSpecification(String property, Collection<?> values) {
        this.property = property;
        this.values = values;
    }

    @Override
    public Predicate toPredicate(@Nullable Root<T> root,@Nullable CriteriaQuery<?> query,@Nullable CriteriaBuilder cb) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        return from.get(field).in(values);
    }
}