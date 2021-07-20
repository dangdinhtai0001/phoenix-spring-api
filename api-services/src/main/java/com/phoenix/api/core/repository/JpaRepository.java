package com.phoenix.api.core.repository;

import com.phoenix.api.core.entity.BaseEntity;
import com.phoenix.api.core.model.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface JpaRepository<T extends BaseEntity<ID>, ID> {

    Optional<T> add(T entity) throws Exception;

    @Transactional
    void addAll(List<T> entities) throws Exception;

    Optional<T> update(T entity) throws Exception;

    void delete(T entity) throws Exception;

    void deleteById(ID id) throws Exception;

    void deleteAllById(List<ID> ids) throws Exception;

    List<T> findAll();

    int count();

    List<T> findById(ID id) throws Exception;

    List<T> findAllById(List<ID> ids) throws Exception;


    List<T> findAllBySpecification(Specification<T> conditional) throws Exception;

    Page<T> findBySpecification(int page, int size, Specification<T> conditional) throws Exception;
    Page<T> findBySpecification(PageRequest pageRequest,Specification<T> conditional) throws Exception;
}
