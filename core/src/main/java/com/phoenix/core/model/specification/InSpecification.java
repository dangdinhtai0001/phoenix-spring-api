package com.phoenix.core.model.specification;

import javax.annotation.Nullable;
import javax.persistence.criteria.*;
import java.util.Collection;

public class InSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final transient Collection<?> values;

    public InSpecification(String property, Collection<?> values) {
        this.property = property;
        this.values = values;
    }

    @Override
    public Predicate toPredicate(@Nullable Root<T> root, @Nullable CriteriaQuery<?> query,
                                 @Nullable CriteriaBuilder criteriaBuilder) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        return from.get(field).in(values);
    }
}