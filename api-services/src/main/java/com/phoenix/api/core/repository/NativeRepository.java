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
    List executeNativeQuery(String sql, Object... params);

    @Modifying
    @Transactional
    int updateNativeQuery(String sql, String... params);

    Object parseResult(Object[] record, List<Pair<String, Class>> params, Class aClass)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

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
