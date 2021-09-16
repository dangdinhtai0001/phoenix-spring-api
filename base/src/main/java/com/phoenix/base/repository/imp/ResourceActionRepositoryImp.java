package com.phoenix.base.repository.imp;

import com.google.gson.Gson;
import com.phoenix.api.model.querydsl.QFwResourceAction;
import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.ResourceActionModel;
import com.phoenix.base.repository.ResourceActionRepository;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.repository.AbstractSingleQueryDslRepository;
import com.querydsl.core.Tuple;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(BeanIds.RESOURCE_ACTION_REPOSITORY_IMP)
public class ResourceActionRepositoryImp extends AbstractSingleQueryDslRepository implements ResourceActionRepository {
    protected ResourceActionRepositoryImp(
            @Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory
    ) {
        super(queryFactory);
    }

    @Override
    public List<ResourceActionModel> findAll(String... fields) {
        List<Tuple> queryResult = this.defaultFindAll(fields);
        return parseResult(queryResult, ResourceActionModel.class, fields);
    }

    @Override
    public List<ResourceActionModel> findAll(List<SearchCriteria> criteriaList, String... fields) {
        List<Tuple> queryResult = this.defaultFindAll(criteriaList, fields);

        return parseResult(queryResult, ResourceActionModel.class, fields);
    }

    @Override
    public Long insertAll(List<com.phoenix.common.structure.Tuple> tuples) {
        return defaultInsert(tuples);
    }

    @Override
    protected Class getRelationalPathBaseClass() {
        return QFwResourceAction.class;
    }

    @Override
    protected RelationalPathBase getRelationalPathBase() {
        return QFwResourceAction.fwResourceAction;
    }
}
