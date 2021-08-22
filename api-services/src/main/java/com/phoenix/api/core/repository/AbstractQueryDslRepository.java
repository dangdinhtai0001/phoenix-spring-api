package com.phoenix.api.core.repository;

import com.phoenix.api.business.model.User;
import com.phoenix.api.core.annotation.BusinessObjectField;
import com.phoenix.api.core.model.*;
import com.phoenix.common.util.ReflectionUtil;
import com.querydsl.core.Tuple;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbstractQueryDslRepository implements QueryDslRepository {

    private final SQLQueryFactory queryFactory;

    protected AbstractQueryDslRepository(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    //---------------------------------

    protected abstract List<PathBuilder> getListPathBuilder();
    //---------------------------------

    /**
     * @param expressions              select clause
     * @param relationalPathBaseObject from clause
     * @return SQLQuery
     */
    @Override
    public SQLQuery<Tuple> createSelectQuery(Expression[] expressions, RelationalPathBase relationalPathBaseObject) {
        return queryFactory
                .select(expressions)
                .from(relationalPathBaseObject);
    }

    /**
     * @param expressions select clause
     * @param pathBuilder from clause
     * @return SQLQuery
     */
    @Override
    public SQLQuery<Tuple> createSelectQuery(Expression[] expressions, PathBuilder pathBuilder) {
        return queryFactory
                .select(expressions)
                .from(pathBuilder);
    }

    //---------------------------------

    @Override
    public String getTableName(RelationalPathBase relationalPathBase) {
        return relationalPathBase.getTableName();
    }

    @Override
    public PathBuilder getPathBuilder(String className, String tableName) throws ClassNotFoundException {
        Class<?> entityType = Class.forName(className);
        return new PathBuilder(entityType, tableName);
    }

    @Override
    public PathBuilder getPathBuilder(Class aClass, String tableName) {
        return new PathBuilder(aClass, tableName);
    }

    @Override
    public PathBuilder getPathBuilder(Class aClass, RelationalPathBase relationalPathBase) {
        String tableName = getTableName(relationalPathBase);
        return new PathBuilder(aClass, tableName);
    }

    @Override
    public PathBuilder getPathBuilder(List<PathBuilder> pathBuilders, String anotationTableName) {
        for (PathBuilder pathBuilder : pathBuilders) {
            if (anotationTableName.equals(pathBuilder.getMetadata().getName())) {
                return pathBuilder;
            }
        }
        return null;
    }

    //---------------------------------

    @Override
    public Expression[] getExpressions(PathBuilder pathBuilder, String... properties) {
        Expression[] expressions = new Expression[properties.length];

        for (int i = 0; i < expressions.length; i++) {
            expressions[i] = pathBuilder.get(properties[i]);
        }

        return expressions;
    }

    @Override
    public Expression[] mergeExpressions(Expression[]... expressions) {
        List<Expression> result = new LinkedList<>();

        for (Expression[] expression : expressions) {
            result.addAll(Arrays.asList(expression));
        }

        return result.toArray(new Expression[0]);
    }

    //---------------------------------

    @Override
    public QueryBase addWhereClause(SQLQuery query, Predicate predicate) {
        return query.where(predicate);
    }

    @Override
    public QueryBase addWhereClause(SQLQuery query, List<Predicate> predicates) {
        for (Predicate predicate : predicates) {
            query.where(predicate);
        }
        return query;
    }

    //---------------------------------

    @Override
    public Predicate getPredicateFromSearchCriteria(PathBuilder pathBuilder, SearchCriteria criteria) {
        String key = criteria.getKey();
        List<String> stringArguments = criteria.getArguments().stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

        switch (criteria.getSearchOperation()) {

            case BETWEEN:
                return pathBuilder.getString(key).between(
                        String.valueOf(stringArguments.get(0)),
                        String.valueOf(stringArguments.get(1))
                );
            //--------------------------------------
            case EQUAL:
                return pathBuilder.getString(key).eq(stringArguments.get(0));
            case GREATER_THAN_OR_EQUAL:
                return pathBuilder.getString(key).goe(stringArguments.get(0));
            case GREATER_THAN:
                return pathBuilder.getString(key).gt(stringArguments.get(0));
            case LESS_THAN_OR_EQUAL:
                return pathBuilder.getString(key).loe(stringArguments.get(0));
            case LIKE:
                return pathBuilder.getString(key).like(stringArguments.get(0));
            case LESS_THAN:
                return pathBuilder.getString(key).lt(stringArguments.get(0));
            case NOT_EQUAL:
                return pathBuilder.getString(key).ne(stringArguments.get(0));
            case NOT_LIKE:
                return pathBuilder.getString(key).notLike(stringArguments.get(0));
            //--------------------------------------
            case IN:
                return pathBuilder.getString(key).in(stringArguments);
            case NOT_IN:
                return pathBuilder.getString(key).notIn(stringArguments);
            default:
                return null;
        }
    }

    @Override
    public List<Predicate> getPredicateFromSearchCriteria(PathBuilder pathBuilder, List<SearchCriteria> searchCriteriaList) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            return predicates;
        }

        for (SearchCriteria criteria : searchCriteriaList) {
            predicates.add(getPredicateFromSearchCriteria(pathBuilder, criteria));
        }

        return predicates;
    }

    @Override
    public List<Predicate> getPredicateFromSearchCriteria(List<PathBuilder> pathBuilders, List<SearchCriteria> searchCriteriaList) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            return predicates;
        }

        String key;
        BusinessObjectField annotation;
        String annotationTableName;
        PathBuilder pathBuilder;

        for (SearchCriteria criteria : searchCriteriaList) {
            key = criteria.getKey();
            annotation = (BusinessObjectField) ReflectionUtil.getAnotationOfField(key, User.class, BusinessObjectField.class);
            annotationTableName = annotation.table();
            pathBuilder = getPathBuilder(pathBuilders, annotationTableName);

            predicates.add(getPredicateFromSearchCriteria(pathBuilder, criteria));
        }

        return predicates;
    }

    //---------------------------------

    @Override
    public List<Object> parseResult(List<Tuple> queryResult, Class<?> aClass, String... properties) {
        List result = new LinkedList<>();

        for (Tuple record : queryResult) {
            Object object = null;

            try {
                object = ReflectionUtil.createInstance(aClass);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < properties.length; i++) {
                try {
                    ReflectionUtil.setField(object, properties[i],
                            record.get(i, ReflectionUtil.getTypeOfFieldByName(aClass, properties[i])));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


            result.add(object);
        }

        return result;
    }

    @Transactional
    public BasePagination fetchWithPagination(PageRequest pageRequest, SQLQuery query, Class aClass, String... properties) {
        int limit = pageRequest.getPageSize();
        int offset = pageRequest.getPageNumber() * pageRequest.getPageSize();

        long total = query.fetchCount();

        query.limit(limit).offset(offset);
        List<Tuple> queryResult = query.fetch();
        List results = parseResult(queryResult, User.class, properties);

        // Create result
        Page page = new PageImpl(results, pageRequest, total);

        return new BasePagination(page);
    }

    //---------------------------------

    @Override
    public SQLQuery join(JoinType joinType, SQLQuery query, PathBuilder sourceBuilder, PathBuilder joinBuilder,
                         String sourceProperty, String joinProperty) {

        Predicate predicate = sourceBuilder.getString(sourceProperty).eq(joinBuilder.getString(joinProperty));

        if (joinType == JoinType.LEFT) {
            query.leftJoin(joinBuilder).on(predicate);
        }

        if (joinType == JoinType.RIGHT) {
            query.rightJoin(joinBuilder).on(predicate);
        }

        if (joinType == JoinType.INNER) {
            query.innerJoin(joinBuilder).on(predicate);
        }

        return query;
    }

    @Override
    public SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, String property, OrderDirection direction) {
        if (direction == OrderDirection.DESC) {
            query.orderBy(pathBuilder.getString(property).desc());
        }

        if (direction == OrderDirection.ASC) {
            query.orderBy(pathBuilder.getString(property).asc());
        }
        return query;
    }

    @Override
    public SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, OrderBy orderBy) {
        List<String> keys = orderBy.getKeys();

        for (String key : keys) {
            query = addOrderBy(query, pathBuilder, key, orderBy.getDirection());
        }

        return query;
    }


}
