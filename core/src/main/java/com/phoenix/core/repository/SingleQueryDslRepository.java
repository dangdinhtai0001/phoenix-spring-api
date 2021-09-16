package com.phoenix.core.repository;

import com.phoenix.core.model.query.OrderBy;
import com.phoenix.core.model.query.SearchCriteria;
import com.querydsl.core.Tuple;

import java.util.List;

public interface SingleQueryDslRepository {
    List<Tuple> defaultFindAll(String... columns);

    List<Tuple> defaultFindAll(List<SearchCriteria> criteriaList, String... columns);

    List<Tuple> defaultFindAll(List<SearchCriteria> criteriaList, OrderBy[] orders, String... columns);

    Long defaultInsert(List<com.phoenix.common.structure.Tuple> tuples);

    Long defaultInsert(com.phoenix.common.structure.Tuple tuple);
}
