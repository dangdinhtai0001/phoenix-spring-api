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
import com.phoenix.api.base.repositories.GenericSpecificationsBuilder;
import com.phoenix.api.base.repositories.SearchCriteria;
import com.phoenix.api.base.repositories.SpecificationFactory;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.base.repositories.AbstractRepository;
import com.phoenix.api.entities.common.MenuEntity;
import com.phoenix.reflection.ReflectionUtil;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

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
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public LinkedHashMap findBy(int page, int size, List<SearchCriteria> conditional) throws Exception {
        PageRequest pageRequest = PageRequest.of(page, size);

        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        SpecificationFactory<T> specificationFactory = new SpecificationFactory<>();

        if (conditional != null && !conditional.isEmpty()) {
            for (SearchCriteria criteria : conditional) {
                switch (criteria.getSearchOperation()) {
                    case EQUALITY: {
                        builder.with(specificationFactory.isEqual(criteria.getKey(), criteria.getArguments().get(0)));
                        break;
                    }
                    case NOT_EQUAL: {
                        builder.with(specificationFactory.isNotEqual(criteria.getKey(), criteria.getArguments().get(0)));
                        break;
                    }
                    case GREATER_THAN: {
                        builder.with(specificationFactory.isGreaterThan(criteria.getKey(), (Comparable) criteria.getArguments().get(0)));
                        break;
                    }
                    case LESS_THAN: {
                        builder.with(specificationFactory.isLessThan(criteria.getKey(), (Comparable) criteria.getArguments().get(0)));
                        break;
                    }
                    case GREATER_THAN_OR_EQUAL: {
                        builder.with(specificationFactory.isGreaterThanOrEqual(criteria.getKey(), (Comparable) criteria.getArguments().get(0)));
                        break;
                    }
                    case LESS_THAN_OR_EQUAL: {
                        builder.with(specificationFactory.isLessThanOrEqual(criteria.getKey(), (Comparable) criteria.getArguments().get(0)));
                        break;
                    }
                    case IN: {
                        builder.with(specificationFactory.isIn(criteria.getKey(), criteria.getArguments()));
                        break;
                    }
                    case NOT_IN: {
                        builder.with(specificationFactory.isNotIn(criteria.getKey(), criteria.getArguments()));
                        break;
                    }
                    default:
                        break;
                }
            }
        }
        Specification specification = builder.build();

        PagedListHolder pagedListHolder = repository.findBySpecificationAndPageRequest(specification, pageRequest);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();

        response.put("content", pagedListHolder.getPageList());
        response.put("refreshDate", pagedListHolder.getRefreshDate());
        response.put("pageSize", pagedListHolder.getPageSize());
        response.put("page", pagedListHolder.getPage());
        response.put("total", pagedListHolder.getNrOfElements());
        response.put("pageCount", pagedListHolder.getPageCount());
        response.put("isFirstPage", pagedListHolder.isFirstPage());
        response.put("isLastPage", pagedListHolder.isLastPage());
        response.put("sort", pagedListHolder.getSort());


        return response;
    }
}
