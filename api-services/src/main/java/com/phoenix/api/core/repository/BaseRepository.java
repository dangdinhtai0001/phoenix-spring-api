package com.phoenix.api.core.repository;

import com.phoenix.structure.Pair;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface BaseRepository {
    List executeNativeQuery(String sql, String... params);

    @Modifying
    @Transactional
    int updateNativeQuery(String sql, String... params);

    Object parseResult(Object[] record, List<Pair<String, Class>> params, Class aClass)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException;

    List parseResult(List<Object[]> results, List<Pair<String, Class>> params, Class aClass)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException;
}
