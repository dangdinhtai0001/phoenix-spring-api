package com.phoenix.core.repository.specification;

import static javax.persistence.criteria.Predicate.BooleanOperator.AND;
import static javax.persistence.criteria.Predicate.BooleanOperator.OR;

public final class Specifications {

    private Specifications() {
    }

    public static <T> PredicateBuilder<T> and() {
        return new PredicateBuilder<>(AND);
    }

    public static <T> PredicateBuilder<T> or() {
        return new PredicateBuilder<>(OR);
    }
}
