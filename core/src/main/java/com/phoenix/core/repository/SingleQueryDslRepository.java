package com.phoenix.core.repository;

import com.phoenix.core.model.query.OrderBy;
import com.phoenix.core.model.query.SearchCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.sql.RelationalPathBase;

import java.util.List;

public interface SingleQueryDslRepository {
    List<Tuple> defaultFindAll(String... columns);

    List<Tuple> defaultFindAll(List<SearchCriteria> criteriaList, String... columns);

    List<Tuple> defaultFindAll(List<SearchCriteria> criteriaList, OrderBy[] orders, String... columns);

    <T extends RelationalPathBase<T>> Long defaultInsert(List<com.phoenix.common.structure.Tuple> tuples);

    <T extends RelationalPathBase<T>> Long defaultInsert(com.phoenix.common.structure.Tuple tuple);
}
