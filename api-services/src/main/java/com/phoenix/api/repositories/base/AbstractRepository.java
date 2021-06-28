/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 12:24 PM
 */

package com.phoenix.api.repositories.base;

import com.phoenix.api.entities.base.BaseEntity;
import com.phoenix.reflection.ReflectionUtil;
import com.phoenix.structure.Pair;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.Reflection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends BaseEntity> implements BaseRepository<T> {

    private final EntityManager entityManager;

    private final Class<T> typeParameterClass;

    public AbstractRepository(EntityManager entityManager, Class<T> typeParameterClass) {
        this.entityManager = entityManager;
        this.typeParameterClass = typeParameterClass;
    }


    @Override
    public Iterable<T> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(typeParameterClass);
        Root<T> root = cq.from(typeParameterClass);
        cq.select(root);
        return this.entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Optional<T> add(T object) throws RuntimeException, Exception {
        try {
            this.entityManager.persist(object);
            return Optional.of(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T object) throws RuntimeException, Exception {
        return Optional.ofNullable(this.entityManager.merge(object));
    }

    @Override
    public void remove(T object) throws RuntimeException, Exception {
        this.entityManager.remove(this.entityManager.merge(object));
    }

    @Override
    public Optional<T> findById(Long id) throws RuntimeException, Exception {
        T entity = entityManager.find(typeParameterClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean exists(Long id) {
        T entity = this.entityManager.find(this.typeParameterClass, id);
        return entity != null;
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