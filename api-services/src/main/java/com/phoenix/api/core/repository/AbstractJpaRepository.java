package com.phoenix.api.core.repository;

import com.phoenix.api.core.entity.BaseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJpaRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends AbstractBaseRepository implements JpaRepository<T, ID> {

    private final EntityManager entityManager;
    private final Class<T> typeParameterClass;

    private final int BATCH_SIZE = 10;

    public AbstractJpaRepository(EntityManager entityManager, Class<T> typeParameterClass) {
        super(entityManager);
        this.entityManager = entityManager;
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public Optional<T> add(T object) {
        try {
            this.entityManager.persist(object);
            return Optional.of(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void addAll(List<T> entities) throws Exception {
        int i = 0;
        for (T entity : entities) {
            if (i > 0 && i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            this.entityManager.persist(entity);
            i++;
        }
    }

    @Override
    public Optional<T> update(T object) throws Exception {
        return Optional.ofNullable(this.entityManager.merge(object));
    }

    @Override
    public void delete(T object) {
        this.entityManager.remove(this.entityManager.merge(object));
    }

    @Override
    public void deleteById(ID id) {
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(typeParameterClass);
        Root<T> root = criteriaQuery.from(typeParameterClass);
        criteriaQuery.select(root);
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }


    @Override
    public List<T> findAllBySpecification(Specification<T> specification) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(typeParameterClass);
        Root<T> root = criteriaQuery.from(typeParameterClass);
        criteriaQuery.select(root);
        if (specification != null) {
            criteriaQuery.where(specification.toPredicate(root, criteriaQuery, builder));
        }
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }


}
