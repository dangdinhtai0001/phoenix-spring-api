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
 * @Created_date: 6/26/21, 12:24 PM
 */

package com.phoenix.api.base.repositories;

import com.phoenix.api.base.entities.BaseEntity;
import com.phoenix.reflection.ReflectionUtil;
import com.phoenix.structure.Pair;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

/**
 * @param <T> Đối tượng Entity để map với bảng trong csdl
 *            <p>
 *            Là kết hợp của AbstractBaseRepository và AbstractNativeRepository
 *            <p>
 *            Kế thừa AbstractBaseRepository và clone code từ AbstractNativeRepository
 */
public abstract class AbstractRepository<T extends BaseEntity> extends AbstractBaseRepository<T> implements BaseRepository<T>, NativeRepository {

    private final EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager, Class<T> typeParameterClass) {
        super(entityManager, typeParameterClass);
        this.entityManager = entityManager;
    }

    @Override
    public List executeNativeQuery(String sql, String... params) {
        Query query = createNativeQuery(sql, params);
        List<Object[]> result = query.getResultList();

        return result;
    }

    @Override
    @Modifying
    @Transactional
    public int updateNativeQuery(String sql, String... params) {
        Query query = createNativeQuery(sql, params);
        return query.executeUpdate();
    }

    private Query createNativeQuery(String sql, String... params) {
        Query query = entityManager.createNativeQuery(sql);

        int index = 1;
        for (String param : params) {
            query.setParameter(index++, param);
        }

        return query;
    }

    @Override
    public Object parseResult(Object[] record, List<Pair<String, Class>> params, Class aClass)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Object target = aClass.newInstance();
        Pair<String, Class> pair;
        for (int i = 0; i < params.size(); i++) {
            pair = params.get(i);
            ReflectionUtil.setField(pair.first(), pair.second(), String.valueOf(record[i]), target);
        }
        return target;
    }

    @Override
    public List parseResult(List<Object[]> results, List<Pair<String, Class>> params, Class aClass)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Object target;
        Pair<String, Class> pair;
        List<Object> list = new LinkedList<>();
        for (Object[] record : results) {
            target = aClass.newInstance();
            for (int i = 0; i < params.size(); i++) {
                pair = params.get(i);
                ReflectionUtil.setField(pair.first(), pair.second(), String.valueOf(record[i]), target);
            }
            list.add(target);
        }
        return list;
    }
}
