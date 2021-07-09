/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:25 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/28/21, 11:08 AM
 */

package com.phoenix.api.base.services;

import com.phoenix.api.base.entities.BaseEntity;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.base.repositories.AbstractRepository;
import com.phoenix.reflection.ReflectionUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @param <T>: Đối tượng Entity để map với bảng trong csdl
 */
public abstract class AbstractCrudService<T extends BaseEntity> extends AbstractBaseService implements CrudService<T> {
    private final AbstractRepository<T> repository;
    private final Class<T> typeParameterClass;

    protected AbstractCrudService(
            List<ExceptionEntity> exceptionEntities,
            AbstractRepository<T> repository,
            Class<T> typeParameterClass) {
        super(exceptionEntities);
        this.repository = repository;
        this.typeParameterClass = typeParameterClass;
    }

    public T convertPayload(Map payload)
            throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        return (T) ReflectionUtil.convertMapToObject(payload, typeParameterClass);
    }

    public abstract void preAdd(T object);

    public abstract void preUpdate(T object);

    public abstract void preRemove(T object);

    public abstract void afterAdd(T object);

    public abstract void afterUpdate(T object);

    public abstract void afterRemove(T object);

    @Override
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> add(Map payload) throws RuntimeException, Exception {
        T obj = convertPayload(payload);
        preAdd(obj);
        Optional<T> optional = repository.add(obj);
        afterAdd(obj);

        return optional;
    }

    @Override
    public Optional<T> update(Map payload) throws RuntimeException, Exception {
        T obj = convertPayload(payload);
        preUpdate(obj);
        Optional<T> optional = repository.update(obj);
        afterUpdate(obj);

        return optional;
    }

    @Override
    public Optional<T> remove(Map payload) throws RuntimeException, Exception {
        T obj = convertPayload(payload);
        preRemove(obj);
        repository.remove(obj);
        afterRemove(obj);

        return Optional.ofNullable(obj);
    }

    @Override
    public Optional<T> findById(Long id) throws RuntimeException, Exception {
        return repository.findById(id);
    }
}
