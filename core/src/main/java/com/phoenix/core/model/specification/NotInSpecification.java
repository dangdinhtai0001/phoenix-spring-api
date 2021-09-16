package com.phoenix.api.core.model.specification;

import javax.annotation.Nullable;
import javax.persistence.criteria.*;
import java.util.Collection;

public class NotInSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final transient Collection<?> values;

    public NotInSpecification(String property, Collection<?> values) {
        this.property = property;
        this.values = values;
    }

    @Override
    public Predicate toPredicate(@Nullable Root<T> root, @Nullable CriteriaQuery<?> query, @Nullable CriteriaBuilder cb) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        return from.get(field).in(values).not();
    }
}
