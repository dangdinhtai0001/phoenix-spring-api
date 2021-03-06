/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:14 PM
 */

package com.phoenix.api.base.repositories;

import com.phoenix.api.base.constant.BeanIds;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

public class SpecificationFactory<T> {
    public Specification<T> isEqual(String key, Object arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.EQUALITY, Collections.singletonList(arg)).build();
    }

    public Specification<T> isNotEqual(String key, Object arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.NOT_EQUAL, Collections.singletonList(arg)).build();
    }

    public Specification<T> isGreaterThan(String key, Comparable arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.GREATER_THAN, Collections.singletonList(arg)).build();
    }

    public Specification<T> isLessThan(String key, Comparable arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.LESS_THAN, Collections.singletonList(arg)).build();
    }

    public Specification<T> isGreaterThanOrEqual(String key, Comparable arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.GREATER_THAN_OR_EQUAL, Collections.singletonList(arg)).build();
    }

    public Specification<T> isLessThanOrEqual(String key, Comparable arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.LESS_THAN_OR_EQUAL, Collections.singletonList(arg)).build();
    }

    public Specification<T> isIn(String key, List<Object> args) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.IN, Collections.singletonList(args)).build();
    }

    public Specification<T> isNotIn(String key, List<Object> args) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, SearchOperation.NOT_IN, Collections.singletonList(args)).build();
    }
}
