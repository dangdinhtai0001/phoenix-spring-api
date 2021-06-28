/*
 * @Author Đặng Đình Tài
 * @Date 6/28/21, 11:08 AM
 */

package com.phoenix.api.services.base;

import com.phoenix.api.entities.base.BaseEntity;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.repositories.base.AbstractRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudAbstractService<T extends BaseEntity> extends AbstractService implements CrudService<T> {
    private final AbstractRepository<T> repository;

    protected CrudAbstractService(
            List<ExceptionEntity> exceptionEntities,
            AbstractRepository<T> repository
    ) {
        super(exceptionEntities);
        this.repository = repository;
    }

    public abstract T preAdd(T object);

    public abstract T preUpdate(T object);

    public abstract T preRemove(T object);

    public abstract T afterAdd(T object);

    public abstract T afterUpdate(T object);

    public abstract T afterRemove(T object);


    @Override
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> add(T obj) throws RuntimeException, Exception {
        preAdd(obj);
        Optional<T> optional = repository.add(obj);
        afterAdd(obj);

        return optional;
    }

    @Override
    public Optional<T> update(T obj) throws RuntimeException, Exception {
        preAdd(obj);
        Optional<T> optional = repository.update(obj);
        afterAdd(obj);

        return optional;
    }

    @Override
    public void remove(T obj) throws RuntimeException, Exception {
        preAdd(obj);
        repository.remove(obj);
        afterAdd(obj);
    }

    @Override
    public Optional<T> findById(Long id) throws RuntimeException, Exception {
        return repository.findById(id);
    }
}
