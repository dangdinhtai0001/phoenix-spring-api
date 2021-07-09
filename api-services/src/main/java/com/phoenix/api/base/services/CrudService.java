/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:25 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/23/21, 9:29 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/23/21, 10:32 AM
 */

package com.phoenix.api.base.services;

import com.phoenix.api.base.entities.BaseEntity;

import java.util.Map;
import java.util.Optional;


/**
 * @param <T>
 */
public interface CrudService<T extends BaseEntity> {
    Iterable<T> findAll();

    Optional<T> add(Map payload) throws RuntimeException, Exception;

    Optional<T> update(Map payload) throws RuntimeException, Exception;

    Optional<T> remove(Map payload) throws RuntimeException, Exception;

    Optional<T> findById(Long id) throws RuntimeException, Exception;
}
