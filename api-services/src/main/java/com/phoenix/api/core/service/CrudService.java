package com.phoenix.api.core.service;

import com.phoenix.api.core.model.SearchCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CrudService {
    Object add(Object entity);

    Object addAll(List entities);

    Object update(Object entity);

    void delete(Object entity);

    void deleteById(Object id);

    void deleteAllById(List ids);

    List findBy(List<SearchCriteria> conditions, Pageable pageable, Sort sort);

    long count(List<SearchCriteria> conditions);


}
