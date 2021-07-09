package com.phoenix.api.repositories.base;

import com.phoenix.api.constant.BeanIds;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component(BeanIds.SPECIFICATION_FACTORY)
public class SpecificationFactory<T> {
    public Specification<T> isEqual(String key, Object arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.EQUALITY, Collections.singletonList(arg)).build();
    }

    public Specification<T> isGreaterThan(String key, Comparable arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.GREATER_THAN, Collections.singletonList(arg)).build();
    }
}
