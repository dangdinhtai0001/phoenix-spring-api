/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/23/21, 9:29 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/23/21, 10:32 AM
 */

package com.phoenix.api.services.base;

import com.phoenix.api.entities.base.BaseEntity;

import java.util.Optional;

public interface CrudService<T extends BaseEntity> {
    Iterable<T> findAll();

    T add(T obj) throws RuntimeException, Exception;

    T update(T obj) throws RuntimeException, Exception;

    int remove(Long key) throws RuntimeException, Exception;

    Optional<T> findById(Long id) throws RuntimeException, Exception;
}
