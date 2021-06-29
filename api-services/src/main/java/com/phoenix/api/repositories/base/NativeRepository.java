/*
 * @Author Đặng Đình Tài
 * @Date 6/29/21, 9:22 AM
 */

package com.phoenix.api.repositories.base;

import com.phoenix.structure.Pair;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NativeRepository {
    /**
     * @param sql    : Câu sql cần thực thi
     * @param params : danh sách param theo đúng thứ tự sẽ thêm vào
     * @return : Một List<Object[]> kết quả của câu sql
     */
    List executeNativeQuery(String sql, String... params);

    /**
     * @param sql    : Câu sql cần thực thi
     * @param params : danh sách param theo đúng thứ tự sẽ thêm vào
     * @return : số row chịu tác động của lệnh sql
     */
    @Modifying
    @Transactional
    int updateNativeQuery(String sql, String... params);

    /**
     * @param record  : Object[] thường là lấy từ kết quả của executeNativeQuery
     * @param params  : Danh sách các Pair (String: value | Class: class của trường đó, tương ứng với aClass )
     *                Cần phải theo đúng thứ tự xuất hiện trong object[]
     * @param aClass: Class của đối tượng cần parse
     * @return : Một instance của aClass
     * @throws NoSuchFieldException    : khi không tìm thấy value trong params ở danh sách các field của aClass
     * @throws IllegalAccessException: Khi không thể convert từ String sang kiểu giá trị tương ứng ở aClass
     * @throws InstantiationException  : khi không thể khởi tạo đc instance của aClass
     */
    Object parseResult(Object[] record, List<Pair<String, Class>> params, Class aClass)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException;

    /**
     * @param results : 1 Danh sách Object[]
     * @param params  :  Danh sách các Pair (String: value | Class: class của trường đó, tương ứng với aClass )
     *                Cần phải theo đúng thứ tự xuất hiện trong object[]
     * @param aClass  : Class của đối tượng cần parse
     * @return : 1 List các object.
     * @throws NoSuchFieldException    : khi không tìm thấy value trong params ở danh sách các field của aClass
     * @throws IllegalAccessException: Khi không thể convert từ String sang kiểu giá trị tương ứng ở aClass
     * @throws InstantiationException  : khi không thể khởi tạo đc instance của aClass
     */
    List parseResult(List<Object[]> results, List<Pair<String, Class>> params, Class aClass)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException;
}
