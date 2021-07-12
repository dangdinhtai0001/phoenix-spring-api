package com.phoenix.api.base.repositories;

import com.phoenix.api.base.entities.BaseEntity;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * @param <T> : Đối tượng Entity để map với bảng trong csdl
 *            <p>
 *            - Thực hiện các thao tác crud với @param <T>
 */
public abstract class AbstractBaseRepository<T extends BaseEntity> implements BaseRepository<T> {
    private final EntityManager entityManager;

    private final Class<T> typeParameterClass;

    public AbstractBaseRepository(EntityManager entityManager, Class<T> typeParameterClass) {
        this.entityManager = entityManager;
        this.typeParameterClass = typeParameterClass;
    }


    @Override
    public Iterable<T> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(typeParameterClass);
        Root<T> root = criteriaQuery.from(typeParameterClass);
        criteriaQuery.select(root);
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<T> add(T object) throws Exception {
        try {
            this.entityManager.persist(object);
            return Optional.of(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T object) throws Exception {
        return Optional.ofNullable(this.entityManager.merge(object));
    }

    @Override
    public void remove(T object) throws Exception {
        this.entityManager.remove(this.entityManager.merge(object));
    }

    @Override
    public Optional<T> findById(Long id) throws Exception {
        T entity = entityManager.find(typeParameterClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean exists(Long id) {
        T entity = this.entityManager.find(this.typeParameterClass, id);
        return entity != null;
    }

    @Override
    public Iterable<T> findBySpecification(Specification specification) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(typeParameterClass);
        Root<T> root = criteriaQuery.from(typeParameterClass);
        criteriaQuery.select(root);
        if (specification != null) {
            criteriaQuery.where(specification.toPredicate(root, criteriaQuery, builder));
        }
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public PagedListHolder<T> findBySpecificationAndPageRequest(Specification specification, PageRequest pageRequest) {
        List<T> queryResultList = (List<T>) findBySpecification(specification);

        PagedListHolder page = new PagedListHolder(queryResultList);
        page.setPageSize(pageRequest.getPageSize()); // number of items per page
        page.setPage(pageRequest.getPageNumber());      // set to first page

        return page;
    }
}