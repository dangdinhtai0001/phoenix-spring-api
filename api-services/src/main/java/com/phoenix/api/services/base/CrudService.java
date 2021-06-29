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

import java.util.Map;
import java.util.Optional;

public interface CrudService<T extends BaseEntity> {
    Iterable<T> findAll();

    Optional<T> add(Map payload) throws RuntimeException, Exception;

    Optional<T> update(Map payload) throws RuntimeException, Exception;

    Optional<T> remove(Map payload) throws RuntimeException, Exception;

    Optional<T> findById(Long id) throws RuntimeException, Exception;
}
