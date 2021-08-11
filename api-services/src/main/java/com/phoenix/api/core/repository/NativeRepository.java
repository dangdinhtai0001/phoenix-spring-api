package com.phoenix.api.core.repository;

import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.BasePagination;
import com.phoenix.api.core.model.OrderBy;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.common.structure.Pair;
import com.phoenix.common.util.ReflectionUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface NativeRepository {
    /**
     * @param sql    Câu lệnh sql (hàm này chỉ dùng cho các lệnh ko cần thực hiện transaction như select )
     * @param params các param để truyền vào
     * @return Danh sách gồm các Object[]
     */
    List executeNativeQuery(String sql, Object... params);

    /**
     * @param sql    Câu lệnh sql (hàm này chỉ dùng cho các lệnh cần thực hiện transaction như insert, update )
     * @param params các param để truyền vào
     * @return số row bị tác động bởi lệnh sql
     */
    @Modifying
    @Transactional
    int updateNativeQuery(String sql, String... params);

    /**
     * <br/>
     * Hàm này dùng {@link ReflectionUtil#setField} để set giá trị cho instance của aClass
     * <br/>
     * Đối với Danh sách các {@link Pair} <br/>
     * <ul>
     * <li>E1 (String): Tên của field trong class target </li>
     * <li>E2 (Class): Type của field trong class target </li>
     * </ul>
     * <br/>
     * Thứ tự của các {@link Pair} phải trùng với thứ tự các kết quả của Object[], hay nói cách khác, phải trùng với thứ tự các column trong lệnh sql
     * <br/>
     * <br/>
     *
     * @param record thường sẽ là kết quả của hàm {@link NativeRepository#executeNativeQuery}
     * @param params Danh sách các {@link Pair}
     * @param aClass class target
     * @return 1 instance của class aClass
     * @throws NoSuchFieldException      see {@link ReflectionUtil#setField}
     * @throws IllegalAccessException    see {@link ReflectionUtil#setField}
     * @throws InstantiationException    see {@link ReflectionUtil#setField}
     * @throws NoSuchMethodException     see {@link ReflectionUtil#setField}
     * @throws InvocationTargetException see {@link ReflectionUtil#setField}
     */
    Object parseResult(Object[] record, List<Pair<String, Class>> params, Class aClass)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    /**
     * Tương tự: {{@link NativeRepository#parseResult(Object[], List, Class)}} nhưng áp dụng cho List các Object[] - chính là kết quả của hàm {@link NativeRepository#executeNativeQuery}
     */
    List parseResult(List<Object[]> results, List<Pair<String, Class>> params, Class aClass)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    /**
     * @param conditions Danh sách các SearchCriteria định nghĩa các biểu thức điều kiện
     * @return Chuỗi String điều kiện where
     * @throws SearchCriteriaException Khi thông tin định nghĩa biểu thức điều kiện lỗi
     */
    String getConditionClauseFromSearchCriteria(List<SearchCriteria> conditions) throws SearchCriteriaException;

    /**
     * @param conditions Danh sách các SearchCriteria định nghĩa các biểu thức điều kiện
     * @return List các parameter của các biểu thức điều kiện
     */
    List<Object> getParameterFromSearchCriteria(List<SearchCriteria> conditions);

    String getOderByClause(OrderBy order);

    /**
     * @param aClass      Class để parse kết quả lệnh query
     * @param pageRequest Định nghĩa pageIndex + pageSize
     * @param totalSql    Lệnh sql để tìm tổng số bản ghi
     * @param sql         Lệnh sql để tìm dữ liệu
     * @param params      Tham số của native query
     * @return BasePagination
     * @throws NoSuchFieldException      Xem {@link ReflectionUtil} (set Field)
     * @throws InvocationTargetException Xem {@link ReflectionUtil} (set Field)
     * @throws IllegalAccessException    Xem {@link ReflectionUtil} (set Field)
     * @throws InstantiationException    Xem {@link ReflectionUtil} (set Field)
     * @throws NoSuchMethodException     Xem {@link ReflectionUtil} (set Field)
     */
    BasePagination executeNativeQuery(Class aClass, PageRequest pageRequest, String totalSql, String sql, Object... params)
            throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;
}
