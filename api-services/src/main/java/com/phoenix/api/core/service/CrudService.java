package com.phoenix.api.core.service;

import com.phoenix.api.core.model.SearchCriteria;

import java.util.Collection;
import java.util.List;

public interface CrudService {
    Object create(Object entity);

    List createAll(Collection entities);

    Object update(Object entity);

    void delete(Object entity);

    void deleteAll(Collection entities);

    List findByCondition(List<SearchCriteria> conditions, int pageOffset, int pageSize);

    long countByCondition(List<SearchCriteria> conditions);


}
