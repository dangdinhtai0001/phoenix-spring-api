/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:14 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 12:23 PM
 */

package com.phoenix.api.base.repositories;

import com.phoenix.api.base.entities.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

/**
 * @param <T> : Đối tượng Entity để map với abrng trong csdl
 */
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


    /**
     * @param specification : Điều kiện truy vấn.
     * @return Tìm tất cả các entity thỏa mãn điều kiện specification
     */
    Iterable<T> findBySpecification(Specification specification);
}
