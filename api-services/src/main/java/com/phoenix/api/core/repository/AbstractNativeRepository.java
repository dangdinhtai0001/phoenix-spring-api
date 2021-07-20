package com.phoenix.api.core.repository;

import com.phoenix.common.structure.Pair;
import com.phoenix.common.util.ReflectionUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class AbstractNativeRepository implements NativeRepository {
    private final EntityManager entityManager;

    public AbstractNativeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List executeNativeQuery(String sql, String... params) {
        Query query = createNativeQuery(sql, params);
        return query.getResultList();
    }

    @Override
    public int updateNativeQuery(String sql, String... params) {
        Query query = createNativeQuery(sql, params);
        return query.executeUpdate();
    }

    @Override
    public Object parseResult(Object[] record, List<Pair<String, Class>> params, Class aClass) throws NoSuchFieldException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Object target = aClass.newInstance();
        Constructor constructor = aClass.getConstructor();
        Object target = constructor.newInstance();
        Pair<String, Class> pair;
        for (int i = 0; i < params.size(); i++) {
            pair = params.get(i);
            ReflectionUtil.setField(pair.first(), pair.second(), String.valueOf(record[i]), target);
        }
        return target;
    }

    @Override
    public List parseResult(List<Object[]> results, List<Pair<String, Class>> params, Class aClass) throws NoSuchFieldException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object target;
        Pair<String, Class> pair;
        List<Object> list = new LinkedList<>();
        Constructor constructor = aClass.getConstructor();

        for (Object[] record : results) {
            target = constructor.newInstance();
            for (int i = 0; i < params.size(); i++) {
                pair = params.get(i);
                ReflectionUtil.setField(pair.first(), pair.second(), String.valueOf(record[i]), target);
            }
            list.add(target);
        }
        return list;
    }

    //    ================================================================
    //
    //    ================================================================

    private Query createNativeQuery(String sql, String... params) {
        Query query = entityManager.createNativeQuery(sql);

        int index = 1;
        for (String param : params) {
            query.setParameter(index++, param);
        }

        return query;
    }
}
