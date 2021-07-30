package com.phoenix.api.core.repository;

import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.common.structure.Pair;
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

    String getConditionClauseFromSearchCriteria(List<SearchCriteria> conditions) throws SearchCriteriaException;

    List<Object> getParameterFromSearchCriteria(List<SearchCriteria> conditions);
}
