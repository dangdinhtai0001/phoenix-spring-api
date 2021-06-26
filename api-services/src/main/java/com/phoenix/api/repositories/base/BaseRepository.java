/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 12:23 PM
 */

package com.phoenix.api.repositories.base;

import com.phoenix.api.entities.base.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends BaseEntity> {
    Iterable<T> findAll();

    Optional<T> add(T object) throws RuntimeException, Exception;

    Optional<T> update(T object) throws RuntimeException, Exception;

    void remove(T object) throws RuntimeException, Exception;

    Optional<T> findById(Long id) throws RuntimeException, Exception;

    boolean exists(Long id);

    List executeNativeQuery(String sql, String... params);
}
