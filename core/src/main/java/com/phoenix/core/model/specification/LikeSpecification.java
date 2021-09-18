package com.phoenix.core.model.specification;

import javax.annotation.Nullable;
import javax.persistence.criteria.*;

public class LikeSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final String[] patterns;

    public LikeSpecification(String property, String... patterns) {
        this.property = property;
        this.patterns = patterns;
    }

    @Override
    public Predicate toPredicate(@Nullable Root<T> root, @Nullable CriteriaQuery<?> query,
                                 @Nullable CriteriaBuilder criteriaBuilder) {
        assert criteriaBuilder != null;
        From from = getRoot(property, root);
        String field = getProperty(property);
        if (patterns.length == 1) {
            try {
                return criteriaBuilder.like(from.get(field), patterns[0]);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        Predicate[] predicates = new Predicate[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            predicates[i] = criteriaBuilder.like(from.get(field), patterns[i]);
        }
        return criteriaBuilder.or(predicates);
    }
}
