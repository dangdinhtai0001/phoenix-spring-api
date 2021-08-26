package com.phoenix.api.core.repository;

import com.phoenix.api.core.entity.BaseEntity;
import com.phoenix.api.core.exception.RepositoryException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.specification.PredicateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseJpaRepository<T extends BaseEntity<ID>, ID extends Serializable> extends AbstractBaseNativeRepository
        implements BaseJpaRepository<T, ID> {

    private final SimpleJpaRepository<T, ID> jpaRepository;

    public AbstractBaseJpaRepository(EntityManager entityManager, Class<T> typeParameterClass) {
        super(entityManager);
        jpaRepository = new SimpleJpaRepository<>(typeParameterClass, entityManager);
    }

    @Override
    public Specification<T> getSpecificationFromSearchCriteria(PredicateBuilder<T> predicateBuilder, List<SearchCriteria> conditions){
        for (SearchCriteria criteria : conditions) {
            switch (criteria.getSearchOperation()) {
                case BETWEEN:
                    predicateBuilder.between(criteria.getKey(), criteria.getArguments().get(0), criteria.getArguments().get(1));
                    break;
                case EQUAL:
                    predicateBuilder.eq(criteria.getKey(), criteria.getArguments().get(0));
                    break;
                case GREATER_THAN_OR_EQUAL:
                    predicateBuilder.ge(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case GREATER_THAN:
                    predicateBuilder.gt(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case IN:
                    predicateBuilder.in(criteria.getKey(), criteria.getArguments());
                    break;
                case LESS_THAN_OR_EQUAL:
                    predicateBuilder.le(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case LIKE:
                    predicateBuilder.like(criteria.getKey(), String.valueOf(criteria.getArguments().get(0)));
                    break;
                case LESS_THAN:
                    predicateBuilder.lt(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case NOT_EQUAL:
                    predicateBuilder.ne(criteria.getKey(), criteria.getArguments().get(0));
                    break;
                case NOT_IN:
                    predicateBuilder.notIn(criteria.getKey(), criteria.getArguments());
                    break;
                case NOT_LIKE:
                    predicateBuilder.notLike(criteria.getKey(), String.valueOf(criteria.getArguments().get(0)));
                    break;
            }
        }
        return predicateBuilder.build();
    }

    @Override
    public Specification<T> getSpecificationFromSearchCriteria(List<SearchCriteria> conditions, Predicate.BooleanOperator booleanOperator) {
        PredicateBuilder<T> predicateBuilder = new PredicateBuilder<>(booleanOperator);
        return getSpecificationFromSearchCriteria(predicateBuilder, conditions);
    }

    public SimpleJpaRepository<T, ID> getJpaRepository() {
        return jpaRepository;
    }

    @Override
    public long count() {
        return this.jpaRepository.count();
    }

    @Override
    public long count(Specification<T> spec) {
        return this.jpaRepository.count(spec);
    }

    @Override
    public void delete(T entity) throws RepositoryException {
        this.jpaRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        this.jpaRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) throws RepositoryException {
        this.jpaRepository.deleteAll(entities);
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        this.jpaRepository.deleteAllById(ids);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<ID> ids) {
        this.jpaRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void deleteAllInBatch() {
        this.jpaRepository.deleteAllInBatch();
    }

    @Override
    public void deleteAllInBatch(Iterable<T> entities) {
        this.jpaRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteById(ID id) throws RepositoryException {
        this.jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(ID id) throws RepositoryException {
        return this.jpaRepository.existsById(id);
    }

    @Override
    public List<T> findAll() {
        return this.jpaRepository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return this.jpaRepository.findAll(pageable);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return this.jpaRepository.findAll(sort);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return this.jpaRepository.findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return this.jpaRepository.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return this.jpaRepository.findAll(spec, sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) throws RepositoryException {
        return this.jpaRepository.findAllById(ids);
    }

    @Override
    public Optional<T> findById(ID id) throws RepositoryException {
        return this.jpaRepository.findById(id);
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return this.jpaRepository.findOne(spec);
    }

    @Override
    public T getById(ID id) {
        return this.jpaRepository.getById(id);
    }

    @Override
    public <S extends T> S save(S entity) {
        return this.jpaRepository.save(entity);
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return this.jpaRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        return this.jpaRepository.saveAllAndFlush(entities);
    }

    @Override
    public void flush() {
        this.jpaRepository.flush();
    }


}
