/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 12:23 PM
 */

package com.phoenix.api.repositories.base;

import com.phoenix.api.entities.base.BaseEntity;
import com.phoenix.structure.Pair;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends BaseEntity> {
    /**
     * @return : Tìm tất cả
     */
    Iterable<T> findAll();

    /**
     * @param object : Object cần thêm
     * @return : Optional của object vừa truyền vào
     * - Optional empty nếu xảy ra lỗi
     * - Optional isPresent nếu add thành công
     * @throws RuntimeException
     * @throws Exception
     */
    Optional<T> add(T object) throws RuntimeException, Exception;

    /**
     * @param object : Object cần update
     * @return Optional của object vừa truyền vào
     * - Optional empty nếu xảy ra lỗi
     * - Optional isPresent nếu update thành công
     * @throws RuntimeException
     * @throws Exception
     */
    Optional<T> update(T object) throws RuntimeException, Exception;

    /**
     * @param object Object cần remove
     * @throws RuntimeException
     * @throws Exception
     */
    void remove(T object) throws RuntimeException, Exception;

    /**
     * @param id : Id của object cần tìm
     * @return Optional của object kết quả
     * - Optional empty nếu xảy ra lỗi/ không tìm ra
     * - Optional isPresent nếu tìm đc
     * @throws RuntimeException
     * @throws Exception
     */
    Optional<T> findById(Long id) throws RuntimeException, Exception;

    /**
     * @param id : Id của object cần kiểm tra
     * @return :
     * - true; nếu tồn tại
     * - false: nếu ko tồn tại
     */
    boolean exists(Long id);
}
