/*
 * @Author Đặng Đình Tài
 * @Date 6/23/21, 10:32 AM
 */

package com.phoenix.api.services.auth.base;

import com.phoenix.api.entities.base.BaseEntity;

import java.util.Optional;

public interface BaseService<T extends BaseEntity> {
    Iterable<T> findAll();

    T add(T obj) throws RuntimeException, Exception;

    T edit(T obj) throws RuntimeException, Exception;

    void remove(Long key) throws RuntimeException, Exception;

    Optional<T> findById(Long id) throws RuntimeException, Exception;
}
