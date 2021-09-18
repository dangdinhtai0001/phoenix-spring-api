package com.phoenix.core.model.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Objects;

abstract class AbstractSpecification<T> implements Specification<T>, Serializable {
    public String getProperty(String property) {
        if (property.contains(".")) {
            return Objects.requireNonNull(StringUtils.split(property, "."))[1];
        }
        return property;
    }

    public From getRoot(String property, Root<T> root) {
        if (property.contains(".")) {
            String joinProperty = Objects.requireNonNull(StringUtils.split(property, "."))[0];
            return root.join(joinProperty, JoinType.LEFT);
        }
        return root;
    }
}
